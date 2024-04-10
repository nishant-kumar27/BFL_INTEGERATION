package com.bfl.gencode.merchhier.ItemMaster;

import java.util.HashMap;
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
    "originCountry",
    "primarySupplierInd",
    "primaryCountryInd",
    "unitCost",
    "leadTime",
    "pickupLeadTime",
    "supplierPackSize",
    "innerPackSize",
    "roundLevel",
    "minimumOrderQuantity",
    "maximumOrderQuantity",
    "packingMethod",
    "defaultUop",
    "ti",
    "hi",
    "costUom",
    "toleranceType",
    "maximumTolerance",
    "minimumTolerance",
    "roundToInnerPercentage",
    "roundToCasePercentage",
    "roundToLayerPercentage",
    "roundToPalletPercentage",
    "supplierHierarchyType1",
    "supplierHierarchyLevel1",
    "supplierHierarchyType2",
    "supplierHierarchyLevel2",
    "supplierHierarchyType3",
    "supplierHierarchyLevel3",
    "negotiatedItemCost",
    "extendedBaseCost",
    "inclusiveCost",
    "baseCost",
    "purchaseType",
    "calculationBasis",
    "purchaseRate",
    "createDateTime",
    "updateDateTime",
    "customFlexAttribute",
    "itemSupplierCountryDimension"
})
public class ItemSupplierCountry {

    @JsonProperty("originCountry")
    private String originCountry;
    @JsonProperty("primarySupplierInd")
    private String primarySupplierInd;
    @JsonProperty("primaryCountryInd")
    private String primaryCountryInd;
    @JsonProperty("unitCost")
    private Integer unitCost;
    @JsonProperty("leadTime")
    private Integer leadTime;
    @JsonProperty("pickupLeadTime")
    private Integer pickupLeadTime;
    @JsonProperty("supplierPackSize")
    private Integer supplierPackSize;
    @JsonProperty("innerPackSize")
    private Integer innerPackSize;
    @JsonProperty("roundLevel")
    private String roundLevel;
    @JsonProperty("minimumOrderQuantity")
    private Integer minimumOrderQuantity;
    @JsonProperty("maximumOrderQuantity")
    private Integer maximumOrderQuantity;
    @JsonProperty("packingMethod")
    private String packingMethod;
    @JsonProperty("defaultUop")
    private String defaultUop;
    @JsonProperty("ti")
    private Integer ti;
    @JsonProperty("hi")
    private Integer hi;
    @JsonProperty("costUom")
    private String costUom;
    @JsonProperty("toleranceType")
    private String toleranceType;
    @JsonProperty("maximumTolerance")
    private Integer maximumTolerance;
    @JsonProperty("minimumTolerance")
    private Integer minimumTolerance;
    @JsonProperty("roundToInnerPercentage")
    private Integer roundToInnerPercentage;
    @JsonProperty("roundToCasePercentage")
    private Integer roundToCasePercentage;
    @JsonProperty("roundToLayerPercentage")
    private Integer roundToLayerPercentage;
    @JsonProperty("roundToPalletPercentage")
    private Integer roundToPalletPercentage;
    @JsonProperty("supplierHierarchyType1")
    private String supplierHierarchyType1;
    @JsonProperty("supplierHierarchyLevel1")
    private String supplierHierarchyLevel1;
    @JsonProperty("supplierHierarchyType2")
    private String supplierHierarchyType2;
    @JsonProperty("supplierHierarchyLevel2")
    private String supplierHierarchyLevel2;
    @JsonProperty("supplierHierarchyType3")
    private String supplierHierarchyType3;
    @JsonProperty("supplierHierarchyLevel3")
    private String supplierHierarchyLevel3;
    @JsonProperty("negotiatedItemCost")
    private Integer negotiatedItemCost;
    @JsonProperty("extendedBaseCost")
    private Integer extendedBaseCost;
    @JsonProperty("inclusiveCost")
    private Integer inclusiveCost;
    @JsonProperty("baseCost")
    private Integer baseCost;
    @JsonProperty("purchaseType")
    private Integer purchaseType;
    @JsonProperty("calculationBasis")
    private String calculationBasis;
    @JsonProperty("purchaseRate")
    private Integer purchaseRate;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute__2> customFlexAttribute = null;
    @JsonProperty("itemSupplierCountryDimension")
    private List<ItemSupplierCountryDimension> itemSupplierCountryDimension = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("originCountry")
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("originCountry")
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @JsonProperty("primarySupplierInd")
    public String getPrimarySupplierInd() {
        return primarySupplierInd;
    }

