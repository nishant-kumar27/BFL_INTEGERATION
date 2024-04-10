package com.bfl.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import com.bfl.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfl.ConfigProperties;
import com.bfl.dao.IReturnSalesDataDao;
import com.bfl.dao.ISalesDataDao;
import com.bfl.gencode.resa.otherShopReturnResponse.Item;
import com.bfl.gencode.resa.sales.SalesFtailTbl;
import com.bfl.gencode.resa.sales.SalesRequest;
import com.bfl.gencode.resa.sales.TransactionCustomerTbl;
import com.bfl.gencode.resa.sales.TransactionHeadTbl;
import com.bfl.gencode.resa.sales.TransactionItemDiscountTbl;
import com.bfl.gencode.resa.sales.TransactionItemTaxTbl;
import com.bfl.gencode.resa.sales.TransactionItemTbl;
import com.bfl.gencode.resa.sales.TransactionPaymentTbl;
import com.bfl.gencode.resa.sales.TransactionTaxTbl;
import com.bfl.gencode.resa.sales.TransactionTenderTbl;

@Service
public class SalesHelperImpl {
	
	@Autowired
	ISalesDataDao salesDataDao;

	@Autowired
	IReturnSalesDataDao returnSalesDataDao;

	@Autowired
	IReturnSalesService returnSalesDataService;

	@Autowired
	ISalesDataService salesDataService;

	Logger logger = LoggerFactory.getLogger(SalesHelperImpl.class);
	
