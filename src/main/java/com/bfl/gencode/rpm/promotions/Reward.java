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
    "rewardid",
    "changetype",
    "changeamount",
    "changepercent",
    "qtytodisc",
    "applyind",
    "pricerestrictcode",
    "pricerestrictvalue1",
    "pricerestrictvalue2",
    "rewardsmerch"
})
public class Reward {

    @JsonProperty("rewardid")
    private String rewardid;
    @JsonProperty("changetype")
    private String changetype;
    @JsonProperty("changeamount")
    private String changeamount;
    @JsonProperty("changepercent")
    private String changepercent;
    @JsonProperty("qtytodisc")
    private String qtytodisc;
    @JsonProperty("applyind")
    private String applyind;
    @JsonProperty("pricerestrictcode")
    private String pricerestrictcode;
    @JsonProperty("pricerestrictvalue1")
    private String pricerestrictvalue1;
    @JsonProperty("pricerestrictvalue2")
    private String pricerestrictvalue2;
    @JsonProperty("rewardsmerch")
    private List<Rewardsmerch> rewardsmerch = null;
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    @JsonProperty("rewardid")
    public String getRewardid() {
        return rewardid;
    }

    @JsonProperty("rewardid")
    public void setRewardid(String rewardid) {
        this.rewardid = rewardid;
    }

    @JsonProperty("changetype")
    public String getChangetype() {
        return changetype;
    }

    @JsonProperty("changetype")
    public void setChangetype(String changetype) {
        this.changetype = changetype;
    }

    @JsonProperty("changeamount")
    public String getChangeamount() {
        return changeamount;
    }

    @JsonProperty("changeamount")
    public void setChangeamount(String changeamount) {
        this.changeamount = changeamount;
    }

    @JsonProperty("changepercent")
    public String getChangepercent() {
        return changepercent;
    }

    @JsonProperty("changepercent")
    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    @JsonProperty("qtytodisc")
    public String getQtytodisc() {
        return qtytodisc;
    }

    @JsonProperty("qtytodisc")
    public void setQtytodisc(String qtytodisc) {
        this.qtytodisc = qtytodisc;
    }

    @JsonProperty("applyind")
    public String getApplyind() {
        return applyind;
    }

    @JsonProperty("applyind")
    public void setApplyind(String applyind) {
        this.applyind = applyind;
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

    @JsonProperty("rewardsmerch")
    public List<Rewardsmerch> getRewardsmerch() {
        return rewardsmerch;
    }

    @JsonProperty("rewardsmerch")
    public void setRewardsmerch(List<Rewardsmerch> rewardsmerch) {
        this.rewardsmerch = rewardsmerch;
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
