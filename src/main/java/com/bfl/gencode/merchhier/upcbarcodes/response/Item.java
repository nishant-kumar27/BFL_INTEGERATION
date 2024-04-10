package com.bfl.gencode.merchhier.upcbarcodes.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "container_id",
    "qty_received",
    "shippedqty",
    "boxno",
    "item",
    "upc",
    "itemname",
    "brand_name",
    "season",
    "rrp",
    "style",
    "group_code",
    "color",
    "rrp_currency",
    "product_material",
    "siz",
    "sub_name",
    "dept_name",
    "division",
    "origin_country_id",
    "hscode",
    "country_desc",
    "gender_code",
    "gender",
    "comments",
    "bol_no",
    "vendor_order_no",
    "location",
    "unit_cost",
    "unit_retail",
    "sellingprice",
    "av_cost",
    "genbarcode",
    "cost_currency",
    "order_no",
    "refno",
    "sellingprice_currency",
    "dhscost",
    "style_description",
    "supplier",
    "upload"
})

public class Item implements Serializable {
	
    @JsonProperty("container_id")
    private String containerId;
    
    @JsonProperty("order_no")
    private String orderNo;
    
    @JsonProperty("refno")
    private String refNo;
    
    @JsonProperty("qty_received")
    private String qtyReceived;
    
    @JsonProperty("shippedqty")
    private String shippedqty;
    
    @JsonProperty("boxno")
    private String boxno;
    
    @JsonProperty("item")
    private String item;
    
    @JsonProperty("upc")
    private String upc;
    
    @JsonProperty("itemname")
    private String itemname;
    
    @JsonProperty("brand_name")
    private String brandName;
    
    @JsonProperty("season")
    private String season;
    
    @JsonProperty("rrp")
    private String rrp;
    
    @JsonProperty("style")
    private String style;
    
    @JsonProperty("group_code")
    private String groupCode;
    
    @JsonProperty("color")
    private String color;
    
    @JsonProperty("rrp_currency")
    private String rrpCurrency;
    
    @JsonProperty("product_material")
    private String productMaterial;
    
    @JsonProperty("siz")
    private String siz;
    
    @JsonProperty("sub_name")
    private String subName;
    
    @JsonProperty("dept_name")
    private String deptName;
    
    @JsonProperty("division")
    private String division;
    
    @JsonProperty("origin_country_id")
    private String originCountryId;
    
    @JsonProperty("hscode")
    private String hscode;
    
    @JsonProperty("country_desc")
    private String countryDesc;
    
    @JsonProperty("gender_code")
    private String genderCode;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("comments")
    private String comments;
    
    @JsonProperty("bol_no")
    private String bolNo;
    
    @JsonProperty("vendor_order_no")
    private String vendorOrderNo;
    
    @JsonProperty("location")
    private String location;
    
    @JsonProperty("unit_cost")
    private String unitCost;
    
    @JsonProperty("unit_retail")
    private String unitRetail;
    
    @JsonProperty("sellingprice")
    private String sellingprice;
    
    @JsonProperty("av_cost")
    private String avCost;
    
    @JsonProperty("genbarcode")
    private String genbarcode;
    
    @JsonProperty("upload")
    private String upload;
    
    @JsonProperty("cost_currency")
    private String costCurrency;
    
    @JsonProperty("sellingprice_currency")
    private String sellingPriceCurrency;
    
    @JsonProperty("dhscost")
    private String dhsCost;
    
    @JsonProperty("style_description")
    private String style_description;
    
    @JsonProperty("supplier")
    private String supplier;
    
    private final static long serialVersionUID = 6981482868916554662L;

    @JsonProperty("container_id")
    public String getContainerId() {
        return containerId;
    }

    @JsonProperty("container_id")
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    @JsonProperty("qty_received")
    public String getQtyReceived() {
        return qtyReceived;
    }

