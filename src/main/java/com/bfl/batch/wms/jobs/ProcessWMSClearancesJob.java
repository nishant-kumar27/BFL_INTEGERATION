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
import com.bfl.dto.ClearancesDTO;
import com.bfl.dto.StoreConfigDTO;
import com.bfl.gencode.merchhier.PriceChange.Item;
import com.bfl.gencode.merchhier.PriceChange.Link;
import com.bfl.gencode.merchhier.PriceChange.PriceChangeResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSClearancesJob")
public class ProcessWMSClearancesJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSClearancesJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationWMSDataService foundationDataService;
	
//	Map<String, StoreConfigDTO> map = new HashMap<String, StoreConfigDTO>();
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessWMSClearancesJob job running...");
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
					fromDate = convertDateFormat("2023-10-01T10:00:01.493+05:30");
				}
//				ClearancesResponse response = null;
				PriceChangeResponse response = null;
				int count = 0;
//				List<ClearancesDTO> clearances = new ArrayList<>();
				do {
					if(response == null) {
						response = getClearancesResponse(mapper, fromDate, toDate, null);
					} else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links!=null && links.size() > 0)
							response = getClearancesResponse(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
				
				if(response != null && response.getItems() != null && response.getItems().size() > 0) {

					Map<String, List<ClearancesDTO>> pricingData = parseResponse(response);
					if(null != pricingData && pricingData.size() > 0) {
						count = count + response.getItems().size();
//						foundationDataService.persistPriceChangeData(pricingData);
//						pricingData = new ArrayList<>();
						foundationDataService.persistSlashingforAllCostCenters(pricingData);
						pricingData = new HashMap<String, List<ClearancesDTO>>();
					}
				}
			} while(response != null && response.getHasMore());
				
