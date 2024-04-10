package com.bfl.ui.jobmanager.service;

import java.util.List;

import com.bfl.model.JobConfig;
import com.bfl.ui.jobmanager.dto.JobSearchQueryDTO;

public interface IJobConfigService {

	
	String getJobCron(Long id);
    
    List<JobConfig> getAllActiveJobs();
    
    JobConfig getJobById(Long jobId);
    
}
