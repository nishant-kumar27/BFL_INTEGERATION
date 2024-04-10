package com.bfl.gencode.merchhier.subcategory;

import java.util.HashMap;
import java.util.List;
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
    "subclass",
    "subclassname",
    "class",
    "dept",
    "uniquesubclassid",
    "uniqueClassId",
    "createDateTime",
    "updateDateTime",
    "customFlexAttribute",
    "groupcode",
    "supplier",
    "sup_name",
    "country_id",
    "cacheTimestamp"
})

public class Item {
	
    @JsonProperty("action")
    private String action;
    
    @JsonProperty("hierarchyLevel")
    private String hierarchyLevel;
    
    @JsonProperty("subclass")
    private Integer subclass;
    
    @JsonProperty("subclassname")
    private String subclassName;
    
    @JsonProperty("class")
    private Integer _class;
    
    @JsonProperty("dept")
    private Integer dept;
    
    @JsonProperty("uniquesubclassid")
    private Integer uniqueSubclassId;
    
    @JsonProperty("uniqueClassId")
    private Integer uniqueClassId;
    
    @JsonProperty("groupcode")
    private String groupCode;
    
    @JsonProperty("createDateTime")
    private String createDateTime;
    
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute = null;
    
    @JsonProperty("cacheTimestamp")
    private String cacheTimestamp;
    
    @JsonProperty("supplier")
    private String supplier;
    
    @JsonProperty("sup_name")
    private String supTsCode;
    
    @JsonProperty("country_id")
    private String countryId;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
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

    @JsonProperty("subclass")
    public Integer getSubclass() {
        return subclass;
    }

    @JsonProperty("subclass")
    public void setSubclass(Integer subclass) {
        this.subclass = subclass;
    }

    @JsonProperty("subclassname")
    public String getSubclassName() {
        return subclassName;
    }

    @JsonProperty("subclassname")
    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    @JsonProperty("class")
    public Integer getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(Integer _class) {
        this._class = _class;
    }

    @JsonProperty("dept")
    public Integer getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(Integer dept) {
        this.dept = dept;
    }

    @JsonProperty("uniquesubclassid")
    public Integer getUniqueSubclassId() {
        return uniqueSubclassId;
    }

    @JsonProperty("uniquesubclassid")
    public void setUniqueSubclassId(Integer uniqueSubclassId) {
        this.uniqueSubclassId = uniqueSubclassId;
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
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
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
    
    @JsonProperty("groupcode")
	public String getGroupCode() {
		return groupCode;
	}
	
	@JsonProperty("groupcode")
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@JsonProperty("supplier")
	public String getSupplier() {
		return supplier;
	}

	@JsonProperty("supplier")
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@JsonProperty("sup_name")    
	public String getSupTsCode() {
		return supTsCode;
	}

	@JsonProperty("sup_name")
	public void setSupTsCode(String supTsCode) {
		this.supTsCode = supTsCode;
	}
	
    @JsonProperty("country_id")
	public String getCountryId() {
		return countryId;
	}
    
    @JsonProperty("country_id")
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
}
