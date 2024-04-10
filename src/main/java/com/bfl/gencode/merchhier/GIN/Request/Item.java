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
	"toLocation",
	"fromLocation",
	"cartonQuantity",
	"asnNo",
	"bolNo",
	"shipDate",
	"estimatedArrivalDate",
	"comments",
	"carrierCode",
	"shipmentDetails"
})
public class Item {
	
	@JsonProperty("toLocation")
	private Long toLocation;
	
	@JsonProperty("fromLocation")
	private Long fromLocation;
	
	@JsonProperty("cartonQuantity")
	private Integer cartonQuantity;
	
	@JsonProperty("asnNo")
	private String asnNo;
	
	@JsonProperty("bolNo")
	private String bolNo;
	
	@JsonProperty("shipDate")
	private String shipDate;
	
	@JsonProperty("estimatedArrivalDate")
	private String estimatedArrivalDate;
	
	@JsonProperty("comments")
	private String comments;
	
	@JsonProperty("carrierCode")
	private String carrierCode;
	
	@JsonProperty("shipmentDetails")
	private List<ShipmentDetail> shipmentDetails;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	
	@JsonProperty("toLocation")
	public Long getToLocation() {
		return toLocation;
	}

	@JsonProperty("toLocation")
	public void setToLocation(Long toLocation) {
		this.toLocation = toLocation;
	}

	@JsonProperty("fromLocation")
	public Long getFromLocation() {
		return fromLocation;
	}

	@JsonProperty("fromLocation")
	public void setFromLocation(Long fromLocation) {
		this.fromLocation = fromLocation;
	}

	@JsonProperty("cartonQuantity")
	public Integer getCartonQuantity() {
		return cartonQuantity;
	}

	@JsonProperty("cartonQuantity")
	public void setCartonQuantity(Integer cartonQuantity) {
		this.cartonQuantity = cartonQuantity;
	}

	@JsonProperty("asnNo")
	public String getAsnNo() {
		return asnNo;
	}

	@JsonProperty("asnNo")
	public void setAsnNo(String asnNo) {
		this.asnNo = asnNo;
	}

	@JsonProperty("bolNo")
	public String getBolNo() {
		return bolNo;
	}

	@JsonProperty("bolNo")
	public void setBolNo(String bolNo) {
		this.bolNo = bolNo;
	}

	@JsonProperty("shipDate")
	public String getShipDate() {
		return shipDate;
	}

	@JsonProperty("shipDate")
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	@JsonProperty("estimatedArrivalDate")
	public String getEstimatedArrivalDate() {
		return estimatedArrivalDate;
	}

	@JsonProperty("estimatedArrivalDate")
	public void setEstimatedArrivalDate(String estimatedArrivalDate) {
		this.estimatedArrivalDate = estimatedArrivalDate;
	}

	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}

	@JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonProperty("carrierCode")
	public String getCarrierCode() {
		return carrierCode;
	}

	@JsonProperty("carrierCode")
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	@JsonProperty("shipmentDetails")
	public List<ShipmentDetail> getShipmentDetails() {
		return shipmentDetails;
	}

	@JsonProperty("shipmentDetails")
	public void setShipmentDetails(List<ShipmentDetail> shipmentDetails) {
		this.shipmentDetails = shipmentDetails;
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
