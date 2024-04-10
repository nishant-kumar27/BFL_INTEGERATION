package com.bfl.dto;

import java.util.List;

public class SalesHeaderDto {

	private String InvoiceNo;

	private String InvoiceDate;

	private String TrnType;

	private String LocationCode;

	private String SystemName;

	private String GrossAmount;

	private String TotalDiscount;

	private String CrYes;

	private String Netamount;

	private String PaidAmount;

	private String DONo;

	private String LPONo;

	private String UserId;

	private String PreparedBy;

	private String details;

	private String Name;

	private String Fax;

	private String Time1;

	private String CashAmt;

	private String CreditAmt;

	private String MobileNo;

	private String CreditNoteNo;

	private String CreditNoteAmt;

	private String LoyaltyCardNo;

	private String LoyaltyPoint;

	private String VoucherNo;

	private String VoucherAmt;

	private String BeamCardNo;

	private String trndate;

	private String CreditNoteNo1;

	private String CreditNoteAmt1;

	private String CreditNoteNo2;

	private String CreditNoteAmt2;

	private String CreditNoteNo3;

	private String CreditNoteAmt3;

	private String CreditNoteNo4;

	private String CreditNoteAmt4;

	private String SReturnNo;

	private String SReturnDate;

	private String DebitCode;

	private String CreditCode;

	private String RefType;

	private String InvoiceDONo;

	private String CustCode;

	private String CostCode;

	private String LocCode;

	private String RepCode;

	private String FCCode;

	private String FCRate;

	private String ApprovedBy;

	private String EntryMode;

	private String RevMarginCode;

	private String Remarks;

	private String Reason;

	private String EmpName;

	private String CreditInvNo;

	private String CreditExpiryDt;

	private String VoidAppCode;

	private String addr1;

	private String pobox;

	private String cardname;

	private String cardno;

	private String ReturnAmount;
	
	private String ccNo;
	
	private String ccName;
	
	private String ccAmt;

	private String ccCode;
	
	private String ccRectNo;
	
	private String ccBank;
	
	private String shop;
	
	private String totVohamount;
	
	private List<SalesDetailsDto> salesDetailsDtos;

	private List<PaymentsDto> paymentsDto;
	
	private List<CreditCardDTO> creditCardDTO;

	private List<InvoiceVatItemDTO> invoiceVatItemDTOs;
	
	private List<CreditNoteHistoryDTO> creditNoteHistoryDTOs;
	
	private String discountReason;

