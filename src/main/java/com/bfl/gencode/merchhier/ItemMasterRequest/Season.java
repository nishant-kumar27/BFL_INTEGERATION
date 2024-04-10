
package com.bfl.gencode.merchhier.ItemMasterRequest;

import java.io.Serializable;
import java.util.LinkedHashMap;
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
    "seasonId",
    "phaseId",
    "sequenceNo",
    "diffId"
})
@Generated("jsonschema2pojo")
public class Season implements Serializable
{

    @JsonProperty("seasonId")
    private Integer seasonId;
    @JsonProperty("phaseId")
    private Integer phaseId;
    @JsonProperty("sequenceNo")
    private Integer sequenceNo;
    @JsonProperty("diffId")
    private String diffId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -3741673682150184437L;

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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