    @JsonProperty("qty_received")
    public void setQtyReceived(String qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    @JsonProperty("shippedqty")
    public String getShippedqty() {
        return shippedqty;
    }

    @JsonProperty("shippedqty")
    public void setShippedqty(String shippedqty) {
        this.shippedqty = shippedqty;
    }

    @JsonProperty("boxno")
    public String getBoxno() {
        return boxno;
    }

    @JsonProperty("boxno")
    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("upc")
    public String getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @JsonProperty("itemname")
    public String getItemname() {
        return itemname;
    }

    @JsonProperty("itemname")
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("season")
    public String getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(String season) {
        this.season = season;
    }

    @JsonProperty("rrp")
    public String getRrp() {
        return rrp;
    }

    @JsonProperty("rrp")
    public void setRrp(String rrp) {
        this.rrp = rrp;
    }

    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    @JsonProperty("style")
    public void setStyle(String style) {
        this.style = style;
    }

    @JsonProperty("group_code")
    public String getGroupCode() {
        return groupCode;
    }

    @JsonProperty("group_code")
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("rrp_currency")
    public String getRrpCurrency() {
        return rrpCurrency;
    }

    @JsonProperty("rrp_currency")
    public void setRrpCurrency(String rrpCurrency) {
        this.rrpCurrency = rrpCurrency;
    }

    @JsonProperty("product_material")
    public String getProductMaterial() {
        return productMaterial;
    }

    @JsonProperty("product_material")
    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    @JsonProperty("siz")
    public String getSiz() {
        return siz;
    }

    @JsonProperty("siz")
    public void setSiz(String siz) {
        this.siz = siz;
    }

    @JsonProperty("sub_name")
    public String getSubName() {
        return subName;
    }

    @JsonProperty("sub_name")
    public void setSubName(String subName) {
        this.subName = subName;
    }

    @JsonProperty("dept_name")
    public String getDeptName() {
        return deptName;
    }

    @JsonProperty("dept_name")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonProperty("division")
    public String getDivision() {
        return division;
    }

    @JsonProperty("division")
    public void setDivision(String division) {
        this.division = division;
    }

    @JsonProperty("origin_country_id")
    public String getOriginCountryId() {
        return originCountryId;
    }

    @JsonProperty("origin_country_id")
    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    @JsonProperty("hscode")
    public String getHscode() {
        return hscode;
    }

    @JsonProperty("hscode")
    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    @JsonProperty("country_desc")
    public String getCountryDesc() {
        return countryDesc;
    }

    @JsonProperty("country_desc")
    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    @JsonProperty("gender_code")
    public String getGenderCode() {
        return genderCode;
    }

    @JsonProperty("gender_code")
    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("bol_no")
    public String getBolNo() {
        return bolNo;
    }

    @JsonProperty("bol_no")
    public void setBolNo(String bolNo) {
        this.bolNo = bolNo;
    }

    @JsonProperty("vendor_order_no")
    public String getVendorOrderNo() {
        return vendorOrderNo;
    }

    @JsonProperty("vendor_order_no")
    public void setVendorOrderNo(String vendorOrderNo) {
        this.vendorOrderNo = vendorOrderNo;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("unit_cost")
    public String getUnitCost() {
        return unitCost;
    }

    @JsonProperty("unit_cost")
    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    @JsonProperty("unit_retail")
    public String getUnitRetail() {
        return unitRetail;
    }

    @JsonProperty("unit_retail")
    public void setUnitRetail(String unitRetail) {
        this.unitRetail = unitRetail;
    }

    @JsonProperty("sellingprice")
    public String getSellingprice() {
        return sellingprice;
    }

    @JsonProperty("sellingprice")
    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    @JsonProperty("av_cost")
    public String getAvCost() {
        return avCost;
    }

    @JsonProperty("av_cost")
    public void setAvCost(String avCost) {
        this.avCost = avCost;
    }

    @JsonProperty("genbarcode")
    public String getGenbarcode() {
        return genbarcode;
    }

    @JsonProperty("genbarcode")
    public void setGenbarcode(String genbarcode) {
        this.genbarcode = genbarcode;
    }

    @JsonProperty("upload")
    public String getUpload() {
        return upload;
    }

    @JsonProperty("upload")
    public void setUpload(String upload) {
        this.upload = upload;
    }
    
    @JsonProperty("cost_currency")
	public String getCostCurrency() {
		return costCurrency;
	}

    @JsonProperty("cost_currency")
	public void setCostCurrency(String costCurrency) {
		this.costCurrency = costCurrency;
	}

    @JsonProperty("sellingprice_currency")
	public String getSellingPriceCurrency() {
		return sellingPriceCurrency;
	}

    @JsonProperty("sellingprice_currency")
	public void setSellingPriceCurrency(String sellingPriceCurrency) {
		this.sellingPriceCurrency = sellingPriceCurrency;
	}

    @JsonProperty("order_no")
	public String getOrderNo() {
		return orderNo;
	}

	@JsonProperty("order_no")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@JsonProperty("refno")
	public String getRefNo() {
		return refNo;
	}

	@JsonProperty("refno")
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	@JsonProperty("dhscost")
	public String getDhsCost() {
		return dhsCost;
	}

	@JsonProperty("dhscost")
	public void setDhsCost(String dhsCost) {
		this.dhsCost = dhsCost;
	}
	
	@JsonProperty("style_description")
	public String getStyle_description() {
		return style_description;
	}
	
	@JsonProperty("style_description")
	public void setStyle_description(String style_description) {
		this.style_description = style_description;
	}
	
	@JsonProperty("supplier")
	public String getSupplier() {
		return supplier;
	}
	
	@JsonProperty("supplier")
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
}