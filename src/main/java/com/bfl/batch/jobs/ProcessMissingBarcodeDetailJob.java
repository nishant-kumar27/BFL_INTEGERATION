package com.bfl.batch.jobs;

/**
@author deepak.walia
 */

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.MissingBarcodeDetailDTO;
import com.bfl.gencode.apex.requests.MissingBarcodeDetailsRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessMissingBarcodeDetailJob")
public class ProcessMissingBarcodeDetailJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessMissingBarcodeDetailJob.class);

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
	
	String trnType = "MissingBarcodeDetail";

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessMissingBarcodeDetailJob job running...");
			List<MissingBarcodeDetailDTO> listOfMissingBarcodeDetailDTOs = postApexApiService.getMissingBarcodeDetailsRequest(trnType);
			try {
				if(null != listOfMissingBarcodeDetailDTOs && listOfMissingBarcodeDetailDTOs.size() > 0) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					String message = "";
					ApexDataExportDTO dataToBeExported = null;
					for(MissingBarcodeDetailDTO missingBarcodeDetailDTO : listOfMissingBarcodeDetailDTOs) {
						MissingBarcodeDetailsRequest missingBarcodeDetailsRequest = new MissingBarcodeDetailsRequest();
						missingBarcodeDetailsRequest.setEntryNo(missingBarcodeDetailDTO.getEntryNo());
						missingBarcodeDetailsRequest.setItemCode(missingBarcodeDetailDTO.getItemCode());
						missingBarcodeDetailsRequest.setItemDesc(missingBarcodeDetailDTO.getItemDesc());
						missingBarcodeDetailsRequest.setQuantity(missingBarcodeDetailDTO.getQuantity());
						missingBarcodeDetailsRequest.setRemarks(missingBarcodeDetailDTO.getRemarks());
						missingBarcodeDetailsRequest.setSalesPrice(missingBarcodeDetailDTO.getPrice());
						MissingBarcodeDetailsResponse sendBarcodeHeaderDetails = helper.sendBarcodeHeaderDetails(request, missingBarcodeDetailsRequest);
						if(null != sendBarcodeHeaderDetails && null != sendBarcodeHeaderDetails.getCode() && sendBarcodeHeaderDetails.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", missingBarcodeDetailDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (null != sendBarcodeHeaderDetails && null != sendBarcodeHeaderDetails.getCode() && !sendBarcodeHeaderDetails.getCode().equals("200")) {
							message = sendBarcodeHeaderDetails.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", missingBarcodeDetailDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessMissingBarcodeDetailJob ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For missing barcode details : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, MissingBarcodeDetailDTO missingBarcodeDetailDTO) {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(missingBarcodeDetailDTO.getEntryNo());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
		exportData.setError(message);
		exportData.setExported(exported);
		return exportData;
	}
}
