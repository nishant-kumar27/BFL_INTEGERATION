package com.bfl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BFL_RIT_PROCESS_TS")
public class LastDownloadTime {

	@Id
	@Column(name = "JOB_ID") 
	private String jobId;
	
	@Column(name = "JOB_NAME") 
	private String jobName;
	
	@Column(name = "LAST_SUCCESS_PROCESS_TS")
	private String lastSuccessProcessTimestamp;
	
	public String getjobId() {
		return jobId;
	}
	public void setjobId(String jobId) {
		this.jobId = jobId;
	}
	public String getjobName() {
		return jobName;
	}
	public void setjobName(String jobName) {
		this.jobName = jobName;
	}
	public String getLastSuccessProcessTimestamp() {
		return lastSuccessProcessTimestamp;
	}
	public void setLastSuccessProcessTimestamp(String lastSuccessProcessTimestamp) {
		this.lastSuccessProcessTimestamp = lastSuccessProcessTimestamp;
	}
	
	
	
}