    @JsonProperty("primarySupplierInd")
    public void setPrimarySupplierInd(String primarySupplierInd) {
        this.primarySupplierInd = primarySupplierInd;
    }

    @JsonProperty("primaryCountryInd")
    public String getPrimaryCountryInd() {
        return primaryCountryInd;
    }

    @JsonProperty("primaryCountryInd")
    public void setPrimaryCountryInd(String primaryCountryInd) {
        this.primaryCountryInd = primaryCountryInd;
    }

    @JsonProperty("unitCost")
    public Integer getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unitCost")
    public void setUnitCost(Integer unitCost) {
        this.unitCost = unitCost;
    }

    @JsonProperty("leadTime")
    public Integer getLeadTime() {
        return leadTime;
    }

    @JsonProperty("leadTime")
    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    @JsonProperty("pickupLeadTime")
    public Integer getPickupLeadTime() {
        return pickupLeadTime;
    }

    @JsonProperty("pickupLeadTime")
    public void setPickupLeadTime(Integer pickupLeadTime) {
        this.pickupLeadTime = pickupLeadTime;
    }

    @JsonProperty("supplierPackSize")
    public Integer getSupplierPackSize() {
        return supplierPackSize;
    }

    @JsonProperty("supplierPackSize")
    public void setSupplierPackSize(Integer supplierPackSize) {
        this.supplierPackSize = supplierPackSize;
    }

    @JsonProperty("innerPackSize")
    public Integer getInnerPackSize() {
        return innerPackSize;
    }

    @JsonProperty("innerPackSize")
    public void setInnerPackSize(Integer innerPackSize) {
        this.innerPackSize = innerPackSize;
    }

    @JsonProperty("roundLevel")
    public String getRoundLevel() {
        return roundLevel;
    }

    @JsonProperty("roundLevel")
    public void setRoundLevel(String roundLevel) {
        this.roundLevel = roundLevel;
    }

    @JsonProperty("minimumOrderQuantity")
    public Integer getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    @JsonProperty("minimumOrderQuantity")
    public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    @JsonProperty("maximumOrderQuantity")
    public Integer getMaximumOrderQuantity() {
        return maximumOrderQuantity;
    }

    @JsonProperty("maximumOrderQuantity")
    public void setMaximumOrderQuantity(Integer maximumOrderQuantity) {
        this.maximumOrderQuantity = maximumOrderQuantity;
    }

    @JsonProperty("packingMethod")
    public String getPackingMethod() {
        return packingMethod;
    }

