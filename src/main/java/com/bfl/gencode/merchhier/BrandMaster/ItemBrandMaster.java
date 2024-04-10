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
	"brandname",
    "branddescription",
    "createdatetime",
    "lastupdatedatetime"
})
public class ItemBrandMaster implements Serializable {
	
    @JsonProperty("brandname")
    private String brandName;
    
    @JsonProperty("branddescription")
    private String brandDescription;
    
    @JsonProperty("createdatetime")
    private String createDatetime;
    
    @JsonProperty("lastupdatedatetime")
    private String lastUpdateDatetime;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = -2873183883751730221L;
    
    @JsonProperty("brandname")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brandname")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("branddescription")
    public String getBrandDescription() {
        return brandDescription;
    }

    @JsonProperty("branddescription")
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    @JsonProperty("createdatetime")
    public String getCreateDatetime() {
        return createDatetime;
    }

    @JsonProperty("createdatetime")
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("lastupdatedatetime")
    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    @JsonProperty("lastupdatedatetime")
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
