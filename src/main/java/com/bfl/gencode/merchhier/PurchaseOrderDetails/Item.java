package com.bfl.gencode.merchhier.PurchaseOrderDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"buyer_name",
	"supplier",
	"sup_currecy_code",
	"sup_name",
	"sup_add_1",
	"sup_add_2",
	"sup_add_3",
	"sup_city",
	"sup_state",
	"sup_post",
	"sup_contact_name",
	"sup_contact_phone",
	"sup_contact_email",
	"invoice_no",
	"order_no",
	"status",
	"approve_date",
	"terms",
	"freight_terms",
	"import_country_id",
	"total_value",
	"loc_name",
	"add_1",
	"add_2",
	"city",
	"state",
	"total_vat_value",
	"total_net_value",
	"ord_desc",
	"grand_total_amount",
	"total_discount",
	"total_ord_qty",
	"post",
	"comment_desc",
	"location",
	"total_summ_discount",
	"total_summ_qty",
	"total_summ_val", 
	"total_summ_qty",
	"total_summ_grand_amt",
	"total_summ_vat_value",
	"earliest_ship_date", 
	"latest_ship_date"
})
public class Item {
	
	@JsonProperty("buyer_name")
	private String buyerName;
	@JsonProperty("supplier")
	private Integer supplier;
	@JsonProperty("sup_currecy_code")
	private String supCurrecyCode;
	@JsonProperty("sup_name")
	private String supName;
	@JsonProperty("sup_add_1")
	private String supAdd1;
	@JsonProperty("sup_add_2")
	private String supAdd2;
	@JsonProperty("sup_add_3")
	private String supAdd3;
	@JsonProperty("sup_city")
	private String supCity;
	@JsonProperty("sup_state")
	private String supState;
	@JsonProperty("sup_post")
	private String supPost;
	@JsonProperty("sup_contact_name")
	private String supContactName;
	@JsonProperty("sup_contact_phone")
	private String supContactPhone;
	@JsonProperty("sup_contact_email")
	private String supContactEmail;
	@JsonProperty("invoice_no")
	private String invoiceNo;
	@JsonProperty("order_no")
	private Integer orderNo;
	@JsonProperty("status")
	private String status;
	@JsonProperty("approve_date")
	private String approveDate;
	@JsonProperty("terms")
	private String terms;
	@JsonProperty("freight_terms")
	private String freightTerms;
	@JsonProperty("import_country_id")
	private String importCountryId;
	@JsonProperty("total_value")
	private Integer totalValue;
	@JsonProperty("total_vat_value")
	private String totalVatValue;
	@JsonProperty("total_net_value")
	private String totalNetValue;
	@JsonProperty("ord_desc")
	private String ordDesc;
	@JsonProperty("loc_name")
	private String locName;
	@JsonProperty("add_1")
	private String add1;
	@JsonProperty("add_2")
	private String add2;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("post")
	private String post;

	@JsonProperty("grand_total_amount")
	private String grandTotalAmount;

	@JsonProperty("total_discount")
	private String totalDiscount;

	@JsonProperty("total_ord_qty")
	private String totalQty;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("comment_desc")
	private String comments;
	
	@JsonProperty("total_summ_qty")
	private String totalSummQty;
	
	@JsonProperty("total_summ_val")
	private String totalSummVal;
	
	@JsonProperty("total_summ_discount")
	private String totalSummDiscount;
	
	@JsonProperty("total_summ_grand_amt")
	private String totalSummGrandAmt;
	
	@JsonProperty("total_summ_vat_value")
	private String totalSummVatVal;
	
	@JsonProperty("earliest_ship_date")
	private String earlistShipDate;
	
	@JsonProperty("latest_ship_date")
	private String latestShipDate;
	
	@JsonProperty("buyer_name")
	public String getBuyerName() {
		return buyerName;
	}

	@JsonProperty("buyer_name")
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@JsonProperty("supplier")
	public Integer getSupplier() {
		return supplier;
	}

