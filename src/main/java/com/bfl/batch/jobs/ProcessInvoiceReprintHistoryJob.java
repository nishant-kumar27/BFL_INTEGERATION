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
import com.bfl.dto.InvoiceReprintHistoryDTO;
import com.bfl.gencode.apex.requests.InvoiceReprintHistoryRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessInvoiceReprintHistoryJob")
public class ProcessInvoiceReprintHistoryJob extends AbstractJob{

	Logger logger = LoggerFactory.getLogger(ProcessInvoiceReprintHistoryJob.class);

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
	
	String trnType = "InvoiceReprintHistory";
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessInvoiceReprintHistoryJob job running...");
			List<InvoiceReprintHistoryDTO> listOfInvoiceReprintHistoryDTOs = postApexApiService.getInvoiceReprintHistoryRequest(trnType);
			try {
				if(null != listOfInvoiceReprintHistoryDTOs && listOfInvoiceReprintHistoryDTOs.size() > 0) {
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
					for(InvoiceReprintHistoryDTO invoiceReprintHistoryDTO : listOfInvoiceReprintHistoryDTOs) {
						InvoiceReprintHistoryRequest invoiceReprintHistoryRequest = new InvoiceReprintHistoryRequest();
						if(null != storeId && !storeId.isEmpty()) {
							invoiceReprintHistoryRequest.setShopName(storeId);
						} else {
							invoiceReprintHistoryRequest.setShopName("");
						}
						invoiceReprintHistoryRequest.setInvoiceno(invoiceReprintHistoryDTO.getInvoiceno());
						String sourceOFDate = invoiceReprintHistoryDTO.getTrndate();
						Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
						String trndate =  new SimpleDateFormat("dd-MMM-yy HH:mm:ss").format(tranDate);
						invoiceReprintHistoryRequest.setTrndate(trndate.substring(0, 10));
						invoiceReprintHistoryRequest.setUserid(invoiceReprintHistoryDTO.getUserid());
						invoiceReprintHistoryRequest.setReason(invoiceReprintHistoryDTO.getReason());
						invoiceReprintHistoryRequest.setMgrCode(invoiceReprintHistoryDTO.getMgrCode());
						invoiceReprintHistoryRequest.setMgrPass(invoiceReprintHistoryDTO.getMgrPass());
						invoiceReprintHistoryRequest.setMgrName(invoiceReprintHistoryDTO.getMgrName());
						MissingBarcodeDetailsResponse sendInvoiceReprintHistory = helper.sendInvoiceReprintHistoryData(request, invoiceReprintHistoryRequest);
						if(null != sendInvoiceReprintHistory && null != sendInvoiceReprintHistory.getCode() && sendInvoiceReprintHistory.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", invoiceReprintHistoryDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendInvoiceReprintHistory && null != sendInvoiceReprintHistory.getCode() && !sendInvoiceReprintHistory.getCode().equals("200")) {
							message = sendInvoiceReprintHistory.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", invoiceReprintHistoryDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessInvoiceReprintHistoryJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For Invoice Reprint History: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, InvoiceReprintHistoryDTO dto) {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(dto.getInvoiceno());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		try {
			String dateTime=formatDateTime(dto.getTrndate());
			exportData.setTrnDateTime(dateTime);
			logger.info("Date for Invoice reprint history in exp_data method is :" + dateTime);
		} catch (Exception e) {
			logger.error("Error occured while formatting date in InvoiceReprintHistory" + e);
		}
		exportData.setError(message);
		exportData.setExported(exported);
		return exportData;
	}

	public String formatDateTime(String Trndate) throws ParseException {
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Trndate);
		String trnDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tranDate);
		return trnDate;
	}

}
