package com.bfl.gencode.merchhier.ItemMaster;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "action",
    "item",
    "itemNumberType",
    "status",
    "itemLevel",
    "tranLevel",
    "itemDescription",
    "shortDescription",
    "itemDescriptionUppercase",
    "itemDescriptionSecondary",
    "itemParent",
    "itemGrandparent",
    "sellableInd",
    "orderableInd",
    "inventoryInd",
    "packInd",
    "simplePackInd",
    "containsInnerInd",
    "dept",
    "deptName",
    "class",
    "className",
    "uniqueClassId",
    "subclass",
    "subclassName",
    "uniqueSubclassId",
    "unitRetail",
    "variableUpcFormatId",
    "variableWeightUpcPrefix",
    "diff1",
    "diff1Type",
    "diff2",
    "diff2Type",
    "diff3",
    "diff3Type",
    "diff4",
    "diff4Type",
    "costZoneGroupId",
    "standardUom",
    "uomConversionFactor",
    "packageSize",
    "packageUom",
    "merchandiseInd",
    "storeOrderMultiple",
    "forecastInd",
    "manufacturerRecommendedRetail",
    "manufacturerRetailCurrencyCode",
    "originalRetail",
    "originalRetailCurrencyCode",
    "retailLabelType",
    "retailLabelTypeDescription",
    "retailLabelValue",
    "handlingTemperature",
    "handlingTemperatureDescription",
    "handlingSensitivity",
    "handlingSensitivityDescription",
    "catchWeightInd",
    "catchWeightType",
    "catchWeightUom",
    "orderType",
    "saleType",
    "wasteType",
    "wasteTypeDescription",
    "averageWastePercentage",
    "defaultWastePercentage",
    "constantDimensionInd",
    "packType",
    "orderAsType",
    "comments",
    "itemServiceLevel",
    "giftWrapInd",
    "shipAloneInd",
    "brandName",
    "brandDescription",
    "perishableInd",
    "itemTransformInd",
    "depositItemType",
    "containerItem",
    "depositInPricePerUom",
    "storePackInventoryInd",
    "sohInquiryAtPackInd",
    "purchaseType",
    "productClassification",
    "productClassificationDescription",
    "itemAggregateInd",
    "diff1AggregateInd",
    "diff2AggregateInd",
    "diff3AggregateInd",
    "diff4AggregateInd",
    "diff1Level",
    "diff1Description",
    "diff2Level",
    "diff2Description",
    "diff3Level",
    "diff3Description",
    "diff4Level",
    "diff4Description",
    "primaryImageUrl",
    "createDateTime",
    "updateDateTime",
    "customFlexAttribute",
    "itemTranslation",
    "itemSupplier",
    "itemUda",
    "itemImage",
    "referenceItem",
    "itemBOM",
    "itemTicket",
    "relatedItem",
    "groupcode",
    "color",
    "cacheTimestamp"
})
public class Item {

