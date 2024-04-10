
package com.bfl.gencode.apex.requests;

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
    "EntryNo",
    "EntryDate",
    "EntryType",
    "CostCode",
    "SubLocCode",
    "Remarks",
    "Delivery",
    "TrfNo1",
    "TrfNo2",
    "LabelType",
    "AgeingOther",
    "Shopname",
    "UserId",
    "currentDateTime"
})
@Generated("jsonschema2pojo")
public class PriceHeadAgeingRequest {

    @JsonProperty("EntryNo")
    private String entryNo;
    @JsonProperty("EntryDate")
    private String entryDate;
    @JsonProperty("EntryType")
    private String entryType;
    @JsonProperty("CostCode")
    private String costCode;
    @JsonProperty("SubLocCode")
    private String subLocCode;
    @JsonProperty("Remarks")
    private String remarks;
    @JsonProperty("Delivery")
    private String delivery;
    @JsonProperty("TrfNo1")
    private String trfNo1;
    @JsonProperty("TrfNo2")
    private String trfNo2;
    @JsonProperty("LabelType")
    private String labelType;
    @JsonProperty("AgeingOther")
    private String ageingOther;
    @JsonProperty("Shopname")
    private String shopname;
    @JsonProperty("UserId")
    private String userId;
    @JsonProperty("currentDateTime")
    private String currentDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("EntryNo")
    public String getEntryNo() {
        return entryNo;
    }

    @JsonProperty("EntryNo")
    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    @JsonProperty("EntryDate")
    public String getEntryDate() {
        return entryDate;
    }

    @JsonProperty("EntryDate")
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @JsonProperty("EntryType")
    public String getEntryType() {
        return entryType;
    }

    @JsonProperty("EntryType")
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    @JsonProperty("CostCode")
    public String getCostCode() {
        return costCode;
    }

    @JsonProperty("CostCode")
    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    @JsonProperty("SubLocCode")
    public String getSubLocCode() {
        return subLocCode;
    }

    @JsonProperty("SubLocCode")
    public void setSubLocCode(String subLocCode) {
        this.subLocCode = subLocCode;
    }

    @JsonProperty("Remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("Remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("Delivery")
    public String getDelivery() {
        return delivery;
    }

    @JsonProperty("Delivery")
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("TrfNo1")
    public String getTrfNo1() {
        return trfNo1;
    }

    @JsonProperty("TrfNo1")
    public void setTrfNo1(String trfNo1) {
        this.trfNo1 = trfNo1;
    }

    @JsonProperty("TrfNo2")
    public String getTrfNo2() {
        return trfNo2;
    }

    @JsonProperty("TrfNo2")
    public void setTrfNo2(String trfNo2) {
        this.trfNo2 = trfNo2;
    }

    @JsonProperty("LabelType")
    public String getLabelType() {
        return labelType;
    }

    @JsonProperty("LabelType")
    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    @JsonProperty("AgeingOther")
    public String getAgeingOther() {
        return ageingOther;
    }

    @JsonProperty("AgeingOther")
    public void setAgeingOther(String ageingOther) {
        this.ageingOther = ageingOther;
    }

    @JsonProperty("Shopname")
    public String getShopname() {
        return shopname;
    }

    @JsonProperty("Shopname")
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @JsonProperty("UserId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("UserId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("currentDateTime")
    public String getCurrentDateTime() {
        return currentDateTime;
    }

    @JsonProperty("currentDateTime")
    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
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
