package com.bfl.dto;

public class TransferConfigDTO {
	
	private String bflTransferNo;
	
	private String bflTableName;
	
	private String tranType;
	
	private String trnDate;
	
	private String exported;
	
	private String error;
	
	private String createDateTime;
	
	private String rmsTransferNo;

	public String getBflTransferNo() {
		return bflTransferNo;
	}


	public String getBflTableName() {
		return bflTableName;
	}


	public String getTranType() {
		return tranType;
	}


	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}

	public String getExported() {
		return exported;
	}

	public void setExported(String exported) {
		this.exported = exported;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getRmsTransferNo() {
		return rmsTransferNo;
	}


}
