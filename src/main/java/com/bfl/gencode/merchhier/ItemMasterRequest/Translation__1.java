
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
    "supplierDiff1",
    "supplierDiff2",
    "supplierDiff3",
    "supplierDiff4",
    "supplierLabel"
})
@Generated("jsonschema2pojo")
public class Translation__1 implements Serializable
{

    @JsonProperty("language")
    private Integer language;
    @JsonProperty("supplierDiff1")
    private String supplierDiff1;
    @JsonProperty("supplierDiff2")
    private String supplierDiff2;
    @JsonProperty("supplierDiff3")
    private String supplierDiff3;
    @JsonProperty("supplierDiff4")
    private String supplierDiff4;
    @JsonProperty("supplierLabel")
    private String supplierLabel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -4350279170123069623L;

    @JsonProperty("language")
    public Integer getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Integer language) {
        this.language = language;
    }

    @JsonProperty("supplierDiff1")
    public String getSupplierDiff1() {
        return supplierDiff1;
    }

    @JsonProperty("supplierDiff1")
    public void setSupplierDiff1(String supplierDiff1) {
        this.supplierDiff1 = supplierDiff1;
    }

    @JsonProperty("supplierDiff2")
    public String getSupplierDiff2() {
        return supplierDiff2;
    }

    @JsonProperty("supplierDiff2")
    public void setSupplierDiff2(String supplierDiff2) {
        this.supplierDiff2 = supplierDiff2;
    }

    @JsonProperty("supplierDiff3")
    public String getSupplierDiff3() {
        return supplierDiff3;
    }

    @JsonProperty("supplierDiff3")
    public void setSupplierDiff3(String supplierDiff3) {
        this.supplierDiff3 = supplierDiff3;
    }

    @JsonProperty("supplierDiff4")
    public String getSupplierDiff4() {
        return supplierDiff4;
    }

    @JsonProperty("supplierDiff4")
    public void setSupplierDiff4(String supplierDiff4) {
        this.supplierDiff4 = supplierDiff4;
    }

    @JsonProperty("supplierLabel")
    public String getSupplierLabel() {
        return supplierLabel;
    }

    @JsonProperty("supplierLabel")
    public void setSupplierLabel(String supplierLabel) {
        this.supplierLabel = supplierLabel;
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
