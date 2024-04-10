package com.bfl.gencode.apex.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CONTAINER_MAPPING",
    "BUSINESS_DATE",
    "ITEM_GROUPS",
    "ITEMS",
    "ITEM_RECLASSIFY",
    "ORDER_NEW",
    "ORDER_MOD",
    "ORDER_CANCELLED",
    "SHIPMENTS",
    "CONTAINER_ID",
    "MANIFEST_UPLOAD",
    "PRICE_CHANGES",
    "PROMOTIONS",
    "CLEARANCES",
    "BRANDS",
    "SUPPLIERS",
    "DIVISION",
    "DEPT",
    "CATEGORY",
    "SUBCATEGORY"  
})

public class BFLIntegrationDataRequest {
	
	@JsonProperty("CONTAINER_MAPPING")
	private String CONTAINER_MAPPING;
	
    @JsonProperty("BUSINESS_DATE")
    private String BUSINESS_DATE;
    
    @JsonProperty("ITEM_GROUPS")
    private String ITEM_GROUPS;
    
    @JsonProperty("ITEMS")
    private String ITEMS;
    
    @JsonProperty("ITEM_RECLASSIFY")
    private String ITEM_RECLASSIFY;
    
    @JsonProperty("ORDER_NEW")
    private String ORDER_NEW;
    
    @JsonProperty("ORDER_MOD")
    private String ORDER_MOD;
    
    @JsonProperty("ORDER_CANCELLED")
    private String ORDER_CANCELLED;
    
    @JsonProperty("SHIPMENTS")
    private String SHIPMENTS;
    
    @JsonProperty("CONTAINER_ID")
    private String CONTAINER_ID;
    
    @JsonProperty("MANIFEST_UPLOAD")
    private String MANIFEST_UPLOAD;
    
    @JsonProperty("PRICE_CHANGES")
    private String PRICE_CHANGES;
    
    @JsonProperty("PROMOTIONS")
    private String PROMOTIONS;
    
    @JsonProperty("CLEARANCES")
    private String CLEARANCES;
    
    @JsonProperty("BRANDS")
    private String BRANDS;
    
    @JsonProperty("SUPPLIERS")
    private String SUPPLIERS;

    @JsonProperty("DIVISION")
    private String DIVISION;
    
    @JsonProperty("DEPT")
    private String DEPT;
    
    @JsonProperty("CATEGORY")
    private String CATEGORY;
    
    @JsonProperty("SUBCATEGORY")
    private String SUBCATEGORY;

    @JsonProperty("CONTAINER_MAPPING")
	public String getCONTAINER_MAPPING() {
		return CONTAINER_MAPPING;
	}

    @JsonProperty("CONTAINER_MAPPING")
	public void setCONTAINER_MAPPING(String cONTAINER_MAPPING) {
		CONTAINER_MAPPING = cONTAINER_MAPPING;
	}

    @JsonProperty("BUSINESS_DATE")
	public String getBUSINESS_DATE() {
		return BUSINESS_DATE;
	}

    @JsonProperty("BUSINESS_DATE")
	public void setBUSINESS_DATE(String bUSINESS_DATE) {
		BUSINESS_DATE = bUSINESS_DATE;
	}

	@JsonProperty("ITEM_GROUPS")
	public String getITEM_GROUPS() {
		return ITEM_GROUPS;
	}

	@JsonProperty("ITEM_GROUPS")
	public void setITEM_GROUPS(String iTEM_GROUPS) {
		ITEM_GROUPS = iTEM_GROUPS;
	}

	@JsonProperty("ITEMS")
	public String getITEMS() {
		return ITEMS;
	}

	@JsonProperty("ITEMS")
	public void setITEMS(String iTEMS) {
		ITEMS = iTEMS;
	}

	@JsonProperty("ITEM_RECLASSIFY")
	public String getITEM_RECLASSIFY() {
		return ITEM_RECLASSIFY;
	}

	public void setITEM_RECLASSIFY(String iTEM_RECLASSIFY) {
		ITEM_RECLASSIFY = iTEM_RECLASSIFY;
	}

	@JsonProperty("ORDER_NEW")
	public String getORDER_NEW() {
		return ORDER_NEW;
	}

