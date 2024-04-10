package com.bfl.alerts;

import java.io.File;
import java.util.List;
import java.util.Map;

public class EmailTemplateDTO {
	
	private String[] recepients;
	
	private String subject;
	
	private File attachment;
	
	private String attachmentFile;
	
	private Map<String, Object> variables;
	
	private String emailTemplate;
	
	private String notificationId;
	
	private String notificationType;
	
	private String toRecepients;
	
	private String ccRecepients;
	
	private String bccRecepients;
	
	private String notificationBody;
	
	private String status;
	
	private String errorMessage;
	
	private String createId;
	
	private String createDateTime;
	
	private String updatedBy;
	
	private String updatedDateTime;
	
	private String orderId;
	
	private String fileName;
	
	private String attachmnetInd;
	
	private List<OrderAttachmentDTO> orderAttachmentDTO;
	
	public String[] getRecepients() {
		return recepients;
	}

	public void setRecepients(String[] recepients) {
		this.recepients = recepients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getToRecepients() {
		return toRecepients;
	}

	public void setToRecepients(String toRecepients) {
		this.toRecepients = toRecepients;
	}

	public String getCcRecepients() {
		return ccRecepients;
	}

	public void setCcRecepients(String ccRecepients) {
		this.ccRecepients = ccRecepients;
	}

	public String getBccRecepients() {
		return bccRecepients;
	}

	public void setBccRecepients(String bccRecepients) {
		this.bccRecepients = bccRecepients;
	}

	public String getNotificationBody() {
		return notificationBody;
	}

	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public List<OrderAttachmentDTO> getOrderAttachmentDTO() {
		return orderAttachmentDTO;
	}

	public void setOrderAttachmentDTO(List<OrderAttachmentDTO> orderAttachmentDTO) {
		this.orderAttachmentDTO = orderAttachmentDTO;
	}

	public String getAttachmnetInd() {
		return attachmnetInd;
	}

	public void setAttachmnetInd(String attachmnetInd) {
		this.attachmnetInd = attachmnetInd;
	}
	
}