	public String getInvoiceNo() {
		return InvoiceNo;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getReturnAmount() {
		return ReturnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		ReturnAmount = returnAmount;
	}

	public String getPobox() {
		return pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}



	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public String getTrnType() {
		return TrnType;
	}

	public void setTrnType(String trnType) {
		TrnType = trnType;
	}

	public String getLocationCode() {
		return LocationCode;
	}

	public void setLocationCode(String locationCode) {
		LocationCode = locationCode;
	}

	public String getSystemName() {
		return SystemName;
	}

	public void setSystemName(String systemName) {
		SystemName = systemName;
	}

	public String getGrossAmount() {
		return GrossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		GrossAmount = grossAmount;
	}

	public String getTotalDiscount() {
		return TotalDiscount;
	}

	public void setTotalDiscount(String totalDiscount) {
		TotalDiscount = totalDiscount;
	}

	public String getNetamount() {
		return Netamount;
	}

	public void setNetamount(String netamount) {
		Netamount = netamount;
	}

	public String getPaidAmount() {
		return PaidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		PaidAmount = paidAmount;
	}

	public String getDONo() {
		return DONo;
	}

	public void setDONo(String dONo) {
		DONo = dONo;
	}

	public String getLPONo() {
		return LPONo;
	}

	public void setLPONo(String lPONo) {
		LPONo = lPONo;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getPreparedBy() {
		return PreparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		PreparedBy = preparedBy;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
	}

	public String getTime1() {
		return Time1;
	}

	public void setTime1(String time1) {
		Time1 = time1;
	}

	public String getCashAmt() {
		return CashAmt;
	}

	public void setCashAmt(String cashAmt) {
		CashAmt = cashAmt;
	}

	public String getCreditAmt() {
		return CreditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		CreditAmt = creditAmt;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getCreditNoteNo() {
		return CreditNoteNo;
	}

	public void setCreditNoteNo(String creditNoteNo) {
		CreditNoteNo = creditNoteNo;
	}

	public String getCrYes() {
		return CrYes;
	}

	public void setCrYes(String crYes) {
		CrYes = crYes;
	}

	public String getCreditNoteAmt() {
		return CreditNoteAmt;
	}

	public void setCreditNoteAmt(String creditNoteAmt) {
		CreditNoteAmt = creditNoteAmt;
	}

	public String getLoyaltyCardNo() {
		return LoyaltyCardNo;
	}

	public void setLoyaltyCardNo(String loyaltyCardNo) {
		LoyaltyCardNo = loyaltyCardNo;
	}

	public String getLoyaltyPoint() {
		return LoyaltyPoint;
	}

	public void setLoyaltyPoint(String loyaltyPoint) {
		LoyaltyPoint = loyaltyPoint;
	}

	public String getVoucherNo() {
		return VoucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		VoucherNo = voucherNo;
	}

	public String getVoucherAmt() {
		return VoucherAmt;
	}

	public void setVoucherAmt(String voucherAmt) {
		VoucherAmt = voucherAmt;
	}

	public String getBeamCardNo() {
		return BeamCardNo;
	}

	public void setBeamCardNo(String beamCardNo) {
		BeamCardNo = beamCardNo;
	}

	public String getTrndate() {
		return trndate;
	}

	public void setTrndate(String trndate) {
		this.trndate = trndate;
	}

	public String getCreditNoteNo1() {
		return CreditNoteNo1;
	}

	public void setCreditNoteNo1(String creditNoteNo1) {
		CreditNoteNo1 = creditNoteNo1;
	}

	public String getCreditNoteAmt1() {
		return CreditNoteAmt1;
	}

	public void setCreditNoteAmt1(String creditNoteAmt1) {
		CreditNoteAmt1 = creditNoteAmt1;
	}

	public String getCreditNoteNo2() {
		return CreditNoteNo2;
	}

	public void setCreditNoteNo2(String creditNoteNo2) {
		CreditNoteNo2 = creditNoteNo2;
	}

	public String getCreditNoteAmt2() {
		return CreditNoteAmt2;
	}

	public void setCreditNoteAmt2(String creditNoteAmt2) {
		CreditNoteAmt2 = creditNoteAmt2;
	}

	public String getCreditNoteNo3() {
		return CreditNoteNo3;
	}

	public void setCreditNoteNo3(String creditNoteNo3) {
		CreditNoteNo3 = creditNoteNo3;
	}

	public String getCreditNoteAmt3() {
		return CreditNoteAmt3;
	}

	public void setCreditNoteAmt3(String creditNoteAmt3) {
		CreditNoteAmt3 = creditNoteAmt3;
	}

	public String getCreditNoteNo4() {
		return CreditNoteNo4;
	}

	public void setCreditNoteNo4(String creditNoteNo4) {
		CreditNoteNo4 = creditNoteNo4;
	}

	public String getCreditNoteAmt4() {
		return CreditNoteAmt4;
	}

	public void setCreditNoteAmt4(String creditNoteAmt4) {
		CreditNoteAmt4 = creditNoteAmt4;
	}

	public String getSReturnNo() {
		return SReturnNo;
	}

	public void setSReturnNo(String sReturnNo) {
		SReturnNo = sReturnNo;
	}

	public String getSReturnDate() {
		return SReturnDate;
	}

	public void setSReturnDate(String sReturnDate) {
		SReturnDate = sReturnDate;
	}

	public String getDebitCode() {
		return DebitCode;
	}

	public void setDebitCode(String debitCode) {
		DebitCode = debitCode;
	}

	public String getCreditCode() {
		return CreditCode;
	}

	public void setCreditCode(String creditCode) {
		CreditCode = creditCode;
	}

	public String getRefType() {
		return RefType;
	}

	public void setRefType(String refType) {
		RefType = refType;
	}

	public String getInvoiceDONo() {
		return InvoiceDONo;
	}

	public void setInvoiceDONo(String invoiceDONo) {
		InvoiceDONo = invoiceDONo;
	}

	public String getCustCode() {
		return CustCode;
	}

	public void setCustCode(String custCode) {
		CustCode = custCode;
	}

	public String getCostCode() {
		return CostCode;
	}

	public void setCostCode(String costCode) {
		CostCode = costCode;
	}

	public String getLocCode() {
		return LocCode;
	}

	public void setLocCode(String locCode) {
		LocCode = locCode;
	}

	public String getRepCode() {
		return RepCode;
	}

	public void setRepCode(String repCode) {
		RepCode = repCode;
	}

	public String getFCCode() {
		return FCCode;
	}

	public void setFCCode(String fCCode) {
		FCCode = fCCode;
	}

	public String getFCRate() {
		return FCRate;
	}

	public void setFCRate(String fCRate) {
		FCRate = fCRate;
	}

	public String getApprovedBy() {
		return ApprovedBy;
	}

	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}

	public String getEntryMode() {
		return EntryMode;
	}

	public void setEntryMode(String entryMode) {
		EntryMode = entryMode;
	}

	public String getRevMarginCode() {
		return RevMarginCode;
	}

	public void setRevMarginCode(String revMarginCode) {
		RevMarginCode = revMarginCode;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getCreditInvNo() {
		return CreditInvNo;
	}

	public void setCreditInvNo(String creditInvNo) {
		CreditInvNo = creditInvNo;
	}

	public String getCreditExpiryDt() {
		return CreditExpiryDt;
	}

	public void setCreditExpiryDt(String creditExpiryDt) {
		CreditExpiryDt = creditExpiryDt;
	}

	public String getVoidAppCode() {
		return VoidAppCode;
	}

	public void setVoidAppCode(String voidAppCode) {
		VoidAppCode = voidAppCode;
	}
	
	public String getCcNo() {
		return ccNo;
	}

	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcAmt() {
		return ccAmt;
	}

	public void setCcAmt(String ccAmt) {
		this.ccAmt = ccAmt;
	}

	public String getCcCode() {
		return ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getCcRectNo() {
		return ccRectNo;
	}

	public void setCcRectNo(String ccRectNo) {
		this.ccRectNo = ccRectNo;
	}

	public String getCcBank() {
		return ccBank;
	}

	public void setCcBank(String ccBank) {
		this.ccBank = ccBank;
	}

	public List<SalesDetailsDto> getSalesDetailsDtos() {
		return salesDetailsDtos;
	}

	public void setSalesDetailsDtos(List<SalesDetailsDto> salesDetailsDtos) {
		this.salesDetailsDtos = salesDetailsDtos;
	}

	public List<PaymentsDto> getPaymentsDto() {
		return paymentsDto;
	}

	public void setPaymentsDto(List<PaymentsDto> paymentsDto) {
		this.paymentsDto = paymentsDto;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public List<InvoiceVatItemDTO> getInvoiceVatItemDTOs() {
		return invoiceVatItemDTOs;
	}

	public void setInvoiceVatItemDTOs(List<InvoiceVatItemDTO> invoiceVatItemDTOs) {
		this.invoiceVatItemDTOs = invoiceVatItemDTOs;
	}
	
	public List<CreditCardDTO> getCreditCardDTO() {
		return creditCardDTO;
	}

	public void setCreditCardDTO(List<CreditCardDTO> creditCardDTO) {
		this.creditCardDTO = creditCardDTO;
	}
	
	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}
	
	public String getTotVohamount() {
		return totVohamount;
	}

	public void setTotVohamount(String totVohamount) {
		this.totVohamount = totVohamount;
	}
	
	public String getDiscountReason() {
		return discountReason;
	}

	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}
	
	public List<CreditNoteHistoryDTO> getCreditNoteHistoryDTOs() {
		return creditNoteHistoryDTOs;
	}

	public void setCreditNoteHistoryDTOs(List<CreditNoteHistoryDTO> creditNoteHistoryDTOs) {
		this.creditNoteHistoryDTOs = creditNoteHistoryDTOs;
	}

	@Override
	public String toString() {
		return "SalesHeaderDto [InvoiceNo=" + InvoiceNo + ", InvoiceDate=" + InvoiceDate + ", TrnType=" + TrnType
			+ ", LocationCode=" + LocationCode + ", SystemName=" + SystemName + ", GrossAmount=" + GrossAmount
			+ ", TotalDiscount=" + TotalDiscount + ", CrYes=" + CrYes + ", Netamount=" + Netamount + ", PaidAmount="
			+ PaidAmount + ", DONo=" + DONo + ", LPONo=" + LPONo + ", UserId=" + UserId + ", PreparedBy="
			+ PreparedBy + ", details=" + details + ", Name=" + Name + ", Fax=" + Fax + ", Time1=" + Time1
			+ ", CashAmt=" + CashAmt + ", CreditAmt=" + CreditAmt + ", MobileNo=" + MobileNo + ", CreditNoteNo="
			+ CreditNoteNo + ", CreditNoteAmt=" + CreditNoteAmt + ", LoyaltyCardNo=" + LoyaltyCardNo
			+ ", VoucherNo=" + VoucherNo + ", VoucherAmt=" + VoucherAmt + ", BeamCardNo=" + BeamCardNo
			+ ", trndate=" + trndate + ", CreditNoteNo1=" + CreditNoteNo1 + ", CreditNoteAmt1=" + CreditNoteAmt1
			+ ", CreditNoteNo2=" + CreditNoteNo2 + ", CreditNoteAmt2=" + CreditNoteAmt2 + ", CreditNoteNo3="
			+ CreditNoteNo3 + ", CreditNoteAmt3=" + CreditNoteAmt3 + ", CreditNoteNo4=" + CreditNoteNo4
			+ ", CreditNoteAmt4=" + CreditNoteAmt4 + ", SReturnNo=" + SReturnNo + ", SReturnDate=" + SReturnDate
			+ ", DebitCode=" + DebitCode + ", CreditCode=" + CreditCode + ", RefType=" + RefType + ", InvoiceDONo="
			+ InvoiceDONo + ", CustCode=" + CustCode + ", CostCode=" + CostCode + ", LocCode=" + LocCode
			+ ", RepCode=" + RepCode + ", FCCode=" + FCCode + ", FCRate=" + FCRate + ", ApprovedBy=" + ApprovedBy
			+ ", EntryMode=" + EntryMode + ", RevMarginCode=" + RevMarginCode + ", Remarks=" + Remarks + ", Reason="
			+ Reason + ", EmpName=" + EmpName + ", CreditInvNo=" + CreditInvNo + ", CreditExpiryDt="
			+ CreditExpiryDt + ", VoidAppCode=" + VoidAppCode + ", addr1=" + addr1 + ", pobox=" + pobox
			+ ", cardname=" + cardname + ", cardno=" + cardno + ", ReturnAmount=" + ReturnAmount
			+ ", salesDetailsDtos=" + salesDetailsDtos + ", paymentsDto=" + paymentsDto + ", invoiceVatItemDTOs="
			+ invoiceVatItemDTOs  + ", shop=" + shop + ", totVohamount =" + totVohamount + "]";
	}

}
