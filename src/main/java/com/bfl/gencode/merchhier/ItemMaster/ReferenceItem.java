package com.bfl.gencode.merchhier.ItemMaster;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "referenceItem",
    "primaryInd",
    "formatId",
    "prefix",
    "itemNoType",
    "createDateTime",
    "updateDateTime"
})
public class ReferenceItem {

    @JsonProperty("referenceItem")
    private String referenceItem;
    @JsonProperty("primaryInd")
    private String primaryInd;
    @JsonProperty("formatId")
    private String formatId;
    @JsonProperty("prefix")
    private Integer prefix;
    @JsonProperty("itemNoType")
    private String itemNoType;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("referenceItem")
    public String getReferenceItem() {
        return referenceItem;
    }

    @JsonProperty("referenceItem")
    public void setReferenceItem(String referenceItem) {
        this.referenceItem = referenceItem;
    }

    @JsonProperty("primaryInd")
    public String getPrimaryInd() {
        return primaryInd;
    }

    @JsonProperty("primaryInd")
    public void setPrimaryInd(String primaryInd) {
        this.primaryInd = primaryInd;
    }

    @JsonProperty("formatId")
    public String getFormatId() {
        return formatId;
    }

    @JsonProperty("formatId")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @JsonProperty("prefix")
    public Integer getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    @JsonProperty("itemNoType")
    public String getItemNoType() {
        return itemNoType;
    }

    @JsonProperty("itemNoType")
    public void setItemNoType(String itemNoType) {
        this.itemNoType = itemNoType;
    }

    @JsonProperty("createDateTime")
    public String getCreateDateTime() {
        return createDateTime;
    }

    @JsonProperty("createDateTime")
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    @JsonProperty("updateDateTime")
    public String getUpdateDateTime() {
        return updateDateTime;
    }

    @JsonProperty("updateDateTime")
    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
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
