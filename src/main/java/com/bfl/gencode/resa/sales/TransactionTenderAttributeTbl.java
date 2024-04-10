
package com.bfl.gencode.resa.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "attributeType",
    "attributeValue"
})
public class TransactionTenderAttributeTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("attributeType")
    private String attributeType;
    @JsonProperty("attributeValue")
    private String attributeValue;
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

    @JsonProperty("attributeType")
    public String getAttributeType() {
        return attributeType;
    }

    @JsonProperty("attributeType")
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    @JsonProperty("attributeValue")
    public String getAttributeValue() {
        return attributeValue;
    }

    @JsonProperty("attributeValue")
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
