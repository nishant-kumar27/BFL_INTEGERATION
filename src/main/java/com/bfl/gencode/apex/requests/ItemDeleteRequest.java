
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
    "TrnDate",
    "Time1",
    "Invoiceno",
    "ItemCode",
    "qty",
    "price",
    "userId",
    "compName",
    "approvedBy",
    "reason",
    "costCode",
    "username"
})
@Generated("jsonschema2pojo")
public class ItemDeleteRequest {

    @JsonProperty("TrnDate")
    private String trnDate;
    @JsonProperty("Time1")
    private String time1;
    @JsonProperty("Invoiceno")
    private String invoiceno;
    @JsonProperty("ItemCode")
    private String itemCode;
    @JsonProperty("qty")
    private String qty;
    @JsonProperty("price")
    private String price;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("compName")
    private String compName;
    @JsonProperty("approvedBy")
    private String approvedBy;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("costCode")
    private String costCode;
    @JsonProperty("username")
    private String username;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("TrnDate")
    public String getTrnDate() {
        return trnDate;
    }

    @JsonProperty("TrnDate")
    public void setTrnDate(String trnDate) {
        this.trnDate = trnDate;
    }

    @JsonProperty("Time1")
    public String getTime1() {
        return time1;
    }

    @JsonProperty("Time1")
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    @JsonProperty("Invoiceno")
    public String getInvoiceno() {
        return invoiceno;
    }

    @JsonProperty("Invoiceno")
    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    @JsonProperty("ItemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("ItemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("compName")
    public String getCompName() {
        return compName;
    }

    @JsonProperty("compName")
    public void setCompName(String compName) {
        this.compName = compName;
    }

    @JsonProperty("approvedBy")
    public String getApprovedBy() {
        return approvedBy;
    }

    @JsonProperty("approvedBy")
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("costCode")
    public String getCostCode() {
        return costCode;
    }

    @JsonProperty("costCode")
    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
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
