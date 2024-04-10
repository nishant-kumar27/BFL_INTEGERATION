package com.bfl.gencode.merchhier.ItemMaster;

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
    "relationshipId",
    "relationshipName",
    "relationshipType",
    "mandatoryInd",
    "createDateTime",
    "updateDateTime",
    "details"
})
public class RelatedItem {

    @JsonProperty("relationshipId")
    private Integer relationshipId;
    @JsonProperty("relationshipName")
    private String relationshipName;
    @JsonProperty("relationshipType")
    private String relationshipType;
    @JsonProperty("mandatoryInd")
    private String mandatoryInd;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("details")
    private List<Detail__1> details = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("relationshipId")
    public Integer getRelationshipId() {
        return relationshipId;
    }

    @JsonProperty("relationshipId")
    public void setRelationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
    }

    @JsonProperty("relationshipName")
    public String getRelationshipName() {
        return relationshipName;
    }

    @JsonProperty("relationshipName")
    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    @JsonProperty("relationshipType")
    public String getRelationshipType() {
        return relationshipType;
    }

    @JsonProperty("relationshipType")
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    @JsonProperty("mandatoryInd")
    public String getMandatoryInd() {
        return mandatoryInd;
    }

    @JsonProperty("mandatoryInd")
    public void setMandatoryInd(String mandatoryInd) {
        this.mandatoryInd = mandatoryInd;
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

    @JsonProperty("details")
    public List<Detail__1> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail__1> details) {
        this.details = details;
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
