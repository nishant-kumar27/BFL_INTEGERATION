package com.bfl.gencode.merchhier.OrderAttachment;

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
    "order_no",
    "shipment",
    "doc_seq",
    "doc_type",
    "doc_file",
    "doc_file_name",
    "doc_file_meme"
})
public class Item {

    @JsonProperty("order_no")
    private Integer orderNo;
    @JsonProperty("shipment")
    private Object shipment;
    @JsonProperty("doc_seq")
    private Integer docSeq;
    @JsonProperty("doc_type")
    private Object docType;
    @JsonProperty("doc_file")
    private String docFile;
    @JsonProperty("doc_file_name")
    private String docFileName;
    @JsonProperty("doc_file_meme")
    private String docFileMeme;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("order_no")
    public Integer getOrderNo() {
        return orderNo;
    }

    @JsonProperty("order_no")
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @JsonProperty("shipment")
    public Object getShipment() {
        return shipment;
    }

    @JsonProperty("shipment")
    public void setShipment(Object shipment) {
        this.shipment = shipment;
    }

    @JsonProperty("doc_seq")
    public Integer getDocSeq() {
        return docSeq;
    }

    @JsonProperty("doc_seq")
    public void setDocSeq(Integer docSeq) {
        this.docSeq = docSeq;
    }

    @JsonProperty("doc_type")
    public Object getDocType() {
        return docType;
    }

    @JsonProperty("doc_type")
    public void setDocType(Object docType) {
        this.docType = docType;
    }

    @JsonProperty("doc_file")
    public String getDocFile() {
        return docFile;
    }

    @JsonProperty("doc_file")
    public void setDocFile(String docFile) {
        this.docFile = docFile;
    }

    @JsonProperty("doc_file_name")
    public String getDocFileName() {
        return docFileName;
    }

    @JsonProperty("doc_file_name")
    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    @JsonProperty("doc_file_meme")
    public String getDocFileMeme() {
        return docFileMeme;
    }

    @JsonProperty("doc_file_meme")
    public void setDocFileMeme(String docFileMeme) {
        this.docFileMeme = docFileMeme;
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
