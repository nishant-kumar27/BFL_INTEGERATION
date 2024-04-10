package com.bfl.batch.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfl.alerts.EmailService;
import com.bfl.model.JobConfig;
import com.bfl.request.FoundadtionRequest;
import com.bfl.service.IFoundationDataService;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("ProcessBFLOrderProcedureQueueJob")
public class ProcessBFLOrderProcedureQueueJob extends AbstractJob {
	
	Logger logger = LoggerFactory.getLogger(ProcessBFLOrderProcedureQueueJob.class);
	
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
			logger.info("ProcessBFLOrderProcedureQueueJob job running...");
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

				
				callOrdersProcedure(mapper, fromDate, toDate, null);
				
				if(lastTimestamp == null) {
					foundationDataService.insertLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				} else {
					foundationDataService.updateLastProcessingTimestamp(beforeDate, job.getJobId(), job.getJobName());
				}
				logger.info("ProcessBFLOrderProcedureQueueJob ended successfully");
			} catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: " + ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void callOrdersProcedure(ObjectMapper mapper, String startTime, String endTime, String url) throws Exception {
		String token = helper.getAuthTokenForProd();
		FoundadtionRequest request = new FoundadtionRequest();
		request.setEndTime(endTime);
		request.setStartTime(startTime);
		request.setUrl(url);
		request.setStatus("N");
		request.setToken(token);
		helper.callOrdersProcedure(request);
	}
	
	private String convertDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		Date d = sdf.parse(date);
		return output.format(d);
	}
	
}
