package com.bfl.batch.wms.jobs;

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
import com.bfl.batch.jobs.AbstractJob;
import com.bfl.model.JobConfig;
import com.bfl.service.IWebServiceHelper;
import com.bfl.ui.jobmanager.service.IJobConfigService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component("PublishMessagesJob")
public class PublishMessagesJob extends AbstractJob {
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
		//helper.ping(authToken);
		//helper.publish(buildInventoryAdjustmentMessage(),authToken);
	}
	
	/*private ApplicationMessages buildInventoryAdjustmentMessage() throws Exception
	{
		ObjectFactory fact = new ObjectFactory();
		ApplicationMessages  messages = fact.createApplicationMessages();
		
		ApplicationMessage  message = fact.createApplicationMessage();
		message.setFamily("InvAdjust");
		message.setType("InvAdjustCre");
		
		
		com.oracle.retail.integration.base.bo.invadjustdesc.v1.ObjectFactory adjfact = new com.oracle.retail.integration.base.bo.invadjustdesc.v1.ObjectFactory();
		InvAdjustDesc invAdjustDesc = adjfact.createInvAdjustDesc();
		invAdjustDesc.setDcDestId("DC_ES");
		
		InvAdjustDtl invAdjustDtl = adjfact.createInvAdjustDtl();
		invAdjustDtl.setItemId("Aline");
		invAdjustDtl.setUnitQty(new BigDecimal("22.4"));
		invAdjustDtl.setAdjustmentReasonCode(1);
		invAdjustDtl.setFromDisposition("qqq");
		invAdjustDtl.setToDisposition("ww");
		invAdjustDtl.setTransactionCode(0);
		invAdjustDtl.setCreateDate(parseDateString("2022-12-19T12:03:08+03:00"));
		invAdjustDtl.setUserId("test");
		invAdjustDesc.getInvAdjustDtl().add(invAdjustDtl);
		
		//Create JAXB Context
		JAXBContext jaxbContext = JAXBContext.newInstance(InvAdjustDesc.class);

		//Create Marshaller
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		//Required formatting??
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		//Print XML String to Console
		StringWriter sw = new StringWriter();

		//Write XML to StringWriter
		jaxbMarshaller.marshal(invAdjustDesc, sw);

		//Verify XML Content
		message.setPayloadXml(sw.toString());
		
		messages.getApplicationMessage().add(message);
		
		return messages;
	}*/
	
	@SuppressWarnings("unused")
	private static Calendar parseDateString(String dateString) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(ZonedDateTime.parse(dateString).toInstant()));
		return cal;
	}
}
