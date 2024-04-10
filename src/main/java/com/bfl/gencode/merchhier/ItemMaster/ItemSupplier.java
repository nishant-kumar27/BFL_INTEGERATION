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
    "supplier",
    "primarySupplierInd",
    "vpn",
    "supplierLabel",
    "consignmentRate",
    "supplierDiff1",
    "supplierDiff2",
    "supplierDiff3",
    "supplierDiff4",
    "palletName",
    "caseName",
    "innerName",
    "supplierDiscontinueDate",
    "directShipInd",
    "primaryCaseSize",
    "createDateTime",
    "updateDateTime",
    "customFlexAttribute",
    "itemSupplierCountry",
    "itemSupplierCountryOfManufacture",
    "itemSupplierUom"
})
public class ItemSupplier {

    @JsonProperty("supplier")
    private Integer supplier;
    @JsonProperty("primarySupplierInd")
    private String primarySupplierInd;
    @JsonProperty("vpn")
    private String vpn;
    @JsonProperty("supplierLabel")
    private String supplierLabel;
    @JsonProperty("consignmentRate")
    private Integer consignmentRate;
    @JsonProperty("supplierDiff1")
    private String supplierDiff1;
    @JsonProperty("supplierDiff2")
    private String supplierDiff2;
    @JsonProperty("supplierDiff3")
    private String supplierDiff3;
    @JsonProperty("supplierDiff4")
    private String supplierDiff4;
    @JsonProperty("palletName")
    private String palletName;
    @JsonProperty("caseName")
    private String caseName;
    @JsonProperty("innerName")
    private String innerName;
    @JsonProperty("supplierDiscontinueDate")
    private String supplierDiscontinueDate;
    @JsonProperty("directShipInd")
    private String directShipInd;
    @JsonProperty("primaryCaseSize")
    private String primaryCaseSize;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute__1> customFlexAttribute = null;
    @JsonProperty("itemSupplierCountry")
    private List<ItemSupplierCountry> itemSupplierCountry = null;
    @JsonProperty("itemSupplierCountryOfManufacture")
    private List<ItemSupplierCountryOfManufacture> itemSupplierCountryOfManufacture = null;
    @JsonProperty("itemSupplierUom")
    private List<ItemSupplierUom> itemSupplierUom = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("supplier")
    public Integer getSupplier() {
        return supplier;
    }

    @JsonProperty("supplier")
    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    @JsonProperty("primarySupplierInd")
    public String getPrimarySupplierInd() {
        return primarySupplierInd;
    }

    @JsonProperty("primarySupplierInd")
    public void setPrimarySupplierInd(String primarySupplierInd) {
        this.primarySupplierInd = primarySupplierInd;
    }

    @JsonProperty("vpn")
    public String getVpn() {
        return vpn;
    }

    @JsonProperty("vpn")
    public void setVpn(String vpn) {
        this.vpn = vpn;
    }

    @JsonProperty("supplierLabel")
    public String getSupplierLabel() {
        return supplierLabel;
    }

    @JsonProperty("supplierLabel")
    public void setSupplierLabel(String supplierLabel) {
        this.supplierLabel = supplierLabel;
    }

    @JsonProperty("consignmentRate")
    public Integer getConsignmentRate() {
        return consignmentRate;
    }

    @JsonProperty("consignmentRate")
    public void setConsignmentRate(Integer consignmentRate) {
        this.consignmentRate = consignmentRate;
    }

    @JsonProperty("supplierDiff1")
    public String getSupplierDiff1() {
        return supplierDiff1;
    }

    @JsonProperty("supplierDiff1")
    public void setSupplierDiff1(String supplierDiff1) {
        this.supplierDiff1 = supplierDiff1;
    }

    @JsonProperty("supplierDiff2")
    public String getSupplierDiff2() {
        return supplierDiff2;
    }

    @JsonProperty("supplierDiff2")
    public void setSupplierDiff2(String supplierDiff2) {
        this.supplierDiff2 = supplierDiff2;
    }

    @JsonProperty("supplierDiff3")
    public String getSupplierDiff3() {
        return supplierDiff3;
    }

    @JsonProperty("supplierDiff3")
    public void setSupplierDiff3(String supplierDiff3) {
        this.supplierDiff3 = supplierDiff3;
    }

    @JsonProperty("supplierDiff4")
    public String getSupplierDiff4() {
        return supplierDiff4;
    }

    @JsonProperty("supplierDiff4")
    public void setSupplierDiff4(String supplierDiff4) {
        this.supplierDiff4 = supplierDiff4;
    }

    @JsonProperty("palletName")
    public String getPalletName() {
        return palletName;
    }

    @JsonProperty("palletName")
    public void setPalletName(String palletName) {
        this.palletName = palletName;
    }

    @JsonProperty("caseName")
    public String getCaseName() {
        return caseName;
    }

    @JsonProperty("caseName")
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    @JsonProperty("innerName")
    public String getInnerName() {
        return innerName;
    }

    @JsonProperty("innerName")
    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    @JsonProperty("supplierDiscontinueDate")
    public String getSupplierDiscontinueDate() {
        return supplierDiscontinueDate;
    }

    @JsonProperty("supplierDiscontinueDate")
    public void setSupplierDiscontinueDate(String supplierDiscontinueDate) {
        this.supplierDiscontinueDate = supplierDiscontinueDate;
    }

    @JsonProperty("directShipInd")
    public String getDirectShipInd() {
        return directShipInd;
    }

    @JsonProperty("directShipInd")
    public void setDirectShipInd(String directShipInd) {
        this.directShipInd = directShipInd;
    }

    @JsonProperty("primaryCaseSize")
    public String getPrimaryCaseSize() {
        return primaryCaseSize;
    }

    @JsonProperty("primaryCaseSize")
    public void setPrimaryCaseSize(String primaryCaseSize) {
        this.primaryCaseSize = primaryCaseSize;
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
    public List<CustomFlexAttribute__1> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute__1> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("itemSupplierCountry")
    public List<ItemSupplierCountry> getItemSupplierCountry() {
        return itemSupplierCountry;
    }

    @JsonProperty("itemSupplierCountry")
    public void setItemSupplierCountry(List<ItemSupplierCountry> itemSupplierCountry) {
        this.itemSupplierCountry = itemSupplierCountry;
    }

    @JsonProperty("itemSupplierCountryOfManufacture")
    public List<ItemSupplierCountryOfManufacture> getItemSupplierCountryOfManufacture() {
        return itemSupplierCountryOfManufacture;
    }

    @JsonProperty("itemSupplierCountryOfManufacture")
    public void setItemSupplierCountryOfManufacture(List<ItemSupplierCountryOfManufacture> itemSupplierCountryOfManufacture) {
        this.itemSupplierCountryOfManufacture = itemSupplierCountryOfManufacture;
    }

    @JsonProperty("itemSupplierUom")
    public List<ItemSupplierUom> getItemSupplierUom() {
        return itemSupplierUom;
    }

    @JsonProperty("itemSupplierUom")
    public void setItemSupplierUom(List<ItemSupplierUom> itemSupplierUom) {
        this.itemSupplierUom = itemSupplierUom;
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
