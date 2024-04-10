package com.bfl.gencode.merchhier.CreateBrandMaster;

import java.io.Serializable;
import java.util.LinkedHashMap;
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
    "brandName",
    "brandDescription",
    "deleteInd",
    "translation"
})

public class Item implements Serializable {

    @JsonProperty("brandName")
    private String brandName;
    
    @JsonProperty("brandDescription")
    private String brandDescription;
    
    @JsonProperty("deleteInd")
    private String deleteInd;
    
    @JsonProperty("translation")
    private List<Translation> translation;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = 9148120937968515913L;

    @JsonProperty("brandName")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brandName")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    @JsonProperty("translation")
    public List<Translation> getTranslation() {
        return translation;
    }

    @JsonProperty("translation")
    public void setTranslation(List<Translation> translation) {
        this.translation = translation;
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
