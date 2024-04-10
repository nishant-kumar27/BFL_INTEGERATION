package com.bfl.dto;

public class CloseShopDTO {

	private String closeDate;
	private String closeTime;	
	private String code;
	private String userId;
	private String compName;
	private String trnDateTime;
	private String remarks;
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getTrnDateTime() {
		return trnDateTime;
	}
	public void setTrnDateTime(String trnDateTime) {
		this.trnDateTime = trnDateTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "CloseShopDTO [closeDate=" + closeDate + ", closeTime=" + closeTime + ", code=" + code + ", userId="
				+ userId + ", compName=" + compName + ", trnDateTime=" + trnDateTime + ", remarks=" + remarks + "]";
	}


}
