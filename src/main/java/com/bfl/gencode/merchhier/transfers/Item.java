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
    "action",
    "transferNo",
    "docType",
    "physicalFromLocation",
    "fromLocationType",
    "fromStoreType",
    "fromStockholdingInd",
    "fromLocation",
    "physicalToLocation",
    "toLocationType",
    "toStoreType",
    "toStockholdingInd",
    "toLocation",
    "transferType",
    "pickNotBeforeDate",
    "pickNotAfterDate",
    "orderType",
    "breakByDistro",
    "deliveryDate",
    "deliverAdd1",
    "deliverAdd2",
    "deliverCity",
    "deliverState",
    "deliverPost",
    "deliverCountryId",
    "comments",
    "details",
    "transferParentNo",
    "expectedDcDate",
    "approvalId",
    "approvalDate",
    "fromLocationTransferEntity",
    "toLocationTransferEntity",
    "invType",
    "transferStatus",
    "notAfterDate",
    "contextType",
    "contextValue",
    "deliverySlotId",
    "deliverySlotDescription",
    "customerOrderNo",
    "fulfillmentOrderNumber",
    "carrierCode",
    "carrierServiceCode",
    "consumerDeliveryDate",
    "consumerDeliveryTime",
    "deliverFirstName",
    "deliverPhoneticFirst",
    "deliverLastName",
    "deliverPhoneticLast",
    "deliverPreferredName",
    "deliverCompanyName",
    "deliverAdd3",
    "deliverCounty",
    "deliverPhone",
    "billFirstName",
    "billPhoneticFirst",
    "billLastName",
    "billPhoneticLast",
    "billPreferredName",
    "billCompanyName",
    "billAdd1",
    "billAdd2",
    "billAdd3",
    "billCounty",
    "billCity",
    "billCountry",
    "billPost",
    "billState",
    "billPhone",
    "partialDeliveryInd",
    "consumerDirectInd",
    "customFlexAttribute",
    "createDateTime",
    "updateDateTime",
    "cacheTimestamp"
})
public class Item {

