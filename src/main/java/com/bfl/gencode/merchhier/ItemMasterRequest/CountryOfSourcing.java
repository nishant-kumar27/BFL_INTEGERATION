
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
    "originCountry",
    "primaryCountryInd",
    "unitCost",
    "leadTime",
    "pickupLeadTime",
    "mininumOrderQuantity",
    "maximumOrderQuantity",
    "supplierHierarchyLevel1",
    "supplierHierarchyLevel2",
    "supplierHierarchyLevel3",
    "defaultUop",
    "supplierPackSize",
    "innerPackSize",
    "ti",
    "hi",
    "costUom",
    "toleranceType",
    "minimumTolerance",
    "maximumTolerance",
    "supplierHierarchyType1",
    "supplierHierarchyType2",
    "supplierHierarchyType3",
    "roundLevel",
    "roundToInnerPercentage",
    "roundToCasePercentage",
    "roundToLayerPercentage",
    "roundToPalletPercentage",
    "packingMethod",
    "defaultExpenseProfilesInd",
    "purchaseType",
    "calculationBasis",
    "purchaseRate",
    "customFlexAttribute",
    "dimension",
    "location"
})
@Generated("jsonschema2pojo")
public class CountryOfSourcing implements Serializable
{

    @JsonProperty("originCountry")
    private String originCountry;
    @JsonProperty("primaryCountryInd")
    private String primaryCountryInd;
    @JsonProperty("unitCost")
    private Double unitCost;
    @JsonProperty("leadTime")
    private Integer leadTime;
    @JsonProperty("pickupLeadTime")
    private Integer pickupLeadTime;
    @JsonProperty("mininumOrderQuantity")
    private Integer mininumOrderQuantity;
    @JsonProperty("maximumOrderQuantity")
    private Integer maximumOrderQuantity;
    @JsonProperty("supplierHierarchyLevel1")
    private String supplierHierarchyLevel1;
    @JsonProperty("supplierHierarchyLevel2")
    private String supplierHierarchyLevel2;
    @JsonProperty("supplierHierarchyLevel3")
    private String supplierHierarchyLevel3;
    @JsonProperty("defaultUop")
    private String defaultUop;
    @JsonProperty("supplierPackSize")
    private Integer supplierPackSize;
    @JsonProperty("innerPackSize")
    private Integer innerPackSize;
    @JsonProperty("ti")
    private Integer ti;
    @JsonProperty("hi")
    private Integer hi;
    @JsonProperty("costUom")
    private String costUom;
    @JsonProperty("toleranceType")
    private String toleranceType;
    @JsonProperty("minimumTolerance")
    private Integer minimumTolerance;
    @JsonProperty("maximumTolerance")
    private Integer maximumTolerance;
    @JsonProperty("supplierHierarchyType1")
    private String supplierHierarchyType1;
    @JsonProperty("supplierHierarchyType2")
    private String supplierHierarchyType2;
    @JsonProperty("supplierHierarchyType3")
    private String supplierHierarchyType3;
    @JsonProperty("roundLevel")
    private String roundLevel;
    @JsonProperty("roundToInnerPercentage")
    private Integer roundToInnerPercentage;
    @JsonProperty("roundToCasePercentage")
    private Integer roundToCasePercentage;
    @JsonProperty("roundToLayerPercentage")
    private Integer roundToLayerPercentage;
    @JsonProperty("roundToPalletPercentage")
    private Integer roundToPalletPercentage;
    @JsonProperty("packingMethod")
    private String packingMethod;
    @JsonProperty("defaultExpenseProfilesInd")
    private String defaultExpenseProfilesInd;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("calculationBasis")
    private String calculationBasis;
    @JsonProperty("purchaseRate")
    private Integer purchaseRate;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute__2> customFlexAttribute;
    @JsonProperty("dimension")
    private List<Dimension> dimension;
    @JsonProperty("location")
    private List<Location> location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -5033409100637608089L;

