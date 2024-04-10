package com.bfl.gencode.resa.otherShopReturnResponse;

import java.io.Serializable;
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
	"tranno",
	"register",
	"businessdate",
	"store"
})

public class Item implements Serializable {

	@JsonProperty("tranno")
	private String tranno;

	@JsonProperty("register")
	private String register;

	@JsonProperty("store")
	private String store;

	@JsonProperty("businessdate")
	private String businessdate;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	private final static long serialVersionUID = 4226782945214069638L;

	@JsonProperty("tranno")
	public String getTranno() {
		return tranno;
	}

	@JsonProperty("tranno")
	public void setTranno(String tranno) {
		this.tranno = tranno;
	}

	@JsonProperty("register")
	public String getRegister() {
		return register;
	}

	@JsonProperty("register")
	public void setRegister(String register) {
		this.register = register;
	}

	@JsonProperty("store")
	public String getStore() {
		return store;
	}

	@JsonProperty("store")
	public void setStore(String store) {
		this.store = store;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("businessdate")
	public String getBusinessdate() {
		return businessdate;
	}

	@JsonProperty("businessdate")
	public void setBusinessdate(String businessdate) {
		this.businessdate = businessdate;
	}
}
