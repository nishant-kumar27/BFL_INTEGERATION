package com.bfl.gencode.merchhier.ItemMasterLocRequest;

import java.io.Serializable;
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
    "item",
    "hierarchyLevel",
    "locations"
})

public class Item implements Serializable {
	
    @JsonProperty("item")
    private String item;
    
    @JsonProperty("hierarchyLevel")
    private String hierarchyLevel;
    
    @JsonProperty("locations")
    private List<Location> locations;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -4827649782919466040L;
    
    @JsonProperty("item")
    public String getItem() {
        return item;
    }
    
    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }
    
    @JsonProperty("hierarchyLevel")
    public String getHierarchyLevel() {
        return hierarchyLevel;
    }
    
    @JsonProperty("hierarchyLevel")
    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }
    
    @JsonProperty("locations")
    public List<Location> getLocations() {
        return locations;
    }
    
    @JsonProperty("locations")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
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