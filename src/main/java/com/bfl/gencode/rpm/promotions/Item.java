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
    "promoid",
    "offerid",
    "desc",
    "custdesc",
    "levelcode",
    "typecode",
    "templateid",
    "couponcode",
    "couponcodereq",
    "startdatetime",
    "enddatetime",
    "canceldatetime",
    "comments",
    "distributionrulecode",
    "exclusivediscountind",
    "rewards",
    "conditions",
    "locations"
})
public class Item {

    @JsonProperty("promoid")
    private String promoid;
    @JsonProperty("offerid")
    private String offerid;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("custdesc")
    private String custdesc;
    @JsonProperty("levelcode")
    private String levelcode;
    @JsonProperty("typecode")
    private String typecode;
    @JsonProperty("templateid")
    private String templateid;
    @JsonProperty("couponcode")
    private String couponcode;
    @JsonProperty("couponcodereq")
    private String couponcodereq;
    @JsonProperty("startdatetime")
    private String startdatetime;
    @JsonProperty("enddatetime")
    private String enddatetime;
    @JsonProperty("canceldatetime")
    private String canceldatetime;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("distributionrulecode")
    private String distributionrulecode;
    @JsonProperty("exclusivediscountind")
    private String exclusivediscountind;
    @JsonProperty("rewards")
    private List<Reward> rewards = null;
    @JsonProperty("conditions")
    private List<Condition> conditions = null;
    @JsonProperty("locations")
    private List<Location> locations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("promoid")
    public String getPromoid() {
        return promoid;
    }

    @JsonProperty("promoid")
    public void setPromoid(String promoid) {
        this.promoid = promoid;
    }

    @JsonProperty("offerid")
    public String getOfferid() {
        return offerid;
    }

    @JsonProperty("offerid")
    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("custdesc")
    public String getCustdesc() {
        return custdesc;
    }

    @JsonProperty("custdesc")
    public void setCustdesc(String custdesc) {
        this.custdesc = custdesc;
    }

    @JsonProperty("levelcode")
    public String getLevelcode() {
        return levelcode;
    }

    @JsonProperty("levelcode")
    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode;
    }

    @JsonProperty("typecode")
    public String getTypecode() {
        return typecode;
    }

    @JsonProperty("typecode")
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    @JsonProperty("templateid")
    public String getTemplateid() {
        return templateid;
    }

    @JsonProperty("templateid")
    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    @JsonProperty("couponcode")
    public String getCouponcode() {
        return couponcode;
    }

    @JsonProperty("couponcode")
    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode;
    }

    @JsonProperty("couponcodereq")
    public String getCouponcodereq() {
        return couponcodereq;
    }

    @JsonProperty("couponcodereq")
    public void setCouponcodereq(String couponcodereq) {
        this.couponcodereq = couponcodereq;
    }

    @JsonProperty("startdatetime")
    public String getStartdatetime() {
        return startdatetime;
    }

    @JsonProperty("startdatetime")
    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    @JsonProperty("enddatetime")
    public String getEnddatetime() {
        return enddatetime;
    }

    @JsonProperty("enddatetime")
    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    @JsonProperty("canceldatetime")
    public String getCanceldatetime() {
        return canceldatetime;
    }

    @JsonProperty("canceldatetime")
    public void setCanceldatetime(String canceldatetime) {
        this.canceldatetime = canceldatetime;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("distributionrulecode")
    public String getDistributionrulecode() {
        return distributionrulecode;
    }

    @JsonProperty("distributionrulecode")
    public void setDistributionrulecode(String distributionrulecode) {
        this.distributionrulecode = distributionrulecode;
    }

    @JsonProperty("exclusivediscountind")
    public String getExclusivediscountind() {
        return exclusivediscountind;
    }

    @JsonProperty("exclusivediscountind")
    public void setExclusivediscountind(String exclusivediscountind) {
        this.exclusivediscountind = exclusivediscountind;
    }

    @JsonProperty("rewards")
    public List<Reward> getRewards() {
        return rewards;
    }

    @JsonProperty("rewards")
    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    @JsonProperty("conditions")
    public List<Condition> getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("locations")
    public List<Location> getLocations() {
        return locations;
    }

    @JsonProperty("locations")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
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
