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
    "languageCode",
    "languageName",
    "isoCode",
    "itemDescription",
    "itemDescriptionSecondary",
    "shortDescription",
    "createDateTime",
    "updateDateTime"
})
public class ItemTranslation {

    @JsonProperty("languageCode")
    private Integer languageCode;
    @JsonProperty("languageName")
    private String languageName;
    @JsonProperty("isoCode")
    private String isoCode;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("itemDescriptionSecondary")
    private String itemDescriptionSecondary;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("languageCode")
    public Integer getLanguageCode() {
        return languageCode;
    }

    @JsonProperty("languageCode")
    public void setLanguageCode(Integer languageCode) {
        this.languageCode = languageCode;
    }

    @JsonProperty("languageName")
    public String getLanguageName() {
        return languageName;
    }

    @JsonProperty("languageName")
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @JsonProperty("isoCode")
    public String getIsoCode() {
        return isoCode;
    }

    @JsonProperty("isoCode")
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
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

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
