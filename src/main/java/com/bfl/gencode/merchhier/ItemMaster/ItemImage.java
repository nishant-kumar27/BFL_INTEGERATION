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
    "imageName",
    "imageAddress",
    "imageType",
    "primaryImageInd",
    "displayPriority",
    "imageDescription",
    "createDateTime",
    "updateDateTime"
})
public class ItemImage {

    @JsonProperty("imageName")
    private String imageName;
    @JsonProperty("imageAddress")
    private String imageAddress;
    @JsonProperty("imageType")
    private String imageType;
    @JsonProperty("primaryImageInd")
    private String primaryImageInd;
    @JsonProperty("displayPriority")
    private Integer displayPriority;
    @JsonProperty("imageDescription")
    private String imageDescription;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("imageDescription")
    public String getImageDescription() {
        return imageDescription;
    }

    @JsonProperty("imageDescription")
    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
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
