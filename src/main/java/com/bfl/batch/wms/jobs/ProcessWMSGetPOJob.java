package com.bfl.batch.wms.jobs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.PurchaseOrdersDTO;
import com.bfl.dto.PurchaseOrdersDetailsDTO;
import com.bfl.gencode.merchhier.PurchaseOrders.Item;
import com.bfl.gencode.merchhier.PurchaseOrders.Link;
import com.bfl.gencode.merchhier.PurchaseOrders.PurchaseOrdersResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSGetPOJob")
public class ProcessWMSGetPOJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSGetPOJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessWMSGetPOJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC);
			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			String lastTimestamp = foundationDataService.getLastProcessingTimestamp(job.getJobId()); 
			try {
				String toDate = convertDateFormat(date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
				String beforeDate = date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				if(!StringUtils.isEmpty(lastTimestamp)) {
					fromDate = convertDateFormat(lastTimestamp);
					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
						@SuppressWarnings("static-access")
						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp).now(ZoneOffset.UTC);
//						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp);
						toDate = convertDateFormat(date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
					}
				} else {
					fromDate = convertDateFormat("2023-05-05T12:00:00.493+05:30");
				}
				PurchaseOrdersResponse response = null;
				List<PurchaseOrdersDTO> purchaseOrdersDTO = new ArrayList<>();
				do {
					if(response == null) {
						response = getPurchaseOrdersData(mapper, fromDate, toDate, null);
					} else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(null != links && links.size() > 0)
							response = getPurchaseOrdersData(mapper,fromDate,toDate,links.get(0).getHref());
						else
							break;
					}
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						purchaseOrdersDTO.addAll(parseResponse(response));
						if(null != purchaseOrdersDTO && purchaseOrdersDTO.size() > 0) {
							foundationDataService.persistPurchaseOrdersData(purchaseOrdersDTO);
						}
					}
				} while(null != response && response.getHasMore());
				
