package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "fileCreateDate",
    "businessDate",
    "store",
    "referenceNbr",
    "rtlogOrgSys",
    "transactionHeadTbl",
    "salesFtailTbl"
})
public class SalesRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("recType")
    private String recType;
    @JsonProperty("fileCreateDate")
    private String fileCreateDate;
    @JsonProperty("businessDate")
    private String businessDate;
    @JsonProperty("store")
    private String store;
    @JsonProperty("referenceNbr")
    private String referenceNbr;
    @JsonProperty("rtlogOrgSys")
    private String rtlogOrgSys;
    @JsonProperty("transactionHeadTbl")
    private List<TransactionHeadTbl> transactionHeadTbl = null;
    @JsonProperty("salesFtailTbl")
    private List<SalesFtailTbl> salesFtailTbl = null;

    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("fileCreateDate")
    public String getFileCreateDate() {
        return fileCreateDate;
    }

    @JsonProperty("fileCreateDate")
    public void setFileCreateDate(String fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }

    @JsonProperty("businessDate")
    public String getBusinessDate() {
        return businessDate;
    }

    @JsonProperty("businessDate")
    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    @JsonProperty("store")
    public String getStore() {
        return store;
    }

    @JsonProperty("store")
    public void setStore(String store) {
        this.store = store;
    }

    @JsonProperty("referenceNbr")
    public String getReferenceNbr() {
        return referenceNbr;
    }

    @JsonProperty("referenceNbr")
    public void setReferenceNbr(String referenceNbr) {
        this.referenceNbr = referenceNbr;
    }

    @JsonProperty("rtlogOrgSys")
    public String getRtlogOrgSys() {
        return rtlogOrgSys;
    }

    @JsonProperty("rtlogOrgSys")
    public void setRtlogOrgSys(String rtlogOrgSys) {
        this.rtlogOrgSys = rtlogOrgSys;
    }

    @JsonProperty("transactionHeadTbl")
    public List<TransactionHeadTbl> getTransactionHeadTbl() {
        return transactionHeadTbl;
    }

    @JsonProperty("transactionHeadTbl")
    public void setTransactionHeadTbl(List<TransactionHeadTbl> transactionHeadTbl) {
        this.transactionHeadTbl = transactionHeadTbl;
    }

    @JsonProperty("salesFtailTbl")
    public List<SalesFtailTbl> getSalesFtailTbl() {
        return salesFtailTbl;
    }

    @JsonProperty("salesFtailTbl")
    public void setSalesFtailTbl(List<SalesFtailTbl> salesFtailTbl) {
        this.salesFtailTbl = salesFtailTbl;
    }

}
