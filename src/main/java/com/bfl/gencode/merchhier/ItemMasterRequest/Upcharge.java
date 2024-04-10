
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
    "fromLocationType",
    "fromLocation",
    "toLocationType",
    "toLocation",
    "details"
})
@Generated("jsonschema2pojo")
public class Upcharge implements Serializable
{

    @JsonProperty("fromLocationType")
    private String fromLocationType;
    @JsonProperty("fromLocation")
    private String fromLocation;
    @JsonProperty("toLocationType")
    private String toLocationType;
    @JsonProperty("toLocation")
    private String toLocation;
    @JsonProperty("details")
    private List<Detail> details;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -259008196450495113L;

    @JsonProperty("fromLocationType")
    public String getFromLocationType() {
        return fromLocationType;
    }

    @JsonProperty("fromLocationType")
    public void setFromLocationType(String fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    @JsonProperty("fromLocation")
    public String getFromLocation() {
        return fromLocation;
    }

    @JsonProperty("fromLocation")
    public void setFromLocation(String fromLocation) {
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
    public String getToLocation() {
        return toLocation;
    }

    @JsonProperty("toLocation")
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
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