    @JsonProperty("action")
    private String action;
    @JsonProperty("item")
    private String item;
    @JsonProperty("itemNumberType")
    private String itemNumberType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("itemLevel")
    private Integer itemLevel;
    @JsonProperty("tranLevel")
    private Integer tranLevel;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("itemDescriptionUppercase")
    private String itemDescriptionUppercase;
    @JsonProperty("itemDescriptionSecondary")
    private String itemDescriptionSecondary;
    @JsonProperty("itemParent")
    private String itemParent;
    @JsonProperty("itemGrandparent")
    private String itemGrandparent;
    @JsonProperty("sellableInd")
    private String sellableInd;
    @JsonProperty("orderableInd")
    private String orderableInd;
    @JsonProperty("inventoryInd")
    private String inventoryInd;
    @JsonProperty("packInd")
    private String packInd;
    @JsonProperty("simplePackInd")
    private String simplePackInd;
    @JsonProperty("containsInnerInd")
    private String containsInnerInd;
    @JsonProperty("dept")
    private Integer dept;
    @JsonProperty("deptName")
    private String deptName;
    @JsonProperty("class")
    private Integer _class;
    @JsonProperty("className")
    private String className;
    @JsonProperty("uniqueClassId")
    private Integer uniqueClassId;
    @JsonProperty("subclass")
    private Integer subclass;
    @JsonProperty("subclassName")
    private String subclassName;
    @JsonProperty("uniqueSubclassId")
    private Integer uniqueSubclassId;
    @JsonProperty("unitRetail")
    private Integer unitRetail;
    @JsonProperty("variableUpcFormatId")
    private String variableUpcFormatId;
    @JsonProperty("variableWeightUpcPrefix")
    private Integer variableWeightUpcPrefix;
    @JsonProperty("diff1")
    private String diff1;
    @JsonProperty("diff1Type")
    private String diff1Type;
    @JsonProperty("diff2")
    private String diff2;
    @JsonProperty("diff2Type")
    private String diff2Type;
    @JsonProperty("diff3")
    private String diff3;
    @JsonProperty("diff3Type")
    private String diff3Type;
    @JsonProperty("diff4")
    private String diff4;
    @JsonProperty("diff4Type")
    private String diff4Type;
    @JsonProperty("costZoneGroupId")
    private Integer costZoneGroupId;
    @JsonProperty("standardUom")
    private String standardUom;
    @JsonProperty("uomConversionFactor")
    private Integer uomConversionFactor;
    @JsonProperty("packageSize")
    private Integer packageSize;
    @JsonProperty("packageUom")
    private String packageUom;
    @JsonProperty("merchandiseInd")
    private String merchandiseInd;
    @JsonProperty("storeOrderMultiple")
    private String storeOrderMultiple;
    @JsonProperty("forecastInd")
    private String forecastInd;
    @JsonProperty("manufacturerRecommendedRetail")
    private Integer manufacturerRecommendedRetail;
    @JsonProperty("manufacturerRetailCurrencyCode")
    private String manufacturerRetailCurrencyCode;
    @JsonProperty("originalRetail")
    private Integer originalRetail;
    @JsonProperty("originalRetailCurrencyCode")
    private String originalRetailCurrencyCode;
    @JsonProperty("retailLabelType")
    private String retailLabelType;
    @JsonProperty("retailLabelTypeDescription")
    private String retailLabelTypeDescription;
    @JsonProperty("retailLabelValue")
    private Integer retailLabelValue;
    @JsonProperty("handlingTemperature")
    private String handlingTemperature;
    @JsonProperty("handlingTemperatureDescription")
    private String handlingTemperatureDescription;
    @JsonProperty("handlingSensitivity")
    private String handlingSensitivity;
    @JsonProperty("handlingSensitivityDescription")
    private String handlingSensitivityDescription;
    @JsonProperty("catchWeightInd")
    private String catchWeightInd;
    @JsonProperty("catchWeightType")
    private String catchWeightType;
    @JsonProperty("catchWeightUom")
    private String catchWeightUom;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("saleType")
    private String saleType;
    @JsonProperty("wasteType")
    private String wasteType;
    @JsonProperty("wasteTypeDescription")
    private String wasteTypeDescription;
    @JsonProperty("averageWastePercentage")
    private Integer averageWastePercentage;
    @JsonProperty("defaultWastePercentage")
    private Integer defaultWastePercentage;
    @JsonProperty("constantDimensionInd")
    private String constantDimensionInd;
    @JsonProperty("packType")
    private String packType;
    @JsonProperty("orderAsType")
    private String orderAsType;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("itemServiceLevel")
    private String itemServiceLevel;
    @JsonProperty("giftWrapInd")
    private String giftWrapInd;
    @JsonProperty("shipAloneInd")
    private String shipAloneInd;
    @JsonProperty("brandName")
    private String brandName;
    @JsonProperty("brandDescription")
    private String brandDescription;
    @JsonProperty("perishableInd")
    private String perishableInd;
    @JsonProperty("itemTransformInd")
    private String itemTransformInd;
    @JsonProperty("depositItemType")
    private String depositItemType;
    @JsonProperty("containerItem")
    private String containerItem;
    @JsonProperty("depositInPricePerUom")
    private String depositInPricePerUom;
    @JsonProperty("storePackInventoryInd")
    private String storePackInventoryInd;
    @JsonProperty("sohInquiryAtPackInd")
    private String sohInquiryAtPackInd;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("productClassification")
    private String productClassification;
    @JsonProperty("productClassificationDescription")
    private String productClassificationDescription;
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
    @JsonProperty("diff1Level")
    private String diff1Level;
    @JsonProperty("diff1Description")
    private String diff1Description;
    @JsonProperty("diff2Level")
    private String diff2Level;
    @JsonProperty("diff2Description")
    private String diff2Description;
    @JsonProperty("diff3Level")
    private String diff3Level;
    @JsonProperty("diff3Description")
    private String diff3Description;
    @JsonProperty("diff4Level")
    private String diff4Level;
    @JsonProperty("diff4Description")
    private String diff4Description;
    @JsonProperty("primaryImageUrl")
    private String primaryImageUrl;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute = null;
    @JsonProperty("itemTranslation")
    private List<ItemTranslation> itemTranslation = null;
    @JsonProperty("itemSupplier")
    private List<ItemSupplier> itemSupplier = null;
    @JsonProperty("itemUda")
    private ItemUda itemUda;
    @JsonProperty("itemSeason")
    private List<ItemSeason> itemSeason;
    @JsonProperty("itemImage")
    private List<ItemImage> itemImage = null;
    @JsonProperty("referenceItem")
    private List<ReferenceItem> referenceItem = null;
    @JsonProperty("itemBOM")
    private List<ItemBOM> itemBOM = null;
    @JsonProperty("itemTicket")
    private List<ItemTicket> itemTicket = null;
    @JsonProperty("relatedItem")
    private List<RelatedItem> relatedItem = null;
    @JsonProperty("cacheTimestamp")
    private String cacheTimestamp;
    
