package com.bfl.batch.wms.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.ItemMasterConfigDTO;
import com.bfl.dto.ItemMasterDTO;
import com.bfl.gencode.merchhier.ItemMaster.ItemMasterRequest;
import com.bfl.gencode.merchhier.ItemMasterLocRequest.ItemMasterLocRequest;
import com.bfl.gencode.merchhier.ItemMasterRequest.ItemMasterReq;
import com.bfl.gencode.merchhier.ItemMasterRequest.Supplier;
import com.bfl.gencode.merchhier.ItemMasterResponse.ItemMasterResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.ItemMasterHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessWMSMissingItemCreation")
public class ProcessWMSMissingItemCreation extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSMissingItemCreation.class);
	
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
			logger.info("ProcessWMSMissingItemCreation job running...");
			String itemCode = "";
			try {
//				String goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				
//				List<ItemMasterDTO> itemMasterData = foundationDataService.getMissingItemMasterData(goLiveDate);
				
				List<ItemMasterDTO> itemMasterData = new ArrayList<ItemMasterDTO>();
				
				String databaseNames = ConfigProperties.getInstance().getConfigValue("Database_Names");
				
				if(null != databaseNames && !databaseNames.isEmpty()) {
					String [] splitDatabaseName = databaseNames.split(",");
					for(int i = 0; i < splitDatabaseName.length; i++) {
						itemMasterData = foundationDataService.getMissingItemsData(splitDatabaseName[i]);
						if(null != itemMasterData && itemMasterData.size() > 0 ) {
							FoundadtionRequest request = new FoundadtionRequest();
//							String token = helper.getAuthToken();
							String token = helper.getAuthTokenForProd();
							request.setUrl(null);
							request.setLimit(1);
							request.setToken(token);
							String message = "";
							for(ItemMasterDTO itemMaster : itemMasterData) {
								itemCode = itemMaster.getItemCode();
								ItemMasterReq buildItemMaster = itemMasterHelperImpl.buildItemMaster(itemMaster);
								if(null != buildItemMaster && null != buildItemMaster.getItems() && buildItemMaster.getItems().size() > 0) {
									ItemMasterConfigDTO itemMasterConfigDTO = null;
									ItemMasterResponse itemMasterResponse = helper.createItemMaster(request, buildItemMaster);
									if(null != itemMasterResponse && null != itemMasterResponse.getStatus() && itemMasterResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
										itemMasterConfigDTO = getItemMasterConfig(message, "Y", buildItemMaster);
										foundationDataService.setExportedItems(itemMasterConfigDTO);
										Double sellingUnitRetail = buildItemMaster.getItems().get(0).getRetailByZone().get(0).getSellingUnitRetail();
										Supplier supplier = buildItemMaster.getItems().get(0).getSupplier().get(0);
										ItemMasterLocRequest buildItemLocData = itemMasterHelperImpl.buildItemRaningData(itemMaster, "W", sellingUnitRetail, supplier);
										if(null != buildItemLocData && null != buildItemLocData.getItems() && buildItemLocData.getItems().size() > 0) {
											ItemMasterResponse itemMasterlocRanging = helper.itemLocationRaning(request, buildItemLocData);
											logger.info("Ranging info : " + itemMasterlocRanging);
										}
									} else if(null != itemMasterResponse && null != itemMasterResponse.getValidationErrors() && itemMasterResponse.getValidationErrors().size() > 0) {
										ItemMasterRequest checkItemMasterExist = helper.checkItemMasterExist(request, itemCode);
										if(null != checkItemMasterExist && checkItemMasterExist.getItems().size() > 0) {
											itemMasterConfigDTO = getItemMasterConfig(message, "Y", buildItemMaster);
											foundationDataService.setExportedItems(itemMasterConfigDTO);
										} else {
											message = itemMasterResponse.getValidationErrors().get(0).getError();
											itemMasterConfigDTO = getItemMasterConfig(message, "N", buildItemMaster);
											foundationDataService.setExportedItems(itemMasterConfigDTO);
										}
									}
								}
							}
						}
					}
				}				
				logger.info("ProcessWMSMissingItemCreation completed Successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName() + " Item Code is :: " + itemCode, ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private ItemMasterConfigDTO getItemMasterConfig(String message, String exported, ItemMasterReq buildItemMaster) {
		ItemMasterConfigDTO itemMasterConfigDTO = new ItemMasterConfigDTO();
		itemMasterConfigDTO.setCREATE_DATETIME(new Date());
		itemMasterConfigDTO.setExported(exported);
		itemMasterConfigDTO.setERROR(message);
		itemMasterConfigDTO.setItemCode(buildItemMaster.getItems().get(0).getItem());
		return itemMasterConfigDTO;
	}

	@SuppressWarnings("unused")
	private ItemMasterConfigDTO setItemMasterConfigERROR(String message, String exported, String itemCode) {
		ItemMasterConfigDTO itemMasterConfigDTO = new ItemMasterConfigDTO();
		itemMasterConfigDTO.setCREATE_DATETIME(new Date());
		itemMasterConfigDTO.setExported(exported);
		itemMasterConfigDTO.setERROR(message);
		itemMasterConfigDTO.setItemCode(itemCode);
		return itemMasterConfigDTO;
	}
}
