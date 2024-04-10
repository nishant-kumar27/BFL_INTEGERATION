package com.bfl.dto;

public class RTLogConfigDTO {

	private String	RECORD_TYPE;
	private String	BFL_VALUES;
	private String	RESA_VALUES;
	private String	RESA_CODES;
	private String BFL_CODES;
	
	public String getRECORD_TYPE() {
		return RECORD_TYPE;
	}
	public void setRECORD_TYPE(String rECORD_TYPE) {
		RECORD_TYPE = rECORD_TYPE;
	}
	public String getBFL_VALUES() {
		return BFL_VALUES;
	}
	public void setBFL_VALUES(String bFL_VALUES) {
		BFL_VALUES = bFL_VALUES;
	}
	public String getRESA_VALUES() {
		return RESA_VALUES;
	}
	public void setRESA_VALUES(String rESA_VALUES) {
		RESA_VALUES = rESA_VALUES;
	}
	public String getRESA_CODES() {
		return RESA_CODES;
	}
	public void setRESA_CODES(String rESA_CODES) {
		RESA_CODES = rESA_CODES;
	}
	
	public String getBFL_CODES() {
		return BFL_CODES;
	}

	public void setBFL_CODES(String bFL_CODES) {
		BFL_CODES = bFL_CODES;
	}

	@Override
	public String toString() {
		return "RTLogConfigDTO [RECORD_TYPE=" + RECORD_TYPE + ", BFL_VALUES=" + BFL_VALUES + ", RESA_VALUES="
				+ RESA_VALUES + ", RESA_CODES=" + RESA_CODES + ", BFL_CODES=" + BFL_CODES + "]";
	}
}
