package com.bfl.api.logging;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bfl.ConfigProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class LoggingHelper {

	private static final Logger logger = LoggerFactory.getLogger(LoggingHelper.class); 

	public static void logRequest(Object obj)
	{
		try {
			String logging = ConfigProperties.getInstance().getConfigValue("app.api.logging.enable");
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			if (obj != null && logging.equalsIgnoreCase("true")) {
				logger.info("\nRequest: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public static void logResponse(Object obj)
	{
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			String logging = ConfigProperties.getInstance().getConfigValue("app.api.logging.enable");
			if (obj != null && logging.equalsIgnoreCase("true")) {
				logger.info("\nResponse: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public static String getResponseString(Object obj)
	{
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}

}
