package com.bfl.gencode.merchhier.Cont.Response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "container_id",
    "process_id",
    "order_no",
    "shipment",
    "asn",
    "partial_shipment_ind",
    "ref_no",
    "manifest_ref_status"
})
public class Item implements Serializable {
	
    @JsonProperty("container_id")
    private String containerId;
    
    @JsonProperty("process_id")
    private String processId;
    
    @JsonProperty("order_no")
    private String orderNo;
    
    @JsonProperty("shipment")
    private String shipment;
    
    @JsonProperty("asn")
    private String asn;
    
    @JsonProperty("partial_shipment_ind")
    private String partialShipmentInd;
    
    @JsonProperty("manifest_ref_status")
    private String manifestRefStatus;
    
    @JsonProperty("ref_no")
    private String refNo;
    
    private final static long serialVersionUID = 5124726686460929292L;

    @JsonProperty("container_id")
    public String getContainerId() {
        return containerId;
    }

    @JsonProperty("container_id")
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
    
    @JsonProperty("process_id")
    public String getProcessId() {
		return processId;
	}

    @JsonProperty("process_id")
	public void setProcessId(String processId) {
		this.processId = processId;
	}

    @JsonProperty("order_no")
	public String getOrderNo() {
		return orderNo;
	}

    @JsonProperty("order_no")
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
    @JsonProperty("shipment")
    public String getShipment() {
		return shipment;
	}

    @JsonProperty("shipment")
	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

    @JsonProperty("asn")
	public String getAsn() {
		return asn;
	}

    @JsonProperty("asn")
	public void setAsn(String asn) {
		this.asn = asn;
	}
    
    @JsonProperty("partial_shipment_ind")
	public String getPartialShipmentInd() {
		return partialShipmentInd;
	}

    @JsonProperty("partial_shipment_ind")
	public void setPartialShipmentInd(String partialShipmentInd) {
		this.partialShipmentInd = partialShipmentInd;
	}

    @JsonProperty("manifest_ref_status")
	public String getManifestRefStatus() {
		return manifestRefStatus;
	}

    @JsonProperty("manifest_ref_status")
	public void setManifestRefStatus(String manifestRefStatus) {
		this.manifestRefStatus = manifestRefStatus;
	}

    @JsonProperty("ref_no")
	public String getRefNo() {
		return refNo;
	}

	@JsonProperty("ref_no")
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
    
}
