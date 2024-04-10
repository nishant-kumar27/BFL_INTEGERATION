package com.bfl.batch.jobs.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.bfl.batch.jobs.AbstractJob;
import com.bfl.model.JobConfig;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Configuration
@EnableScheduling
public class ExternalScheduler implements SchedulingConfigurer {

	private static Logger LOGGER = LoggerFactory.getLogger(ExternalScheduler.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	IJobConfigService jobService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		List<JobConfig> jobs = jobService.getAllActiveJobs();
		for(JobConfig jobConfig : jobs)
		{
			AbstractJob jobInstance =   (AbstractJob)applicationContext.getBean(jobConfig.getJobName());
			jobInstance.setJobConfig(jobConfig);
			taskRegistrar.addTriggerTask(new Runnable() {
				@Override
				public void run() {
					try {
						jobInstance.run();
					} catch (Exception e) {
						LOGGER.error(e.getMessage(),e);
					}
				}
			},triggerContext -> {
				String cron = jobService.getJobCron(jobConfig.getJobId());
				return new CronTrigger(cron).nextExecutionTime(triggerContext);

			}
					);
		}
	}

}