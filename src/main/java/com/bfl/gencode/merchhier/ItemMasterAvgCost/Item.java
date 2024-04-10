package com.bfl.gencode.merchhier.ItemMasterAvgCost;

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
    "item",
    "location",
    "locationType",
    "averageCost"
})

public class Item implements Serializable {
	
    @JsonProperty("item")
    private String item;
    
    @JsonProperty("location")
    private Integer location;
    
    @JsonProperty("locationType")
    private String locationType;
    
    @JsonProperty("averageCost")
    private Double averageCost;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -4082717471598058564L;
    
    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("location")
    public Integer getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Integer location) {
        this.location = location;
    }

    @JsonProperty("locationType")
    public String getLocationType() {
        return locationType;
    }

    @JsonProperty("locationType")
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    @JsonProperty("averageCost")
    public Double getAverageCost() {
        return averageCost;
    }

    @JsonProperty("averageCost")
    public void setAverageCost(Double averageCost) {
        this.averageCost = averageCost;
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
