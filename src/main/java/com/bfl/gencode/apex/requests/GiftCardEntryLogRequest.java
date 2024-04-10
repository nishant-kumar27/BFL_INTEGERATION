
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
    "sn",
    "trndate",
    "cardNo",
    "amount",
    "payMode",
    "mobile",
    "status",
    "trnno",
    "refNo",
    "firstName",
    "middleName",
    "lastName",
    "email",
    "gender",
    "shopName"
})
@Generated("jsonschema2pojo")
public class GiftCardEntryLogRequest {

    @JsonProperty("sn")
    private String sn;
    @JsonProperty("trndate")
    private String trndate;
    @JsonProperty("cardNo")
    private String cardNo;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("payMode")
    private String payMode;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("status")
    private String status;
    @JsonProperty("trnno")
    private String trnno;
    @JsonProperty("refNo")
    private String refNo;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("shopName")
    private String shopName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sn")
    public String getSn() {
        return sn;
    }

    @JsonProperty("sn")
    public void setSn(String sn) {
        this.sn = sn;
    }

    @JsonProperty("trndate")
    public String getTrndate() {
        return trndate;
    }

    @JsonProperty("trndate")
    public void setTrndate(String trndate) {
        this.trndate = trndate;
    }

    @JsonProperty("cardNo")
    public String getCardNo() {
        return cardNo;
    }

    @JsonProperty("cardNo")
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("payMode")
    public String getPayMode() {
        return payMode;
    }

    @JsonProperty("payMode")
    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    @JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("trnno")
    public String getTrnno() {
        return trnno;
    }

    @JsonProperty("trnno")
    public void setTrnno(String trnno) {
        this.trnno = trnno;
    }

    @JsonProperty("refNo")
    public String getRefNo() {
        return refNo;
    }

    @JsonProperty("refNo")
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("shopName")
    public String getShopName() {
        return shopName;
    }

    @JsonProperty("shopName")
    public void setShopName(String shopName) {
        this.shopName = shopName;
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
