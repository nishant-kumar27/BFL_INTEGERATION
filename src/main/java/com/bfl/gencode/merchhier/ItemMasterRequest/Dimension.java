package com.bfl.gencode.merchhier.ItemMasterRequest;

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
    "dimensionObject",
    "tareWeight",
    "tareType",
    "lwhUom",
    "length",
    "width",
    "height",
    "liquidVolume",
    "liquidVolumeUom",
    "statisticalCase",
    "weightUom",
    "weight",
    "netWeight",
    "presentationMethod"
})

public class Dimension implements Serializable {
	
    @JsonProperty("dimensionObject")
    private String dimensionObject;
    @JsonProperty("tareWeight")
    private Integer tareWeight;
    @JsonProperty("tareType")
    private String tareType;
    @JsonProperty("lwhUom")
    private String lwhUom;
    @JsonProperty("length")
    private Integer length;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("liquidVolume")
    private Integer liquidVolume;
    @JsonProperty("liquidVolumeUom")
    private String liquidVolumeUom;
    @JsonProperty("statisticalCase")
    private String statisticalCase;
    @JsonProperty("weightUom")
    private String weightUom;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("netWeight")
    private Integer netWeight;
    @JsonProperty("presentationMethod")
    private String presentationMethod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 4121905231901540697L;

    @JsonProperty("dimensionObject")
    public String getDimensionObject() {
        return dimensionObject;
    }

    @JsonProperty("dimensionObject")
    public void setDimensionObject(String dimensionObject) {
        this.dimensionObject = dimensionObject;
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

    @JsonProperty("lwhUom")
    public String getLwhUom() {
        return lwhUom;
    }

    @JsonProperty("lwhUom")
    public void setLwhUom(String lwhUom) {
        this.lwhUom = lwhUom;
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
    public String getStatisticalCase() {
        return statisticalCase;
    }

    @JsonProperty("statisticalCase")
    public void setStatisticalCase(String statisticalCase) {
        this.statisticalCase = statisticalCase;
    }

    @JsonProperty("weightUom")
    public String getWeightUom() {
        return weightUom;
    }

    @JsonProperty("weightUom")
    public void setWeightUom(String weightUom) {
        this.weightUom = weightUom;
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

    @JsonProperty("presentationMethod")
    public String getPresentationMethod() {
        return presentationMethod;
    }

    @JsonProperty("presentationMethod")
    public void setPresentationMethod(String presentationMethod) {
        this.presentationMethod = presentationMethod;
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
