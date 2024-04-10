package com.bfl.alerts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bfl.ConfigProperties;
import com.bfl.gencode.merchhier.OrderAttachment.OrderAttachment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EmailService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class); 
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	public void sendEmail(EmailTemplateDTO template) {
		try {
			logger.info("Sending email...");
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			if(template.getAttachment()!=null)
				helper.addAttachment(template.getAttachment().getName(), template.getAttachment());
			
			Context context = new Context();
			for(String key : template.getVariables().keySet()) {
				context.setVariable(key, template.getVariables().get(key));
			}
			
			String process = templateEngine.process("emails/"+template.getEmailTemplate(), context);
			helper.setTo(template.getRecepients());
			helper.setText(process, true);
			helper.setSubject(template.getSubject());
			helper.setFrom(ConfigProperties.getInstance().getConfigValue("spring.mail.username"));
			emailSender.send(message);
			logger.info("Email sent");
		} catch (Exception e) {
			logger.error("Mail could not be sent. Error " +e);
		}
	}

	public void sendJobFailedAlerts(String jobName, String error) {
		EmailTemplateDTO emailDto = new EmailTemplateDTO();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("error", error);
		emailDto.setVariables(vars);
		try {
			String configValue = ConfigProperties.getInstance().getConfigValue("STORE_ID");
			String[] recipients = ConfigProperties.getInstance().getConfigValue("ms.job.failed.alert.recipients").split(",");
			emailDto.setSubject("Alert: Job ["+jobName+"] failed" + " FOR STORE CODE " + configValue);
			emailDto.setEmailTemplate("jobFailedAlertEmailTemplate");
			emailDto.setRecepients(recipients);
			sendEmail(emailDto);
		} catch (IOException e) {
			logger.error("Mail could not be sent. Error " +e);
		}
	}

	public void sendNotificationQueueAlerts(EmailTemplateDTO emailTemplate) throws Exception {
		sendNotificationWithoutAttachment(emailTemplate);
	}
	
	public void sendBulkNotificationQueueAlerts(MimeMessage[] messages) throws Exception {
		sendBulkNotification(messages);
	}
	
	public void sendNotificationAttachment(EmailTemplateDTO emailTemplate, OrderAttachment orderAttachmentData) throws Exception {
		sendNotificationQueue(emailTemplate);
	}
	
	public void sendNotificationWithoutAttachment(EmailTemplateDTO template) throws Exception {
		try {
			logger.info("Sending email...");
			
			List<MimeMessage> emailMessages = new ArrayList<MimeMessage>();
			
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
//			if(template.getAttachment() != null)
//				helper.addAttachment(template.getFileName(), template.getAttachment());
			
			helper.setText(template.getNotificationBody(), true);
			helper.setTo(InternetAddress.parse(template.getToRecepients()));
			
			if(null != template.getCcRecepients() && !template.getCcRecepients().isEmpty()) {
				helper.setCc(InternetAddress.parse(template.getCcRecepients()));
			}
			
			if(null != template.getBccRecepients() && !template.getBccRecepients().isEmpty()) {
				helper.setBcc(InternetAddress.parse(template.getBccRecepients()));
			}
			
			helper.setSubject(template.getSubject());
			helper.setFrom(ConfigProperties.getInstance().getConfigValue("spring.mail.username"));
			
			emailMessages.add(message);
			
			MimeMessage [] messages = new MimeMessage [emailMessages.size()];
			emailMessages.toArray(messages);
			
			emailSender.send(message);
			logger.info("Email sent");
		} catch (Exception e) {
			logger.error("Mail could not be sent. Error " +e);
			throw e;
		}
	}
	
	public void sendBulkNotification(MimeMessage[] messages) throws Exception {
		try {
			logger.info("Sending email...");
			
//			List<MimeMessage> emailMessages = new ArrayList<MimeMessage>();
//			
//			for(EmailTemplateDTO template : messages2) {
//				MimeMessage message = emailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(message,
//						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//				
//				helper.setText(template.getNotificationBody(), true);
//				helper.setTo(InternetAddress.parse(template.getToRecepients()));
//				
//				if(null != template.getCcRecepients() && !template.getCcRecepients().isEmpty()) {
//					helper.setCc(InternetAddress.parse(template.getCcRecepients()));
//				}
//				
//				if(null != template.getBccRecepients() && !template.getBccRecepients().isEmpty()) {
//					helper.setBcc(InternetAddress.parse(template.getBccRecepients()));
//				}
//				
//				helper.setSubject(template.getSubject());
//				helper.setFrom(ConfigProperties.getInstance().getConfigValue("spring.mail.username"));
//				
//				emailMessages.add(message);
//			}
//			
//			MimeMessage [] messages = new MimeMessage [emailMessages.size()];
//			emailMessages.toArray(messages);
			
			emailSender.send(messages);
			
			logger.info("Email sent");
		} catch (Exception e) {
			logger.error("Mail could not be sent. Error " +e);
			throw e;
		}
	}
	
	public void sendNotificationQueue(EmailTemplateDTO template) throws Exception {
		try {
			logger.info("Sending email...");
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			
			if(null != template.getOrderAttachmentDTO() && template.getOrderAttachmentDTO().size() > 0) {
				for(int i = 0; i < template.getOrderAttachmentDTO().size(); i++) {
					helper.addAttachment(template.getOrderAttachmentDTO().get(i).getDOC_FILE_NAME(), template.getOrderAttachmentDTO().get(i).getDOC_FILE());
				}
//				helper.addAttachment("TAX INVOICE.pdf", attachment);
			}
			
//			helper.addAttachment("Test", template.getAttachmentFile());
			
			helper.setText(template.getNotificationBody(), true);
			helper.setTo(InternetAddress.parse(template.getToRecepients()));
			
			if(null != template.getCcRecepients() && !template.getCcRecepients().isEmpty()) {
				helper.setCc(InternetAddress.parse(template.getCcRecepients()));
			}
			
			if(null != template.getBccRecepients() && !template.getBccRecepients().isEmpty()) {
				helper.setBcc(InternetAddress.parse(template.getBccRecepients()));
			}
			
			helper.setSubject(template.getSubject());
			helper.setFrom(ConfigProperties.getInstance().getConfigValue("spring.mail.username"));
			
			emailSender.send(message);
			logger.info("Email sent");
		} catch (Exception e) {
			logger.error("Mail could not be sent. Error " +e);
			throw e;
		}
	}
	
	public void createPDF(OrderDetailsDTO orderDetailsDTO, List<OrderGroupsDTO> orderGroupsDTOs, String DRAFT) {
		try {
			Document document = new Document();
			FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + File.separator + "purchaseOrder"  + File.separator + "Purchase_order_1.pdf");
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			document.open();
			createPDFTable(document, writer, orderDetailsDTO, orderGroupsDTOs, DRAFT);
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createPDFTable(Document document, PdfWriter writer, OrderDetailsDTO orderDetailsDTO, List<OrderGroupsDTO> orderGroupsDTOs, String DRAFT) throws DocumentException, MalformedURLException, IOException {
		
		float[] colsWid = {1f, 1f, 1f};
		
		PdfPTable header = new PdfPTable(colsWid);
		header.setWidthPercentage(100);
		header.getDefaultCell().setBorder(0);
		header.getDefaultCell().setHorizontalAlignment(0);
		Image logo = Image.getInstance(System.getProperty("user.dir") + File.separator + "Images"  + File.separator + "bfl_1.png");
		logo.setWidthPercentage(10);
		Image logo1 = Image.getInstance(System.getProperty("user.dir") + File.separator + "Images"  + File.separator + "bfl_2.png");
		logo1.setWidthPercentage(10);
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(0);
		paragraph.add(new Chunk(logo, 0, 0, true));
		header.addCell(paragraph);
		header.addCell("");
		paragraph = new Paragraph();
		paragraph.setAlignment(0);
		paragraph.add(new Chunk(logo1, 0, 0, true));
		
		header.addCell(paragraph);
		
		document.add(header);
		
		float[] colsWidth1 = {1f, 1f, 1f, 1f};
		
		float[] headerWidth = {1f, 1f, 1f};
		
		Font f = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		
//		for(OrderDetailsDTO orderDetailsDTO : orderDetailsDTOs) {
			
			PdfPTable table = new PdfPTable(headerWidth);
			
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);//Code 3
			table.addCell("");
			table.addCell(new Paragraph("PURCHASE ORDER " +  DRAFT, f));
			table.addCell("");
			document.add(table);
			
			document.add(Chunk.NEWLINE);
			
			document.add(new Paragraph(""));
			
			f = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
			
			table = new PdfPTable(colsWidth1);
			
			table.getDefaultCell().setBorder(0);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("VAT Registration No", f));
			table.addCell(new Phrase("100315930600003", FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Run Date ", f));
			table.addCell(new Phrase(currentDate(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Supplier No", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsSupplier(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Purchase Order No. ", f));
			table.addCell(new Phrase(orderDetailsDTO.getOrderNo(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("", f));
			table.addCell(new Phrase("", FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("", f));
			table.addCell(new Phrase(orderDetailsDTO.getComments(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Supplier Name", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsSupName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Order Status", f));
			table.addCell(new Phrase(orderDetailsDTO.getStatus(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Supplier TRN No", f));
			table.addCell("");
			table.addCell(new Phrase("Purchase Order Approval Date", f));
			table.addCell(new Phrase(orderDetailsDTO.getApproveDate(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Supplier Add", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsSupAdd1() + " " + orderDetailsDTO.getCsAddr2(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Purchaser Name", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsBuyerName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getCsAddr3() + " " + orderDetailsDTO.getCsSupCi(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Invoice Number", f));
			table.addCell(new Phrase(orderDetailsDTO.getInvoiceNo(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getCsSupSta() + " " + orderDetailsDTO.getCsSupPo(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Deliver To", f));
			table.addCell(new Phrase(orderDetailsDTO.getLocName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Contact Name", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsSupContactName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getAdd1(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getAdd2(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Email Address ", f));
			table.addCell(new Phrase(orderDetailsDTO.getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getCity(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Phone No", f));
			table.addCell(new Phrase(orderDetailsDTO.getCsSupContactPhone(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getState(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Alt. Phone No", f));
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase(orderDetailsDTO.getPost(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Mobile", f));
			table.addCell(" ");
			table.addCell(new Phrase("Delivery Country", f));
			table.addCell(new Phrase(orderDetailsDTO.getImportCountryId(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Reference", f));
			table.addCell(new Phrase(orderDetailsDTO.getOrdDesc(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Currency", f));
			table.addCell(new Phrase(orderDetailsDTO.getOrdCurrenyCode(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
//			document.add(Chunk.NEWLINE);
//			document.add(Chunk.NEWLINE);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Payment Terms", f));
			table.addCell(new Phrase(orderDetailsDTO.getTerms(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Total ", f));
//			--Total SUM VALUE 
			table.addCell(new Phrase(orderDetailsDTO.getTotalSummGrandAmt(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100); 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase("Freight Terms", f));
			table.addCell(new Phrase(orderDetailsDTO.getFright(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			table.addCell(new Phrase("Ship Start Date", f));
			table.addCell(new Phrase(orderDetailsDTO.getEarlistShipDate(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
			
			table = new PdfPTable(colsWidth1);
			table.getDefaultCell().setBorder(0);
			table.setWidthPercentage(100);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(new Phrase(""));
			table.addCell(new Phrase(""));
			table.addCell(new Phrase("Ship Stop Date", f));
			table.addCell(new Phrase(orderDetailsDTO.getLatestShipDate(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			document.add(table);
			
//			document.add(new Paragraph(""));
//		}
		
		float[] colsWidth = {0.5f, 1.5f, 0.5f, 0.5f, 2f, 0.5f, 1f, 0.5f, 0.5f, 0.5f};
		
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		
		PdfPTable table1 = new PdfPTable(1);
		table1.getDefaultCell().setBorder(0);
		table1.setWidthPercentage(100); // Code 2
		table1.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		table1.addCell(new Phrase("Description", f));
		document.add(table1);
		
		table1 = new PdfPTable(colsWidth);
		
		table1.setWidthPercentage(100); // Code 2
		
		table1.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		PdfPCell cell1 = new PdfPCell(new Phrase("S NO.", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("LOCATION", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("GROUP CODE", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("BOL NO", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("GROUP NAME", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
		
        cell1 = new PdfPCell(new Phrase("QTY", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("UNIT COST", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("VALUE", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("DISC.", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
        
        cell1 = new PdfPCell(new Phrase("VAT", boldFont));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(cell1);
		
        if(null != orderGroupsDTOs && !orderGroupsDTOs.isEmpty() && orderGroupsDTOs.size() > 0) {
        	for(OrderGroupsDTO orderGroupsDTO : orderGroupsDTOs) {
    			PdfPCell cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getSno(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getLoc_name(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getGroupCode(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getBolNo(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getGroupName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getQty(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getUnitCost(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getValue(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getDiscount(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    			
    			cell2 = new PdfPCell(new Phrase(orderGroupsDTO.getVat(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
    	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    			table1.addCell(cell2);
    		}
        }
        
		document.add(table1);
		
		document.add(Chunk.NEWLINE);
		
		float[] colsWidth2 = {1f, 1f, 1f, 1f};
		
		boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		
		PdfPTable table2 = new PdfPTable(colsWidth2);
		
		table2.setWidthPercentage(100); // Code 2
		
		table2.setHorizontalAlignment(Element.ALIGN_RIGHT);//Code 3
		
		Font fo = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
		
//		for(OrderDetailsDTO orderDetailsDTO : orderDetailsDTOs) {
			
			PdfPCell tableCell = new PdfPCell();
			
			tableCell.setBorder(0);
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			
			table2.addCell(new Phrase("Total Quantity", fo));
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummQty(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			
//			table2.addCell(new Phrase("Order Total (CC)", fo));
			table2.addCell(new Phrase("Order Total ", fo));
//			total Summ val
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummVal(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			
//			table2.addCell(new Phrase("Discount (CC)", fo));
			table2.addCell(new Phrase("Discount ", fo));
			
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummDiscount(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			table2.addCell(new Phrase("Total Net Amount", fo));
			
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummVal(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			table2.addCell(new Phrase("Total VAT Amount", fo));
			
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummVatVal(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			table2.addCell(tableCell);
			table2.addCell(tableCell);
			table2.addCell(new Phrase("Grand Total Amount", fo));
			
			table2.addCell(new Phrase(orderDetailsDTO.getTotalSummGrandAmt(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
//		}
		
		document.add(table2);
		
		document.add(Chunk.NEWLINE);
		
		String str = new String("All invoices must reference the Purchase order against which they are raised, invoices not "
				+ "referencing a purchase order will not be accepted and will have to follow internal compliance "
				+ "check which may negate or delay payment.");
		
		float[] colsWidth10 = {1f};
		
		PdfPTable table8 = new PdfPTable(colsWidth10);
		
		table8.setWidthPercentage(100); // Code 2
		
		table8.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		table8.addCell(new Phrase(str, FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		document.add(table8);
		
		float[] message = {1f};
		
		table2 = new PdfPTable(message);
		
		table2.getDefaultCell().setBorder(0);
		
		table2.setWidthPercentage(100); // Code 2
		
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		Font font = new Font(Font.FontFamily.HELVETICA, 8);
		
		Font bold = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		
		Paragraph p1 = new Paragraph("This is an electronically generated document and does not require signature.", bold);
		
		table2.addCell(p1);
		
		document.add(table2);
		
		table2 = new PdfPTable(message);
		
		table2.getDefaultCell().setBorder(0);
		
		table2.setWidthPercentage(100); // Code 2
		
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		p1 = new Paragraph("Please see terms and conditions overleaf.", bold);
		
		table2.addCell(p1);
		
		document.add(table2);
		
		float[] colsWidth3 = {1f, 1f};
		
		table2 = new PdfPTable(colsWidth3);
		table2.getDefaultCell().setBorder(0);
		table2.setWidthPercentage(100); // Code 2
		table2.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		font = new Font(Font.FontFamily.HELVETICA, 8);
		
		String parah = new String("Standard Purchase Order Terms and Conditions \r\n "
								+ " 1. Definitions a. The \"Client\" shall mean BFL Group, Brands For Less\r\n"
								+ "LLC and its group companies.\r\n"
								+ "b. The \"Supplier\" means to whom the order is issued.\r\n"
								+ "It also includes s products supplier, service provider, contractor &\r\n"
								+ "subcontractor.\r\n"
								+ "c. The \"Goods\" or \"Services\" shall means the goods\r\n"
								+ "or services specified in this order and all parts/components of\r\n"
								+ "them and/or all work to be done \\r\\n"
								+ "2. The Goods and/or Services"
								+ "a. The Goods and Services described in this Purchase Order are to be provided by\r\n"
								+ "Supplier subject to the following terms and conditions. Supplier agrees to be bound\r\n"
								+ "by and to comply with all such conditions upon receipt of the purchase\r\n"
								+ "order in original or copy transmitted electronically through E-Mail, Fax, etc., or by\r\n"
								+ "hand or courier, unless and otherwise there is a concern expressed by the supplier in\r\n"
								+ "writing.\r\n"
								+ "3. Purchase price and terms of payment\r\n"
								+ "a. All payments shall be done in UAE dirhams or as otherwise\r\n"
								+ "specified after reversing the receivables to the client from the supplier. "
								+ "All prices shown in this PO are firm and are not subject to adjustment."
								+ "Invoice\r\n"
								+ "shall be submitted not later than 15 days from the date of goods\r\n"
								+ "delivered or service rendered or advance paid, whichever is\r\n"
								+ "earlier. Client shall not be liable in case of delay in submission\r\n"
								+ "of invoices beyond such period.\r\n"
								+ "b. All payments shall be done within 60 days from the\r\n"
								+ "date of invoice receipt or from acceptance of goods or services\r\n"
								+ "by the Client, unless otherwise agreed upon both parties.\r\n"
								+ "4. Method of delivery or packing\r\n"
								+ "a. Goods shall be packaged in a manner which assures that they are\r\n"
								+ "protected against deterioration and contamination duly in\r\n"
								+ "compliance with legal requirements. All Goods are delivered to the\r\n"
								+ "buyer's designated location as specified in the PO. Title and risks\r\n"
								+ "remain with Supplier until delivery duly acknowledged.\r\n"
								+ "5. Inspection and rejection\r\n"
								+ "a. The Goods and Services are subject to inspection and test by Client\r\n"
								+ "at any time and place. If the Goods and Services furnished are found to\r\n"
								+ "be defective, Client may reject them, or require Supplier to correct or\r\n"
								+ "replace them without charge, or require a reduction in price which is\r\n"
								+ "equitable under the circumstances. If Supplier is unable or refuses to\r\n"
								+ "correct or replace such items within a time deemed reasonable\r\n"
								+ "by Client, Client may terminate this PO in whole or in part. Supplier\r\n"
								+ "bears all risks as to rejected goods and services. Supplier reimburses\r\n"
								+ "Client for all transportation costs, other related costs incurred and\r\n"
								+ "overpayments in respect of the neglected goods and services.\r\n"
								+ "6. Changes "
								+ "a. Client may make changes to this PO including place of "
								+ "delivery, by giving notice to Supplier. If such changes affect the "
								+ "cost of or the time required for performance of this PO, an "
								+ "equitable adjustment in the price or date of delivery or both will be "
								+ "made. No change by Supplier is allowed without the Client written "
								+ "approval. "
								+ "b. Any claim of Supplier for an adjustment such"
								+ "circumstances must be made in writing within thirty 30 days from "
								+ "the date of receipt by Supplier of notification of such change. "
								+ "7. Warranty "
								+ "a. Supplier warrants to Client that for a period of two years (unless "
								+ "otherwise agreed in writing & d on Purchase order) commencing on "
								+ "the Delivery Date, all Goods, Services or Goods furnished in "
								+ "connection with Services will: (a) be new and free from any defects "
								+ "in workmanship, material and design; (b) conform to applicable "
								+ "specifications; (c) be fit for their intended purpose and operate as "
								+ "intended; (d) be free and clear of all liens, security interests or "
								+ "other encumbrances; and (e) not infringe or misappropriate any "
								+ "third party's intellectual property rights. These warranties survive "
								+ "any delivery, inspection, acceptance or payment. "
								+ "8. Termination "
								+ "a. This PO may be terminated or suspended by the Client in whole or "
								+ "in part should the supplier delay the delivery of the Services/Goods "
								+ "other than agreed. The Client then may deliver to the Supplier a "
								+ "written notice specifying the extent to which performance and/or "
								+ "the deliveries of Goods and Services under this PO is terminated "
								+ "and/or suspended and the date upon which such action shall become "
								+ "effective. In the event of such action, the Client shall pay Supplier "
								+ "for the Goods and Services satisfactorily provided to the "
								+ "effective date of termination or suspension. In this case, Supplier "
								+ "may submit a proposal subject to the Client approval for "
								+ "equitable increase in the prices to account for costs of demobilization "
								+ "and direct termination expenses. The termination of this PO shall "
								+ "discharge any further obligations of either party."
								+ "9. Indemnification\r\n"
								+ "a. Except for damages caused by the negligence of Client, Supplier shall\r\n"
								+ "defend, indemnify and hold the Client harmless from all claims, actions, demands,\r\n"
								+ "loss and cases of action arising from injury, including death, to any person, or damage\r\n"
								+ "to any property, when such injury or damage results in whole or in part from\r\n"
								+ "the acts or omissions of Supplier\r\n");
		
		Paragraph p = new Paragraph(parah, font);
		
		table2.addCell(p);
		
		parah = new String("10. Assignment\r\n"
		+ "a. This PO is assignable by Client. This PO may not be assigned by Supplier without\r\n"
		+ "the Client prior written approval. In such consent is given, Supplier remains liable as\r\n"
		+ "if no such transfer has been made.\r\n"
		+ "11. Default\r\n"
		+ "A party is in default of its obligations under this PO if any of the following events\r\n"
		+ "occur, namely:\r\n"
		+ "a. Such party is adjudged bankrupt or insolvent by a court of competent\r\n"
		+ "jurisdiction, or otherwise becomes insolvent, as evidenced by its inability to\r\n"
		+ "pay its debts generally as and when they become due; or\r\n"
		+ "b. Such party is in default of its obligations hereunder and fails to cure\r\n"
		+ "such default within thirty days of written notice from the other party, or if such\r\n"
		+ "default cannot be cured within thirty days, within such longer period as may be\r\n"
		+ "reasonable, provided the defaulting party commences promptly and diligently\r\n"
		+ "proceeds with curing the default.\r\n"
		+ "c. Upon the occurrence of any of the above events, the party not in\r\n"
		+ "default may, by written notice to the defaulting party, terminate this PO\r\n"
		+ "without prejudice to any other right or remedy available to it at law and without\r\n"
		+ "liability for such termination. Neither the Supplier nor Client shall be liable to the\r\n"
		+ "other indirect damages, for loss of profit or for damages arising from loss of use or\r\n"
		+ "production.\r\n"
		+ "12. Confidentiality\r\n"
		+ "a. In the performance of the services, the Supplier and its subcontractors, if any, may\r\n"
		+ "have access to confidential information (hereinafter referred to as the \"Confidential\r\n"
		+ "Information\") which we must protect from disclosure pursuant to the Act respecting\r\n"
		+ "access to documents held by Public Bodies and the protection of\r\n"
		+ "personal information. The supplier undertakes to hold all of the Confidential\r\n"
		+ "Information it receives in strict confidence and neither to disclose or release in any\r\n"
		+ "manner such Confidential Information to any third party nor to use such Confidential\r\n"
		+ "Information for any other purpose that the one for which Client has disclosed same; to\r\n"
		+ "disclose Confidential Information for the said purpose The supplier warrants\r\n"
		+ "that such employees or subcontractors are obligated to and will hold Confidential\r\n"
		+ "Information only to those of its employees or agents who need to know such\r\n"
		+ "Information in strict confidence and to take all reasonable measures to ensure\r\n"
		+ "the confidentiality is respected. The Supplier shall indemnify and hold harmless\r\n"
		+ "Client, its officers, directors and employees from against any and all liabilities,\r\n"
		+ "claims, suits, demands, disputes, recourses, damages and expenses including,\r\n"
		+ "without limitation, any recourses including reasonable legal fees arising from any\r\n"
		+ "and all claims in respect of, or resulting from, the use or the disclosure of\r\n"
		+ "Confidential Information by the Supplier, its employees or subcontractors\r\n"
		+ "13. Force Majeure\r\n"
		+ "a. Supplier shall not be liable for default or delay due to causes beyond Supplier's\r\n"
		+ "reasonable control and without fault or negligence on the part of Supplier. The\r\n"
		+ "Supplier gives us prompt notice in writing when any such cause appears likely\r\n"
		+ "to delay deliveries and/or performances of services and takes appropriate action\r\n"
		+ "to avoid or minimize such delay. If any such default or delay threatens to\r\n"
		+ "impair Supplier's ability to meet delivery requirements for its material, supplies\r\n"
		+ "and services, we shall have the right, without any liability to Supplier, to\r\n"
		+ "cancel the portion or portions of this PO so affected. We shall not be liable for\r\n"
		+ "default or delay in the performance of its obligations due to cause beyond its\r\n"
		+ "reasonable control. 14. Governing Law\r\n"
		+ "a. The PO shall be governed by the local laws of UAE. Any dispute which may arise\r\n"
		+ "in relation to this PO shall be assigned to Dubai courts.");
		
		p = new Paragraph(parah, font);
		
		table2.addCell(p);
		
		document.add(table2);
		
		p = new Paragraph("15. Tax/VAT Terms \n"
				+ "a. Please refer the binding tax terms given as Annexure – 1 herewith", font);
		
		document.add(p);
		
		Font parahFont = new Font(FontFamily.HELVETICA, 8, Font.UNDERLINE, BaseColor.BLACK);
		
		//Font parahFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, Font.UNDERLINE);
		
		p = new Paragraph("Annexure 1 for Clause 10: Tax/VAT Terms:", parahFont);
		
		p.setAlignment(0);
		
		//p.setFont(boldFont);
		
		document.add(p);
		
		float[] colsWidth4 = {1f};
		
		PdfPTable table3 = new PdfPTable(colsWidth4);
		table3.getDefaultCell().setBorder(0);
		table3.setWidthPercentage(100); // Code 2
		table3.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		p = new Paragraph("1 The unit prices mentioned in order are excluding VAT or any other taxes.\r\n"
		+ "2 VAT/Taxes mentioned in purchase order are indicative. VAT may not be applicable in your supply. Please consider the applicability of\r\n"
		+ "VAT/Taxes on concerned supply as per current tax laws and apply accordingly in your Invoice.\r\n"
		+ "3 Suppliers not exceeding the UAE VAT Registration Threshold shall not charge VAT on their sales.\r\n"
		+ "4 In case, VAT is not charged by UAE Supplier then we as recipients of supply will not be held responsible for any tax obligations.\r\n"
		+ "5 In case of Tax Invoice, all the details as required under VAT Law should be mentioned (Please refer Checklist enclosed)\r\n"
		+ "6 Commercial invoices will not be accepted for VAT and payment purpose.\r\n"
		+ "7 We reserve a right to withhold payment in case supplier does not issue a valid Tax Invoice as per given checklist.\r\n"
		+ "8 Tax Invoice should be delivered to us within 15 calendar days from the date of supply/date of Invoice. After 20 days, Tax invoice will not be ac\r\n"
		+ "9 Payment will be made only after the receipt of valid Tax invoice as per given checklist and related documents. Any consequences like\r\n"
		+ "nonrecovery of Input tax, any penalty on account of not receiving a valid tax invoice shall be supplier's responsibility.\r\n"
		+ "10 In case Tax Authorities do not treat the tax invoices submitted by your client as valid tax invoices, we reserve the right to recover any loss of\r\n"
		+ "taxes and penalty paid to authorities in cash by adjusting the said amounts against subsequent payments.\r\n"
		+ "11 At the time of first order, Supplier must provide the copy of its VAT registration certificate or Tax registration number. If at any time the\r\n"
		+ "Supplier ceases to be registered for VAT, they must notify us immediately.\r\n"
		+ "If we have paid VAT to your client/Supplier but due to any non-compliance by the Supplier (including, by reason of any error or omission,\r\n"
		+ "failure to register for VAT, or\r\n"
		+ "12 a defective VAT invoice) VAT is not recoverable by us in full as input tax, the Supplier will indemnify and hold harmless us on demand for the\r\n"
		+ "resulting irrecoverable input VAT, and any costs, namely but not limited to expenses, fines and penalties.", font);
		
		table3.addCell(p);
		
		document.add(table3);
		
		//PdfPTable table4 = new PdfPTable(colsWidth4);
		//table4.getDefaultCell().setBorder(0);
		//table4.setWidthPercentage(100); // Code 2
		//table4.setHorizontalAlignment(Element.ALIGN_CENTER);//Code 3
		
		p = new Paragraph("Checklist for Tax Invoice", parahFont);
		
		p.setAlignment(1);
		
		//table4.addCell(p);
		
		document.add(p);
		
		PdfPTable table5 = new PdfPTable(colsWidth4);
		table5.getDefaultCell().setBorder(0);
		table5.setWidthPercentage(100); // Code 2
		table5.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		table5.addCell(new Phrase("Annexure – 2: Mandatory Particulars for 'Full' Tax Invoice", FontFactory.getFont(FontFactory.HELVETICA, 8)));
		
		document.add(table5);
		
		float[] colsWidth5 = {0.05f, 1f};
		
		PdfPTable table6 = new PdfPTable(colsWidth5);
		
		table6.setWidthPercentage(100); // Code 2
		
		table6.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		Font tableHeader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		
		table6.addCell(new Phrase("Sr No.", tableHeader));
		
		p = new Paragraph("Mandatory Particulars\r\n"
		+ "[In order for a full tax invoice to be valid it must contain the following particulars]", tableHeader);
		
		p.setAlignment(1);
		
		p.setFont(boldFont);
		
		table6.addCell(p);
		
		table6.addCell(new Phrase("1", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The words \"Tax Invoice\" clearly displayed on the invoice", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("2", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The name, address, and TRN of your company (i.e., Vendor's Company)", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("3", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The name, address, and TRN of client as below:", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("4", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("A sequential tax invoice number or a unique invoice number", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("5", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The date of issuing the tax invoice", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("6", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The date of supply (where different from date of issue of the tax invoice)", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("7", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("A description of the goods or services supplied", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("8", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("For each good or service, the unit price, the quantity or volume supplied, the Rate of VAT and the amount payable expressed in AED", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("9", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The amount of any discount offered", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("10", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The gross amount payable expressed in AED", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("11", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("The tax amount payable expressed in AED together with the rate of exchange applied", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("12", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table6.addCell(new Phrase("where the invoice relates to a supply under which the recipient is required to account for VAT, a ment that the recipient is required to\r\n"
			+ "account for VAT, and a reference to the relevant provision of the Law", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		document.add(table6);
		
		document.add(Chunk.NEWLINE);
		
		document.add(Chunk.NEWLINE);
		
		p = new Paragraph("*(Simplified Tax Invoice is allowed only if total value of the invoice including VAT is up to AED 10,000/-)", bold);
		
		p.setAlignment(0);
		
		document.add(p);
		
		p = new Paragraph("Annexure – 3: Mandatory Particulars for 'simplified' Tax Invoice*", bold);
		
		p.setAlignment(0);
		
		p.setFont(boldFont);
		
		document.add(p);
		
		document.add(Chunk.NEWLINE);
		
		PdfPTable table7 = new PdfPTable(colsWidth5);
		
		table7.setWidthPercentage(100); // Code 2
		
		table7.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
		
		table7.addCell(new Phrase("Sr No.", tableHeader));
		
		p = new Paragraph("Mandatory Particulars\r\n"
		+ "[In order for a simplified tax invoice to be valid it must contain the following particulars]", tableHeader);
		
		p.setFont(boldFont);
		
		table7.addCell(p);
		
		table7.addCell(new Phrase("1", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("The words \"Tax Invoice\" clearly displayed on the invoice", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("2", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("The name, address, and TRN of your company (i.e., Vendor's Company)", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("3", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("The date of issuing the tax invoice", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("4", FontFactory.getFont(FontFactory.HELVETICA, 10)));	
		
		table7.addCell(new Phrase("A description of the goods or services supplied", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("5", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		table7.addCell(new Phrase("The total consideration and the VAT amount charged.", FontFactory.getFont(FontFactory.HELVETICA, 10)));
		
		document.add(table7);
		
		document.add(Chunk.NEWLINE);
		
		document.add(Chunk.NEWLINE);
	}
	
	private String currentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentDate = new Date();
		return simpleDateFormat.format(currentDate);
	}
	
}