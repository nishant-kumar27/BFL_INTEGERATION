package com.bfl.gencode.merchhier.GIN.Request;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"item",
	"unitQuantity",
	"fromDisposition",
	"unitCost",
	"baseCost",
	"weight",
	"weightUom"
})
public class Item__1 {
	
	@JsonProperty("item")
	private String item;
	@JsonProperty("unitQuantity")
	private Integer unitQuantity;
	@JsonProperty("fromDisposition")
	private String fromDisposition;
	@JsonProperty("unitCost")
	private Double unitCost;
	@JsonProperty("baseCost")
	private Double baseCost;
	@JsonProperty("weight")
	private Integer weight;
	@JsonProperty("weightUom")
	private String weightUom;
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

	@JsonProperty("unitQuantity")
	public Integer getUnitQuantity() {
		return unitQuantity;
	}

	@JsonProperty("unitQuantity")
	public void setUnitQuantity(Integer unitQuantity) {
		this.unitQuantity = unitQuantity;
	}

	@JsonProperty("fromDisposition")
	public String getFromDisposition() {
		return fromDisposition;
	}

	@JsonProperty("fromDisposition")
	public void setFromDisposition(String fromDisposition) {
		this.fromDisposition = fromDisposition;
	}

	@JsonProperty("unitCost")
	public Double getUnitCost() {
		return unitCost;
	}

	@JsonProperty("unitCost")
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	@JsonProperty("baseCost")
	public Double getBaseCost() {
		return baseCost;
	}

	@JsonProperty("baseCost")
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	@JsonProperty("weight")
	public Integer getWeight() {
		return weight;
	}

	@JsonProperty("weight")
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@JsonProperty("weightUom")
	public String getWeightUom() {
		return weightUom;
	}

	@JsonProperty("weightUom")
	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
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
