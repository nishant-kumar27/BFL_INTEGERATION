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

@Component("ProcessWmsShipmentUploadDataJob")
public class ProcessWmsShipmentUploadDataJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWmsShipmentUploadDataJob.class);

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
			logger.info("ProcessWmsShipmentUploadDataJob job running...");
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
				
				ContainerDetailsResponse contResponse = null;
				
				List<String> ContNos = new ArrayList<String>();
				
				Map<String, List<ContainerDetailsDTO>> map = new HashMap<String, List<ContainerDetailsDTO>>();
				int count = 0;
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
								containerDetailsDTO.setOrderNo(items.getOrderNo());
								containerDetailsDTO.setManifestRefStatus(items.getManifestRefStatus());
								list.add(containerDetailsDTO);
								map.put(items.getContainerId(), list);
							} else {
								List<ContainerDetailsDTO> containerDetailsList = new ArrayList<ContainerDetailsDTO>();
								ContainerDetailsDTO containerDetailsDTO = new ContainerDetailsDTO();
								containerDetailsDTO.setcontNo(items.getContainerId());
								containerDetailsDTO.setShipment(items.getShipment());
								containerDetailsDTO.setRefNo(items.getRefNo());
								containerDetailsDTO.setManifestRefStatus(items.getManifestRefStatus());
								containerDetailsDTO.setOrderNo(items.getOrderNo());
								containerDetailsList.add(containerDetailsDTO);
								map.put(items.getContainerId(), containerDetailsList);
							}
							if(!ContNos.contains(items.getContainerId()) && (null == items.getManifestRefStatus() || items.getManifestRefStatus().isEmpty())) {
								ContNos.add(items.getContainerId());
							}
						}
					}
				} while(null != contResponse && contResponse.getHasMore());
				
				UpcBarcodeResponse response = null;
				
				Boolean flag = false;
				
				for(String contNo : ContNos) {
					response = null;
					flag = false;
					ContainerDetailsDTO legacyContNo = null;
					List<ContainerDetailsDTO> listOfContData = null;
					do {
						if(response == null)
							response = getShipmentUploadResponse(mapper, fromDate, toDate, null, contNo);
						else {
							List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
							if(links != null && links.size() > 0)
								response = getShipmentUploadResponse(mapper, fromDate, toDate, links.get(0).getHref(), contNo);
							else
								break;
						}
						if(null != response && null != response.getItems() && response.getItems().size() > 0) {
							count++;
							flag = true;
							listOfContData = map.get(contNo);
							legacyContNo = foundationDataService.getLegacyContainerNo(contNo);
							foundationDataService.persistShipmentUploadData(response, (null != legacyContNo && null != legacyContNo.getLegacy() && !legacyContNo.getLegacy().isEmpty()) ? legacyContNo.getLegacy() : contNo);
						}
					} while(null != response && response.getHasMore());
					
					if(null != listOfContData && listOfContData.size() > 0) {
						foundationDataService.persistPurchaseOrderData(listOfContData);
					}
					
					if(flag) {
						foundationDataService.setUsaContnoMapping(response, contNo, (null != legacyContNo && null != legacyContNo.getLegacy() && !legacyContNo.getLegacy().isEmpty()) ? legacyContNo.getLegacy() : "");
						List<ContainerDetailsDTO> list = map.get(contNo);
						for(ContainerDetailsDTO containerDetailsDTO : list) {
							if(null == containerDetailsDTO.getManifestRefStatus() || containerDetailsDTO.getManifestRefStatus().isEmpty()) {
								updateContainerStatus(contNo, containerDetailsDTO.getOrderNo());
							}
						}
					}
				}
				
//
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setContainerData(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setContainerData(count);
					}
				}
				
				logger.info("ProcessWmsShipmentUploadDataJob ended successfully");

			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void setContainerData(int count) {
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

	private UpcBarcodeResponse getShipmentUploadResponse(ObjectMapper mapper, String startTime, String endTime, String url, String contNo) throws Exception {
		String token = helper.getAuthToken();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("A");
		request.setContNo(contNo);
		request.setToken(token);
		return helper.getShipmentUploadResponse(request);
	}
	
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

	@SuppressWarnings("unused")
	private List<UpcBarcodesDTO> parseResponse(UpcBarcodeResponse response) {
		List<UpcBarcodesDTO> upcBarcodes = new ArrayList<>();
		if(response.getItems() != null) {
			for(Item item : response.getItems()) {
				UpcBarcodesDTO upcBarcodeDto = new UpcBarcodesDTO();
				upcBarcodeDto.setBOLNO((null != item.getBolNo() && !item.getBolNo().isEmpty()) ? item.getBolNo() : item.getContainerId());
				upcBarcodeDto.setClientCost(item.getUnitCost());
				upcBarcodeDto.setColor(item.getColor());
				upcBarcodeDto.setCountryOrigin(item.getOriginCountryId());
				upcBarcodeDto.setDeptName(item.getDeptName());
				upcBarcodeDto.setDHSCost(item.getAvCost());
				upcBarcodeDto.setGENDER(item.getGender());
				upcBarcodeDto.setGroupCode(null != item.getGroupCode() ? item.getGroupCode().trim() : item.getGroupCode());
				upcBarcodeDto.setHSCode(item.getHscode());
				upcBarcodeDto.setItemcode(item.getUpc());
				upcBarcodeDto.setItemName(item.getItemname());
				upcBarcodeDto.setItemType(null != item.getSeason() ? String.valueOf(item.getSeason().charAt(0)) : item.getSeason());
				upcBarcodeDto.setMaterial(item.getProductMaterial());
				upcBarcodeDto.setOrgRetRate(item.getRrp());
				upcBarcodeDto.setRRPCurrency(item.getRrpCurrency());
				upcBarcodeDto.setShortname("");
				upcBarcodeDto.setShortSkirt("");
				upcBarcodeDto.setShortUpdated("");
				upcBarcodeDto.setSize1("");
				upcBarcodeDto.setSizeCheck("");
				upcBarcodeDto.setSizeUpdated("");
				upcBarcodeDto.setStyle(item.getStyle());
				upcBarcodeDto.setSubcategory(item.getSubName());
				upcBarcodeDto.setTYPE1("");
				upcBarcodeDto.setUPC(item.getUpc());
				upcBarcodeDto.setUserID("");//--need to  discuss
				upcBarcodeDto.setVendor(item.getBrandName());
				upcBarcodeDto.setWinterUpdated("");
				upcBarcodes.add(upcBarcodeDto);
			}
		}
		return upcBarcodes;
	}

	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}	

}