package com.bfl.dto;

public class ContainerDetailsDTO {
	
	public String oldContNo;
	
	public String newContNo;
	
	public String legacy;
	
	public String rms;
	
	public String trndate;
	
	public String contNo;
	    
	private String orderNo;
	
	private String shipment;
	
	private String asn;
	
	private String partialShipmentInd;
	
	private String manifestRefStatus;
	
	private String refNo;
	
	public String getOldContNo() {
		return oldContNo;
	}
	
	public void setOldContNo(String oldContNo) {
		this.oldContNo = oldContNo;
	}
	
	public String getNewContNo() {
		return newContNo;
	}
	
	public void setNewContNo(String newContNo) {
		this.newContNo = newContNo;
	}

	public String getLegacy() {
		return legacy;
	}

	public void setLegacy(String legacy) {
		this.legacy = legacy;
	}

	public String getRms() {
		return rms;
	}

	public void setRms(String rms) {
		this.rms = rms;
	}

	public String getTrndate() {
		return trndate;
	}

	public void setTrndate(String trndate) {
		this.trndate = trndate;
	}

	public String getcontNo() {
		return contNo;
	}

	public void setcontNo(String containerno) {
		this.contNo = containerno;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShipment() {
		return shipment;
	}

	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

	public String getAsn() {
		return asn;
	}

	public void setAsn(String asn) {
		this.asn = asn;
	}

	public String getPartialShipmentInd() {
		return partialShipmentInd;
	}

	public void setPartialShipmentInd(String partialShipmentInd) {
		this.partialShipmentInd = partialShipmentInd;
	}

	public String getManifestRefStatus() {
		return manifestRefStatus;
	}

	public void setManifestRefStatus(String manifestRefStatus) {
		this.manifestRefStatus = manifestRefStatus;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
}