package com.adobe.aem.guides.vishnu.core.listeners;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventListener.class,immediate = true)
public class JCREventListner implements EventListener{

	private static final Logger Log=LoggerFactory.getLogger(JCREventListner.class);
	
	private Session session;
	
	@Reference
	SlingRepository slingRepository;
	
	@Activate
	public void activate() throws LoginException, RepositoryException {
		session=slingRepository.loginService("vishnuwkndguides", null);
		session.getWorkspace().getObservationManager().addEventListener(this, Event.NODE_ADDED| Event.PROPERTY_ADDED, "/content/vishnuwknd/us/events/jcr-event-listner", true, null, null, false);
		
		
	}

	@Override
	public void onEvent(EventIterator events) {
		try {
			while(events.hasNext()) {
				Log.info("Type: "+ events.nextEvent().getType()+" Identifier: "+events.nextEvent().getIdentifier()+" path :" + events.nextEvent().getPath());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
