package com.bfl.batch.jobs;

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
import com.bfl.dto.SalesHeaderDto;
import com.bfl.dto.StoreDTO;
import com.bfl.dto.TransactionConfigDTO;
import com.bfl.gencode.resa.otherShopReturnResponse.Item;
import com.bfl.gencode.resa.otherShopReturnResponse.OtherShopResponse;
import com.bfl.gencode.resa.sales.SalesRequest;
import com.bfl.gencode.resa.sales.response.SalesResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IDailyStoreClosingService;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IOtherStoreReturnSalesService;
import com.bfl.service.IReturnSalesService;
import com.bfl.service.ISalesDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.SalesDataServiceImpl;
import com.bfl.service.SalesHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessSalesDataJob")
public class ProcessSalesDataJob extends AbstractJob {

	Logger logger = LoggerFactory.getLogger(ProcessSalesDataJob.class);

	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationDataService foundationDataService;
	
	@Autowired
	ISalesDataService salesDataService;
	
	@Autowired
	SalesHelperImpl salesHelperImpl;
	
	@Autowired
	SalesDataServiceImpl salesDataServiceImpl;
	
	@Autowired
	IReturnSalesService returnSalesDataService;
	
	@Autowired
	IOtherStoreReturnSalesService otherStoreReturnSalesDataService;
	
