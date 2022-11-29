package com.adobe.aem.guides.vishnu.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.webservicesupport.Service;

@Designate(ocd = SchedulerConfiguration.class)
@Component(service = Runnable.class, immediate = true)
public class SchedulerRunnable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(SchedulerRunnable.class);
	private int schedulerId;
//comment
	@Reference
	Scheduler scheduler;

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
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n ===== Vishnu Wknd Scheduler 1  added========== \n");

		/*
		 * This will run scheduler immediately once.
		 * 
		 * ScheduleOptions scheduleOptions2 = scheduler.NOW(); scheduler.schedule(this,
		 * scheduleOptions2);
		 */

		/*
		 * This will run scheduler immediately and two more times in interval of 5
		 * seconds
		 * 
		 * ScheduleOptions scheduleOptions3=scheduler.NOW(3,5);
		 * scheduler.schedule(this,scheduleOptions3);
		 */

	}

	private void removeScheduler() {
		// TODO Auto-generated method stub
		scheduler.unschedule(String.valueOf(schedulerId));

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		LOG.info("\n ===== Vishnu Wknd Run Method========== \n");
	}

}
