package com.bfl.gencode.merchhier.category;

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
    "action",
    "hierarchyLevel",
    "class",
    "className",
    "classVatInd",
    "dept",
    "uniqueClassId",
    "createDateTime",
    "updateDateTime",
    "customFlexAttribute",
    "cacheTimestamp"
})
public class Item {

    @JsonProperty("action")
    private String action;
    @JsonProperty("hierarchyLevel")
    private String hierarchyLevel;
    @JsonProperty("class")
    private Integer _class;
    @JsonProperty("className")
    private String className;
    @JsonProperty("classVatInd")
    private String classVatInd;
    @JsonProperty("dept")
    private Integer dept;
    @JsonProperty("uniqueClassId")
    private Integer uniqueClassId;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("customFlexAttribute")
    private Object customFlexAttribute;
    @JsonProperty("cacheTimestamp")
    private String cacheTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("hierarchyLevel")
    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    @JsonProperty("hierarchyLevel")
    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    @JsonProperty("class")
    public Integer getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    @JsonProperty("className")
    public String getClassName() {
        return className;
    }

    @JsonProperty("className")
    public void setClassName(String className) {
        this.className = className;
    }

    @JsonProperty("classVatInd")
    public String getClassVatInd() {
        return classVatInd;
    }

    @JsonProperty("classVatInd")
    public void setClassVatInd(String classVatInd) {
        this.classVatInd = classVatInd;
    }

    @JsonProperty("dept")
    public Integer getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(Integer dept) {
        this.dept = dept;
    }

    @JsonProperty("uniqueClassId")
    public Integer getUniqueClassId() {
        return uniqueClassId;
    }

    @JsonProperty("uniqueClassId")
    public void setUniqueClassId(Integer uniqueClassId) {
        this.uniqueClassId = uniqueClassId;
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

    @JsonProperty("customFlexAttribute")
    public Object getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(Object customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
    }

    @JsonProperty("cacheTimestamp")
    public String getCacheTimestamp() {
        return cacheTimestamp;
    }

    @JsonProperty("cacheTimestamp")
    public void setCacheTimestamp(String cacheTimestamp) {
        this.cacheTimestamp = cacheTimestamp;
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