	public SalesRequest buildSalesRequest(SalesHeaderDto salesHeaderDTO, StoreDTO storeDTO) throws Exception {
		//----------For FHead
		logger.info("Inside buildSalesRequest method");
		SalesRequest salesRequest = new SalesRequest();
		
		String registerId = getRegisterDetails(salesHeaderDTO, "SALE", null, null);
		
		if(null != registerId && !registerId.isEmpty()) {
			String tenderTypeIdForCash = "";
			String tenderTypeIdForCard = "";
			
			String transSeq = salesDataService.getLastTransactionNo(registerId);
			
			long transSequence = 1;
			
			if(null != transSeq && !transSeq.isEmpty()) {
				transSequence = Long.parseLong(transSeq);
			}
			
			List<TransactionHeadTbl> transactionHeadTbl = new ArrayList<>();
			salesRequest.setStore(storeDTO.getRms_StoreId());
			salesRequest.setRecType("FHEAD");
			salesRequest.setRtlogOrgSys("POS");
			TransactionHeadTbl tranHeader = new TransactionHeadTbl();
			
			//---------- To set transactiondate format
			String source = salesHeaderDTO.getInvoiceDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
			String trnDate =  new SimpleDateFormat("yyyyMMdd").format(date);
			salesRequest.setBusinessDate(trnDate);
			
			//--------- For THEAD
			
			SalesFtailTbl tranFtail = new SalesFtailTbl();
//			Double paidAmt = Double.parseDouble(salesHeaderDTO.getNetamount());
			tranHeader.setCashier(salesHeaderDTO.getPreparedBy());
//			String businessDate =  new SimpleDateFormat("yyyyMMddHHMMSS").format(date);
			
			Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(salesHeaderDTO.getTrndate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMSS");
			String businessDate = sdf.format(format);
			
			tranHeader.setTransactionDate(businessDate);
			tranHeader.setRecType("THEAD");
			tranHeader.setRefNo1(getSubString(salesHeaderDTO.getInvoiceNo()));
			tranHeader.setRefNo2(getSubString(salesHeaderDTO.getGrossAmount()));
			tranHeader.setRefNo3(getSubString(salesHeaderDTO.getDONo()));
			tranHeader.setRegister(registerId);
			
			tranHeader.setSalesperson(salesHeaderDTO.getUserId());
			//		tranHeader.setReasonCode("rea");
			String tranNumber = "" + transSequence;
			tranHeader.setTransactionNo(tranNumber);
			tranHeader.setTransactionType("SALE");
			tranHeader.setSubTransactionType("SALE");
			
			String currencyRounding = ConfigProperties.getInstance().getConfigValue("CURRENCY_ROUNDING") + "f";
			
			//-- To set tender Data
			List<TransactionTenderTbl> transactionTenderTbls = getTransactionTenderTbls(salesHeaderDTO, tenderTypeIdForCash, tenderTypeIdForCard, currencyRounding);
			tranHeader.setTransactionTenderTbl(transactionTenderTbls);
			tranHeader.setTranProcessSys("POS");
			double tenderAmt = 0.0;
			for(int i = 0; i < transactionTenderTbls.size(); i++) {
				tenderAmt = tenderAmt + Double.parseDouble(transactionTenderTbls.get(i).getTenderAmount());
			}
			tranHeader.setValue(String.valueOf(tenderAmt));
			//-----------For Ftail
			tranFtail.setRecCounter(businessDate);
			
			//-----------For TItems
			List<TransactionItemTbl> listOfTransactionItemTbl = new ArrayList<>();
			for(int j = 0; j < salesHeaderDTO.getSalesDetailsDtos().size(); j++) {
				TransactionItemTbl transactionItemTbl = new TransactionItemTbl();
				transactionItemTbl.setQuantity(salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				transactionItemTbl.setItem(salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode());
				transactionItemTbl.setItemType("ITEM");
				transactionItemTbl.setSalesType("R");
				transactionItemTbl.setRecType("TITEM");
				transactionItemTbl.setItemStatus("S");
				transactionItemTbl.setItemNoType("ITEM");
				transactionItemTbl.setCatchWeightInd("N");
				transactionItemTbl.setUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
				transactionItemTbl.setOriginalUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
				transactionItemTbl.setTaxableIndicator("Y");
				transactionItemTbl.setVoucherNo(salesHeaderDTO.getVoucherNo());
				//			transactionItemTbl.setSellingUom(salesHeaderDTO.getSalesDetailsDtos().get(j).getUnitCode());
				transactionItemTbl.setSellingUom("EA");
				transactionItemTbl.setItemSwipedInd("Y");
				transactionItemTbl.setDropShipInd("N");
				transactionItemTbl.setUomQty(salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				
				//---------------- For TIGTAX
				List<InvoiceVatItemDTO> vatItemDetails = salesDataService.getVatItemDetails(salesHeaderDTO.getSalesDetailsDtos().get(j).getInvoiceNo(), salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode(), salesHeaderDTO.getSalesDetailsDtos().get(j).getMRow());
				if(null != vatItemDetails && vatItemDetails.size() > 0) {
					List<TransactionItemTaxTbl> tigTaxTableList = setTigTax(vatItemDetails, storeDTO);
					transactionItemTbl.setTransactionItemTaxTbl(tigTaxTableList);
				}
				//---------------- For Discount
				double discAmount = Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getDiscount());
				if(discAmount > 0) {
					List<TransactionItemDiscountTbl> discountTblsList = setTransactionItemDiscountTbl(salesHeaderDTO.getSalesDetailsDtos().get(j), salesHeaderDTO.getDiscountReason(), "SALE");
					transactionItemTbl.setTransactionItemDiscountTbl(discountTblsList);
				}
				listOfTransactionItemTbl.add(transactionItemTbl);
			}
			
			//-----------------For Customer
			List<TransactionCustomerTbl> customerList = setCustomerTbls(salesHeaderDTO, tranNumber);

			//---------------- For VAT
			//		List<TransactionTaxTbl> vatItemList = setTransactionTaxTbls(salesHeaderDTO.getInvoiceVatItemDTOs());
			
			//------- For Payment
//			List<TransactionPaymentTbl> paymentTblsList = setPaymentData(salesHeaderDTO.getPaymentsDto());

			//		tranHeader.setTransactionTaxTbl(vatItemList);
//			tranHeader.setTransactionPaymentTbl(paymentTblsList);
			tranHeader.setTransactionCustomerTbl(customerList);
			tranHeader.setTransactionItemTbl(listOfTransactionItemTbl);
			transactionHeadTbl.add(tranHeader);
			salesRequest.setTransactionHeadTbl(transactionHeadTbl);
		}
		return salesRequest;
	}

	public List<TransactionItemDiscountTbl> setTransactionItemDiscountTbl(SalesDetailsDto salesDetailsDto, String discountReasonCode, String trnType) {
		List<TransactionItemDiscountTbl> discountTblsList = new ArrayList<>();
		TransactionItemDiscountTbl discountTbl = new TransactionItemDiscountTbl();
		discountTbl.setRecType("IDISC");
		Double discountAmount = Double.parseDouble(salesDetailsDto.getDiscount());
		discountAmount = discountAmount / Double.parseDouble(salesDetailsDto.getQuantity());
		if("RETURN".equals(trnType)) {
			discountTbl.setUnitDiscountAmount(String.valueOf(discountAmount));
			discountTbl.setQuantity("-" + salesDetailsDto.getQuantity());
			discountTbl.setUomQty("-" + salesDetailsDto.getQuantity());
		} else if("SALE".equals(trnType)) {
			discountTbl.setUnitDiscountAmount(String.valueOf(discountAmount));
			discountTbl.setQuantity(salesDetailsDto.getQuantity());
			discountTbl.setUomQty(salesDetailsDto.getQuantity());
		}
		discountTbl.setDiscountType("S");
		discountTbl.setCatchWeightInd("N");
		discountTbl.setMerchPromoNo("1004");
		discountTbl.setPromoComponent("1004");
		discountTbl.setRefNo13((null != discountReasonCode && !discountReasonCode.trim().isEmpty()) ? (discountReasonCode.length() > 30 ? discountReasonCode.substring(0, 29) : discountReasonCode) : "");
		discountTblsList.add(discountTbl);
		return discountTblsList;
	}

	public List<TransactionItemTaxTbl> setTigTax(List<InvoiceVatItemDTO> vatItemDetails, StoreDTO storeDTO) {
		List<TransactionItemTaxTbl> tigTaxTableList = new ArrayList<>();
		Formatter fm = new Formatter();
		for(int m = 0; m < vatItemDetails.size(); m++) {
			VATDTO vatDetails = salesDataService.getVatDetails(storeDTO.getVAT_REGION(), (int) Double.parseDouble(vatItemDetails.get(m).getVatPer()));
			TransactionItemTaxTbl itemTaxTbl = new TransactionItemTaxTbl();
			itemTaxTbl.setRecType("IGTAX");
			itemTaxTbl.setIgtaxCode(vatDetails.getVatCode());
			itemTaxTbl.setTaxAuthority(vatDetails.getVatRegion());
			itemTaxTbl.setIgtaxRate(vatItemDetails.get(m).getVatPer());
			itemTaxTbl.setIgtaxAmount(String.valueOf(fm.format("%.4f", Double.parseDouble(vatItemDetails.get(m).getVatAmt()))));
			tigTaxTableList.add(itemTaxTbl);
		}
		fm.close();
		return tigTaxTableList;
	}

	public List<TransactionCustomerTbl> setCustomerTbls(SalesHeaderDto salesHeaderDto, String tranNumber) {
		List<TransactionCustomerTbl> customerList = new ArrayList<>();
		if(null != salesHeaderDto.getMobileNo() && !salesHeaderDto.getMobileNo().isEmpty() && !"NA".equals(salesHeaderDto.getMobileNo()) && !salesHeaderDto.getMobileNo().contains("NA") && !salesHeaderDto.getMobileNo().contains("N") && (Long.parseLong(salesHeaderDto.getMobileNo()) > 0)) {
			TransactionCustomerTbl customerTbl = new TransactionCustomerTbl();
			customerTbl.setCustomerId(salesHeaderDto.getMobileNo());
			customerTbl.setCustomerType("CUSTID");
			customerTbl.setHomePhone(salesHeaderDto.getMobileNo());
			customerTbl.setCustomerName(salesHeaderDto.getName());
			customerTbl.setAddress1(salesHeaderDto.getAddr1());
			customerTbl.setRecType("TCUST");
			customerTbl.setZipCode(salesHeaderDto.getPobox());
			customerList.add(customerTbl);
		} else if(null != salesHeaderDto.getName() && !salesHeaderDto.getName().isEmpty()) {
			TransactionCustomerTbl customerTbl = new TransactionCustomerTbl();
			customerTbl.setCustomerId(tranNumber);
			customerTbl.setCustomerType("CUSTID");
			customerTbl.setHomePhone("");
			customerTbl.setCustomerName(salesHeaderDto.getName());
			customerTbl.setAddress1(salesHeaderDto.getAddr1());
			customerTbl.setRecType("TCUST");
			customerTbl.setZipCode(salesHeaderDto.getPobox());
			customerList.add(customerTbl);
		}
		return customerList;
	}



	public List<TransactionTenderTbl> getTransactionTenderTbls(SalesHeaderDto salesHeaderDto, String tenderTypeIdForCash, String tenderTypeIdForCard, String currencyRounding) {
		//		Double card=0.0;
		List<TransactionTenderTbl> transactionTenderTbls = new ArrayList<>();
		Double card = Double.parseDouble(salesHeaderDto.getCreditAmt());
		//		if(salesHeaderDto.getCreditCardDTO() != null && salesHeaderDto.getCreditCardDTO().size()>0) {
		//		 card=Double.parseDouble(salesHeaderDto.getCreditCardDTO().get(0).getCcamt());
		//		}
		Double voucherAmt = Double.parseDouble(salesHeaderDto.getVoucherAmt());
		Double cash = Double.parseDouble(salesHeaderDto.getCashAmt());
		Double creditnote = Double.parseDouble(salesHeaderDto.getCreditNoteAmt());
		Double creditnote1 = Double.parseDouble(salesHeaderDto.getCreditNoteAmt1());
		Double creditnote2 = Double.parseDouble(salesHeaderDto.getCreditNoteAmt2());
		Double creditnote3 = Double.parseDouble(salesHeaderDto.getCreditNoteAmt3());
		Double creditnote4 = Double.parseDouble(salesHeaderDto.getCreditNoteAmt4());
		Double mallVoucAmount = Double.parseDouble(salesHeaderDto.getTotVohamount());
		
//		Double netAmt = 0.0;
		Double usedAmount = 0.0;
		String creditNoteNo = "";
		List<CreditNoteHistoryDTO> creditNoteHistoryDTOs = salesHeaderDto.getCreditNoteHistoryDTOs();
		if(null != creditNoteHistoryDTOs && creditNoteHistoryDTOs.size() > 0) {
			for(CreditNoteHistoryDTO creditNoteHistoryDTO : creditNoteHistoryDTOs) {
				Double totalAmt = Double.parseDouble(creditNoteHistoryDTO.getTotalAmt());
				Double usedAmt = Double.parseDouble(creditNoteHistoryDTO.getUsedAmt());
				if(usedAmt < totalAmt) {
//					netAmt = totalAmt;
					usedAmount = totalAmt;
					creditNoteNo = creditNoteHistoryDTO.getCrNoteNo();
					if(null != creditNoteHistoryDTO.getWalletAmt() && Double.parseDouble(creditNoteHistoryDTO.getWalletAmt()) > 0) {
						TransactionTenderTbl tenderTblforWallet = new TransactionTenderTbl();
						tenderTblforWallet.setTenderAmount("-" + String.format("%." + currencyRounding, Double.parseDouble(creditNoteHistoryDTO.getWalletAmt())));
						tenderTblforWallet.setTendTypeId("10010");
						tenderTblforWallet.setTendTypeGroup("OTHERS");
						tenderTblforWallet.setRecType("TTEND");
						transactionTenderTbls.add(tenderTblforWallet);
					} else if(null != creditNoteHistoryDTO.getReIssdAmt() && Double.parseDouble(creditNoteHistoryDTO.getReIssdAmt()) > 0) {
						TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
						tenderTblforCreditNote.setCouponNbr(creditNoteHistoryDTO.getCrNoteNo());
						tenderTblforCreditNote.setVoucherNo(creditNoteHistoryDTO.getCrNoteNo());
						tenderTblforCreditNote.setTenderAmount("-" + String.format("%." + currencyRounding, Double.parseDouble(creditNoteHistoryDTO.getReIssdAmt())));
						tenderTblforCreditNote.setTendTypeGroup("VOUCH");
						tenderTblforCreditNote.setTendTypeId("4050");
						tenderTblforCreditNote.setRecType("TTEND");
						transactionTenderTbls.add(tenderTblforCreditNote);
					}
				}
			}
		}
		
		if(salesHeaderDto.getTrnType().equals("C")) {
			if(cash > 0) {
				TransactionTenderTbl tenderTblforCash = new TransactionTenderTbl();
				if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
					!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
					String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
					beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
					tenderTblforCash.setRefNbr12(beamCardNo);
					tenderTblforCash.setTendTypeId("10000");
					tenderTblforCash.setTendTypeGroup("OTHERS");
					tenderTblforCash.setRecType("TTEND");
					tenderTblforCash.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
				} else {
					tenderTblforCash.setTendTypeId("1000");
					tenderTblforCash.setTendTypeGroup("CASH");
					tenderTblforCash.setTenderAmount(String.format("%." + currencyRounding, cash));
					tenderTblforCash.setRecType("TTEND");
				}
				transactionTenderTbls.add(tenderTblforCash);
			}
			if(voucherAmt > 0) {
				TransactionTenderTbl tenderTblforVoucher = new TransactionTenderTbl();
				if(salesHeaderDto.getVoucherNo() != null && !salesHeaderDto.getVoucherNo().isEmpty()) {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getVoucherNo());
				} else {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getLoyaltyCardNo());
				}
				if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
					!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
					String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
					beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
					tenderTblforVoucher.setRefNbr10(beamCardNo);
					tenderTblforVoucher.setRefNbr12(beamCardNo);
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
					tenderTblforVoucher.setTendTypeId("10000");
					tenderTblforVoucher.setTendTypeGroup("OTHERS");
					tenderTblforVoucher.setRecType("TTEND");
				} else {
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, voucherAmt));
					tenderTblforVoucher.setTendTypeGroup("VOUCH");
					tenderTblforVoucher.setTendTypeId("4000");
					tenderTblforVoucher.setRecType("TTEND");
				}
				transactionTenderTbls.add(tenderTblforVoucher);
			}
			
			if(mallVoucAmount > 0) {
				TransactionTenderTbl tenderTblforVoucher = null;
				List<PaymentsDto> paymentsDto = salesHeaderDto.getPaymentsDto();
				for(int i = 0; i < paymentsDto.size(); i++) {
					tenderTblforVoucher = new TransactionTenderTbl();
					tenderTblforVoucher.setVoucherNo(paymentsDto.get(i).getRefNo());
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(paymentsDto.get(i).getAmount())));
					tenderTblforVoucher.setTendTypeGroup("VOUCH");
					tenderTblforVoucher.setTendTypeId("4040");
					tenderTblforVoucher.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforVoucher);
				}
			}
			
			if(creditnote > 0) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo());
				if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
				} else {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote));
				}
				tenderTblforCreditNote.setTendTypeGroup("VOUCH");
				tenderTblforCreditNote.setTendTypeId("4050");
				tenderTblforCreditNote.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCreditNote);
			}
			
			if(creditnote1 > 0	||	creditnote2 > 0	||	creditnote3 > 0	||	creditnote4 > 0) {
				if(creditnote1 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo1());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo1());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo1())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote1));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				} else if(creditnote2 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo2());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo2());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo2())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote2));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				} else if(creditnote3 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo3());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo3());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo3())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote3));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				}
