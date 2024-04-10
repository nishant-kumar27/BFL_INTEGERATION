package com.bfl.batch.jobs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.alerts.EmailTemplateDTO;
import com.bfl.alerts.OrderAttachmentDTO;
import com.bfl.alerts.OrderDetailsDTO;
import com.bfl.alerts.OrderGroupsDTO;
import com.bfl.gencode.merchhier.GroupSummaryDetails.GroupSumPurOrderDetailReport;
import com.bfl.gencode.merchhier.OrderAttachment.OrderAttachment;
import com.bfl.gencode.merchhier.PurchaseOrderDetails.PurchaseOrderDetails;
import com.bfl.gencode.merchhier.emailNotification.EmailNotificationResponse;
import com.bfl.gencode.merchhier.emailNotification.Item;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Component("ProcessOrderAttachmentQueueJob")
public class ProcessOrderAttachmentQueueJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessOrderAttachmentQueueJob.class);

	@Autowired
	IJobConfigService jobconfigService;

	@Autowired
	EmailService emailService;

	@Autowired
	IWebServiceHelper helper;

	@Autowired
	IFoundationDataService foundationDataService;

	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessOrderAttachmentQueueJob job running...");
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
					fromDate = convertDateFormat("2023-05-01T16:00:01.493+05:30");
				}
				
				EmailNotificationResponse response = null;
				
				List<EmailTemplateDTO> emailTemplateDTO = new ArrayList<>();
				
				String token = helper.getAuthTokenForProd();
				//this is used to get the Orders with attachment
				do {
					if(response == null)
						response = getNotificationEmailData(fromDate, toDate, null, token);
					else {
						List<com.bfl.gencode.merchhier.emailNotification.Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links != null && links.size() > 0)
							response = getNotificationEmailData(fromDate, toDate, links.get(0).getHref(), token);
						else
							break;
					}
					//Parse webservice response
					if(null != response && null != response.getItems() && response.getItems().size() > 0)
						emailTemplateDTO.addAll(parseResponse(response));
					
				} while(null != response && response.getHasMore());
				
				//				List<String> attachments = new ArrayList<String>();
				List<InputStreamSource> inputStream = new ArrayList<InputStreamSource>();
				
				for(int i = 0; i < emailTemplateDTO.size(); i++) {
					
					EmailTemplateDTO emailDTO = emailTemplateDTO.get(i);
					
					OrderAttachment orderAttachmentsDetails = null;
					
					List<com.bfl.gencode.merchhier.OrderAttachment.Item> items = new ArrayList<com.bfl.gencode.merchhier.OrderAttachment.Item>();
					
					if("Y".equals(emailDTO.getAttachmnetInd())) {
						orderAttachmentsDetails = getOrderAttachmentsDetails(token, emailDTO.getOrderId());
						if(null != orderAttachmentsDetails && null != orderAttachmentsDetails.getItems() && orderAttachmentsDetails.getItems().size() > 0) {
							items = orderAttachmentsDetails.getItems();
						}
					}
					
					String Draft = "";
					
					if(emailDTO.getSubject().toUpperCase().contains("FYI") && !emailDTO.getSubject().toUpperCase().contains("APPROVED")) {
						Draft = "DRAFT";
					} else if(emailDTO.getSubject().toUpperCase().contains("APPROVE") && !emailDTO.getSubject().toUpperCase().contains("APPROVED")) {
						Draft = "DRAFT";
					} else {
						Draft = "";
					}
					
					List<OrderAttachmentDTO> listOfAttachmentDTOs = new ArrayList<OrderAttachmentDTO>();
					
					ByteArrayOutputStream outputStream = null;
					
					if(null != items && items.size() > 0) {
						for(com.bfl.gencode.merchhier.OrderAttachment.Item item : items) {
							OrderAttachmentDTO orderAttachmentDTO = new OrderAttachmentDTO();
							String attachmentResponse = getOneOrderAttachment(token, item.getDocSeq());
							outputStream = new ByteArrayOutputStream();
							int j = 0;
							while (j < attachmentResponse.length()) {
								outputStream.write(attachmentResponse.charAt(j));
								j++;
							}
							final InputStreamSource attachment = new ByteArrayResource(outputStream.toByteArray());
							
							inputStream.add(attachment);
							
							orderAttachmentDTO.setDOC_FILE(attachment);
							orderAttachmentDTO.setDOC_FILE_NAME(item.getDocFileName());
							orderAttachmentDTO.setDOC_FILE_MEME(item.getDocFileMeme());
							
							listOfAttachmentDTOs.add(orderAttachmentDTO);
						}
						PurchaseOrderDetails purchaseOrders = null;
						List<OrderDetailsDTO> orderDetailsDTOs = new ArrayList<OrderDetailsDTO>();
						
						do {
							if(purchaseOrders == null)
								purchaseOrders = getPurchaseDetailsData(fromDate, toDate, null, token, emailDTO.getOrderId());
							
							else {
								List<com.bfl.gencode.merchhier.emailNotification.Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
								if(links != null && links.size() > 0)
									purchaseOrders = getPurchaseDetailsData(fromDate, toDate, links.get(0).getHref(), token, emailDTO.getOrderId());
								else
									break;
							}
							if(purchaseOrders != null && purchaseOrders.getItems() != null && purchaseOrders.getItems().size() > 0) {
								orderDetailsDTOs.addAll(getOrderDetailsDTO(purchaseOrders, Draft));
							}
						} while(purchaseOrders != null && purchaseOrders.getHasMore());
						
						GroupSumPurOrderDetailReport groupSumDetails = null;
						
//						List<LinkedHashMap<String, String>> groupMap = new ArrayList<LinkedHashMap<String, String>>();
						
						List<OrderGroupsDTO> orderGroupsDTOs = new ArrayList<OrderGroupsDTO>();
						//this is to get the purchase orders group...
						do {
							if(groupSumDetails == null)
								groupSumDetails = getGroupSumPurOrderDetailReport(fromDate, toDate, null, token, emailDTO.getOrderId());
							
							else {
								List<com.bfl.gencode.merchhier.GroupSummaryDetails.Link> links = groupSumDetails.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
								if(links != null && links.size() > 0)
									groupSumDetails = getGroupSumPurOrderDetailReport(fromDate, toDate, links.get(0).getHref(), token, emailDTO.getOrderId());
								else
									break;
							}
							//Parse webservice response
							if(groupSumDetails != null && groupSumDetails.getItems() != null && groupSumDetails.getItems().size() > 0) {
								orderGroupsDTOs.addAll(getOrderGroupsDTOs(groupSumDetails));
							}
						} while(purchaseOrders != null && purchaseOrders.getHasMore());
						
						for(OrderDetailsDTO orderDetailsDTO : orderDetailsDTOs) {
							
//							List<OrderGroupsDTO> groupsData = new ArrayList<OrderGroupsDTO>();
//							int count = 1;
//							for(OrderGroupsDTO gorupsDTO : orderGroupsDTOs) {
//								if(gorupsDTO.getLocation().equals(orderDetailsDTO.getLoc())) {
//									gorupsDTO.setSno(String.valueOf(count));
//									groupsData.add(gorupsDTO);
//									count++;
//								}
//							}
							
							emailService.createPDF(orderDetailsDTO, orderGroupsDTOs, Draft);
							
							ByteArrayOutputStream bo = new ByteArrayOutputStream();
							
							File outfile = new File(System.getProperty("user.dir") + File.separator + "purchaseOrder"  + File.separator + "Purchase_order_1.pdf");
							
				            @SuppressWarnings("resource")
							FileInputStream fis = new FileInputStream(outfile);
				            
				            int c;

				            while ((c = fis.read()) != -1) {
				            	bo.write(c);
				            }
							OrderAttachmentDTO orderAttachmentDTO = new OrderAttachmentDTO();
							if(null != bo) {
								logger.info("Entered into If Condition for generatedPdf.....");
								final InputStreamSource attachment = new ByteArrayResource(bo.toByteArray());
								orderAttachmentDTO.setDOC_FILE(attachment);
								String orderId = emailDTO.getOrderId() == null ? "" : emailDTO.getOrderId();
								String locName = (null != orderDetailsDTO.getLocName() && !orderDetailsDTO.getLocName().isEmpty()) ? "_" + orderDetailsDTO.getLocName() : "";
								orderAttachmentDTO.setDOC_FILE_NAME("Purchase Order Details_" + orderId + locName + ".pdf");
								listOfAttachmentDTOs.add(orderAttachmentDTO);
							}
						}
						
						emailDTO.setOrderAttachmentDTO(listOfAttachmentDTOs);
						emailService.sendNotificationAttachment(emailDTO, null);
						outputStream.close();
						emailDTO.setOrderAttachmentDTO(null);
						emailDTO.setStatus("P");
						emailDTO.setUpdatedDateTime(formatUpdatedDate());
						updateNotificationEmailStatus(emailDTO);
					} else {
						
						OrderAttachmentDTO orderAttachmentDTO = new OrderAttachmentDTO();
						
						PurchaseOrderDetails purchaseOrders = null;
						
						List<OrderDetailsDTO> orderDetailsDTOs = new ArrayList<OrderDetailsDTO>();
						
//						HashMap<String, String> map = new HashMap<String, String>();
						
						do {
							if(purchaseOrders == null)
								purchaseOrders = getPurchaseDetailsData(fromDate, toDate, null, token, emailDTO.getOrderId());

							else {
								List<com.bfl.gencode.merchhier.PurchaseOrderDetails.Link> links = purchaseOrders.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
								if(links != null && links.size() > 0)
									purchaseOrders = getPurchaseDetailsData(fromDate, toDate, links.get(0).getHref(), token, emailDTO.getOrderId());
								else
									break;
							}
							//Parse webservice response
							if(null != purchaseOrders && null != purchaseOrders.getItems() && purchaseOrders.getItems().size() > 0) {
								orderDetailsDTOs.addAll(getOrderDetailsDTO(purchaseOrders, "Draft"));
//								map.putAll(fillMap(purchaseOrders, "DRAFT"));
							}
						} while(null != purchaseOrders && null != purchaseOrders.getHasMore() && purchaseOrders.getHasMore());
						
						GroupSumPurOrderDetailReport groupSumDetails = null;
						
//						List<LinkedHashMap<String, String>> groupMap = new ArrayList<LinkedHashMap<String, String>>();
						
						List<OrderGroupsDTO> orderGroupsDTOs = new ArrayList<OrderGroupsDTO>();
						
						if(null != orderDetailsDTOs && !orderDetailsDTOs.isEmpty() && orderDetailsDTOs.size() > 0) {
							do {
								if(groupSumDetails == null)
									groupSumDetails = getGroupSumPurOrderDetailReport(fromDate, toDate, null, token, emailDTO.getOrderId());

								else {
									List<com.bfl.gencode.merchhier.GroupSummaryDetails.Link> links = groupSumDetails.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
									if(links != null && links.size() > 0)
										groupSumDetails = getGroupSumPurOrderDetailReport(fromDate, toDate, links.get(0).getHref(), token, emailDTO.getOrderId());
									else
										break;
								}
								//Parse webservice response
								if(groupSumDetails != null && groupSumDetails.getItems() != null && groupSumDetails.getItems().size() > 0) {
//									groupMap.addAll(fillGroupMap(groupSumDetails));
									orderGroupsDTOs.addAll(getOrderGroupsDTOs(groupSumDetails));
								}
							} while(null != groupSumDetails && null != groupSumDetails.getHasMore() && groupSumDetails.getHasMore());
						}
//						groupMap.addAll(fillGroupMap(groupSumDetails));
						
//						ByteArrayOutputStream generatedPdf = NewMainReplaceText.main(map, groupMap);
						
						if(null != orderDetailsDTOs && orderDetailsDTOs.size() > 0 && null != orderGroupsDTOs && orderGroupsDTOs.size() > 0) {
							for(OrderDetailsDTO orderDetailsDTO : orderDetailsDTOs) {
								
//								List<OrderGroupsDTO> groupsData = new ArrayList<OrderGroupsDTO>();
								
//								int count = 1;
//								for(OrderGroupsDTO gorupsDTO : orderGroupsDTOs) {
//									if(gorupsDTO.getLocation().equals(orderDetailsDTO.getLoc())) {
//										gorupsDTO.setSno(String.valueOf(count));
//										groupsData.add(gorupsDTO);
//										count++;
//									}
//								}
								
								emailService.createPDF(orderDetailsDTO, orderGroupsDTOs, Draft);
								
//								emailService.createPDF(orderDetailsDTO, orderGroupsDTOs, Draft);
								
								ByteArrayOutputStream bo = new ByteArrayOutputStream();
								
								File outfile = new File(System.getProperty("user.dir") + File.separator + "purchaseOrder"  + File.separator + "Purchase_order_1.pdf");
								
					            @SuppressWarnings("resource")
								FileInputStream fis = new FileInputStream(outfile);
					            
					            int c;
					            
					            while ((c = fis.read()) != -1) {
					            	bo.write(c);
					            }
								
								if(null != bo) {
									final InputStreamSource attachment = new ByteArrayResource(bo.toByteArray());
									orderAttachmentDTO.setDOC_FILE(attachment);
									String orderId = emailDTO.getOrderId() == null ? "" : emailDTO.getOrderId();
									String locName = (null != orderDetailsDTO.getLocName() && !orderDetailsDTO.getLocName().isEmpty()) ? "_" + orderDetailsDTO.getLocName() : "";
									orderAttachmentDTO.setDOC_FILE_NAME("Purchase Order Details_" + orderId + locName + ".pdf");
									listOfAttachmentDTOs.add(orderAttachmentDTO);
									emailDTO.setOrderAttachmentDTO(listOfAttachmentDTOs);
									emailService.sendNotificationAttachment(emailDTO, null);
									emailDTO.setOrderAttachmentDTO(null);
									emailDTO.setStatus("P");
									emailDTO.setUpdatedDateTime(formatUpdatedDate());
									updateNotificationEmailStatus(emailDTO);
									bo.close();
								}
							}
						}
					}
				}
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				}
				logger.info("ProcessOrderAttachmentQueueJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private List<OrderGroupsDTO> getOrderGroupsDTOs(GroupSumPurOrderDetailReport groupSumDetails) {
		List<OrderGroupsDTO> orderGroupsDTOs = new ArrayList<OrderGroupsDTO>();
		if(null != groupSumDetails && null != groupSumDetails.getItems() && groupSumDetails.getItems().size() > 0) {
			int count = 1;
			
			for(com.bfl.gencode.merchhier.GroupSummaryDetails.Item items : groupSumDetails.getItems()) {
				OrderGroupsDTO orderGroupsDTO = new OrderGroupsDTO();
				orderGroupsDTO.setSno(String.valueOf(count));
				orderGroupsDTO.setGroupCode(items.getGroupCode());
				orderGroupsDTO.setGroupName(items.getGroupName());
				orderGroupsDTO.setLoc_name(items.getLocName());
				orderGroupsDTO.setQty(items.getQty() == null ? "0" : String.format("%,.2f", Double.parseDouble(items.getQty())));
				orderGroupsDTO.setUnitCost(items.getUnitCost() == null ? "0" : String.format("%,.2f", Double.parseDouble(items.getUnitCost())));
				orderGroupsDTO.setDiscount(items.getDiscount() == null ? "0" : String.format("%,.2f", Double.parseDouble(items.getDiscount())));
				orderGroupsDTO.setValue(items.getValue() == null ? "0" : String.format("%,.2f", Double.parseDouble(items.getValue())));
				orderGroupsDTO.setVat(items.getVatValue() == null ? "0" : String.format("%,.2f", Double.parseDouble(items.getVatValue())));
				orderGroupsDTO.setBolNo((null != items.getBolNo() && !items.getBolNo().trim().isEmpty()) ? items.getBolNo() : "");
				orderGroupsDTO.setLocation(items.getLocation());
				orderGroupsDTOs.add(orderGroupsDTO);
				count++;
			}
		}
		return orderGroupsDTOs;
	}
	
	
	private EmailNotificationResponse getNotificationEmailData(String startTime, String endTime, String url, String token) throws Exception {
		//		String token = helper.getAuthToken();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getEmailNotificationDetails(request);
	}

	private PurchaseOrderDetails getPurchaseDetailsData(String startTime, String endTime, String url, String token, String OrderId) throws Exception {
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getPurchaseDetailsData(request, OrderId);
	}

	private GroupSumPurOrderDetailReport getGroupSumPurOrderDetailReport(String startTime, String endTime, String url, String token, String OrderId) throws Exception {
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getGroupSumPurOrderDetailReport(request, OrderId);
	}

	private OrderAttachment getOrderAttachmentsDetails(String token, String orderNo) throws Exception {
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(null);
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getOrderAttachment(request, orderNo);
	}

	private String getOneOrderAttachment(String token, int docSeq) throws Exception {
		FoundadtionRequest request = new FoundadtionRequest();
		request.setLimit(1000);
		request.setStatus("N");
		request.setToken(token);
		return helper.getOneOrderAttachment(request, docSeq);
	}

	private void updateNotificationEmailStatus(EmailTemplateDTO emailTemplate) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(null);
		request.setLimit(1000);
		request.setToken(token);
		helper.updateNotificationEmailStatus(request, emailTemplate);
	}

	private List<EmailTemplateDTO> parseResponse(EmailNotificationResponse response) {
		List<EmailTemplateDTO> emailTemplateDTOs = new ArrayList<>();
		if(response.getItems() != null) {
			for(Item emailData : response.getItems()) {
//				if(null != emailData && (emailData.getNotificationId() == 207389 || emailData.getNotificationId() == 207369)) {
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
					emailDTO.setAttachmnetInd(emailData.getAttachmentInd());
					emailTemplateDTOs.add(emailDTO);
				}
//			}
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
	
	public List<OrderDetailsDTO> getOrderDetailsDTO(PurchaseOrderDetails purchaseOrders, String draft) {
		
		List<OrderDetailsDTO> orderDetailsDTOs = new ArrayList<OrderDetailsDTO>();
		
		if(null != purchaseOrders && null != purchaseOrders.getItems() && purchaseOrders.getItems().size() > 0) {
			String orderNo = "";
			int count = purchaseOrders.getItems().size() > 1 ? 1 : 0;
			for(com.bfl.gencode.merchhier.PurchaseOrderDetails.Item items : purchaseOrders.getItems()) {
				if(orderNo.isEmpty()) {
					orderDetailsDTOs.add(getOrders(items, count));
					orderNo = String.valueOf(items.getOrderNo());
				} else if(null != orderNo && !orderNo.isEmpty() && !orderNo.equals(String.valueOf(items.getOrderNo()))) {
					orderDetailsDTOs.add(getOrders(items, count));
					orderNo = String.valueOf(items.getOrderNo());
				}
			}
		}		
		return orderDetailsDTOs;
	}
	
	private OrderDetailsDTO getOrders(com.bfl.gencode.merchhier.PurchaseOrderDetails.Item items, int count) {
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		orderDetailsDTO.setApproveDate(items.getApproveDate() == null ? "" : items.getApproveDate());
		orderDetailsDTO.setCsAddr2(items.getSupAdd2() == null ? "" : items.getSupAdd2());
		if(count == 1) {
			orderDetailsDTO.setLocName("Direct Shops");
			orderDetailsDTO.setAdd1("");
			orderDetailsDTO.setAdd2("");
			orderDetailsDTO.setCity("");
		} else {
			orderDetailsDTO.setAdd1(items.getAdd1() == null ? "" : items.getAdd1());
			orderDetailsDTO.setAdd2(items.getAdd2() == null ? "" : items.getAdd2());
			orderDetailsDTO.setCity(items.getCity() ==  null ? "" : items.getCity());
			orderDetailsDTO.setLocName(items.getLocName() == null ? "" : items.getLocName().toString());
		}
		orderDetailsDTO.setComments(null != items.getComments() && !items.getComments().isEmpty() ? items.getComments() : "");
		orderDetailsDTO.setCsAddr3(null == items.getSupAdd3() ? "" : items.getSupAdd3());
		orderDetailsDTO.setCsBuyerName(items.getBuyerName());
		orderDetailsDTO.setCsSupAdd1(null == items.getSupAdd1() ? "" : items.getSupAdd1());
		orderDetailsDTO.setCsSupCi(null == items.getSupCity() ? "" : items.getSupCity());
		orderDetailsDTO.setCsSupContactName(null == items.getSupContactName() ? "" : items.getSupContactName());
		orderDetailsDTO.setCsSupContactPhone(null == items.getSupContactPhone() ? "" : items.getSupContactPhone());
		orderDetailsDTO.setCsSupName(items.getSupName());
		orderDetailsDTO.setCsSupplier(items.getSupplier().toString());
		orderDetailsDTO.setCsSupPo(null == items.getPost() ? "" : items.getPost());
		orderDetailsDTO.setCsSupSta(null == items.getState() ? "" : items.getState());
		orderDetailsDTO.setDraft((items.getStatus().equals("Approved") || items.getStatus().equals("Closed")) ? "" : "DRAFT");
		orderDetailsDTO.setEmail(null == items.getSupContactEmail() ? "" : items.getSupContactEmail());
		orderDetailsDTO.setFright(null == items.getFreightTerms() ? "" : items.getFreightTerms());
		orderDetailsDTO.setImportCountryId(null == items.getImportCountryId() ? "" : items.getImportCountryId());
		orderDetailsDTO.setInvoiceNo(null == items.getInvoiceNo() ? "" : items.getInvoiceNo());
		orderDetailsDTO.setOrdCurrenyCode((null != items.getSupCurrecyCode() && !items.getSupCurrecyCode().isEmpty()) ? items.getSupCurrecyCode().toString() : "");
		orderDetailsDTO.setOrdDesc(null == items.getOrdDesc() ? "" : items.getOrdDesc());
		orderDetailsDTO.setOrderNo(items.getOrderNo().toString());
		orderDetailsDTO.setPost(null == items.getPost() ? "" : items.getPost().toString());
		orderDetailsDTO.setState(null == items.getState() ? "" : items.getState().toString());
		orderDetailsDTO.setStatus(items.getStatus());
		orderDetailsDTO.setLoc(items.getLocation());
		orderDetailsDTO.setTerms(null == items.getTerms() ? "" : items.getTerms());
		orderDetailsDTO.setTotalQty(null == items.getTotalQty() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalQty())));
		orderDetailsDTO.setTotalVal(null != items.getTotalValue() && items.getTotalValue() > 0 ? String.format("%,.2f", Double.parseDouble(items.getTotalValue().toString())) : "0");
		orderDetailsDTO.setTotalVatVal(null == items.getTotalVatValue() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalVatValue())));
		orderDetailsDTO.setDiscount(null == items.getTotalDiscount() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalDiscount())));
		orderDetailsDTO.setTotalNetVal(null == items.getTotalNetValue() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalNetValue())));
		orderDetailsDTO.setGrandTotal(null == items.getGrandTotalAmount() ? "0" : String.format("%,.2f", Double.parseDouble(items.getGrandTotalAmount())));
		orderDetailsDTO.setEarlistShipDate(items.getEarlistShipDate() == null ? "" : items.getEarlistShipDate());
		orderDetailsDTO.setLatestShipDate(items.getLatestShipDate() == null ? "" : items.getLatestShipDate());
		
		orderDetailsDTO.setTotalSummDiscount(null == items.getTotalDiscount() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalDiscount())));
		orderDetailsDTO.setTotalSummGrandAmt(null == items.getTotalSummGrandAmt() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalSummGrandAmt())));
		orderDetailsDTO.setTotalSummVatVal(null == items.getTotalSummVatVal() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalSummVatVal())));
		orderDetailsDTO.setTotalSummQty(null == items.getTotalSummQty() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalSummQty())));
		orderDetailsDTO.setTotalSummVal(null == items.getTotalSummVal() ? "0" : String.format("%,.2f", Double.parseDouble(items.getTotalSummVal())));
		
		orderDetailsDTO.setUser("");
		return orderDetailsDTO;
	}
}