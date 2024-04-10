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
import com.bfl.dto.MissingBarcodeHeaderDTO;
import com.bfl.dto.StoreConfigDTO;
import com.bfl.gencode.apex.requests.MissingBarcodeHeaderRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessMissingBarcodeHeaderDataJob")
public class ProcessMissingBarcodeHeaderDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessMissingBarcodeHeaderDataJob.class);

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
	
	String trnType = "MissingBarcodeHeader";
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessMissingBarcodeHeaderDataJob job running...");
			List<MissingBarcodeHeaderDTO> listOfMissingBarcodeHeaderDataDTOs = postApexApiService.getMissingBarcodeHeaderRequest(trnType);
			try {
				if(null != listOfMissingBarcodeHeaderDataDTOs && listOfMissingBarcodeHeaderDataDTOs.size() > 0) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					String message = "";
					ApexDataExportDTO dataToBeExported = null;
					String storeId = "";
					StoreConfigDTO storeDto = null;
					try {
						storeId = ConfigProperties.getInstance().getConfigValue("STORE_ID");
						storeDto = foundationDataService.getStoreDTO(storeId);
					} catch(Exception e) {
						logger.error("Error while getting store details from properties file :- " + ExceptionUtils.getStackTrace(e));
					}
					for(MissingBarcodeHeaderDTO barcodeHeaderDTO : listOfMissingBarcodeHeaderDataDTOs) {
						MissingBarcodeHeaderRequest missingBarcodeHeaderDataRequest = new MissingBarcodeHeaderRequest();
						if(null != storeDto && null != storeDto.getStoreName() && !storeDto.getStoreName().isEmpty()) {
							missingBarcodeHeaderDataRequest.setShopname(storeDto.getStoreName());
						} else {
							missingBarcodeHeaderDataRequest.setShopname("");
						}
						missingBarcodeHeaderDataRequest.setBarcodePrintDate(barcodeHeaderDTO.getBarcodePrintDate());
						missingBarcodeHeaderDataRequest.setCostcode(storeId);
						missingBarcodeHeaderDataRequest.setDispatchDate(barcodeHeaderDTO.getDispatchDate());
						missingBarcodeHeaderDataRequest.setEntryNo(barcodeHeaderDTO.getEntryNo());
						missingBarcodeHeaderDataRequest.setLoccode(barcodeHeaderDTO.getLoccode());
						missingBarcodeHeaderDataRequest.setPreparedBy(barcodeHeaderDTO.getPreparedBy());
						missingBarcodeHeaderDataRequest.setReceivedBy(barcodeHeaderDTO.getReceivedBy());
						
						missingBarcodeHeaderDataRequest.setRemarks(barcodeHeaderDTO.getRemarks());
						missingBarcodeHeaderDataRequest.setShopManager(barcodeHeaderDTO.getShopManager());
						missingBarcodeHeaderDataRequest.setTime1(barcodeHeaderDTO.getTime1());
						if(null !=  barcodeHeaderDTO.getReceivedDate() && !barcodeHeaderDTO.getReceivedDate().isEmpty()) {
							missingBarcodeHeaderDataRequest.setReceivedDate(formatDateTime(barcodeHeaderDTO.getReceivedDate()));
						}
						missingBarcodeHeaderDataRequest.setTrndate(formatDateTime(barcodeHeaderDTO.getTrndate()));
						missingBarcodeHeaderDataRequest.setUserId(barcodeHeaderDTO.getUserId());
						MissingBarcodeDetailsResponse sendMissingBarcodeHeader = helper.sendMissingBarcodeHeaderDataRequest(request, missingBarcodeHeaderDataRequest);
						if(sendMissingBarcodeHeader != null && sendMissingBarcodeHeader.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", barcodeHeaderDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (sendMissingBarcodeHeader != null && sendMissingBarcodeHeader.getMessage() != null && !sendMissingBarcodeHeader.getCode().equals("200")) {
							message = sendMissingBarcodeHeader.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", barcodeHeaderDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessMissingBarcodeHeaderDataJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For missing barcode headers : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, MissingBarcodeHeaderDTO barcodeHeaderDTO) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(barcodeHeaderDTO.getEntryNo());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		String sourceOFDate = barcodeHeaderDTO.getTrndate();
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
		String trndate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tranDate);
		exportData.setTrnDateTime(trndate);
		exportData.setError(message);
		exportData.setExported(exported);
		return exportData;
	}

	public String formatDateTime(String entryDate) throws ParseException {
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entryDate);
		String trndate =  new SimpleDateFormat("dd-MMM-yy").format(tranDate);
		return trndate;
	}


}
