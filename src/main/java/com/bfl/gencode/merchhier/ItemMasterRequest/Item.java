package com.bfl.gencode.merchhier.ItemMasterRequest;

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
    "item",
    "itemParent",
    "itemGrandparent",
    "itemNumberType",
    "itemLevel",
    "tranLevel",
    "status",
    "inventoryInd",
    "dept",
    "class",
    "subclass",
    "itemDescription",
    "shortDescription",
    "itemDescriptionSecondary",
    "standardUom",
    "sellableInd",
    "orderableInd",
    "simplePackInd",
    "packInd",
    "containsInnerInd",
    "packType",
    "orderAsType",
    "diff1",
    "diff2",
    "diff3",
    "diff4",
    "storeOrderMultiple",
    "forecastInd",
    "uomConversionFactor",
    "packageSize",
    "handlingTemperature",
    "handlingSensitivity",
    "manufacturerRecommendedRetail",
    "wasteType",
    "averageWastePercentage",
    "catchWeightInd",
    "orderType",
    "saleType",
    "catchWeightUom",
    "depositItemType",
    "containerItem",
    "packageUom",
    "formatId",
    "prefix",
    "itemTransformationInd",
    "brandName",
    "productClassification",
    "merchandiseInd",
    "originalRetail",
    "retailLabelType",
    "retailLabelValue",
    "defaultWastePercentage",
    "itemServiceLevel",
    "depositInPricePerUom",
    "constantDimensionInd",
    "giftWrapInd",
    "shipAloneInd",
    "itemAggregateInd",
    "diff1AggregateInd",
    "diff2AggregateInd",
    "diff3AggregateInd",
    "diff4AggregateInd",
    "perishableInd",
    "storePackInventoryInd",
    "sohInquiryAtPackInd",
    "aipCaseType",
    "costZoneGroupId",
    "itemSuppCountryLocHierarchyLevel",
    "itemZonePriceHierarchyLevel",
    "comments",
    "dataLoadingDestination",
    "customFlexAttribute",
    "translation",
    "supplier",
    "retailByZone",
    "packDetail",
    "vat",
    "uda",
    "season",
    "image",
    "hts",
    "expense",
    "ticket",
    "upcharge"
})

public class Item implements Serializable {
	
