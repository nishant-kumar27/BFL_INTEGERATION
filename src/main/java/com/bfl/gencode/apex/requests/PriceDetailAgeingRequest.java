
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
    "ItemCode",
    "Quantity",
    "salesPrice",
    "NewPrice",
    "TrfNo",
    "TrfDate",
    "IDNo",
    "Status",
    "RFID",
    "UserId",
    "currentDate"
})
@Generated("jsonschema2pojo")
public class PriceDetailAgeingRequest {

    @JsonProperty("EntryNo")
    private String entryNo;
    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("salesPrice")
    private String salesPrice;
    @JsonProperty("NewPrice")
    private String newPrice;
    @JsonProperty("TrfNo")
    private String trfNo;
    @JsonProperty("TrfDate")
    private String trfDate;
    @JsonProperty("IDNo")
    private String iDNo;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("RFID")
    private String rfid;
    @JsonProperty("UserId")
    private String userId;
    @JsonProperty("currentDate")
    private String currentDate;
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

    @JsonProperty("ItemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("ItemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("Quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("Quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("salesPrice")
    public String getSalesPrice() {
        return salesPrice;
    }

    @JsonProperty("salesPrice")
    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    @JsonProperty("NewPrice")
    public String getNewPrice() {
        return newPrice;
    }

    @JsonProperty("NewPrice")
    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    @JsonProperty("TrfNo")
    public String getTrfNo() {
        return trfNo;
    }

    @JsonProperty("TrfNo")
    public void setTrfNo(String trfNo) {
        this.trfNo = trfNo;
    }

    @JsonProperty("TrfDate")
    public String getTrfDate() {
        return trfDate;
    }

    @JsonProperty("TrfDate")
    public void setTrfDate(String trfDate) {
        this.trfDate = trfDate;
    }

    @JsonProperty("IDNo")
    public String getIDNo() {
        return iDNo;
    }

    @JsonProperty("IDNo")
    public void setIDNo(String iDNo) {
        this.iDNo = iDNo;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("RFID")
    public String getRfid() {
        return rfid;
    }

    @JsonProperty("RFID")
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @JsonProperty("UserId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("UserId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("currentDate")
    public String getCurrentDate() {
        return currentDate;
    }

    @JsonProperty("currentDate")
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
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
