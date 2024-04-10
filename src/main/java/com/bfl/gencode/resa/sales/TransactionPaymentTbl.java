
package com.bfl.gencode.resa.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "paymentAmount"
})
public class TransactionPaymentTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("paymentAmount")
    private String paymentAmount;
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

    @JsonProperty("paymentAmount")
    public String getPaymentAmount() {
        return paymentAmount;
    }

    @JsonProperty("paymentAmount")
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}