    @JsonProperty("item")
    private String item;
    @JsonProperty("itemParent")
    private String itemParent;
    @JsonProperty("itemGrandparent")
    private String itemGrandparent;
    @JsonProperty("itemNumberType")
    private String itemNumberType;
    @JsonProperty("itemLevel")
    private Integer itemLevel;
    @JsonProperty("tranLevel")
    private Integer tranLevel;
    @JsonProperty("status")
    private String status;
    @JsonProperty("inventoryInd")
    private String inventoryInd;
    @JsonProperty("dept")
    private Integer dept;
    @JsonProperty("class")
    private Integer _class;
    @JsonProperty("subclass")
    private Integer subclass;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("itemDescriptionSecondary")
    private String itemDescriptionSecondary;
    @JsonProperty("standardUom")
    private String standardUom;
    @JsonProperty("sellableInd")
    private String sellableInd;
    @JsonProperty("orderableInd")
    private String orderableInd;
    @JsonProperty("simplePackInd")
    private String simplePackInd;
    @JsonProperty("packInd")
    private String packInd;
    @JsonProperty("containsInnerInd")
    private String containsInnerInd;
    @JsonProperty("packType")
    private String packType;
    @JsonProperty("orderAsType")
    private String orderAsType;
    @JsonProperty("diff1")
    private String diff1;
    @JsonProperty("diff2")
    private String diff2;
    @JsonProperty("diff3")
    private String diff3;
    @JsonProperty("diff4")
    private String diff4;
    @JsonProperty("storeOrderMultiple")
    private String storeOrderMultiple;
    @JsonProperty("forecastInd")
    private String forecastInd;
    @JsonProperty("uomConversionFactor")
    private Integer uomConversionFactor;
    @JsonProperty("packageSize")
    private Integer packageSize;
    @JsonProperty("handlingTemperature")
    private String handlingTemperature;
    @JsonProperty("handlingSensitivity")
    private String handlingSensitivity;
    @JsonProperty("manufacturerRecommendedRetail")
    private Double manufacturerRecommendedRetail;
    @JsonProperty("wasteType")
    private String wasteType;
    @JsonProperty("averageWastePercentage")
    private Integer averageWastePercentage;
    @JsonProperty("catchWeightInd")
    private String catchWeightInd;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("saleType")
    private String saleType;
    @JsonProperty("catchWeightUom")
    private String catchWeightUom;
    @JsonProperty("depositItemType")
    private String depositItemType;
    @JsonProperty("containerItem")
    private String containerItem;
    @JsonProperty("packageUom")
    private String packageUom;
    @JsonProperty("formatId")
    private String formatId;
    @JsonProperty("prefix")
    private Integer prefix;
    @JsonProperty("itemTransformationInd")
    private String itemTransformationInd;
    @JsonProperty("brandName")
    private String brandName;
    @JsonProperty("productClassification")
    private String productClassification;
    @JsonProperty("merchandiseInd")
    private String merchandiseInd;
    @JsonProperty("originalRetail")
    private Double originalRetail;
    @JsonProperty("retailLabelType")
    private String retailLabelType;
    @JsonProperty("retailLabelValue")
    private Integer retailLabelValue;
    @JsonProperty("defaultWastePercentage")
    private Integer defaultWastePercentage;
    @JsonProperty("itemServiceLevel")
    private String itemServiceLevel;
    @JsonProperty("depositInPricePerUom")
    private String depositInPricePerUom;
    @JsonProperty("constantDimensionInd")
    private String constantDimensionInd;
    @JsonProperty("giftWrapInd")
    private String giftWrapInd;
    @JsonProperty("shipAloneInd")
    private String shipAloneInd;
    @JsonProperty("itemAggregateInd")
    private String itemAggregateInd;
    @JsonProperty("diff1AggregateInd")
    private String diff1AggregateInd;
    @JsonProperty("diff2AggregateInd")
    private String diff2AggregateInd;
    @JsonProperty("diff3AggregateInd")
    private String diff3AggregateInd;
    @JsonProperty("diff4AggregateInd")
    private String diff4AggregateInd;
    @JsonProperty("perishableInd")
    private String perishableInd;
    @JsonProperty("storePackInventoryInd")
    private String storePackInventoryInd;
    @JsonProperty("sohInquiryAtPackInd")
    private String sohInquiryAtPackInd;
    @JsonProperty("aipCaseType")
    private String aipCaseType;
    @JsonProperty("costZoneGroupId")
    private Integer costZoneGroupId;
    @JsonProperty("itemSuppCountryLocHierarchyLevel")
    private String itemSuppCountryLocHierarchyLevel;
    @JsonProperty("itemZonePriceHierarchyLevel")
    private String itemZonePriceHierarchyLevel;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("dataLoadingDestination")
    private String dataLoadingDestination;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute;
    @JsonProperty("translation")
    private List<Translation> translation;
    @JsonProperty("supplier")
    private List<Supplier> supplier;
    @JsonProperty("retailByZone")
    private List<RetailByZone> retailByZone;
    @JsonProperty("packDetail")
    private List<PackDetail> packDetail;
    @JsonProperty("vat")
    private List<Vat> vat;
    @JsonProperty("uda")
    private List<Uda> uda;
    @JsonProperty("season")
    private List<Season> season;
    @JsonProperty("image")
    private List<Image> image;
    @JsonProperty("hts")
    private List<Ht> hts;
    @JsonProperty("expense")
    private List<Expense> expense;
    @JsonProperty("ticket")
    private List<Ticket> ticket;
    @JsonProperty("upcharge")
    private List<Upcharge> upcharge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -7661677068609573465L;

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("itemParent")
    public String getItemParent() {
        return itemParent;
    }

    @JsonProperty("itemParent")
    public void setItemParent(String itemParent) {
        this.itemParent = itemParent;
    }

    @JsonProperty("itemGrandparent")
    public String getItemGrandparent() {
        return itemGrandparent;
    }

    @JsonProperty("itemGrandparent")
    public void setItemGrandparent(String itemGrandparent) {
        this.itemGrandparent = itemGrandparent;
    }

    @JsonProperty("itemNumberType")
    public String getItemNumberType() {
        return itemNumberType;
    }

    @JsonProperty("itemNumberType")
    public void setItemNumberType(String itemNumberType) {
        this.itemNumberType = itemNumberType;
    }

    @JsonProperty("itemLevel")
    public Integer getItemLevel() {
        return itemLevel;
    }

    @JsonProperty("itemLevel")
    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    @JsonProperty("tranLevel")
    public Integer getTranLevel() {
        return tranLevel;
    }

