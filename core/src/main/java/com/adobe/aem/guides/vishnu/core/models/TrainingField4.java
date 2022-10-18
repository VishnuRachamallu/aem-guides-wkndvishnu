package com.adobe.aem.guides.vishnu.core.models;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.vishnu.core.models.interfa.TrainingField4Interface;
import com.adobe.aem.guides.vishnu.core.serviceinterface.DemoService;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = TrainingField4Interface.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TrainingField4 implements TrainingField4Interface {

	@SlingObject
	ResourceResolver resourceResolver;
	
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@OSGiService
	DemoService demoServiceA;

	@Override
	public String getPa() {
		String pagestotal="";
		Iterator<Page> pages=demoServiceA.getPages();
		
		while (pages.hasNext()) {
			pagestotal=pagestotal+"\n"+pages.next().getTitle();
		}
		return pagestotal;
	}
}
