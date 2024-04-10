
package com.bfl.gencode.store.header.request;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"item",
	"transferQuantity",
	"supplierPackSize",
	"invStatus",
	"adjustmentType",
	"adjustmentValue"
})
@Generated("jsonschema2pojo")
public class Detail {

	@JsonProperty("item")
	private String item;
	
	@JsonProperty("transferQuantity")
	private Integer transferQuantity;
	
	@JsonProperty("supplierPackSize")
	private Integer supplierPackSize;
	
	@JsonProperty("invStatus")
	private Integer invStatus;
	
	@JsonProperty("adjustmentType")
	private String adjustmentType;
	
	@JsonProperty("adjustmentValue")
	private Object adjustmentValue;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("item")
	public String getItem() {
		return item;
	}

	@JsonProperty("item")
	public void setItem(String item) {
		this.item = item;
	}

	@JsonProperty("transferQuantity")
	public Integer getTransferQuantity() {
		return transferQuantity;
	}

	@JsonProperty("transferQuantity")
	public void setTransferQuantity(Integer transferQuantity) {
		this.transferQuantity = transferQuantity;
	}

	@JsonProperty("supplierPackSize")
	public Integer getSupplierPackSize() {
		return supplierPackSize;
	}

	@JsonProperty("supplierPackSize")
	public void setSupplierPackSize(Integer supplierPackSize) {
		this.supplierPackSize = supplierPackSize;
	}

	@JsonProperty("invStatus")
	public Integer getInvStatus() {
		return invStatus;
	}

	@JsonProperty("invStatus")
	public void setInvStatus(Integer invStatus) {
		this.invStatus = invStatus;
	}

	@JsonProperty("adjustmentType")
	public String getAdjustmentType() {
		return adjustmentType;
	}

	@JsonProperty("adjustmentType")
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	@JsonProperty("adjustmentValue")
	public Object getAdjustmentValue() {
		return adjustmentValue;
	}

	@JsonProperty("adjustmentValue")
	public void setAdjustmentValue(Object adjustmentValue) {
		this.adjustmentValue = adjustmentValue;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