    @JsonProperty("action")
    private String action;
    @JsonProperty("transferNo")
    private String transferNo;
    @JsonProperty("docType")
    private String docType;
    @JsonProperty("physicalFromLocation")
    private Integer physicalFromLocation;
    @JsonProperty("fromLocationType")
    private String fromLocationType;
    @JsonProperty("fromStoreType")
    private String fromStoreType;
    @JsonProperty("fromStockholdingInd")
    private String fromStockholdingInd;
    @JsonProperty("fromLocation")
    private Integer fromLocation;
    @JsonProperty("physicalToLocation")
    private Integer physicalToLocation;
    @JsonProperty("toLocationType")
    private String toLocationType;
    @JsonProperty("toStoreType")
    private String toStoreType;
    @JsonProperty("toStockholdingInd")
    private String toStockholdingInd;
    @JsonProperty("toLocation")
    private Integer toLocation;
    @JsonProperty("transferType")
    private String transferType;
    @JsonProperty("pickNotBeforeDate")
    private String pickNotBeforeDate;
    @JsonProperty("pickNotAfterDate")
    private String pickNotAfterDate;
    @JsonProperty("orderType")
    private String orderType;
    @JsonProperty("breakByDistro")
    private String breakByDistro;
    @JsonProperty("deliveryDate")
    private String deliveryDate;
    @JsonProperty("deliverAdd1")
    private String deliverAdd1;
    @JsonProperty("deliverAdd2")
    private String deliverAdd2;
    @JsonProperty("deliverCity")
    private String deliverCity;
    @JsonProperty("deliverState")
    private String deliverState;
    @JsonProperty("deliverPost")
    private String deliverPost;
    @JsonProperty("deliverCountryId")
    private String deliverCountryId;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("details")
    private List<Detail> details = null;
    @JsonProperty("transferParentNo")
    private Integer transferParentNo;
    @JsonProperty("expectedDcDate")
    private String expectedDcDate;
    @JsonProperty("approvalId")
    private String approvalId;
    @JsonProperty("approvalDate")
    private String approvalDate;
    @JsonProperty("fromLocationTransferEntity")
    private Integer fromLocationTransferEntity;
    @JsonProperty("toLocationTransferEntity")
    private Integer toLocationTransferEntity;
    @JsonProperty("invType")
    private String invType;
    @JsonProperty("transferStatus")
    private String transferStatus;
    @JsonProperty("notAfterDate")
    private String notAfterDate;
    @JsonProperty("contextType")
    private String contextType;
    @JsonProperty("contextValue")
    private String contextValue;
    @JsonProperty("deliverySlotId")
    private String deliverySlotId;
    @JsonProperty("deliverySlotDescription")
    private String deliverySlotDescription;
    @JsonProperty("customerOrderNo")
    private String customerOrderNo;
    @JsonProperty("fulfillmentOrderNumber")
    private String fulfillmentOrderNumber;
    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("carrierServiceCode")
    private String carrierServiceCode;
    @JsonProperty("consumerDeliveryDate")
    private String consumerDeliveryDate;
    @JsonProperty("consumerDeliveryTime")
    private String consumerDeliveryTime;
    @JsonProperty("deliverFirstName")
    private String deliverFirstName;
    @JsonProperty("deliverPhoneticFirst")
    private String deliverPhoneticFirst;
    @JsonProperty("deliverLastName")
    private String deliverLastName;
    @JsonProperty("deliverPhoneticLast")
    private String deliverPhoneticLast;
    @JsonProperty("deliverPreferredName")
    private String deliverPreferredName;
    @JsonProperty("deliverCompanyName")
    private String deliverCompanyName;
    @JsonProperty("deliverAdd3")
    private String deliverAdd3;
    @JsonProperty("deliverCounty")
    private String deliverCounty;
    @JsonProperty("deliverPhone")
    private String deliverPhone;
    @JsonProperty("billFirstName")
    private String billFirstName;
    @JsonProperty("billPhoneticFirst")
    private String billPhoneticFirst;
    @JsonProperty("billLastName")
    private String billLastName;
    @JsonProperty("billPhoneticLast")
    private String billPhoneticLast;
    @JsonProperty("billPreferredName")
    private String billPreferredName;
    @JsonProperty("billCompanyName")
    private String billCompanyName;
    @JsonProperty("billAdd1")
    private String billAdd1;
    @JsonProperty("billAdd2")
    private String billAdd2;
    @JsonProperty("billAdd3")
    private String billAdd3;
    @JsonProperty("billCounty")
    private String billCounty;
    @JsonProperty("billCity")
    private String billCity;
    @JsonProperty("billCountry")
    private String billCountry;
    @JsonProperty("billPost")
    private String billPost;
    @JsonProperty("billState")
    private String billState;
    @JsonProperty("billPhone")
    private String billPhone;
    @JsonProperty("partialDeliveryInd")
    private String partialDeliveryInd;
    @JsonProperty("consumerDirectInd")
    private String consumerDirectInd;
    @JsonProperty("customFlexAttribute")
    private List<CustomFlexAttribute> customFlexAttribute = null;
    @JsonProperty("createDateTime")
    private String createDateTime;
    @JsonProperty("updateDateTime")
    private String updateDateTime;
    @JsonProperty("cacheTimestamp")
    private String cacheTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("transferNo")
    public String getTransferNo() {
        return transferNo;
    }

    @JsonProperty("transferNo")
    public void setTransferNo(String transferNo) {
        this.transferNo = transferNo;
    }

    @JsonProperty("docType")
    public String getDocType() {
        return docType;
    }

    @JsonProperty("docType")
    public void setDocType(String docType) {
        this.docType = docType;
    }

    @JsonProperty("physicalFromLocation")
    public Integer getPhysicalFromLocation() {
        return physicalFromLocation;
    }

    @JsonProperty("physicalFromLocation")
    public void setPhysicalFromLocation(Integer physicalFromLocation) {
        this.physicalFromLocation = physicalFromLocation;
    }

    @JsonProperty("fromLocationType")
    public String getFromLocationType() {
        return fromLocationType;
    }

    @JsonProperty("fromLocationType")
    public void setFromLocationType(String fromLocationType) {
        this.fromLocationType = fromLocationType;
    }

    @JsonProperty("fromStoreType")
    public String getFromStoreType() {
        return fromStoreType;
    }

