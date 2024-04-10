package com.bfl.gencode.merchhier.PartialShipment;

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
    "order_no",
    "shipment",
    "group_code",
    "qty",
    "ref_no",
    "amt",
    "comment_desc",
    "bol_no",
    "vat"
})
public class Item implements Serializable {
	
    @JsonProperty("order_no")
    private Integer orderNo;
    
    @JsonProperty("shipment")
    private Integer shipment;
    
    @JsonProperty("group_code")
    private String groupCode;
    
    @JsonProperty("qty")
    private Integer qty;
    
    @JsonProperty("amt")
    private Double amt;
    
    @JsonProperty("vat")
    private Integer vat;
    
    @JsonProperty("ref_no")
    private String refNo;
    
    @JsonProperty("comment_desc")
    private String poNo;
    
    @JsonProperty("bol_no")
    private String bolNo;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -5235389050831255318L;

    @JsonProperty("order_no")
    public Integer getOrderNo() {
        return orderNo;
    }

    @JsonProperty("order_no")
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @JsonProperty("shipment")
    public Integer getShipment() {
        return shipment;
    }

    @JsonProperty("shipment")
    public void setShipment(Integer shipment) {
        this.shipment = shipment;
    }

    @JsonProperty("group_code")
    public String getGroupCode() {
        return groupCode;
    }

    @JsonProperty("group_code")
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonProperty("qty")
    public Integer getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @JsonProperty("amt")
    public Double getAmt() {
        return amt;
    }

    @JsonProperty("amt")
    public void setAmt(Double amt) {
        this.amt = amt;
    }

    @JsonProperty("vat")
    public Integer getVat() {
        return vat;
    }

    @JsonProperty("vat")
    public void setVat(Integer vat) {
        this.vat = vat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @JsonProperty("ref_no")
	public String getRefNo() {
		return refNo;
	}

	@JsonProperty("ref_no")
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	@JsonProperty("comment_desc")
	public String getPoNo() {
		return poNo;
	}

	@JsonProperty("comment_desc")
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@JsonProperty("bol_no")
	public String getBolNo() {
		return bolNo;
	}

	@JsonProperty("bol_no")
	public void setBolNo(String bolNo) {
		this.bolNo = bolNo;
	}
	
}
