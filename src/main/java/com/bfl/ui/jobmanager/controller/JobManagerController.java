/*package com.bfl.ui.jobmanager.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bfl.batch.jobs.AbstractJob;
import com.bfl.model.JobConfig;
import com.bfl.service.JobConfigRepository;
import com.bfl.ui.jobmanager.dto.JobSearchQueryDTO;
import com.bfl.ui.jobmanager.service.IJobConfigService;

@Controller
public class JobManagerController {

	private static final Logger logger = LoggerFactory.getLogger(JobManagerController.class);

	private final String SEARCH_RESULTS_SESSION_ATTR="SEARCH_RESULTS";
	@Autowired
	JobConfigRepository jobConfigRepository;
	@Autowired
	private ApplicationContext context;
	@Autowired
	IJobConfigService jobService;
	
	@RequestMapping("/jobManager")
	public ModelAndView displayjobsearchHome(ModelMap model) {
		ModelAndView view = new ModelAndView();
		view.addObject("jobSearchQuery", new JobSearchQueryDTO());
		view.setViewName("jobManager");
		return view;
	}

	@RequestMapping(value={"/jobs/processJobSearch"}, method=RequestMethod.POST)
	public ModelAndView processJobSearch(@ModelAttribute("jobSearchQuery") @Valid JobSearchQueryDTO jobSearchQueryDTO, BindingResult bindingResults,HttpServletRequest request)
	{
		ModelAndView view = new ModelAndView();
		if(bindingResults.hasErrors())
		{
			view.addObject(jobSearchQueryDTO);
			view.setViewName("jobManager");
			return view;
		}
		List<JobConfig> jobs  = jobService.searchJob(jobSearchQueryDTO);
		if(jobs == null || jobs.size()==0)
		{
			view.addObject("message","No Job found for given criteria!");
			view.setViewName("jobManager");
		}
		else
		{
			view.addObject("jobs",jobs);
			request.getSession().setAttribute(SEARCH_RESULTS_SESSION_ATTR, jobs);
			view.setViewName("jobManager");
		}
		return view;
	}
	@RequestMapping(value={"/jobs/jobDetail"}, method=RequestMethod.GET)
	public ModelAndView fetchJobDetail(@RequestParam("jobId") String jobId)
	{
		ModelAndView view = new ModelAndView();

		JobConfig job  = jobService.getJobById(Long.valueOf(jobId));
		view.addObject("job", job);
		view.setViewName("jobDetail");
		return view;
	}
	@RequestMapping(value={"/jobs/updateJob"}, params = "updateJob", method=RequestMethod.POST)
	public ModelAndView updateJobDetails(@ModelAttribute("job") JobConfig job, HttpServletRequest request)
	{
		ModelAndView view = new ModelAndView();
		try {
			JobConfig jobObj  = jobService.getJobById(Long.valueOf(job.getJobId()));
			jobObj.setEnabled(job.isEnabled());
			jobObj.setCronExpression(job.getCronExpression());
			jobObj.setAlertEmailSubject(job.getAlertEmailSubject());
			jobObj.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			jobService.save(jobObj);
			view.addObject("job", job);
			view.addObject("message","Details saved successfully!");
			view.setViewName("jobDetail");

		}catch (Exception e) {
			logger.error("Error occurred while saving the details.. "+e);
			view.addObject("job", job);
			view.addObject("message","Error occurred while saving the details!");
			view.setViewName("jobDetail");
		}
		return view;
	}
	@RequestMapping(value={"/jobs/updateJob"}, method=RequestMethod.POST)
	public ModelAndView updateCronDescription(@ModelAttribute("job") JobConfig job, BindingResult bindingResults)
	{
		ModelAndView view = new ModelAndView();
		view.addObject("job", job);
		view.setViewName("jobDetail");
		return view;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/jobs/runJobAdhoc"}, method=RequestMethod.GET)
	public ModelAndView runJobAdhoc(@RequestParam("jobId") String jobId, HttpServletRequest request)
	{
		ModelAndView view = new ModelAndView();

		List<JobConfig> jobs = ((List<JobConfig>)request.getSession().getAttribute(SEARCH_RESULTS_SESSION_ATTR));
		try {
			if(jobs!=null && jobs.size()>0)
			{
				for(JobConfig jobObj : jobs)
				{
					if(jobObj.getJobId().longValue()==Long.valueOf(jobId))
					{
						AbstractJob job = ((AbstractJob)context.getBean(jobObj.getJobClassName()));
						job.setJobConfig(jobObj);
						job.run();
						view.addObject("jobs",jobs);
						view.addObject("jobSearchQuery", new JobSearchQueryDTO());
						request.getSession().setAttribute(SEARCH_RESULTS_SESSION_ATTR, jobs);
						view.setViewName("jobManager");
						view.addObject("message","Job successfully triggered!");
						return view;
					}
				}
			}
			view.addObject("jobs",jobs);
			view.addObject("jobSearchQuery", new JobSearchQueryDTO());
			view.setViewName("jobManager");
			view.addObject("message","No Job found!");

		}catch (Exception e) {
			view.addObject("jobs",jobs);
			view.addObject("jobSearchQuery", new JobSearchQueryDTO());
			view.setViewName("jobManager");
			view.addObject("message","Could not trigger job...please check the aplication logs for more detail!");
		}
		return view;
	}
	/*
	@RequestMapping(value={"/jobs/statistics"}, method=RequestMethod.GET)
	public ModelAndView showStatistics(@RequestParam("jobId") String jobId, HttpServletRequest request)
	{
		ModelAndView view = new ModelAndView();
		List<JobExecutionLog> statistics = jobExecutionLogService.getJobStatistics(Long.valueOf(jobId));
		view.addObject("statistics", statistics!=null?statistics: new ArrayList<JobExecutionLog>());
		view.setViewName("jobStatistics");
		return view;
	}
}
*/