//				else {
//					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getLoyaltyCardNo());
//					tenderTblforCreditNote.setTenderAmount(String.valueOf(creditnote1+creditnote2+creditnote3+creditnote4));
//				}
				if(creditnote4 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo4());
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote4));
					tenderTblforCreditNote.setTendTypeId("10010");
					tenderTblforCreditNote.setTendTypeGroup("OTHERS");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				}
			}
		} else if(salesHeaderDto.getTrnType().equals("R")) {
			if(card > 0) {
				List<CreditCardDTO> creditCardDTO = salesHeaderDto.getCreditCardDTO();
				if(null != creditCardDTO && creditCardDTO.size() > 0) {
					for(int i = 0; i < creditCardDTO.size(); i++) {
						TransactionTenderTbl tendercardTbl = new TransactionTenderTbl();
						if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
								!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
							String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
							beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
							tendercardTbl.setRefNbr10(beamCardNo);
							tendercardTbl.setRefNbr12(beamCardNo);
							tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
							tendercardTbl.setTendTypeId("10000");
							tendercardTbl.setTendTypeGroup("OTHERS");
							tendercardTbl.setRecType("TTEND");
						} else {
							tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getCreditCardDTO().get(i).getCcamt())));
							tendercardTbl.setCcNo(salesHeaderDto.getCreditCardDTO().get(i).getCcno().replaceAll(" ", ""));
							tendercardTbl.setRefNbr10(getSubString(salesHeaderDto.getCreditCardDTO().get(i).getCcname().replaceAll("\r\n", "").replaceAll("/", " ")));
							tendercardTbl.setRefNbr11(getSubString(salesHeaderDto.getCreditCardDTO().get(i).getCcbank()));
							tendercardTbl.setTendTypeGroup("CCARD");
							tendercardTbl.setCcAuthNo(salesHeaderDto.getCreditCardDTO().get(i).getCccode());
							tendercardTbl.setTendTypeId("3000");
							tendercardTbl.setRecType("TTEND");
						}
						transactionTenderTbls.add(tendercardTbl);
					}
				} else {
					TransactionTenderTbl tendercardTbl = new TransactionTenderTbl();
					tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, card));
					tendercardTbl.setCcNo(salesHeaderDto.getCardno());
					tendercardTbl.setRefNbr10(getSubString(salesHeaderDto.getCardname().replaceAll("\r\n", "").replaceAll("/", " ")));
					tendercardTbl.setTendTypeGroup("CCARD");
					tendercardTbl.setTendTypeId("3000");
					tendercardTbl.setRecType("TTEND");
					transactionTenderTbls.add(tendercardTbl);
				}
			}
			
			if(mallVoucAmount > 0) {
				TransactionTenderTbl tenderTblforVoucher = null;
				List<PaymentsDto> paymentsDto = salesHeaderDto.getPaymentsDto();
				for(int i = 0; i < paymentsDto.size(); i++) {
					tenderTblforVoucher = new TransactionTenderTbl();
					tenderTblforVoucher.setVoucherNo(paymentsDto.get(i).getRefNo());
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(paymentsDto.get(i).getAmount())));
					tenderTblforVoucher.setTendTypeGroup("VOUCH");
					tenderTblforVoucher.setTendTypeId("4040");
					tenderTblforVoucher.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforVoucher);
				}
			}
			
			if(creditnote > 0) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo());
				if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
				} else {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote));
				}
				tenderTblforCreditNote.setTendTypeGroup("VOUCH");
				tenderTblforCreditNote.setTendTypeId("4050");
				tenderTblforCreditNote.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCreditNote);
			}
			
			if(voucherAmt > 0) {
				TransactionTenderTbl tenderTblforVoucher = new TransactionTenderTbl();
				if(salesHeaderDto.getVoucherNo() != null && !salesHeaderDto.getVoucherNo().isEmpty()) {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getVoucherNo());
				} else {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getLoyaltyCardNo());
				}
				if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
						!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
					String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
					beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
					tenderTblforVoucher.setRefNbr12(beamCardNo);
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
					tenderTblforVoucher.setTendTypeId("10000");
					tenderTblforVoucher.setTendTypeGroup("OTHERS");
					tenderTblforVoucher.setRecType("TTEND");
				} else {
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, voucherAmt));
					tenderTblforVoucher.setTendTypeId("4000");
					tenderTblforVoucher.setTendTypeGroup("VOUCH");
					tenderTblforVoucher.setRecType("TTEND");
				}
				transactionTenderTbls.add(tenderTblforVoucher);
			}
			if(creditnote1 > 0	||	creditnote2 > 0	||	creditnote3 > 0	||	creditnote4 > 0) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				if(creditnote1 > 0) {
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo1());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo1());
				} else if(creditnote2 > 0) {
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo2());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo2());
				} else if(creditnote3 > 0) {
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo3());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo3());
				} 
