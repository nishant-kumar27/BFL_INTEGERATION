
package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "taxAuthority",
    "igtaxCode",
    "igtaxRate",
    "igtaxAmount",
    "refNo21",
    "refNo22",
    "refNo23",
    "refNo24",
    "transactionItemTaxAttributeTbl"
})
@Generated("jsonschema2pojo")
public class TransactionItemTaxTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("taxAuthority")
    private String taxAuthority;
    @JsonProperty("igtaxCode")
    private String igtaxCode;
    @JsonProperty("igtaxRate")
    private String igtaxRate;
    @JsonProperty("igtaxAmount")
    private String igtaxAmount;
    @JsonProperty("refNo21")
    private String refNo21;
    @JsonProperty("refNo22")
    private String refNo22;
    @JsonProperty("refNo23")
    private String refNo23;
    @JsonProperty("refNo24")
    private String refNo24;
    @JsonProperty("transactionItemTaxAttributeTbl")
    private List<TransactionItemTaxAttributeTbl> transactionItemTaxAttributeTbl = null;
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

    @JsonProperty("taxAuthority")
    public String getTaxAuthority() {
        return taxAuthority;
    }

    @JsonProperty("taxAuthority")
    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    @JsonProperty("igtaxCode")
    public String getIgtaxCode() {
        return igtaxCode;
    }

    @JsonProperty("igtaxCode")
    public void setIgtaxCode(String igtaxCode) {
        this.igtaxCode = igtaxCode;
    }

    @JsonProperty("igtaxRate")
    public String getIgtaxRate() {
        return igtaxRate;
    }

    @JsonProperty("igtaxRate")
    public void setIgtaxRate(String igtaxRate) {
        this.igtaxRate = igtaxRate;
    }

    @JsonProperty("igtaxAmount")
    public String getIgtaxAmount() {
        return igtaxAmount;
    }

    @JsonProperty("igtaxAmount")
    public void setIgtaxAmount(String igtaxAmount) {
        this.igtaxAmount = igtaxAmount;
    }

    @JsonProperty("refNo21")
    public String getRefNo21() {
        return refNo21;
    }

    @JsonProperty("refNo21")
    public void setRefNo21(String refNo21) {
        this.refNo21 = refNo21;
    }

    @JsonProperty("refNo22")
    public String getRefNo22() {
        return refNo22;
    }

    @JsonProperty("refNo22")
    public void setRefNo22(String refNo22) {
        this.refNo22 = refNo22;
    }

    @JsonProperty("refNo23")
    public String getRefNo23() {
        return refNo23;
    }

    @JsonProperty("refNo23")
    public void setRefNo23(String refNo23) {
        this.refNo23 = refNo23;
    }

    @JsonProperty("refNo24")
    public String getRefNo24() {
        return refNo24;
    }

    @JsonProperty("refNo24")
    public void setRefNo24(String refNo24) {
        this.refNo24 = refNo24;
    }

    @JsonProperty("transactionItemTaxAttributeTbl")
    public List<TransactionItemTaxAttributeTbl> getTransactionItemTaxAttributeTbl() {
        return transactionItemTaxAttributeTbl;
    }

    @JsonProperty("transactionItemTaxAttributeTbl")
    public void setTransactionItemTaxAttributeTbl(List<TransactionItemTaxAttributeTbl> transactionItemTaxAttributeTbl) {
        this.transactionItemTaxAttributeTbl = transactionItemTaxAttributeTbl;
    }
}
