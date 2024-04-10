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
    "hierarchyId",
    "sellingUnitRetail",
    "sellingUom",
    "multiUnitSellingUom",
    "country",
    "currencyCode",
    "multiUnits",
    "multiUnitRetail"
})

public class RetailByZone implements Serializable {
	
    @JsonProperty("hierarchyId")
    private Integer hierarchyId;
    @JsonProperty("sellingUnitRetail")
    private Double sellingUnitRetail;
    @JsonProperty("sellingUom")
    private String sellingUom;
    @JsonProperty("multiUnitSellingUom")
    private String multiUnitSellingUom;
    @JsonProperty("country")
    private String country;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("multiUnits")
    private Integer multiUnits;
    @JsonProperty("multiUnitRetail")
    private Double multiUnitRetail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 243645648900539777L;

    @JsonProperty("hierarchyId")
    public Integer getHierarchyId() {
        return hierarchyId;
    }

    @JsonProperty("hierarchyId")
    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    @JsonProperty("sellingUnitRetail")
    public Double getSellingUnitRetail() {
        return sellingUnitRetail;
    }

    @JsonProperty("sellingUnitRetail")
    public void setSellingUnitRetail(Double sellingUnitRetail) {
        this.sellingUnitRetail = sellingUnitRetail;
    }

    @JsonProperty("sellingUom")
    public String getSellingUom() {
        return sellingUom;
    }

    @JsonProperty("sellingUom")
    public void setSellingUom(String sellingUom) {
        this.sellingUom = sellingUom;
    }

    @JsonProperty("multiUnitSellingUom")
    public String getMultiUnitSellingUom() {
        return multiUnitSellingUom;
    }

    @JsonProperty("multiUnitSellingUom")
    public void setMultiUnitSellingUom(String multiUnitSellingUom) {
        this.multiUnitSellingUom = multiUnitSellingUom;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("multiUnits")
    public Integer getMultiUnits() {
        return multiUnits;
    }

    @JsonProperty("multiUnits")
    public void setMultiUnits(Integer multiUnits) {
        this.multiUnits = multiUnits;
    }

    @JsonProperty("multiUnitRetail")
    public Double getMultiUnitRetail() {
        return multiUnitRetail;
    }

    @JsonProperty("multiUnitRetail")
    public void setMultiUnitRetail(Double multiUnitRetail) {
        this.multiUnitRetail = multiUnitRetail;
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
