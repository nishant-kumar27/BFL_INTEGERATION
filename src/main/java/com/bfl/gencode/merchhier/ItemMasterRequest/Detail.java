
package com.bfl.gencode.merchhier.ItemMasterRequest;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "componentId",
    "componentRate",
    "perCount",
    "perCountUom",
    "upChargeGroup",
    "componentCurrency",
    "transferAllocationDefaultInd",
    "computationValueBase",
    "costBasis",
    "includeInTotalUpchargeInd"
})
@Generated("jsonschema2pojo")
public class Detail implements Serializable
{

    @JsonProperty("componentId")
    private String componentId;
    @JsonProperty("componentRate")
    private Double componentRate;
    @JsonProperty("perCount")
    private Integer perCount;
    @JsonProperty("perCountUom")
    private String perCountUom;
    @JsonProperty("upChargeGroup")
    private String upChargeGroup;
    @JsonProperty("componentCurrency")
    private String componentCurrency;
    @JsonProperty("transferAllocationDefaultInd")
    private String transferAllocationDefaultInd;
    @JsonProperty("computationValueBase")
    private String computationValueBase;
    @JsonProperty("costBasis")
    private String costBasis;
    @JsonProperty("includeInTotalUpchargeInd")
    private String includeInTotalUpchargeInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 6539373462874813110L;

    @JsonProperty("componentId")
    public String getComponentId() {
        return componentId;
    }

    @JsonProperty("componentId")
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @JsonProperty("componentRate")
    public Double getComponentRate() {
        return componentRate;
    }

    @JsonProperty("componentRate")
    public void setComponentRate(Double componentRate) {
        this.componentRate = componentRate;
    }

    @JsonProperty("perCount")
    public Integer getPerCount() {
        return perCount;
    }

    @JsonProperty("perCount")
    public void setPerCount(Integer perCount) {
        this.perCount = perCount;
    }

    @JsonProperty("perCountUom")
    public String getPerCountUom() {
        return perCountUom;
    }

    @JsonProperty("perCountUom")
    public void setPerCountUom(String perCountUom) {
        this.perCountUom = perCountUom;
    }

    @JsonProperty("upChargeGroup")
    public String getUpChargeGroup() {
        return upChargeGroup;
    }

    @JsonProperty("upChargeGroup")
    public void setUpChargeGroup(String upChargeGroup) {
        this.upChargeGroup = upChargeGroup;
    }

    @JsonProperty("componentCurrency")
    public String getComponentCurrency() {
        return componentCurrency;
    }

    @JsonProperty("componentCurrency")
    public void setComponentCurrency(String componentCurrency) {
        this.componentCurrency = componentCurrency;
    }

    @JsonProperty("transferAllocationDefaultInd")
    public String getTransferAllocationDefaultInd() {
        return transferAllocationDefaultInd;
    }

    @JsonProperty("transferAllocationDefaultInd")
    public void setTransferAllocationDefaultInd(String transferAllocationDefaultInd) {
        this.transferAllocationDefaultInd = transferAllocationDefaultInd;
    }

    @JsonProperty("computationValueBase")
    public String getComputationValueBase() {
        return computationValueBase;
    }

    @JsonProperty("computationValueBase")
    public void setComputationValueBase(String computationValueBase) {
        this.computationValueBase = computationValueBase;
    }

    @JsonProperty("costBasis")
    public String getCostBasis() {
        return costBasis;
    }

    @JsonProperty("costBasis")
    public void setCostBasis(String costBasis) {
        this.costBasis = costBasis;
    }

    @JsonProperty("includeInTotalUpchargeInd")
    public String getIncludeInTotalUpchargeInd() {
        return includeInTotalUpchargeInd;
    }

    @JsonProperty("includeInTotalUpchargeInd")
    public void setIncludeInTotalUpchargeInd(String includeInTotalUpchargeInd) {
        this.includeInTotalUpchargeInd = includeInTotalUpchargeInd;
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
