package com.bfl.dto;

public class SalesDetailsDto {
	
	private String	InvoiceNo;

	private String	ItemCode;

	private String	Quantity;

	private String	Rate;

	private String	Discount;

	private String	QuotNo;

	private String	UnitCode;

	private String	TrfNo;

	private String	RFID;

	private String	MRow;
	
	private String BatchNo;
	
	private String BasicQty;
	
	private String BasicRate;
	
	private String SReturnNo;
	
	private String discountReasonCode;

	public String getBatchNo() {
		return BatchNo;
	}

	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}

	public String getBasicQty() {
		return BasicQty;
	}

	public void setBasicQty(String basicQty) {
		BasicQty = basicQty;
	}

	public String getBasicRate() {
		return BasicRate;
	}

	public void setBasicRate(String basicRate) {
		BasicRate = basicRate;
	}

	public String getSReturnNo() {
		return SReturnNo;
	}

	public void setSReturnNo(String sReturnNo) {
		SReturnNo = sReturnNo;
	}

	public String getInvoiceNo() {
		return InvoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}

	public String getItemCode() {
		return ItemCode;
	}

	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getRate() {
		return Rate;
	}

	public void setRate(String rate) {
		Rate = rate;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public String getQuotNo() {
		return QuotNo;
	}

	public void setQuotNo(String quotNo) {
		QuotNo = quotNo;
	}

	public String getUnitCode() {
		return UnitCode;
	}

	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
	}

	public String getTrfNo() {
		return TrfNo;
	}

	public void setTrfNo(String trfNo) {
		TrfNo = trfNo;
	}

	public String getRFID() {
		return RFID;
	}

	public void setRFID(String rFID) {
		RFID = rFID;
	}

	public String getMRow() {
		return MRow;
	}

	public void setMRow(String mRow) {
		MRow = mRow;
	}
	
	public String getDiscountReasonCode() {
		return discountReasonCode;
	}

	public void setDiscountReasonCode(String discountReasonCode) {
		this.discountReasonCode = discountReasonCode;
	}

	@Override
	public String toString() {
		return "SalesDetailsDto [InvoiceNo=" + InvoiceNo + ", ItemCode=" + ItemCode + ", Quantity=" + Quantity
				+ ", Rate=" + Rate + ", Discount=" + Discount + ", QuotNo=" + QuotNo + ", UnitCode=" + UnitCode
				+ ", TrfNo=" + TrfNo + ", RFID=" + RFID + ", MRow=" + MRow + ", BatchNo=" + BatchNo + ", BasicQty="
				+ BasicQty + ", BasicRate=" + BasicRate + ", SReturnNo=" + SReturnNo + discountReasonCode + " = discountReasonCode" + "]";
	}
}
