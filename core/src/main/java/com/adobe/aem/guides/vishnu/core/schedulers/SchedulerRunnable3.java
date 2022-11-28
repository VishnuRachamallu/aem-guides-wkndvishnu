package com.adobe.aem.guides.vishnu.core.schedulers;

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

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfiguration3.class)
public class SchedulerRunnable3 implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(SchedulerRunnable3.class);
	private int schedulerID;

	@Reference
	Scheduler scheduler;

	@Activate
	public void active(SchedulerConfiguration3 config) {
		schedulerID = config.schedulerName().hashCode();
		addScheduler(config);
	}

	@Deactivate
	public void deactive() {
		removeScheduler();
	}

	private void removeScheduler() {
		scheduler.unschedule(String.valueOf(schedulerID));

	}

	@Modified
	public void modify(SchedulerConfiguration3 config) {

		removeScheduler();

		schedulerID = config.schedulerName().hashCode();
		addScheduler(null);

	}

	private void addScheduler(SchedulerConfiguration3 config) {
		// TODO Auto-generated method stub

		ScheduleOptions scheduleOptions = scheduler.EXPR(config.crexp());

		scheduleOptions.name("This is Scheduler config 3");
		scheduleOptions.canRunConcurrently(false);

		scheduler.schedule(this, scheduleOptions);

	}

	@Override
	public void run() {
		LOG.info("\n ===== Vishnu Wknd Run Method 3========== \n");
	}

}
