package com.bfl.batch.jobs;

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
import com.bfl.dto.ItemDeleteDTO;
import com.bfl.gencode.apex.requests.ItemDeleteRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessItemDeleteDataJob")
public class ProcessItemDeleteDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessItemDeleteDataJob.class);

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
	
	String trnType = "ItemDelete";

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessItemDeleteDataJob job running...");
			List<ItemDeleteDTO> listOfDeletedItemsDTOs = postApexApiService.getItemDeleteRequest(trnType);
			try {
				if(null != listOfDeletedItemsDTOs && listOfDeletedItemsDTOs.size() > 0) {
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
					for(ItemDeleteDTO deleteItemDTO : listOfDeletedItemsDTOs) {
						ItemDeleteRequest itemDeleteRequest = new ItemDeleteRequest();
						if(null != storeId && !storeId.isEmpty()) {
							itemDeleteRequest.setCostCode(storeId);
						}
						else {
							itemDeleteRequest.setCostCode("");
						}
						itemDeleteRequest.setTrnDate(formatDateTime(deleteItemDTO.getTrnDate()));
						String datetime=formatDateTime(deleteItemDTO.getTrnDate());
						itemDeleteRequest.setTime1(datetime + " " + deleteItemDTO.getTime1());
						itemDeleteRequest.setInvoiceno(deleteItemDTO.getInvoiceNo());
						itemDeleteRequest.setItemCode(deleteItemDTO.getItemCode());
						itemDeleteRequest.setQty(deleteItemDTO.getQty());
						itemDeleteRequest.setPrice(deleteItemDTO.getsPrice());
						itemDeleteRequest.setUserId(deleteItemDTO.getUserId());
						itemDeleteRequest.setCompName(deleteItemDTO.getCompName());
						itemDeleteRequest.setApprovedBy(deleteItemDTO.getApprovedBy());
						itemDeleteRequest.setReason(deleteItemDTO.getReason());
						itemDeleteRequest.setUsername(deleteItemDTO.getUserName());
						MissingBarcodeDetailsResponse sendItemDeleteDetails = helper.sendItemDeleteDetails(request, itemDeleteRequest);
						if(null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && sendItemDeleteDetails.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", deleteItemDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && !sendItemDeleteDetails.getCode().equals("200")) {
							message = sendItemDeleteDetails.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", deleteItemDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessItemDeleteDataJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For item delete data : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, ItemDeleteDTO deleteItemDTO) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(deleteItemDTO.getInvoiceNo());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		String sourceOFDate = deleteItemDTO.getTrnDate();
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
		String trndate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tranDate);
		exportData.setTrnDateTime(trndate);
		exportData.setError(message);
		exportData.setExported(exported);
		return exportData;
	}

	public String formatDateTime(String Trndate) throws ParseException {
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Trndate);
		String trnDate =  new SimpleDateFormat("dd-MMM-yy").format(tranDate);
		return trnDate;
	}

}
