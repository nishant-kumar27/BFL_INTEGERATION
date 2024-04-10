package com.bfl.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BFL_RIT_JOB_CONFIG")
public class JobConfig {

	@Id
	private Long jobId;
	@Column(nullable = false) 
	private String jobName;
	@Column(nullable = false) 
	private String jobClassName;
	@Column(nullable = false) 
	private String cronExpression;
	@Column(nullable = false) 
	private boolean enabled;
	@Column(nullable = false) 
	private boolean adhocRun;
	@Column(nullable = false) 
	private String email;

	
	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getCronExpression() {
		return cronExpression;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