//				else {
//					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getLoyaltyCardNo());
//				}
				if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
						!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
					String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
					beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
					tenderTblforCreditNote.setRefNbr10(beamCardNo);
					tenderTblforCreditNote.setRefNbr12(beamCardNo);
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
					tenderTblforCreditNote.setTendTypeId("10000");
					tenderTblforCreditNote.setTendTypeGroup("OTHERS");
					tenderTblforCreditNote.setRecType("TTEND");				
					transactionTenderTbls.add(tenderTblforCreditNote);
				} else if(creditnote1 > 0 || creditnote2 > 0 || creditnote3 > 0) {
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo1())) {
						creditnote1 = usedAmount;
					} else if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo2())) {
						creditnote2 = usedAmount;
					} else if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo3())) {
						creditnote3 = usedAmount;
					}
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, (creditnote1 + creditnote2 + creditnote3)));
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				}
				if(creditnote4 > 0) {
					tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo4());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo4());
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote4));
					tenderTblforCreditNote.setTendTypeId("10010");
					tenderTblforCreditNote.setTendTypeGroup("OTHERS");
//					tenderTblforCreditNote.setTendTypeGroup("COUPON");
//					tenderTblforCreditNote.setTendTypeId("5000");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				}
			}
		} else if(salesHeaderDto.getTrnType().equals("B")) {
			if(cash > 0 && card > 0) {
				if(cash > 0 ) {
					TransactionTenderTbl tenderTblforCash = new TransactionTenderTbl();
					tenderTblforCash.setTenderAmount(String.format("%." + currencyRounding, cash));
					tenderTblforCash.setTendTypeId("1000");
					tenderTblforCash.setTendTypeGroup("CASH");
					tenderTblforCash.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCash);
				}
				if(card > 0 ) {
					List<CreditCardDTO> creditCardDTO = salesHeaderDto.getCreditCardDTO();
					if(null != creditCardDTO && creditCardDTO.size() > 0) {
						for(int i = 0; i < creditCardDTO.size(); i++) {
							TransactionTenderTbl tendercardTbl = new TransactionTenderTbl();
							if(salesHeaderDto.getBeamCardNo() != null && !salesHeaderDto.getBeamCardNo().isEmpty() && null != salesHeaderDto.getNetamount() && 
									!salesHeaderDto.getNetamount().isEmpty() && Double.parseDouble(salesHeaderDto.getNetamount()) > 0) {
								String beamCardNo = salesHeaderDto.getBeamCardNo().replaceAll("\r\n", "").replaceAll("/", " ");
								beamCardNo = beamCardNo.length() > 30 ? beamCardNo.substring(0, 29) : beamCardNo;
								tendercardTbl.setRefNbr12(beamCardNo);
								tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount())));
								tendercardTbl.setTendTypeId("10000");
								tendercardTbl.setTendTypeGroup("OTHERS");
								tendercardTbl.setRecType("TTEND");					
							} else {
								tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getCreditCardDTO().get(i).getCcamt())));
								tendercardTbl.setCcNo(salesHeaderDto.getCreditCardDTO().get(i).getCcno().replaceAll(" ", ""));
								tendercardTbl.setRefNbr10(getSubString(salesHeaderDto.getCreditCardDTO().get(i).getCcname().replaceAll("\r\n", "").replaceAll("/", " ")));
								tendercardTbl.setRefNbr11(getSubString(salesHeaderDto.getCreditCardDTO().get(i).getCcbank()));
								tendercardTbl.setTendTypeGroup("CCARD");
								tendercardTbl.setTendTypeId("3000");
								tendercardTbl.setRecType("TTEND");
							}
							transactionTenderTbls.add(tendercardTbl);
						}
					} else {
						TransactionTenderTbl tendercardTbl = new TransactionTenderTbl();
						tendercardTbl.setTenderAmount(String.format("%." + currencyRounding, card));
						tendercardTbl.setCcNo(salesHeaderDto.getCardno());
						tendercardTbl.setRefNbr10(getSubString(salesHeaderDto.getCardname().replaceAll("\r\n", "").replaceAll("/", " ")));
						tendercardTbl.setTendTypeGroup("CCARD");
						tendercardTbl.setTendTypeId("3000");
						tendercardTbl.setRecType("TTEND");
						transactionTenderTbls.add(tendercardTbl);
					}
				}
			}
			
			if(mallVoucAmount > 0) {
				TransactionTenderTbl tenderTblforVoucher = null;
				List<PaymentsDto> paymentsDto = salesHeaderDto.getPaymentsDto();
				for(int i = 0; i < paymentsDto.size(); i++) {
					tenderTblforVoucher = new TransactionTenderTbl();
					tenderTblforVoucher.setVoucherNo(paymentsDto.get(i).getRefNo());
					tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, Double.parseDouble(paymentsDto.get(i).getAmount())));
					tenderTblforVoucher.setTendTypeGroup("VOUCH");
					tenderTblforVoucher.setTendTypeId("4040");
					tenderTblforVoucher.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforVoucher);
				}
			}
			
			if(creditnote > 0) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo());
				if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
				} else {
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote));
				}
				tenderTblforCreditNote.setTendTypeGroup("VOUCH");
				tenderTblforCreditNote.setTendTypeId("4050");
				tenderTblforCreditNote.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCreditNote);
			}
			
			if(voucherAmt > 0) {
				TransactionTenderTbl tenderTblforVoucher = new TransactionTenderTbl();
				if(salesHeaderDto.getVoucherNo() != null && !salesHeaderDto.getVoucherNo().isEmpty()) {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getVoucherNo());
				} else {
					tenderTblforVoucher.setVoucherNo(salesHeaderDto.getLoyaltyCardNo());
				}
				tenderTblforVoucher.setTenderAmount(String.format("%." + currencyRounding, voucherAmt));
				tenderTblforVoucher.setTendTypeGroup("VOUCH");
				tenderTblforVoucher.setTendTypeId("4000");
				tenderTblforVoucher.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforVoucher);
			}
			if(creditnote1 > 0	||	creditnote2 > 0	||	creditnote3 > 0	||	creditnote4 > 0) {
				if(creditnote1 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo1());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo1());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote1));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				} else if(creditnote2 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo2());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo2());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote2));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				} else if(creditnote3 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo3());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo3());
					if(usedAmount > 0 && null != creditNoteNo && !creditNoteNo.isEmpty() && creditNoteNo.equals(salesHeaderDto.getCreditNoteNo())) {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, usedAmount));
					} else {
						tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote3));
					}
					tenderTblforCreditNote.setTendTypeGroup("VOUCH");
					tenderTblforCreditNote.setTendTypeId("4050");
					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				} 

				if(creditnote4 > 0) {
					TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
//					tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo4());
					tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo4());
					tenderTblforCreditNote.setTenderAmount(String.format("%." + currencyRounding, creditnote4));
					tenderTblforCreditNote.setTendTypeId("10010");
					tenderTblforCreditNote.setTendTypeGroup("OTHERS");

					tenderTblforCreditNote.setRecType("TTEND");
					transactionTenderTbls.add(tenderTblforCreditNote);
				}
			}
		}
		return transactionTenderTbls;
	}
	
	public SalesRequest buildReturnSalesRequest(SalesHeaderDto salesHeaderDTO, StoreDTO storeDTO) throws Exception {
		//----------For FHead
		logger.info("Inside buildReturnSalesRequest method");
		SalesRequest salesRequest = new SalesRequest();
		
		String registerId = getRegisterDetails(salesHeaderDTO, "RETURN", null, null);
		
		if(null != registerId && !registerId.isEmpty()) {
			logger.info("registerId for Return is :: " + registerId);
			String transSeq = salesDataService.getLastTransactionNo(registerId);
			
			String tenderTypeIdForCash = "";
			String tenderTypeIdForCard = "";
			long transSequence = Long.parseLong(transSeq);
			List<TransactionHeadTbl> transactionHeadTbl = new ArrayList<>();
			salesRequest.setStore(storeDTO.getRms_StoreId());
			salesRequest.setRtlogOrgSys("POS");
			salesRequest.setRecType("FHEAD");
			
			//---------- To set transactiondate format
			String source = salesHeaderDTO.getSReturnDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
			salesRequest.setBusinessDate(new SimpleDateFormat("yyyyMMdd").format(date));
			
			//--------- For THEAD
			TransactionHeadTbl tranHeader = new TransactionHeadTbl();
			SalesFtailTbl tranFtail = new SalesFtailTbl();
			tranHeader.setCashier(salesHeaderDTO.getPreparedBy());
//			String trndate =  new SimpleDateFormat("yyyyMMddHHMMSS").format(date);
			Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(salesHeaderDTO.getTrndate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMSS");
			String businessDate = sdf.format(format);
			tranHeader.setTransactionDate(businessDate);
			tranHeader.setRecType("THEAD");
			tranHeader.setRefNo1(getSubString(salesHeaderDTO.getSReturnNo()));
			tranHeader.setRefNo2(getSubString(salesHeaderDTO.getInvoiceDONo()));
//			if(salesHeaderDTO.getBeamCardNo() != null && !salesHeaderDTO.getBeamCardNo().isEmpty()) {
//				String payRollSeq = salesDataService.getNextTransSequence();
//				tranHeader.setRefNo3(payRollSeq);
//			}
//			tranHeader.setValue(salesHeaderDTO.getNetamount());
			tranHeader.setSalesperson(salesHeaderDTO.getUserId());
			//tranHeader.setReasonCode(salesHeaderDTO.getReason());
			
			tranHeader.setRegister(registerId);
			String tranNumber = "" + transSequence;
			tranHeader.setTransactionNo(tranNumber);
			tranHeader.setTransactionType("RETURN");
			tranHeader.setSubTransactionType("RETURN");
			
//			ReturnTenderDTO tenderDetailsForReturn = returnSalesDataService.fetchTenderDetailsForReturn(salesHeaderDTO.getSReturnNo());
			
			String currencyRounding = ConfigProperties.getInstance().getConfigValue("CURRENCY_ROUNDING") + "f";

			//------ tender Data
			List<TransactionTenderTbl> transactionTenderTbls = setReturnTenderData(salesHeaderDTO, tenderTypeIdForCash, tenderTypeIdForCard, currencyRounding);
			tranHeader.setTransactionTenderTbl(transactionTenderTbls);
			tranHeader.setTranProcessSys("POS");
			
			double tenderAmt = 0.0;
			for(int i = 0; i < transactionTenderTbls.size(); i++) {
				tenderAmt = tenderAmt - Double.parseDouble(transactionTenderTbls.get(i).getTenderAmount());
			}
			tranHeader.setValue(String.valueOf("-" + tenderAmt));
			
//			String.format("%.3f", tenderAmt));
			
			//-----------For Ftail
			tranFtail.setRecCounter(businessDate);
			
			//-----------For TItems
			List<TransactionItemTbl> listOfTransactionItemTbl = new ArrayList<>();
			for(int j = 0; j < salesHeaderDTO.getSalesDetailsDtos().size(); j++) {
				TransactionItemTbl transactionItemTbl = new TransactionItemTbl();
				transactionItemTbl.setRecType("TITEM");
				transactionItemTbl.setQuantity("-" + salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				transactionItemTbl.setItem(salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode());
				transactionItemTbl.setItemType("ITEM");
				transactionItemTbl.setSalesType("R");
				transactionItemTbl.setItemStatus("R");
				transactionItemTbl.setItemNoType("ITEM");
				transactionItemTbl.setCatchWeightInd("N");
				transactionItemTbl.setUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
				transactionItemTbl.setOriginalUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
//				transactionItemTbl.setUnitRetail(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate());
//				transactionItemTbl.setOriginalUnitRetail(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate());
				transactionItemTbl.setTaxableIndicator("Y");
				transactionItemTbl.setVoucherNo(salesHeaderDTO.getVoucherNo());
				transactionItemTbl.setSellingUom("EA");
				//			transactionItemTbl.setSellingUom(salesHeaderDTO.getSalesDetailsDtos().get(j).getUnitCode());
				transactionItemTbl.setItemSwipedInd("Y");
				transactionItemTbl.setDropShipInd("N");
				//transactionItemTbl.setReturnReasonCode(salesHeaderDTO.getReason());
				transactionItemTbl.setUomQty("-" + salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				
				String reason = salesHeaderDTO.getReason();
				
				transactionItemTbl.setRefNo5(getSubString(reason));
				
				try {
					String originaTransactionNo = salesDataService.getOriginaTransactionNo(salesHeaderDTO.getInvoiceDONo());
					transactionItemTbl.setOriginalStore(storeDTO.getRms_StoreId());
					if(null != originaTransactionNo && !originaTransactionNo.isEmpty()) {
						transactionItemTbl.setOriginalTransactionNo(originaTransactionNo);
					}
				} catch(Exception e) {
					logger.info("we did not find the transaction no in sales Header for RETURN TRAN :- " + e.getMessage());
				}
				
				//---------------- For TIGTAX
				List<InvoiceVatItemDTO> vatItemDetails = returnSalesDataService.getVatItemDetails(salesHeaderDTO.getSalesDetailsDtos().get(j).getSReturnNo(), salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode());
				if(null != vatItemDetails && vatItemDetails.size() > 0) {
					List<TransactionItemTaxTbl> tigTaxTableList = setTigTax(vatItemDetails, storeDTO);
					transactionItemTbl.setTransactionItemTaxTbl(tigTaxTableList);
				}

				//---------------- For Discount

				double discAmount = Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getDiscount());
				if(discAmount > 0) {
					List<TransactionItemDiscountTbl> discountTblsList = setTransactionItemDiscountTbl(salesHeaderDTO.getSalesDetailsDtos().get(j), null, "RETURN");
					transactionItemTbl.setTransactionItemDiscountTbl(discountTblsList);
				}
				listOfTransactionItemTbl.add(transactionItemTbl);
			}
			//-----------------For Customer
			List<TransactionCustomerTbl> customerList = setCustomerTbls(salesHeaderDTO, tranNumber);

			tranHeader.setTransactionCustomerTbl(customerList);
			tranHeader.setTransactionItemTbl(listOfTransactionItemTbl);
			transactionHeadTbl.add(tranHeader);
			salesRequest.setTransactionHeadTbl(transactionHeadTbl);
		}
		return salesRequest;
	}
	
	public List<TransactionTenderTbl> setReturnTenderData(SalesHeaderDto salesHeaderDto, String tenderTypeIdForCash, String tenderTypeIdForCard, String currencyRounding) {
		List<TransactionTenderTbl> transactionTenderTbls = new ArrayList<>();
		String netAmount = "-" + String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDto.getNetamount()));
		List<CreditNoteHistoryDTO> creditNoteHistoryDTOs = salesHeaderDto.getCreditNoteHistoryDTOs();
		if(salesHeaderDto.getTrnType().equals("C")) {
			if(null != salesHeaderDto.getRemarks() && !salesHeaderDto.getRemarks().trim().isEmpty() && salesHeaderDto.getRemarks().toLowerCase().contains("card")) {
				TransactionTenderTbl tendercardTbl = new TransactionTenderTbl();
				tendercardTbl.setTenderAmount(netAmount);
				tendercardTbl.setCcNo("");
				tendercardTbl.setRefNbr10("");
				tendercardTbl.setRefNbr11("");
				tendercardTbl.setTendTypeGroup("CCARD");
				tendercardTbl.setTendTypeId("3000");
				tendercardTbl.setRecType("TTEND");
				transactionTenderTbls.add(tendercardTbl);
			} else {
				TransactionTenderTbl tenderTblforCash = new TransactionTenderTbl();
				tenderTblforCash.setTenderAmount(netAmount);
				tenderTblforCash.setTendTypeId("1000");
				tenderTblforCash.setTendTypeGroup("CASH");
				tenderTblforCash.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCash);
			}
		} else if(salesHeaderDto.getTrnType().equals("R")) {
			if(null != salesHeaderDto.getCreditNoteNo() && !salesHeaderDto.getCreditNoteNo().trim().isEmpty()) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setTenderAmount(netAmount);
				tenderTblforCreditNote.setTendTypeGroup("VOUCH");
				tenderTblforCreditNote.setTendTypeId("4050");
				tenderTblforCreditNote.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCreditNote);
			} else {
				TransactionTenderTbl tenderTblforWallet = new TransactionTenderTbl();
				tenderTblforWallet.setTenderAmount(netAmount);
				tenderTblforWallet.setTendTypeId("10010");
				tenderTblforWallet.setTendTypeGroup("OTHERS");
				tenderTblforWallet.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforWallet);
			}
		} else {
			if(null != salesHeaderDto.getCreditNoteNo() && !salesHeaderDto.getCreditNoteNo().trim().isEmpty()) {
				TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
				tenderTblforCreditNote.setCouponNbr(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setVoucherNo(salesHeaderDto.getCreditNoteNo());
				tenderTblforCreditNote.setTenderAmount(netAmount);
				tenderTblforCreditNote.setTendTypeGroup("VOUCH");
				tenderTblforCreditNote.setTendTypeId("4050");
				tenderTblforCreditNote.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforCreditNote);
			} else {
				TransactionTenderTbl tenderTblforWallet = new TransactionTenderTbl();
				tenderTblforWallet.setTenderAmount(netAmount);
				tenderTblforWallet.setTendTypeId("10010");
				tenderTblforWallet.setTendTypeGroup("OTHERS");
				tenderTblforWallet.setRecType("TTEND");
				transactionTenderTbls.add(tenderTblforWallet);
			}
		}
		if(null != creditNoteHistoryDTOs && creditNoteHistoryDTOs.size() > 0) {
			for(CreditNoteHistoryDTO creditNoteHistoryDTO : creditNoteHistoryDTOs) {
				Double totalAmt = Double.parseDouble(creditNoteHistoryDTO.getTotalAmt());
				Double usedAmt = Double.parseDouble(creditNoteHistoryDTO.getUsedAmt());
				if(usedAmt < totalAmt) {
					if(null != creditNoteHistoryDTO.getWalletAmt() && Double.parseDouble(creditNoteHistoryDTO.getWalletAmt()) > 0) {
						TransactionTenderTbl tenderTblforWallet = new TransactionTenderTbl();
						tenderTblforWallet.setTenderAmount("-" + Double.parseDouble(creditNoteHistoryDTO.getWalletAmt()));
						tenderTblforWallet.setTendTypeId("10010");
						tenderTblforWallet.setTendTypeGroup("OTHERS");
						tenderTblforWallet.setRecType("TTEND");
						transactionTenderTbls.add(tenderTblforWallet);
					} else if(null != creditNoteHistoryDTO.getReIssdAmt() && Double.parseDouble(creditNoteHistoryDTO.getReIssdAmt()) > 0) {
						TransactionTenderTbl tenderTblforCreditNote = new TransactionTenderTbl();
						tenderTblforCreditNote.setCouponNbr(creditNoteHistoryDTO.getCrNoteNo());
						tenderTblforCreditNote.setVoucherNo(creditNoteHistoryDTO.getCrNoteNo());
						tenderTblforCreditNote.setTenderAmount("-" + Double.parseDouble(creditNoteHistoryDTO.getReIssdAmt()));
						tenderTblforCreditNote.setTendTypeGroup("VOUCH");
						tenderTblforCreditNote.setTendTypeId("4050");
						tenderTblforCreditNote.setRecType("TTEND");
						transactionTenderTbls.add(tenderTblforCreditNote);
					}
				}
			}
		}
		return transactionTenderTbls;
	}

	
	public SalesRequest buildOtherStoreReturnSalesRequest(SalesHeaderDto salesHeaderDTO, StoreDTO storeDTO, Item item, StoreDTO originalStoreDetials) throws Exception {
		logger.info("Inside buildOtherStoreReturnSalesRequest method");
		//----------For FHead
		SalesRequest salesRequest = new SalesRequest();
		
		String registerId = getRegisterDetails(salesHeaderDTO, "RETURN", null, null);
		
		if(null != registerId && !registerId.isEmpty()) {
			
			String transSeq = salesDataService.getLastTransactionNo(registerId);
			
			String tenderTypeIdForCash = "";
			String tenderTypeIdForCard = "";
			long transSequence = Long.parseLong(transSeq);
			List<TransactionHeadTbl> transactionHeadTbl = new ArrayList<>();
			salesRequest.setStore(storeDTO.getRms_StoreId());
			salesRequest.setRtlogOrgSys("POS");
			salesRequest.setRecType("FHEAD");
			TransactionHeadTbl tranHeader = new TransactionHeadTbl();
			
			//---------- To set transaction date format
			String source = salesHeaderDTO.getSReturnDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
			String trnDate =  new SimpleDateFormat("yyyyMMdd").format(date);
			salesRequest.setBusinessDate(trnDate);
//			String trndate =  new SimpleDateFormat("yyyyMMddHHMMSS").format(date);
			Date format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(salesHeaderDTO.getTrndate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMSS");
			String businessDate = sdf.format(format);
			tranHeader.setTransactionDate(businessDate);
			
			//--------- For THEAD
			
			SalesFtailTbl tranFtail = new SalesFtailTbl();
			
			if(null != salesHeaderDTO.getShop() && !salesHeaderDTO.getShop().trim().isEmpty() && salesHeaderDTO.getShop().equalsIgnoreCase("Online")) {
				tranHeader.setCashier("Online");
			} else {
				tranHeader.setCashier(salesHeaderDTO.getPreparedBy());
			}
			tranHeader.setRecType("THEAD");
			tranHeader.setRefNo1(getSubString(salesHeaderDTO.getSReturnNo()));
			tranHeader.setRefNo2(getSubString(salesHeaderDTO.getInvoiceDONo()));

			tranHeader.setRefNo28("OTH");
			tranHeader.setRefNo29(getSubString(salesHeaderDTO.getCostCode()));
			
//			tranHeader.setRefNo30(item.getStore());
//			tranHeader.setRefNo31(item.getRegister());
			
//			tranHeader.setValue(salesHeaderDTO.getNetamount());
			tranHeader.setRegister(registerId);
			tranHeader.setSalesperson(salesHeaderDTO.getUserId());
			//tranHeader.setReasonCode(salesHeaderDTO.getReason());
			String tranNumber = "" + transSequence;
			tranHeader.setTransactionNo(tranNumber);
			tranHeader.setTransactionType("RETURN");
			tranHeader.setSubTransactionType("RETURN");
			
			String currencyRounding = ConfigProperties.getInstance().getConfigValue("CURRENCY_ROUNDING") + "f";
			
//			ReturnTenderDTO tenderDetailsForReturn = returnSalesDataService.fetchTenderDetailsForReturn(salesHeaderDTO.getSReturnNo());
			
			//-- To set tender Data
			List<TransactionTenderTbl> transactionTenderTbls = setReturnTenderData(salesHeaderDTO, tenderTypeIdForCash, tenderTypeIdForCard, currencyRounding);
			tranHeader.setTransactionTenderTbl(transactionTenderTbls);
			tranHeader.setTranProcessSys("POS");
			
			double tenderAmt = 0.0;
			for(int i = 0; i < transactionTenderTbls.size(); i++) {
				tenderAmt = tenderAmt - Double.parseDouble(transactionTenderTbls.get(i).getTenderAmount());
			}
			tranHeader.setValue(String.valueOf("-" + tenderAmt));

			//-----------For Ftail
			tranFtail.setRecCounter(businessDate);

			//-----------For TItems
			List<TransactionItemTbl> listOfTransactionItemTbl = new ArrayList<>();
			for(int j = 0; j < salesHeaderDTO.getSalesDetailsDtos().size(); j++) {
				TransactionItemTbl transactionItemTbl = new TransactionItemTbl();
				transactionItemTbl.setQuantity("-" + salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				transactionItemTbl.setItem(salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode());
				transactionItemTbl.setItemType("ITEM");
				transactionItemTbl.setRecType("TITEM");
				transactionItemTbl.setSalesType("R");
				transactionItemTbl.setItemStatus("R");
				transactionItemTbl.setItemNoType("ITEM");
				transactionItemTbl.setCatchWeightInd("N");
//				transactionItemTbl.setUnitRetail(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate());
//				transactionItemTbl.setOriginalUnitRetail(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate());
				transactionItemTbl.setUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
				transactionItemTbl.setOriginalUnitRetail(String.format("%." + currencyRounding, Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getRate())));
				transactionItemTbl.setTaxableIndicator("Y");
				transactionItemTbl.setVoucherNo(salesHeaderDTO.getVoucherNo());
				transactionItemTbl.setSellingUom("EA");
				//			transactionItemTbl.setSellingUom(salesHeaderDTO.getSalesDetailsDtos().get(j).getUnitCode());
				transactionItemTbl.setItemSwipedInd("Y");
				transactionItemTbl.setDropShipInd("N");
				//transactionItemTbl.setReturnReasonCode(salesHeaderDTO.getReason());
				transactionItemTbl.setUomQty("-" + salesHeaderDTO.getSalesDetailsDtos().get(j).getQuantity());
				
				if(null != item) {
					transactionItemTbl.setOriginalStore(item.getStore());
					transactionItemTbl.setOriginalTransactionNo(item.getTranno());
				} else {
					if(null != originalStoreDetials && null != originalStoreDetials.getRms_StoreId() && !originalStoreDetials.getRms_StoreId().isEmpty()) {
						transactionItemTbl.setOriginalStore(originalStoreDetials.getRms_StoreId());
					}
				}
				
				//---------------- For TIGTAX
				List<InvoiceVatItemDTO> vatItemDetails = returnSalesDataService.getVatItemDetails(salesHeaderDTO.getSalesDetailsDtos().get(j).getSReturnNo(), salesHeaderDTO.getSalesDetailsDtos().get(j).getItemCode());
				if(null != vatItemDetails && vatItemDetails.size() > 0) {
					List<TransactionItemTaxTbl> tigTaxTableList = setTigTax(vatItemDetails, storeDTO);
					transactionItemTbl.setTransactionItemTaxTbl(tigTaxTableList);
				}
				
				//---------------- For Discount
				double discAmount = Double.parseDouble(salesHeaderDTO.getSalesDetailsDtos().get(j).getDiscount());
				if(discAmount > 0) {
					List<TransactionItemDiscountTbl> discountTblsList = setTransactionItemDiscountTbl(salesHeaderDTO.getSalesDetailsDtos().get(j), null, "RETURN");
					transactionItemTbl.setTransactionItemDiscountTbl(discountTblsList);
				}
				listOfTransactionItemTbl.add(transactionItemTbl);
			}
			//-----------------For Customer
			List<TransactionCustomerTbl> customerList = setCustomerTbls(salesHeaderDTO, tranNumber);
			//---------------- For VAT -- BFL is not maintaining transaction level tax
			//		List<TransactionTaxTbl> vatItemList = setTransactionTaxTbls(salesHeaderDTO.getInvoiceVatItemDTOs());
			//------- For Payment
//			List<TransactionPaymentTbl> paymentTblsList = setPaymentData(salesHeaderDTO.getPaymentsDto());
			//		tranHeader.setTransactionTaxTbl(vatItemList);
//			tranHeader.setTransactionPaymentTbl(paymentTblsList);
			tranHeader.setTransactionCustomerTbl(customerList);
			tranHeader.setTransactionItemTbl(listOfTransactionItemTbl);
			transactionHeadTbl.add(tranHeader);
			salesRequest.setTransactionHeadTbl(transactionHeadTbl);
		}
		return salesRequest;
	}
	
	public String getRegisterDetails(SalesHeaderDto salesData, String tranType, DailyStoreClosingDTO dailyStoreClosingDTO, CloseShopDTO closeShopDTO) {
		String registerVal = "";
		
		List<RTLogConfigDTO> rtlogData = null;
		
		if("SALE".equals(tranType)) {
			rtlogData = salesDataService.getDataFromRtlogConfig(salesData.getLocCode(), "");
		} else if("RETURN".equals(tranType)) {
			rtlogData = salesDataService.getDataFromRtlogConfig(salesData.getRepCode(), "");
		} else if("CLOSESHOP".equals(tranType)) {
			rtlogData = salesDataService.getDataFromRtlogConfig(closeShopDTO.getCompName(), "");
		} else {
			rtlogData = salesDataService.getDataFromRtlogConfig(dailyStoreClosingDTO.getSystemName(), tranType);
		}
		
		if(null == rtlogData || rtlogData.size() == 0) {
			RTLogConfigDTO configDTO = new RTLogConfigDTO();
			configDTO.setRECORD_TYPE("REGISTER_ID");
			if("DailyClosing".equals(tranType)) {
				configDTO.setBFL_CODES(dailyStoreClosingDTO.getCashier());
				configDTO.setBFL_VALUES(dailyStoreClosingDTO.getSystemName());
			} else if("RETURN".equals(tranType)) {
				configDTO.setBFL_CODES(salesData.getPreparedBy());
				configDTO.setBFL_VALUES(salesData.getRepCode());
			} else if("CLOSESHOP".equals(tranType)) {
				configDTO.setBFL_CODES(closeShopDTO.getCode());
				configDTO.setBFL_VALUES(closeShopDTO.getCompName());
			} else {
				configDTO.setBFL_CODES(salesData.getPreparedBy());
				configDTO.setBFL_VALUES(salesData.getLocCode());
			}
			configDTO.setRESA_CODES(" ");
			List<RTLogConfigDTO> latestDataFromRtlogConfig = salesDataService.getLatestDataFromRtlogConfig();
			if(null != latestDataFromRtlogConfig && latestDataFromRtlogConfig.size() > 0) {
				registerVal = String.valueOf(Integer.parseInt(latestDataFromRtlogConfig.get(0).getRESA_VALUES()) + 1);
				configDTO.setRESA_VALUES(registerVal);
			} else {
				registerVal = "101";
				configDTO.setRESA_VALUES(registerVal);
			}
			rtlogData = new ArrayList<>();
			rtlogData.add(configDTO);
			salesDataService.setRTLogConfigDTO(rtlogData);
		} else {
			registerVal = rtlogData.get(0).getRESA_VALUES();
		}
		return registerVal;
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
	
	public String getSubString(String input) {
		return (null != input && !input.isEmpty() && input.length() > 30) ? input.substring(0, 29) : input;
	}
	
}
