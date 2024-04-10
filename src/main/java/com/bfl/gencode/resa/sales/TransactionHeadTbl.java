package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "register",
    "transactionDate",
    "transactionNo",
    "cashier",
    "salesperson",
    "transactionType",
    "subTransactionType",
    "origSalesNo",
    "origRegNo",
    "reasonCode",
    "vendorNo",
    "vendorInvcNo",
    "paymentRefNo",
    "prfDlvyNo",
    "refNo1",
    "refNo2",
    "refNo3",
    "refNo4",
    "value",
    "bannerId",
    "roundedAmt",
    "roundedOffAmt",
    "creditPromoId",
    "refNo25",
    "refNo26",
    "refNo27",
    "tranProcessSys",
    "refNo28",
    "refNo29",
    "refNo30",
    "refNo31",
    "transactionHeadAttributeTbl",
    "transactionCustomerTbl",
    "customerAttributeTbl",
    "transactionItemTbl",
    "transactionTaxTbl",
    "transactionPaymentTbl",
    "transactionTenderTbl",
    "transactionTrailerTbl"
})
public class TransactionHeadTbl implements Serializable {
	
    @JsonProperty("recType")
    private String recType;
    @JsonProperty("register")
    private String register;
    @JsonProperty("transactionDate")
    private String transactionDate;
    @JsonProperty("transactionNo")
    private String transactionNo;
    @JsonProperty("cashier")
    private String cashier;
    @JsonProperty("salesperson")
    private String salesperson;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("subTransactionType")
    private String subTransactionType;
    @JsonProperty("origSalesNo")
    private String origSalesNo;
    @JsonProperty("origRegNo")
    private String origRegNo;
    @JsonProperty("reasonCode")
    private String reasonCode;
    @JsonProperty("vendorNo")
    private String vendorNo;
    @JsonProperty("vendorInvcNo")
    private String vendorInvcNo;
    @JsonProperty("paymentRefNo")
    private String paymentRefNo;
    @JsonProperty("prfDlvyNo")
    private String prfDlvyNo;
    @JsonProperty("refNo1")
    private String refNo1;
    @JsonProperty("refNo2")
    private String refNo2;
    @JsonProperty("refNo3")
    private String refNo3;
    @JsonProperty("refNo4")
    private String refNo4;
    @JsonProperty("value")
    private String value;
    @JsonProperty("bannerId")
    private String bannerId;
    @JsonProperty("roundedAmt")
    private String roundedAmt;
    @JsonProperty("roundedOffAmt")
    private String roundedOffAmt;
    @JsonProperty("creditPromoId")
    private String creditPromoId;
    @JsonProperty("refNo25")
    private String refNo25;
    @JsonProperty("refNo26")
    private String refNo26;
    @JsonProperty("refNo27")
    private String refNo27;
    @JsonProperty("tranProcessSys")
    private String tranProcessSys;
    @JsonProperty("refNo28")
    private String refNo28;
    @JsonProperty("refNo29")
    private String refNo29;
    @JsonProperty("refNo30")
    private String refNo30;
    @JsonProperty("refNo31")
    private String refNo31;
    @JsonProperty("transactionHeadAttributeTbl")
    private List<TransactionHeadAttributeTbl> transactionHeadAttributeTbl = null;
    @JsonProperty("transactionCustomerTbl")
    private List<TransactionCustomerTbl> transactionCustomerTbl = null;
    @JsonProperty("customerAttributeTbl")
    private List<CustomerAttributeTbl> customerAttributeTbl = null;
    @JsonProperty("transactionItemTbl")
    private List<TransactionItemTbl> transactionItemTbl = null;
    @JsonProperty("transactionTaxTbl")
    private List<TransactionTaxTbl> transactionTaxTbl = null;
    @JsonProperty("transactionPaymentTbl")
    private List<TransactionPaymentTbl> transactionPaymentTbl = null;
    @JsonProperty("transactionTenderTbl")
    private List<TransactionTenderTbl> transactionTenderTbl = null;
    @JsonProperty("transactionTrailerTbl")
    private List<TransactionTrailerTbl> transactionTrailerTbl = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6245840546588154037L;

    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("register")
    public String getRegister() {
        return register;
    }

    @JsonProperty("register")
    public void setRegister(String register) {
        this.register = register;
    }

    @JsonProperty("transactionDate")
    public String getTransactionDate() {
        return transactionDate;
    }

    @JsonProperty("transactionDate")
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @JsonProperty("transactionNo")
    public String getTransactionNo() {
        return transactionNo;
    }

    @JsonProperty("transactionNo")
    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    @JsonProperty("cashier")
    public String getCashier() {
        return cashier;
    }

