package com.bfl.gencode.merchhier.PriceChange;

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
    "msgType",
    "item",
    "location",
    "loctype",
    "price",
    "pricetype",
    "effective",
    "eventid",
    "resetind",
    "retailchangeind",
    "unitofmeasure",
    "currency",
    "multiunitimpact",
    "multiunits",
    "multiunitretail",
    "multiunitcurrency",
    "multiunitsellinguom",
    "action",
    "action_date",
    "aedprice",
    "country",
    "itemname"
})
public class Item {

    @JsonProperty("msgType")
    private String msgType;
    
    @JsonProperty("item")
    private String item;
    
    @JsonProperty("location")
    private String location;
    
    @JsonProperty("loctype")
    private String loctype;
    
    @JsonProperty("price")
    private String price;
    
    @JsonProperty("pricetype")
    private String pricetype;
    @JsonProperty("effective")
    private String effective;
    @JsonProperty("eventid")
    private String eventid;
    @JsonProperty("resetind")
    private String resetind;
    @JsonProperty("retailchangeind")
    private String retailchangeind;
    @JsonProperty("unitofmeasure")
    private String unitofmeasure;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("multiunitimpact")
    private String multiunitimpact;
    @JsonProperty("multiunits")
    private String multiunits;
    @JsonProperty("multiunitretail")
    private String multiunitretail;
    @JsonProperty("multiunitcurrency")
    private String multiunitcurrency;
    @JsonProperty("multiunitsellinguom")
    private String multiunitsellinguom;
    @JsonProperty("action")
    private String action;
    
    @JsonProperty("action_date")
    private String actionDate;
    
    @JsonProperty("itemname")
    private String itemName;
    
    @JsonProperty("aedprice")
    private String aedPrice;
    
    @JsonProperty("country")
    private String country;
    
    @JsonIgnore
    private Map<String, String> additionalProperties = new LinkedHashMap<String, String>();

    @JsonProperty("msgType")
    public String getMsgType() {
        return msgType;
    }

    @JsonProperty("msgType")
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("loctype")
    public String getLoctype() {
        return loctype;
    }

    @JsonProperty("loctype")
    public void setLoctype(String loctype) {
        this.loctype = loctype;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("pricetype")
    public String getPricetype() {
        return pricetype;
    }

    @JsonProperty("pricetype")
    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    @JsonProperty("effective")
    public String getEffective() {
        return effective;
    }

    @JsonProperty("effective")
    public void setEffective(String effective) {
        this.effective = effective;
    }

    @JsonProperty("eventid")
    public String getEventid() {
        return eventid;
    }

    @JsonProperty("eventid")
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    @JsonProperty("resetind")
    public String getResetind() {
        return resetind;
    }

    @JsonProperty("resetind")
    public void setResetind(String resetind) {
        this.resetind = resetind;
    }

    @JsonProperty("retailchangeind")
    public String getRetailchangeind() {
        return retailchangeind;
    }

    @JsonProperty("retailchangeind")
    public void setRetailchangeind(String retailchangeind) {
        this.retailchangeind = retailchangeind;
    }

    @JsonProperty("unitofmeasure")
    public String getUnitofmeasure() {
        return unitofmeasure;
    }

    @JsonProperty("unitofmeasure")
    public void setUnitofmeasure(String unitofmeasure) {
        this.unitofmeasure = unitofmeasure;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("multiunitimpact")
    public String getMultiunitimpact() {
        return multiunitimpact;
    }

    @JsonProperty("multiunitimpact")
    public void setMultiunitimpact(String multiunitimpact) {
        this.multiunitimpact = multiunitimpact;
    }

    @JsonProperty("multiunits")
    public String getMultiunits() {
        return multiunits;
    }

    @JsonProperty("multiunits")
    public void setMultiunits(String multiunits) {
        this.multiunits = multiunits;
    }

    @JsonProperty("multiunitretail")
    public String getMultiunitretail() {
        return multiunitretail;
    }

    @JsonProperty("multiunitretail")
    public void setMultiunitretail(String multiunitretail) {
        this.multiunitretail = multiunitretail;
    }

    @JsonProperty("multiunitcurrency")
    public String getMultiunitcurrency() {
        return multiunitcurrency;
    }

    @JsonProperty("multiunitcurrency")
    public void setMultiunitcurrency(String multiunitcurrency) {
        this.multiunitcurrency = multiunitcurrency;
    }

    @JsonProperty("multiunitsellinguom")
    public String getMultiunitsellinguom() {
        return multiunitsellinguom;
    }

    @JsonProperty("multiunitsellinguom")
    public void setMultiunitsellinguom(String multiunitsellinguom) {
        this.multiunitsellinguom = multiunitsellinguom;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }
    
    @JsonProperty("action_date")
    public String getActionDate() {
		return actionDate;
	}

    @JsonProperty("action_date")
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	@JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("itemname")
	public String getItemName() {
		return itemName;
	}

    @JsonProperty("itemname")
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

    @JsonProperty("aedprice")
	public String getAedPrice() {
		return aedPrice;
	}

    @JsonProperty("aedprice")
	public void setAedPrice(String aedPrice) {
		this.aedPrice = aedPrice;
	}

    @JsonProperty("country")
	public String getCountry() {
		return country;
	}

    @JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}
    
}
