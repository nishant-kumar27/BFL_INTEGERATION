package com.bfl.gencode.merchhier.transfers;

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
    "item",
    "transferQuantity",
    "price",
    "sellingUom",
    "expediteFlag",
    "storeOrderMultiple",
    "transferOrderLinkNo",
    "ticketTypeId",
    "ticketDetails",
    "invStatus",
    "transactionUom",
    "itemLineNo",
    "comments",
    "createDateTime",
    "updateDateTime"
})
public class Detail {

    @JsonProperty("item")
    private String item;
    @JsonProperty("transferQuantity")
    private Integer transferQuantity;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("sellingUom")
    private String sellingUom;
    @JsonProperty("expediteFlag")
    private String expediteFlag;
    @JsonProperty("storeOrderMultiple")
    private String storeOrderMultiple;
    @JsonProperty("transferOrderLinkNo")
    private Integer transferOrderLinkNo;
    @JsonProperty("ticketTypeId")
    private String ticketTypeId;
    @JsonProperty("ticketDetails")
    private List<TicketDetail> ticketDetails = null;
    @JsonProperty("invStatus")
    private String invStatus;
    @JsonProperty("transactionUom")
    private String transactionUom;
    @JsonProperty("itemLineNo")
    private String itemLineNo;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("transferQuantity")
    public Integer getTransferQuantity() {
        return transferQuantity;
    }

    @JsonProperty("transferQuantity")
    public void setTransferQuantity(Integer transferQuantity) {
        this.transferQuantity = transferQuantity;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("sellingUom")
    public String getSellingUom() {
        return sellingUom;
    }

    @JsonProperty("sellingUom")
    public void setSellingUom(String sellingUom) {
        this.sellingUom = sellingUom;
    }

    @JsonProperty("expediteFlag")
    public String getExpediteFlag() {
        return expediteFlag;
    }

    @JsonProperty("expediteFlag")
    public void setExpediteFlag(String expediteFlag) {
        this.expediteFlag = expediteFlag;
    }

    @JsonProperty("storeOrderMultiple")
    public String getStoreOrderMultiple() {
        return storeOrderMultiple;
    }

    @JsonProperty("storeOrderMultiple")
    public void setStoreOrderMultiple(String storeOrderMultiple) {
        this.storeOrderMultiple = storeOrderMultiple;
    }

    @JsonProperty("transferOrderLinkNo")
    public Integer getTransferOrderLinkNo() {
        return transferOrderLinkNo;
    }

    @JsonProperty("transferOrderLinkNo")
    public void setTransferOrderLinkNo(Integer transferOrderLinkNo) {
        this.transferOrderLinkNo = transferOrderLinkNo;
    }

    @JsonProperty("ticketTypeId")
    public String getTicketTypeId() {
        return ticketTypeId;
    }

    @JsonProperty("ticketTypeId")
    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    @JsonProperty("ticketDetails")
    public List<TicketDetail> getTicketDetails() {
        return ticketDetails;
    }

    @JsonProperty("ticketDetails")
    public void setTicketDetails(List<TicketDetail> ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    @JsonProperty("invStatus")
    public String getInvStatus() {
        return invStatus;
    }

    @JsonProperty("invStatus")
    public void setInvStatus(String invStatus) {
        this.invStatus = invStatus;
    }

    @JsonProperty("transactionUom")
    public String getTransactionUom() {
        return transactionUom;
    }

    @JsonProperty("transactionUom")
    public void setTransactionUom(String transactionUom) {
        this.transactionUom = transactionUom;
    }

    @JsonProperty("itemLineNo")
    public String getItemLineNo() {
        return itemLineNo;
    }

    @JsonProperty("itemLineNo")
    public void setItemLineNo(String itemLineNo) {
        this.itemLineNo = itemLineNo;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("createDateTime")
    public String getCreateDateTime() {
        return createDateTime;
    }

    @JsonProperty("createDateTime")
    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    @JsonProperty("updateDateTime")
    public String getUpdateDateTime() {
        return updateDateTime;
    }

    @JsonProperty("updateDateTime")
    public void setUpdateDateTime(String updateDateTime) {
        this.updateDateTime = updateDateTime;
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
