
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
    "udaId",
    "displayType",
    "udaDate",
    "udaValue",
    "udaText"
})
public class Uda implements Serializable {
	
    @JsonProperty("udaId")
    private Integer udaId;
    
    @JsonProperty("displayType")
    private String displayType;
    
    @JsonProperty("udaDate")
    private String udaDate;
    
    @JsonProperty("udaValue")
    private String udaValue;
    
    @JsonProperty("udaText")
    private String udaText;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -5188565573901737976L;

    @JsonProperty("udaId")
    public Integer getUdaId() {
        return udaId;
    }

    @JsonProperty("udaId")
    public void setUdaId(Integer udaId) {
        this.udaId = udaId;
    }

    @JsonProperty("displayType")
    public String getDisplayType() {
        return displayType;
    }

    @JsonProperty("displayType")
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    @JsonProperty("udaDate")
    public String getUdaDate() {
        return udaDate;
    }

    @JsonProperty("udaDate")
    public void setUdaDate(String udaDate) {
        this.udaDate = udaDate;
    }

    @JsonProperty("udaValue")
    public String getUdaValue() {
        return udaValue;
    }

    @JsonProperty("udaValue")
    public void setUdaValue(String udaValue) {
        this.udaValue = udaValue;
    }

    @JsonProperty("udaText")
    public String getUdaText() {
        return udaText;
    }

    @JsonProperty("udaText")
    public void setUdaText(String udaText) {
        this.udaText = udaText;
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
