
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
    "imageDescription"
})
@Generated("jsonschema2pojo")
public class Translation__2 implements Serializable
{

    @JsonProperty("language")
    private Integer language;
    @JsonProperty("imageDescription")
    private String imageDescription;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -7740646174540733075L;

    @JsonProperty("language")
    public Integer getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Integer language) {
        this.language = language;
    }

    @JsonProperty("imageDescription")
    public String getImageDescription() {
        return imageDescription;
    }

    @JsonProperty("imageDescription")
    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
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
