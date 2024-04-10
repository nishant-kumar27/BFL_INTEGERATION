package com.bfl.batch.wms.jobs;

import java.text.SimpleDateFormat;
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
import com.bfl.dto.CategoryDTO;
import com.bfl.gencode.merchhier.category.CategoryResponse;
import com.bfl.gencode.merchhier.category.Item;
import com.bfl.gencode.merchhier.category.Link;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationWMSDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessWMSSubCategoryJob")
public class ProcessWMSSubCategoryJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessWMSSubCategoryJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Autowired
	IFoundationWMSDataService foundationWMSDataService;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("ProcessWMSSubCategoryJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			ZonedDateTime date = ZonedDateTime.now();

			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			String lastTimestamp = foundationWMSDataService.getLastProcessingTimestamp(job.getJobId());
			String toDate= date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME); 
			try {
				if(!StringUtils.isEmpty(lastTimestamp)) {
					fromDate = lastTimestamp;
					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp);
						toDate= date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
					}
				} else {
					fromDate = "";
				}
				CategoryResponse response = null;
				List<CategoryDTO> categories = new ArrayList<>();
				do {
					if(response == null)
						response = getCategory(mapper,fromDate,toDate,null);
					else {
						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
						if(null != links && links.size() > 0)
							response = getCategory(mapper,fromDate,toDate,links.get(0).getHref());
						else
							break;
					}
					//Parse webservice response
					if(null != response && null != response.getItems() && response.getItems().size() > 0) {
						categories.addAll(parseResponse(response));
					}
					
				} while(null != response && response.getHasMore());
				
				if(categories.size() > 0) {
					foundationWMSDataService.persistCategories(categories);
				}

				if(lastTimestamp == null) {
					foundationWMSDataService.insertLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
				} else {
					foundationWMSDataService.updateLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
				}

				logger.info("ProcessWMSSubCategoryJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS For Sub Category : " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}

	private CategoryResponse getCategory(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setLimit(1000);
		request.setToken(token);
		return helper.getClass(request);
	}

	private List<CategoryDTO> parseResponse(CategoryResponse response) {
		List<CategoryDTO> subCategories = new ArrayList<>();
		if(response.getItems()!= null) {
			String format = "";
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date currentDate = new Date();
				format = simpleDateFormat.format(currentDate);
			} catch (Exception e) {
				logger.error("Error occured while converting date into string in sub category :: " + e.getMessage());
			}
			for(Item subCategory : response.getItems()) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setTrnDate(format);
				categoryDTO.setAction(subCategory.getAction());
				categoryDTO.setCategory(subCategory.getClass_());
				categoryDTO.setCreateDateTime(subCategory.getCreateDateTime());
				categoryDTO.setDept(subCategory.getDept());
				categoryDTO.setHierarchyLevel(subCategory.getHierarchyLevel());
				categoryDTO.setSubclass(subCategory.getClass_());
				categoryDTO.setSubclassName(subCategory.getClassName().toUpperCase());
				categoryDTO.setUniqueClassId(subCategory.getUniqueClassId());
				categoryDTO.setUniqueSubclassId(subCategory.getUniqueClassId());
				categoryDTO.setUpdateDateTime(subCategory.getUpdateDateTime());
				subCategories.add(categoryDTO);
			}
		}
		return subCategories;
	}
	
	
	
	
	
//	@Override
//	public void run() {
//		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
//		if(job.isEnabled()) {
//			logger.info("ProcessWMSSubCategoryJob job running...");
//			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC);
//			String fromDate = date.plusDays(-3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//			String lastTimestamp = foundationDataService.getLastProcessingTimestamp(job.getJobId());
//			String toDate= date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME); 
//			try {
//				if(!StringUtils.isEmpty(lastTimestamp)) {
//					fromDate = lastTimestamp;
//					if(ZonedDateTime.parse(lastTimestamp).isBefore(date.plusDays(-3))) {
//						@SuppressWarnings("static-access")
//						ZonedDateTime date1 = ZonedDateTime.parse(lastTimestamp).now(ZoneOffset.UTC);
//						toDate= date1.plusDays(3).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//					}
//				} else {
//					fromDate = "";
//				}
//				SubCategoryResponse response = null;
//				List<SubCategoryDTO> subCategories = new ArrayList<>();
//				do {
//					if(response == null)
//						response = getSubCategory(mapper,fromDate,toDate,null);
//					else {
//						List<Link> links = response.getLinks().stream().filter(link->link.getRel().equalsIgnoreCase("next")).collect(Collectors.toList());
//						if(links!=null && links.size() > 0)
//							response = getSubCategory(mapper,fromDate,toDate,links.get(0).getHref());
//						else
//							break;
//					}
//					//Parse webservice response
//					if(response!=null && response.getItems() != null && response.getItems().size() > 0)
//						subCategories.addAll(parseResponse(response));
//				
//				} while(response!=null && response.getHasMore());
//				
//				if(subCategories.size() > 0)
//					foundationDataService.persistSubCategories(subCategories);
//
//				if(lastTimestamp == null) {
//					foundationDataService.insertLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				} else {
//					foundationDataService.updateLastProcessingTimestamp(toDate, job.getJobId(), job.getJobName());
//				}
//				
//				logger.info("ProcessWMSSubCategoryJob ended successfully");
//			} catch (Exception e) {
//				logger.error("Error occured while publishing the messages to RICS For Sub Category : " + ExceptionUtils.getStackTrace(e));
//				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
//			}
//		}
//	}
//	
//	private SubCategoryResponse getSubCategory(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
//		String token = helper.getAuthToken();
//		FoundadtionRequest request = new FoundadtionRequest();
//		request.setEndTime(endTime);
//		request.setStartTime(startTime);
//		request.setUrl(url);
//		request.setLimit(1000);
//		request.setToken(token);
//		return helper.getSubClass(request);
//	}
//	
//	private List<SubCategoryDTO> parseResponse(SubCategoryResponse response) {
//		List<SubCategoryDTO> subCategories = new ArrayList<>();
//		if( response.getItems()!= null) {
//			for(Item subCategory : response.getItems()) {
//				SubCategoryDTO subCategoryDto = new SubCategoryDTO();
//				subCategoryDto.setAction(subCategory.getAction());
//				subCategoryDto.setCategory(subCategory.getClass_());
//				subCategoryDto.setCreateDateTime(subCategory.getCreateDateTime());
//				subCategoryDto.setDept(subCategory.getDept());
//				subCategoryDto.setHierarchyLevel(subCategory.getHierarchyLevel());
//				subCategoryDto.setSubclass(subCategory.getSubclass());
//				subCategoryDto.setSubclassName(subCategory.getSubclassName());
//				subCategoryDto.setUniqueClassId(subCategory.getUniqueClassId());
//				subCategoryDto.setUniqueSubclassId(subCategory.getUniqueSubclassId());
//				subCategoryDto.setUpdateDateTime(subCategory.getUpdateDateTime());
//				subCategories.add(subCategoryDto);
//			}
//		}
//		return subCategories;
//	}
	
}
