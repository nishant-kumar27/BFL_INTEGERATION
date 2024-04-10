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
import com.bfl.dto.BflEwalletLogDTO;
import com.bfl.gencode.apex.requests.BflEwalletLogRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessBflEWalletLogDataJob")
public class ProcessBflEWalletLogDataJob extends AbstractJob {

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
	
	String trnType = "BFLEWALLETLOG";

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessBflEWalletLogDataJob job running...");
			List<BflEwalletLogDTO> listOfBflEwalletLogDTOs = postApexApiService.getBflEwalletLogRequest(trnType);
			try {
				if(null != listOfBflEwalletLogDTOs && listOfBflEwalletLogDTOs.size() > 0) {
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
					for(BflEwalletLogDTO eWalletLogDTO : listOfBflEwalletLogDTOs) {
						BflEwalletLogRequest eWalletLogRequest = new BflEwalletLogRequest();
						if(null != storeId && !storeId.isEmpty()) {
							eWalletLogRequest.setShopname(storeId);
						} else {
							eWalletLogRequest.setShopname(" ");
						}
						eWalletLogRequest.setAmount(eWalletLogDTO.getAmount());
						eWalletLogRequest.setBalance(eWalletLogDTO.getBalance());
						eWalletLogRequest.setBonusAmount(eWalletLogDTO.getBonusAmount());
						eWalletLogRequest.setCardNo(eWalletLogDTO.getCardNo());
						eWalletLogRequest.setCardtype(eWalletLogDTO.getCardtype());
						eWalletLogRequest.setClosed(eWalletLogDTO.getClosed());
						eWalletLogRequest.setConfirmationno(eWalletLogDTO.getConfirmationno());
						eWalletLogRequest.setConfirmedBalance(eWalletLogDTO.getConfirmedBalance());
						eWalletLogRequest.setCreditReload(eWalletLogDTO.getCreditReload());
						eWalletLogRequest.setEntryDate(formatDateTime(eWalletLogDTO.getEntryDate()));
						eWalletLogRequest.setLoyaltyReload(eWalletLogDTO.getLoyaltyReload());
						eWalletLogRequest.setMobile(eWalletLogDTO.getMobile());
						eWalletLogRequest.setNormalReload(eWalletLogDTO.getNormalReload());
						eWalletLogRequest.setOnlineCode(eWalletLogDTO.getOnlineCode());
						eWalletLogRequest.setOtherReload(eWalletLogDTO.getOtherReload());
						eWalletLogRequest.setPayMode(eWalletLogDTO.getPayMode());
						eWalletLogRequest.setPointsAdded(eWalletLogDTO.getPointsAdded());
						eWalletLogRequest.setPointsbalance(eWalletLogDTO.getPointsbalance());
						eWalletLogRequest.setPreviousBalance(eWalletLogDTO.getPreviousBalance());
						eWalletLogRequest.setPreviouspoints(eWalletLogDTO.getPreviouspoints());
						eWalletLogRequest.setReconciled(eWalletLogDTO.getReconciled());
						eWalletLogRequest.setRecordno(eWalletLogDTO.getRecordno());
						eWalletLogRequest.setRefNo(eWalletLogDTO.getRefNo());
						eWalletLogRequest.setSn(eWalletLogDTO.getSn());
						eWalletLogRequest.setStatus(eWalletLogDTO.getStatus());
						eWalletLogRequest.setTrndate(formatDateTime(eWalletLogDTO.getTrndate()));
						eWalletLogRequest.setTrnno(eWalletLogDTO.getTrnno());
						MissingBarcodeDetailsResponse sendItemDeleteDetails = helper.sendEWalletLogData(request, eWalletLogRequest);
						if(null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && sendItemDeleteDetails.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", eWalletLogDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendItemDeleteDetails && null != sendItemDeleteDetails.getCode() && !sendItemDeleteDetails.getCode().equals("200")) {
							message = sendItemDeleteDetails.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", eWalletLogDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					
					logger.info("ProcessBflEWalletLogDataJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For ProcessBflEWalletLogData : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, BflEwalletLogDTO eWalletLogDTO) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(eWalletLogDTO.getSn());
		exportData.setTable_Name("EWalletLog");
		exportData.setTran_Type(trnType);
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