	@JsonProperty("supplier")
	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}

	@JsonProperty("sup_currecy_code")
	public String getSupCurrecyCode() {
		return supCurrecyCode;
	}

	@JsonProperty("sup_currecy_code")
	public void setSupCurrecyCode(String supCurrecyCode) {
		this.supCurrecyCode = supCurrecyCode;
	}

	@JsonProperty("sup_name")
	public String getSupName() {
		return supName;
	}

	@JsonProperty("sup_name")
	public void setSupName(String supName) {
		this.supName = supName;
	}

	@JsonProperty("sup_add_1")
	public String getSupAdd1() {
		return supAdd1;
	}

	@JsonProperty("sup_add_1")
	public void setSupAdd1(String supAdd1) {
		this.supAdd1 = supAdd1;
	}

	@JsonProperty("sup_add_2")
	public String getSupAdd2() {
		return supAdd2;
	}

	@JsonProperty("sup_add_2")
	public void setSupAdd2(String supAdd2) {
		this.supAdd2 = supAdd2;
	}

	@JsonProperty("sup_add_3")
	public String getSupAdd3() {
		return supAdd3;
	}

	@JsonProperty("sup_add_3")
	public void setSupAdd3(String supAdd3) {
		this.supAdd3 = supAdd3;
	}

	@JsonProperty("sup_city")
	public String getSupCity() {
		return supCity;
	}

	@JsonProperty("sup_city")
	public void setSupCity(String supCity) {
		this.supCity = supCity;
	}

	@JsonProperty("sup_state")
	public String getSupState() {
		return supState;
	}

	@JsonProperty("sup_state")
	public void setSupState(String supState) {
		this.supState = supState;
	}

	@JsonProperty("sup_post")
	public String getSupPost() {
		return supPost;
	}

	@JsonProperty("sup_post")
	public void setSupPost(String supPost) {
		this.supPost = supPost;
	}

	@JsonProperty("sup_contact_name")
	public String getSupContactName() {
		return supContactName;
	}

	@JsonProperty("sup_contact_name")
	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}

	@JsonProperty("sup_contact_phone")
	public String getSupContactPhone() {
		return supContactPhone;
	}

	@JsonProperty("sup_contact_phone")
	public void setSupContactPhone(String supContactPhone) {
		this.supContactPhone = supContactPhone;
	}

	@JsonProperty("sup_contact_email")
	public String getSupContactEmail() {
		return supContactEmail;
	}

	@JsonProperty("sup_contact_email")
	public void setSupContactEmail(String supContactEmail) {
		this.supContactEmail = supContactEmail;
	}

	@JsonProperty("invoice_no")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	@JsonProperty("invoice_no")
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@JsonProperty("order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	@JsonProperty("order_no")
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("approve_date")
	public String getApproveDate() {
		return approveDate;
	}

	@JsonProperty("approve_date")
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
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

	@JsonProperty("import_country_id")
	public String getImportCountryId() {
		return importCountryId;
	}

	@JsonProperty("import_country_id")
	public void setImportCountryId(String importCountryId) {
		this.importCountryId = importCountryId;
	}

	@JsonProperty("total_value")
	public Integer getTotalValue() {
		return totalValue;
	}

	@JsonProperty("total_value")
	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}

	@JsonProperty("loc_name")
	public String getLocName() {
		return locName;
	}

	@JsonProperty("loc_name")
	public void setLocName(String locName) {
		this.locName = locName;
	}

	@JsonProperty("add_1")
	public String getAdd1() {
		return add1;
	}

	@JsonProperty("add_1")
	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	@JsonProperty("add_2")
	public String getAdd2() {
		return add2;
	}

	@JsonProperty("add_2")
	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("post")
	public String getPost() {
		return post;
	}

	@JsonProperty("post")
	public void setPost(String post) {
		this.post = post;
	}

	@JsonProperty("total_vat_value")
	public String getTotalVatValue() {
		return totalVatValue;
	}

	@JsonProperty("total_vat_value")
	public void setTotalVatValue(String totalVatValue) {
		this.totalVatValue = totalVatValue;
	}

	@JsonProperty("total_net_value")
	public String getTotalNetValue() {
		return totalNetValue;
	}

	@JsonProperty("total_net_value")
	public void setTotalNetValue(String totalNetValue) {
		this.totalNetValue = totalNetValue;
	}

	@JsonProperty("ord_desc")
	public String getOrdDesc() {
		return ordDesc;
	}

	@JsonProperty("ord_desc")
	public void setOrdDesc(String ordDesc) {
		this.ordDesc = ordDesc;
	}
	
	@JsonProperty("grand_total_amount")
	public String getGrandTotalAmount() {
		return grandTotalAmount;
	}
	
	@JsonProperty("grand_total_amount")
	public void setGrandTotalAmount(String grandTotalAmount) {
		this.grandTotalAmount = grandTotalAmount;
	}
	
	@JsonProperty("total_discount")
	public String getTotalDiscount() {
		return totalDiscount;
	}
	
	@JsonProperty("total_discount")
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	
	@JsonProperty("total_ord_qty")
	public String getTotalQty() {
		return totalQty;
	}
	
	@JsonProperty("total_ord_qty")
	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation(String location) {
		this.location = location;
	}

	@JsonProperty("comment_desc")
	public String getComments() {
		return comments;
	}

	@JsonProperty("comment_desc")
	public void setComments(String comments) {
		this.comments = comments;
	}

	@JsonProperty("total_summ_qty")
	public String getTotalSummQty() {
		return totalSummQty;
	}

	@JsonProperty("total_summ_qty")
	public void setTotalSummQty(String totalSummQty) {
		this.totalSummQty = totalSummQty;
	}

	@JsonProperty("total_summ_val")
	public String getTotalSummVal() {
		return totalSummVal;
	}

	@JsonProperty("total_summ_val")
	public void setTotalSummVal(String totalSummVal) {
		this.totalSummVal = totalSummVal;
	}
	
	@JsonProperty("total_summ_discount")
	public String getTotalSummDiscount() {
		return totalSummDiscount;
	}

	@JsonProperty("total_summ_discount")
	public void setTotalSummDiscount(String totalSummDiscount) {
		this.totalSummDiscount = totalSummDiscount;
	}

	@JsonProperty("total_summ_grand_amt")
	public String getTotalSummGrandAmt() {
		return totalSummGrandAmt;
	}

	@JsonProperty("total_summ_grand_amt")
	public void setTotalSummGrandAmt(String totalSummGrandAmt) {
		this.totalSummGrandAmt = totalSummGrandAmt;
	}

	@JsonProperty("total_summ_vat_value")
	public String getTotalSummVatVal() {
		return totalSummVatVal;
	}

	@JsonProperty("total_summ_vat_value")
	public void setTotalSummVatVal(String totalSummVatVal) {
		this.totalSummVatVal = totalSummVatVal;
	}

	@JsonProperty("earlist_ship_date")
	public String getEarlistShipDate() {
		return earlistShipDate;
	}

	@JsonProperty("earlist_ship_date")
	public void setEarlistShipDate(String earlistShipDate) {
		this.earlistShipDate = earlistShipDate;
	}
	
	@JsonProperty("latest_ship_date")
	public String getLatestShipDate() {
		return latestShipDate;
	}

	@JsonProperty("latest_ship_date")
	public void setLatestShipDate(String latestShipDate) {
		this.latestShipDate = latestShipDate;
	}
	
}