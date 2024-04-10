package com.bfl.dto;

public class CategoryDTO {
	
	private String action;
	
	private String hierarchyLevel;
	
	private Integer subclass;
	
	private String subclassName;
	
	//Class
	private Integer Category;
	
	private Integer Dept;
	
	private Integer uniqueSubclassId;
	
	private Integer uniqueClassId;
	
	private String createDateTime;
	
	private String updateDateTime;
	
	private String cacheTimestamp;
	
	private String remarks;
	
	private String trnDate;
	
	private String userId;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public Integer getSubclass() {
		return subclass;
	}

	public void setSubclass(Integer subclass) {
		this.subclass = subclass;
	}

	public String getSubclassName() {
		return subclassName;
	}

	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}

	public Integer getCategory() {
		return Category;
	}

	public void setCategory(Integer category) {
		Category = category;
	}

	public Integer getDept() {
		return Dept;
	}

	public void setDept(Integer dept) {
		Dept = dept;
	}

	public Integer getUniqueSubclassId() {
		return uniqueSubclassId;
	}

	public void setUniqueSubclassId(Integer uniqueSubclassId) {
		this.uniqueSubclassId = uniqueSubclassId;
	}

	public Integer getUniqueClassId() {
		return uniqueClassId;
	}

	public void setUniqueClassId(Integer uniqueClassId) {
		this.uniqueClassId = uniqueClassId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getCacheTimestamp() {
		return cacheTimestamp;
	}

	public void setCacheTimestamp(String cacheTimestamp) {
		this.cacheTimestamp = cacheTimestamp;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTrnDate() {
		return trnDate;
	}

	public void setTrnDate(String trnDate) {
		this.trnDate = trnDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}