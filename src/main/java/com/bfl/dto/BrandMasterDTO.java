package com.bfl.dto;

public class BrandMasterDTO {
	
	private String brandName;
	
	private String brandDescription;
	
	private String createDatetime; 
	
	private String lastUpdateDatetime;
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	public void setLastUpdateDatetime(String lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
}
