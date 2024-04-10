package com.bfl.batch.wms.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.dto.ContainerDetailsDTO;
import com.bfl.dto.UpcBarcodesDTO;
import com.bfl.gencode.merchhier.Cont.Response.ContainerDetailsResponse;
import com.bfl.gencode.merchhier.upcbarcodes.response.Item;
import com.bfl.gencode.merchhier.upcbarcodes.response.Link;
import com.bfl.gencode.merchhier.upcbarcodes.response.UpcBarcodeResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWmsUpcBarcodesDataJob")
public class ProcessWmsUpcBarcodesDataJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWmsUpcBarcodesDataJob.class);

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
			logger.info("ProcessUpcBarcodesDataJob job running...");
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
						toDate = convertDateFormat(date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
					}
				} else {
					fromDate = convertDateFormat("2023-05-05T12:00:00.493+05:30");
				}
				//This is to get all the containers..
				ContainerDetailsResponse contResponse = null;
				List<String> ContNos = new ArrayList<String>();
				Map<String, List<ContainerDetailsDTO>> map = new HashMap<String, List<ContainerDetailsDTO>>();
				do {
					if(contResponse == null)
						contResponse = getContainerDetailsResponse(mapper, fromDate, toDate, null);
					else {
						List<com.bfl.gencode.merchhier.Cont.Response.Link> links = contResponse.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links != null && links.size() > 0)
							contResponse = getContainerDetailsResponse(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					if(null != contResponse && null != contResponse.getItems() && contResponse.getItems().size() > 0) {
						for(com.bfl.gencode.merchhier.Cont.Response.Item items : contResponse.getItems()) {
							List<ContainerDetailsDTO> list = map.get(items.getContainerId());
							if(null != list && list.size() > 0) {
								ContainerDetailsDTO containerDetailsDTO = new ContainerDetailsDTO();
								containerDetailsDTO.setcontNo(items.getContainerId());
								containerDetailsDTO.setShipment(items.getShipment());
								containerDetailsDTO.setRefNo(items.getRefNo());
								containerDetailsDTO.setManifestRefStatus(items.getManifestRefStatus());
								containerDetailsDTO.setOrderNo(items.getOrderNo());
								list.add(containerDetailsDTO);
								map.put(items.getContainerId(), list);
							} else {
								List<ContainerDetailsDTO> containerDetailsList = new ArrayList<ContainerDetailsDTO>();
								ContainerDetailsDTO containerDetailsDTO = new ContainerDetailsDTO();
								containerDetailsDTO.setcontNo(items.getContainerId());
								containerDetailsDTO.setShipment(items.getShipment());
								containerDetailsDTO.setManifestRefStatus(items.getManifestRefStatus());
								containerDetailsDTO.setRefNo(items.getRefNo());
								containerDetailsDTO.setOrderNo(items.getOrderNo());
								containerDetailsList.add(containerDetailsDTO);
								map.put(items.getContainerId(), containerDetailsList);
							}
							if(!ContNos.contains(items.getContainerId()) && null != items.getManifestRefStatus() && !items.getManifestRefStatus().isEmpty()) {
								Boolean containerUsaOrgfileDetails = foundationDataService.getContainerUsaOrgfileDetails(items.getContainerId(), "UsaOrgfile");
								if(!containerUsaOrgfileDetails)
									ContNos.add(items.getContainerId());
							}
						}
					}
				} while(null != contResponse && contResponse.getHasMore());
				
				UpcBarcodeResponse response = null;
				int count = 0;
				List<UpcBarcodesDTO> upcBarcodeData = new ArrayList<UpcBarcodesDTO>();
				for(String contNo : ContNos) {
					Boolean flag = false;
					response = null;
					ContainerDetailsDTO legacyContNo = null;
					List<ContainerDetailsDTO> listOfContData = null;
					do {
						if(response == null)
							response = getUpcBarcodesDataResponse(mapper, fromDate, toDate, null, contNo);
						else {
							List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
							if(links != null && links.size() > 0)
								response = getUpcBarcodesDataResponse(mapper, fromDate, toDate, links.get(0).getHref(), contNo);
							else
								break;
						}
						if(null != response && null != response.getItems() && response.getItems().size() > 0) {
							flag = true;
							legacyContNo = foundationDataService.getLegacyContainerNo(contNo);
							listOfContData = map.get(contNo);
							foundationDataService.persistUpcBarcodes(response, upcBarcodeData, (null != legacyContNo && null != legacyContNo.getLegacy()) ? legacyContNo.getLegacy() : "", contNo, listOfContData);
							count++;
						}
					} while(null != response && response.getHasMore());
					
					if(flag) {
						foundationDataService.persistItemAttributes((null != legacyContNo && null != legacyContNo.getLegacy()) ? legacyContNo.getLegacy() : contNo);
					}
					
					if(null != listOfContData && listOfContData.size() > 0) {
						foundationDataService.persistPurchaseOrderData(listOfContData);
					}
					
					if(flag) {
						List<ContainerDetailsDTO> list = map.get(contNo);
						for(ContainerDetailsDTO containerDetailsDTO : list) {
							if(null != containerDetailsDTO.getManifestRefStatus() && !containerDetailsDTO.getManifestRefStatus().isEmpty()) {
								//updateContainerStatus(contNo, containerDetailsDTO.getOrderNo());
							}
						}
					}
				}
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setUpcBarcodes(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setUpcBarcodes(count);
					}
				}
				
				logger.info("ProcessUpcBarcodesDataJob ended successfully");

			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void updateContainerStatus(String contNo, String orderNo) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setUrl(null);
		request.setStatus("A");
		request.setToken(token);
		request.setContNo(contNo);
		ContainerDetailsDTO containerDetailsDTO = new ContainerDetailsDTO();
		containerDetailsDTO.setcontNo(contNo);
		containerDetailsDTO.setOrderNo(orderNo);
		helper.updateContainerStatus(request, containerDetailsDTO);
	}
	
	private void setUpcBarcodes(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = foundationDataService.getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			int counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getContainerId());
			foundationDataService.updateBFLIntegrationData(business_date, "CONTAINER_ID", String.valueOf(counter));
			counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getContainerMapping());
			foundationDataService.updateBFLIntegrationData(business_date, "CONTAINER_MAPPING", String.valueOf(counter));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId(String.valueOf(count));
			bflIntegrationDataDTO.setContainerMapping(String.valueOf(count));
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups("0");
			bflIntegrationDataDTO.setItemReclassify("0");
			bflIntegrationDataDTO.setItems("0");
			bflIntegrationDataDTO.setManifestUpload("0");
			bflIntegrationDataDTO.setOrderCancelled("0");
			bflIntegrationDataDTO.setOrderMod("0");
			bflIntegrationDataDTO.setOrderNew("0");
			bflIntegrationDataDTO.setPriceChanges("0");
			bflIntegrationDataDTO.setPromotions("0");
			bflIntegrationDataDTO.setShipments("0");
			bflIntegrationDataDTO.setSlashing("0");
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			foundationDataService.insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}

