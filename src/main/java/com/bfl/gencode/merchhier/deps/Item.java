package com.bfl.gencode.merchhier.deps;

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
    "approver",
    "purchase_type",
    "group_no",
    "group_name",
    "supplier",
    "supplier_name",
    "category",
    "discount_on_retail",
    "status",
    "rejection_reason",
    "otb_calc_type",
    "create_id",
    "create_datetime",
    "last_update_id",
    "last_update_datetime",
    "brand",
    "subcategory",
    "profit_calc_type",
    "report_category",
    "uda_value"
})
public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("group_code")
	private String groupCode;
	@JsonProperty("division")
	private String division;
	@JsonProperty("div_name")
	private String divisionName;
	@JsonProperty("dept")
	private Integer dept;
	@JsonProperty("dept_name")
	private String deptName;
	@JsonProperty("buyer")
	private String buyer;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("approver")
	private Integer approver;
	@JsonProperty("purchase_type")
	private String purchaseType;
	@JsonProperty("group_no")
	private Integer groupNo;
	@JsonProperty("group_name")
	private String groupName;
	@JsonProperty("supplier")
	private String supplier;
	@JsonProperty("supplier_name")
	private String supplierName;
	@JsonProperty("category")
	private String category;
	@JsonProperty("discount_on_retail")
	private Integer discountOnRetail;
	@JsonProperty("status")
	private String status;
	@JsonProperty("rejection_reason")
	private String rejectionReason;
	@JsonProperty("otb_calc_type")
	private String otbCalcType;
	@JsonProperty("create_id")
	private String createId;
	@JsonProperty("create_datetime")
	private String createDateTime;
	@JsonProperty("last_update_id")
	private String lastUpdateId;
	@JsonProperty("last_update_datetime")
	private String lastUpdateDatetime;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("subcategory")
	private String subcategory;
	@JsonProperty("profit_calc_type")
	private String profitCalType;
	@JsonProperty("report_category")
	private String reportCategory;
	@JsonProperty("uda_value")
	private String udaValue;
	
	@JsonProperty("dept")
	public Integer getDept() {
		return dept;
	}

	@JsonProperty("dept")
	public void setDept(Integer dept) {
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

	@JsonProperty("buyer")
	public String getBuyer() {
		return buyer;
	}

	@JsonProperty("buyer")
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	@JsonProperty("purchase_type")
	public String getPurchaseType() {
		return purchaseType;
	}

	@JsonProperty("purchase_type")
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	@JsonProperty("otb_calc_type")
	public String getOtbCalcType() {
		return otbCalcType;
	}

	@JsonProperty("otb_calc_type")
	public void setOtbCalcType(String otbCalcType) {
		this.otbCalcType = otbCalcType;
	}

	@JsonProperty("create_datetime")
	public String getCreateDateTime() {
		return createDateTime;
	}

	@JsonProperty("create_datetime")
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

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

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("approver")
	public Integer getApprover() {
		return approver;
	}

	@JsonProperty("approver")
	public void setApprover(Integer approver) {
		this.approver = approver;
	}

	@JsonProperty("group_no")
	public Integer getGroupNo() {
		return groupNo;
	}

	@JsonProperty("group_no")
	public void setGroupNo(Integer groupNo) {
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

	@JsonProperty("supplier")
	public String getSupplier() {
		return supplier;
	}

	@JsonProperty("supplier")
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@JsonProperty("supplier_name")
	public String getSupplierName() {
		return supplierName;
	}

	@JsonProperty("supplier_name")
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("discount_on_retail")
	public Integer getDiscountOnRetail() {
		return discountOnRetail;
	}

	@JsonProperty("discount_on_retail")
	public void setDiscountOnRetail(Integer discountOnRetail) {
		this.discountOnRetail = discountOnRetail;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("rejection_reason")
	public String getRejectionReason() {
		return rejectionReason;
	}

	@JsonProperty("rejection_reason")
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	@JsonProperty("create_id")
	public String getCreateId() {
		return createId;
	}

	@JsonProperty("create_id")
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@JsonProperty("last_update_id")
	public String getLastUpdateId() {
		return lastUpdateId;
	}

	@JsonProperty("last_update_id")
	public void setLastUpdateId(String lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	@JsonProperty("last_update_datetime")
	public String getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}

	@JsonProperty("last_update_datetime")
	public void setLastUpdateDatetime(String lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
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
	
	@JsonProperty("profit_calc_type")
	public String getProfitCalType() {
		return profitCalType;
	}

	@JsonProperty("profit_calc_type")
	public void setProfitCalType(String profitCalType) {
		this.profitCalType = profitCalType;
	}
	
	@JsonProperty("report_category")
	public String getReportCategory() {
		return reportCategory;
	}
	
	@JsonProperty("report_category")
	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}
	
	@JsonProperty("uda_value")
	public String getUdaValue() {
		return udaValue;
	}
	
	@JsonProperty("uda_value")
	public void setUdaValue(String udaValue) {
		this.udaValue = udaValue;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
}
