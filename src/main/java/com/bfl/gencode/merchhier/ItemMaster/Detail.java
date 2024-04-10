package com.bfl.gencode.merchhier.ItemMaster;

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
    "ticketItemId",
    "ticketItemIdDescription",
    "udaId",
    "sequenceNo"
})
public class Detail {

    @JsonProperty("ticketItemId")
    private String ticketItemId;
    @JsonProperty("ticketItemIdDescription")
    private String ticketItemIdDescription;
    @JsonProperty("udaId")
    private Integer udaId;
    @JsonProperty("sequenceNo")
    private Integer sequenceNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ticketItemId")
    public String getTicketItemId() {
        return ticketItemId;
    }

    @JsonProperty("ticketItemId")
    public void setTicketItemId(String ticketItemId) {
        this.ticketItemId = ticketItemId;
    }

    @JsonProperty("ticketItemIdDescription")
    public String getTicketItemIdDescription() {
        return ticketItemIdDescription;
    }

    @JsonProperty("ticketItemIdDescription")
    public void setTicketItemIdDescription(String ticketItemIdDescription) {
        this.ticketItemIdDescription = ticketItemIdDescription;
    }

    @JsonProperty("udaId")
    public Integer getUdaId() {
        return udaId;
    }

    @JsonProperty("udaId")
    public void setUdaId(Integer udaId) {
        this.udaId = udaId;
    }

    @JsonProperty("sequenceNo")
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    @JsonProperty("sequenceNo")
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
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
