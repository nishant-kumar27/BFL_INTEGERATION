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
	"carton",
	"weight",
	"weightUom",
	"items"
})
public class Carton {
	
	@JsonProperty("carton")
	private String carton;
	
	@JsonProperty("weight")
	private Integer weight;
	
	@JsonProperty("weightUom")
	private String weightUom;
	
	@JsonProperty("items")
	private List<Item__1> items;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("carton")
	public String getCarton() {
		return carton;
	}

	@JsonProperty("carton")
	public void setCarton(String carton) {
		this.carton = carton;
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

	@JsonProperty("items")
	public List<Item__1> getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(List<Item__1> items) {
		this.items = items;
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
