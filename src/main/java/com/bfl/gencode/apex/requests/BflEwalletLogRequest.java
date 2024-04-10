
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
    "entryDate",
    "cardNo",
    "amount",
    "payMode",
    "mobile",
    "status",
    "trnno",
    "trndate",
    "refNo",
    "confirmationno",
    "normalReload",
    "creditReload",
    "loyaltyReload",
    "otherReload",
    "pointsAdded",
    "previousBalance",
    "pointsbalance",
    "previouspoints",
    "balance",
    "bonusAmount",
    "confirmedBalance",
    "cardtype",
    "recordno",
    "closed",
    "reconciled",
    "onlineCode",
    "shopname"
})
@Generated("jsonschema2pojo")
public class BflEwalletLogRequest {

    @JsonProperty("sn")
    private String sn;
    @JsonProperty("entryDate")
    private String entryDate;
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
    @JsonProperty("trndate")
    private String trndate;
    @JsonProperty("refNo")
    private String refNo;
    @JsonProperty("confirmationno")
    private String confirmationno;
    @JsonProperty("normalReload")
    private String normalReload;
    @JsonProperty("creditReload")
    private String creditReload;
    @JsonProperty("loyaltyReload")
    private String loyaltyReload;
    @JsonProperty("otherReload")
    private String otherReload;
    @JsonProperty("pointsAdded")
    private String pointsAdded;
    @JsonProperty("previousBalance")
    private String previousBalance;
    @JsonProperty("pointsbalance")
    private String pointsbalance;
    @JsonProperty("previouspoints")
    private String previouspoints;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("bonusAmount")
    private String bonusAmount;
    @JsonProperty("confirmedBalance")
    private String confirmedBalance;
    @JsonProperty("cardtype")
    private String cardtype;
    @JsonProperty("recordno")
    private String recordno;
    @JsonProperty("closed")
    private String closed;
    @JsonProperty("reconciled")
    private String reconciled;
    @JsonProperty("onlineCode")
    private String onlineCode;
    @JsonProperty("shopname")
    private String shopname;
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

    @JsonProperty("entryDate")
    public String getEntryDate() {
        return entryDate;
    }

    @JsonProperty("entryDate")
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
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

    @JsonProperty("trndate")
    public String getTrndate() {
        return trndate;
    }

    @JsonProperty("trndate")
    public void setTrndate(String trndate) {
        this.trndate = trndate;
    }

    @JsonProperty("refNo")
    public String getRefNo() {
        return refNo;
    }

    @JsonProperty("refNo")
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @JsonProperty("confirmationno")
    public String getConfirmationno() {
        return confirmationno;
    }

    @JsonProperty("confirmationno")
    public void setConfirmationno(String confirmationno) {
        this.confirmationno = confirmationno;
    }

    @JsonProperty("normalReload")
    public String getNormalReload() {
        return normalReload;
    }

    @JsonProperty("normalReload")
    public void setNormalReload(String normalReload) {
        this.normalReload = normalReload;
    }

    @JsonProperty("creditReload")
    public String getCreditReload() {
        return creditReload;
    }

    @JsonProperty("creditReload")
    public void setCreditReload(String creditReload) {
        this.creditReload = creditReload;
    }

    @JsonProperty("loyaltyReload")
    public String getLoyaltyReload() {
        return loyaltyReload;
    }

    @JsonProperty("loyaltyReload")
    public void setLoyaltyReload(String loyaltyReload) {
        this.loyaltyReload = loyaltyReload;
    }

    @JsonProperty("otherReload")
    public String getOtherReload() {
        return otherReload;
    }

    @JsonProperty("otherReload")
    public void setOtherReload(String otherReload) {
        this.otherReload = otherReload;
    }

    @JsonProperty("pointsAdded")
    public String getPointsAdded() {
        return pointsAdded;
    }

    @JsonProperty("pointsAdded")
    public void setPointsAdded(String pointsAdded) {
        this.pointsAdded = pointsAdded;
    }

    @JsonProperty("previousBalance")
    public String getPreviousBalance() {
        return previousBalance;
    }

    @JsonProperty("previousBalance")
    public void setPreviousBalance(String previousBalance) {
        this.previousBalance = previousBalance;
    }

    @JsonProperty("pointsbalance")
    public String getPointsbalance() {
        return pointsbalance;
    }

    @JsonProperty("pointsbalance")
    public void setPointsbalance(String pointsbalance) {
        this.pointsbalance = pointsbalance;
    }

    @JsonProperty("previouspoints")
    public String getPreviouspoints() {
        return previouspoints;
    }

    @JsonProperty("previouspoints")
    public void setPreviouspoints(String previouspoints) {
        this.previouspoints = previouspoints;
    }

    @JsonProperty("balance")
    public String getBalance() {
        return balance;
    }

    @JsonProperty("balance")
    public void setBalance(String balance) {
        this.balance = balance;
    }

    @JsonProperty("bonusAmount")
    public String getBonusAmount() {
        return bonusAmount;
    }

    @JsonProperty("bonusAmount")
    public void setBonusAmount(String bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    @JsonProperty("confirmedBalance")
    public String getConfirmedBalance() {
        return confirmedBalance;
    }

    @JsonProperty("confirmedBalance")
    public void setConfirmedBalance(String confirmedBalance) {
        this.confirmedBalance = confirmedBalance;
    }

    @JsonProperty("cardtype")
    public String getCardtype() {
        return cardtype;
    }

    @JsonProperty("cardtype")
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    @JsonProperty("recordno")
    public String getRecordno() {
        return recordno;
    }

    @JsonProperty("recordno")
    public void setRecordno(String recordno) {
        this.recordno = recordno;
    }

    @JsonProperty("closed")
    public String getClosed() {
        return closed;
    }

    @JsonProperty("closed")
    public void setClosed(String closed) {
        this.closed = closed;
    }

    @JsonProperty("reconciled")
    public String getReconciled() {
        return reconciled;
    }

    @JsonProperty("reconciled")
    public void setReconciled(String reconciled) {
        this.reconciled = reconciled;
    }

    @JsonProperty("onlineCode")
    public String getOnlineCode() {
        return onlineCode;
    }

    @JsonProperty("onlineCode")
    public void setOnlineCode(String onlineCode) {
        this.onlineCode = onlineCode;
    }

    @JsonProperty("shopname")
    public String getShopname() {
        return shopname;
    }

    @JsonProperty("shopname")
    public void setShopname(String shopname) {
        this.shopname = shopname;
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
