package com.bfl.gencode.merchhier.ItemMasterRequest;

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
    "manufacturerCountry",
    "primaryManufacturerCountryInd"
})
public class CountryOfManufacture implements Serializable {
	
    @JsonProperty("manufacturerCountry")
    private String manufacturerCountry;
    @JsonProperty("primaryManufacturerCountryInd")
    private String primaryManufacturerCountryInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -5667584342370599483L;

    @JsonProperty("manufacturerCountry")
    public String getManufacturerCountry() {
        return manufacturerCountry;
    }

    @JsonProperty("manufacturerCountry")
    public void setManufacturerCountry(String manufacturerCountry) {
        this.manufacturerCountry = manufacturerCountry;
    }

    @JsonProperty("primaryManufacturerCountryInd")
    public String getPrimaryManufacturerCountryInd() {
        return primaryManufacturerCountryInd;
    }

    @JsonProperty("primaryManufacturerCountryInd")
    public void setPrimaryManufacturerCountryInd(String primaryManufacturerCountryInd) {
        this.primaryManufacturerCountryInd = primaryManufacturerCountryInd;
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
