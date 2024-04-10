
package com.bfl.gencode.merchhier.ItemMasterRequest;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
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
    "hierarchyId",
    "unitCost",
    "negotiatedItemCost",
    "pickupLeadTime",
    "roundLevel",
    "roundToCasePercentage",
    "roundToLayerPercentage",
    "roundToPalletPercentage",
    "roundToInnerPercentage",
    "supplierHierarchyLevel1",
    "supplierHierarchyLevel2",
    "supplierHierarchyLevel3",
    "costUom",
    "purchaseType",
    "calculationBasis",
    "purchaseRate",
    "customFlexAttribute"
})
@Generated("jsonschema2pojo")
public class Location implements Serializable
{

    @JsonProperty("hierarchyId")
    private Integer hierarchyId;
    @JsonProperty("unitCost")
    private Double unitCost;
    @JsonProperty("negotiatedItemCost")
    private Double negotiatedItemCost;
    @JsonProperty("pickupLeadTime")
    private Integer pickupLeadTime;
    @JsonProperty("roundLevel")
    private String roundLevel;
    @JsonProperty("roundToCasePercentage")
    private Integer roundToCasePercentage;
    @JsonProperty("roundToLayerPercentage")
    private Integer roundToLayerPercentage;
    @JsonProperty("roundToPalletPercentage")
    private Integer roundToPalletPercentage;
    @JsonProperty("roundToInnerPercentage")
    private Integer roundToInnerPercentage;
    @JsonProperty("supplierHierarchyLevel1")
    private String supplierHierarchyLevel1;
    @JsonProperty("supplierHierarchyLevel2")
    private String supplierHierarchyLevel2;
    @JsonProperty("supplierHierarchyLevel3")
    private String supplierHierarchyLevel3;
    @JsonProperty("costUom")
    private String costUom;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("calculationBasis")
    private String calculationBasis;
    @JsonProperty("purchaseRate")
    private Integer purchaseRate;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute__3> customFlexAttribute;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -8617165937550564989L;

    @JsonProperty("hierarchyId")
    public Integer getHierarchyId() {
        return hierarchyId;
    }

    @JsonProperty("hierarchyId")
    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    @JsonProperty("unitCost")
    public Double getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unitCost")
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    @JsonProperty("negotiatedItemCost")
    public Double getNegotiatedItemCost() {
        return negotiatedItemCost;
    }

    @JsonProperty("negotiatedItemCost")
    public void setNegotiatedItemCost(Double negotiatedItemCost) {
        this.negotiatedItemCost = negotiatedItemCost;
    }

    @JsonProperty("pickupLeadTime")
    public Integer getPickupLeadTime() {
        return pickupLeadTime;
    }

    @JsonProperty("pickupLeadTime")
    public void setPickupLeadTime(Integer pickupLeadTime) {
        this.pickupLeadTime = pickupLeadTime;
    }

    @JsonProperty("roundLevel")
    public String getRoundLevel() {
        return roundLevel;
    }

    @JsonProperty("roundLevel")
    public void setRoundLevel(String roundLevel) {
        this.roundLevel = roundLevel;
    }

    @JsonProperty("roundToCasePercentage")
    public Integer getRoundToCasePercentage() {
        return roundToCasePercentage;
    }

    @JsonProperty("roundToCasePercentage")
    public void setRoundToCasePercentage(Integer roundToCasePercentage) {
        this.roundToCasePercentage = roundToCasePercentage;
    }

    @JsonProperty("roundToLayerPercentage")
    public Integer getRoundToLayerPercentage() {
        return roundToLayerPercentage;
    }

    @JsonProperty("roundToLayerPercentage")
    public void setRoundToLayerPercentage(Integer roundToLayerPercentage) {
        this.roundToLayerPercentage = roundToLayerPercentage;
    }

    @JsonProperty("roundToPalletPercentage")
    public Integer getRoundToPalletPercentage() {
        return roundToPalletPercentage;
    }

    @JsonProperty("roundToPalletPercentage")
    public void setRoundToPalletPercentage(Integer roundToPalletPercentage) {
        this.roundToPalletPercentage = roundToPalletPercentage;
    }

    @JsonProperty("roundToInnerPercentage")
    public Integer getRoundToInnerPercentage() {
        return roundToInnerPercentage;
    }

    @JsonProperty("roundToInnerPercentage")
    public void setRoundToInnerPercentage(Integer roundToInnerPercentage) {
        this.roundToInnerPercentage = roundToInnerPercentage;
    }

    @JsonProperty("supplierHierarchyLevel1")
    public String getSupplierHierarchyLevel1() {
        return supplierHierarchyLevel1;
    }

    @JsonProperty("supplierHierarchyLevel1")
    public void setSupplierHierarchyLevel1(String supplierHierarchyLevel1) {
        this.supplierHierarchyLevel1 = supplierHierarchyLevel1;
    }

    @JsonProperty("supplierHierarchyLevel2")
    public String getSupplierHierarchyLevel2() {
        return supplierHierarchyLevel2;
    }

    @JsonProperty("supplierHierarchyLevel2")
    public void setSupplierHierarchyLevel2(String supplierHierarchyLevel2) {
        this.supplierHierarchyLevel2 = supplierHierarchyLevel2;
    }

    @JsonProperty("supplierHierarchyLevel3")
    public String getSupplierHierarchyLevel3() {
        return supplierHierarchyLevel3;
    }

    @JsonProperty("supplierHierarchyLevel3")
    public void setSupplierHierarchyLevel3(String supplierHierarchyLevel3) {
        this.supplierHierarchyLevel3 = supplierHierarchyLevel3;
    }

    @JsonProperty("costUom")
    public String getCostUom() {
        return costUom;
    }

    @JsonProperty("costUom")
    public void setCostUom(String costUom) {
        this.costUom = costUom;
    }

    @JsonProperty("purchaseType")
    public String getPurchaseType() {
        return purchaseType;
    }

    @JsonProperty("purchaseType")
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @JsonProperty("calculationBasis")
    public String getCalculationBasis() {
        return calculationBasis;
    }

    @JsonProperty("calculationBasis")
    public void setCalculationBasis(String calculationBasis) {
        this.calculationBasis = calculationBasis;
    }

    @JsonProperty("purchaseRate")
    public Integer getPurchaseRate() {
        return purchaseRate;
    }

    @JsonProperty("purchaseRate")
    public void setPurchaseRate(Integer purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute__3> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute__3> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
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
