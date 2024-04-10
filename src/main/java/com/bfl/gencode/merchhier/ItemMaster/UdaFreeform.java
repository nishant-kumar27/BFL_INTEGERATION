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
    "udaText",
    "createDateTime",
    "updateDateTime"
})
public class UdaFreeform {

    @JsonProperty("udaId")
    private Integer udaId;
    @JsonProperty("udaDescription")
    private String udaDescription;
    @JsonProperty("udaText")
    private String udaText;
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

    @JsonProperty("udaText")
    public String getUdaText() {
        return udaText;
    }

    @JsonProperty("udaText")
    public void setUdaText(String udaText) {
        this.udaText = udaText;
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
