package com.bfl.gencode.merchhier.emailNotification;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "notification_id",
    "notification_type",
    "subject",
    "to_receipients",
    "cc_receipients",
    "bcc_receipients",
    "notification_body",
    "status",
    "error_message",
    "create_id",
    "create_datetime",
    "last_update_id",
    "order_id",
    "attachment_ind",
    "last_update_datetime"
})
public class Item {
	
    @JsonProperty("notification_id")
    private Integer notificationId;
    
    @JsonProperty("notification_type")
    private String notificationType;
    
    @JsonProperty("subject")
    private String subject;
    
    @JsonProperty("to_receipients")
    private String toReceipients;
    
    @JsonProperty("cc_receipients")
    private String ccReceipients;
    
    @JsonProperty("bcc_receipients")
    private String bccReceipients;
    
    @JsonProperty("notification_body")
    private String notificationBody;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("error_message")
    private String errorMessage;
    
    @JsonProperty("create_id")
    private String createId;
    
    @JsonProperty("create_datetime")
    private String createDatetime;
    
    @JsonProperty("last_update_id")
    private String lastUpdateId;
    
    @JsonProperty("last_update_datetime")
    private String lastUpdateDatetime;
    
    @JsonProperty("order_id")
    private String orderId;
    
    @JsonProperty("attachment_ind")
    private String attachmentInd;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("notification_id")
    public Integer getNotificationId() {
        return notificationId;
    }

    @JsonProperty("notification_id")
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    @JsonProperty("notification_type")
    public String getNotificationType() {
        return notificationType;
    }

    @JsonProperty("notification_type")
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("to_receipients")
    public String getToReceipients() {
        return toReceipients;
    }

    @JsonProperty("to_receipients")
    public void setToReceipients(String toReceipients) {
        this.toReceipients = toReceipients;
    }

    @JsonProperty("cc_receipients")
    public String getCcReceipients() {
        return ccReceipients;
    }

    @JsonProperty("cc_receipients")
    public void setCcReceipients(String ccReceipients) {
        this.ccReceipients = ccReceipients;
    }

    @JsonProperty("bcc_receipients")
    public String getBccReceipients() {
        return bccReceipients;
    }

    @JsonProperty("bcc_receipients")
    public void setBccReceipients(String bccReceipients) {
        this.bccReceipients = bccReceipients;
    }

    @JsonProperty("notification_body")
    public String getNotificationBody() {
        return notificationBody;
    }

    @JsonProperty("notification_body")
    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("error_message")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("create_id")
    public String getCreateId() {
        return createId;
    }

    @JsonProperty("create_id")
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    @JsonProperty("create_datetime")
    public String getCreateDatetime() {
        return createDatetime;
    }

    @JsonProperty("create_datetime")
    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    @JsonProperty("last_update_id")
    public String getLastUpdateId() {
        return lastUpdateId;
    }

    @JsonProperty("last_update_id")
    public void setLastUpdateId(String lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    @JsonProperty("last_update_datetime")
    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    @JsonProperty("last_update_datetime")
    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @JsonProperty("order_id")
	public String getOrderId() {
		return orderId;
	}
    
    @JsonProperty("order_id")
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
    @JsonProperty("attachment_ind")
	public String getAttachmentInd() {
		return attachmentInd;
	}
    
    @JsonProperty("attachment_ind")
	public void setAttachmentInd(String attachmentInd) {
		this.attachmentInd = attachmentInd;
	}
    
    
}
