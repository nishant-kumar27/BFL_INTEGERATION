package com.bfl.batch.jobs;

/**
@author deepak.walia
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.PriceHeaderAgeingDTO;
import com.bfl.gencode.apex.requests.PriceHeadAgeingRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessPriceHeaderAgeingDataJob")
public class ProcessPriceHeaderAgeingDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessPriceHeaderAgeingDataJob.class);

	@Autowired
	IJobConfigService jobconfigService;

	@Autowired
	EmailService emailService;

	@Autowired
	IWebServiceHelper helper;

	@Autowired
	PostApexApiService postApexApiService;

	@Autowired
	IFoundationDataService foundationDataService;
	
	String trnType = "PriceHeaderAgeing";
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessPriceHeaderAgeingDataJob job running...");
			List<PriceHeaderAgeingDTO> listOfPriceHeaderAgeingDTOs = postApexApiService.getPriceHeadAgeingRequest(trnType);
			for(PriceHeaderAgeingDTO dto : listOfPriceHeaderAgeingDTOs) {
				try {
					if(null != listOfPriceHeaderAgeingDTOs && listOfPriceHeaderAgeingDTOs.size() > 0) {
						FoundadtionRequest request = new FoundadtionRequest();
						String token = helper.getAuthTokenForProd();
						request.setUrl(null);
						request.setLimit(1);
						request.setToken(token);
						String message = "";
						ApexDataExportDTO dataToBeExported = null;
						String storeId = "";
						try {
							storeId = ConfigProperties.getInstance().getConfigValue("STORE_ID");
						} catch(Exception e) {
							logger.error("Error while getting store details from properties file :- " + ExceptionUtils.getStackTrace(e));
						}
						for(PriceHeaderAgeingDTO priceHeaderAgeingDTO : listOfPriceHeaderAgeingDTOs) {
							PriceHeadAgeingRequest priceHeaderAgeingDataRequest = new PriceHeadAgeingRequest();
							if(null != storeId && !storeId.isEmpty()) {
								priceHeaderAgeingDataRequest.setCostCode(storeId);
							} else {
								priceHeaderAgeingDataRequest.setCostCode("");
							}
							priceHeaderAgeingDataRequest.setShopname(priceHeaderAgeingDTO.getShopname());
							priceHeaderAgeingDataRequest.setAgeingOther(priceHeaderAgeingDTO.getAgeingOther());
							priceHeaderAgeingDataRequest.setCurrentDateTime(priceHeaderAgeingDTO.getCurrentDateTime());
							priceHeaderAgeingDataRequest.setDelivery(priceHeaderAgeingDTO.getDelivery());
							String sourceOFDate = priceHeaderAgeingDTO.getEntryDate();
							Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
							String trndate =  new SimpleDateFormat("dd-MMM-yy").format(tranDate);
							priceHeaderAgeingDataRequest.setEntryDate(trndate);
							priceHeaderAgeingDataRequest.setEntryNo(priceHeaderAgeingDTO.getEntryNo());
							priceHeaderAgeingDataRequest.setEntryType(priceHeaderAgeingDTO.getEntryType());
							priceHeaderAgeingDataRequest.setLabelType(priceHeaderAgeingDTO.getLabelType());
							priceHeaderAgeingDataRequest.setRemarks(priceHeaderAgeingDTO.getRemarks());
							priceHeaderAgeingDataRequest.setSubLocCode(priceHeaderAgeingDTO.getSubLocCode());
							priceHeaderAgeingDataRequest.setTrfNo1(priceHeaderAgeingDTO.getTrfNo1());
							priceHeaderAgeingDataRequest.setTrfNo2(priceHeaderAgeingDTO.getTrfNo2());
							priceHeaderAgeingDataRequest.setUserId(priceHeaderAgeingDTO.getUserId());
							MissingBarcodeDetailsResponse sendPriceHeaderAgeing = helper.sendPriceHeaderAgeingData(request, priceHeaderAgeingDataRequest);
							if(null != sendPriceHeaderAgeing && null != sendPriceHeaderAgeing.getCode() && sendPriceHeaderAgeing.getCode().equals("200")) {
								dataToBeExported = getTransactionConfigDTO(message, "Y", dto);
								postApexApiService.sendDataInExportedTable(dataToBeExported);
							} else if (null != sendPriceHeaderAgeing && null != sendPriceHeaderAgeing.getCode() && !sendPriceHeaderAgeing.getCode().equals("200")) {
								message = sendPriceHeaderAgeing.getMessage();
								dataToBeExported = getTransactionConfigDTO(message, "N", dto);
								postApexApiService.sendDataInExportedTable(dataToBeExported);
							}
						}
						logger.info("ProcessPriceHeaderAgeingDataJob ended successfully");
					}
				} catch (Exception e) {
					logger.error("Error occured while publishing the messages to APEX For Price Header Ageing: " + ExceptionUtils.getStackTrace(e));
					emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
				}
			}}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, PriceHeaderAgeingDTO dto) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(dto.getEntryNo());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		exportData.setTrnDateTime(formatDateTime(dto.getEntryDate()));
		exportData.setError(message);
		exportData.setExported(exported);
		return exportData;
	}

	public String formatDateTime(String entryDate) throws ParseException {
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entryDate);
		String trndate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tranDate);
		return trndate;
	}


}
