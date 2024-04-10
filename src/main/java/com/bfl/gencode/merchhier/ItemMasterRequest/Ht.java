
package com.bfl.gencode.merchhier.ItemMasterRequest;

import java.io.Serializable;
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
    "hts",
    "importCountry",
    "originCountry",
    "effectFrom",
    "effectTo",
    "clearingZoneId",
    "status",
    "assessments"
})
@Generated("jsonschema2pojo")
public class Ht implements Serializable
{

    @JsonProperty("hts")
    private String hts;
    @JsonProperty("importCountry")
    private String importCountry;
    @JsonProperty("originCountry")
    private String originCountry;
    @JsonProperty("effectFrom")
    private String effectFrom;
    @JsonProperty("effectTo")
    private String effectTo;
    @JsonProperty("clearingZoneId")
    private String clearingZoneId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("assessments")
    private List<Assessment> assessments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -7487995983480573625L;

    @JsonProperty("hts")
    public String getHts() {
        return hts;
    }

    @JsonProperty("hts")
    public void setHts(String hts) {
        this.hts = hts;
    }

    @JsonProperty("importCountry")
    public String getImportCountry() {
        return importCountry;
    }

    @JsonProperty("importCountry")
    public void setImportCountry(String importCountry) {
        this.importCountry = importCountry;
    }

    @JsonProperty("originCountry")
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("originCountry")
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @JsonProperty("effectFrom")
    public String getEffectFrom() {
        return effectFrom;
    }

    @JsonProperty("effectFrom")
    public void setEffectFrom(String effectFrom) {
        this.effectFrom = effectFrom;
    }

    @JsonProperty("effectTo")
    public String getEffectTo() {
        return effectTo;
    }

    @JsonProperty("effectTo")
    public void setEffectTo(String effectTo) {
        this.effectTo = effectTo;
    }

    @JsonProperty("clearingZoneId")
    public String getClearingZoneId() {
        return clearingZoneId;
    }

    @JsonProperty("clearingZoneId")
    public void setClearingZoneId(String clearingZoneId) {
        this.clearingZoneId = clearingZoneId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("assessments")
    public List<Assessment> getAssessments() {
        return assessments;
    }

    @JsonProperty("assessments")
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
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
