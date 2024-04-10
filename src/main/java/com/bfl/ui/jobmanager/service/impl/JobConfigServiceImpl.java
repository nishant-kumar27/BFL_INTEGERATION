package com.bfl.ui.jobmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bfl.model.JobConfig;
import com.bfl.ui.jobmanager.dao.JobConfigDao;
import com.bfl.ui.jobmanager.dto.JobSearchQueryDTO;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Service
public class JobConfigServiceImpl implements IJobConfigService {

	private static final Logger logger = LoggerFactory.getLogger(JobConfigServiceImpl.class);
	
    @Autowired
    private JobConfigDao jobConfigDao;


	@SuppressWarnings("serial")
	@Override
	public List<JobConfig> getAllActiveJobs() {
		try {
			Specification<JobConfig> spec = new Specification<JobConfig>() {
				@Override
				public Predicate toPredicate(Root<JobConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
					Path<Integer> enabled = root.get("enabled");
					Predicate predicate = criteriaBuilder.equal(enabled, 1);
					return predicate;
				}
			};
			return jobConfigDao.findAll(spec);
		}catch (Exception e) {
			logger.error("getAllActiveJobs() "+e);
		}
		return null;
	}

	@Override
	public String getJobCron(Long id) {
		JobConfig job = jobConfigDao.findById(id).get();
		return job.getCronExpression();
	}

	@Override
	public JobConfig getJobById(Long jobId) {
		return jobConfigDao.findById(jobId).get();
	}


}