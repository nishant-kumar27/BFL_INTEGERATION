package com.bfl.gencode.merchhier.container.qty;

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
	"qty_expected",
	"qty_ordered"
})
public class Item {

	@JsonProperty("item")
	private String item;

	@JsonProperty("qty_expected")
	private Integer qtyExpected;

	@JsonProperty("qty_ordered")
	private Integer qtyOrdered;

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

	@JsonProperty("qty_expected")
	public Integer getQtyExpected() {
		return qtyExpected;
	}

	@JsonProperty("qty_expected")
	public void setQtyExpected(Integer qtyExpected) {
		this.qtyExpected = qtyExpected;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("qty_ordered")
	public Integer getQtyOrdered() {
		return qtyOrdered;
	}

	@JsonProperty("qty_ordered")
	public void setQtyOrdered(Integer qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
