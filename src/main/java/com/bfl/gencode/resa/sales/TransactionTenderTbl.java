
package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "tendTypeGroup",
    "tendTypeId",
    "tenderAmount",
    "ccNo",
    "ccAuthNo",
    "ccAuthSource",
    "ccCardholderVerification",
    "ccExpirationDate",
    "ccEntryMode",
    "ccTerminalId",
    "ccSpecialCondition",
    "ccToken",
    "voucherNo",
    "couponNbr",
    "couponRefNo",
    "chequeAccNo",
    "chequeNo",
    "identificationMethod",
    "identificationId",
    "originalCurrency",
    "originalCurrencyAmount",
    "refNbr9",
    "refNbr10",
    "refNbr11",
    "refNbr12",
    "transactionTenderAttributeTbl"
})
public class TransactionTenderTbl implements Serializable {

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("tendTypeGroup")
    private String tendTypeGroup;
    @JsonProperty("tendTypeId")
    private String tendTypeId;
    @JsonProperty("tenderAmount")
    private String tenderAmount;
    @JsonProperty("ccNo")
    private String ccNo;
    @JsonProperty("ccAuthNo")
    private String ccAuthNo;
    @JsonProperty("ccAuthSource")
    private String ccAuthSource;
    @JsonProperty("ccCardholderVerification")
    private String ccCardholderVerification;
    @JsonProperty("ccExpirationDate")
    private String ccExpirationDate;
    @JsonProperty("ccEntryMode")
    private String ccEntryMode;
    @JsonProperty("ccTerminalId")
    private String ccTerminalId;
    @JsonProperty("ccSpecialCondition")
    private String ccSpecialCondition;
    @JsonProperty("ccToken")
    private String ccToken;
    @JsonProperty("voucherNo")
    private String voucherNo;
    @JsonProperty("couponNbr")
    private String couponNbr;
    @JsonProperty("couponRefNo")
    private String couponRefNo;
    @JsonProperty("chequeAccNo")
    private String chequeAccNo;
    @JsonProperty("chequeNo")
    private String chequeNo;
    @JsonProperty("identificationMethod")
    private String identificationMethod;
    @JsonProperty("identificationId")
    private String identificationId;
    @JsonProperty("originalCurrency")
    private String originalCurrency;
    @JsonProperty("originalCurrencyAmount")
    private String originalCurrencyAmount;
    @JsonProperty("refNbr9")
    private String refNbr9;
    @JsonProperty("refNbr10")
    private String refNbr10;
    @JsonProperty("refNbr11")
    private String refNbr11;
    @JsonProperty("refNbr12")
    private String refNbr12;
    @JsonProperty("transactionTenderAttributeTbl")
    private List<TransactionTenderAttributeTbl> transactionTenderAttributeTbl = null;
    /**
   	 * 
   	 */
   	private static final long serialVersionUID = 1L;
   	
    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("tendTypeGroup")
    public String getTendTypeGroup() {
        return tendTypeGroup;
    }

    @JsonProperty("tendTypeGroup")
    public void setTendTypeGroup(String tendTypeGroup) {
        this.tendTypeGroup = tendTypeGroup;
    }

    @JsonProperty("tendTypeId")
    public String getTendTypeId() {
        return tendTypeId;
    }

    @JsonProperty("tendTypeId")
    public void setTendTypeId(String tendTypeId) {
        this.tendTypeId = tendTypeId;
    }

    @JsonProperty("tenderAmount")
    public String getTenderAmount() {
        return tenderAmount;
    }

    @JsonProperty("tenderAmount")
    public void setTenderAmount(String tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    @JsonProperty("ccNo")
    public String getCcNo() {
        return ccNo;
    }

    @JsonProperty("ccNo")
    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }

    @JsonProperty("ccAuthNo")
    public String getCcAuthNo() {
        return ccAuthNo;
    }

    @JsonProperty("ccAuthNo")
    public void setCcAuthNo(String ccAuthNo) {
        this.ccAuthNo = ccAuthNo;
    }

    @JsonProperty("ccAuthSource")
    public String getCcAuthSource() {
        return ccAuthSource;
    }

    @JsonProperty("ccAuthSource")
    public void setCcAuthSource(String ccAuthSource) {
        this.ccAuthSource = ccAuthSource;
    }

    @JsonProperty("ccCardholderVerification")
    public String getCcCardholderVerification() {
        return ccCardholderVerification;
    }

    @JsonProperty("ccCardholderVerification")
    public void setCcCardholderVerification(String ccCardholderVerification) {
        this.ccCardholderVerification = ccCardholderVerification;
    }

    @JsonProperty("ccExpirationDate")
    public String getCcExpirationDate() {
        return ccExpirationDate;
    }

