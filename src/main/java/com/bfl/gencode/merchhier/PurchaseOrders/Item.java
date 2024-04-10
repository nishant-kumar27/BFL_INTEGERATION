
package com.bfl.gencode.merchhier.PurchaseOrders;

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
    "group_code",
    "sup_name",
    "group_name",
    "written_date",
    "vendor_order_no",
    "bolno",
    "currency_code",
    "import_country_id",
    "not_after_date",
    "orig_approval_date",
    "buyer_name",
    "order_no",
    "create_datetime",
    "earliest_ship_date",
    "latest_ship_date",
    "terms_code",
    "unit_cost",
    "qty",
    "discount",
    "val",
    "comments",
    "shipment",
    "vat_value"
})
public class Item implements Serializable {
	
    @JsonProperty("group_code")
    private String groupCode;
    
    @JsonProperty("sup_name")
    private String supName;
    
    @JsonProperty("group_name")
    private String groupName;
    
    @JsonProperty("written_date")
    private String writtenDate;
    
    @JsonProperty("vendor_order_no")
    private String vendorOrderNo;
    
    @JsonProperty("bolno")
    private String bolno;
    
    @JsonProperty("currency_code")
    private String currencyCode;
    
    @JsonProperty("import_country_id")
    private String importCountryId;
    
    @JsonProperty("not_after_date")
    private String notAfterDate;
    
    @JsonProperty("orig_approval_date")
    private String origApprovalDate;
    
    @JsonProperty("buyer_name")
    private String buyerName;
    
    @JsonProperty("order_no")
    private Integer orderNo;
    
    @JsonProperty("create_datetime")
    private String createDatetime;
    
    @JsonProperty("earliest_ship_date")
    private String earliestShipDate;
    
    @JsonProperty("latest_ship_date")
    private String latestShipDate;
    
    @JsonProperty("terms_code")
    private String termsCode;
    
    @JsonProperty("unit_cost")
    private String unitCost;
    
    @JsonProperty("qty")
    private String qty;
    
    @JsonProperty("discount")
    private String discount;
    
    @JsonProperty("val")
    private String val;
    
    @JsonProperty("vat_value")
    private String vatValue;
    
    @JsonProperty("comments")
    private String comments;
    
    @JsonProperty("shipment")
    private String shipment;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    
    private final static long serialVersionUID = 925039934607524191L;

    @JsonProperty("group_code")
    public String getGroupCode() {
        return groupCode;
    }

    @JsonProperty("group_code")
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonProperty("sup_name")
    public String getSupName() {
        return supName;
    }

    @JsonProperty("sup_name")
    public void setSupName(String supName) {
        this.supName = supName;
    }

    @JsonProperty("group_name")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("group_name")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonProperty("written_date")
    public String getWrittenDate() {
        return writtenDate;
    }

    @JsonProperty("written_date")
    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    @JsonProperty("vendor_order_no")
    public String getVendorOrderNo() {
        return vendorOrderNo;
    }

    @JsonProperty("vendor_order_no")
    public void setVendorOrderNo(String vendorOrderNo) {
        this.vendorOrderNo = vendorOrderNo;
    }

    @JsonProperty("bolno")
    public String getBolno() {
        return bolno;
    }

    @JsonProperty("bolno")
    public void setBolno(String bolno) {
        this.bolno = bolno;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("import_country_id")
    public String getImportCountryId() {
        return importCountryId;
    }

    @JsonProperty("import_country_id")
    public void setImportCountryId(String importCountryId) {
        this.importCountryId = importCountryId;
    }

    @JsonProperty("not_after_date")
    public String getNotAfterDate() {
        return notAfterDate;
    }

    @JsonProperty("not_after_date")
    public void setNotAfterDate(String notAfterDate) {
        this.notAfterDate = notAfterDate;
    }

    @JsonProperty("orig_approval_date")
    public String getOrigApprovalDate() {
        return origApprovalDate;
    }

    @JsonProperty("orig_approval_date")
    public void setOrigApprovalDate(String origApprovalDate) {
        this.origApprovalDate = origApprovalDate;
    }

    @JsonProperty("buyer_name")
    public String getBuyerName() {
        return buyerName;
    }

    @JsonProperty("buyer_name")
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @JsonProperty("order_no")
    public Integer getOrderNo() {
        return orderNo;
    }

    @JsonProperty("order_no")
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @JsonProperty("create_datetime")
    public String getCreateDatetime() {
        return createDatetime;
    }

    @JsonProperty("create_datetime")
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("earliest_ship_date")
    public String getEarliestShipDate() {
        return earliestShipDate;
    }

    @JsonProperty("earliest_ship_date")
    public void setEarliestShipDate(String earliestShipDate) {
        this.earliestShipDate = earliestShipDate;
    }

    @JsonProperty("latest_ship_date")
    public String getLatestShipDate() {
        return latestShipDate;
    }

    @JsonProperty("latest_ship_date")
    public void setLatestShipDate(String latestShipDate) {
        this.latestShipDate = latestShipDate;
    }

    @JsonProperty("terms_code")
    public String getTermsCode() {
        return termsCode;
    }

    @JsonProperty("terms_code")
    public void setTermsCode(String termsCode) {
        this.termsCode = termsCode;
    }

    @JsonProperty("unit_cost")
    public String getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unit_cost")
    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    @JsonProperty("qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    @JsonProperty("discount")
    public String getDiscount() {
        return discount;
    }

    @JsonProperty("discount")
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @JsonProperty("val")
    public String getVal() {
        return val;
    }

    @JsonProperty("val")
    public void setVal(String val) {
        this.val = val;
    }

    @JsonProperty("vat_value")
    public String getVatValue() {
        return vatValue;
    }

    @JsonProperty("vat_value")
    public void setVatValue(String vatValue) {
        this.vatValue = vatValue;
    }
    
    @JsonProperty("comments")
    public String getComments() {
		return comments;
	}
    
    @JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}
    
    @JsonProperty("shipment")
	public String getShipment() {
		return shipment;
	}

    @JsonProperty("shipment")
	public void setShipment(String shipment) {
		this.shipment = shipment;
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
