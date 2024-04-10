
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
    "supplier",
    "componentId",
    "dischargePort",
    "originCountry",
    "ladingPort",
    "costZoneId",
    "costZoneGroupId",
    "baseExpenseInd",
    "computationValueBase",
    "componentRate",
    "perCount",
    "perCountUom",
    "componentCurrency",
    "updateOrdersInd",
    "nominationFlag1",
    "inDuty",
    "nominationFlag3",
    "inExpense",
    "inAlc"
})
@Generated("jsonschema2pojo")
public class Expense implements Serializable
{

    @JsonProperty("supplier")
    private Integer supplier;
    @JsonProperty("componentId")
    private String componentId;
    @JsonProperty("dischargePort")
    private String dischargePort;
    @JsonProperty("originCountry")
    private String originCountry;
    @JsonProperty("ladingPort")
    private String ladingPort;
    @JsonProperty("costZoneId")
    private Integer costZoneId;
    @JsonProperty("costZoneGroupId")
    private Integer costZoneGroupId;
    @JsonProperty("baseExpenseInd")
    private String baseExpenseInd;
    @JsonProperty("computationValueBase")
    private String computationValueBase;
    @JsonProperty("componentRate")
    private Double componentRate;
    @JsonProperty("perCount")
    private Integer perCount;
    @JsonProperty("perCountUom")
    private String perCountUom;
    @JsonProperty("componentCurrency")
    private String componentCurrency;
    @JsonProperty("updateOrdersInd")
    private String updateOrdersInd;
    @JsonProperty("nominationFlag1")
    private String nominationFlag1;
    @JsonProperty("inDuty")
    private String inDuty;
    @JsonProperty("nominationFlag3")
    private String nominationFlag3;
    @JsonProperty("inExpense")
    private String inExpense;
    @JsonProperty("inAlc")
    private String inAlc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -1609696897276648002L;

    @JsonProperty("supplier")
    public Integer getSupplier() {
        return supplier;
    }

    @JsonProperty("supplier")
    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    @JsonProperty("componentId")
    public String getComponentId() {
        return componentId;
    }

    @JsonProperty("componentId")
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @JsonProperty("dischargePort")
    public String getDischargePort() {
        return dischargePort;
    }

    @JsonProperty("dischargePort")
    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    @JsonProperty("originCountry")
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("originCountry")
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @JsonProperty("ladingPort")
    public String getLadingPort() {
        return ladingPort;
    }

    @JsonProperty("ladingPort")
    public void setLadingPort(String ladingPort) {
        this.ladingPort = ladingPort;
    }

    @JsonProperty("costZoneId")
    public Integer getCostZoneId() {
        return costZoneId;
    }

    @JsonProperty("costZoneId")
    public void setCostZoneId(Integer costZoneId) {
        this.costZoneId = costZoneId;
    }

    @JsonProperty("costZoneGroupId")
    public Integer getCostZoneGroupId() {
        return costZoneGroupId;
    }

    @JsonProperty("costZoneGroupId")
    public void setCostZoneGroupId(Integer costZoneGroupId) {
        this.costZoneGroupId = costZoneGroupId;
    }

    @JsonProperty("baseExpenseInd")
    public String getBaseExpenseInd() {
        return baseExpenseInd;
    }

    @JsonProperty("baseExpenseInd")
    public void setBaseExpenseInd(String baseExpenseInd) {
        this.baseExpenseInd = baseExpenseInd;
    }

    @JsonProperty("computationValueBase")
    public String getComputationValueBase() {
        return computationValueBase;
    }

    @JsonProperty("computationValueBase")
    public void setComputationValueBase(String computationValueBase) {
        this.computationValueBase = computationValueBase;
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

    @JsonProperty("componentCurrency")
    public String getComponentCurrency() {
        return componentCurrency;
    }

    @JsonProperty("componentCurrency")
    public void setComponentCurrency(String componentCurrency) {
        this.componentCurrency = componentCurrency;
    }

    @JsonProperty("updateOrdersInd")
    public String getUpdateOrdersInd() {
        return updateOrdersInd;
    }

    @JsonProperty("updateOrdersInd")
    public void setUpdateOrdersInd(String updateOrdersInd) {
        this.updateOrdersInd = updateOrdersInd;
    }

    @JsonProperty("nominationFlag1")
    public String getNominationFlag1() {
        return nominationFlag1;
    }

    @JsonProperty("nominationFlag1")
    public void setNominationFlag1(String nominationFlag1) {
        this.nominationFlag1 = nominationFlag1;
    }

    @JsonProperty("inDuty")
    public String getInDuty() {
        return inDuty;
    }

    @JsonProperty("inDuty")
    public void setInDuty(String inDuty) {
        this.inDuty = inDuty;
    }

    @JsonProperty("nominationFlag3")
    public String getNominationFlag3() {
        return nominationFlag3;
    }

    @JsonProperty("nominationFlag3")
    public void setNominationFlag3(String nominationFlag3) {
        this.nominationFlag3 = nominationFlag3;
    }

    @JsonProperty("inExpense")
    public String getInExpense() {
        return inExpense;
    }

    @JsonProperty("inExpense")
    public void setInExpense(String inExpense) {
        this.inExpense = inExpense;
    }

    @JsonProperty("inAlc")
    public String getInAlc() {
        return inAlc;
    }

    @JsonProperty("inAlc")
    public void setInAlc(String inAlc) {
        this.inAlc = inAlc;
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
