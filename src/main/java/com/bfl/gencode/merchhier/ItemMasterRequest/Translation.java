
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
    "language",
    "shortDescription",
    "itemDescription",
    "itemDescriptionSecondary"
})
@Generated("jsonschema2pojo")
public class Translation implements Serializable
{

    @JsonProperty("language")
    private Integer language;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("itemDescriptionSecondary")
    private String itemDescriptionSecondary;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 4989861457912008894L;

    @JsonProperty("language")
    public Integer getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Integer language) {
        this.language = language;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("itemDescriptionSecondary")
    public String getItemDescriptionSecondary() {
        return itemDescriptionSecondary;
    }

    @JsonProperty("itemDescriptionSecondary")
    public void setItemDescriptionSecondary(String itemDescriptionSecondary) {
        this.itemDescriptionSecondary = itemDescriptionSecondary;
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