    @JsonProperty("ccExpirationDate")
    public void setCcExpirationDate(String ccExpirationDate) {
        this.ccExpirationDate = ccExpirationDate;
    }

    @JsonProperty("ccEntryMode")
    public String getCcEntryMode() {
        return ccEntryMode;
    }

    @JsonProperty("ccEntryMode")
    public void setCcEntryMode(String ccEntryMode) {
        this.ccEntryMode = ccEntryMode;
    }

    @JsonProperty("ccTerminalId")
    public String getCcTerminalId() {
        return ccTerminalId;
    }

    @JsonProperty("ccTerminalId")
    public void setCcTerminalId(String ccTerminalId) {
        this.ccTerminalId = ccTerminalId;
    }

    @JsonProperty("ccSpecialCondition")
    public String getCcSpecialCondition() {
        return ccSpecialCondition;
    }

    @JsonProperty("ccSpecialCondition")
    public void setCcSpecialCondition(String ccSpecialCondition) {
        this.ccSpecialCondition = ccSpecialCondition;
    }

    @JsonProperty("ccToken")
    public String getCcToken() {
        return ccToken;
    }

    @JsonProperty("ccToken")
    public void setCcToken(String ccToken) {
        this.ccToken = ccToken;
    }

    @JsonProperty("voucherNo")
    public String getVoucherNo() {
        return voucherNo;
    }

    @JsonProperty("voucherNo")
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    @JsonProperty("couponNbr")
    public String getCouponNbr() {
        return couponNbr;
    }

    @JsonProperty("couponNbr")
    public void setCouponNbr(String couponNbr) {
        this.couponNbr = couponNbr;
    }

    @JsonProperty("couponRefNo")
    public String getCouponRefNo() {
        return couponRefNo;
    }

    @JsonProperty("couponRefNo")
    public void setCouponRefNo(String couponRefNo) {
        this.couponRefNo = couponRefNo;
    }

    @JsonProperty("chequeAccNo")
    public String getChequeAccNo() {
        return chequeAccNo;
    }

    @JsonProperty("chequeAccNo")
    public void setChequeAccNo(String chequeAccNo) {
        this.chequeAccNo = chequeAccNo;
    }

    @JsonProperty("chequeNo")
    public String getChequeNo() {
        return chequeNo;
    }

    @JsonProperty("chequeNo")
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    @JsonProperty("identificationMethod")
    public String getIdentificationMethod() {
        return identificationMethod;
    }

    @JsonProperty("identificationMethod")
    public void setIdentificationMethod(String identificationMethod) {
        this.identificationMethod = identificationMethod;
    }

    @JsonProperty("identificationId")
    public String getIdentificationId() {
        return identificationId;
    }

    @JsonProperty("identificationId")
    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    @JsonProperty("originalCurrency")
    public String getOriginalCurrency() {
        return originalCurrency;
    }

    @JsonProperty("originalCurrency")
    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    @JsonProperty("originalCurrencyAmount")
    public String getOriginalCurrencyAmount() {
        return originalCurrencyAmount;
    }

    @JsonProperty("originalCurrencyAmount")
    public void setOriginalCurrencyAmount(String originalCurrencyAmount) {
        this.originalCurrencyAmount = originalCurrencyAmount;
    }

    @JsonProperty("refNbr9")
    public String getRefNbr9() {
        return refNbr9;
    }

    @JsonProperty("refNbr9")
    public void setRefNbr9(String refNbr9) {
        this.refNbr9 = refNbr9;
    }

    @JsonProperty("refNbr10")
    public String getRefNbr10() {
        return refNbr10;
    }

    @JsonProperty("refNbr10")
    public void setRefNbr10(String refNbr10) {
        this.refNbr10 = refNbr10;
    }

    @JsonProperty("refNbr11")
    public String getRefNbr11() {
        return refNbr11;
    }

    @JsonProperty("refNbr11")
    public void setRefNbr11(String refNbr11) {
        this.refNbr11 = refNbr11;
    }

    @JsonProperty("refNbr12")
    public String getRefNbr12() {
        return refNbr12;
    }

    @JsonProperty("refNbr12")
    public void setRefNbr12(String refNbr12) {
        this.refNbr12 = refNbr12;
    }

    @JsonProperty("transactionTenderAttributeTbl")
    public List<TransactionTenderAttributeTbl> getTransactionTenderAttributeTbl() {
        return transactionTenderAttributeTbl;
    }

    @JsonProperty("transactionTenderAttributeTbl")
    public void setTransactionTenderAttributeTbl(List<TransactionTenderAttributeTbl> transactionTenderAttributeTbl) {
        this.transactionTenderAttributeTbl = transactionTenderAttributeTbl;
    }
}
