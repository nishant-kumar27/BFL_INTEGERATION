
package com.bfl.gencode.resa.sales;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "merchPromoNo",
    "discountRefNo",
    "discountType",
    "couponNo",
    "couponRefNo",
    "quantity",
    "unitDiscountAmount",
    "refNo13",
    "refNo14",
    "refNo15",
    "refNo16",
    "uomQty",
    "catchWeightInd",
    "promoComponent",
    "transactionItemDiscountAtrributeTbl"
})
public class TransactionItemDiscountTbl implements Serializable {

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("merchPromoNo")
    private String merchPromoNo;
    @JsonProperty("discountRefNo")
    private String discountRefNo;
    @JsonProperty("discountType")
    private String discountType;
    @JsonProperty("couponNo")
    private String couponNo;
    @JsonProperty("couponRefNo")
    private String couponRefNo;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("unitDiscountAmount")
    private String unitDiscountAmount;
    @JsonProperty("refNo13")
    private String refNo13;
    @JsonProperty("refNo14")
    private String refNo14;
    @JsonProperty("refNo15")
    private String refNo15;
    @JsonProperty("refNo16")
    private String refNo16;
    @JsonProperty("uomQty")
    private String uomQty;
    @JsonProperty("catchWeightInd")
    private String catchWeightInd;
    @JsonProperty("promoComponent")
    private String promoComponent;
    @JsonProperty("transactionItemDiscountAtrributeTbl")
    private List<TransactionItemDiscountAtrributeTbl> transactionItemDiscountAtrributeTbl = null;
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

    @JsonProperty("merchPromoNo")
    public String getMerchPromoNo() {
        return merchPromoNo;
    }

    @JsonProperty("merchPromoNo")
    public void setMerchPromoNo(String merchPromoNo) {
        this.merchPromoNo = merchPromoNo;
    }

    @JsonProperty("discountRefNo")
    public String getDiscountRefNo() {
        return discountRefNo;
    }

    @JsonProperty("discountRefNo")
    public void setDiscountRefNo(String discountRefNo) {
        this.discountRefNo = discountRefNo;
    }

    @JsonProperty("discountType")
    public String getDiscountType() {
        return discountType;
    }

    @JsonProperty("discountType")
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @JsonProperty("couponNo")
    public String getCouponNo() {
        return couponNo;
    }

    @JsonProperty("couponNo")
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    @JsonProperty("couponRefNo")
    public String getCouponRefNo() {
        return couponRefNo;
    }

    @JsonProperty("couponRefNo")
    public void setCouponRefNo(String couponRefNo) {
        this.couponRefNo = couponRefNo;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("unitDiscountAmount")
    public String getUnitDiscountAmount() {
        return unitDiscountAmount;
    }

    @JsonProperty("unitDiscountAmount")
    public void setUnitDiscountAmount(String unitDiscountAmount) {
        this.unitDiscountAmount = unitDiscountAmount;
    }

    @JsonProperty("refNo13")
    public String getRefNo13() {
        return refNo13;
    }

    @JsonProperty("refNo13")
    public void setRefNo13(String refNo13) {
        this.refNo13 = refNo13;
    }

    @JsonProperty("refNo14")
    public String getRefNo14() {
        return refNo14;
    }

    @JsonProperty("refNo14")
    public void setRefNo14(String refNo14) {
        this.refNo14 = refNo14;
    }

    @JsonProperty("refNo15")
    public String getRefNo15() {
        return refNo15;
    }

    @JsonProperty("refNo15")
    public void setRefNo15(String refNo15) {
        this.refNo15 = refNo15;
    }

    @JsonProperty("refNo16")
    public String getRefNo16() {
        return refNo16;
    }

    @JsonProperty("refNo16")
    public void setRefNo16(String refNo16) {
        this.refNo16 = refNo16;
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

    @JsonProperty("promoComponent")
    public String getPromoComponent() {
        return promoComponent;
    }

    @JsonProperty("promoComponent")
    public void setPromoComponent(String promoComponent) {
        this.promoComponent = promoComponent;
    }

    @JsonProperty("transactionItemDiscountAtrributeTbl")
    public List<TransactionItemDiscountAtrributeTbl> getTransactionItemDiscountAtrributeTbl() {
        return transactionItemDiscountAtrributeTbl;
    }

    @JsonProperty("transactionItemDiscountAtrributeTbl")
    public void setTransactionItemDiscountAtrributeTbl(List<TransactionItemDiscountAtrributeTbl> transactionItemDiscountAtrributeTbl) {
        this.transactionItemDiscountAtrributeTbl = transactionItemDiscountAtrributeTbl;
    }

}
