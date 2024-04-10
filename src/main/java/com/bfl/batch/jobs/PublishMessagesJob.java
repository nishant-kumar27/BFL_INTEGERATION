package com.bfl.batch.jobs;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bfl.ConfigProperties;
import com.bfl.alerts.EmailService;
import com.bfl.model.JobConfig;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component("PublishMessagesJob")
public class PublishMessagesJob extends AbstractJob{
	Logger logger = LoggerFactory.getLogger(PublishMessagesJob.class);
	
	@Autowired
	IJobConfigService jobconfigService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IWebServiceHelper helper;
	
	@Override
	public void run() {
		JobConfig job = jobconfigService.getJobById(getJobConfig().getJobId());
		if(job.isEnabled()) {
			logger.info("PublishMessagesJob job running...");
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				publishMessages(mapper);

				logger.info("PublishMessagesJob ended successfully");

			}catch (Exception e) {
				logger.error("Error occured while publishing the messages to RICS: "+ExceptionUtils.getStackTrace(e));
				emailService.sendJobFailedAlerts(job.getJobName(), ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
	private void publishMessages(ObjectMapper mapper) throws Exception {
		@SuppressWarnings("unused")
		String authToken = ConfigProperties.getInstance().getConfigValue("rics.api.token");

	}
	

	
	@SuppressWarnings("unused")
	private static Calendar parseDateString(String dateString) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(ZonedDateTime.parse(dateString).toInstant()));
		return cal;
	}
}
