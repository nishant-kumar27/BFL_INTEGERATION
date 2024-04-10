
package com.bfl.gencode.resa.sales.response;

import java.util.LinkedHashMap;
import java.util.List;
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
    "store",
    "transactionNo",
    "recType",
    "businessDateDisplay",
    "businessDate",
    "errorMsg",
    "links",
    "hyperMediaContent"
})
@Generated("jsonschema2pojo")
public class SalesErrTbl {

    @JsonProperty("store")
    private Integer store;
    @JsonProperty("transactionNo")
    private Object transactionNo;
    @JsonProperty("recType")
    private String recType;
    @JsonProperty("businessDateDisplay")
    private String businessDateDisplay;
    @JsonProperty("businessDate")
    private Long businessDate;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("links")
    private List<Object> links;
    @JsonProperty("hyperMediaContent")
    private HyperMediaContent hyperMediaContent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("store")
    public Integer getStore() {
        return store;
    }

    @JsonProperty("store")
    public void setStore(Integer store) {
        this.store = store;
    }

    @JsonProperty("transactionNo")
    public Object getTransactionNo() {
        return transactionNo;
    }

    @JsonProperty("transactionNo")
    public void setTransactionNo(Object transactionNo) {
        this.transactionNo = transactionNo;
    }

    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("businessDateDisplay")
    public String getBusinessDateDisplay() {
        return businessDateDisplay;
    }

    @JsonProperty("businessDateDisplay")
    public void setBusinessDateDisplay(String businessDateDisplay) {
        this.businessDateDisplay = businessDateDisplay;
    }

    @JsonProperty("businessDate")
    public Long getBusinessDate() {
        return businessDate;
    }

    @JsonProperty("businessDate")
    public void setBusinessDate(Long businessDate) {
        this.businessDate = businessDate;
    }

    @JsonProperty("errorMsg")
    public String getErrorMsg() {
        return errorMsg;
    }

    @JsonProperty("errorMsg")
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
    }

    @JsonProperty("hyperMediaContent")
    public HyperMediaContent getHyperMediaContent() {
        return hyperMediaContent;
    }

    @JsonProperty("hyperMediaContent")
    public void setHyperMediaContent(HyperMediaContent hyperMediaContent) {
        this.hyperMediaContent = hyperMediaContent;
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
