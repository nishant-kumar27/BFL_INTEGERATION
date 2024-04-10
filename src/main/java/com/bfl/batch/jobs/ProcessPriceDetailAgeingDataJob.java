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

import com.bfl.alerts.EmailService;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.PriceDetailsAgeingDTO;
import com.bfl.gencode.apex.requests.PriceDetailAgeingRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessPriceDetailAgeingDataJob")
public class ProcessPriceDetailAgeingDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessPriceDetailAgeingDataJob.class);

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
	
	String trnType = "PriceDetailAgeing";

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessPriceDetailAgeingDataJob job running...");
			List<PriceDetailsAgeingDTO> listOfPriceDetailAgeingDTOs = postApexApiService.getPriceDetailsAgeingRequest(trnType);
			try {
				if(null != listOfPriceDetailAgeingDTOs && listOfPriceDetailAgeingDTOs.size() > 0) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					String message = "";
					ApexDataExportDTO dataToBeExported = null;
					for(PriceDetailsAgeingDTO priceDetailAgeingDTO : listOfPriceDetailAgeingDTOs) {
						PriceDetailAgeingRequest priceDetailAgeingDataRequest = new PriceDetailAgeingRequest();
						priceDetailAgeingDataRequest.setCurrentDate(priceDetailAgeingDTO.getCurrentDate());
						priceDetailAgeingDataRequest.setEntryNo(priceDetailAgeingDTO.getEntryNo());
						priceDetailAgeingDataRequest.setIDNo(priceDetailAgeingDTO.getiDNo());
						priceDetailAgeingDataRequest.setItemCode(priceDetailAgeingDTO.getItemCode());
						priceDetailAgeingDataRequest.setNewPrice(priceDetailAgeingDTO.getNewPrice());
						priceDetailAgeingDataRequest.setQuantity(priceDetailAgeingDTO.getQuantity());
						priceDetailAgeingDataRequest.setRfid(priceDetailAgeingDTO.getRfid());
						priceDetailAgeingDataRequest.setSalesPrice(priceDetailAgeingDTO.getSalesPrice());
						priceDetailAgeingDataRequest.setStatus(priceDetailAgeingDTO.getStatus());
						String sourceOFDate = priceDetailAgeingDTO.getTrfDate();
						Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
						String trndate =  new SimpleDateFormat("dd-MMM-yy").format(tranDate);
						priceDetailAgeingDataRequest.setTrfDate(trndate);
						priceDetailAgeingDataRequest.setTrfNo(priceDetailAgeingDTO.getTrfNo());
						priceDetailAgeingDTO.setUserId(priceDetailAgeingDTO.getUserId());
						MissingBarcodeDetailsResponse sendPriceDetailAgeing = helper.sendPriceDetailAgeingData(request, priceDetailAgeingDataRequest);
						if(null != sendPriceDetailAgeing && null != sendPriceDetailAgeing.getCode() && sendPriceDetailAgeing.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", priceDetailAgeingDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendPriceDetailAgeing && null != sendPriceDetailAgeing.getCode() && !sendPriceDetailAgeing.getCode().equals("200")) {
							message = sendPriceDetailAgeing.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", priceDetailAgeingDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessPriceDetailAgeingDataJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For Price Detail Ageing: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, PriceDetailsAgeingDTO listOfPriceDetailAgeingDTOs) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(listOfPriceDetailAgeingDTOs.getEntryNo());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		exportData.setTrnDateTime(formatDateTime(listOfPriceDetailAgeingDTOs.getTrfDate()));
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