    @JsonProperty("tranLevel")
    public void setTranLevel(Integer tranLevel) {
        this.tranLevel = tranLevel;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("inventoryInd")
    public String getInventoryInd() {
        return inventoryInd;
    }

    @JsonProperty("inventoryInd")
    public void setInventoryInd(String inventoryInd) {
        this.inventoryInd = inventoryInd;
    }

    @JsonProperty("dept")
    public Integer getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(Integer dept) {
        this.dept = dept;
    }

    @JsonProperty("class")
    public Integer getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    @JsonProperty("subclass")
    public Integer getSubclass() {
        return subclass;
    }

    @JsonProperty("subclass")
    public void setSubclass(Integer subclass) {
        this.subclass = subclass;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("itemDescriptionSecondary")
    public String getItemDescriptionSecondary() {
        return itemDescriptionSecondary;
    }

    @JsonProperty("itemDescriptionSecondary")
    public void setItemDescriptionSecondary(String itemDescriptionSecondary) {
        this.itemDescriptionSecondary = itemDescriptionSecondary;
    }

    @JsonProperty("standardUom")
    public String getStandardUom() {
        return standardUom;
    }

    @JsonProperty("standardUom")
    public void setStandardUom(String standardUom) {
        this.standardUom = standardUom;
    }

    @JsonProperty("sellableInd")
    public String getSellableInd() {
        return sellableInd;
    }

    @JsonProperty("sellableInd")
    public void setSellableInd(String sellableInd) {
        this.sellableInd = sellableInd;
    }

    @JsonProperty("orderableInd")
    public String getOrderableInd() {
        return orderableInd;
    }

    @JsonProperty("orderableInd")
    public void setOrderableInd(String orderableInd) {
        this.orderableInd = orderableInd;
    }

    @JsonProperty("simplePackInd")
    public String getSimplePackInd() {
        return simplePackInd;
    }

    @JsonProperty("simplePackInd")
    public void setSimplePackInd(String simplePackInd) {
        this.simplePackInd = simplePackInd;
    }

    @JsonProperty("packInd")
    public String getPackInd() {
        return packInd;
    }

    @JsonProperty("packInd")
    public void setPackInd(String packInd) {
        this.packInd = packInd;
    }

    @JsonProperty("containsInnerInd")
    public String getContainsInnerInd() {
        return containsInnerInd;
    }

    @JsonProperty("containsInnerInd")
    public void setContainsInnerInd(String containsInnerInd) {
        this.containsInnerInd = containsInnerInd;
    }

    @JsonProperty("packType")
    public String getPackType() {
        return packType;
    }

    @JsonProperty("packType")
    public void setPackType(String packType) {
        this.packType = packType;
    }

    @JsonProperty("orderAsType")
    public String getOrderAsType() {
        return orderAsType;
    }

    @JsonProperty("orderAsType")
    public void setOrderAsType(String orderAsType) {
        this.orderAsType = orderAsType;
    }

    @JsonProperty("diff1")
    public String getDiff1() {
        return diff1;
    }

    @JsonProperty("diff1")
    public void setDiff1(String diff1) {
        this.diff1 = diff1;
    }

    @JsonProperty("diff2")
    public String getDiff2() {
        return diff2;
    }

    @JsonProperty("diff2")
    public void setDiff2(String diff2) {
        this.diff2 = diff2;
    }

    @JsonProperty("diff3")
    public String getDiff3() {
        return diff3;
    }

    @JsonProperty("diff3")
    public void setDiff3(String diff3) {
        this.diff3 = diff3;
    }

    @JsonProperty("diff4")
    public String getDiff4() {
        return diff4;
    }

    @JsonProperty("diff4")
    public void setDiff4(String diff4) {
        this.diff4 = diff4;
    }

    @JsonProperty("storeOrderMultiple")
    public String getStoreOrderMultiple() {
        return storeOrderMultiple;
    }

    @JsonProperty("storeOrderMultiple")
    public void setStoreOrderMultiple(String storeOrderMultiple) {
        this.storeOrderMultiple = storeOrderMultiple;
    }

    @JsonProperty("forecastInd")
    public String getForecastInd() {
        return forecastInd;
    }

    @JsonProperty("forecastInd")
    public void setForecastInd(String forecastInd) {
        this.forecastInd = forecastInd;
    }

    @JsonProperty("uomConversionFactor")
    public Integer getUomConversionFactor() {
        return uomConversionFactor;
    }

    @JsonProperty("uomConversionFactor")
    public void setUomConversionFactor(Integer uomConversionFactor) {
        this.uomConversionFactor = uomConversionFactor;
    }

    @JsonProperty("packageSize")
    public Integer getPackageSize() {
        return packageSize;
    }

    @JsonProperty("packageSize")
    public void setPackageSize(Integer packageSize) {
        this.packageSize = packageSize;
    }

    @JsonProperty("handlingTemperature")
    public String getHandlingTemperature() {
        return handlingTemperature;
    }

    @JsonProperty("handlingTemperature")
    public void setHandlingTemperature(String handlingTemperature) {
        this.handlingTemperature = handlingTemperature;
    }

    @JsonProperty("handlingSensitivity")
    public String getHandlingSensitivity() {
        return handlingSensitivity;
    }

    @JsonProperty("handlingSensitivity")
    public void setHandlingSensitivity(String handlingSensitivity) {
        this.handlingSensitivity = handlingSensitivity;
    }

    @JsonProperty("manufacturerRecommendedRetail")
    public Double getManufacturerRecommendedRetail() {
        return manufacturerRecommendedRetail;
    }

    @JsonProperty("manufacturerRecommendedRetail")
    public void setManufacturerRecommendedRetail(Double manufacturerRecommendedRetail) {
        this.manufacturerRecommendedRetail = manufacturerRecommendedRetail;
    }

    @JsonProperty("wasteType")
    public String getWasteType() {
        return wasteType;
    }

    @JsonProperty("wasteType")
    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    @JsonProperty("averageWastePercentage")
    public Integer getAverageWastePercentage() {
        return averageWastePercentage;
    }

    @JsonProperty("averageWastePercentage")
    public void setAverageWastePercentage(Integer averageWastePercentage) {
        this.averageWastePercentage = averageWastePercentage;
    }

    @JsonProperty("catchWeightInd")
    public String getCatchWeightInd() {
        return catchWeightInd;
    }

    @JsonProperty("catchWeightInd")
    public void setCatchWeightInd(String catchWeightInd) {
        this.catchWeightInd = catchWeightInd;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("saleType")
    public String getSaleType() {
        return saleType;
    }

    @JsonProperty("saleType")
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    @JsonProperty("catchWeightUom")
    public String getCatchWeightUom() {
        return catchWeightUom;
    }

    @JsonProperty("catchWeightUom")
    public void setCatchWeightUom(String catchWeightUom) {
        this.catchWeightUom = catchWeightUom;
    }

    @JsonProperty("depositItemType")
    public String getDepositItemType() {
        return depositItemType;
    }

    @JsonProperty("depositItemType")
    public void setDepositItemType(String depositItemType) {
        this.depositItemType = depositItemType;
    }

    @JsonProperty("containerItem")
    public String getContainerItem() {
        return containerItem;
    }

    @JsonProperty("containerItem")
    public void setContainerItem(String containerItem) {
        this.containerItem = containerItem;
    }

    @JsonProperty("packageUom")
    public String getPackageUom() {
        return packageUom;
    }

    @JsonProperty("packageUom")
    public void setPackageUom(String packageUom) {
        this.packageUom = packageUom;
    }

    @JsonProperty("formatId")
    public String getFormatId() {
        return formatId;
    }

    @JsonProperty("formatId")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @JsonProperty("prefix")
    public Integer getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("itemTransformationInd")
    public String getItemTransformationInd() {
        return itemTransformationInd;
    }

    @JsonProperty("itemTransformationInd")
    public void setItemTransformationInd(String itemTransformationInd) {
        this.itemTransformationInd = itemTransformationInd;
    }

    @JsonProperty("brandName")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brandName")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("productClassification")
    public String getProductClassification() {
        return productClassification;
    }

    @JsonProperty("productClassification")
    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
    }

    @JsonProperty("merchandiseInd")
    public String getMerchandiseInd() {
        return merchandiseInd;
    }

    @JsonProperty("merchandiseInd")
    public void setMerchandiseInd(String merchandiseInd) {
        this.merchandiseInd = merchandiseInd;
    }

    @JsonProperty("originalRetail")
    public Double getOriginalRetail() {
        return originalRetail;
    }

    @JsonProperty("originalRetail")
    public void setOriginalRetail(Double originalRetail) {
        this.originalRetail = originalRetail;
    }

    @JsonProperty("retailLabelType")
    public String getRetailLabelType() {
        return retailLabelType;
    }

    @JsonProperty("retailLabelType")
    public void setRetailLabelType(String retailLabelType) {
        this.retailLabelType = retailLabelType;
    }

    @JsonProperty("retailLabelValue")
    public Integer getRetailLabelValue() {
        return retailLabelValue;
    }

    @JsonProperty("retailLabelValue")
    public void setRetailLabelValue(Integer retailLabelValue) {
        this.retailLabelValue = retailLabelValue;
    }

    @JsonProperty("defaultWastePercentage")
    public Integer getDefaultWastePercentage() {
        return defaultWastePercentage;
    }

    @JsonProperty("defaultWastePercentage")
    public void setDefaultWastePercentage(Integer defaultWastePercentage) {
        this.defaultWastePercentage = defaultWastePercentage;
    }

    @JsonProperty("itemServiceLevel")
    public String getItemServiceLevel() {
        return itemServiceLevel;
    }

    @JsonProperty("itemServiceLevel")
    public void setItemServiceLevel(String itemServiceLevel) {
        this.itemServiceLevel = itemServiceLevel;
    }

    @JsonProperty("depositInPricePerUom")
    public String getDepositInPricePerUom() {
        return depositInPricePerUom;
    }

    @JsonProperty("depositInPricePerUom")
    public void setDepositInPricePerUom(String depositInPricePerUom) {
        this.depositInPricePerUom = depositInPricePerUom;
    }

    @JsonProperty("constantDimensionInd")
    public String getConstantDimensionInd() {
        return constantDimensionInd;
    }

    @JsonProperty("constantDimensionInd")
    public void setConstantDimensionInd(String constantDimensionInd) {
        this.constantDimensionInd = constantDimensionInd;
    }

    @JsonProperty("giftWrapInd")
    public String getGiftWrapInd() {
        return giftWrapInd;
    }

    @JsonProperty("giftWrapInd")
    public void setGiftWrapInd(String giftWrapInd) {
        this.giftWrapInd = giftWrapInd;
    }

    @JsonProperty("shipAloneInd")
    public String getShipAloneInd() {
        return shipAloneInd;
    }

    @JsonProperty("shipAloneInd")
    public void setShipAloneInd(String shipAloneInd) {
        this.shipAloneInd = shipAloneInd;
    }

    @JsonProperty("itemAggregateInd")
    public String getItemAggregateInd() {
        return itemAggregateInd;
    }

    @JsonProperty("itemAggregateInd")
    public void setItemAggregateInd(String itemAggregateInd) {
        this.itemAggregateInd = itemAggregateInd;
    }

    @JsonProperty("diff1AggregateInd")
    public String getDiff1AggregateInd() {
        return diff1AggregateInd;
    }

    @JsonProperty("diff1AggregateInd")
    public void setDiff1AggregateInd(String diff1AggregateInd) {
        this.diff1AggregateInd = diff1AggregateInd;
    }

    @JsonProperty("diff2AggregateInd")
    public String getDiff2AggregateInd() {
        return diff2AggregateInd;
    }

    @JsonProperty("diff2AggregateInd")
    public void setDiff2AggregateInd(String diff2AggregateInd) {
        this.diff2AggregateInd = diff2AggregateInd;
    }

    @JsonProperty("diff3AggregateInd")
    public String getDiff3AggregateInd() {
        return diff3AggregateInd;
    }

    @JsonProperty("diff3AggregateInd")
    public void setDiff3AggregateInd(String diff3AggregateInd) {
        this.diff3AggregateInd = diff3AggregateInd;
    }

    @JsonProperty("diff4AggregateInd")
    public String getDiff4AggregateInd() {
        return diff4AggregateInd;
    }

    @JsonProperty("diff4AggregateInd")
    public void setDiff4AggregateInd(String diff4AggregateInd) {
        this.diff4AggregateInd = diff4AggregateInd;
    }

    @JsonProperty("perishableInd")
    public String getPerishableInd() {
        return perishableInd;
    }

    @JsonProperty("perishableInd")
    public void setPerishableInd(String perishableInd) {
        this.perishableInd = perishableInd;
    }

    @JsonProperty("storePackInventoryInd")
    public String getStorePackInventoryInd() {
        return storePackInventoryInd;
    }

    @JsonProperty("storePackInventoryInd")
    public void setStorePackInventoryInd(String storePackInventoryInd) {
        this.storePackInventoryInd = storePackInventoryInd;
    }

    @JsonProperty("sohInquiryAtPackInd")
    public String getSohInquiryAtPackInd() {
        return sohInquiryAtPackInd;
    }

    @JsonProperty("sohInquiryAtPackInd")
    public void setSohInquiryAtPackInd(String sohInquiryAtPackInd) {
        this.sohInquiryAtPackInd = sohInquiryAtPackInd;
    }

    @JsonProperty("aipCaseType")
    public String getAipCaseType() {
        return aipCaseType;
    }

    @JsonProperty("aipCaseType")
    public void setAipCaseType(String aipCaseType) {
        this.aipCaseType = aipCaseType;
    }

    @JsonProperty("costZoneGroupId")
    public Integer getCostZoneGroupId() {
        return costZoneGroupId;
    }

    @JsonProperty("costZoneGroupId")
    public void setCostZoneGroupId(Integer costZoneGroupId) {
        this.costZoneGroupId = costZoneGroupId;
    }

    @JsonProperty("itemSuppCountryLocHierarchyLevel")
    public String getItemSuppCountryLocHierarchyLevel() {
        return itemSuppCountryLocHierarchyLevel;
    }

    @JsonProperty("itemSuppCountryLocHierarchyLevel")
    public void setItemSuppCountryLocHierarchyLevel(String itemSuppCountryLocHierarchyLevel) {
        this.itemSuppCountryLocHierarchyLevel = itemSuppCountryLocHierarchyLevel;
    }

    @JsonProperty("itemZonePriceHierarchyLevel")
    public String getItemZonePriceHierarchyLevel() {
        return itemZonePriceHierarchyLevel;
    }

    @JsonProperty("itemZonePriceHierarchyLevel")
    public void setItemZonePriceHierarchyLevel(String itemZonePriceHierarchyLevel) {
        this.itemZonePriceHierarchyLevel = itemZonePriceHierarchyLevel;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("dataLoadingDestination")
    public String getDataLoadingDestination() {
        return dataLoadingDestination;
    }

    @JsonProperty("dataLoadingDestination")
    public void setDataLoadingDestination(String dataLoadingDestination) {
        this.dataLoadingDestination = dataLoadingDestination;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("translation")
    public List<Translation> getTranslation() {
        return translation;
    }

    @JsonProperty("translation")
    public void setTranslation(List<Translation> translation) {
        this.translation = translation;
    }

    @JsonProperty("supplier")
    public List<Supplier> getSupplier() {
        return supplier;
    }

    @JsonProperty("supplier")
    public void setSupplier(List<Supplier> supplier) {
        this.supplier = supplier;
    }

    @JsonProperty("retailByZone")
    public List<RetailByZone> getRetailByZone() {
        return retailByZone;
    }

    @JsonProperty("retailByZone")
    public void setRetailByZone(List<RetailByZone> retailByZone) {
        this.retailByZone = retailByZone;
    }

    @JsonProperty("packDetail")
    public List<PackDetail> getPackDetail() {
        return packDetail;
    }

    @JsonProperty("packDetail")
    public void setPackDetail(List<PackDetail> packDetail) {
        this.packDetail = packDetail;
    }

    @JsonProperty("vat")
    public List<Vat> getVat() {
        return vat;
    }

    @JsonProperty("vat")
    public void setVat(List<Vat> vat) {
        this.vat = vat;
    }

    @JsonProperty("uda")
    public List<Uda> getUda() {
        return uda;
    }

    @JsonProperty("uda")
    public void setUda(List<Uda> uda) {
        this.uda = uda;
    }

    @JsonProperty("season")
    public List<Season> getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(List<Season> season) {
        this.season = season;
    }

    @JsonProperty("image")
    public List<Image> getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(List<Image> image) {
        this.image = image;
    }

    @JsonProperty("hts")
    public List<Ht> getHts() {
        return hts;
    }

    @JsonProperty("hts")
    public void setHts(List<Ht> hts) {
        this.hts = hts;
    }

    @JsonProperty("expense")
    public List<Expense> getExpense() {
        return expense;
    }

    @JsonProperty("expense")
    public void setExpense(List<Expense> expense) {
        this.expense = expense;
    }

    @JsonProperty("ticket")
    public List<Ticket> getTicket() {
        return ticket;
    }

    @JsonProperty("ticket")
    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    @JsonProperty("upcharge")
    public List<Upcharge> getUpcharge() {
        return upcharge;
    }

    @JsonProperty("upcharge")
    public void setUpcharge(List<Upcharge> upcharge) {
        this.upcharge = upcharge;
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
