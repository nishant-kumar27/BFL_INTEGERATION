package com.bfl.gencode.merchhier.CreateBrandMaster;

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
    "collectionSize",
    "items"
})

public class BrandMasterRequest implements Serializable {
	
    @JsonProperty("collectionSize")
    private Integer collectionSize;
    
    @JsonProperty("items")
    private List<Item> items;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -8720703043720743746L;

    @JsonProperty("collectionSize")
    public Integer getCollectionSize() {
        return collectionSize;
    }

    @JsonProperty("collectionSize")
    public void setCollectionSize(Integer collectionSize) {
        this.collectionSize = collectionSize;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
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
