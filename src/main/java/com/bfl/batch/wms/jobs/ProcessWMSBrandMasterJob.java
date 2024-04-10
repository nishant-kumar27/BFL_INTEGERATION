package com.bfl.batch.wms.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.dto.BrandMasterDTO;
import com.bfl.gencode.merchhier.BrandMaster.BrandResponse;
import com.bfl.gencode.merchhier.BrandMaster.ItemBrandMaster;
import com.bfl.gencode.merchhier.BrandMaster.Link;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSBrandMasterJob")
public class ProcessWMSBrandMasterJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSBrandMasterJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessWMSBrandMasterJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC);
			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			String lastTimestamp = foundationDataService.getLastProcessingTimestamp(job.getJobId()); 
			try {
				String toDate = convertDateFormat(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
				String beforeDate = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				if(!StringUtils.isEmpty(lastTimestamp)) {
					fromDate = convertDateFormat(lastTimestamp);
					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
						@SuppressWarnings("static-access")
						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp).now(ZoneOffset.UTC);
						toDate = convertDateFormat(date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
					}
				} else {
					fromDate = convertDateFormat("2023-05-05T12:00:00.493+05:30");
				}
				BrandResponse response = null;
				List<BrandMasterDTO> brands = new ArrayList<>();
				int count = 0;
				do {
					if(response == null)
						response = getBrandMaster(mapper, fromDate, toDate, null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(null != links && links.size() > 0)
							response = getBrandMaster(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						count = count + response.getItems().size();
						brands.addAll(parseResponse(response));
					}
				} while(null != response && response.getHasMore());
				
				if(brands.size() > 0) {
					foundationDataService.persistBrands(brands);
				}
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setBrandData(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setBrandData(count);
					}
				}
//				if(lastTimestamp == null) {
//					foundationDataService.insertLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				} else {
//					foundationDataService.updateLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				}
				logger.info("ProcessWMSBrandMasterJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS for brand master : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private BrandResponse getBrandMaster(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("A");
		request.setToken(token);
		return helper.getBrandMaster(request);
	}
	
	private void setBrandData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = foundationDataService.getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getBrands());
			foundationDataService.updateBFLIntegrationData(business_date, "BRANDS", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands(String.valueOf(count));
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups("0");
			bflIntegrationDataDTO.setItemReclassify("0");
			bflIntegrationDataDTO.setItems("0");
			bflIntegrationDataDTO.setManifestUpload("0");
			bflIntegrationDataDTO.setOrderCancelled("0");
			bflIntegrationDataDTO.setOrderMod("0");
			bflIntegrationDataDTO.setOrderNew("0");
			bflIntegrationDataDTO.setPriceChanges("0");
			bflIntegrationDataDTO.setPromotions("0");
			bflIntegrationDataDTO.setShipments("0");
			bflIntegrationDataDTO.setSlashing("0");
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			foundationDataService.insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}
	
	private List<BrandMasterDTO> parseResponse(BrandResponse response) throws ParseException {
		List<BrandMasterDTO> brandDTOs = new ArrayList<>();
		if(response.getItems() != null) {
			for(ItemBrandMaster brands : response.getItems()) {
				BrandMasterDTO brandDTO = new BrandMasterDTO();
				brandDTO.setBrandName(brands.getBrandName());
				brandDTO.setBrandDescription(brands.getBrandDescription());
				brandDTO.setCreateDatetime(covertBrandDate(brands.getCreateDatetime()));
				brandDTO.setLastUpdateDatetime(covertBrandDate(brands.getLastUpdateDatetime()));
				brandDTOs.add(brandDTO);
			}
		}
		return brandDTOs;
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
	private String covertBrandDate(String createDateTime) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = inputFormat.parse(createDateTime);
		return outputFormat.format(d);
	}
	
}
