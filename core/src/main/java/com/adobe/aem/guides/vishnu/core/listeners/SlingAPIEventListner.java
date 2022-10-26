package com.adobe.aem.guides.vishnu.core.listeners;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ResourceChangeListener.class,immediate=true,
property = {
		ResourceChangeListener.PATHS+"=/content/vishnuwknd/us/events/sling-api-evnt-handler",
		ResourceChangeListener.CHANGES+"=ADDED",
		ResourceChangeListener.CHANGES+"=REMOVED",
		ResourceChangeListener.CHANGES+"=CHANGED",
})
public class SlingAPIEventListner implements ResourceChangeListener{

	private static final Logger Log=LoggerFactory.getLogger(SlingAPIEventListner.class);
	
	
	@Override
	public void onChange(List<ResourceChange> list) {
		for(ResourceChange rc: list) {
			Log.info("Type :"+rc.getType()+" Path :"+rc.getPath());
		}
		
	}

}
