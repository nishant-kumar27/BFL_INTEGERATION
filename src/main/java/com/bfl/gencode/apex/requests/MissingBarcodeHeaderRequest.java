package com.bfl.gencode.apex.requests;

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
    "entryNo",
    "remarks",
    "preparedBy",
    "shopManager",
    "barcodePrintDate",
    "dispatchDate",
    "receivedBy",
    "receivedDate",
    "userId",
    "loccode",
    "shopname",
    "trndate",
    "time1",
    "costcode"
})

public class MissingBarcodeHeaderRequest {

    @JsonProperty("entryNo")
    private String entryNo;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("preparedBy")
    private String preparedBy;
    @JsonProperty("shopManager")
    private String shopManager;
    @JsonProperty("barcodePrintDate")
    private Object barcodePrintDate;
    @JsonProperty("dispatchDate")
    private Object dispatchDate;
    @JsonProperty("receivedBy")
    private String receivedBy;
    @JsonProperty("receivedDate")
    private String receivedDate;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("loccode")
    private String loccode;
    @JsonProperty("shopname")
    private String shopname;
    @JsonProperty("trndate")
    private String trndate;
    @JsonProperty("time1")
    private String time1;
    @JsonProperty("costcode")
    private String costcode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entryNo")
    public String getEntryNo() {
        return entryNo;
    }

    @JsonProperty("entryNo")
    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    @JsonProperty("remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("preparedBy")
    public String getPreparedBy() {
        return preparedBy;
    }

    @JsonProperty("preparedBy")
    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    @JsonProperty("shopManager")
    public String getShopManager() {
        return shopManager;
    }

    @JsonProperty("shopManager")
    public void setShopManager(String shopManager) {
        this.shopManager = shopManager;
    }

    @JsonProperty("barcodePrintDate")
    public Object getBarcodePrintDate() {
        return barcodePrintDate;
    }

    @JsonProperty("barcodePrintDate")
    public void setBarcodePrintDate(Object barcodePrintDate) {
        this.barcodePrintDate = barcodePrintDate;
    }

    @JsonProperty("dispatchDate")
    public Object getDispatchDate() {
        return dispatchDate;
    }

    @JsonProperty("dispatchDate")
    public void setDispatchDate(Object dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    @JsonProperty("receivedBy")
    public String getReceivedBy() {
        return receivedBy;
    }

    @JsonProperty("receivedBy")
    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    @JsonProperty("receivedDate")
    public String getReceivedDate() {
        return receivedDate;
    }

    @JsonProperty("receivedDate")
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("loccode")
    public String getLoccode() {
        return loccode;
    }

    @JsonProperty("loccode")
    public void setLoccode(String loccode) {
        this.loccode = loccode;
    }

    @JsonProperty("shopname")
    public String getShopname() {
        return shopname;
    }

    @JsonProperty("shopname")
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @JsonProperty("trndate")
    public String getTrndate() {
        return trndate;
    }

    @JsonProperty("trndate")
    public void setTrndate(String trndate) {
        this.trndate = trndate;
    }

    @JsonProperty("time1")
    public String getTime1() {
        return time1;
    }

    @JsonProperty("time1")
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    @JsonProperty("costcode")
    public String getCostcode() {
        return costcode;
    }

    @JsonProperty("costcode")
    public void setCostcode(String costcode) {
        this.costcode = costcode;
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
