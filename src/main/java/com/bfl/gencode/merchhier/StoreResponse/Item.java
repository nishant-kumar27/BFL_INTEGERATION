package com.bfl.gencode.merchhier.StoreResponse;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "action",
    "storeid",
    "storename",
    "storename10",
    "manager",
    "phonenumber",
    "faxnumber",
    "email",
    "totalsqfeet",
    "sellingsqfeet",
    "currencycode",
    "vatregion",
    "stockholding",
    "integratedpos",
    "contactname",
    "address1",
    "address2",
    "address3",
    "city",
    "state",
    "country",
    "postalcode"
})

public class Item implements Serializable {

    @JsonProperty("action")
    private String action;
	
    @JsonProperty("storeid")
    private Integer storeid;
	
    @JsonProperty("storename")
    private String storename;
	
    @JsonProperty("storename10")
    private String storename10;
	
    @JsonProperty("manager")
    private String manager;
	
    @JsonProperty("phonenumber")
    private String phonenumber;
	
    @JsonProperty("faxnumber")
    private Object faxnumber;
	
    @JsonProperty("email")
    private String email;
	
    @JsonProperty("totalsqfeet")
    private Object totalsqfeet;
	
    @JsonProperty("sellingsqfeet")
    private Object sellingsqfeet;
	
    @JsonProperty("currencycode")
    private String currencycode;
	
    @JsonProperty("vatregion")
    private Integer vatregion;
	
    @JsonProperty("stockholding")
    private String stockholding;
	
    @JsonProperty("integratedpos")
    private String integratedpos;
	
    @JsonProperty("contactname")
    private Object contactname;
	
    @JsonProperty("address1")
    private String address1;
	
    @JsonProperty("address2")
    private Object address2;
	
    @JsonProperty("address3")
    private Object address3;
	
    @JsonProperty("city")
    private String city;
	
    @JsonProperty("state")
    private Object state;
	
    @JsonProperty("country")
    private String country;
	
    @JsonProperty("postalcode")
    private Object postalcode;
	
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	
    private final static long serialVersionUID = -1106795337526119698L;

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("storeid")
    public Integer getStoreid() {
        return storeid;
    }

    @JsonProperty("storeid")
    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    @JsonProperty("storename")
    public String getStorename() {
        return storename;
    }

    @JsonProperty("storename")
    public void setStorename(String storename) {
        this.storename = storename;
    }

    @JsonProperty("storename10")
    public String getStorename10() {
        return storename10;
    }

    @JsonProperty("storename10")
    public void setStorename10(String storename10) {
        this.storename10 = storename10;
    }

    @JsonProperty("manager")
    public String getManager() {
        return manager;
    }

    @JsonProperty("manager")
    public void setManager(String manager) {
        this.manager = manager;
    }

    @JsonProperty("phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    @JsonProperty("phonenumber")
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @JsonProperty("faxnumber")
    public Object getFaxnumber() {
        return faxnumber;
    }

    @JsonProperty("faxnumber")
    public void setFaxnumber(Object faxnumber) {
        this.faxnumber = faxnumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("totalsqfeet")
    public Object getTotalsqfeet() {
        return totalsqfeet;
    }

    @JsonProperty("totalsqfeet")
    public void setTotalsqfeet(Object totalsqfeet) {
        this.totalsqfeet = totalsqfeet;
    }

    @JsonProperty("sellingsqfeet")
    public Object getSellingsqfeet() {
        return sellingsqfeet;
    }

    @JsonProperty("sellingsqfeet")
    public void setSellingsqfeet(Object sellingsqfeet) {
        this.sellingsqfeet = sellingsqfeet;
    }

    @JsonProperty("currencycode")
    public String getCurrencycode() {
        return currencycode;
    }

    @JsonProperty("currencycode")
    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    @JsonProperty("vatregion")
    public Integer getVatregion() {
        return vatregion;
    }

    @JsonProperty("vatregion")
    public void setVatregion(Integer vatregion) {
        this.vatregion = vatregion;
    }

    @JsonProperty("stockholding")
    public String getStockholding() {
        return stockholding;
    }

    @JsonProperty("stockholding")
    public void setStockholding(String stockholding) {
        this.stockholding = stockholding;
    }

    @JsonProperty("integratedpos")
    public String getIntegratedpos() {
        return integratedpos;
    }

    @JsonProperty("integratedpos")
    public void setIntegratedpos(String integratedpos) {
        this.integratedpos = integratedpos;
    }

    @JsonProperty("contactname")
    public Object getContactname() {
        return contactname;
    }

    @JsonProperty("contactname")
    public void setContactname(Object contactname) {
        this.contactname = contactname;
    }

    @JsonProperty("address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("address2")
    public Object getAddress2() {
        return address2;
    }

    @JsonProperty("address2")
    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    @JsonProperty("address3")
    public Object getAddress3() {
        return address3;
    }

    @JsonProperty("address3")
    public void setAddress3(Object address3) {
        this.address3 = address3;
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
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("postalcode")
    public Object getPostalcode() {
        return postalcode;
    }

    @JsonProperty("postalcode")
    public void setPostalcode(Object postalcode) {
        this.postalcode = postalcode;
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