    @JsonProperty("fromStoreType")
    public void setFromStoreType(String fromStoreType) {
        this.fromStoreType = fromStoreType;
    }

    @JsonProperty("fromStockholdingInd")
    public String getFromStockholdingInd() {
        return fromStockholdingInd;
    }

    @JsonProperty("fromStockholdingInd")
    public void setFromStockholdingInd(String fromStockholdingInd) {
        this.fromStockholdingInd = fromStockholdingInd;
    }

    @JsonProperty("fromLocation")
    public Integer getFromLocation() {
        return fromLocation;
    }

    @JsonProperty("fromLocation")
    public void setFromLocation(Integer fromLocation) {
        this.fromLocation = fromLocation;
    }

    @JsonProperty("physicalToLocation")
    public Integer getPhysicalToLocation() {
        return physicalToLocation;
    }

    @JsonProperty("physicalToLocation")
    public void setPhysicalToLocation(Integer physicalToLocation) {
        this.physicalToLocation = physicalToLocation;
    }

    @JsonProperty("toLocationType")
    public String getToLocationType() {
        return toLocationType;
    }

    @JsonProperty("toLocationType")
    public void setToLocationType(String toLocationType) {
        this.toLocationType = toLocationType;
    }

    @JsonProperty("toStoreType")
    public String getToStoreType() {
        return toStoreType;
    }

    @JsonProperty("toStoreType")
    public void setToStoreType(String toStoreType) {
        this.toStoreType = toStoreType;
    }

    @JsonProperty("toStockholdingInd")
    public String getToStockholdingInd() {
        return toStockholdingInd;
    }

    @JsonProperty("toStockholdingInd")
    public void setToStockholdingInd(String toStockholdingInd) {
        this.toStockholdingInd = toStockholdingInd;
    }

    @JsonProperty("toLocation")
    public Integer getToLocation() {
        return toLocation;
    }

    @JsonProperty("toLocation")
    public void setToLocation(Integer toLocation) {
        this.toLocation = toLocation;
    }

    @JsonProperty("transferType")
    public String getTransferType() {
        return transferType;
    }

    @JsonProperty("transferType")
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    @JsonProperty("pickNotBeforeDate")
    public String getPickNotBeforeDate() {
        return pickNotBeforeDate;
    }

    @JsonProperty("pickNotBeforeDate")
    public void setPickNotBeforeDate(String pickNotBeforeDate) {
        this.pickNotBeforeDate = pickNotBeforeDate;
    }

    @JsonProperty("pickNotAfterDate")
    public String getPickNotAfterDate() {
        return pickNotAfterDate;
    }

