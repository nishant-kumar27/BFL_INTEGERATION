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

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.ItemMasterLocRequest;
import com.bfl.gencode.merchhier.ItemMasterRequest.ItemMasterReq;
import com.bfl.gencode.merchhier.ItemMasterRequest.Supplier;
import com.bfl.gencode.merchhier.ItemMasterResponse.ItemMasterResponse;
import com.bfl.gencode.resa.MissingResaItems.Link;
import com.bfl.gencode.resa.MissingResaItems.MissingResaItemsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.ItemMasterHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessResaMissingItemsJob")
public class ProcessResaMissingItemsJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessResaMissingItemsJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;
	
	@Autowired
	ItemMasterHelperImpl itemMasterHelperImpl;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessResaMissingItemsJob job running...");
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
//						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp);
						toDate = convertDateFormat(date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
					}
				} else {
					fromDate = convertDateFormat("2023-05-05T12:00:00.493+05:30");
				}
				MissingResaItemsResponse response = null;
				do {
					if(response == null) {
						response = getMissingResaItems(mapper, fromDate, toDate, null);
					} else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(null != links && links.size() > 0)
							response = getMissingResaItems(mapper,fromDate,toDate,links.get(0).getHref());
						else
							break;
					}
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						foundationDataService.persistMissingResaItems(response);
					}
				} while(null != response && response.getHasMore());
				
//				List<ItemMasterDTO> missingResaItems = foundationDataService.getMissingResaItems();
				List<ItemMasterDTO> missingResaItems = new ArrayList<ItemMasterDTO>();
				
				String databaseNames = ConfigProperties.getInstance().getConfigValue("Database_Names");
				
				if(null != databaseNames && !databaseNames.isEmpty()) {
					String [] splitDatabaseName = databaseNames.split(",");
					for(int i = 0; i < splitDatabaseName.length; i++) {
						missingResaItems = foundationDataService.getMissingItemsData(splitDatabaseName[i]);
						String itemCode = "";
						if(null != missingResaItems && missingResaItems.size() > 0 ) {
							FoundadtionRequest request = new FoundadtionRequest();
							String token = helper.getAuthTokenForProd();
							request.setUrl(null);
							request.setLimit(1);
							request.setToken(token);
							for(ItemMasterDTO itemMaster : missingResaItems) {
								itemCode = itemMaster.getItemCode();
								ItemMasterReq buildItemMaster = itemMasterHelperImpl.buildItemMaster(itemMaster);
								if(null != buildItemMaster && null != buildItemMaster.getItems() && buildItemMaster.getItems().size() > 0) {
									ItemMasterResponse itemMasterResponse = helper.createItemMaster(request, buildItemMaster);
									if(null != itemMasterResponse && null != itemMasterResponse.getStatus() && itemMasterResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
										foundationDataService.deleteCreatedItems(itemCode);
										Double sellingUnitRetail = buildItemMaster.getItems().get(0).getRetailByZone().get(0).getSellingUnitRetail();
										Supplier supplier = buildItemMaster.getItems().get(0).getSupplier().get(0);
										ItemMasterLocRequest buildItemLocData = itemMasterHelperImpl.buildItemRaningData(itemMaster, "W", sellingUnitRetail, supplier);
										if(null != buildItemLocData && null != buildItemLocData.getItems() && buildItemLocData.getItems().size() > 0) {
											ItemMasterResponse itemMasterlocRanging = helper.itemLocationRaning(request, buildItemLocData);
											logger.info("Ranging info : " + itemMasterlocRanging);
										}
									}
								}
							}
						}
					}
				}
				foundationDataService.truncateResaItems();
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				}
				logger.info("ProcessResaMissingItemsJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS For purchase Orders : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private MissingResaItemsResponse getMissingResaItems(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		return helper.getMissingResaItems(request);
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
}
