package com.bfl.batch.wms.jobs;

import java.util.List;

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
import com.bfl.gencode.merchhier.ItemMasterResponse.ItemMasterResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.ItemMasterHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessWMSItemLocRanging")
public class ProcessWMSItemLocRangingJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSItemLocRangingJob.class);
	
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
			logger.info("ProcessWMSItemLocRangingJob job running...");
			try {
				String goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				
				List<ItemMasterDTO> itemMasterData = foundationDataService.getItemLocRangingData(goLiveDate);
				
				if(null != itemMasterData && itemMasterData.size() > 0 ) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthToken();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					for(ItemMasterDTO itemMaster : itemMasterData) {
						ItemMasterLocRequest buildItemMaster = itemMasterHelperImpl.buildItemRaningData(itemMaster, "W", 0.0, null);
						if(null != buildItemMaster && null != buildItemMaster.getItems() && buildItemMaster.getItems().size() > 0) {
							ItemMasterResponse itemMasterResponse = helper.itemLocationRaning(request, buildItemMaster);
							if(null != itemMasterResponse && null != itemMasterResponse.getStatus() && itemMasterResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
								
							} else if(null != itemMasterResponse && null != itemMasterResponse.getValidationErrors() && itemMasterResponse.getValidationErrors().size() > 0) {
								
							}
						}
					}
				}
				logger.info("ProcessWMSItemLocRangingJob completed Successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS for location ranging : "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
}
