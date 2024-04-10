
package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "itemStatus",
    "itemType",
    "itemNoType",
    "formatId",
    "item",
    "refItem",
    "nonMerchItem",
    "voucherNo",
    "quantity",
    "sellingUom",
    "unitRetail",
    "overrideReason",
    "originalUnitRetail",
    "taxableIndicator",
    "pump",
    "refNo5",
    "refNo6",
    "refNo7",
    "refNo8",
    "itemSwipedInd",
    "returnReasonCode",
    "salesperson",
    "expirationDate",
    "dropShipInd",
    "uomQty",
    "catchWeightInd",
    "sellingItem",
    "custOrdLineNo",
    "mediaId",
    "totalIgtaxAmount",
    "uniqueId",
    "custOrdNo",
    "custOrdDate",
    "fulfillmentOrdNo",
    "noInventoryReturn",
    "salesType",
    "returnWh",
    "returnDeposition",
    "originalStore",
    "originalTransactionNo",
    "fulfillmentLocType",
    "fulfillmentLoc",
    "postingStore",
    "transactionItemAttributeTbl",
    "transactionItemDiscountTbl",
    "transactionItemTaxTbl"
})
public class TransactionItemTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("itemStatus")
    private String itemStatus;
    @JsonProperty("itemType")
    private String itemType;
    @JsonProperty("itemNoType")
    private String itemNoType;
    @JsonProperty("formatId")
    private String formatId;
    @JsonProperty("item")
    private String item;
    @JsonProperty("refItem")
    private String refItem;
    @JsonProperty("nonMerchItem")
    private String nonMerchItem;
    @JsonProperty("voucherNo")
    private String voucherNo;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("sellingUom")
    private String sellingUom;
    @JsonProperty("unitRetail")
    private String unitRetail;
    @JsonProperty("overrideReason")
    private String overrideReason;
    @JsonProperty("originalUnitRetail")
    private String originalUnitRetail;
    @JsonProperty("taxableIndicator")
    private String taxableIndicator;
    @JsonProperty("pump")
    private String pump;
    @JsonProperty("refNo5")
    private String refNo5;
    @JsonProperty("refNo6")
    private String refNo6;
    @JsonProperty("refNo7")
    private String refNo7;
    @JsonProperty("refNo8")
    private String refNo8;
    @JsonProperty("itemSwipedInd")
    private String itemSwipedInd;
    @JsonProperty("returnReasonCode")
    private String returnReasonCode;
    @JsonProperty("salesperson")
    private String salesperson;
    @JsonProperty("expirationDate")
    private String expirationDate;
    @JsonProperty("dropShipInd")
    private String dropShipInd;
    @JsonProperty("uomQty")
    private String uomQty;
    @JsonProperty("catchWeightInd")
    private String catchWeightInd;
    @JsonProperty("sellingItem")
    private String sellingItem;
    @JsonProperty("custOrdLineNo")
    private String custOrdLineNo;
    @JsonProperty("mediaId")
    private String mediaId;
    @JsonProperty("totalIgtaxAmount")
    private String totalIgtaxAmount;
    @JsonProperty("uniqueId")
    private String uniqueId;
    @JsonProperty("custOrdNo")
    private String custOrdNo;
    @JsonProperty("custOrdDate")
    private String custOrdDate;
    @JsonProperty("fulfillmentOrdNo")
    private String fulfillmentOrdNo;
    @JsonProperty("noInventoryReturn")
    private String noInventoryReturn;
    @JsonProperty("salesType")
    private String salesType;
    @JsonProperty("returnWh")
    private String returnWh;
    @JsonProperty("returnDeposition")
    private String returnDeposition;
    @JsonProperty("originalStore")
    private String originalStore;
    @JsonProperty("originalTransactionNo")
    private String originalTransactionNo;
    @JsonProperty("fulfillmentLocType")
    private String fulfillmentLocType;
    @JsonProperty("fulfillmentLoc")
    private String fulfillmentLoc;
    @JsonProperty("postingStore")
    private String postingStore;
    @JsonProperty("transactionItemAttributeTbl")
    private List<TransactionItemAttributeTbl> transactionItemAttributeTbl = null;
    @JsonProperty("transactionItemDiscountTbl")
    private List<TransactionItemDiscountTbl> transactionItemDiscountTbl = null;
    @JsonProperty("transactionItemTaxTbl")
    private List<TransactionItemTaxTbl> transactionItemTaxTbl = null;
    /**
   	 * 
   	 */
   	private static final long serialVersionUID = 1L;
   	

    @JsonProperty("recType")
    public String getRecType() {
        return recType;
    }

    @JsonProperty("recType")
    public void setRecType(String recType) {
        this.recType = recType;
    }

    @JsonProperty("itemStatus")
    public String getItemStatus() {
        return itemStatus;
    }

    @JsonProperty("itemStatus")
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    @JsonProperty("itemType")
    public String getItemType() {
        return itemType;
    }

    @JsonProperty("itemType")
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @JsonProperty("itemNoType")
    public String getItemNoType() {
        return itemNoType;
    }

    @JsonProperty("itemNoType")
    public void setItemNoType(String itemNoType) {
        this.itemNoType = itemNoType;
    }

    @JsonProperty("formatId")
    public String getFormatId() {
        return formatId;
    }

    @JsonProperty("formatId")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("refItem")
    public String getRefItem() {
        return refItem;
    }

    @JsonProperty("refItem")
    public void setRefItem(String refItem) {
        this.refItem = refItem;
    }

    @JsonProperty("nonMerchItem")
    public String getNonMerchItem() {
        return nonMerchItem;
    }

    @JsonProperty("nonMerchItem")
    public void setNonMerchItem(String nonMerchItem) {
        this.nonMerchItem = nonMerchItem;
    }

    @JsonProperty("voucherNo")
    public String getVoucherNo() {
        return voucherNo;
    }

    @JsonProperty("voucherNo")
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("sellingUom")
    public String getSellingUom() {
        return sellingUom;
    }

    @JsonProperty("sellingUom")
    public void setSellingUom(String sellingUom) {
        this.sellingUom = sellingUom;
    }

    @JsonProperty("unitRetail")
    public String getUnitRetail() {
        return unitRetail;
    }

    @JsonProperty("unitRetail")
    public void setUnitRetail(String unitRetail) {
        this.unitRetail = unitRetail;
    }

    @JsonProperty("overrideReason")
    public String getOverrideReason() {
        return overrideReason;
    }

    @JsonProperty("overrideReason")
    public void setOverrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
    }

    @JsonProperty("originalUnitRetail")
    public String getOriginalUnitRetail() {
        return originalUnitRetail;
    }

    @JsonProperty("originalUnitRetail")
    public void setOriginalUnitRetail(String originalUnitRetail) {
        this.originalUnitRetail = originalUnitRetail;
    }

    @JsonProperty("taxableIndicator")
    public String getTaxableIndicator() {
        return taxableIndicator;
    }

    @JsonProperty("taxableIndicator")
    public void setTaxableIndicator(String taxableIndicator) {
        this.taxableIndicator = taxableIndicator;
    }

    @JsonProperty("pump")
    public String getPump() {
        return pump;
    }

    @JsonProperty("pump")
    public void setPump(String pump) {
        this.pump = pump;
    }

    @JsonProperty("refNo5")
    public String getRefNo5() {
        return refNo5;
    }

    @JsonProperty("refNo5")
    public void setRefNo5(String refNo5) {
        this.refNo5 = refNo5;
    }

    @JsonProperty("refNo6")
    public String getRefNo6() {
        return refNo6;
    }

    @JsonProperty("refNo6")
    public void setRefNo6(String refNo6) {
        this.refNo6 = refNo6;
    }

    @JsonProperty("refNo7")
    public String getRefNo7() {
        return refNo7;
    }

    @JsonProperty("refNo7")
    public void setRefNo7(String refNo7) {
        this.refNo7 = refNo7;
    }

    @JsonProperty("refNo8")
    public String getRefNo8() {
        return refNo8;
    }

    @JsonProperty("refNo8")
    public void setRefNo8(String refNo8) {
        this.refNo8 = refNo8;
    }

    @JsonProperty("itemSwipedInd")
    public String getItemSwipedInd() {
        return itemSwipedInd;
    }

    @JsonProperty("itemSwipedInd")
    public void setItemSwipedInd(String itemSwipedInd) {
        this.itemSwipedInd = itemSwipedInd;
    }

    @JsonProperty("returnReasonCode")
    public String getReturnReasonCode() {
        return returnReasonCode;
    }

    @JsonProperty("returnReasonCode")
    public void setReturnReasonCode(String returnReasonCode) {
        this.returnReasonCode = returnReasonCode;
    }

    @JsonProperty("salesperson")
    public String getSalesperson() {
        return salesperson;
    }

    @JsonProperty("salesperson")
    public void setSalesperson(String salesperson) {
        this.salesperson = salesperson;
    }

    @JsonProperty("expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("dropShipInd")
    public String getDropShipInd() {
        return dropShipInd;
    }

    @JsonProperty("dropShipInd")
    public void setDropShipInd(String dropShipInd) {
        this.dropShipInd = dropShipInd;
    }

    @JsonProperty("uomQty")
    public String getUomQty() {
        return uomQty;
    }

    @JsonProperty("uomQty")
    public void setUomQty(String uomQty) {
        this.uomQty = uomQty;
    }

    @JsonProperty("catchWeightInd")
    public String getCatchWeightInd() {
        return catchWeightInd;
    }

    @JsonProperty("catchWeightInd")
    public void setCatchWeightInd(String catchWeightInd) {
        this.catchWeightInd = catchWeightInd;
    }

    @JsonProperty("sellingItem")
    public String getSellingItem() {
        return sellingItem;
    }

    @JsonProperty("sellingItem")
    public void setSellingItem(String sellingItem) {
        this.sellingItem = sellingItem;
    }

    @JsonProperty("custOrdLineNo")
    public String getCustOrdLineNo() {
        return custOrdLineNo;
    }

    @JsonProperty("custOrdLineNo")
    public void setCustOrdLineNo(String custOrdLineNo) {
        this.custOrdLineNo = custOrdLineNo;
    }

    @JsonProperty("mediaId")
    public String getMediaId() {
        return mediaId;
    }

    @JsonProperty("mediaId")
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @JsonProperty("totalIgtaxAmount")
    public String getTotalIgtaxAmount() {
        return totalIgtaxAmount;
    }

    @JsonProperty("totalIgtaxAmount")
    public void setTotalIgtaxAmount(String totalIgtaxAmount) {
        this.totalIgtaxAmount = totalIgtaxAmount;
    }

    @JsonProperty("uniqueId")
    public String getUniqueId() {
        return uniqueId;
    }

    @JsonProperty("uniqueId")
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @JsonProperty("custOrdNo")
    public String getCustOrdNo() {
        return custOrdNo;
    }

    @JsonProperty("custOrdNo")
    public void setCustOrdNo(String custOrdNo) {
        this.custOrdNo = custOrdNo;
    }

    @JsonProperty("custOrdDate")
    public String getCustOrdDate() {
        return custOrdDate;
    }

    @JsonProperty("custOrdDate")
    public void setCustOrdDate(String custOrdDate) {
        this.custOrdDate = custOrdDate;
    }

    @JsonProperty("fulfillmentOrdNo")
    public String getFulfillmentOrdNo() {
        return fulfillmentOrdNo;
    }

    @JsonProperty("fulfillmentOrdNo")
    public void setFulfillmentOrdNo(String fulfillmentOrdNo) {
        this.fulfillmentOrdNo = fulfillmentOrdNo;
    }

    @JsonProperty("noInventoryReturn")
    public String getNoInventoryReturn() {
        return noInventoryReturn;
    }

    @JsonProperty("noInventoryReturn")
    public void setNoInventoryReturn(String noInventoryReturn) {
        this.noInventoryReturn = noInventoryReturn;
    }

    @JsonProperty("salesType")
    public String getSalesType() {
        return salesType;
    }

    @JsonProperty("salesType")
    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    @JsonProperty("returnWh")
    public String getReturnWh() {
        return returnWh;
    }

    @JsonProperty("returnWh")
    public void setReturnWh(String returnWh) {
        this.returnWh = returnWh;
    }

    @JsonProperty("returnDeposition")
    public String getReturnDeposition() {
        return returnDeposition;
    }

    @JsonProperty("returnDeposition")
    public void setReturnDeposition(String returnDeposition) {
        this.returnDeposition = returnDeposition;
    }

    @JsonProperty("originalStore")
    public String getOriginalStore() {
        return originalStore;
    }

    @JsonProperty("originalStore")
    public void setOriginalStore(String originalStore) {
        this.originalStore = originalStore;
    }

    @JsonProperty("originalTransactionNo")
    public String getOriginalTransactionNo() {
        return originalTransactionNo;
    }

    @JsonProperty("originalTransactionNo")
    public void setOriginalTransactionNo(String originalTransactionNo) {
        this.originalTransactionNo = originalTransactionNo;
    }

    @JsonProperty("fulfillmentLocType")
    public String getFulfillmentLocType() {
        return fulfillmentLocType;
    }

    @JsonProperty("fulfillmentLocType")
    public void setFulfillmentLocType(String fulfillmentLocType) {
        this.fulfillmentLocType = fulfillmentLocType;
    }

    @JsonProperty("fulfillmentLoc")
    public String getFulfillmentLoc() {
        return fulfillmentLoc;
    }

    @JsonProperty("fulfillmentLoc")
    public void setFulfillmentLoc(String fulfillmentLoc) {
        this.fulfillmentLoc = fulfillmentLoc;
    }

    @JsonProperty("postingStore")
    public String getPostingStore() {
        return postingStore;
    }

    @JsonProperty("postingStore")
    public void setPostingStore(String postingStore) {
        this.postingStore = postingStore;
    }

    @JsonProperty("transactionItemAttributeTbl")
    public List<TransactionItemAttributeTbl> getTransactionItemAttributeTbl() {
        return transactionItemAttributeTbl;
    }

    @JsonProperty("transactionItemAttributeTbl")
    public void setTransactionItemAttributeTbl(List<TransactionItemAttributeTbl> transactionItemAttributeTbl) {
        this.transactionItemAttributeTbl = transactionItemAttributeTbl;
    }

    @JsonProperty("transactionItemDiscountTbl")
    public List<TransactionItemDiscountTbl> getTransactionItemDiscountTbl() {
        return transactionItemDiscountTbl;
    }

    @JsonProperty("transactionItemDiscountTbl")
    public void setTransactionItemDiscountTbl(List<TransactionItemDiscountTbl> transactionItemDiscountTbl) {
        this.transactionItemDiscountTbl = transactionItemDiscountTbl;
    }

    @JsonProperty("transactionItemTaxTbl")
    public List<TransactionItemTaxTbl> getTransactionItemTaxTbl() {
        return transactionItemTaxTbl;
    }

    @JsonProperty("transactionItemTaxTbl")
    public void setTransactionItemTaxTbl(List<TransactionItemTaxTbl> transactionItemTaxTbl) {
        this.transactionItemTaxTbl = transactionItemTaxTbl;
    }
}
