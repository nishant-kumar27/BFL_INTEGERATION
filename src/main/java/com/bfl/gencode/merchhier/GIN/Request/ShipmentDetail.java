package com.bfl.gencode.merchhier.GIN.Request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"distroNo",
	"distroDocumentType",
	"customerOrderNo",
	"fulfillOrderNo",
	"comments",
	"cartons"
})
public class ShipmentDetail {
	
	@JsonProperty("distroNo")
	private String distroNo;
	
	@JsonProperty("distroDocumentType")
	private String distroDocumentType;
	
	@JsonProperty("customerOrderNo")
	private String customerOrderNo;
	
	@JsonProperty("fulfillOrderNo")
	private String fulfillOrderNo;
	
	@JsonProperty("comments")
	private String comments;
	
	@JsonProperty("cartons")
	private List<Carton> cartons;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("distroNo")
	public String getDistroNo() {
		return distroNo;
	}

	@JsonProperty("distroNo")
	public void setDistroNo(String distroNo) {
		this.distroNo = distroNo;
	}

	@JsonProperty("distroDocumentType")
	public String getDistroDocumentType() {
		return distroDocumentType;
	}

	@JsonProperty("distroDocumentType")
	public void setDistroDocumentType(String distroDocumentType) {
		this.distroDocumentType = distroDocumentType;
	}

	@JsonProperty("customerOrderNo")
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	@JsonProperty("customerOrderNo")
	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	@JsonProperty("fulfillOrderNo")
	public String getFulfillOrderNo() {
		return fulfillOrderNo;
	}

	@JsonProperty("fulfillOrderNo")
	public void setFulfillOrderNo(String fulfillOrderNo) {
		this.fulfillOrderNo = fulfillOrderNo;
	}

	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}

	@JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonProperty("cartons")
	public List<Carton> getCartons() {
		return cartons;
	}

	@JsonProperty("cartons")
	public void setCartons(List<Carton> cartons) {
		this.cartons = cartons;
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
