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
    "dimensionObject",
    "dimensionObjectDescription",
    "presentationMethod",
    "presentationMethodDescription",
    "length",
    "width",
    "height",
    "lwhUom",
    "weight",
    "netWeight",
    "weightUom",
    "liquidVolume",
    "liquidVolumeUom",
    "statisticalCase",
    "tareWeight",
    "tareType",
    "createDateTime",
    "updateDateTime"
})
public class ItemSupplierCountryDimension {

    @JsonProperty("dimensionObject")
    private String dimensionObject;
    @JsonProperty("dimensionObjectDescription")
    private String dimensionObjectDescription;
    @JsonProperty("presentationMethod")
    private String presentationMethod;
    @JsonProperty("presentationMethodDescription")
    private String presentationMethodDescription;
    @JsonProperty("length")
    private Integer length;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("lwhUom")
    private String lwhUom;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("netWeight")
    private Integer netWeight;
    @JsonProperty("weightUom")
    private String weightUom;
    @JsonProperty("liquidVolume")
    private Integer liquidVolume;
    @JsonProperty("liquidVolumeUom")
    private String liquidVolumeUom;
    @JsonProperty("statisticalCase")
    private Integer statisticalCase;
    @JsonProperty("tareWeight")
    private Integer tareWeight;
    @JsonProperty("tareType")
    private String tareType;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dimensionObject")
    public String getDimensionObject() {
        return dimensionObject;
    }

    @JsonProperty("dimensionObject")
    public void setDimensionObject(String dimensionObject) {
        this.dimensionObject = dimensionObject;
    }

    @JsonProperty("dimensionObjectDescription")
    public String getDimensionObjectDescription() {
        return dimensionObjectDescription;
    }

    @JsonProperty("dimensionObjectDescription")
    public void setDimensionObjectDescription(String dimensionObjectDescription) {
        this.dimensionObjectDescription = dimensionObjectDescription;
    }

    @JsonProperty("presentationMethod")
    public String getPresentationMethod() {
        return presentationMethod;
    }

    @JsonProperty("presentationMethod")
    public void setPresentationMethod(String presentationMethod) {
        this.presentationMethod = presentationMethod;
    }

    @JsonProperty("presentationMethodDescription")
    public String getPresentationMethodDescription() {
        return presentationMethodDescription;
    }

    @JsonProperty("presentationMethodDescription")
    public void setPresentationMethodDescription(String presentationMethodDescription) {
        this.presentationMethodDescription = presentationMethodDescription;
    }

    @JsonProperty("length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Integer length) {
        this.length = length;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("lwhUom")
    public String getLwhUom() {
        return lwhUom;
    }

    @JsonProperty("lwhUom")
    public void setLwhUom(String lwhUom) {
        this.lwhUom = lwhUom;
    }

    @JsonProperty("weight")
    public Integer getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @JsonProperty("netWeight")
    public Integer getNetWeight() {
        return netWeight;
    }

    @JsonProperty("netWeight")
    public void setNetWeight(Integer netWeight) {
        this.netWeight = netWeight;
    }

    @JsonProperty("weightUom")
    public String getWeightUom() {
        return weightUom;
    }

    @JsonProperty("weightUom")
    public void setWeightUom(String weightUom) {
        this.weightUom = weightUom;
    }

    @JsonProperty("liquidVolume")
    public Integer getLiquidVolume() {
        return liquidVolume;
    }

    @JsonProperty("liquidVolume")
    public void setLiquidVolume(Integer liquidVolume) {
        this.liquidVolume = liquidVolume;
    }

    @JsonProperty("liquidVolumeUom")
    public String getLiquidVolumeUom() {
        return liquidVolumeUom;
    }

    @JsonProperty("liquidVolumeUom")
    public void setLiquidVolumeUom(String liquidVolumeUom) {
        this.liquidVolumeUom = liquidVolumeUom;
    }

    @JsonProperty("statisticalCase")
    public Integer getStatisticalCase() {
        return statisticalCase;
    }

    @JsonProperty("statisticalCase")
    public void setStatisticalCase(Integer statisticalCase) {
        this.statisticalCase = statisticalCase;
    }

    @JsonProperty("tareWeight")
    public Integer getTareWeight() {
        return tareWeight;
    }

    @JsonProperty("tareWeight")
    public void setTareWeight(Integer tareWeight) {
        this.tareWeight = tareWeight;
    }

    @JsonProperty("tareType")
    public String getTareType() {
        return tareType;
    }

    @JsonProperty("tareType")
    public void setTareType(String tareType) {
        this.tareType = tareType;
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