	@Autowired
	IDailyStoreClosingService iDailyStoreClosingService;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessSalesDataJob job running...");
			String goLiveDate = "";
			String stageIntegration = "";
			//SALES DATA...
			try {
				goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				stageIntegration = ConfigProperties.getInstance().getConfigValue("STAGE_INTEGRATION");
				
				List<SalesHeaderDto> salesHeaderData = salesDataService.getSalesData(goLiveDate);
				
				if(null != salesHeaderData && salesHeaderData.size() > 0 ) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					FoundadtionRequest request1 = null;
					if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
						request1 = new FoundadtionRequest();
						String stageToken = helper.getAuthToken();
						request1.setUrl(null);
						request1.setLimit(1);
						request1.setToken(stageToken);
					}
					String storeId = ConfigProperties.getInstance().getConfigValue("STORE_ID");
					StoreDTO storeDTO = salesDataService.getStoreDetailsFromStoreId(storeId);
					for(SalesHeaderDto salesData : salesHeaderData) {
						try {
							SalesRequest salesRequest = salesHelperImpl.buildSalesRequest(salesData, storeDTO);
							if(null != salesRequest && null != salesRequest.getBusinessDate() && !salesRequest.getBusinessDate().isEmpty()) {
								TransactionConfigDTO transactionConfigDTO = null;
								String message = "";
								SalesResponse sendSalesData = helper.sendSalesData(request, salesRequest);
								if(null != sendSalesData && null != sendSalesData.getStatusMsg() && sendSalesData.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
									transactionConfigDTO = getTransactionConfigDTO(message, "Y", salesData, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
									salesDataService.sendDataInExportedTable(transactionConfigDTO);
									if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
										sendSalesData = helper.sendSalesDataForStage(request1, salesRequest);
									}
								} else if(null != sendSalesData && null != sendSalesData.getSalesErrTbl() && sendSalesData.getSalesErrTbl().size() > 0) {
									message = sendSalesData.getSalesErrTbl().get(0).getErrorMsg();
									transactionConfigDTO = getTransactionConfigDTO(message, "N", salesData, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
									salesDataService.sendDataInExportedTable(transactionConfigDTO);
								} else {
									transactionConfigDTO = getTransactionConfigDTO("", "N", salesData, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
									salesDataService.sendDataInExportedTable(transactionConfigDTO);
								}
							}
						} catch (Exception e) {
							logger.error("Error occured while posting the transaction to RESA Invoice: " + salesData.getInvoiceNo() + " " +ExceptionUtils.getStackTrace(e));
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
			
			//RETURN DATA...
			
			logger.info("ReturnSalesData running...");
			try {
				goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				stageIntegration = ConfigProperties.getInstance().getConfigValue("STAGE_INTEGRATION");
				List<SalesHeaderDto> returnsHeaderData = returnSalesDataService.getReturnSalesData(goLiveDate);
				
				if(null != returnsHeaderData && returnsHeaderData.size() > 0 ) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					FoundadtionRequest request1 = null;
					if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) { 
						request1 = new FoundadtionRequest();
						String stageToken = helper.getAuthToken();
						request1.setUrl(null);
						request1.setLimit(1);
						request1.setToken(stageToken);
					}
					String storeId = ConfigProperties.getInstance().getConfigValue("STORE_ID");
					StoreDTO storeDTO = salesDataService.getStoreDetailsFromStoreId(storeId);
					for(SalesHeaderDto dto : returnsHeaderData) {
						SalesRequest salesRequest = salesHelperImpl.buildReturnSalesRequest(dto, storeDTO);
						if(null != salesRequest && null != salesRequest.getBusinessDate() && !salesRequest.getBusinessDate().isEmpty()) {
							TransactionConfigDTO transactionConfigDTO = null;
							String message = "";
							SalesResponse sendSalesData = helper.sendSalesData(request, salesRequest);
							if(null != sendSalesData && null != sendSalesData.getStatusMsg() && "SUCCESS".equalsIgnoreCase(sendSalesData.getStatusMsg())) {
								transactionConfigDTO = getReturnTransactionConfigDTO(message, "Y", dto, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
								salesDataService.sendDataInExportedTable(transactionConfigDTO);
								if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
									sendSalesData = helper.sendSalesDataForStage(request1, salesRequest);
								}
							} else if(null != sendSalesData && null != sendSalesData.getSalesErrTbl() && sendSalesData.getSalesErrTbl().size() > 0) {
								message = sendSalesData.getSalesErrTbl().get(0).getErrorMsg();
								transactionConfigDTO = getReturnTransactionConfigDTO(message, "N", dto, salesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), salesRequest, storeDTO, salesRequest.getTransactionHeadTbl().get(0).getRegister());
								salesDataService.sendDataInExportedTable(transactionConfigDTO);
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS for Sales Return : "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts("ERROR IN RETURN ", ExceptionUtils.getStackTrace(e));
			}
			
			//OTHER RETURN DATA...
			logger.info("OTHER SHOP RETURN running...");
			try {
				goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				stageIntegration = ConfigProperties.getInstance().getConfigValue("STAGE_INTEGRATION");
				List<SalesHeaderDto> salesHeaderData = otherStoreReturnSalesDataService.getOtherStoreReturnSalesData(goLiveDate);
				if(null != salesHeaderData && salesHeaderData.size() > 0 ) {
					FoundadtionRequest request = new FoundadtionRequest();
					String token = helper.getAuthTokenForProd();
					request.setUrl(null);
					request.setLimit(1);
					request.setToken(token);
					FoundadtionRequest request1 = null;
					if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) { 
						request1 = new FoundadtionRequest();
						String stageToken = helper.getAuthToken();
						request1.setUrl(null);
						request1.setLimit(1);
						request1.setToken(stageToken);
					}
					TransactionConfigDTO transactionConfigDTO = null;
					String message = "";
					for(SalesHeaderDto salesData : salesHeaderData) {
						try {
							StoreDTO storeDTO = otherStoreReturnSalesDataService.getStoreFromStoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
							OtherShopResponse otherShopResponse = helper.getOtherShopData(request, salesData.getInvoiceDONo());
							Item item = null;
							if(null != otherShopResponse && null != otherShopResponse.getItems() && otherShopResponse.getItems().size() > 0) {
								item = otherShopResponse.getItems().get(0);
							}
							
							StoreDTO originalStoreDetials = otherStoreReturnSalesDataService.getStoreDetials(salesData.getShop());
							
							SalesRequest otherStoreReturnSalesRequest = salesHelperImpl.buildOtherStoreReturnSalesRequest(salesData, storeDTO, item, originalStoreDetials);
							SalesResponse sendSalesData = helper.sendSalesData(request, otherStoreReturnSalesRequest);
							if(null != sendSalesData && null != sendSalesData.getStatusMsg() && sendSalesData.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
								transactionConfigDTO = getReturnTransactionConfigDTO(message, "Y", salesData, otherStoreReturnSalesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), otherStoreReturnSalesRequest, storeDTO, otherStoreReturnSalesRequest.getTransactionHeadTbl().get(0).getRegister());
								salesDataService.sendDataInExportedTable(transactionConfigDTO);
								if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration) && null != request1) {
									sendSalesData = helper.sendSalesDataForStage(request1, otherStoreReturnSalesRequest);
								}
							} else if(null != sendSalesData && null != sendSalesData.getSalesErrTbl() && sendSalesData.getSalesErrTbl().size() > 0) {
								message = sendSalesData.getSalesErrTbl().get(0).getErrorMsg();
								transactionConfigDTO = getReturnTransactionConfigDTO(message, "N", salesData, otherStoreReturnSalesRequest.getTransactionHeadTbl().get(0).getTransactionNo(), otherStoreReturnSalesRequest, storeDTO, otherStoreReturnSalesRequest.getTransactionHeadTbl().get(0).getRegister());
								salesDataService.sendDataInExportedTable(transactionConfigDTO);
							}
						} catch (Exception e) {
							logger.error("Error occured while posting the otherStoreReturnSales transaction to RESA Invoice: " +salesData.getInvoiceNo() + " " +ExceptionUtils.getStackTrace(e));
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts("ERROR IN OTHER SHOP RETURN", ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private TransactionConfigDTO getTransactionConfigDTO(String message, String exported, SalesHeaderDto salesHeaderDTO, String transSeq, SalesRequest salesRequest, StoreDTO storeDTO, String registerNo) {
		TransactionConfigDTO transactionConfigDTO = new TransactionConfigDTO();
		String invoiceNumber = "";
		transactionConfigDTO.setTRAN_SEQ(transSeq);
		transactionConfigDTO.setStore_ID(storeDTO.getRms_StoreId());
		transactionConfigDTO.setREG_ID(registerNo);
		transactionConfigDTO.setBFL_INVOICE_NO(salesHeaderDTO.getInvoiceNo());
		transactionConfigDTO.setBFL_STORE_ID(salesHeaderDTO.getCostCode());
		transactionConfigDTO.setBFL_REG_NO(salesHeaderDTO.getLocCode());
		transactionConfigDTO.setRMS_INVOICENO(invoiceNumber + salesRequest.getBusinessDate());
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
	
	private TransactionConfigDTO getReturnTransactionConfigDTO(String message, String exported, SalesHeaderDto dto, String transSeq, SalesRequest salesRequest, StoreDTO storeDTO, String registerNo) {
		TransactionConfigDTO transactionConfigDTO = new TransactionConfigDTO();
		String invoiceNumber = "";
		transactionConfigDTO.setTRAN_SEQ(transSeq);
		transactionConfigDTO.setStore_ID(storeDTO.getRms_StoreId());
		transactionConfigDTO.setBFL_INVOICE_NO(dto.getSReturnNo());
		transactionConfigDTO.setBFL_STORE_ID(dto.getCostCode());
		transactionConfigDTO.setREG_ID(registerNo);
		transactionConfigDTO.setBFL_REG_NO(dto.getLocCode());
		transactionConfigDTO.setBUSINESS_DATE(formatDateTime(dto.getTrndate()));
		transactionConfigDTO.setRMS_INVOICENO(invoiceNumber + salesRequest.getBusinessDate());
		transactionConfigDTO.setTRAN_TYPE(salesRequest.getTransactionHeadTbl().get(0).getTransactionType());
		transactionConfigDTO.setERROR(message);
		transactionConfigDTO.setExported(exported);
		return transactionConfigDTO;
	}
	
}
