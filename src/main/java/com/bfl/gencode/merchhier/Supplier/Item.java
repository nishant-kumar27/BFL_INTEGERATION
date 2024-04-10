package com.bfl.gencode.merchhier.Supplier;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "supplier",
    "sup_name",
    "country_id"
})

public class Item implements Serializable {
	
    @JsonProperty("supplier")
    private Integer supplier;
    
    @JsonProperty("sup_name")
    private String supName;
    
    @JsonProperty("country_id")
    private String countryId;
    
    private final static long serialVersionUID = -1461555295604396451L;

    @JsonProperty("supplier")
    public Integer getSupplier() {
        return supplier;
    }

    @JsonProperty("supplier")
    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    @JsonProperty("sup_name")
    public String getSupName() {
        return supName;
    }

    @JsonProperty("sup_name")
    public void setSupName(String supName) {
        this.supName = supName;
    }

    @JsonProperty("country_id")
    public String getCountryId() {
        return countryId;
    }

    @JsonProperty("country_id")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
    
}
