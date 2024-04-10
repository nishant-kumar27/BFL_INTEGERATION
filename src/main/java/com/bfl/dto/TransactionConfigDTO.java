package com.bfl.dto;

public class TransactionConfigDTO {

	private String	Store_ID;

	private String	REG_ID;

	private String	TRAN_SEQ;

	private String	BUSINESS_DATE;
	
	private String	BFL_REG_NO;
	
	private String	BFL_INVOICE_NO;
	
	private String	TRAN_TYPE;

	private String	TRN_DATETIME;
	
	private String	CREATE_DATETIME;

	private String	Exported;
	
	private String	RMS_INVOICENO;
	
	private String  BFL_STORE_ID;
	
	private String  ERROR;
	
	private String STORE_CLOSE_DATE;
	
	public String getBFL_STORE_ID() {
		return BFL_STORE_ID;
	}

	public void setBFL_STORE_ID(String bFL_STORE_ID) {
		BFL_STORE_ID = bFL_STORE_ID;
	}

	public String getExported() {
		return Exported;
	}

	public void setExported(String exported) {
		Exported = exported;
	}

	public String getRMS_INVOICENO() {
		return RMS_INVOICENO;
	}

	public void setRMS_INVOICENO(String rMS_INVOICENO) {
		RMS_INVOICENO = rMS_INVOICENO;
	}

	public String getStore_ID() {
		return Store_ID;
	}

	public void setStore_ID(String store_ID) {
		Store_ID = store_ID;
	}

	public String getREG_ID() {
		return REG_ID;
	}

	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}

	public String getTRAN_SEQ() {
		return TRAN_SEQ;
	}

	public void setTRAN_SEQ(String tRAN_SEQ) {
		TRAN_SEQ = tRAN_SEQ;
	}

	public String getBUSINESS_DATE() {
		return BUSINESS_DATE;
	}

	public void setBUSINESS_DATE(String bUSINESS_DATE) {
		BUSINESS_DATE = bUSINESS_DATE;
	}

	public String getBFL_REG_NO() {
		return BFL_REG_NO;
	}

	public void setBFL_REG_NO(String bFL_REG_NO) {
		BFL_REG_NO = bFL_REG_NO;
	}

	public String getBFL_INVOICE_NO() {
		return BFL_INVOICE_NO;
	}

	public void setBFL_INVOICE_NO(String bFL_INVOICE_NO) {
		BFL_INVOICE_NO = bFL_INVOICE_NO;
	}

	public String getTRAN_TYPE() {
		return TRAN_TYPE;
	}

	public void setTRAN_TYPE(String tRAN_TYPE) {
		TRAN_TYPE = tRAN_TYPE;
	}

	public String getTRN_DATETIME() {
		return TRN_DATETIME;
	}

	public void setTRN_DATETIME(String tRN_DATETIME) {
		TRN_DATETIME = tRN_DATETIME;
	}

	public String getCREATE_DATETIME() {
		return CREATE_DATETIME;
	}

	public void setCREATE_DATETIME(String cREATE_DATETIME) {
		CREATE_DATETIME = cREATE_DATETIME;
	}

	public String getERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
	}

	public String getSTORE_CLOSE_DATE() {
		return STORE_CLOSE_DATE;
	}

	public void setSTORE_CLOSE_DATE(String sTORE_CLOSE_DATE) {
		STORE_CLOSE_DATE = sTORE_CLOSE_DATE;
	}
	
}