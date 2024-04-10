package com.bfl.batch.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;
import com.bfl.dto.TransactionConfigDTO;
import com.bfl.gencode.resa.sales.SalesRequest;
import com.bfl.gencode.resa.sales.response.SalesResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IReturnSalesService;
import com.bfl.service.ISalesDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.SalesHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;

//@Component("ProcessReturnSalesDataJob")
public class ProcessReturnSalesDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessReturnSalesDataJob.class);

	@Autowired
	IJobConfigService jobconfigService;

	@Autowired
	EmailService emailService;

	@Autowired
	IWebServiceHelper helper;

	@Autowired
	IReturnSalesService returnSalesDataService;

	@Autowired
	SalesHelperImpl salesHelperImpl;

	@Autowired
	ISalesDataService salesDataService;

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessReturnSalesDataJob job running...");
			try {
				String goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				List<SalesHeaderDto> salesHeaderData = returnSalesDataService.getReturnSalesData(goLiveDate);
				
				if(null != salesHeaderData && salesHeaderData.size() > 0 ) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthToken();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					String storeId = ConfigProperties.getInstance().getConfigValue("STORE_ID");
					StoreDTO storeDTO = salesDataService.getStoreDetailsFromStoreId(storeId);
					for(SalesHeaderDto dto : salesHeaderData) {
						try {
							SalesRequest salesRequest = salesHelperImpl.buildReturnSalesRequest(dto, storeDTO);
							if(null != salesRequest && null != salesRequest.getBusinessDate() && !salesRequest.getBusinessDate().isEmpty()) {
								TransactionConfigDTO transactionConfigDTO = null;
								String message = "";
								SalesResponse sendSalesData = helper.sendSalesData(request, salesRequest);
								if(sendSalesData!=null && sendSalesData.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
									transactionConfigDTO = getTransactionConfigDTO(message, "Y", dto, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
									salesDataService.sendDataInExportedTable(transactionConfigDTO);
								} else if(sendSalesData.getSalesErrTbl() != null && sendSalesData.getSalesErrTbl().size() > 0) {
									message = sendSalesData.getSalesErrTbl().get(0).getErrorMsg();
									transactionConfigDTO = getTransactionConfigDTO(message, "N", dto, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
									salesDataService.sendDataInExportedTable(transactionConfigDTO);
								}
							}
						} catch (Exception e) {
							logger.error("Error occured while posting the transaction to RESA Invoice: " +dto.getInvoiceNo() + " " +ExceptionUtils.getStackTrace(e));
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private TransactionConfigDTO getTransactionConfigDTO(String message, String exported, SalesHeaderDto dto, String transSeq, SalesRequest salesRequest, StoreDTO storeDTO, String registerNo) {
		TransactionConfigDTO transactionConfigDTO = new TransactionConfigDTO();
		String invoiceNumber = "";
		transactionConfigDTO.setTRAN_SEQ(transSeq);
		transactionConfigDTO.setStore_ID(storeDTO.getRms_StoreId());
		transactionConfigDTO.setBFL_INVOICE_NO(dto.getSReturnNo());
		transactionConfigDTO.setBFL_STORE_ID(dto.getCostCode());
		transactionConfigDTO.setREG_ID(registerNo);
		transactionConfigDTO.setBFL_REG_NO(dto.getLocCode());
		transactionConfigDTO.setBUSINESS_DATE(formatDateTime(dto.getTrndate()));
		transactionConfigDTO.setRMS_INVOICENO(invoiceNumber + storeDTO.getRms_StoreId() + salesRequest.getBusinessDate());
		transactionConfigDTO.setTRAN_TYPE(salesRequest.getTransactionHeadTbl().get(0).getTransactionType());
		transactionConfigDTO.setERROR(message);
		transactionConfigDTO.setExported(exported);
		return transactionConfigDTO;
	}
	
	public String formatDateTime(String businessDate) {
		try {
			Date tranDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(businessDate);
			String trndate = new SimpleDateFormat("dd/MM/yyyy").format(tranDate);
			return trndate;
		} catch (Exception e) {
			return "";
		}
	}
	
}