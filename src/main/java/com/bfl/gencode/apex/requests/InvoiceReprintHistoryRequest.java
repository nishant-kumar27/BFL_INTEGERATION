
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
    "invoiceno",
    "shopName",
    "trndate",
    "reason",
    "mgrCode",
    "mgrPass",
    "mgrName",
    "userid",
    "createdDateTime",
    "updatedDateTime"
})
@Generated("jsonschema2pojo")
public class InvoiceReprintHistoryRequest {

    @JsonProperty("invoiceno")
    private String invoiceno;
    @JsonProperty("shopName")
    private String shopName;
    @JsonProperty("trndate")
    private String trndate;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("mgrCode")
    private String mgrCode;
    @JsonProperty("mgrPass")
    private String mgrPass;
    @JsonProperty("mgrName")
    private String mgrName;
    @JsonProperty("userid")
    private String userid;
    @JsonProperty("createdDateTime")
    private String createdDateTime;
    @JsonProperty("updatedDateTime")
    private String updatedDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("invoiceno")
    public String getInvoiceno() {
        return invoiceno;
    }

    @JsonProperty("invoiceno")
    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    @JsonProperty("shopName")
    public String getShopName() {
        return shopName;
    }

    @JsonProperty("shopName")
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @JsonProperty("trndate")
    public String getTrndate() {
        return trndate;
    }

    @JsonProperty("trndate")
    public void setTrndate(String trndate) {
        this.trndate = trndate;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("mgrCode")
    public String getMgrCode() {
        return mgrCode;
    }

    @JsonProperty("mgrCode")
    public void setMgrCode(String mgrCode) {
        this.mgrCode = mgrCode;
    }

    @JsonProperty("mgrPass")
    public String getMgrPass() {
        return mgrPass;
    }

    @JsonProperty("mgrPass")
    public void setMgrPass(String mgrPass) {
        this.mgrPass = mgrPass;
    }

    @JsonProperty("mgrName")
    public String getMgrName() {
        return mgrName;
    }

    @JsonProperty("mgrName")
    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    @JsonProperty("userid")
    public String getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @JsonProperty("createdDateTime")
    public String getCreatedDateTime() {
        return createdDateTime;
    }

    @JsonProperty("createdDateTime")
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @JsonProperty("updatedDateTime")
    public String getUpdatedDateTime() {
        return updatedDateTime;
    }

    @JsonProperty("updatedDateTime")
    public void setUpdatedDateTime(String updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
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
