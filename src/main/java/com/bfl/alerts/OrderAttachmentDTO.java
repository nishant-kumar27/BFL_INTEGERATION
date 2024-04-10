package com.bfl.alerts;

import org.springframework.core.io.InputStreamSource;

public class OrderAttachmentDTO {
	
	private String ORDER_NO;
	
	private String SHIPMENT;
	
	private String DOC_SEQ;
	
	private String DOC_TYPE;
	
	private InputStreamSource DOC_FILE;
	
	private String DOC_FILE_NAME;
	
	private String DOC_FILE_MEME;

	public String getORDER_NO() {
		return ORDER_NO;
	}

	public void setORDER_NO(String oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}

	public String getSHIPMENT() {
		return SHIPMENT;
	}

	public void setSHIPMENT(String sHIPMENT) {
		SHIPMENT = sHIPMENT;
	}

	public String getDOC_SEQ() {
		return DOC_SEQ;
	}

	public void setDOC_SEQ(String dOC_SEQ) {
		DOC_SEQ = dOC_SEQ;
	}

	public String getDOC_TYPE() {
		return DOC_TYPE;
	}

	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}

	public InputStreamSource getDOC_FILE() {
		return DOC_FILE;
	}

	public void setDOC_FILE(InputStreamSource dOC_FILE) {
		DOC_FILE = dOC_FILE;
	}

	public String getDOC_FILE_NAME() {
		return DOC_FILE_NAME;
	}

	public void setDOC_FILE_NAME(String dOC_FILE_NAME) {
		DOC_FILE_NAME = dOC_FILE_NAME;
	}

	public String getDOC_FILE_MEME() {
		return DOC_FILE_MEME;
	}

	public void setDOC_FILE_MEME(String dOC_FILE_MEME) {
		DOC_FILE_MEME = dOC_FILE_MEME;
	}
}