package com.adobe.aem.guides.vishnu.core.schedulers;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd = SchedulerConfigurationJob.class)
@Component(service = Job.class,immediate = true)
public class SchedulerJob implements Job{

	private static final Logger LOG=LoggerFactory.getLogger(SchedulerJob.class);
	
	@Reference
	Scheduler scheduler;
	
	private int schedulerId;
	
	@Activate
	public void activate(SchedulerConfiguration configuration) {
		schedulerId = configuration.schedulerName().hashCode();
		addScheduler(configuration);
	}

	@Deactivate
	public void diactivate(SchedulerConfiguration configuration) {
		removeScheduler();
	}
	
	@Modified
	protected void modified(SchedulerConfiguration config) {
		removeScheduler();

		schedulerId = config.schedulerName().hashCode();
		addScheduler(config);
	}

	private void addScheduler(SchedulerConfiguration configuration) {
		// TODO Auto-generated method stub
		ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.cornExpression());
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n ===== Vishnu Wknd Scheduler JOB  added========== \n");

	}

	private void removeScheduler() {
		// TODO Auto-generated method stub
		scheduler.unschedule(String.valueOf(schedulerId));

	}
	
	@Override
	public void execute(JobContext jobContext) {
		// TODO Auto-generated method stub
		LOG.info("\n ===== Execute Vishnu Wknd  JOB Method========== \n");
		
	}

}