	@JsonProperty("ORDER_NEW")
	public void setORDER_NEW(String oRDER_NEW) {
		ORDER_NEW = oRDER_NEW;
	}

	@JsonProperty("ORDER_MOD")
	public String getORDER_MOD() {
		return ORDER_MOD;
	}

	@JsonProperty("ORDER_MOD")
	public void setORDER_MOD(String oRDER_MOD) {
		ORDER_MOD = oRDER_MOD;
	}

	@JsonProperty("ORDER_CANCELLED")
	public String getORDER_CANCELLED() {
		return ORDER_CANCELLED;
	}

	@JsonProperty("ORDER_CANCELLED")
	public void setORDER_CANCELLED(String oRDER_CANCELLED) {
		ORDER_CANCELLED = oRDER_CANCELLED;
	}

	@JsonProperty("SHIPMENTS")
	public String getSHIPMENTS() {
		return SHIPMENTS;
	}

	@JsonProperty("SHIPMENTS")
	public void setSHIPMENTS(String sHIPMENTS) {
		SHIPMENTS = sHIPMENTS;
	}

	@JsonProperty("CONTAINER_ID")
	public String getCONTAINER_ID() {
		return CONTAINER_ID;
	}

	@JsonProperty("CONTAINER_ID")
	public void setCONTAINER_ID(String cONTAINER_ID) {
		CONTAINER_ID = cONTAINER_ID;
	}

	@JsonProperty("MANIFEST_UPLOAD")
	public String getMANIFEST_UPLOAD() {
		return MANIFEST_UPLOAD;
	}

	@JsonProperty("MANIFEST_UPLOAD")
	public void setMANIFEST_UPLOAD(String mANIFEST_UPLOAD) {
		MANIFEST_UPLOAD = mANIFEST_UPLOAD;
	}

	@JsonProperty("PRICE_CHANGES")
	public String getPRICE_CHANGES() {
		return PRICE_CHANGES;
	}

	@JsonProperty("PRICE_CHANGES")
	public void setPRICE_CHANGES(String pRICE_CHANGES) {
		PRICE_CHANGES = pRICE_CHANGES;
	}

	@JsonProperty("PROMOTIONS")
	public String getPROMOTIONS() {
		return PROMOTIONS;
	}

	@JsonProperty("PROMOTIONS")
	public void setPROMOTIONS(String pROMOTIONS) {
		PROMOTIONS = pROMOTIONS;
	}

	@JsonProperty("CLEARANCES")
	public String getCLEARANCES() {
		return CLEARANCES;
	}

	@JsonProperty("CLEARANCES")
	public void setCLEARANCES(String cLEARANCES) {
		CLEARANCES = cLEARANCES;
	}

	@JsonProperty("BRANDS")
	public String getBRANDS() {
		return BRANDS;
	}

	@JsonProperty("BRANDS")
	public void setBRANDS(String bRANDS) {
		BRANDS = bRANDS;
	}

	@JsonProperty("SUPPLIERS")
	public String getSUPPLIERS() {
		return SUPPLIERS;
	}

	@JsonProperty("SUPPLIERS")
	public void setSUPPLIERS(String sUPPLIERS) {
		SUPPLIERS = sUPPLIERS;
	}

	@JsonProperty("DIVISION")
	public String getDIVISION() {
		return DIVISION;
	}

	@JsonProperty("DIVISION")
	public void setDIVISION(String dIVISION) {
		DIVISION = dIVISION;
	}

	@JsonProperty("DEPT")
	public String getDEPT() {
		return DEPT;
	}

	@JsonProperty("DEPT")
	public void setDEPT(String dEPT) {
		DEPT = dEPT;
	}

	@JsonProperty("CATEGORY")
	public String getCATEGORY() {
		return CATEGORY;
	}

	@JsonProperty("CATEGORY")
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	
	@JsonProperty("SUBCATEGORY")
	public String getSUBCATEGORY() {
		return SUBCATEGORY;
	}

	@JsonProperty("SUBCATEGORY")
	public void setSUBCATEGORY(String sUBCATEGORY) {
		SUBCATEGORY = sUBCATEGORY;
	}
    
}
