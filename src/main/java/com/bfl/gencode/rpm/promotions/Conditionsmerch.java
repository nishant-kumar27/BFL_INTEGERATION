package com.bfl.gencode.rpm.promotions;

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
    "condmerchid",
    "merchlevel",
    "dept",
    "uniqueclass",
    "subclass",
    "uniquesubclass",
    "item",
    "diffid",
    "excludeind",
    "canceldatetime",
    "suppliersite",
    "brandname",
    "class"
})
public class Conditionsmerch {

    @JsonProperty("condmerchid")
    private String condmerchid;
    @JsonProperty("merchlevel")
    private String merchlevel;
    @JsonProperty("dept")
    private String dept;
    @JsonProperty("uniqueclass")
    private String uniqueclass;
    @JsonProperty("subclass")
    private String subclass;
    @JsonProperty("uniquesubclass")
    private String uniquesubclass;
    @JsonProperty("item")
    private String item;
    @JsonProperty("diffid")
    private String diffid;
    @JsonProperty("excludeind")
    private String excludeind;
    @JsonProperty("canceldatetime")
    private String canceldatetime;
    @JsonProperty("suppliersite")
    private String suppliersite;
    @JsonProperty("brandname")
    private String brandname;
    @JsonProperty("class")
    private String _class;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("condmerchid")
    public String getCondmerchid() {
        return condmerchid;
    }

    @JsonProperty("condmerchid")
    public void setCondmerchid(String condmerchid) {
        this.condmerchid = condmerchid;
    }

    @JsonProperty("merchlevel")
    public String getMerchlevel() {
        return merchlevel;
    }

    @JsonProperty("merchlevel")
    public void setMerchlevel(String merchlevel) {
        this.merchlevel = merchlevel;
    }

    @JsonProperty("dept")
    public String getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(String dept) {
        this.dept = dept;
    }

    @JsonProperty("uniqueclass")
    public String getUniqueclass() {
        return uniqueclass;
    }

    @JsonProperty("uniqueclass")
    public void setUniqueclass(String uniqueclass) {
        this.uniqueclass = uniqueclass;
    }

    @JsonProperty("subclass")
    public String getSubclass() {
        return subclass;
    }

    @JsonProperty("subclass")
    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    @JsonProperty("uniquesubclass")
    public String getUniquesubclass() {
        return uniquesubclass;
    }

    @JsonProperty("uniquesubclass")
    public void setUniquesubclass(String uniquesubclass) {
        this.uniquesubclass = uniquesubclass;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("diffid")
    public String getDiffid() {
        return diffid;
    }

    @JsonProperty("diffid")
    public void setDiffid(String diffid) {
        this.diffid = diffid;
    }

    @JsonProperty("excludeind")
    public String getExcludeind() {
        return excludeind;
    }

    @JsonProperty("excludeind")
    public void setExcludeind(String excludeind) {
        this.excludeind = excludeind;
    }

    @JsonProperty("canceldatetime")
    public String getCanceldatetime() {
        return canceldatetime;
    }

    @JsonProperty("canceldatetime")
    public void setCanceldatetime(String canceldatetime) {
        this.canceldatetime = canceldatetime;
    }

    @JsonProperty("suppliersite")
    public String getSuppliersite() {
        return suppliersite;
    }

    @JsonProperty("suppliersite")
    public void setSuppliersite(String suppliersite) {
        this.suppliersite = suppliersite;
    }

    @JsonProperty("brandname")
    public String getBrandname() {
        return brandname;
    }

    @JsonProperty("brandname")
    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}
