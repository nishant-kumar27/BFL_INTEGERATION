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
import com.bfl.gencode.merchhier.subcategory.Item;
import com.bfl.gencode.merchhier.subcategory.Link;
import com.bfl.gencode.merchhier.subcategory.SubCategoryResponse;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSGroupsJob")
public class ProcessWMSGroupsJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSGroupsJob.class);
	
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
			logger.info("ProcessWMSGroupsJob job running...");
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
				SubCategoryResponse response = null;
				List<DepartmentDto> departments = new ArrayList<>();
				int count = 0;
				do {
					if(response == null)
						response = getGroups(mapper, fromDate, toDate, null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(null != links && links.size() > 0)
							response = getGroups(mapper, fromDate, toDate, links.get(0).getHref());
						else
							break;
					}
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						count = count + response.getItems().size();
						departments.addAll(parseResponse(response));
					}
				} while(null != response && response.getHasMore());
				
				if(departments.size() > 0) {
					foundationDataService.persistDepartments(departments);
				}
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setGroupData(count);
					}
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
					if(count > 0) {
						setGroupData(count);
					}
				}
//				if(lastTimestamp == null) {
//					foundationDataService.insertLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				} else {
//					foundationDataService.updateLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				}
				logger.info("ProcessWMSGroupsJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void setGroupData(int count) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		String business_date = simpleDateFormat.format(new Date());
		List<BflIntegrationDataDTO> listBflIntegrationDataDTOs = foundationDataService.getBFLIntegrationData(business_date);
		if(null != listBflIntegrationDataDTOs && listBflIntegrationDataDTOs.size() > 0) {
			count = count + Integer.parseInt(listBflIntegrationDataDTOs.get(0).getItemGroups());
			foundationDataService.updateBFLIntegrationData(business_date, "ITEM_GROUPS", String.valueOf(count));
		} else {
			BflIntegrationDataDTO bflIntegrationDataDTO = new BflIntegrationDataDTO();
			bflIntegrationDataDTO.setBusinessDate(business_date);
			bflIntegrationDataDTO.setBrands("0");
			bflIntegrationDataDTO.setCategory("0");
			bflIntegrationDataDTO.setContainerId("0");
			bflIntegrationDataDTO.setContainerMapping("0");
			bflIntegrationDataDTO.setDept("0");
			bflIntegrationDataDTO.setDivision("0");
			bflIntegrationDataDTO.setItemGroups(String.valueOf(count));
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

	private SubCategoryResponse getGroups(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setStatus("A");
		request.setToken(token);
		return helper.getSubClass(request);
	}
	
	private List<DepartmentDto> parseResponse(SubCategoryResponse response) {
		List<DepartmentDto> depts = new ArrayList<>();
		for(Item dept : response.getItems()) {
			DepartmentDto deptDto = new DepartmentDto();
//			String groupCode = "";
//			for(CustomFlexAttribute customFlexAttribute : dept.getCustomFlexAttribute()) {
//				if("Group_code".equalsIgnoreCase(customFlexAttribute.getName())) {
//					groupCode = customFlexAttribute.getValue(); 
//				}
//			}
			deptDto.setGroupCode(dept.getGroupCode());
			deptDto.setGroupName(dept.getSubclassName());
			deptDto.setAction(dept.getAction());
			
			deptDto.setDeptId(String.valueOf(dept.getDept()));
			deptDto.setClassId(String.valueOf(dept.getClass_()));
			deptDto.setSubclassId(String.valueOf(dept.getSubclass()));
			deptDto.setSubClassUniqueId(String.valueOf(dept.getUniqueSubclassId()));
			deptDto.setSupplier(dept.getSupplier());
			deptDto.setSuppCode(dept.getSupTsCode());
			deptDto.setCountry(dept.getCountryId());
//			deptDto.setGroupCode(dept.getGroupCode());
//			deptDto.setGroupName(dept.getGroupName());
			deptDto.setShortName(" ");
			depts.add(deptDto);
		}
		return depts;
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
}