//				if(clearances.size() > 0)
//					foundationDataService.persistClearancesData(clearances);
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setSlashingData(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setSlashingData(count);
					}
				}

				logger.info("ProcessWMSClearancesJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS For clearances : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void setSlashingData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = foundationDataService.getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getSlashing());
			foundationDataService.updateBFLIntegrationData(business_date, "CLEARANCES", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
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
			bflIntegrationDataDTO.setSlashing(String.valueOf(count));
			bflIntegrationDataDTO.setSubCategory("0");
			bflIntegrationDataDTO.setSuppliers("0");
			foundationDataService.insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}
	
	private PriceChangeResponse getClearancesResponse(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthToken();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		return helper.getClearancesWHData(request);
	}
	
	private Map<String, List<ClearancesDTO>> parseResponse(PriceChangeResponse response) {
		Map<String, List<ClearancesDTO>> pricingMap = new HashMap<String, List<ClearancesDTO>>();
		for(Item item : response.getItems()) {
			if("REGULAR".equalsIgnoreCase(item.getPricetype())) {
				ClearancesDTO clearancesDTO = getClearancesDTO(item);
//				String country = getCountryDetailsOfItem(item, map);
				String country = item.getCountry();
				if(!pricingMap.containsKey(item.getItem() + "-" + country)) {
					List<ClearancesDTO> listOfClearances = getPrices(clearancesDTO, item, country);
					pricingMap.put(item.getItem() + "-" + country, listOfClearances);
				} else if (pricingMap.size() == 0) {
					List<ClearancesDTO> listOfClearances = getPrices(clearancesDTO, item, country);
					pricingMap.put(item.getItem() + "-" + country, listOfClearances);
				}
			}
		}
		logger.info("Size of clearancesDTOs in pricing : " + pricingMap.size());
		return pricingMap;
	}
	
	private List<ClearancesDTO> getPrices(ClearancesDTO clearancesdto, Item item, String country) {
		List<ClearancesDTO> listOfClearancesDTOs = new ArrayList<ClearancesDTO>();
		ClearancesDTO clearances = null;
		if("AE".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("001");
			clearances.setDataBaseName("HODATA");
			clearances.setCountry("UAE");
			listOfClearancesDTOs.add(clearances);
		} else if("SA".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("010");
			clearances.setDataBaseName("BFLKSA");
			clearances.setCountry("KSA");
			listOfClearancesDTOs.add(clearances);
		} else if("KW".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("010");
			clearances.setDataBaseName("BFLKUWAIT");
			clearances.setCountry("KUWAIT");
			listOfClearancesDTOs.add(clearances);
		} else if("OM".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("005");
			clearances.setDataBaseName("BFLOMAN");
			clearances.setCountry("OMAN");
			listOfClearancesDTOs.add(clearances);
		} else if("QA".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("010");
			clearances.setDataBaseName("BFLQATAR");
			clearances.setCountry("QATAR");
			listOfClearancesDTOs.add(clearances);
		} else if("BH".equalsIgnoreCase(country)) {
			clearances = clearancesdto;
			clearances.setCostCode("004");
			clearances.setDataBaseName("BFLBAHRAIN");
			clearances.setCountry("BAHRAIN");
			listOfClearancesDTOs.add(clearances);
		}
		return listOfClearancesDTOs;	
	}
	
	@SuppressWarnings("unused")
	private List<ClearancesDTO> parseResponse(PriceChangeResponse response, Map<String, StoreConfigDTO> storeList) {
		List<ClearancesDTO> clearancesDTOs = new ArrayList<>();
		if(response.getItems() != null) {
			for(Item item : response.getItems()) {
				ClearancesDTO clearancesDTO = getClearancesDTO(item);
				if("S".equals(item.getLoctype())) {
					StoreConfigDTO storeConfigDTO = storeList.get(String.valueOf(item.getLocation()));
					if(null != storeConfigDTO && null != storeConfigDTO.getDataName() && !storeConfigDTO.getDataName().isEmpty()) {
						clearancesDTO.setCostCode(storeConfigDTO.getCostCodeTo());
						clearancesDTO.setDataBaseName(storeConfigDTO.getDataName());
						clearancesDTO.setCountry(storeConfigDTO.getCountry());
					} else if("2000".equals(item.getLocation())) {
						clearancesDTO.setCostCode("019");
						clearancesDTO.setDataBaseName("BFLKSA");
						clearancesDTO.setCountry("KSA");
					}
				} else {
					int loc = Integer.parseInt(item.getLocation());
					if("W".equals(item.getLoctype()) && loc > 10000 && loc < 20000) {
						clearancesDTO.setCostCode("001");
						clearancesDTO.setDataBaseName("HODATA");
						clearancesDTO.setCountry("UAE");
					} else if("W".equals(item.getLoctype()) && loc > 20000 && loc < 30000) {
						clearancesDTO.setCostCode("010");
						clearancesDTO.setDataBaseName("BFLKSA");
						clearancesDTO.setCountry("KSA");
						clearancesDTOs.add(clearancesDTO);
						
						clearancesDTO = getClearancesDTO(item);
						
						clearancesDTO.setCostCode("005");
						clearancesDTO.setDataBaseName("BFLKSA");
						clearancesDTO.setCountry("KSA");
					} else if("W".equals(item.getLoctype()) && loc > 30000 && loc < 40000) {
						clearancesDTO.setCostCode("010");
						clearancesDTO.setDataBaseName("BFLKUWAIT");
						clearancesDTO.setCountry("KUWAIT");
						clearancesDTOs.add(clearancesDTO);
						
						clearancesDTO = getClearancesDTO(item);
						
						clearancesDTO.setCostCode("005");
						clearancesDTO.setDataBaseName("BFLKUWAIT");
						clearancesDTO.setCountry("KUWAIT");
					} else if("W".equals(item.getLoctype()) && loc > 40000 && loc < 50000) {
						clearancesDTO.setCostCode("005");
						clearancesDTO.setDataBaseName("BFLOMAN");
						clearancesDTO.setCountry("OMAN");
					} else if("W".equals(item.getLoctype()) && loc > 50000 && loc < 60000) {
						clearancesDTO.setCostCode("010");
						clearancesDTO.setDataBaseName("BFLQATAR");
						clearancesDTO.setCountry("QATAR");
						clearancesDTOs.add(clearancesDTO);
						
						clearancesDTO = getClearancesDTO(item);
						
						clearancesDTO.setCostCode("002");
						clearancesDTO.setDataBaseName("BFLQATAR");
						clearancesDTO.setCountry("QATAR");
					} else if("W".equals(item.getLoctype()) && loc > 70000 && loc < 80000) {
						clearancesDTO.setCostCode("004");
						clearancesDTO.setDataBaseName("BFLBAHRAIN");
						clearancesDTO.setCountry("BAHRAIN");
						clearancesDTOs.add(clearancesDTO);
						
						clearancesDTO = getClearancesDTO(item);
						
						clearancesDTO.setCostCode("005");
						clearancesDTO.setDataBaseName("BFLBAHRAIN");
						clearancesDTO.setCountry("BAHRAIN");
					} else {
						clearancesDTO.setCostCode("001");
						clearancesDTO.setDataBaseName("HODATA");
						clearancesDTO.setCountry("UAE");
						clearancesDTOs.add(clearancesDTO);
					}
				}
				clearancesDTOs.add(clearancesDTO);
			}
		}
		return clearancesDTOs;
	}
	
	public ClearancesDTO getClearancesDTO(Item item) {
		ClearancesDTO clearancesDTO = new ClearancesDTO();
		clearancesDTO.setItemCode(item.getItem());
		try {
			SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
			String [] currentDateTime = output.format(new Date()).split(" ");
//			String [] trnDate = converEffectiveDateToDateTime(item.getActionDate()).split(" ");
			clearancesDTO.setTrndate(currentDateTime[0]);
			clearancesDTO.setTrnTime(currentDateTime[1]);
		} catch (Exception e) {
			logger.error("Exception occured while converting date for price fixing " + ExceptionUtils.getStackTrace(e));
		}
//		clearancesDTO.setTrndate(item.getEffective());
		clearancesDTO.setAction(item.getAction());
		clearancesDTO.setRetailRate(item.getPrice());
		clearancesDTO.setAedPrice(item.getAedPrice());
		clearancesDTO.setItemDesc(item.getItemName());
		clearancesDTO.setPrice(item.getPrice());
		clearancesDTO.setSalesRate(item.getPrice());
		clearancesDTO.setUserId("99");
		clearancesDTO.setTrnMode("R");
		clearancesDTO.setLocationType(item.getLoctype());
		return clearancesDTO;
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
	@SuppressWarnings("unused")
	private String converEffectiveDateToDateTime(String entryDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		Date d = sdf.parse(entryDate);
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
		return output.format(d);
	}
	
}