    @JsonProperty("cashier")
    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    @JsonProperty("salesperson")
    public String getSalesperson() {
        return salesperson;
    }

    @JsonProperty("salesperson")
    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }

    @JsonProperty("transactionType")
    public String getTransactionType() {
        return transactionType;
    }

    @JsonProperty("transactionType")
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @JsonProperty("subTransactionType")
    public String getSubTransactionType() {
        return subTransactionType;
    }

    @JsonProperty("subTransactionType")
    public void setSubTransactionType(String subTransactionType) {
        this.subTransactionType = subTransactionType;
    }

    @JsonProperty("origSalesNo")
    public String getOrigSalesNo() {
        return origSalesNo;
    }

    @JsonProperty("origSalesNo")
    public void setOrigSalesNo(String origSalesNo) {
        this.origSalesNo = origSalesNo;
    }

    @JsonProperty("origRegNo")
    public String getOrigRegNo() {
        return origRegNo;
    }

    @JsonProperty("origRegNo")
    public void setOrigRegNo(String origRegNo) {
        this.origRegNo = origRegNo;
    }

    @JsonProperty("reasonCode")
    public String getReasonCode() {
        return reasonCode;
    }

    @JsonProperty("reasonCode")
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    @JsonProperty("vendorNo")
    public String getVendorNo() {
        return vendorNo;
    }

    @JsonProperty("vendorNo")
    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    @JsonProperty("vendorInvcNo")
    public String getVendorInvcNo() {
        return vendorInvcNo;
    }

    @JsonProperty("vendorInvcNo")
    public void setVendorInvcNo(String vendorInvcNo) {
        this.vendorInvcNo = vendorInvcNo;
    }

    @JsonProperty("paymentRefNo")
    public String getPaymentRefNo() {
        return paymentRefNo;
    }

    @JsonProperty("paymentRefNo")
    public void setPaymentRefNo(String paymentRefNo) {
        this.paymentRefNo = paymentRefNo;
    }

    @JsonProperty("prfDlvyNo")
    public String getPrfDlvyNo() {
        return prfDlvyNo;
    }

    @JsonProperty("prfDlvyNo")
    public void setPrfDlvyNo(String prfDlvyNo) {
        this.prfDlvyNo = prfDlvyNo;
    }

    @JsonProperty("refNo1")
    public String getRefNo1() {
        return refNo1;
    }

    @JsonProperty("refNo1")
    public void setRefNo1(String refNo1) {
        this.refNo1 = refNo1;
    }

    @JsonProperty("refNo2")
    public String getRefNo2() {
        return refNo2;
    }

    @JsonProperty("refNo2")
    public void setRefNo2(String refNo2) {
        this.refNo2 = refNo2;
    }

    @JsonProperty("refNo3")
    public String getRefNo3() {
        return refNo3;
    }

    @JsonProperty("refNo3")
    public void setRefNo3(String refNo3) {
        this.refNo3 = refNo3;
    }

    @JsonProperty("refNo4")
    public String getRefNo4() {
        return refNo4;
    }

    @JsonProperty("refNo4")
    public void setRefNo4(String refNo4) {
        this.refNo4 = refNo4;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("bannerId")
    public String getBannerId() {
        return bannerId;
    }

    @JsonProperty("bannerId")
    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    @JsonProperty("roundedAmt")
    public String getRoundedAmt() {
        return roundedAmt;
    }

    @JsonProperty("roundedAmt")
    public void setRoundedAmt(String roundedAmt) {
        this.roundedAmt = roundedAmt;
    }

    @JsonProperty("roundedOffAmt")
    public String getRoundedOffAmt() {
        return roundedOffAmt;
    }

    @JsonProperty("roundedOffAmt")
    public void setRoundedOffAmt(String roundedOffAmt) {
        this.roundedOffAmt = roundedOffAmt;
    }

    @JsonProperty("creditPromoId")
    public String getCreditPromoId() {
        return creditPromoId;
    }

    @JsonProperty("creditPromoId")
    public void setCreditPromoId(String creditPromoId) {
        this.creditPromoId = creditPromoId;
    }

    @JsonProperty("refNo25")
    public String getRefNo25() {
        return refNo25;
    }

    @JsonProperty("refNo25")
    public void setRefNo25(String refNo25) {
        this.refNo25 = refNo25;
    }

    @JsonProperty("refNo26")
    public String getRefNo26() {
        return refNo26;
    }

    @JsonProperty("refNo26")
    public void setRefNo26(String refNo26) {
        this.refNo26 = refNo26;
    }

    @JsonProperty("refNo27")
    public String getRefNo27() {
        return refNo27;
    }

    @JsonProperty("refNo27")
    public void setRefNo27(String refNo27) {
        this.refNo27 = refNo27;
    }

    @JsonProperty("tranProcessSys")
    public String getTranProcessSys() {
        return tranProcessSys;
    }

    @JsonProperty("tranProcessSys")
    public void setTranProcessSys(String tranProcessSys) {
        this.tranProcessSys = tranProcessSys;
    }

    @JsonProperty("refNo28")
    public String getRefNo28() {
        return refNo28;
    }

    @JsonProperty("refNo28")
    public void setRefNo28(String refNo28) {
        this.refNo28 = refNo28;
    }

    @JsonProperty("refNo29")
    public String getRefNo29() {
        return refNo29;
    }

    @JsonProperty("refNo29")
    public void setRefNo29(String refNo29) {
        this.refNo29 = refNo29;
    }

    @JsonProperty("refNo30")
    public String getRefNo30() {
        return refNo30;
    }

    @JsonProperty("refNo30")
    public void setRefNo30(String refNo30) {
        this.refNo30 = refNo30;
    }

    @JsonProperty("refNo31")
    public String getRefNo31() {
        return refNo31;
    }

    @JsonProperty("refNo31")
    public void setRefNo31(String refNo31) {
        this.refNo31 = refNo31;
    }

    @JsonProperty("transactionHeadAttributeTbl")
    public List<TransactionHeadAttributeTbl> getTransactionHeadAttributeTbl() {
        return transactionHeadAttributeTbl;
    }

    @JsonProperty("transactionHeadAttributeTbl")
    public void setTransactionHeadAttributeTbl(List<TransactionHeadAttributeTbl> transactionHeadAttributeTbl) {
        this.transactionHeadAttributeTbl = transactionHeadAttributeTbl;
    }

    @JsonProperty("transactionCustomerTbl")
    public List<TransactionCustomerTbl> getTransactionCustomerTbl() {
        return transactionCustomerTbl;
    }

    @JsonProperty("transactionCustomerTbl")
    public void setTransactionCustomerTbl(List<TransactionCustomerTbl> transactionCustomerTbl) {
        this.transactionCustomerTbl = transactionCustomerTbl;
    }

    @JsonProperty("customerAttributeTbl")
    public List<CustomerAttributeTbl> getCustomerAttributeTbl() {
        return customerAttributeTbl;
    }

    @JsonProperty("customerAttributeTbl")
    public void setCustomerAttributeTbl(List<CustomerAttributeTbl> customerAttributeTbl) {
        this.customerAttributeTbl = customerAttributeTbl;
    }

    @JsonProperty("transactionItemTbl")
    public List<TransactionItemTbl> getTransactionItemTbl() {
        return transactionItemTbl;
    }

    @JsonProperty("transactionItemTbl")
    public void setTransactionItemTbl(List<TransactionItemTbl> transactionItemTbl) {
        this.transactionItemTbl = transactionItemTbl;
    }

    @JsonProperty("transactionTaxTbl")
    public List<TransactionTaxTbl> getTransactionTaxTbl() {
        return transactionTaxTbl;
    }

    @JsonProperty("transactionTaxTbl")
    public void setTransactionTaxTbl(List<TransactionTaxTbl> transactionTaxTbl) {
        this.transactionTaxTbl = transactionTaxTbl;
    }

    @JsonProperty("transactionPaymentTbl")
    public List<TransactionPaymentTbl> getTransactionPaymentTbl() {
        return transactionPaymentTbl;
    }

    @JsonProperty("transactionPaymentTbl")
    public void setTransactionPaymentTbl(List<TransactionPaymentTbl> transactionPaymentTbl) {
        this.transactionPaymentTbl = transactionPaymentTbl;
    }

    @JsonProperty("transactionTenderTbl")
    public List<TransactionTenderTbl> getTransactionTenderTbl() {
        return transactionTenderTbl;
    }

    @JsonProperty("transactionTenderTbl")
    public void setTransactionTenderTbl(List<TransactionTenderTbl> transactionTenderTbl) {
        this.transactionTenderTbl = transactionTenderTbl;
    }

    @JsonProperty("transactionTrailerTbl")
    public List<TransactionTrailerTbl> getTransactionTrailerTbl() {
        return transactionTrailerTbl;
    }

    @JsonProperty("transactionTrailerTbl")
    public void setTransactionTrailerTbl(List<TransactionTrailerTbl> transactionTrailerTbl) {
        this.transactionTrailerTbl = transactionTrailerTbl;
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
