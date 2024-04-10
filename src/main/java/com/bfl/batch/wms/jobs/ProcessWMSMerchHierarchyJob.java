package com.bfl.batch.wms.jobs;

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

import com.bfl.alerts.EmailService;
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.dto.BflIntegrationDataDTO;
import com.bfl.dto.DepartmentDto;
import com.bfl.gencode.merchhier.deps.DepartmentResponse;
import com.bfl.gencode.merchhier.deps.Item;
import com.bfl.gencode.merchhier.deps.Link;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSMerchHierarchyJob")
public class ProcessWMSMerchHierarchyJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSMerchHierarchyJob.class);
	
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
			logger.info("ProcessWMSMerchHierarchyJob job running...");
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
					fromDate = convertDateFormat("2023-04-01T10:00:01.493+05:30");
				}
				DepartmentResponse response = null;
				List<DepartmentDto> departments = new ArrayList<>();
				int count = 0;
				do {
					if(response == null)
						response = getMerchHierarchy(mapper, fromDate, toDate, null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(links != null && links.size() > 0)
							response = getMerchHierarchy(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					//Parse webservice response
					if(response != null && response.getItems() != null && response.getItems().size() > 0) {
						count = count + response.getItems().size();
						departments.addAll(parseResponse(response));
					}
				} while(response != null && response.getHasMore());
				
				if(departments.size() > 0)
					foundationDataService.persistMerchHierarchyData(departments);
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setMerchHirarchy(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setMerchHirarchy(count);
					}
				}
				
				logger.info("ProcessWMSMerchHierarchyJob ended successfully");

			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private DepartmentResponse getMerchHierarchy(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setStatus("A");
		request.setLimit(1000);
		request.setToken(token);
		return helper.getMerchHierarchyForWMS(request);
	}
	
	private void setMerchHirarchy(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = foundationDataService.getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			int counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getCategory());
			foundationDataService.updateBFLIntegrationData(business_date, "CATEGORY", String.valueOf(counter));
			counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getDept());
			foundationDataService.updateBFLIntegrationData(business_date, "DEPT", String.valueOf(counter));
			counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getDivision());
			foundationDataService.updateBFLIntegrationData(business_date, "DIVISION", String.valueOf(counter));
			counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getSubCategory());
			foundationDataService.updateBFLIntegrationData(business_date, "SUBCATEGORY", String.valueOf(counter));
			counter = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getSuppliers());
			foundationDataService.updateBFLIntegrationData(business_date, "SUPPLIERS", String.valueOf(counter));			
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory(String.valueOf(count));
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept(String.valueOf(count));
			bflIntegrationDataDTO.setDivision(String.valueOf(count));
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
			bflIntegrationDataDTO.setSubCategory(String.valueOf(count));
			bflIntegrationDataDTO.setSuppliers(String.valueOf(count));
			foundationDataService.insertBFLIntegrationData(bflIntegrationDataDTO);
		}
	}
	
	private List<DepartmentDto> parseResponse(DepartmentResponse response) {
		List<DepartmentDto> depts = new ArrayList<>();
		Date date = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-dd-MM");
        String trnDate = dt1.format(date);
		for(Item dept : response.getItems()) {
			DepartmentDto deptDto = new DepartmentDto();
			deptDto.setGroupCode(dept.getGroupCode());
			deptDto.setUpdateDateTime(trnDate);
			deptDto.setGroupName(dept.getGroupName());
			deptDto.setSupplier(dept.getSupplier().length() > 20 ? dept.getSupplier().substring(0, 19) : dept.getSupplier());
			deptDto.setReportCategory(dept.getReportCategory());
			deptDto.setDeptName(dept.getDeptName());
			deptDto.setCategory(dept.getCategory());
			deptDto.setSubCategory(dept.getSubcategory());
			deptDto.setGender(dept.getGender());
			deptDto.setBrand(dept.getBrand());
			deptDto.setWasPerc("0.40");
			deptDto.setDivision(dept.getDivisionName());
			deptDto.setConsignment(Integer.parseInt(dept.getPurchaseType()) == 0 ? "N" : "Y");
			deptDto.setShortName(dept.getDept() + "-" +	dept.getGroupNo() + "-" +	dept.getDivision());
			if(null != dept.getBrand() && !dept.getBrand().isEmpty() && "MIXED BRANDS".equalsIgnoreCase(dept.getBrand().trim())) {
				deptDto.setMultiBrand("Y");
			} else {
				deptDto.setMultiBrand("N");
			}
			depts.add(deptDto);
		}
		return depts;
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
}