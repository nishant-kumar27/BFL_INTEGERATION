package com.bfl.dto;

public class CreditNoteHistoryDTO {
	
	private String SReturnNo;
	
	private String invoiceNo;
	
	private String crNoteNo;
	
	private String totalAmt;
	
	private String usedAmt;
	
	private String walletRef;
	
	private String walletAmt;
	
	private String reIssdAmt;
	
	private String newCrNoteNo;
	
	private String returnDate;
	
	public String getSReturnNo() {
		return SReturnNo;
	}

	public void setSReturnNo(String sReturnNo) {
		SReturnNo = sReturnNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCrNoteNo() {
		return crNoteNo;
	}

	public void setCrNoteNo(String crNoteNo) {
		this.crNoteNo = crNoteNo;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getUsedAmt() {
		return usedAmt;
	}

	public void setUsedAmt(String usedAmt) {
		this.usedAmt = usedAmt;
	}

	public String getWalletRef() {
		return walletRef;
	}

	public void setWalletRef(String walletRef) {
		this.walletRef = walletRef;
	}

	public String getWalletAmt() {
		return walletAmt;
	}

	public void setWalletAmt(String walletAmt) {
		this.walletAmt = walletAmt;
	}

	public String getReIssdAmt() {
		return reIssdAmt;
	}

	public void setReIssdAmt(String reIssdAmt) {
		this.reIssdAmt = reIssdAmt;
	}

	public String getNewCrNoteNo() {
		return newCrNoteNo;
	}

	public void setNewCrNoteNo(String newCrNoteNo) {
		this.newCrNoteNo = newCrNoteNo;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
}
