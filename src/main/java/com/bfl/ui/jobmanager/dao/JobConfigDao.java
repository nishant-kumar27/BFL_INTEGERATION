package com.bfl.ui.jobmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bfl.model.JobConfig;

@Repository
public interface JobConfigDao extends JpaRepository<JobConfig, Long>, JpaSpecificationExecutor<JobConfig> {
	
	
		
}