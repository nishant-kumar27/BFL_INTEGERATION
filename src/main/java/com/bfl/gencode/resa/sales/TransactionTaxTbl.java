
package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "taxCode",
    "taxAmount",
    "refNo17",
    "refNo18",
    "refNo19",
    "refNo20",
    "transactionTaxAttributeTbl"
})
public class TransactionTaxTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("taxCode")
    private String taxCode;
    @JsonProperty("taxAmount")
    private String taxAmount;
    @JsonProperty("refNo17")
    private String refNo17;
    @JsonProperty("refNo18")
    private String refNo18;
    @JsonProperty("refNo19")
    private String refNo19;
    @JsonProperty("refNo20")
    private String refNo20;
    @JsonProperty("transactionTaxAttributeTbl")
    private List<TransactionTaxAttributeTbl> transactionTaxAttributeTbl = null;
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

    @JsonProperty("taxCode")
    public String getTaxCode() {
        return taxCode;
    }

    @JsonProperty("taxCode")
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @JsonProperty("taxAmount")
    public String getTaxAmount() {
        return taxAmount;
    }

    @JsonProperty("taxAmount")
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    @JsonProperty("refNo17")
    public String getRefNo17() {
        return refNo17;
    }

    @JsonProperty("refNo17")
    public void setRefNo17(String refNo17) {
        this.refNo17 = refNo17;
    }

    @JsonProperty("refNo18")
    public String getRefNo18() {
        return refNo18;
    }

    @JsonProperty("refNo18")
    public void setRefNo18(String refNo18) {
        this.refNo18 = refNo18;
    }

    @JsonProperty("refNo19")
    public String getRefNo19() {
        return refNo19;
    }

    @JsonProperty("refNo19")
    public void setRefNo19(String refNo19) {
        this.refNo19 = refNo19;
    }

    @JsonProperty("refNo20")
    public String getRefNo20() {
        return refNo20;
    }

    @JsonProperty("refNo20")
    public void setRefNo20(String refNo20) {
        this.refNo20 = refNo20;
    }

    @JsonProperty("transactionTaxAttributeTbl")
    public List<TransactionTaxAttributeTbl> getTransactionTaxAttributeTbl() {
        return transactionTaxAttributeTbl;
    }

    @JsonProperty("transactionTaxAttributeTbl")
    public void setTransactionTaxAttributeTbl(List<TransactionTaxAttributeTbl> transactionTaxAttributeTbl) {
        this.transactionTaxAttributeTbl = transactionTaxAttributeTbl;
    }

}
