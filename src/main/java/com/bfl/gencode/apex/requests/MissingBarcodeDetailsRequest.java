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
    "itemCode",
    "itemDesc",
    "quantity",
    "salesPrice",
    "Remarks",
    "CREATE_USERID",
    "CREATE_DATETIME",
    "LAST_UPDATE_USERID",
    "LAST_UPDATE_DATETIME"
})
public class MissingBarcodeDetailsRequest {

    @JsonProperty("entryNo")
    private String entryNo;
    @JsonProperty("itemCode")
    private String itemCode;
    @JsonProperty("itemDesc")
    private String itemDesc;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("salesPrice")
    private String salesPrice;
    @JsonProperty("Remarks")
    private String remarks;
    @JsonProperty("CREATE_USERID")
    private String createUserid;
    @JsonProperty("CREATE_DATETIME")
    private String createDatetime;
    @JsonProperty("LAST_UPDATE_USERID")
    private String lastUpdateUserid;
    @JsonProperty("LAST_UPDATE_DATETIME")
    private String lastUpdateDatetime;
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

    @JsonProperty("itemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("itemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("itemDesc")
    public String getItemDesc() {
        return itemDesc;
    }

    @JsonProperty("itemDesc")
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
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

    @JsonProperty("Remarks")
    public String getRemarks() {
        return remarks;
    }

    @JsonProperty("Remarks")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonProperty("CREATE_USERID")
    public String getCreateUserid() {
        return createUserid;
    }

    @JsonProperty("CREATE_USERID")
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    @JsonProperty("CREATE_DATETIME")
    public String getCreateDatetime() {
        return createDatetime;
    }

    @JsonProperty("CREATE_DATETIME")
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("LAST_UPDATE_USERID")
    public String getLastUpdateUserid() {
        return lastUpdateUserid;
    }

    @JsonProperty("LAST_UPDATE_USERID")
    public void setLastUpdateUserid(String lastUpdateUserid) {
        this.lastUpdateUserid = lastUpdateUserid;
    }

    @JsonProperty("LAST_UPDATE_DATETIME")
    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    @JsonProperty("LAST_UPDATE_DATETIME")
    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
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
