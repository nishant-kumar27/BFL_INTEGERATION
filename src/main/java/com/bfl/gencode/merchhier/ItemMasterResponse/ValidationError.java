package com.bfl.gencode.merchhier.ItemMasterResponse;

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
    "error",
    "field",
    "inputValue"
})
public class ValidationError implements Serializable {

    @JsonProperty("error")
    private String error;
    
    @JsonProperty("field")
    private String field;
    
    @JsonProperty("inputValue")
    private String inputValue;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = 5705569553233646765L;
    
    @JsonProperty("error")
    public String getError() {
        return error;
    }
    
    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }
    
    @JsonProperty("field")
    public String getField() {
        return field;
    }
    
    @JsonProperty("field")
    public void setField(String field) {
        this.field = field;
    }
    
    @JsonProperty("inputValue")
    public String getInputValue() {
        return inputValue;
    }
    
    @JsonProperty("inputValue")
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
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
