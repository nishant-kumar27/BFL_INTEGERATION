package com.bfl.gencode.merchhier.ItemMaster;

import java.io.Serializable;
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
    "seasonId",
    "phaseId",
    "sequenceNo",
    "diffId",
    "createDateTime",
    "updateDateTime"
})

public class ItemSeason implements Serializable {
	
    @JsonProperty("seasonId")
    private Integer seasonId;
    
    @JsonProperty("phaseId")
    private Integer phaseId;
    
    @JsonProperty("sequenceNo")
    private Integer sequenceNo;
    
    @JsonProperty("diffId")
    private String diffId;
    
    @JsonProperty("createDateTime")
    private String createDateTime;
    
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -5031966760596874800L;

    @JsonProperty("seasonId")
    public Integer getSeasonId() {
        return seasonId;
    }

    @JsonProperty("seasonId")
    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    @JsonProperty("phaseId")
    public Integer getPhaseId() {
        return phaseId;
    }

    @JsonProperty("phaseId")
    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    @JsonProperty("sequenceNo")
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    @JsonProperty("sequenceNo")
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    @JsonProperty("diffId")
    public String getDiffId() {
        return diffId;
    }

    @JsonProperty("diffId")
    public void setDiffId(String diffId) {
        this.diffId = diffId;
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
