package com.bfl.dto;

public class InvoiceVatItemDTO {

	private String	invoiceno;
	
	private String	Itemcode;
	
	private String	Discount;
	
	private String	VatPer;
	
	private String	VatAmt;
	
	private String	VatCode;
	
	private String	MRow;
	
	private String	Loyalty;

	public String getInvoiceno() {
		return invoiceno;
	}
	
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	
	public String getItemcode() {
		return Itemcode;
	}
	
	public void setItemcode(String itemcode) {
		Itemcode = itemcode;
	}
	
	public String getDiscount() {
		return Discount;
	}
	
	public void setDiscount(String discount) {
		Discount = discount;
	}
	
	public String getVatPer() {
		return VatPer;
	}
	
	public void setVatPer(String vatPer) {
		VatPer = vatPer;
	}
	
	public String getVatAmt() {
		return VatAmt;
	}
	
	public void setVatAmt(String vatAmt) {
		VatAmt = vatAmt;
	}
	
	public String getVatCode() {
		return VatCode;
	}
	
	public void setVatCode(String vatCode) {
		VatCode = vatCode;
	}
	
	public String getMRow() {
		return MRow;
	}
	
	public void setMRow(String mRow) {
		MRow = mRow;
	}
	
	public String getLoyalty() {
		return Loyalty;
	}
	
	public void setLoyalty(String loyalty) {
		Loyalty = loyalty;
	}
	
}

