package com.bfl.gencode.resa.sales.response;

import java.util.LinkedHashMap;
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
    "statusMsg",
    "salesErrTbl",
    "links",
    "hyperMediaContent"
})
public class SalesResponse {

    @JsonProperty("statusMsg")
    private String statusMsg;
    @JsonProperty("salesErrTbl")
    private List<SalesErrTbl> salesErrTbl;
    @JsonProperty("links")
    private List<Object> links;
    @JsonProperty("hyperMediaContent")
    private HyperMediaContent hyperMediaContent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("statusMsg")
    public String getStatusMsg() {
        return statusMsg;
    }

    @JsonProperty("statusMsg")
    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    @JsonProperty("salesErrTbl")
    public List<SalesErrTbl> getSalesErrTbl() {
        return salesErrTbl;
    }

    @JsonProperty("salesErrTbl")
    public void setSalesErrTbl(List<SalesErrTbl> salesErrTbl) {
        this.salesErrTbl = salesErrTbl;
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
