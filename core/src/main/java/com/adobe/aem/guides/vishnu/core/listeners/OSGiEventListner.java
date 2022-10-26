package com.adobe.aem.guides.vishnu.core.listeners;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil;

@Component(service = EventHandler.class, immediate = true, property = {
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
		EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
		EventConstants.EVENT_FILTER + "=(path=/content/vishnuwknd/us/events/osgi-event-handler/*)" 
		})

public class OSGiEventListner implements EventHandler {

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	private static final Logger Log = LoggerFactory.getLogger(OSGiEventListner.class);

	@Override
	public void handleEvent(Event event) {

		Log.info("Event topic with : " + event.getTopic() + " Properties :"
				+ event.getProperty(SlingConstants.PROPERTY_PATH));
		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			Resource resource = resourceResolver
					.getResource(event.getProperty(SlingConstants.PROPERTY_PATH).toString());

			Node node = resource.adaptTo(Node.class);
			node.setProperty("Event Handler Task", "event " + event.getTopic() + " by " + resourceResolver.getUserID());

			for (String prop : event.getPropertyNames()) {
				Log.info("property: " + prop + " value " + event.getProperty(prop));
			}

			resourceResolver.commit();

		} catch (LoginException | RepositoryException | PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