    @JsonProperty("packingMethod")
    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }

    @JsonProperty("defaultUop")
    public String getDefaultUop() {
        return defaultUop;
    }

    @JsonProperty("defaultUop")
    public void setDefaultUop(String defaultUop) {
        this.defaultUop = defaultUop;
    }

    @JsonProperty("ti")
    public Integer getTi() {
        return ti;
    }

    @JsonProperty("ti")
    public void setTi(Integer ti) {
        this.ti = ti;
    }

    @JsonProperty("hi")
    public Integer getHi() {
        return hi;
    }

    @JsonProperty("hi")
    public void setHi(Integer hi) {
        this.hi = hi;
    }

    @JsonProperty("costUom")
    public String getCostUom() {
        return costUom;
    }

    @JsonProperty("costUom")
    public void setCostUom(String costUom) {
        this.costUom = costUom;
    }

    @JsonProperty("toleranceType")
    public String getToleranceType() {
        return toleranceType;
    }

    @JsonProperty("toleranceType")
    public void setToleranceType(String toleranceType) {
        this.toleranceType = toleranceType;
    }

    @JsonProperty("maximumTolerance")
    public Integer getMaximumTolerance() {
        return maximumTolerance;
    }

    @JsonProperty("maximumTolerance")
    public void setMaximumTolerance(Integer maximumTolerance) {
        this.maximumTolerance = maximumTolerance;
    }

    @JsonProperty("minimumTolerance")
    public Integer getMinimumTolerance() {
        return minimumTolerance;
    }

    @JsonProperty("minimumTolerance")
    public void setMinimumTolerance(Integer minimumTolerance) {
        this.minimumTolerance = minimumTolerance;
    }

    @JsonProperty("roundToInnerPercentage")
    public Integer getRoundToInnerPercentage() {
        return roundToInnerPercentage;
    }

    @JsonProperty("roundToInnerPercentage")
    public void setRoundToInnerPercentage(Integer roundToInnerPercentage) {
        this.roundToInnerPercentage = roundToInnerPercentage;
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

    @JsonProperty("supplierHierarchyType1")
    public String getSupplierHierarchyType1() {
        return supplierHierarchyType1;
    }

    @JsonProperty("supplierHierarchyType1")
    public void setSupplierHierarchyType1(String supplierHierarchyType1) {
        this.supplierHierarchyType1 = supplierHierarchyType1;
    }

    @JsonProperty("supplierHierarchyLevel1")
    public String getSupplierHierarchyLevel1() {
        return supplierHierarchyLevel1;
    }

    @JsonProperty("supplierHierarchyLevel1")
    public void setSupplierHierarchyLevel1(String supplierHierarchyLevel1) {
        this.supplierHierarchyLevel1 = supplierHierarchyLevel1;
    }

    @JsonProperty("supplierHierarchyType2")
    public String getSupplierHierarchyType2() {
        return supplierHierarchyType2;
    }

    @JsonProperty("supplierHierarchyType2")
    public void setSupplierHierarchyType2(String supplierHierarchyType2) {
        this.supplierHierarchyType2 = supplierHierarchyType2;
    }

    @JsonProperty("supplierHierarchyLevel2")
    public String getSupplierHierarchyLevel2() {
        return supplierHierarchyLevel2;
    }

    @JsonProperty("supplierHierarchyLevel2")
    public void setSupplierHierarchyLevel2(String supplierHierarchyLevel2) {
        this.supplierHierarchyLevel2 = supplierHierarchyLevel2;
    }

    @JsonProperty("supplierHierarchyType3")
    public String getSupplierHierarchyType3() {
        return supplierHierarchyType3;
    }

    @JsonProperty("supplierHierarchyType3")
    public void setSupplierHierarchyType3(String supplierHierarchyType3) {
        this.supplierHierarchyType3 = supplierHierarchyType3;
    }

    @JsonProperty("supplierHierarchyLevel3")
    public String getSupplierHierarchyLevel3() {
        return supplierHierarchyLevel3;
    }

    @JsonProperty("supplierHierarchyLevel3")
    public void setSupplierHierarchyLevel3(String supplierHierarchyLevel3) {
        this.supplierHierarchyLevel3 = supplierHierarchyLevel3;
    }

    @JsonProperty("negotiatedItemCost")
    public Integer getNegotiatedItemCost() {
        return negotiatedItemCost;
    }

    @JsonProperty("negotiatedItemCost")
    public void setNegotiatedItemCost(Integer negotiatedItemCost) {
        this.negotiatedItemCost = negotiatedItemCost;
    }

    @JsonProperty("extendedBaseCost")
    public Integer getExtendedBaseCost() {
        return extendedBaseCost;
    }

    @JsonProperty("extendedBaseCost")
    public void setExtendedBaseCost(Integer extendedBaseCost) {
        this.extendedBaseCost = extendedBaseCost;
    }

    @JsonProperty("inclusiveCost")
    public Integer getInclusiveCost() {
        return inclusiveCost;
    }

    @JsonProperty("inclusiveCost")
    public void setInclusiveCost(Integer inclusiveCost) {
        this.inclusiveCost = inclusiveCost;
    }

    @JsonProperty("baseCost")
    public Integer getBaseCost() {
        return baseCost;
    }

    @JsonProperty("baseCost")
    public void setBaseCost(Integer baseCost) {
        this.baseCost = baseCost;
    }

    @JsonProperty("purchaseType")
    public Integer getPurchaseType() {
        return purchaseType;
    }

    @JsonProperty("purchaseType")
    public void setPurchaseType(Integer purchaseType) {
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

    @JsonProperty("createDateTime")
    public String getCreateDateTime() {
        return createDateTime;
    }

    @JsonProperty("createDateTime")
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    @JsonProperty("updateDateTime")
    public String getUpdateDateTime() {
        return updateDateTime;
    }

    @JsonProperty("updateDateTime")
    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute__2> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute__2> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("itemSupplierCountryDimension")
    public List<ItemSupplierCountryDimension> getItemSupplierCountryDimension() {
        return itemSupplierCountryDimension;
    }

    @JsonProperty("itemSupplierCountryDimension")
    public void setItemSupplierCountryDimension(List<ItemSupplierCountryDimension> itemSupplierCountryDimension) {
        this.itemSupplierCountryDimension = itemSupplierCountryDimension;
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
