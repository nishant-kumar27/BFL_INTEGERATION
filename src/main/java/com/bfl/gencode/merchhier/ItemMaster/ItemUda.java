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
    "udaLov",
    "udaFreeform",
    "udaDate"
})
public class ItemUda {

    @JsonProperty("udaLov")
    private List<UdaLov> udaLov = null;
    @JsonProperty("udaFreeform")
    private List<UdaFreeform> udaFreeform = null;
    @JsonProperty("udaDate")
    private List<UdaDate> udaDate = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("udaLov")
    public List<UdaLov> getUdaLov() {
        return udaLov;
    }

    @JsonProperty("udaLov")
    public void setUdaLov(List<UdaLov> udaLov) {
        this.udaLov = udaLov;
    }

    @JsonProperty("udaFreeform")
    public List<UdaFreeform> getUdaFreeform() {
        return udaFreeform;
    }

    @JsonProperty("udaFreeform")
    public void setUdaFreeform(List<UdaFreeform> udaFreeform) {
        this.udaFreeform = udaFreeform;
    }

    @JsonProperty("udaDate")
    public List<UdaDate> getUdaDate() {
        return udaDate;
    }

    @JsonProperty("udaDate")
    public void setUdaDate(List<UdaDate> udaDate) {
        this.udaDate = udaDate;
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