//	private void getItemAttributes(ObjectMapper mapper, String fromDate, String toDate, String url, String contNo) throws Exception {
//		UpcBarcodeResponse response = null;
//		List<String> contCreatedInBase = new ArrayList<String>();
//		do {
//			if(response == null)
//				response = getItemAttributesDATADataResponse(mapper, fromDate, toDate, null, contNo);
//			else {
//				List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
//				if(links != null && links.size() > 0)
//					response = getItemAttributesDATADataResponse(mapper, fromDate, toDate, links.get(0).getHref(), contNo);
//				else
//					break;
//			}
//			if(null != response && null != response.getItems() && response.getItems().size() > 0) {
//				ContainerDetailsDTO legacyContNo = foundationDataService.getLegacyContainerNo(contNo);
//				foundationDataService.persistItemAttributes(response, (null != legacyContNo && null != legacyContNo.getLegacy()) ? legacyContNo.getLegacy() : "");
//			} else {
//				contCreatedInBase.add(contNo);
//			}
//		} while(null != response && response.getHasMore());
//	}

	private ContainerDetailsResponse getContainerDetailsResponse(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(100);
		request.setStatus("A");
		request.setToken(token);
		return helper.getContainerDetailsResponse(request);
	}

	private UpcBarcodeResponse getUpcBarcodesDataResponse(ObjectMapper mapper, String startTime, String endTime, String url, String contNo) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(5000);
		request.setStatus("A");
		request.setToken(token);
		request.setContNo(contNo);
		return helper.getUpcBarcodesData(request);
	}
	
	@SuppressWarnings("unused")
	private UpcBarcodeResponse getItemAttributesDATADataResponse(ObjectMapper mapper, String startTime, String endTime, String url, String contNo) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(5000);
		request.setStatus("A");
		request.setToken(token);
		request.setContNo(contNo);
		return helper.getItemAttributesDATADataResponse(request);
	}
	
	@SuppressWarnings("unused")
	private UpcBarcodeResponse getBaseOrders(ObjectMapper mapper, String startTime, String endTime, String url, String contNo) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(2000);
		request.setStatus("A");
		request.setToken(token);
		request.setContNo(contNo);
		return helper.getBaseOrders(request);
	}
	
	@SuppressWarnings("unused")
	private List<UpcBarcodesDTO> parseResponse(UpcBarcodeResponse response, ContainerDetailsDTO legacyContDetails) {
		List<UpcBarcodesDTO> upcBarcodes = new ArrayList<>();
		if(response.getItems() != null) {
			String itemCode = "";
			UpcBarcodesDTO upcBarcodeData = null;
			int count = 1;
			String legacyContainerNo = (null != legacyContDetails && null != legacyContDetails.getLegacy()) ? legacyContDetails.getLegacy() : "";
			for(Item item : response.getItems()) {
				if(null != itemCode && !itemCode.isEmpty() && itemCode.equals(item.getItem()) && response.getItems().size() != count) {
					Double shippedQty = Double.parseDouble(upcBarcodeData.getShippedqty());
					upcBarcodeData = getUpcBarcodeData(item, shippedQty, legacyContainerNo);
					count++;
				} else if(null != itemCode && !itemCode.isEmpty() && itemCode.equals(item.getItem()) && response.getItems().size() == count) {
					Double shippedQty = Double.parseDouble(upcBarcodeData.getShippedqty());
					upcBarcodeData = getUpcBarcodeData(item, shippedQty, legacyContainerNo);
					upcBarcodes.add(upcBarcodeData);
					itemCode = item.getItem();
					count++;
				} else if(itemCode.isEmpty()) {
					itemCode = item.getItem();
					upcBarcodeData = getUpcBarcodeData(item, 0, legacyContainerNo);
				} else {
					upcBarcodes.add(upcBarcodeData);
					upcBarcodeData = getUpcBarcodeData(item, 0, legacyContainerNo);
					count++;
					itemCode = item.getItem();
				}
			}
			if(response.getItems().size() != 1 && count == response.getItems().size() && upcBarcodes.size() != count) {
				upcBarcodes.add(upcBarcodeData);
			}
		}
		return upcBarcodes;
	}
	
	private UpcBarcodesDTO getUpcBarcodeData(Item item, double ShippedQty, String legacyContNo) {
		UpcBarcodesDTO upcBarcodeDto = new UpcBarcodesDTO();
		upcBarcodeDto.setUpload("Y");
		upcBarcodeDto.setRemarks((null != item.getComments() && !item.getComments().isEmpty()) ? item.getComments() : "");
		upcBarcodeDto.setBrand(item.getBrandName());
		upcBarcodeDto.setSeason((null != item.getSeason() && !item.getSeason().isEmpty()) ? String.valueOf(item.getSeason().charAt(0)) : "S");
		upcBarcodeDto.setGenBarcode((null != item.getGenbarcode() && !item.getGenbarcode().isEmpty()) ? item.getGenbarcode() : "");
		upcBarcodeDto.setOrgqty(String.valueOf(Double.parseDouble(item.getShippedqty()) + ShippedQty));
		upcBarcodeDto.setOrgretail(item.getRrp());
		upcBarcodeDto.setTotalretail((Double.parseDouble(item.getShippedqty()) + + ShippedQty) * Double.parseDouble(item.getRrp()));
		upcBarcodeDto.setOrgcost(item.getUnitCost());
		upcBarcodeDto.setTotalorgcost("0");
		upcBarcodeDto.setDivision(item.getDivision());
		upcBarcodeDto.setDhsSalesOrg("0");
		upcBarcodeDto.setdHSSalesAdj((null != item.getSellingprice() && !item.getSellingprice().isEmpty()) ? item.getSellingprice() : "0");
		upcBarcodeDto.setCOSTCURR(item.getCostCurrency());
		upcBarcodeDto.setItemIssued("0");
		upcBarcodeDto.setStatus("R");
		upcBarcodeDto.setInvoiceNo(item.getVendorOrderNo());
		upcBarcodeDto.setOrgRetExcel(item.getUnitRetail());
		upcBarcodeDto.setSalesPriceCurrency(item.getSellingPriceCurrency());
		upcBarcodeDto.setSplitPO("N");
		upcBarcodeDto.setLoadManifestType("NORMAL-" + item.getContainerId());
		upcBarcodeDto.setContNo(null != legacyContNo && !legacyContNo.isEmpty() ? legacyContNo : item.getContainerId());
		upcBarcodeDto.setBOLNO((null != item.getBolNo() && !item.getBolNo().isEmpty()) ? item.getBolNo() : item.getBoxno());
		upcBarcodeDto.setClientCost((null != item.getUnitCost() && !item.getUnitCost().isEmpty()) ? item.getUnitCost() : "0");
		upcBarcodeDto.setColor((null != item.getColor() && !item.getColor().isEmpty()) ? item.getColor() : "");
		upcBarcodeDto.setCountryOrigin(item.getOriginCountryId());
		upcBarcodeDto.setDeptName(item.getDeptName());
		upcBarcodeDto.setDHSCost((null != item.getDhsCost() && !item.getDhsCost().isEmpty()) ? item.getDhsCost() : "0");
		upcBarcodeDto.setGENDER((null != item.getGender() && !item.getGender().isEmpty()) ? item.getGender() : "");
		upcBarcodeDto.setGroupCode(((null != item.getGroupCode() && !item.getGroupCode().isEmpty()) ? item.getGroupCode().trim() : item.getGroupCode()));
		upcBarcodeDto.setHSCode(item.getHscode());
		upcBarcodeDto.setItemcode(item.getItem());
		upcBarcodeDto.setItemName(item.getItemname());
		upcBarcodeDto.setItemType((null != item.getSeason() ? String.valueOf(item.getSeason().charAt(0)) : "S"));
		upcBarcodeDto.setMaterial(item.getProductMaterial());
		upcBarcodeDto.setOrgRetRate(item.getRrp());
		upcBarcodeDto.setRRPCurrency(item.getRrpCurrency());
		upcBarcodeDto.setShortname("");
		upcBarcodeDto.setShortSkirt("");
		upcBarcodeDto.setShortUpdated("");
		upcBarcodeDto.setSize(item.getSiz());
		upcBarcodeDto.setSize1("");
		upcBarcodeDto.setSizeCheck("");
		upcBarcodeDto.setSizeUpdated("");
		upcBarcodeDto.setShippedqty(String.valueOf(Double.parseDouble(item.getShippedqty()) + ShippedQty));
		upcBarcodeDto.setStyle((null != item.getStyle() && !item.getStyle().isEmpty()) ? item.getStyle() : "");
		upcBarcodeDto.setSubcategory(item.getSubName());
		upcBarcodeDto.setTYPE1("");
		upcBarcodeDto.setUPC((null != item.getUpc() && !item.getUpc().trim().isEmpty()) ? item.getUpc() : item.getItem());
		upcBarcodeDto.setUserID("");//--need to  discuss
		upcBarcodeDto.setVendor(item.getBrandName());
		upcBarcodeDto.setWinterUpdated("");
		return upcBarcodeDto;
	}

	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}

}