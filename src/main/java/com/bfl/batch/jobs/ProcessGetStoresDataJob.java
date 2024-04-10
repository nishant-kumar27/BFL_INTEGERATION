package com.bfl.batch.jobs;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.dto.StoreConfigDTO;
import com.bfl.gencode.merchhier.StoreResponse.Item;
import com.bfl.gencode.merchhier.StoreResponse.Link;
import com.bfl.gencode.merchhier.StoreResponse.StoresResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessGetStoresDataJob")
public class ProcessGetStoresDataJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessGetStoresDataJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationDataService foundationDataService;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessGetStoresDataJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ZonedDateTime date = ZonedDateTime.now();
			
			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			String lastTimestamp = foundationDataService.getLastProcessingTimestamp(job.getJobId());
			String toDate= date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME); 
			try {
				if(!StringUtils.isEmpty(lastTimestamp)) {
					fromDate = lastTimestamp;
					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp);
						toDate= date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
					}
				} else {
					fromDate = "";
				}
				StoresResponse response = null;
				List<StoreConfigDTO> storesList = new ArrayList<>();
				do {
					if(response == null)
						response = getStoresData(mapper, fromDate, toDate, null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links != null && links.size() > 0)
							response = getStoresData(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					//Parse webservice response
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						storesList.addAll(parseResponse(response));
					}
					
				} while(null != response && response.getHasMore());
				
				if(storesList.size() > 0) {
					foundationDataService.persistStoresData(storesList);
				}
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
				}
				
				logger.info("ProcessGetStoresDataJob ended successfully");

			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS for Store Config DATA : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private StoresResponse getStoresData(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("A");
		request.setToken(token);
		return helper.getStoresData(request);
	}
	
	private List<StoreConfigDTO> parseResponse(StoresResponse response) {
		List<StoreConfigDTO> storesDTO = new ArrayList<>();
		for(Item store : response.getItems()) {
			StoreConfigDTO storeDto = new StoreConfigDTO();
			storeDto.setAction(store.getAction());
			storeDto.setCountry(store.getCountry());
			storeDto.setStatus("A");
			storeDto.setStoreId(String.valueOf(store.getStoreid()));
			storeDto.setStoreName(store.getStorename10());
			storeDto.setVatRegion(String.valueOf(store.getVatregion()));
			if(null != store.getStorename() && !store.getStorename().isEmpty()) {
				storeDto.setStoreName(store.getStorename().replaceAll("\\s", ""));
			}
			storesDTO.add(storeDto);
		}
		return storesDTO;
	}
	
}
