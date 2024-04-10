package com.bfl.gencode.rpm.clearances;

import java.util.HashMap;
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
    "action"
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
    @JsonProperty("action")
    private String action;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
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
