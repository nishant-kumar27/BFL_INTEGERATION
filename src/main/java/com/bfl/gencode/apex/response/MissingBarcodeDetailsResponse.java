package com.bfl.gencode.apex.response;

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
	"code",
	"title",
	"message",
	"o:errorCode",
	"cause",
	"action",
	"type",
	"instance"
})
public class MissingBarcodeDetailsResponse {

	@JsonProperty("code")
	private String code;
	@JsonProperty("title")
	private String title;
	@JsonProperty("message")
	private String message;
	@JsonProperty("o:errorCode")
	private String oErrorCode;
	@JsonProperty("cause")
	private String cause;
	@JsonProperty("action")
	private String action;
	@JsonProperty("type")
	private String type;
	@JsonProperty("instance")
	private String instance;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("o:errorCode")
	public String getoErrorCode() {
		return oErrorCode;
	}

	@JsonProperty("o:errorCode")
	public void setoErrorCode(String oErrorCode) {
		this.oErrorCode = oErrorCode;
	}

	@JsonProperty("cause")
	public String getCause() {
		return cause;
	}

	@JsonProperty("cause")
	public void setCause(String cause) {
		this.cause = cause;
	}

	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("instance")
	public String getInstance() {
		return instance;
	}

	@JsonProperty("instance")
	public void setInstance(String instance) {
		this.instance = instance;
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
