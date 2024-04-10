package com.bfl.gencode.rpm.promotions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "conditionid",
    "buyspendtype",
    "buyspendvalue",
    "buyuom",
    "pricerestrictcode",
    "pricerestrictvalue1",
    "pricerestrictvalue2",
    "conditionsmerch"
})
public class Condition {

    @JsonProperty("conditionid")
    private String conditionid;
    @JsonProperty("buyspendtype")
    private String buyspendtype;
    @JsonProperty("buyspendvalue")
    private String buyspendvalue;
    @JsonProperty("buyuom")
    private String buyuom;
    @JsonProperty("pricerestrictcode")
    private String pricerestrictcode;
    @JsonProperty("pricerestrictvalue1")
    private String pricerestrictvalue1;
    @JsonProperty("pricerestrictvalue2")
    private String pricerestrictvalue2;
    @JsonProperty("conditionsmerch")
    private List<Conditionsmerch> conditionsmerch = null;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("conditionid")
    public String getConditionid() {
        return conditionid;
    }

    @JsonProperty("conditionid")
    public void setConditionid(String conditionid) {
        this.conditionid = conditionid;
    }

    @JsonProperty("buyspendtype")
    public String getBuyspendtype() {
        return buyspendtype;
    }

    @JsonProperty("buyspendtype")
    public void setBuyspendtype(String buyspendtype) {
        this.buyspendtype = buyspendtype;
    }

    @JsonProperty("buyspendvalue")
    public String getBuyspendvalue() {
        return buyspendvalue;
    }

    @JsonProperty("buyspendvalue")
    public void setBuyspendvalue(String buyspendvalue) {
        this.buyspendvalue = buyspendvalue;
    }

    @JsonProperty("buyuom")
    public String getBuyuom() {
        return buyuom;
    }

    @JsonProperty("buyuom")
    public void setBuyuom(String buyuom) {
        this.buyuom = buyuom;
    }

    @JsonProperty("pricerestrictcode")
    public String getPricerestrictcode() {
        return pricerestrictcode;
    }

    @JsonProperty("pricerestrictcode")
    public void setPricerestrictcode(String pricerestrictcode) {
        this.pricerestrictcode = pricerestrictcode;
    }

    @JsonProperty("pricerestrictvalue1")
    public String getPricerestrictvalue1() {
        return pricerestrictvalue1;
    }

    @JsonProperty("pricerestrictvalue1")
    public void setPricerestrictvalue1(String pricerestrictvalue1) {
        this.pricerestrictvalue1 = pricerestrictvalue1;
    }

    @JsonProperty("pricerestrictvalue2")
    public String getPricerestrictvalue2() {
        return pricerestrictvalue2;
    }

    @JsonProperty("pricerestrictvalue2")
    public void setPricerestrictvalue2(String pricerestrictvalue2) {
        this.pricerestrictvalue2 = pricerestrictvalue2;
    }

    @JsonProperty("conditionsmerch")
    public List<Conditionsmerch> getConditionsmerch() {
        return conditionsmerch;
    }

    @JsonProperty("conditionsmerch")
    public void setConditionsmerch(List<Conditionsmerch> conditionsmerch) {
        this.conditionsmerch = conditionsmerch;
    }

    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}
