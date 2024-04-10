package com.bfl.dto;

public class ApexDataExportDTO {

	private String invoiceNo;
	private String table_Name;
	private String tran_Type;
	private String trnDateTime;
	private String exported;
	private String error;
	private String createDateTime;
	
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getTable_Name() {
		return table_Name;
	}
	public void setTable_Name(String table_Name) {
		this.table_Name = table_Name;
	}
	public String getTran_Type() {
		return tran_Type;
	}
	public void setTran_Type(String tran_Type) {
		this.tran_Type = tran_Type;
	}
	public String getTrnDateTime() {
		return trnDateTime;
	}
	public void setTrnDateTime(String trnDateTime) {
		this.trnDateTime = trnDateTime;
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
	
}