    @JsonProperty("groupcode")
    private String groupCdoe;
    
    @JsonProperty("color")
    private String color;
    
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("itemNumberType")
    public String getItemNumberType() {
        return itemNumberType;
    }

    @JsonProperty("itemNumberType")
    public void setItemNumberType(String itemNumberType) {
        this.itemNumberType = itemNumberType;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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

    @JsonProperty("itemDescriptionUppercase")
    public String getItemDescriptionUppercase() {
        return itemDescriptionUppercase;
    }

    @JsonProperty("itemDescriptionUppercase")
    public void setItemDescriptionUppercase(String itemDescriptionUppercase) {
        this.itemDescriptionUppercase = itemDescriptionUppercase;
    }

    @JsonProperty("itemDescriptionSecondary")
    public String getItemDescriptionSecondary() {
        return itemDescriptionSecondary;
    }

    @JsonProperty("itemDescriptionSecondary")
    public void setItemDescriptionSecondary(String itemDescriptionSecondary) {
        this.itemDescriptionSecondary = itemDescriptionSecondary;
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

    @JsonProperty("inventoryInd")
    public String getInventoryInd() {
        return inventoryInd;
    }

    @JsonProperty("inventoryInd")
    public void setInventoryInd(String inventoryInd) {
        this.inventoryInd = inventoryInd;
    }

    @JsonProperty("packInd")
    public String getPackInd() {
        return packInd;
    }

    @JsonProperty("packInd")
    public void setPackInd(String packInd) {
        this.packInd = packInd;
    }

    @JsonProperty("simplePackInd")
    public String getSimplePackInd() {
        return simplePackInd;
    }

    @JsonProperty("simplePackInd")
    public void setSimplePackInd(String simplePackInd) {
        this.simplePackInd = simplePackInd;
    }

    @JsonProperty("containsInnerInd")
    public String getContainsInnerInd() {
        return containsInnerInd;
    }

    @JsonProperty("containsInnerInd")
    public void setContainsInnerInd(String containsInnerInd) {
        this.containsInnerInd = containsInnerInd;
    }

    @JsonProperty("dept")
    public Integer getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(Integer dept) {
        this.dept = dept;
    }

    @JsonProperty("deptName")
    public String getDeptName() {
        return deptName;
    }

    @JsonProperty("deptName")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonProperty("class")
    public Integer getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    @JsonProperty("className")
    public String getClassName() {
        return className;
    }

    @JsonProperty("className")
    public void setClassName(String className) {
        this.className = className;
    }

    @JsonProperty("uniqueClassId")
    public Integer getUniqueClassId() {
        return uniqueClassId;
    }

    @JsonProperty("uniqueClassId")
    public void setUniqueClassId(Integer uniqueClassId) {
        this.uniqueClassId = uniqueClassId;
    }

    @JsonProperty("subclass")
    public Integer getSubclass() {
        return subclass;
    }

    @JsonProperty("subclass")
    public void setSubclass(Integer subclass) {
        this.subclass = subclass;
    }

    @JsonProperty("subclassName")
    public String getSubclassName() {
        return subclassName;
    }

    @JsonProperty("subclassName")
    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    @JsonProperty("uniqueSubclassId")
    public Integer getUniqueSubclassId() {
        return uniqueSubclassId;
    }

    @JsonProperty("uniqueSubclassId")
    public void setUniqueSubclassId(Integer uniqueSubclassId) {
        this.uniqueSubclassId = uniqueSubclassId;
    }

    @JsonProperty("unitRetail")
    public Integer getUnitRetail() {
        return unitRetail;
    }

    @JsonProperty("unitRetail")
    public void setUnitRetail(Integer unitRetail) {
        this.unitRetail = unitRetail;
    }

    @JsonProperty("variableUpcFormatId")
    public String getVariableUpcFormatId() {
        return variableUpcFormatId;
    }

    @JsonProperty("variableUpcFormatId")
    public void setVariableUpcFormatId(String variableUpcFormatId) {
        this.variableUpcFormatId = variableUpcFormatId;
    }

    @JsonProperty("variableWeightUpcPrefix")
    public Integer getVariableWeightUpcPrefix() {
        return variableWeightUpcPrefix;
    }

    @JsonProperty("variableWeightUpcPrefix")
    public void setVariableWeightUpcPrefix(Integer variableWeightUpcPrefix) {
        this.variableWeightUpcPrefix = variableWeightUpcPrefix;
    }

    @JsonProperty("diff1")
    public String getDiff1() {
        return diff1;
    }

    @JsonProperty("diff1")
    public void setDiff1(String diff1) {
        this.diff1 = diff1;
    }

    @JsonProperty("diff1Type")
    public String getDiff1Type() {
        return diff1Type;
    }

    @JsonProperty("diff1Type")
    public void setDiff1Type(String diff1Type) {
        this.diff1Type = diff1Type;
    }

    @JsonProperty("diff2")
    public String getDiff2() {
        return diff2;
    }

    @JsonProperty("diff2")
    public void setDiff2(String diff2) {
        this.diff2 = diff2;
    }

    @JsonProperty("diff2Type")
    public String getDiff2Type() {
        return diff2Type;
    }

    @JsonProperty("diff2Type")
    public void setDiff2Type(String diff2Type) {
        this.diff2Type = diff2Type;
    }

    @JsonProperty("diff3")
    public String getDiff3() {
        return diff3;
    }

    @JsonProperty("diff3")
    public void setDiff3(String diff3) {
        this.diff3 = diff3;
    }

    @JsonProperty("diff3Type")
    public String getDiff3Type() {
        return diff3Type;
    }

    @JsonProperty("diff3Type")
    public void setDiff3Type(String diff3Type) {
        this.diff3Type = diff3Type;
    }

    @JsonProperty("diff4")
    public String getDiff4() {
        return diff4;
    }

    @JsonProperty("diff4")
    public void setDiff4(String diff4) {
        this.diff4 = diff4;
    }

    @JsonProperty("diff4Type")
    public String getDiff4Type() {
        return diff4Type;
    }

    @JsonProperty("diff4Type")
    public void setDiff4Type(String diff4Type) {
        this.diff4Type = diff4Type;
    }

    @JsonProperty("costZoneGroupId")
    public Integer getCostZoneGroupId() {
        return costZoneGroupId;
    }

    @JsonProperty("costZoneGroupId")
    public void setCostZoneGroupId(Integer costZoneGroupId) {
        this.costZoneGroupId = costZoneGroupId;
    }

    @JsonProperty("standardUom")
    public String getStandardUom() {
        return standardUom;
    }

    @JsonProperty("standardUom")
    public void setStandardUom(String standardUom) {
        this.standardUom = standardUom;
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

    @JsonProperty("packageUom")
    public String getPackageUom() {
        return packageUom;
    }

    @JsonProperty("packageUom")
    public void setPackageUom(String packageUom) {
        this.packageUom = packageUom;
    }

    @JsonProperty("merchandiseInd")
    public String getMerchandiseInd() {
        return merchandiseInd;
    }

    @JsonProperty("merchandiseInd")
    public void setMerchandiseInd(String merchandiseInd) {
        this.merchandiseInd = merchandiseInd;
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

    @JsonProperty("manufacturerRecommendedRetail")
    public Integer getManufacturerRecommendedRetail() {
        return manufacturerRecommendedRetail;
    }

    @JsonProperty("manufacturerRecommendedRetail")
    public void setManufacturerRecommendedRetail(Integer manufacturerRecommendedRetail) {
        this.manufacturerRecommendedRetail = manufacturerRecommendedRetail;
    }

    @JsonProperty("manufacturerRetailCurrencyCode")
    public String getManufacturerRetailCurrencyCode() {
        return manufacturerRetailCurrencyCode;
    }

    @JsonProperty("manufacturerRetailCurrencyCode")
    public void setManufacturerRetailCurrencyCode(String manufacturerRetailCurrencyCode) {
        this.manufacturerRetailCurrencyCode = manufacturerRetailCurrencyCode;
    }

    @JsonProperty("originalRetail")
    public Integer getOriginalRetail() {
        return originalRetail;
    }

    @JsonProperty("originalRetail")
    public void setOriginalRetail(Integer originalRetail) {
        this.originalRetail = originalRetail;
    }

    @JsonProperty("originalRetailCurrencyCode")
    public String getOriginalRetailCurrencyCode() {
        return originalRetailCurrencyCode;
    }

    @JsonProperty("originalRetailCurrencyCode")
    public void setOriginalRetailCurrencyCode(String originalRetailCurrencyCode) {
        this.originalRetailCurrencyCode = originalRetailCurrencyCode;
    }

    @JsonProperty("retailLabelType")
    public String getRetailLabelType() {
        return retailLabelType;
    }

    @JsonProperty("retailLabelType")
    public void setRetailLabelType(String retailLabelType) {
        this.retailLabelType = retailLabelType;
    }

    @JsonProperty("retailLabelTypeDescription")
    public String getRetailLabelTypeDescription() {
        return retailLabelTypeDescription;
    }

    @JsonProperty("retailLabelTypeDescription")
    public void setRetailLabelTypeDescription(String retailLabelTypeDescription) {
        this.retailLabelTypeDescription = retailLabelTypeDescription;
    }

    @JsonProperty("retailLabelValue")
    public Integer getRetailLabelValue() {
        return retailLabelValue;
    }

    @JsonProperty("retailLabelValue")
    public void setRetailLabelValue(Integer retailLabelValue) {
        this.retailLabelValue = retailLabelValue;
    }

    @JsonProperty("handlingTemperature")
    public String getHandlingTemperature() {
        return handlingTemperature;
    }

    @JsonProperty("handlingTemperature")
    public void setHandlingTemperature(String handlingTemperature) {
        this.handlingTemperature = handlingTemperature;
    }

    @JsonProperty("handlingTemperatureDescription")
    public String getHandlingTemperatureDescription() {
        return handlingTemperatureDescription;
    }

    @JsonProperty("handlingTemperatureDescription")
    public void setHandlingTemperatureDescription(String handlingTemperatureDescription) {
        this.handlingTemperatureDescription = handlingTemperatureDescription;
    }

    @JsonProperty("handlingSensitivity")
    public String getHandlingSensitivity() {
        return handlingSensitivity;
    }

    @JsonProperty("handlingSensitivity")
    public void setHandlingSensitivity(String handlingSensitivity) {
        this.handlingSensitivity = handlingSensitivity;
    }

    @JsonProperty("handlingSensitivityDescription")
    public String getHandlingSensitivityDescription() {
        return handlingSensitivityDescription;
    }

    @JsonProperty("handlingSensitivityDescription")
    public void setHandlingSensitivityDescription(String handlingSensitivityDescription) {
        this.handlingSensitivityDescription = handlingSensitivityDescription;
    }

    @JsonProperty("catchWeightInd")
    public String getCatchWeightInd() {
        return catchWeightInd;
    }

    @JsonProperty("catchWeightInd")
    public void setCatchWeightInd(String catchWeightInd) {
        this.catchWeightInd = catchWeightInd;
    }

    @JsonProperty("catchWeightType")
    public String getCatchWeightType() {
        return catchWeightType;
    }

    @JsonProperty("catchWeightType")
    public void setCatchWeightType(String catchWeightType) {
        this.catchWeightType = catchWeightType;
    }

    @JsonProperty("catchWeightUom")
    public String getCatchWeightUom() {
        return catchWeightUom;
    }

    @JsonProperty("catchWeightUom")
    public void setCatchWeightUom(String catchWeightUom) {
        this.catchWeightUom = catchWeightUom;
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

    @JsonProperty("wasteType")
    public String getWasteType() {
        return wasteType;
    }

    @JsonProperty("wasteType")
    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    @JsonProperty("wasteTypeDescription")
    public String getWasteTypeDescription() {
        return wasteTypeDescription;
    }

    @JsonProperty("wasteTypeDescription")
    public void setWasteTypeDescription(String wasteTypeDescription) {
        this.wasteTypeDescription = wasteTypeDescription;
    }

    @JsonProperty("averageWastePercentage")
    public Integer getAverageWastePercentage() {
        return averageWastePercentage;
    }

    @JsonProperty("averageWastePercentage")
    public void setAverageWastePercentage(Integer averageWastePercentage) {
        this.averageWastePercentage = averageWastePercentage;
    }

    @JsonProperty("defaultWastePercentage")
    public Integer getDefaultWastePercentage() {
        return defaultWastePercentage;
    }

    @JsonProperty("defaultWastePercentage")
    public void setDefaultWastePercentage(Integer defaultWastePercentage) {
        this.defaultWastePercentage = defaultWastePercentage;
    }

    @JsonProperty("constantDimensionInd")
    public String getConstantDimensionInd() {
        return constantDimensionInd;
    }

    @JsonProperty("constantDimensionInd")
    public void setConstantDimensionInd(String constantDimensionInd) {
        this.constantDimensionInd = constantDimensionInd;
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

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("itemServiceLevel")
    public String getItemServiceLevel() {
        return itemServiceLevel;
    }

    @JsonProperty("itemServiceLevel")
    public void setItemServiceLevel(String itemServiceLevel) {
        this.itemServiceLevel = itemServiceLevel;
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

    @JsonProperty("brandName")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brandName")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("brandDescription")
    public String getBrandDescription() {
        return brandDescription;
    }

    @JsonProperty("brandDescription")
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    @JsonProperty("perishableInd")
    public String getPerishableInd() {
        return perishableInd;
    }

    @JsonProperty("perishableInd")
    public void setPerishableInd(String perishableInd) {
        this.perishableInd = perishableInd;
    }

    @JsonProperty("itemTransformInd")
    public String getItemTransformInd() {
        return itemTransformInd;
    }

    @JsonProperty("itemTransformInd")
    public void setItemTransformInd(String itemTransformInd) {
        this.itemTransformInd = itemTransformInd;
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

    @JsonProperty("depositInPricePerUom")
    public String getDepositInPricePerUom() {
        return depositInPricePerUom;
    }

    @JsonProperty("depositInPricePerUom")
    public void setDepositInPricePerUom(String depositInPricePerUom) {
        this.depositInPricePerUom = depositInPricePerUom;
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

    @JsonProperty("purchaseType")
    public String getPurchaseType() {
        return purchaseType;
    }

    @JsonProperty("purchaseType")
    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @JsonProperty("productClassification")
    public String getProductClassification() {
        return productClassification;
    }

    @JsonProperty("productClassification")
    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
    }

    @JsonProperty("productClassificationDescription")
    public String getProductClassificationDescription() {
        return productClassificationDescription;
    }

    @JsonProperty("productClassificationDescription")
    public void setProductClassificationDescription(String productClassificationDescription) {
        this.productClassificationDescription = productClassificationDescription;
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

    @JsonProperty("diff1Level")
    public String getDiff1Level() {
        return diff1Level;
    }

    @JsonProperty("diff1Level")
    public void setDiff1Level(String diff1Level) {
        this.diff1Level = diff1Level;
    }

    @JsonProperty("diff1Description")
    public String getDiff1Description() {
        return diff1Description;
    }

    @JsonProperty("diff1Description")
    public void setDiff1Description(String diff1Description) {
        this.diff1Description = diff1Description;
    }

    @JsonProperty("diff2Level")
    public String getDiff2Level() {
        return diff2Level;
    }

    @JsonProperty("diff2Level")
    public void setDiff2Level(String diff2Level) {
        this.diff2Level = diff2Level;
    }

    @JsonProperty("diff2Description")
    public String getDiff2Description() {
        return diff2Description;
    }

    @JsonProperty("diff2Description")
    public void setDiff2Description(String diff2Description) {
        this.diff2Description = diff2Description;
    }

    @JsonProperty("diff3Level")
    public String getDiff3Level() {
        return diff3Level;
    }

    @JsonProperty("diff3Level")
    public void setDiff3Level(String diff3Level) {
        this.diff3Level = diff3Level;
    }

    @JsonProperty("diff3Description")
    public String getDiff3Description() {
        return diff3Description;
    }

    @JsonProperty("diff3Description")
    public void setDiff3Description(String diff3Description) {
        this.diff3Description = diff3Description;
    }

    @JsonProperty("diff4Level")
    public String getDiff4Level() {
        return diff4Level;
    }

    @JsonProperty("diff4Level")
    public void setDiff4Level(String diff4Level) {
        this.diff4Level = diff4Level;
    }

    @JsonProperty("diff4Description")
    public String getDiff4Description() {
        return diff4Description;
    }

    @JsonProperty("diff4Description")
    public void setDiff4Description(String diff4Description) {
        this.diff4Description = diff4Description;
    }

    @JsonProperty("primaryImageUrl")
    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    @JsonProperty("primaryImageUrl")
    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
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
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("itemTranslation")
    public List<ItemTranslation> getItemTranslation() {
        return itemTranslation;
    }

    @JsonProperty("itemTranslation")
    public void setItemTranslation(List<ItemTranslation> itemTranslation) {
        this.itemTranslation = itemTranslation;
    }

    @JsonProperty("itemSupplier")
    public List<ItemSupplier> getItemSupplier() {
        return itemSupplier;
    }

    @JsonProperty("itemSupplier")
    public void setItemSupplier(List<ItemSupplier> itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    @JsonProperty("itemUda")
    public ItemUda getItemUda() {
        return itemUda;
    }

    @JsonProperty("itemUda")
    public void setItemUda(ItemUda itemUda) {
        this.itemUda = itemUda;
    }

    @JsonProperty("itemImage")
    public List<ItemImage> getItemImage() {
        return itemImage;
    }

    @JsonProperty("itemImage")
    public void setItemImage(List<ItemImage> itemImage) {
        this.itemImage = itemImage;
    }

    @JsonProperty("referenceItem")
    public List<ReferenceItem> getReferenceItem() {
        return referenceItem;
    }

    @JsonProperty("referenceItem")
    public void setReferenceItem(List<ReferenceItem> referenceItem) {
        this.referenceItem = referenceItem;
    }

    @JsonProperty("itemBOM")
    public List<ItemBOM> getItemBOM() {
        return itemBOM;
    }

    @JsonProperty("itemBOM")
    public void setItemBOM(List<ItemBOM> itemBOM) {
        this.itemBOM = itemBOM;
    }

    @JsonProperty("itemTicket")
    public List<ItemTicket> getItemTicket() {
        return itemTicket;
    }

    @JsonProperty("itemTicket")
    public void setItemTicket(List<ItemTicket> itemTicket) {
        this.itemTicket = itemTicket;
    }

    @JsonProperty("relatedItem")
    public List<RelatedItem> getRelatedItem() {
        return relatedItem;
    }

    @JsonProperty("relatedItem")
    public void setRelatedItem(List<RelatedItem> relatedItem) {
        this.relatedItem = relatedItem;
    }

    @JsonProperty("cacheTimestamp")
    public String getCacheTimestamp() {
        return cacheTimestamp;
    }

    @JsonProperty("cacheTimestamp")
    public void setCacheTimestamp(String cacheTimestamp) {
        this.cacheTimestamp = cacheTimestamp;
    }
    
    @JsonProperty("itemSeason")
    public List<ItemSeason> getItemSeason() {
        return itemSeason;
    }

    @JsonProperty("itemSeason")
    public void setItemSeason(List<ItemSeason> itemSeason) {
        this.itemSeason = itemSeason;
    }
    
    @JsonProperty("groupcode")
	public String getGroupCdoe() {
		return groupCdoe;
	}
    
	@JsonProperty("groupcode")
	public void setGroupCdoe(String groupCdoe) {
		this.groupCdoe = groupCdoe;
	}

	@JsonProperty("color")
	public String getColor() {
		return color;
	}

	@JsonProperty("color")
	public void setColor(String color) {
		this.color = color;
	}
	
}
