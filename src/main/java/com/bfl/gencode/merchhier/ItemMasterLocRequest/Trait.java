package com.bfl.gencode.merchhier.ItemMasterLocRequest;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "launchDate",
    "quantityKeyOptions",
    "manualPriceEntry",
    "depositCode",
    "foodStampInd",
    "wicInd",
    "proportionalTarePercent",
    "fixedTareValue",
    "fixedTareUom",
    "rewardEligibleInd",
    "nationalBrandCompetitorItem",
    "returnPolicy",
    "stopSaleInd",
    "electronicMarketClubs",
    "reportCode",
    "requiredShelfLifeOnSelection",
    "requiredShelfLifeOnReceipt",
    "investmentBuyShelfLife",
    "storeReorderableInd",
    "rackSize",
    "fullPalletItem",
    "inStoreMarketBasket",
    "storageLocation",
    "alternateStorageLocation",
    "returnableInd",
    "refundableInd",
    "backOrderInd"
})

public class Trait implements Serializable {
	
    @JsonProperty("launchDate")
    private String launchDate;
    @JsonProperty("quantityKeyOptions")
    private String quantityKeyOptions;
    @JsonProperty("manualPriceEntry")
    private String manualPriceEntry;
    @JsonProperty("depositCode")
    private String depositCode;
    @JsonProperty("foodStampInd")
    private String foodStampInd;
    @JsonProperty("wicInd")
    private String wicInd;
    @JsonProperty("proportionalTarePercent")
    private Integer proportionalTarePercent;
    @JsonProperty("fixedTareValue")
    private Integer fixedTareValue;
    @JsonProperty("fixedTareUom")
    private String fixedTareUom;
    @JsonProperty("rewardEligibleInd")
    private String rewardEligibleInd;
    @JsonProperty("nationalBrandCompetitorItem")
    private String nationalBrandCompetitorItem;
    @JsonProperty("returnPolicy")
    private String returnPolicy;
    @JsonProperty("stopSaleInd")
    private String stopSaleInd;
    @JsonProperty("electronicMarketClubs")
    private String electronicMarketClubs;
    @JsonProperty("reportCode")
    private String reportCode;
    @JsonProperty("requiredShelfLifeOnSelection")
    private String requiredShelfLifeOnSelection;
    @JsonProperty("requiredShelfLifeOnReceipt")
    private String requiredShelfLifeOnReceipt;
    @JsonProperty("investmentBuyShelfLife")
    private String investmentBuyShelfLife;
    @JsonProperty("storeReorderableInd")
    private String storeReorderableInd;
    @JsonProperty("rackSize")
    private String rackSize;
    @JsonProperty("fullPalletItem")
    private String fullPalletItem;
    @JsonProperty("inStoreMarketBasket")
    private String inStoreMarketBasket;
    @JsonProperty("storageLocation")
    private String storageLocation;
    @JsonProperty("alternateStorageLocation")
    private String alternateStorageLocation;
    @JsonProperty("returnableInd")
    private String returnableInd;
    @JsonProperty("refundableInd")
    private String refundableInd;
    @JsonProperty("backOrderInd")
    private String backOrderInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 5171367903900492279L;

    @JsonProperty("launchDate")
    public String getLaunchDate() {
        return launchDate;
    }

    @JsonProperty("launchDate")
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    @JsonProperty("quantityKeyOptions")
    public String getQuantityKeyOptions() {
        return quantityKeyOptions;
    }

    @JsonProperty("quantityKeyOptions")
    public void setQuantityKeyOptions(String quantityKeyOptions) {
        this.quantityKeyOptions = quantityKeyOptions;
    }

    @JsonProperty("manualPriceEntry")
    public String getManualPriceEntry() {
        return manualPriceEntry;
    }

    @JsonProperty("manualPriceEntry")
    public void setManualPriceEntry(String manualPriceEntry) {
        this.manualPriceEntry = manualPriceEntry;
    }

    @JsonProperty("depositCode")
    public String getDepositCode() {
        return depositCode;
    }

    @JsonProperty("depositCode")
    public void setDepositCode(String depositCode) {
        this.depositCode = depositCode;
    }

    @JsonProperty("foodStampInd")
    public String getFoodStampInd() {
        return foodStampInd;
    }

    @JsonProperty("foodStampInd")
    public void setFoodStampInd(String foodStampInd) {
        this.foodStampInd = foodStampInd;
    }

    @JsonProperty("wicInd")
    public String getWicInd() {
        return wicInd;
    }

    @JsonProperty("wicInd")
    public void setWicInd(String wicInd) {
        this.wicInd = wicInd;
    }

    @JsonProperty("proportionalTarePercent")
    public Integer getProportionalTarePercent() {
        return proportionalTarePercent;
    }

    @JsonProperty("proportionalTarePercent")
    public void setProportionalTarePercent(Integer proportionalTarePercent) {
        this.proportionalTarePercent = proportionalTarePercent;
    }

    @JsonProperty("fixedTareValue")
    public Integer getFixedTareValue() {
        return fixedTareValue;
    }

    @JsonProperty("fixedTareValue")
    public void setFixedTareValue(Integer fixedTareValue) {
        this.fixedTareValue = fixedTareValue;
    }

    @JsonProperty("fixedTareUom")
    public String getFixedTareUom() {
        return fixedTareUom;
    }

