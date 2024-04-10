package com.bfl.gencode.merchhier.transfers;

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
    "componentPrice",
    "componentSellingUom"
})
public class TicketDetail {

    @JsonProperty("componentItem")
    private String componentItem;
    @JsonProperty("componentPrice")
    private Integer componentPrice;
    @JsonProperty("componentSellingUom")
    private String componentSellingUom;
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

    @JsonProperty("componentPrice")
    public Integer getComponentPrice() {
        return componentPrice;
    }

    @JsonProperty("componentPrice")
    public void setComponentPrice(Integer componentPrice) {
        this.componentPrice = componentPrice;
    }

    @JsonProperty("componentSellingUom")
    public String getComponentSellingUom() {
        return componentSellingUom;
    }

    @JsonProperty("componentSellingUom")
    public void setComponentSellingUom(String componentSellingUom) {
        this.componentSellingUom = componentSellingUom;
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
