
package com.bfl.gencode.merchhier.CreateBrandMaster;

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
    "language",
    "brandDescription",
    "deleteInd"
})
@Generated("jsonschema2pojo")
public class Translation implements Serializable
{

    @JsonProperty("language")
    private Integer language;
    @JsonProperty("brandDescription")
    private String brandDescription;
    @JsonProperty("deleteInd")
    private String deleteInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 8173984016888095129L;

    @JsonProperty("language")
    public Integer getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Integer language) {
        this.language = language;
    }

    @JsonProperty("brandDescription")
    public String getBrandDescription() {
        return brandDescription;
    }

    @JsonProperty("brandDescription")
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    @JsonProperty("deleteInd")
    public String getDeleteInd() {
        return deleteInd;
    }

    @JsonProperty("deleteInd")
    public void setDeleteInd(String deleteInd) {
        this.deleteInd = deleteInd;
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