    @JsonProperty("fixedTareUom")
    public void setFixedTareUom(String fixedTareUom) {
        this.fixedTareUom = fixedTareUom;
    }

    @JsonProperty("rewardEligibleInd")
    public String getRewardEligibleInd() {
        return rewardEligibleInd;
    }

    @JsonProperty("rewardEligibleInd")
    public void setRewardEligibleInd(String rewardEligibleInd) {
        this.rewardEligibleInd = rewardEligibleInd;
    }

    @JsonProperty("nationalBrandCompetitorItem")
    public String getNationalBrandCompetitorItem() {
        return nationalBrandCompetitorItem;
    }

    @JsonProperty("nationalBrandCompetitorItem")
    public void setNationalBrandCompetitorItem(String nationalBrandCompetitorItem) {
        this.nationalBrandCompetitorItem = nationalBrandCompetitorItem;
    }

    @JsonProperty("returnPolicy")
    public String getReturnPolicy() {
        return returnPolicy;
    }

    @JsonProperty("returnPolicy")
    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    @JsonProperty("stopSaleInd")
    public String getStopSaleInd() {
        return stopSaleInd;
    }

    @JsonProperty("stopSaleInd")
    public void setStopSaleInd(String stopSaleInd) {
        this.stopSaleInd = stopSaleInd;
    }

    @JsonProperty("electronicMarketClubs")
    public String getElectronicMarketClubs() {
        return electronicMarketClubs;
    }

    @JsonProperty("electronicMarketClubs")
    public void setElectronicMarketClubs(String electronicMarketClubs) {
        this.electronicMarketClubs = electronicMarketClubs;
    }

    @JsonProperty("reportCode")
    public String getReportCode() {
        return reportCode;
    }

    @JsonProperty("reportCode")
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    @JsonProperty("requiredShelfLifeOnSelection")
    public String getRequiredShelfLifeOnSelection() {
        return requiredShelfLifeOnSelection;
    }

    @JsonProperty("requiredShelfLifeOnSelection")
    public void setRequiredShelfLifeOnSelection(String requiredShelfLifeOnSelection) {
        this.requiredShelfLifeOnSelection = requiredShelfLifeOnSelection;
    }

    @JsonProperty("requiredShelfLifeOnReceipt")
    public String getRequiredShelfLifeOnReceipt() {
        return requiredShelfLifeOnReceipt;
    }

    @JsonProperty("requiredShelfLifeOnReceipt")
    public void setRequiredShelfLifeOnReceipt(String requiredShelfLifeOnReceipt) {
        this.requiredShelfLifeOnReceipt = requiredShelfLifeOnReceipt;
    }

    @JsonProperty("investmentBuyShelfLife")
    public String getInvestmentBuyShelfLife() {
        return investmentBuyShelfLife;
    }

    @JsonProperty("investmentBuyShelfLife")
    public void setInvestmentBuyShelfLife(String investmentBuyShelfLife) {
        this.investmentBuyShelfLife = investmentBuyShelfLife;
    }

    @JsonProperty("storeReorderableInd")
    public String getStoreReorderableInd() {
        return storeReorderableInd;
    }

    @JsonProperty("storeReorderableInd")
    public void setStoreReorderableInd(String storeReorderableInd) {
        this.storeReorderableInd = storeReorderableInd;
    }

    @JsonProperty("rackSize")
    public String getRackSize() {
        return rackSize;
    }

    @JsonProperty("rackSize")
    public void setRackSize(String rackSize) {
        this.rackSize = rackSize;
    }

    @JsonProperty("fullPalletItem")
    public String getFullPalletItem() {
        return fullPalletItem;
    }

    @JsonProperty("fullPalletItem")
    public void setFullPalletItem(String fullPalletItem) {
        this.fullPalletItem = fullPalletItem;
    }

    @JsonProperty("inStoreMarketBasket")
    public String getInStoreMarketBasket() {
        return inStoreMarketBasket;
    }

    @JsonProperty("inStoreMarketBasket")
    public void setInStoreMarketBasket(String inStoreMarketBasket) {
        this.inStoreMarketBasket = inStoreMarketBasket;
    }

    @JsonProperty("storageLocation")
    public String getStorageLocation() {
        return storageLocation;
    }

    @JsonProperty("storageLocation")
    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    @JsonProperty("alternateStorageLocation")
    public String getAlternateStorageLocation() {
        return alternateStorageLocation;
    }

    @JsonProperty("alternateStorageLocation")
    public void setAlternateStorageLocation(String alternateStorageLocation) {
        this.alternateStorageLocation = alternateStorageLocation;
    }

    @JsonProperty("returnableInd")
    public String getReturnableInd() {
        return returnableInd;
    }

    @JsonProperty("returnableInd")
    public void setReturnableInd(String returnableInd) {
        this.returnableInd = returnableInd;
    }

    @JsonProperty("refundableInd")
    public String getRefundableInd() {
        return refundableInd;
    }

    @JsonProperty("refundableInd")
    public void setRefundableInd(String refundableInd) {
        this.refundableInd = refundableInd;
    }

    @JsonProperty("backOrderInd")
    public String getBackOrderInd() {
        return backOrderInd;
    }

    @JsonProperty("backOrderInd")
    public void setBackOrderInd(String backOrderInd) {
        this.backOrderInd = backOrderInd;
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
