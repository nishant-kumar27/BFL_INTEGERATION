
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
    "imageName",
    "imageAddress",
    "imageDescription",
    "imageType",
    "primaryImageInd",
    "displayPriority",
    "translation"
})
@Generated("jsonschema2pojo")
public class Image implements Serializable
{

    @JsonProperty("imageName")
    private String imageName;
    @JsonProperty("imageAddress")
    private String imageAddress;
    @JsonProperty("imageDescription")
    private String imageDescription;
    @JsonProperty("imageType")
    private String imageType;
    @JsonProperty("primaryImageInd")
    private String primaryImageInd;
    @JsonProperty("displayPriority")
    private Integer displayPriority;
    @JsonProperty("translation")
    private List<Translation__2> translation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 2673803692928380505L;

    @JsonProperty("imageName")
    public String getImageName() {
        return imageName;
    }

    @JsonProperty("imageName")
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @JsonProperty("imageAddress")
    public String getImageAddress() {
        return imageAddress;
    }

    @JsonProperty("imageAddress")
    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    @JsonProperty("imageDescription")
    public String getImageDescription() {
        return imageDescription;
    }

    @JsonProperty("imageDescription")
    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @JsonProperty("imageType")
    public String getImageType() {
        return imageType;
    }

    @JsonProperty("imageType")
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @JsonProperty("primaryImageInd")
    public String getPrimaryImageInd() {
        return primaryImageInd;
    }

    @JsonProperty("primaryImageInd")
    public void setPrimaryImageInd(String primaryImageInd) {
        this.primaryImageInd = primaryImageInd;
    }

    @JsonProperty("displayPriority")
    public Integer getDisplayPriority() {
        return displayPriority;
    }

    @JsonProperty("displayPriority")
    public void setDisplayPriority(Integer displayPriority) {
        this.displayPriority = displayPriority;
    }

    @JsonProperty("translation")
    public List<Translation__2> getTranslation() {
        return translation;
    }

    @JsonProperty("translation")
    public void setTranslation(List<Translation__2> translation) {
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
