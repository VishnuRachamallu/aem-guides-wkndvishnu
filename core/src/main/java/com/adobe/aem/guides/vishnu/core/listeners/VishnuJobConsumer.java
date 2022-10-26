package com.adobe.aem.guides.vishnu.core.listeners;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil;

@Component(service = JobConsumer.class, immediate = true)
public class VishnuJobConsumer implements JobConsumer {

	public static final Logger Log = LoggerFactory.getLogger(VishnuJobConsumer.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	@Override
	public JobResult process(Job job) {
		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);

			String path = (String) job.getProperty("event");
			String event = (String) job.getProperty("event");
			String heropage = (String) job.getProperty("heropahe");

			Log.info("\n Job executing for :{}", resourceResolver.getResource(heropage).getName());
			return JobResult.OK;

		} catch (LoginException e) {
			Log.info("\n error in job consumer :{} ", e.getMessage());
			return JobResult.FAILED;
		}

	}

}
