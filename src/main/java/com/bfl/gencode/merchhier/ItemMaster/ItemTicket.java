package com.bfl.gencode.merchhier.ItemMaster;

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
    "printOnPcInd",
    "ticketOverPercentage",
    "ticketTypeId",
    "ticketTypeDescription",
    "shelfEdgeLabelInd",
    "poPrintType",
    "printOnPriceChangeInd",
    "ticketOverPrintPercentage",
    "createDateTime",
    "updateDateTime",
    "details"
})
public class ItemTicket {

    @JsonProperty("printOnPcInd")
    private String printOnPcInd;
    @JsonProperty("ticketOverPercentage")
    private Integer ticketOverPercentage;
    @JsonProperty("ticketTypeId")
    private String ticketTypeId;
    @JsonProperty("ticketTypeDescription")
    private String ticketTypeDescription;
    @JsonProperty("shelfEdgeLabelInd")
    private String shelfEdgeLabelInd;
    @JsonProperty("poPrintType")
    private String poPrintType;
    @JsonProperty("printOnPriceChangeInd")
    private String printOnPriceChangeInd;
    @JsonProperty("ticketOverPrintPercentage")
    private Integer ticketOverPrintPercentage;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("details")
    private List<Detail> details = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("printOnPcInd")
    public String getPrintOnPcInd() {
        return printOnPcInd;
    }

    @JsonProperty("printOnPcInd")
    public void setPrintOnPcInd(String printOnPcInd) {
        this.printOnPcInd = printOnPcInd;
    }

    @JsonProperty("ticketOverPercentage")
    public Integer getTicketOverPercentage() {
        return ticketOverPercentage;
    }

    @JsonProperty("ticketOverPercentage")
    public void setTicketOverPercentage(Integer ticketOverPercentage) {
        this.ticketOverPercentage = ticketOverPercentage;
    }

    @JsonProperty("ticketTypeId")
    public String getTicketTypeId() {
        return ticketTypeId;
    }

    @JsonProperty("ticketTypeId")
    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    @JsonProperty("ticketTypeDescription")
    public String getTicketTypeDescription() {
        return ticketTypeDescription;
    }

    @JsonProperty("ticketTypeDescription")
    public void setTicketTypeDescription(String ticketTypeDescription) {
        this.ticketTypeDescription = ticketTypeDescription;
    }

    @JsonProperty("shelfEdgeLabelInd")
    public String getShelfEdgeLabelInd() {
        return shelfEdgeLabelInd;
    }

    @JsonProperty("shelfEdgeLabelInd")
    public void setShelfEdgeLabelInd(String shelfEdgeLabelInd) {
        this.shelfEdgeLabelInd = shelfEdgeLabelInd;
    }

    @JsonProperty("poPrintType")
    public String getPoPrintType() {
        return poPrintType;
    }

    @JsonProperty("poPrintType")
    public void setPoPrintType(String poPrintType) {
        this.poPrintType = poPrintType;
    }

    @JsonProperty("printOnPriceChangeInd")
    public String getPrintOnPriceChangeInd() {
        return printOnPriceChangeInd;
    }

    @JsonProperty("printOnPriceChangeInd")
    public void setPrintOnPriceChangeInd(String printOnPriceChangeInd) {
        this.printOnPriceChangeInd = printOnPriceChangeInd;
    }

    @JsonProperty("ticketOverPrintPercentage")
    public Integer getTicketOverPrintPercentage() {
        return ticketOverPrintPercentage;
    }

    @JsonProperty("ticketOverPrintPercentage")
    public void setTicketOverPrintPercentage(Integer ticketOverPrintPercentage) {
        this.ticketOverPrintPercentage = ticketOverPrintPercentage;
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

    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
        this.details = details;
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
