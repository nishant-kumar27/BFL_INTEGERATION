package com.bfl.batch.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bfl.dto.CloseShopDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.dto.DailyStoreClosingDTO;
import com.bfl.dto.StoreDTO;
import com.bfl.dto.TransactionConfigDTO;
import com.bfl.gencode.resa.sales.SalesRequest;
import com.bfl.gencode.resa.sales.TransactionHeadTbl;
import com.bfl.gencode.resa.sales.TransactionTenderTbl;
import com.bfl.gencode.resa.sales.response.SalesResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IDailyStoreClosingService;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.ISalesDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.service.SalesHelperImpl;
import com.bfl.ui.jobmanager.service.IJobConfigService;

/**
@author deepak.walia
 */

@Component("ProcessDailyStoreClosingJob")
public class ProcessDailyStoreClosingJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessDailyStoreClosingJob.class);

	@Autowired
	IJobConfigService jobconfigService;

	@Autowired
	EmailService emailService;

	@Autowired
	IWebServiceHelper helper;

	@Autowired
	IDailyStoreClosingService iDailyStoreClosingService;

	@Autowired
	ISalesDataService salesDataService;
	
	@Autowired
	SalesHelperImpl salesHelper;

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			String stageIntegration = "";
			logger.info("ProcessDailyStoreClosingJob job running...");
			try {
				String goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				stageIntegration = ConfigProperties.getInstance().getConfigValue("STAGE_INTEGRATION");
				
				List<DailyStoreClosingDTO> dailytillClosingDetails = iDailyStoreClosingService.getDailyStoreClosingDetails(goLiveDate);
				
				if(null != dailytillClosingDetails && dailytillClosingDetails.size() > 0) {
					TransactionConfigDTO transactionConfigDTO = null;
					String message = "";
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
					for(int i = 0; i < dailytillClosingDetails.size(); i++) {
						try {
							String registerId = salesHelper.getRegisterDetails(null, "DailyClosing", dailytillClosingDetails.get(i), null);
							if(null != registerId && !registerId.isEmpty()) {
								String transSeq = salesDataService.getLastTransactionNo(registerId);
								SalesRequest storeClosingRequest = dailyStoreClosingReqeust(dailytillClosingDetails.get(i), registerId, transSeq);
								if(Double.parseDouble(dailytillClosingDetails.get(i).getExpenses()) > 0) {
									SalesRequest storeClosingExpenseRequest = dailyStoreClosingExpenseRequest(dailytillClosingDetails.get(i), registerId, transSeq);
									SalesResponse sendExpensevalue = helper.sendSalesData(request, storeClosingExpenseRequest);
									if(null != sendExpensevalue && null != sendExpensevalue.getStatusMsg() && sendExpensevalue.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
										transactionConfigDTO = getTransactionConfigDTO(message, "Y", dailytillClosingDetails.get(i), storeClosingRequest, storeClosingExpenseRequest.getTransactionHeadTbl().get(0).getTransactionNo(), "Expense", storeClosingExpenseRequest.getTransactionHeadTbl().get(0).getRegister());
										salesDataService.sendDataInExportedTable(transactionConfigDTO);
										if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
											sendExpensevalue = helper.sendSalesDataForStage(request1, storeClosingExpenseRequest);
										}
									} else if(null != sendExpensevalue && null != sendExpensevalue.getSalesErrTbl() && sendExpensevalue.getSalesErrTbl().size() > 0) {
										message = sendExpensevalue.getSalesErrTbl().get(0).getErrorMsg();
										transactionConfigDTO = getTransactionConfigDTO(message, "N", dailytillClosingDetails.get(i), storeClosingRequest, storeClosingExpenseRequest.getTransactionHeadTbl().get(0).getTransactionNo(), "Expense", storeClosingExpenseRequest.getTransactionHeadTbl().get(0).getRegister());
										salesDataService.sendDataInExportedTable(transactionConfigDTO);
									}
								}
								SalesResponse sendDailyStoreClosingData = helper.sendSalesData(request, storeClosingRequest);
								if(null != sendDailyStoreClosingData && null != sendDailyStoreClosingData.getStatusMsg() && sendDailyStoreClosingData.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
									for(int j = 0; j < storeClosingRequest.getTransactionHeadTbl().size(); j++) {
										transactionConfigDTO = getTransactionConfigDTO(message, "Y", dailytillClosingDetails.get(i), storeClosingRequest, storeClosingRequest.getTransactionHeadTbl().get(j).getTransactionNo(), "CTILL", storeClosingRequest.getTransactionHeadTbl().get(j).getRegister());
										salesDataService.sendDataInExportedTable(transactionConfigDTO);
									}
									if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
										sendDailyStoreClosingData = helper.sendSalesDataForStage(request1, storeClosingRequest);
									}
								} else if(null != sendDailyStoreClosingData && sendDailyStoreClosingData.getSalesErrTbl() != null && sendDailyStoreClosingData.getSalesErrTbl().size() > 0) {
									for(int j = 0; j < storeClosingRequest.getTransactionHeadTbl().size(); j++) {
										message = sendDailyStoreClosingData.getSalesErrTbl().get(0).getErrorMsg();
										transactionConfigDTO = getTransactionConfigDTO(message, "N", dailytillClosingDetails.get(i), storeClosingRequest, storeClosingRequest.getTransactionHeadTbl().get(j).getTransactionNo(), "CTILL", storeClosingRequest.getTransactionHeadTbl().get(j).getRegister());
										salesDataService.sendDataInExportedTable(transactionConfigDTO);
									}
								}
								logger.info("ProcessDailyStoreClosingJob ended successfully");
							}
						} catch (Exception e) {
							logger.error("Error occured while publishing the messages to RESA For Daily Store Closing : " + ExceptionUtils.getStackTrace(e));
							throw e;
						}
					}
				}
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RESA For Daily Store Closing : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
			
			try {
				logger.info("ProcessCloseShopDataJob job running...");
				String goLiveDate = ConfigProperties.getInstance().getConfigValue("GO_LIVE_DATE");
				stageIntegration = ConfigProperties.getInstance().getConfigValue("STAGE_INTEGRATION");
				List<CloseShopDTO> closeShopDetails = iDailyStoreClosingService.getStoreClosingInfo(goLiveDate);
				if(null != closeShopDetails && closeShopDetails.size() > 0) {
//					List<RTLogConfigDTO> rtlogData = salesDataService.getDataFromRtlogConfig(null, null);
					String registerId = salesHelper.getRegisterDetails(null, "CLOSESHOP", null, closeShopDetails.get(0));
					if(null != registerId && !registerId.isEmpty()) {
						String transSeq = salesDataService.getLastTransactionNo(registerId);
						SalesRequest storeClosingRequest = storeClosingReqeust(closeShopDetails, transSeq, registerId);
						String token = helper.getAuthTokenForProd();
						FoundadtionRequest request = new FoundadtionRequest();
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
						SalesResponse sendDailyStoreClosingData = helper.sendSalesData(request, storeClosingRequest);
						if(null != sendDailyStoreClosingData && null != sendDailyStoreClosingData.getStatusMsg() && sendDailyStoreClosingData.getStatusMsg().equalsIgnoreCase("SUCCESS")) {
							transactionConfigDTO = getTransactionConfigDTO(message, "Y", closeShopDetails, transSeq, registerId);
							salesDataService.sendDataInExportedTable(transactionConfigDTO);
							if(null != stageIntegration && !stageIntegration.isEmpty() && "Y".equals(stageIntegration)) {
								sendDailyStoreClosingData = helper.sendSalesDataForStage(request1, storeClosingRequest);
							}
						} else if(null != sendDailyStoreClosingData && sendDailyStoreClosingData.getSalesErrTbl() != null || sendDailyStoreClosingData.getSalesErrTbl().size() > 0) {
							message = sendDailyStoreClosingData.getSalesErrTbl().get(0).getErrorMsg();
							transactionConfigDTO = getTransactionConfigDTO(message, "N", closeShopDetails, transSeq, registerId);
							salesDataService.sendDataInExportedTable(transactionConfigDTO);
						}
					}
				}
			} catch(Exception e) {
				logger.error("Error occured while publishing the messages to RESA For Closing Shop : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts("ERROR IN CLOSE SHOP ", ExceptionUtils.getStackTrace(e));
			}
			
		}
	}

	public SalesRequest dailyStoreClosingExpenseRequest(DailyStoreClosingDTO dailyStoreClosingDTOs, String registerId, String tranSeq) throws ParseException {
		SalesRequest salesRequest = new SalesRequest();
		List<TransactionHeadTbl> transactionHeadTbl = new ArrayList<>();
		Double expenseAmt = Double.parseDouble(dailyStoreClosingDTOs.getExpenses());
		if(expenseAmt > 0) {
			List<TransactionTenderTbl> transactionTenderTbl = new ArrayList<TransactionTenderTbl>();
			TransactionTenderTbl tenderTbl = new TransactionTenderTbl();
			TransactionHeadTbl transactionHeadExpAmt = new TransactionHeadTbl();
			StoreDTO storeDTO = new StoreDTO();
			try {
				storeDTO.setCostCode(ConfigProperties.getInstance().getConfigValue("STORE_COST_CODE"));
				storeDTO.setRms_StoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
				storeDTO.setLocCodeTo(ConfigProperties.getInstance().getConfigValue("STORE_LOC_CODE"));
			} catch (IOException e) {
				logger.error("Error occured while getting store details from properties file in ProcessDailyStoreClosing " + e);
			}
			salesRequest.setStore(storeDTO.getRms_StoreId());
			salesRequest.setRecType("FHEAD");
			salesRequest.setRtlogOrgSys("POS");
//			String sourceOFDate = dailyStoreClosingDTOs.getEntryDate();
			String sourceOFDate = dailyStoreClosingDTOs.getTrnDate();
			Date businessDate = new SimpleDateFormat("yyyy-MM-dd").parse(sourceOFDate);
			String businessdate =  new SimpleDateFormat("yyyyMMdd").format(businessDate);
			salesRequest.setBusinessDate(businessdate);
//			String sourceOFTranDate = dailyStoreClosingDTOs.getTrnDate() + " " + dailyStoreClosingDTOs.getTrnTime();
//			Date tranDate = new SimpleDateFormat("yyyy-MM-dd").parse(sourceOFTranDate);
//			String trndate =  new SimpleDateFormat("yyyyMMddHHMMSS").format(tranDate);
			Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dailyStoreClosingDTOs.getTrnDate().split(" ")[0] + " " + dailyStoreClosingDTOs.getTrnTime());
			String trndate = new SimpleDateFormat("yyyyMMddHHMMSS").format(format);
//			String transSeq0 = salesDataService.getNextTransSequence();
			tranSeq = String.valueOf(Integer.parseInt(tranSeq) + 4);
			transactionHeadExpAmt.setRecType("THEAD");
			transactionHeadExpAmt.setCashier(dailyStoreClosingDTOs.getCashier());
			transactionHeadExpAmt.setRefNo1(dailyStoreClosingDTOs.getSn());
			transactionHeadExpAmt.setSalesperson(dailyStoreClosingDTOs.getCashier());
			transactionHeadExpAmt.setTransactionNo(tranSeq);
			transactionHeadExpAmt.setRefNo4("CASH");
			transactionHeadExpAmt.setRefNo26("1000");
			transactionHeadExpAmt.setRegister(registerId);
			transactionHeadExpAmt.setTransactionDate(trndate);
			transactionHeadExpAmt.setValue(String.valueOf(0 - expenseAmt));
			transactionHeadExpAmt.setTransactionType("PAIDOU");
			transactionHeadExpAmt.setSubTransactionType("POTILL");
			transactionHeadExpAmt.setReasonCode("TPOS");
			transactionHeadExpAmt.setTranProcessSys("POS");
			tenderTbl.setTendTypeId("1000");
			tenderTbl.setTendTypeGroup("CASH");
			tenderTbl.setTenderAmount(String.valueOf(0 - expenseAmt));
			tenderTbl.setRecType("TTEND");
			transactionTenderTbl.add(tenderTbl);
			transactionHeadExpAmt.setTransactionTenderTbl(transactionTenderTbl);
			transactionHeadTbl.add(transactionHeadExpAmt);
		}
		salesRequest.setTransactionHeadTbl(transactionHeadTbl);
		return salesRequest;
	}

	public SalesRequest dailyStoreClosingReqeust(DailyStoreClosingDTO dailyStoreClosingDTOs, String registerId, String transSeq) throws ParseException {
		SalesRequest salesRequest = new SalesRequest();
		List<TransactionHeadTbl> transactionHeadTbls = new ArrayList<>();
		StoreDTO storeDTO = new StoreDTO();
		try {
			storeDTO.setCostCode(ConfigProperties.getInstance().getConfigValue("STORE_COST_CODE"));
			storeDTO.setRms_StoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
			storeDTO.setLocCodeTo(ConfigProperties.getInstance().getConfigValue("STORE_LOC_CODE"));
		} catch (IOException e) {
			logger.error("Error occured while getting store details from properties file in ProcessDailyStoreClosing " + e);
		}
		salesRequest.setStore(storeDTO.getRms_StoreId());
		salesRequest.setRecType("FHEAD");
		salesRequest.setRtlogOrgSys("POS");
		Double creditAmt = Double.parseDouble(dailyStoreClosingDTOs.getCardAmt());
		Double cashAmt = Double.parseDouble(dailyStoreClosingDTOs.getCashAmt());
		Double beamAmt = Double.parseDouble(dailyStoreClosingDTOs.getBeamAmt());
//		Double expenseAmt = Double.parseDouble(dailyStoreClosingDTOs.getExpenses());
//		String sourceOFDate = dailyStoreClosingDTOs.getEntryDate();
		String sourceOFDate = dailyStoreClosingDTOs.getTrnDate();
		Date businessDate = new SimpleDateFormat("yyyy-MM-dd").parse(sourceOFDate);
		String businessdate =  new SimpleDateFormat("yyyyMMdd").format(businessDate);
		salesRequest.setBusinessDate(businessdate);
		Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dailyStoreClosingDTOs.getTrnDate().split(" ")[0] + " " + dailyStoreClosingDTOs.getTrnTime());
		String trndate = new SimpleDateFormat("yyyyMMddHHMMSS").format(format);
		int seq = Integer.parseInt(transSeq);
		if(creditAmt > 0) {
			TransactionHeadTbl transactionHeadCreditAmt = new TransactionHeadTbl();
//			String transSeq1 = salesDataService.getNextTransSequence();
			seq = seq + 1;
			transSeq = String.valueOf(seq);
			transactionHeadCreditAmt.setRecType("THEAD");
			transactionHeadCreditAmt.setCashier(dailyStoreClosingDTOs.getCashier());
			transactionHeadCreditAmt.setRefNo1(dailyStoreClosingDTOs.getSn());
			transactionHeadCreditAmt.setRefNo27(dailyStoreClosingDTOs.getTimeFrom());
			transactionHeadCreditAmt.setRefNo31(dailyStoreClosingDTOs.getTimeTo());
			transactionHeadCreditAmt.setSalesperson(dailyStoreClosingDTOs.getCashier());
			transactionHeadCreditAmt.setTransactionNo(transSeq);
			transactionHeadCreditAmt.setRefNo4("CCARD");
			transactionHeadCreditAmt.setRefNo26("3000");
			transactionHeadCreditAmt.setRegister(registerId);
			transactionHeadCreditAmt.setTransactionDate(trndate);
			transactionHeadCreditAmt.setValue(String.valueOf(creditAmt));
			transactionHeadCreditAmt.setTransactionType("TOTAL");
			transactionHeadCreditAmt.setTranProcessSys("POS");
			transactionHeadCreditAmt.setSubTransactionType("CTILLT");
			transactionHeadTbls.add(transactionHeadCreditAmt);
		} 
		if(cashAmt > 0) {
			TransactionHeadTbl transactionHeadCashAmt = new TransactionHeadTbl();
			seq = seq + 1;
			transSeq = String.valueOf(seq);
//			String transSeq2 = salesDataService.getNextTransSequence();
			transactionHeadCashAmt.setTransactionNo(transSeq);
			transactionHeadCashAmt.setTransactionDate(trndate);
			transactionHeadCashAmt.setRegister(registerId);
			transactionHeadCashAmt.setRefNo4("CASH");
			transactionHeadCashAmt.setRefNo26("1000");
			transactionHeadCashAmt.setRecType("THEAD");
			transactionHeadCashAmt.setRefNo27(dailyStoreClosingDTOs.getTimeFrom());
			transactionHeadCashAmt.setRefNo31(dailyStoreClosingDTOs.getTimeTo());
			transactionHeadCashAmt.setCashier(dailyStoreClosingDTOs.getCashier());
			transactionHeadCashAmt.setRefNo1(dailyStoreClosingDTOs.getSn());
			transactionHeadCashAmt.setSalesperson(dailyStoreClosingDTOs.getCashier());
			transactionHeadCashAmt.setValue(String.valueOf(cashAmt));
			transactionHeadCashAmt.setTransactionType("TOTAL");
			transactionHeadCashAmt.setTranProcessSys("POS");
			transactionHeadCashAmt.setSubTransactionType("CTILLT");
			transactionHeadTbls.add(transactionHeadCashAmt);
		}
		if(beamAmt > 0) {
			TransactionHeadTbl transactionHeadBeamAmt = new TransactionHeadTbl();
			seq = seq + 1;
			transSeq = String.valueOf(seq);
//			String transSeq4 = salesDataService.getNextTransSequence();
			transactionHeadBeamAmt.setTransactionNo(transSeq);
			transactionHeadBeamAmt.setTransactionDate(trndate);
			transactionHeadBeamAmt.setCashier(dailyStoreClosingDTOs.getCashier());
			transactionHeadBeamAmt.setRefNo27(dailyStoreClosingDTOs.getTimeFrom());
			transactionHeadBeamAmt.setRefNo31(dailyStoreClosingDTOs.getTimeTo());
			transactionHeadBeamAmt.setRefNo1(dailyStoreClosingDTOs.getSn());
			transactionHeadBeamAmt.setSalesperson(dailyStoreClosingDTOs.getCashier());
			transactionHeadBeamAmt.setTransactionType("TOTAL");
			transactionHeadBeamAmt.setSubTransactionType("CTILLT");
			transactionHeadBeamAmt.setRegister(registerId);
			transactionHeadBeamAmt.setRecType("THEAD");
			transactionHeadBeamAmt.setTranProcessSys("POS");
			transactionHeadBeamAmt.setRefNo4("OTHERS");
			transactionHeadBeamAmt.setRefNo25("10000");
			transactionHeadBeamAmt.setValue(dailyStoreClosingDTOs.getBeamAmt());
			transactionHeadTbls.add(transactionHeadBeamAmt);
		}
		salesRequest.setTransactionHeadTbl(transactionHeadTbls);
		return salesRequest;
	}

	private TransactionConfigDTO getTransactionConfigDTO(String message, String exported, DailyStoreClosingDTO dailyStoreClosingDetails, SalesRequest storeClosingRequest, String transSeq, String TRAN_TYPE, String registerId) {
		TransactionConfigDTO transactionConfigDTO = new TransactionConfigDTO();
		StoreDTO storeDTO = new StoreDTO();
		try {
			storeDTO.setCostCode(ConfigProperties.getInstance().getConfigValue("STORE_COST_CODE"));
			storeDTO.setRms_StoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
			storeDTO.setLocCodeTo(ConfigProperties.getInstance().getConfigValue("STORE_LOC_CODE"));
		} catch (IOException e) {
			logger.error("Error occured while setting the data for Transaction config dto in ProcessDailyStoreClosingJob : " + e);
		}
		transactionConfigDTO.setBFL_INVOICE_NO(dailyStoreClosingDetails.getSn());
		transactionConfigDTO.setBFL_STORE_ID(storeDTO.getRms_StoreId());
		transactionConfigDTO.setBUSINESS_DATE(dailyStoreClosingDetails.getTrnDate());
		transactionConfigDTO.setTRAN_TYPE(TRAN_TYPE);
		transactionConfigDTO.setTRAN_SEQ(transSeq);
		transactionConfigDTO.setBFL_REG_NO(storeDTO.getLocCodeTo());
		transactionConfigDTO.setREG_ID(registerId);
		transactionConfigDTO.setStore_ID(storeClosingRequest.getStore());
		transactionConfigDTO.setBUSINESS_DATE(formatDateTime(dailyStoreClosingDetails.getTrnDate()));
		transactionConfigDTO.setTRN_DATETIME(dailyStoreClosingDetails.getTrnDate());
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
	
	public SalesRequest storeClosingReqeust(List<CloseShopDTO> dailyShopClosingDTOs, String transSeq, String registerId) throws ParseException {
		SalesRequest salesRequest = new SalesRequest();
		List<TransactionHeadTbl> transactionHeadTbls = new ArrayList<>();
		StoreDTO storeDTO = new StoreDTO();
		try {
			storeDTO.setCostCode(ConfigProperties.getInstance().getConfigValue("STORE_COST_CODE"));
			storeDTO.setRms_StoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
			storeDTO.setLocCodeTo(ConfigProperties.getInstance().getConfigValue("STORE_LOC_CODE"));
		} catch (IOException e) {
			logger.info("Error occured while getting the store code from properties file in close shop " + ExceptionUtils.getMessage(e));
		}
		TransactionHeadTbl transactionHeadTbl = new TransactionHeadTbl();
		salesRequest.setStore(storeDTO.getRms_StoreId());
		salesRequest.setRecType("FHEAD");
		salesRequest.setRtlogOrgSys("POS");
		String sourceOFDate = dailyShopClosingDTOs.get(0).getCloseDate();
		Date businessDate = new SimpleDateFormat("yyyy-MM-dd").parse(sourceOFDate);
		String businessdate =  new SimpleDateFormat("yyyyMMdd").format(businessDate);
		salesRequest.setBusinessDate(businessdate);
		String tranCount = iDailyStoreClosingService.getTotalTransactionsCount(new SimpleDateFormat("dd/MM/yyyy").format(businessDate));
		
		String sourceOFTranDate = dailyShopClosingDTOs.get(0).getTrnDateTime();
		Date tranDate = new SimpleDateFormat("yyyy-MM-dd").parse(sourceOFTranDate);
		String trndate =  new SimpleDateFormat("yyyyMMddHHMMSS").format(tranDate);
		transactionHeadTbl.setTransactionDate(trndate);
		transactionHeadTbl.setRegister(registerId);
		transactionHeadTbl.setValue(tranCount);
		transactionHeadTbl.setTransactionNo(transSeq);
		transactionHeadTbl.setCashier(dailyShopClosingDTOs.get(0).getUserId());
		transactionHeadTbl.setSalesperson(dailyShopClosingDTOs.get(0).getUserId());
		transactionHeadTbl.setTranProcessSys("POS");
		transactionHeadTbl.setRecType("THEAD");
		transactionHeadTbl.setTransactionType("DCLOSE");
		transactionHeadTbls.add(transactionHeadTbl);
		salesRequest.setTransactionHeadTbl(transactionHeadTbls);
		return salesRequest;
	}

	private TransactionConfigDTO getTransactionConfigDTO(String message, String exported,  List<CloseShopDTO> dailyShopClosingDetails, String transSeq, String registerId) {
		TransactionConfigDTO transactionConfigDTO = new TransactionConfigDTO();
		transactionConfigDTO.setBFL_INVOICE_NO(dailyShopClosingDetails.get(0).getCode());
		StoreDTO storeDTO = new StoreDTO();
		try {
			storeDTO.setRms_StoreId(ConfigProperties.getInstance().getConfigValue("STORE_ID"));
		} catch (IOException e) {
			logger.error("Error occured while setting the data for Transaction config dto in closing the shop : " + e);
		}
		transactionConfigDTO.setBFL_STORE_ID(storeDTO.getRms_StoreId());
		transactionConfigDTO.setTRAN_SEQ(transSeq);
		transactionConfigDTO.setREG_ID(registerId);
		transactionConfigDTO.setBUSINESS_DATE(formatDateTime(dailyShopClosingDetails.get(0).getCloseDate()));
		transactionConfigDTO.setTRAN_TYPE("CSTORE");
		transactionConfigDTO.setStore_ID(dailyShopClosingDetails.get(0).getCompName());
		transactionConfigDTO.setTRN_DATETIME(formatDateTime(dailyShopClosingDetails.get(0).getTrnDateTime()));
		transactionConfigDTO.setSTORE_CLOSE_DATE(formatDateTime(dailyShopClosingDetails.get(0).getCloseDate()));
		transactionConfigDTO.setERROR(message);
		transactionConfigDTO.setExported(exported);
		return transactionConfigDTO;
	}
	
}
