package com.bfl.gencode.merchhier.ItemMaster;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "componentItem",
    "packQuantity"
})
public class ItemBOM {

    @JsonProperty("componentItem")
    private String componentItem;
    @JsonProperty("packQuantity")
    private Integer packQuantity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("componentItem")
    public String getComponentItem() {
        return componentItem;
    }

    @JsonProperty("componentItem")
    public void setComponentItem(String componentItem) {
        this.componentItem = componentItem;
    }

    @JsonProperty("packQuantity")
    public Integer getPackQuantity() {
        return packQuantity;
    }

    @JsonProperty("packQuantity")
    public void setPackQuantity(Integer packQuantity) {
        this.packQuantity = packQuantity;
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
