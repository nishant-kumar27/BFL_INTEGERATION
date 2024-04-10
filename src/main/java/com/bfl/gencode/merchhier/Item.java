package com.bfl.gencode.merchhier;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "group_code",
    "division",
    "dept",
    "dept_name",
    "gender",
    "buyer",
    "group_no",
    "group_name",
    "category",
    "brand",
    "subcategory",
    "supplier",
    "sup_name",
    "supplier_parent",
    "contact_name",
    "currency_code",
    "terms",
    "freight_terms",
    "subclass",
    "class",
    "dept",
    "department",
    "groupcode",
    "subclass_id",
    "subclass",
    "class",
	"groupcode",
	"subclass_id"
})

public class Item implements Serializable {
	
    @JsonProperty("group_code")
    private String groupCode;
    
    @JsonProperty("division")
    private String division;
    
    @JsonProperty("dept")
    private String dept;
    
    @JsonProperty("dept_name")
    private String deptName;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("buyer")
    private String buyer;
    
    @JsonProperty("group_no")
    private String groupNo;
    
    @JsonProperty("group_name")
    private String groupName;
    
    @JsonProperty("category")
    private String category;
    
    @JsonProperty("brand")
    private String brand;
    
    @JsonProperty("subcategory")
    private String subcategory;
    
    @JsonProperty("supplier")
    private String supplier;
    
    @JsonProperty("sup_name")
    private String supName;
    
    @JsonProperty("supplier_parent")
    private String supplierParent;
    
    @JsonProperty("contact_name")
    private String contactName;
    
    @JsonProperty("currency_code")
    private String currencyCode;
    
    @JsonProperty("terms")
    private String terms;
    
    @JsonProperty("freight_terms")
    private String freightTerms;
    
    @JsonProperty("subclass")
    private String subclass;
    
    @JsonProperty("class")
    private String _class;
    
	@JsonProperty("department")
	private String department;
	
	@JsonProperty("groupcode")
	private String group;
	
	@JsonProperty("subclass_id")
	private String subclassId;
    
    private final static long serialVersionUID = -4597027379438049971L;

    @JsonProperty("group_code")
    public String getGroupCode() {
        return groupCode;
    }

    @JsonProperty("group_code")
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonProperty("division")
    public String getDivision() {
        return division;
    }

    @JsonProperty("division")
    public void setDivision(String division) {
        this.division = division;
    }

    @JsonProperty("dept")
    public String getDept() {
        return dept;
    }

    @JsonProperty("dept")
    public void setDept(String dept) {
        this.dept = dept;
    }

    @JsonProperty("dept_name")
    public String getDeptName() {
        return deptName;
    }

    @JsonProperty("dept_name")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("buyer")
    public String getBuyer() {
        return buyer;
    }

    @JsonProperty("buyer")
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @JsonProperty("group_no")
    public String getGroupNo() {
        return groupNo;
    }

    @JsonProperty("group_no")
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    @JsonProperty("group_name")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("group_name")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("subcategory")
    public String getSubcategory() {
        return subcategory;
    }

    @JsonProperty("subcategory")
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
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
    public String getSupName() {
        return supName;
    }

    @JsonProperty("sup_name")
    public void setSupName(String supName) {
        this.supName = supName;
    }

    @JsonProperty("supplier_parent")
    public String getSupplierParent() {
        return supplierParent;
    }

    @JsonProperty("supplier_parent")
    public void setSupplierParent(String supplierParent) {
        this.supplierParent = supplierParent;
    }

    @JsonProperty("contact_name")
    public String getContactName() {
        return contactName;
    }

    @JsonProperty("contact_name")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("terms")
    public String getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(String terms) {
        this.terms = terms;
    }

    @JsonProperty("freight_terms")
    public String getFreightTerms() {
        return freightTerms;
    }

    @JsonProperty("freight_terms")
    public void setFreightTerms(String freightTerms) {
        this.freightTerms = freightTerms;
    }

    @JsonProperty("subclass")
	public String getSubclass() {
		return subclass;
	}
    
    @JsonProperty("subclass")
	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}
    
    @JsonProperty("class")
	public String get_class() {
		return _class;
	}
    
    @JsonProperty("class")
	public void set_class(String _class) {
		this._class = _class;
	}
    
	@JsonProperty("department")
	public String getDepartment() {
		return department;
	}
	
	@JsonProperty("department")
	public void setDepartment(String department) {
		this.department = department;
	}

	@JsonProperty("groupcode")
	public String getGroup() {
		return group;
	}

	@JsonProperty("groupcode")
	public void setGroup(String group) {
		this.group = group;
	}
	
	@JsonProperty("subclass_id")
	public String getSubclassId() {
		return subclassId;
	}
	
	@JsonProperty("subclass_id")
	public void setSubclassId(String subclassId) {
		this.subclassId = subclassId;
	}
	
}