    @JsonProperty("originCountry")
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("originCountry")
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
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
    public Double getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unitCost")
    public void setUnitCost(Double unitCost) {
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

    @JsonProperty("mininumOrderQuantity")
    public Integer getMininumOrderQuantity() {
        return mininumOrderQuantity;
    }

    @JsonProperty("mininumOrderQuantity")
    public void setMininumOrderQuantity(Integer mininumOrderQuantity) {
        this.mininumOrderQuantity = mininumOrderQuantity;
    }

    @JsonProperty("maximumOrderQuantity")
    public Integer getMaximumOrderQuantity() {
        return maximumOrderQuantity;
    }

    @JsonProperty("maximumOrderQuantity")
    public void setMaximumOrderQuantity(Integer maximumOrderQuantity) {
        this.maximumOrderQuantity = maximumOrderQuantity;
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

    @JsonProperty("defaultUop")
    public String getDefaultUop() {
        return defaultUop;
    }

    @JsonProperty("defaultUop")
    public void setDefaultUop(String defaultUop) {
        this.defaultUop = defaultUop;
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

    @JsonProperty("minimumTolerance")
    public Integer getMinimumTolerance() {
        return minimumTolerance;
    }

    @JsonProperty("minimumTolerance")
    public void setMinimumTolerance(Integer minimumTolerance) {
        this.minimumTolerance = minimumTolerance;
    }

    @JsonProperty("maximumTolerance")
    public Integer getMaximumTolerance() {
        return maximumTolerance;
    }

    @JsonProperty("maximumTolerance")
    public void setMaximumTolerance(Integer maximumTolerance) {
        this.maximumTolerance = maximumTolerance;
    }

    @JsonProperty("supplierHierarchyType1")
    public String getSupplierHierarchyType1() {
        return supplierHierarchyType1;
    }

    @JsonProperty("supplierHierarchyType1")
    public void setSupplierHierarchyType1(String supplierHierarchyType1) {
        this.supplierHierarchyType1 = supplierHierarchyType1;
    }

    @JsonProperty("supplierHierarchyType2")
    public String getSupplierHierarchyType2() {
        return supplierHierarchyType2;
    }

    @JsonProperty("supplierHierarchyType2")
    public void setSupplierHierarchyType2(String supplierHierarchyType2) {
        this.supplierHierarchyType2 = supplierHierarchyType2;
    }

    @JsonProperty("supplierHierarchyType3")
    public String getSupplierHierarchyType3() {
        return supplierHierarchyType3;
    }

    @JsonProperty("supplierHierarchyType3")
    public void setSupplierHierarchyType3(String supplierHierarchyType3) {
        this.supplierHierarchyType3 = supplierHierarchyType3;
    }

    @JsonProperty("roundLevel")
    public String getRoundLevel() {
        return roundLevel;
    }

    @JsonProperty("roundLevel")
    public void setRoundLevel(String roundLevel) {
        this.roundLevel = roundLevel;
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

    @JsonProperty("packingMethod")
    public String getPackingMethod() {
        return packingMethod;
    }

    @JsonProperty("packingMethod")
    public void setPackingMethod(String packingMethod) {
        this.packingMethod = packingMethod;
    }

    @JsonProperty("defaultExpenseProfilesInd")
    public String getDefaultExpenseProfilesInd() {
        return defaultExpenseProfilesInd;
    }

    @JsonProperty("defaultExpenseProfilesInd")
    public void setDefaultExpenseProfilesInd(String defaultExpenseProfilesInd) {
        this.defaultExpenseProfilesInd = defaultExpenseProfilesInd;
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
    public List<CustomFlexAttribute__2> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute__2> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("dimension")
    public List<Dimension> getDimension() {
        return dimension;
    }

    @JsonProperty("dimension")
    public void setDimension(List<Dimension> dimension) {
        this.dimension = dimension;
    }

    @JsonProperty("location")
    public List<Location> getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(List<Location> location) {
        this.location = location;
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
