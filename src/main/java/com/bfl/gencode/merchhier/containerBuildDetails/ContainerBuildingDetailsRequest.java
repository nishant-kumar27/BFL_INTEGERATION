package com.bfl.gencode.merchhier.containerBuildDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CONTAINER_ID",
    "OPEN_DATE", 
    "CONTAINER_BUILD_DATE", 
    "CONTAINER_BUILD_TIME", 
    "USER_ID", 
    "CONTAINER_DESC", 
    "WHOUSE", 
    "OPEN_REASON"
})

public class ContainerBuildingDetailsRequest {
	
    @JsonProperty("CONTAINER_ID")
    private String containerId;
    
    @JsonProperty("OPEN_DATE")
    private String openDate;
    
    @JsonProperty("CONTAINER_BUILD_DATE")
    private String containerBuildDate;
    
    @JsonProperty("CONTAINER_BUILD_TIME")
    private String containerBuildTime;
    
    @JsonProperty("USER_ID")
    private String userId;
    
    @JsonProperty("CONTAINER_DESC")
    private String containerDesc;
    
    @JsonProperty("WHOUSE")
    private String whouse;
    
    @JsonProperty("OPEN_REASON")
    private String openReason;

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getContainerBuildDate() {
		return containerBuildDate;
	}

	public void setContainerBuildDate(String containerBuildDate) {
		this.containerBuildDate = containerBuildDate;
	}

	public String getContainerBuildTime() {
		return containerBuildTime;
	}

	public void setContainerBuildTime(String containerBuildTime) {
		this.containerBuildTime = containerBuildTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContainerDesc() {
		return containerDesc;
	}

	public void setContainerDesc(String containerDesc) {
		this.containerDesc = containerDesc;
	}

	public String getWhouse() {
		return whouse;
	}

	public void setWhouse(String whouse) {
		this.whouse = whouse;
	}

	public String getOpenReason() {
		return openReason;
	}

	public void setOpenReason(String openReason) {
		this.openReason = openReason;
	}
    
}
