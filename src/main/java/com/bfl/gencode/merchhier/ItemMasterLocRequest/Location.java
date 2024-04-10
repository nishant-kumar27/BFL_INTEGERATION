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
    "hierarchyValue",
    "primarySupplier",
    "primaryCountry",
    "localItemDescription",
    "status",
    "storeOrderMultiple",
    "receiveAsType",
    "taxableInd",
    "ti",
    "hi",
    "dailyWastePercent",
    "localShortDescription",
    "uinType",
    "uinLabel",
    "captureTime",
    "externalUinInd",
    "sourceMethod",
    "sourceWarehouse",
    "unitCost",
    "purchaseType",
    "calculationBasis",
    "purchaseRate",
    "promotableInd",
    "rfidInd",
    "customFlexAttribute",
    "trait"
})

public class Location implements Serializable {
	
    @JsonProperty("hierarchyValue")
    private String hierarchyValue;
    @JsonProperty("primarySupplier")
    private String primarySupplier;
    @JsonProperty("primaryCountry")
    private String primaryCountry;
    @JsonProperty("localItemDescription")
    private String localItemDescription;
    @JsonProperty("status")
    private String status;
    @JsonProperty("storeOrderMultiple")
    private String storeOrderMultiple;
    @JsonProperty("receiveAsType")
    private String receiveAsType;
    @JsonProperty("taxableInd")
    private String taxableInd;
    @JsonProperty("ti")
    private Integer ti;
    @JsonProperty("hi")
    private Integer hi;
    @JsonProperty("dailyWastePercent")
    private Integer dailyWastePercent;
    @JsonProperty("localShortDescription")
    private String localShortDescription;
    @JsonProperty("uinType")
    private String uinType;
    @JsonProperty("uinLabel")
    private String uinLabel;
    @JsonProperty("captureTime")
    private String captureTime;
    @JsonProperty("externalUinInd")
    private String externalUinInd;
    @JsonProperty("sourceMethod")
    private String sourceMethod;
    @JsonProperty("sourceWarehouse")
    private String sourceWarehouse;
    @JsonProperty("unitCost")
    private String unitCost;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("calculationBasis")
    private String calculationBasis;
    @JsonProperty("purchaseRate")
    private String purchaseRate;
    @JsonProperty("promotableInd")
    private String promotableInd;
    @JsonProperty("rfidInd")
    private String rfidInd;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute;
    @JsonProperty("trait")
    private Trait trait;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 5924534004114034181L;

    @JsonProperty("hierarchyValue")
    public String getHierarchyValue() {
        return hierarchyValue;
    }

    @JsonProperty("hierarchyValue")
    public void setHierarchyValue(String hierarchyValue) {
        this.hierarchyValue = hierarchyValue;
    }

    @JsonProperty("primarySupplier")
    public String getPrimarySupplier() {
        return primarySupplier;
    }

    @JsonProperty("primarySupplier")
    public void setPrimarySupplier(String primarySupplier) {
        this.primarySupplier = primarySupplier;
    }

    @JsonProperty("primaryCountry")
    public String getPrimaryCountry() {
        return primaryCountry;
    }

    @JsonProperty("primaryCountry")
    public void setPrimaryCountry(String primaryCountry) {
        this.primaryCountry = primaryCountry;
    }

    @JsonProperty("localItemDescription")
    public String getLocalItemDescription() {
        return localItemDescription;
    }

    @JsonProperty("localItemDescription")
    public void setLocalItemDescription(String localItemDescription) {
        this.localItemDescription = localItemDescription;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("storeOrderMultiple")
    public String getStoreOrderMultiple() {
        return storeOrderMultiple;
    }

    @JsonProperty("storeOrderMultiple")
    public void setStoreOrderMultiple(String storeOrderMultiple) {
        this.storeOrderMultiple = storeOrderMultiple;
    }

    @JsonProperty("receiveAsType")
    public String getReceiveAsType() {
        return receiveAsType;
    }

    @JsonProperty("receiveAsType")
    public void setReceiveAsType(String receiveAsType) {
        this.receiveAsType = receiveAsType;
    }

    @JsonProperty("taxableInd")
    public String getTaxableInd() {
        return taxableInd;
    }

    @JsonProperty("taxableInd")
    public void setTaxableInd(String taxableInd) {
        this.taxableInd = taxableInd;
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

    @JsonProperty("dailyWastePercent")
    public Integer getDailyWastePercent() {
        return dailyWastePercent;
    }

    @JsonProperty("dailyWastePercent")
    public void setDailyWastePercent(Integer dailyWastePercent) {
        this.dailyWastePercent = dailyWastePercent;
    }

    @JsonProperty("localShortDescription")
    public String getLocalShortDescription() {
        return localShortDescription;
    }

    @JsonProperty("localShortDescription")
    public void setLocalShortDescription(String localShortDescription) {
        this.localShortDescription = localShortDescription;
    }

    @JsonProperty("uinType")
    public String getUinType() {
        return uinType;
    }

    @JsonProperty("uinType")
    public void setUinType(String uinType) {
        this.uinType = uinType;
    }

    @JsonProperty("uinLabel")
    public String getUinLabel() {
        return uinLabel;
    }

    @JsonProperty("uinLabel")
    public void setUinLabel(String uinLabel) {
        this.uinLabel = uinLabel;
    }

    @JsonProperty("captureTime")
    public String getCaptureTime() {
        return captureTime;
    }

    @JsonProperty("captureTime")
    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    @JsonProperty("externalUinInd")
    public String getExternalUinInd() {
        return externalUinInd;
    }

    @JsonProperty("externalUinInd")
    public void setExternalUinInd(String externalUinInd) {
        this.externalUinInd = externalUinInd;
    }

    @JsonProperty("sourceMethod")
    public String getSourceMethod() {
        return sourceMethod;
    }

    @JsonProperty("sourceMethod")
    public void setSourceMethod(String sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    @JsonProperty("sourceWarehouse")
    public String getSourceWarehouse() {
        return sourceWarehouse;
    }

    @JsonProperty("sourceWarehouse")
    public void setSourceWarehouse(String sourceWarehouse) {
        this.sourceWarehouse = sourceWarehouse;
    }

    @JsonProperty("unitCost")
    public String getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unitCost")
    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
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
    public String getPurchaseRate() {
        return purchaseRate;
    }

    @JsonProperty("purchaseRate")
    public void setPurchaseRate(String purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @JsonProperty("promotableInd")
    public String getPromotableInd() {
        return promotableInd;
    }

    @JsonProperty("promotableInd")
    public void setPromotableInd(String promotableInd) {
        this.promotableInd = promotableInd;
    }

    @JsonProperty("rfidInd")
    public String getRfidInd() {
        return rfidInd;
    }

    @JsonProperty("rfidInd")
    public void setRfidInd(String rfidInd) {
        this.rfidInd = rfidInd;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("trait")
    public Trait getTrait() {
        return trait;
    }

    @JsonProperty("trait")
    public void setTrait(Trait trait) {
        this.trait = trait;
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