    @JsonProperty("pickNotAfterDate")
    public void setPickNotAfterDate(String pickNotAfterDate) {
        this.pickNotAfterDate = pickNotAfterDate;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @JsonProperty("breakByDistro")
    public String getBreakByDistro() {
        return breakByDistro;
    }

    @JsonProperty("breakByDistro")
    public void setBreakByDistro(String breakByDistro) {
        this.breakByDistro = breakByDistro;
    }

    @JsonProperty("deliveryDate")
    public String getDeliveryDate() {
        return deliveryDate;
    }

    @JsonProperty("deliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JsonProperty("deliverAdd1")
    public String getDeliverAdd1() {
        return deliverAdd1;
    }

    @JsonProperty("deliverAdd1")
    public void setDeliverAdd1(String deliverAdd1) {
        this.deliverAdd1 = deliverAdd1;
    }

    @JsonProperty("deliverAdd2")
    public String getDeliverAdd2() {
        return deliverAdd2;
    }

    @JsonProperty("deliverAdd2")
    public void setDeliverAdd2(String deliverAdd2) {
        this.deliverAdd2 = deliverAdd2;
    }

    @JsonProperty("deliverCity")
    public String getDeliverCity() {
        return deliverCity;
    }

    @JsonProperty("deliverCity")
    public void setDeliverCity(String deliverCity) {
        this.deliverCity = deliverCity;
    }

    @JsonProperty("deliverState")
    public String getDeliverState() {
        return deliverState;
    }

    @JsonProperty("deliverState")
    public void setDeliverState(String deliverState) {
        this.deliverState = deliverState;
    }

    @JsonProperty("deliverPost")
    public String getDeliverPost() {
        return deliverPost;
    }

    @JsonProperty("deliverPost")
    public void setDeliverPost(String deliverPost) {
        this.deliverPost = deliverPost;
    }

    @JsonProperty("deliverCountryId")
    public String getDeliverCountryId() {
        return deliverCountryId;
    }

    @JsonProperty("deliverCountryId")
    public void setDeliverCountryId(String deliverCountryId) {
        this.deliverCountryId = deliverCountryId;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    @JsonProperty("transferParentNo")
    public Integer getTransferParentNo() {
        return transferParentNo;
    }

    @JsonProperty("transferParentNo")
    public void setTransferParentNo(Integer transferParentNo) {
        this.transferParentNo = transferParentNo;
    }

    @JsonProperty("expectedDcDate")
    public String getExpectedDcDate() {
        return expectedDcDate;
    }

    @JsonProperty("expectedDcDate")
    public void setExpectedDcDate(String expectedDcDate) {
        this.expectedDcDate = expectedDcDate;
    }

    @JsonProperty("approvalId")
    public String getApprovalId() {
        return approvalId;
    }

    @JsonProperty("approvalId")
    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    @JsonProperty("approvalDate")
    public String getApprovalDate() {
        return approvalDate;
    }

    @JsonProperty("approvalDate")
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    @JsonProperty("fromLocationTransferEntity")
    public Integer getFromLocationTransferEntity() {
        return fromLocationTransferEntity;
    }

    @JsonProperty("fromLocationTransferEntity")
    public void setFromLocationTransferEntity(Integer fromLocationTransferEntity) {
        this.fromLocationTransferEntity = fromLocationTransferEntity;
    }

    @JsonProperty("toLocationTransferEntity")
    public Integer getToLocationTransferEntity() {
        return toLocationTransferEntity;
    }

    @JsonProperty("toLocationTransferEntity")
    public void setToLocationTransferEntity(Integer toLocationTransferEntity) {
        this.toLocationTransferEntity = toLocationTransferEntity;
    }

    @JsonProperty("invType")
    public String getInvType() {
        return invType;
    }

    @JsonProperty("invType")
    public void setInvType(String invType) {
        this.invType = invType;
    }

    @JsonProperty("transferStatus")
    public String getTransferStatus() {
        return transferStatus;
    }

    @JsonProperty("transferStatus")
    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    @JsonProperty("notAfterDate")
    public String getNotAfterDate() {
        return notAfterDate;
    }

    @JsonProperty("notAfterDate")
    public void setNotAfterDate(String notAfterDate) {
        this.notAfterDate = notAfterDate;
    }

    @JsonProperty("contextType")
    public String getContextType() {
        return contextType;
    }

    @JsonProperty("contextType")
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    @JsonProperty("contextValue")
    public String getContextValue() {
        return contextValue;
    }

    @JsonProperty("contextValue")
    public void setContextValue(String contextValue) {
        this.contextValue = contextValue;
    }

    @JsonProperty("deliverySlotId")
    public String getDeliverySlotId() {
        return deliverySlotId;
    }

    @JsonProperty("deliverySlotId")
    public void setDeliverySlotId(String deliverySlotId) {
        this.deliverySlotId = deliverySlotId;
    }

    @JsonProperty("deliverySlotDescription")
    public String getDeliverySlotDescription() {
        return deliverySlotDescription;
    }

    @JsonProperty("deliverySlotDescription")
    public void setDeliverySlotDescription(String deliverySlotDescription) {
        this.deliverySlotDescription = deliverySlotDescription;
    }

    @JsonProperty("customerOrderNo")
    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    @JsonProperty("customerOrderNo")
    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    @JsonProperty("fulfillmentOrderNumber")
    public String getFulfillmentOrderNumber() {
        return fulfillmentOrderNumber;
    }

    @JsonProperty("fulfillmentOrderNumber")
    public void setFulfillmentOrderNumber(String fulfillmentOrderNumber) {
        this.fulfillmentOrderNumber = fulfillmentOrderNumber;
    }

    @JsonProperty("carrierCode")
    public String getCarrierCode() {
        return carrierCode;
    }

    @JsonProperty("carrierCode")
    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    @JsonProperty("carrierServiceCode")
    public String getCarrierServiceCode() {
        return carrierServiceCode;
    }

    @JsonProperty("carrierServiceCode")
    public void setCarrierServiceCode(String carrierServiceCode) {
        this.carrierServiceCode = carrierServiceCode;
    }

    @JsonProperty("consumerDeliveryDate")
    public String getConsumerDeliveryDate() {
        return consumerDeliveryDate;
    }

    @JsonProperty("consumerDeliveryDate")
    public void setConsumerDeliveryDate(String consumerDeliveryDate) {
        this.consumerDeliveryDate = consumerDeliveryDate;
    }

    @JsonProperty("consumerDeliveryTime")
    public String getConsumerDeliveryTime() {
        return consumerDeliveryTime;
    }

    @JsonProperty("consumerDeliveryTime")
    public void setConsumerDeliveryTime(String consumerDeliveryTime) {
        this.consumerDeliveryTime = consumerDeliveryTime;
    }

    @JsonProperty("deliverFirstName")
    public String getDeliverFirstName() {
        return deliverFirstName;
    }

    @JsonProperty("deliverFirstName")
    public void setDeliverFirstName(String deliverFirstName) {
        this.deliverFirstName = deliverFirstName;
    }

    @JsonProperty("deliverPhoneticFirst")
    public String getDeliverPhoneticFirst() {
        return deliverPhoneticFirst;
    }

    @JsonProperty("deliverPhoneticFirst")
    public void setDeliverPhoneticFirst(String deliverPhoneticFirst) {
        this.deliverPhoneticFirst = deliverPhoneticFirst;
    }

    @JsonProperty("deliverLastName")
    public String getDeliverLastName() {
        return deliverLastName;
    }

    @JsonProperty("deliverLastName")
    public void setDeliverLastName(String deliverLastName) {
        this.deliverLastName = deliverLastName;
    }

    @JsonProperty("deliverPhoneticLast")
    public String getDeliverPhoneticLast() {
        return deliverPhoneticLast;
    }

    @JsonProperty("deliverPhoneticLast")
    public void setDeliverPhoneticLast(String deliverPhoneticLast) {
        this.deliverPhoneticLast = deliverPhoneticLast;
    }

    @JsonProperty("deliverPreferredName")
    public String getDeliverPreferredName() {
        return deliverPreferredName;
    }

    @JsonProperty("deliverPreferredName")
    public void setDeliverPreferredName(String deliverPreferredName) {
        this.deliverPreferredName = deliverPreferredName;
    }

    @JsonProperty("deliverCompanyName")
    public String getDeliverCompanyName() {
        return deliverCompanyName;
    }

    @JsonProperty("deliverCompanyName")
    public void setDeliverCompanyName(String deliverCompanyName) {
        this.deliverCompanyName = deliverCompanyName;
    }

    @JsonProperty("deliverAdd3")
    public String getDeliverAdd3() {
        return deliverAdd3;
    }

    @JsonProperty("deliverAdd3")
    public void setDeliverAdd3(String deliverAdd3) {
        this.deliverAdd3 = deliverAdd3;
    }

    @JsonProperty("deliverCounty")
    public String getDeliverCounty() {
        return deliverCounty;
    }

    @JsonProperty("deliverCounty")
    public void setDeliverCounty(String deliverCounty) {
        this.deliverCounty = deliverCounty;
    }

    @JsonProperty("deliverPhone")
    public String getDeliverPhone() {
        return deliverPhone;
    }

    @JsonProperty("deliverPhone")
    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    @JsonProperty("billFirstName")
    public String getBillFirstName() {
        return billFirstName;
    }

    @JsonProperty("billFirstName")
    public void setBillFirstName(String billFirstName) {
        this.billFirstName = billFirstName;
    }

    @JsonProperty("billPhoneticFirst")
    public String getBillPhoneticFirst() {
        return billPhoneticFirst;
    }

    @JsonProperty("billPhoneticFirst")
    public void setBillPhoneticFirst(String billPhoneticFirst) {
        this.billPhoneticFirst = billPhoneticFirst;
    }

    @JsonProperty("billLastName")
    public String getBillLastName() {
        return billLastName;
    }

    @JsonProperty("billLastName")
    public void setBillLastName(String billLastName) {
        this.billLastName = billLastName;
    }

    @JsonProperty("billPhoneticLast")
    public String getBillPhoneticLast() {
        return billPhoneticLast;
    }

    @JsonProperty("billPhoneticLast")
    public void setBillPhoneticLast(String billPhoneticLast) {
        this.billPhoneticLast = billPhoneticLast;
    }

    @JsonProperty("billPreferredName")
    public String getBillPreferredName() {
        return billPreferredName;
    }

    @JsonProperty("billPreferredName")
    public void setBillPreferredName(String billPreferredName) {
        this.billPreferredName = billPreferredName;
    }

    @JsonProperty("billCompanyName")
    public String getBillCompanyName() {
        return billCompanyName;
    }

    @JsonProperty("billCompanyName")
    public void setBillCompanyName(String billCompanyName) {
        this.billCompanyName = billCompanyName;
    }

    @JsonProperty("billAdd1")
    public String getBillAdd1() {
        return billAdd1;
    }

    @JsonProperty("billAdd1")
    public void setBillAdd1(String billAdd1) {
        this.billAdd1 = billAdd1;
    }

    @JsonProperty("billAdd2")
    public String getBillAdd2() {
        return billAdd2;
    }

    @JsonProperty("billAdd2")
    public void setBillAdd2(String billAdd2) {
        this.billAdd2 = billAdd2;
    }

    @JsonProperty("billAdd3")
    public String getBillAdd3() {
        return billAdd3;
    }

    @JsonProperty("billAdd3")
    public void setBillAdd3(String billAdd3) {
        this.billAdd3 = billAdd3;
    }

    @JsonProperty("billCounty")
    public String getBillCounty() {
        return billCounty;
    }

    @JsonProperty("billCounty")
    public void setBillCounty(String billCounty) {
        this.billCounty = billCounty;
    }

    @JsonProperty("billCity")
    public String getBillCity() {
        return billCity;
    }

    @JsonProperty("billCity")
    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    @JsonProperty("billCountry")
    public String getBillCountry() {
        return billCountry;
    }

    @JsonProperty("billCountry")
    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    @JsonProperty("billPost")
    public String getBillPost() {
        return billPost;
    }

    @JsonProperty("billPost")
    public void setBillPost(String billPost) {
        this.billPost = billPost;
    }

    @JsonProperty("billState")
    public String getBillState() {
        return billState;
    }

    @JsonProperty("billState")
    public void setBillState(String billState) {
        this.billState = billState;
    }

    @JsonProperty("billPhone")
    public String getBillPhone() {
        return billPhone;
    }

    @JsonProperty("billPhone")
    public void setBillPhone(String billPhone) {
        this.billPhone = billPhone;
    }

    @JsonProperty("partialDeliveryInd")
    public String getPartialDeliveryInd() {
        return partialDeliveryInd;
    }

    @JsonProperty("partialDeliveryInd")
    public void setPartialDeliveryInd(String partialDeliveryInd) {
        this.partialDeliveryInd = partialDeliveryInd;
    }

    @JsonProperty("consumerDirectInd")
    public String getConsumerDirectInd() {
        return consumerDirectInd;
    }

    @JsonProperty("consumerDirectInd")
    public void setConsumerDirectInd(String consumerDirectInd) {
        this.consumerDirectInd = consumerDirectInd;
    }

    @JsonProperty("customFlexAttribute")
    public List<CustomFlexAttribute> getCustomFlexAttribute() {
        return customFlexAttribute;
    }

    @JsonProperty("customFlexAttribute")
    public void setCustomFlexAttribute(List<CustomFlexAttribute> customFlexAttribute) {
        this.customFlexAttribute = customFlexAttribute;
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

    @JsonProperty("cacheTimestamp")
    public String getCacheTimestamp() {
        return cacheTimestamp;
    }

    @JsonProperty("cacheTimestamp")
    public void setCacheTimestamp(String cacheTimestamp) {
        this.cacheTimestamp = cacheTimestamp;
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
