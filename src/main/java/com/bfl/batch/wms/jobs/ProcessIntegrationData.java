package com.bfl.batch.wms.jobs;

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
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.ApexDataExportDTO;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.gencode.apex.requests.BFLIntegrationDataRequest;
import com.bfl.gencode.apex.response.MissingBarcodeDetailsResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.PostApexApiService;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessIntegrationData")
public class ProcessIntegrationData extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessIntegrationData.class);

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
	
	String trnType = "BFLIntegrationData";
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessIntegrationData job running...");
			List<BflIntegrationDataDTO> listOfMissingBarcodeHeaderDataDTOs = postApexApiService.getBflIntegrationDataRequest();
			try {
				if(null != listOfMissingBarcodeHeaderDataDTOs && listOfMissingBarcodeHeaderDataDTOs.size() > 0) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					String message = "";
					ApexDataExportDTO dataToBeExported = null;
					for(BflIntegrationDataDTO bflIntegrationDTO : listOfMissingBarcodeHeaderDataDTOs) {
						BFLIntegrationDataRequest bflIntegrationData = new BFLIntegrationDataRequest();
						
						bflIntegrationData.setCONTAINER_MAPPING(bflIntegrationDTO.getContainerMapping());
						bflIntegrationData.setCONTAINER_ID(bflIntegrationDTO.getContainerId());
						bflIntegrationData.setBRANDS(bflIntegrationDTO.getBrands());
						bflIntegrationData.setBUSINESS_DATE(formatDateTime(bflIntegrationDTO.getBusinessDate()));
						bflIntegrationData.setCATEGORY(bflIntegrationDTO.getCategory());
						bflIntegrationData.setCLEARANCES(bflIntegrationDTO.getSlashing());
						bflIntegrationData.setDEPT(bflIntegrationDTO.getDept());
						bflIntegrationData.setDIVISION(bflIntegrationDTO.getDivision());
						bflIntegrationData.setITEM_GROUPS(bflIntegrationDTO.getItemGroups());
						bflIntegrationData.setITEM_RECLASSIFY(bflIntegrationDTO.getItemReclassify());
						bflIntegrationData.setITEMS(bflIntegrationDTO.getItems());
						bflIntegrationData.setMANIFEST_UPLOAD(bflIntegrationDTO.getManifestUpload());
						bflIntegrationData.setORDER_CANCELLED(bflIntegrationDTO.getOrderCancelled());
						bflIntegrationData.setORDER_MOD(bflIntegrationDTO.getOrderMod());
						bflIntegrationData.setORDER_NEW(bflIntegrationDTO.getOrderNew());
						bflIntegrationData.setPRICE_CHANGES(bflIntegrationDTO.getPriceChanges());
						bflIntegrationData.setPROMOTIONS(bflIntegrationDTO.getPromotions());
						bflIntegrationData.setSHIPMENTS(bflIntegrationDTO.getShipments());
						bflIntegrationData.setSUBCATEGORY(bflIntegrationDTO.getSubCategory());
						bflIntegrationData.setSUPPLIERS(bflIntegrationDTO.getSuppliers());
						
						MissingBarcodeDetailsResponse sendMissingBarcodeHeader = helper.sendBFLIntegrationDataRequest(request, bflIntegrationData);
						if(sendMissingBarcodeHeader != null && sendMissingBarcodeHeader.getCode().equals("200")) {
							dataToBeExported = getTransactionConfigDTO(message, "Y", bflIntegrationDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						} else if (sendMissingBarcodeHeader != null && sendMissingBarcodeHeader.getMessage() != null && !sendMissingBarcodeHeader.getCode().equals("200")) {
							message = sendMissingBarcodeHeader.getMessage();
							dataToBeExported = getTransactionConfigDTO(message, "N", bflIntegrationDTO);
							postApexApiService.sendDataInExportedTable(dataToBeExported);
						}
					}
					logger.info("ProcessIntegrationData ended successfully");
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to APEX For BFL Integration Data : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private ApexDataExportDTO getTransactionConfigDTO(String message, String exported, BflIntegrationDataDTO barcodeHeaderDTO) throws ParseException {
		ApexDataExportDTO exportData = new ApexDataExportDTO();
		exportData.setInvoiceNo(barcodeHeaderDTO.getBusinessDate());
		exportData.setTable_Name(trnType);
		exportData.setTran_Type(trnType);
//		String sourceOFDate = barcodeHeaderDTO.getTrndate();
//		Date tranDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sourceOFDate);
//		String trndate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tranDate);
//		exportData.setTrnDateTime(trndate);
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
