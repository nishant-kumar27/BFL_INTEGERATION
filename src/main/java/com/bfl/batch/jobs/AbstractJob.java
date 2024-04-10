package com.bfl.batch.jobs;

import com.bfl.model.JobConfig;

public abstract class AbstractJob implements Runnable{

	private JobConfig jobConfig;
	
	public JobConfig getJobConfig() {
		return jobConfig;
	}

	public void setJobConfig(JobConfig jobConfig) {
		this.jobConfig = jobConfig;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
