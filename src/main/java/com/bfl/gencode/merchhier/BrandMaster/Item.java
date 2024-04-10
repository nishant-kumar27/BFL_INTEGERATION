package com.bfl.gencode.merchhier.BrandMaster;

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
    "brandName",
    "brandDescription",
    "createDatetime",
    "lastUpdateDatetime"
})
public class Item implements Serializable {

    @JsonProperty("brandName")
    private String brandName;
    
    @JsonProperty("brandDescription")
    private String brandDescription;
    
    @JsonProperty("createDatetime")
    private String createDatetime;
    
    @JsonProperty("lastUpdateDatetime")
    private String lastUpdateDatetime;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -2873183883751730221L;
    
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

    @JsonProperty("createDatetime")
    public String getCreateDatetime() {
        return createDatetime;
    }

    @JsonProperty("createDatetime")
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("lastUpdateDatetime")
    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    @JsonProperty("lastUpdateDatetime")
    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
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
