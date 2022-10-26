package com.adobe.aem.guides.vishnu.core.listeners;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = {
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
		EventConstants.EVENT_FILTER + "=(path=/content/vishnuwknd/us/events/job-creator-events/*" })
public class VishnuJobCreator implements EventHandler {

	public static final Logger Log = LoggerFactory.getLogger(VishnuJobCreator.class);

	@Reference
	JobManager jobManager;

	@Override
	public void handleEvent(Event event) {

		try {
			Map<String, Object> jobProperties = new HashMap<>();
			jobProperties.put("event", event.getTopic());
			jobProperties.put("path", event.getProperty(SlingConstants.PROPERTY_PATH).toString());
			jobProperties.put("heropage", "hero page");

			Job job = jobManager.addJob("vishnu/job", jobProperties);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
