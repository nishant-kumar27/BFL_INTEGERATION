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
    "udaId",
    "udaDescription",
    "udaValue",
    "udaValueDescription",
    "createDateTime",
    "updateDateTime"
})
public class UdaLov {

    @JsonProperty("udaId")
    private Integer udaId;
    @JsonProperty("udaDescription")
    private String udaDescription;
    @JsonProperty("udaValue")
    private Integer udaValue;
    @JsonProperty("udaValueDescription")
    private String udaValueDescription;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("udaId")
    public Integer getUdaId() {
        return udaId;
    }

    @JsonProperty("udaId")
    public void setUdaId(Integer udaId) {
        this.udaId = udaId;
    }

    @JsonProperty("udaDescription")
    public String getUdaDescription() {
        return udaDescription;
    }

    @JsonProperty("udaDescription")
    public void setUdaDescription(String udaDescription) {
        this.udaDescription = udaDescription;
    }

    @JsonProperty("udaValue")
    public Integer getUdaValue() {
        return udaValue;
    }

    @JsonProperty("udaValue")
    public void setUdaValue(Integer udaValue) {
        this.udaValue = udaValue;
    }

    @JsonProperty("udaValueDescription")
    public String getUdaValueDescription() {
        return udaValueDescription;
    }

    @JsonProperty("udaValueDescription")
    public void setUdaValueDescription(String udaValueDescription) {
        this.udaValueDescription = udaValueDescription;
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
