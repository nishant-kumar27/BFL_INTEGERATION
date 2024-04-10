
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
    "vatType",
    "vatRegion",
    "vatCode",
    "activeDate",
    "reverseVatInd"
})
@Generated("jsonschema2pojo")
public class Vat implements Serializable
{

    @JsonProperty("vatType")
    private String vatType;
    @JsonProperty("vatRegion")
    private Integer vatRegion;
    @JsonProperty("vatCode")
    private String vatCode;
    @JsonProperty("activeDate")
    private String activeDate;
    @JsonProperty("reverseVatInd")
    private String reverseVatInd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 8121148976140038885L;

    @JsonProperty("vatType")
    public String getVatType() {
        return vatType;
    }

    @JsonProperty("vatType")
    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    @JsonProperty("vatRegion")
    public Integer getVatRegion() {
        return vatRegion;
    }

    @JsonProperty("vatRegion")
    public void setVatRegion(Integer vatRegion) {
        this.vatRegion = vatRegion;
    }

    @JsonProperty("vatCode")
    public String getVatCode() {
        return vatCode;
    }

    @JsonProperty("vatCode")
    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    @JsonProperty("activeDate")
    public String getActiveDate() {
        return activeDate;
    }

    @JsonProperty("activeDate")
    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    @JsonProperty("reverseVatInd")
    public String getReverseVatInd() {
        return reverseVatInd;
    }

    @JsonProperty("reverseVatInd")
    public void setReverseVatInd(String reverseVatInd) {
        this.reverseVatInd = reverseVatInd;
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
