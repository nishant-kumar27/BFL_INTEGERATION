
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
    "computationValueBase",
    "componentRate",
    "perCount",
    "perCountUom",
    "estimatedAssessmentValue",
    "nominationFlag1",
    "inDuty",
    "nominationFlag3",
    "inExpense",
    "inAlc"
})
@Generated("jsonschema2pojo")
public class Assessment implements Serializable
{

    @JsonProperty("componentId")
    private String componentId;
    @JsonProperty("computationValueBase")
    private String computationValueBase;
    @JsonProperty("componentRate")
    private Double componentRate;
    @JsonProperty("perCount")
    private Integer perCount;
    @JsonProperty("perCountUom")
    private String perCountUom;
    @JsonProperty("estimatedAssessmentValue")
    private Integer estimatedAssessmentValue;
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
    private final static long serialVersionUID = 6093690231632973682L;

    @JsonProperty("componentId")
    public String getComponentId() {
        return componentId;
    }

    @JsonProperty("componentId")
    public void setComponentId(String componentId) {
        this.componentId = componentId;
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

    @JsonProperty("estimatedAssessmentValue")
    public Integer getEstimatedAssessmentValue() {
        return estimatedAssessmentValue;
    }

    @JsonProperty("estimatedAssessmentValue")
    public void setEstimatedAssessmentValue(Integer estimatedAssessmentValue) {
        this.estimatedAssessmentValue = estimatedAssessmentValue;
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
