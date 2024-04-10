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
import com.bfl.dto.GiftCardEntryLogDTO;
import com.bfl.gencode.apex.requests.GiftCardEntryLogRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessGiftCardLogJob")
public class ProcessGiftCardLogJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessGiftCardLogJob.class);

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
	
	String trnType = "GiftCardLog";
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessGiftCardLogJob job running...");
			List<GiftCardEntryLogDTO> listOfGiftCardLogDTOs = postApexApiService.getGiftCardLogRequest(trnType);
			try {
				if(null != listOfGiftCardLogDTOs && listOfGiftCardLogDTOs.size() > 0) {
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
					for(GiftCardEntryLogDTO giftCardDTO : listOfGiftCardLogDTOs) {
						GiftCardEntryLogRequest giftCardLog = new GiftCardEntryLogRequest();
						if(null != storeId && !storeId.isEmpty()) {
							giftCardLog.setShopName(storeId);
						} else {
							giftCardLog.setShopName(" ");
						}
						giftCardLog.setAmount(giftCardDTO.getAmount());
						giftCardLog.setCardNo(giftCardDTO.getCardNo());
						giftCardLog.setEmail(giftCardDTO.getEmail());
						giftCardLog.setFirstName(giftCardDTO.getEmail());
						giftCardLog.setGender(giftCardDTO.getGender());
						giftCardLog.setLastName(giftCardDTO.getLastName());
						giftCardLog.setMiddleName(giftCardDTO.getMiddleName());
						giftCardLog.setMobile(giftCardDTO.getMobile());
						giftCardLog.setPayMode(giftCardDTO.getPayMode());
						giftCardLog.setRefNo(giftCardDTO.getRefNo());
						giftCardLog.setSn(giftCardDTO.getSn());
						giftCardLog.setStatus(giftCardDTO.getStatus());
						giftCardLog.setTrndate(formatDateTime(giftCardDTO.getTrndate()));
						giftCardLog.setTrnno(giftCardDTO.getTrnno());
						MissingBarcodeDetailsResponse sendItemDeleteDetails = helper.sendGiftCardLogData(request, giftCardLog);
						if(null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && sendItemDeleteDetails.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", giftCardDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && !sendItemDeleteDetails.getCode().equals("200")) {
							message = sendItemDeleteDetails.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", giftCardDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessGiftCardLogJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For gift Card entry : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, GiftCardEntryLogDTO eWalletLogDTO) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(eWalletLogDTO.getSn());
		exportData.setTable_Name("GiftCardLog");
		exportData.setTran_Type("GiftCardLog");
		String sourceOFDate = eWalletLogDTO.getTrndate();
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
