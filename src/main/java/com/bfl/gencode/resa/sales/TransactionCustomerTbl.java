
package com.bfl.gencode.resa.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "customerId",
    "customerType",
    "customerName",
    "address1",
    "address2",
    "city",
    "state",
    "zipCode",
    "country",
    "homePhone",
    "workPhone",
    "email",
    "birthdate"
})
public class TransactionCustomerTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("homePhone")
    private String homePhone;
    @JsonProperty("workPhone")
    private String workPhone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("birthdate")
    private String birthdate;
    /**
   	 * 
   	 */
   	private static final long serialVersionUID = 1L;
   	
    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @JsonProperty("customerName")
    public String getCustomerName() {
        return customerName;
    }

    @JsonProperty("customerName")
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
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

    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("homePhone")
    public String getHomePhone() {
        return homePhone;
    }

    @JsonProperty("homePhone")
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @JsonProperty("workPhone")
    public String getWorkPhone() {
        return workPhone;
    }

    @JsonProperty("workPhone")
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("birthdate")
    public String getBirthdate() {
        return birthdate;
    }

    @JsonProperty("birthdate")
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
