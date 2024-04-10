package com.bfl.dto;

public class PurchaseOrdersDetailsDTO {
	
	private String SN;
	
	private String DiscountPerc;
	
	private String PalletCnt;
	
	private String Qty;
	
	private String LoadQty;
	
	private String GroupCode;
	
	private String Discount;
	
	private String jobNo;
	
	private String catCode;
	
	private String amount;
	
	private String bolNo;

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getDiscountPerc() {
		return DiscountPerc;
	}

	public void setDiscountPerc(String discountPerc) {
		DiscountPerc = discountPerc;
	}

	public String getPalletCnt() {
		return PalletCnt;
	}

	public void setPalletCnt(String palletCnt) {
		PalletCnt = palletCnt;
	}

	public String getQty() {
		return Qty;
	}

	public void setQty(String qty) {
		Qty = qty;
	}

	public String getLoadQty() {
		return LoadQty;
	}

	public void setLoadQty(String loadQty) {
		LoadQty = loadQty;
	}

	public String getGroupCode() {
		return GroupCode;
	}

	public void setGroupCode(String groupCode) {
		GroupCode = groupCode;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBolNo() {
		return bolNo;
	}

	public void setBolNo(String bolNo) {
		this.bolNo = bolNo;
	}
	
}