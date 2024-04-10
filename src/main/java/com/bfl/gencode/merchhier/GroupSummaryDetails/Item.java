package com.bfl.gencode.merchhier.GroupSummaryDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"order_no",
	"group_code",
	"group_name",
	"qty",
	"unit_cost",
	"value",
	"vat_value", 
	"bolNo",
	"loc_name",
	"location"
})
public class Item {

	@JsonProperty("order_no")
	private String orderNo;

	@JsonProperty("group_code")
	private String groupCode;

	@JsonProperty("group_name")
	private String groupName;

	@JsonProperty("qty")
	private String qty;

	@JsonProperty("unit_cost")
	private String unitCost;

	@JsonProperty("value")
	private String value;

	@JsonProperty("vat_value")
	private String vatValue;

	@JsonProperty("discount")
	private String discount;
	
	@JsonProperty("bolno")
	private String bolNo;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("loc_name")
	private String locName;

	@JsonProperty("order_no")
	public String getOrderNo() {
		return orderNo;
	}

	@JsonProperty("order_no")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@JsonProperty("group_code")
	public String getGroupCode() {
		return groupCode;
	}

	@JsonProperty("group_code")
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@JsonProperty("group_name")
	public String getGroupName() {
		return groupName;
	}

	@JsonProperty("group_name")
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@JsonProperty("qty")
	public String getQty() {
		return qty;
	}

	@JsonProperty("qty")
	public void setQty(String qty) {
		this.qty = qty;
	}

	@JsonProperty("unit_cost")
	public String getUnitCost() {
		return unitCost;
	}

	@JsonProperty("unit_cost")
	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("vat_value")
	public String getVatValue() {
		return vatValue;
	}

	@JsonProperty("vat_value")
	public void setVatValue(String vatValue) {
		this.vatValue = vatValue;
	}

	@JsonProperty("discount")
	public String getDiscount() {
		return discount;
	}

	@JsonProperty("discount")
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	@JsonProperty("bolno")
	public String getBolNo() {
		return bolNo;
	}
	
	@JsonProperty("bolno")
	public void setBolNo(String bolNo) {
		this.bolNo = bolNo;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}

	@JsonProperty("loc_name")
	public String getLocName() {
		return locName;
	}
	
	@JsonProperty("loc_name")
	public void setLocName(String locName) {
		this.locName = locName;
	}
	
}
