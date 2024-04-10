
package com.bfl.gencode.merchhier.ItemMasterRequest;

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
    "ticketTypeId",
    "poPrintType",
    "printOnPriceChangeInd",
    "ticketOverPrintPercentage"
})
@Generated("jsonschema2pojo")
public class Ticket implements Serializable
{

    @JsonProperty("ticketTypeId")
    private String ticketTypeId;
    @JsonProperty("poPrintType")
    private String poPrintType;
    @JsonProperty("printOnPriceChangeInd")
    private String printOnPriceChangeInd;
    @JsonProperty("ticketOverPrintPercentage")
    private Integer ticketOverPrintPercentage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 7967119043062374743L;

    @JsonProperty("ticketTypeId")
    public String getTicketTypeId() {
        return ticketTypeId;
    }

    @JsonProperty("ticketTypeId")
    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