//				if(purchaseOrdersDTO.size() > 0)
//					foundationDataService.persistPurchaseOrdersData(purchaseOrdersDTO);
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				}
				
				logger.info("ProcessWMSGetPOJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS For purchase Orders : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private PurchaseOrdersResponse getPurchaseOrdersData(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		return helper.getPurchaseOrdersData(request);
	}
	
	private List<PurchaseOrdersDTO> parseResponse(PurchaseOrdersResponse response) throws IOException {
		List<PurchaseOrdersDTO> purchaseOrdersDTOs = new ArrayList<>();
		String orderNo = "";
		String groupCode = "";
		double invoiceAmt = 0;
		int count = 0;
		PurchaseOrdersDTO purchaseOrdersDTO = null;
		List<PurchaseOrdersDetailsDTO> list = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String invoiceDate = simpleDateFormat.format(new Date());
		String country = "UAE";
		for(Item item : response.getItems()) {
			if(orderNo.isEmpty()) {
				country = ConfigProperties.getInstance().getConfigValue(item.getImportCountryId());
				purchaseOrdersDTO = getPurchaseOrdersDTO(item, invoiceDate, country);
				list = new ArrayList<PurchaseOrdersDetailsDTO>();
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				invoiceAmt = invoiceAmt + Double.parseDouble(item.getVal());
				groupCode = groupCode + item.getGroupCode() + ",";
				list.add(purchaseOrdersDetailsDTO);
				orderNo = String.valueOf(item.getOrderNo());
				count++;
			} else if(!orderNo.isEmpty() && orderNo.equals(String.valueOf(item.getOrderNo()))) {
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				invoiceAmt = invoiceAmt + Double.parseDouble(item.getVal());
				groupCode = groupCode + item.getGroupCode() + ",";
				list.add(purchaseOrdersDetailsDTO);
				orderNo = String.valueOf(item.getOrderNo());
				count++;
			} else if(!orderNo.isEmpty() && !orderNo.equals(String.valueOf(item.getOrderNo()))) {
				groupCode = groupCode.replaceAll(",$", "");
				purchaseOrdersDTO.setPurchaseOrdersDetailsDTOs(list);
				purchaseOrdersDTO.setGroupCode(groupCode);
				purchaseOrdersDTO.setItemGroups(groupCode);
				purchaseOrdersDTO.setInvAmount(String.valueOf(invoiceAmt));
				purchaseOrdersDTOs.add(purchaseOrdersDTO);
				list = new ArrayList<PurchaseOrdersDetailsDTO>();
				groupCode = item.getGroupCode() + ",";
				invoiceAmt = Double.parseDouble(item.getVal());
				country = ConfigProperties.getInstance().getConfigValue(item.getImportCountryId());
				purchaseOrdersDTO = getPurchaseOrdersDTO(item, invoiceDate, country);
				orderNo = String.valueOf(item.getOrderNo());
				PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = getPurchaseOrdersDetailsDTO(item);
				list.add(purchaseOrdersDetailsDTO);
				count++;
			}
		}
		if(count == response.getItems().size() && list.size() > 0 && null != purchaseOrdersDTO) {
			groupCode = groupCode.replaceAll(",$", "");
			purchaseOrdersDTO.setPurchaseOrdersDetailsDTOs(list);
			purchaseOrdersDTO.setInvAmount(String.valueOf(invoiceAmt));
			purchaseOrdersDTO.setGroupCode(groupCode);
			purchaseOrdersDTO.setItemGroups(groupCode);
			purchaseOrdersDTOs.add(purchaseOrdersDTO);
		}
		return purchaseOrdersDTOs;
	}
	

	
	private PurchaseOrdersDetailsDTO getPurchaseOrdersDetailsDTO(Item item) {
		PurchaseOrdersDetailsDTO purchaseOrdersDetailsDTO = new PurchaseOrdersDetailsDTO();
		purchaseOrdersDetailsDTO.setDiscount(item.getDiscount()!=null && !item.getDiscount().isEmpty()? item.getDiscount() : String.valueOf(0));
		purchaseOrdersDetailsDTO.setDiscountPerc("0");
		purchaseOrdersDetailsDTO.setAmount(item.getVal());
		purchaseOrdersDetailsDTO.setGroupCode(item.getGroupCode());
		purchaseOrdersDetailsDTO.setCatCode(item.getGroupCode());
		purchaseOrdersDetailsDTO.setJobNo(item.getGroupCode() + "-" + item.getOrderNo());
		purchaseOrdersDetailsDTO.setLoadQty("0");
		purchaseOrdersDetailsDTO.setQty(item.getQty());
		purchaseOrdersDetailsDTO.setPalletCnt("0");
		return purchaseOrdersDetailsDTO;
	}

	private PurchaseOrdersDTO getPurchaseOrdersDTO(Item item, String invoiceDate, String country) {
		PurchaseOrdersDTO purchaseOrdersDTO = new PurchaseOrdersDTO(); 
		purchaseOrdersDTO.setActualTT("");
		purchaseOrdersDTO.setOrderNo(String.valueOf(item.getOrderNo()));
		purchaseOrdersDTO.setAirShipment("");
		purchaseOrdersDTO.setApproved_Date(item.getOrigApprovalDate());
		purchaseOrdersDTO.setAssignedTo("");
		purchaseOrdersDTO.setAtaDate(null);
		purchaseOrdersDTO.setBLNo("");
		purchaseOrdersDTO.setCargo_Size("");
		purchaseOrdersDTO.setCatCode("");
		purchaseOrdersDTO.setChqPath("");
		purchaseOrdersDTO.setChqPath2("");
		purchaseOrdersDTO.setChqPath3("");
		purchaseOrdersDTO.setClaim_Period("0");
		purchaseOrdersDTO.setCommAmount("0");
		purchaseOrdersDTO.setCommPerc("0");
		purchaseOrdersDTO.setContNo("");
		purchaseOrdersDTO.setCountry(country);
		purchaseOrdersDTO.setCurrency(item.getCurrencyCode());
		purchaseOrdersDTO.setCust_Decl("");
		purchaseOrdersDTO.setDiscount(null != item.getDiscount() && !item.getDiscount().isEmpty() ? item.getDiscount() : "0");
		purchaseOrdersDTO.setDiscountPerc("0");
		purchaseOrdersDTO.setDueDate(item.getNotAfterDate());
		purchaseOrdersDTO.setEmailDt(null);
		try {
			String createDatetime = converEffectiveDateToDateTime(item.getCreateDatetime());
			String [] createDate = createDatetime.split(" "); 
			purchaseOrdersDTO.setEntryDate(createDate[0]);
		} catch (Exception e) {
			logger.info("Error occured");
		}
		purchaseOrdersDTO.setEtaDate(null);
		purchaseOrdersDTO.setEtdDate(null);
		purchaseOrdersDTO.setExport("");
		purchaseOrdersDTO.setExportDesc("");
		purchaseOrdersDTO.setFF("");
		purchaseOrdersDTO.setFt20("");
		purchaseOrdersDTO.setFt40("");
		purchaseOrdersDTO.setInvoiceDate(invoiceDate);
		purchaseOrdersDTO.setInvoiceNo(item.getVendorOrderNo());
		purchaseOrdersDTO.setInvPath("");
		purchaseOrdersDTO.setJobNo(item.getBolno());
		purchaseOrdersDTO.setLcl("");
		purchaseOrdersDTO.setLoadQty("0");
		purchaseOrdersDTO.setLegacyPONum(item.getComments());
		purchaseOrdersDTO.setNonPayment("");
		purchaseOrdersDTO.setNoofCont("");
		purchaseOrdersDTO.setORAPONo(String.valueOf(item.getOrderNo()));
		purchaseOrdersDTO.setOrder_Changed("");
		purchaseOrdersDTO.setOtherDesc("");
		purchaseOrdersDTO.setOthersPath(item.getBuyerName());
		purchaseOrdersDTO.setPaidAmount("0.0");
		purchaseOrdersDTO.setPaymentTerms(item.getTermsCode());
		purchaseOrdersDTO.setPickupDate(null);
		purchaseOrdersDTO.setPOL("");
		purchaseOrdersDTO.setPOLineStatus("OPEN");
		purchaseOrdersDTO.setProdLeadTime("0");
		purchaseOrdersDTO.setPromisedTT("");
		purchaseOrdersDTO.setReceiptPath("");
		purchaseOrdersDTO.setReceiptPath2("");
		purchaseOrdersDTO.setReceiptPath3("");
		purchaseOrdersDTO.setRemarks(null != item.getComments() && item.getComments().isEmpty() ? item.getComments() : "");
		purchaseOrdersDTO.setSBLC("");
		purchaseOrdersDTO.setShoesCat("");
		purchaseOrdersDTO.setSN(null);
		purchaseOrdersDTO.setStackingFee("0");
		purchaseOrdersDTO.setSuppCode(item.getSupName());
		purchaseOrdersDTO.setTentative_ShipmentDt((null != item.getLatestShipDate() && item.getLatestShipDate().isEmpty()) ? item.getLatestShipDate() : item.getEarliestShipDate());
		purchaseOrdersDTO.setUserid(item.getBuyerName());
		purchaseOrdersDTO.setVatAmt(item.getVatValue());
		purchaseOrdersDTO.setVatJvNo("");
		purchaseOrdersDTO.setVolume("");
		purchaseOrdersDTO.setWinter("");
		return purchaseOrdersDTO;
	}
	
	
//	@SuppressWarnings("unused")
	private String converEffectiveDateToDateTime(String entryDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date d = sdf.parse(entryDate);
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return output.format(d);
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
}
