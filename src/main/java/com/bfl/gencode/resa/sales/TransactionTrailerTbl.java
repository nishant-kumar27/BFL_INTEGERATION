
package com.bfl.gencode.resa.sales;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "recType",
    "recCounter"
})
public class TransactionTrailerTbl implements Serializable
{

    @JsonProperty("recType")
    private String recType;
    @JsonProperty("recCounter")
    private String recCounter;
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

    @JsonProperty("recCounter")
    public String getRecCounter() {
        return recCounter;
    }

    @JsonProperty("recCounter")
    public void setRecCounter(String recCounter) {
        this.recCounter = recCounter;
    }

}
