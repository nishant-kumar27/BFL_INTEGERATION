package com.bfl.gencode.store.header.request;

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
    "transferNo",
    "fromLocationType",
    "fromLocation",
    "toLocationType",
    "toLocation",
    "deliveryDate",
    "department",
    "routingCode",
    "transferType",
    "details",
    "status",
    "createdBy",
    "comments",
    "contextType",
    "contextValue",
    "customFlexAttribute"
})
public class Item {

    @JsonProperty("transferNo")
    private Long transferNo;
    @JsonProperty("fromLocationType")
    private String fromLocationType;
    @JsonProperty("fromLocation")
    private Integer fromLocation;
    @JsonProperty("toLocationType")
    private String toLocationType;
    @JsonProperty("toLocation")
    private Integer toLocation;
    @JsonProperty("deliveryDate")
    private String deliveryDate;
    @JsonProperty("department")
    private Object department;
    @JsonProperty("routingCode")
    private String routingCode;
    @JsonProperty("transferType")
    private String transferType;
    @JsonProperty("details")
    private List<Detail> details;
    @JsonProperty("status")
    private String status;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("contextType")
    private String contextType;
    @JsonProperty("contextValue")
    private String contextValue;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("transferNo")
    public Long getTransferNo() {
        return transferNo;
    }

    @JsonProperty("transferNo")
    public void setTransferNo(Long transferNo) {
        this.transferNo = transferNo;
    }

    @JsonProperty("fromLocationType")
    public String getFromLocationType() {
        return fromLocationType;
    }

    @JsonProperty("fromLocationType")
    public void setFromLocationType(String fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    @JsonProperty("fromLocation")
    public Integer getFromLocation() {
        return fromLocation;
    }

    @JsonProperty("fromLocation")
    public void setFromLocation(Integer fromLocation) {
        this.fromLocation = fromLocation;
    }

    @JsonProperty("toLocationType")
    public String getToLocationType() {
        return toLocationType;
    }

    @JsonProperty("toLocationType")
    public void setToLocationType(String toLocationType) {
        this.toLocationType = toLocationType;
    }

    @JsonProperty("toLocation")
    public Integer getToLocation() {
        return toLocation;
    }

    @JsonProperty("toLocation")
    public void setToLocation(Integer toLocation) {
        this.toLocation = toLocation;
    }

    @JsonProperty("deliveryDate")
    public String getDeliveryDate() {
        return deliveryDate;
    }

    @JsonProperty("deliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JsonProperty("department")
    public Object getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(Object department) {
        this.department = department;
    }

    @JsonProperty("routingCode")
    public String getRoutingCode() {
        return routingCode;
    }

    @JsonProperty("routingCode")
    public void setRoutingCode(String routingCode) {
        this.routingCode = routingCode;
    }

    @JsonProperty("transferType")
    public String getTransferType() {
        return transferType;
    }

    @JsonProperty("transferType")
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("contextType")
    public String getContextType() {
        return contextType;
    }

    @JsonProperty("contextType")
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    @JsonProperty("contextValue")
    public String getContextValue() {
        return contextValue;
    }

    @JsonProperty("contextValue")
    public void setContextValue(String contextValue) {
        this.contextValue = contextValue;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
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
