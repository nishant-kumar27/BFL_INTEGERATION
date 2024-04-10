package com.bfl.ui.jobmanager.dto;

public class JobSearchQueryDTO {
	
	
	private String jobName;
	
	private String jobId;
	
	private boolean includeInactiveJobs;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public boolean isIncludeInactiveJobs() {
		return includeInactiveJobs;
	}

	public void setIncludeInactiveJobs(boolean includeInactiveJobs) {
		this.includeInactiveJobs = includeInactiveJobs;
	}

	@Override
	public String toString() {
		return "JobSearchQueryDTO [jobName=" + jobName + ", jobId=" + jobId + ", includeInactiveJobs="
				+ includeInactiveJobs + "]";
	}
	
}
