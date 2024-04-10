package com.bfl.dto;

import java.util.Date;

public class ItemMasterConfigDTO {

	private String itemCode;
	
	private Date CREATE_DATETIME;

	private String	Exported;
	
	private String  ERROR;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Date getCREATE_DATETIME() {
		return CREATE_DATETIME;
	}

	public void setCREATE_DATETIME(Date cREATE_DATETIME) {
		CREATE_DATETIME = cREATE_DATETIME;
	}

	public String getExported() {
		return Exported;
	}

	public void setExported(String exported) {
		Exported = exported;
	}

	public String getERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
	}
	
}