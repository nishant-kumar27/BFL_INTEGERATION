package com.bfl.batch.jobs;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.alerts.EmailTemplateDTO;
import com.bfl.gencode.merchhier.emailNotification.EmailNotificationResponse;
import com.bfl.gencode.merchhier.emailNotification.Item;
import com.bfl.gencode.merchhier.emailNotification.Link;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessNotificationQueueJob")
public class ProcessNotificationQueueJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessNotificationQueueJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationDataService foundationDataService;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessNotificationQueueJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ZonedDateTime date = ZonedDateTime.now();
			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			String lastTimestamp = foundationDataService.getLastProcessingTimestamp(job.getJobId());
			try {
				String toDate = convertDateFormat(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
				String beforeDate = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				if(!StringUtils.isEmpty(lastTimestamp)) {
					fromDate = convertDateFormat(lastTimestamp);
					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp);
						toDate = convertDateFormat(date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
					}
				} else {
					fromDate = convertDateFormat("2023-01-01T16:00:01.493+05:30");
				}
				EmailNotificationResponse response = null;
//				List<EmailTemplateDTO> emailTemplateDTO = new ArrayList<>();
				do {
					if(response == null)
						response = getNotificationEmailData(mapper, fromDate, toDate, null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links != null && links.size() > 0)
							response = getNotificationEmailData(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					//Parse webservice response
					if(response != null && response.getItems() != null && response.getItems().size() > 0) {
						sendEmailNotifications(response);
					}
				
				} while(response!=null && response.getHasMore());
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				}
				logger.info("ProcessNotificationQueueJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private EmailNotificationResponse getNotificationEmailData(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getEmailNotifications(request);
	}
	
//	@SuppressWarnings("unused")
//	private OrderAttachment getOrderAttachmentData(ObjectMapper mapper, String url) throws Exception {
//		String token = helper.getAuthToken();
//		FoundadtionRequest request = new FoundadtionRequest();
//		request.setUrl(url);
//		request.setLimit(1000);
//		request.setStatus("N");
//		request.setToken(token);
//		return helper.getOrderAttachment(request);
//	}
	
	private void sendEmailNotifications(EmailNotificationResponse response) throws Exception {
		if(response.getItems() != null) {
			List<MimeMessage> emailMessages = new ArrayList<MimeMessage>();
			String notificationId = "";
			int count = 0;
			int emailTemplateSize = 0;
			logger.info("Total mails to be sent :: " + response.getItems().size());
			for(Item emailData : response.getItems()) {
				MimeMessage message = emailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				
				helper.setText(emailData.getNotificationBody(), true);
				helper.setTo(InternetAddress.parse(emailData.getToReceipients()));
				
				if(null != emailData.getCcReceipients() && !emailData.getCcReceipients().isEmpty()) {
					helper.setCc(InternetAddress.parse(emailData.getCcReceipients()));
				}
				
				if(null != emailData.getBccReceipients() && !emailData.getBccReceipients().isEmpty()) {
					helper.setBcc(InternetAddress.parse(emailData.getBccReceipients()));
				}
				
				helper.setSubject(emailData.getSubject());
				helper.setFrom(ConfigProperties.getInstance().getConfigValue("spring.mail.username"));
				
				emailMessages.add(message);
				
				if(count == 0) {
					notificationId = String.valueOf(emailData.getNotificationId());
					count++;
				} else if(count > 0) {
					notificationId = emailData.getNotificationId() + "," + notificationId;
					count++;
				}
				
				emailTemplateSize++;
				
				if(null != emailMessages && emailMessages.size() >= 200) {
					logger.info("Total No of emails to be sent in If block :: " + emailMessages.size());
					MimeMessage [] messages = new MimeMessage [emailMessages.size()];
					emailMessages.toArray(messages);
					emailService.sendBulkNotificationQueueAlerts(messages);
					
					logger.info("emails to be sent ::: " + notificationId);
					
					EmailTemplateDTO emailTemplate = null;
					String[] notifications = notificationId.split(",");
					for(int i = 0; i < notifications.length; i++) {
						emailTemplate = new EmailTemplateDTO();
						emailTemplate.setNotificationId(notifications[i]);
						emailTemplate.setStatus("P");
						emailTemplate.setUpdatedDateTime(formatUpdatedDate());
						updateNotificationEmailStatus(emailTemplate);
					}
					
					notificationId = "";
					count = 1;
					emailMessages = new ArrayList<MimeMessage>();
				} else if(null != emailMessages && emailTemplateSize == response.getItems().size()) {
					logger.info("Total No of emails to be sent in else block :: " + emailMessages.size());
					MimeMessage [] messages = new MimeMessage [emailMessages.size()];
					emailMessages.toArray(messages);
					emailService.sendBulkNotificationQueueAlerts(messages);
					
					logger.info("emails to be sent ::: " + notificationId);
					
					EmailTemplateDTO emailTemplate = null;
					String[] notifications = notificationId.split(",");
					for(int i = 0; i < notifications.length; i++) {
						emailTemplate = new EmailTemplateDTO();
						emailTemplate.setNotificationId(notifications[i]);
						emailTemplate.setStatus("P");
						emailTemplate.setUpdatedDateTime(formatUpdatedDate());
						updateNotificationEmailStatus(emailTemplate);
					}
					
//					EmailTemplateDTO emailTemplate = new EmailTemplateDTO();
//					emailTemplate.setNotificationId(notificationId);
//					emailTemplate.setStatus("P");
//					emailTemplate.setUpdatedDateTime(formatUpdatedDate());
//					updateNotificationEmailStatus(emailTemplate);
					
					notificationId = "";
					count = 1;
					emailTemplateSize = 0;
					emailMessages = new ArrayList<MimeMessage>();
				}
			}
		}
	}
	
	private void updateNotificationEmailStatus(EmailTemplateDTO emailTemplate) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(null);
		request.setLimit(1000);
		request.setStatus("P");
		request.setToken(token);
		helper.updateNotificationEmailStatus(request, emailTemplate);
	}
	
	@SuppressWarnings("unused")
	private List<EmailTemplateDTO> parseResponse(EmailNotificationResponse response) {
		List<EmailTemplateDTO> emailTemplateDTOs = new ArrayList<>();
		if(response.getItems() != null) {
			for(Item emailData : response.getItems()) {
				EmailTemplateDTO emailDTO = new EmailTemplateDTO();
				emailDTO.setBccRecepients(emailData.getBccReceipients());
				emailDTO.setCcRecepients(emailData.getCcReceipients());
				emailDTO.setNotificationBody(emailData.getNotificationBody());
				emailDTO.setNotificationId(String.valueOf(emailData.getNotificationId()));
				emailDTO.setNotificationType(emailData.getNotificationType());
				emailDTO.setStatus(emailData.getStatus());
				emailDTO.setSubject(emailData.getSubject());
				emailDTO.setToRecepients(emailData.getToReceipients());
				emailDTO.setOrderId(emailData.getOrderId());
				emailTemplateDTOs.add(emailDTO);
			}
		}
		return emailTemplateDTOs;
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
	private String formatUpdatedDate() throws ParseException {
		Date d = new Date();
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy hh:mm:ss aa");
		return output.format(d);
	}
}
