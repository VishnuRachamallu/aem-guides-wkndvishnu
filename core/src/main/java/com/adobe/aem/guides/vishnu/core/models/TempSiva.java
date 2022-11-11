package com.adobe.aem.guides.vishnu.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TempSiva {

	@Inject
	@Via("resource")
	@Named("textArea")
	private String area;

	@ValueMapValue @Default(values = "Krishna")
	private String title1;

	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy")
	private String lastModifiedBY;
	
	@RequestAttribute 
	public String attrib;

	public String getTextArea() {
		return area;
	}

	public String getLastModifiedBY() {
		return lastModifiedBY;
	}
	
	public String getUser() {
		return attrib;
	}

	@PostConstruct
	public String getTitle1() {

		Logger Log = LoggerFactory.getLogger(getClass());

		Log.info("in  getTitle1");
		return title1;
	}

	@PostConstruct
	public void initial() {
		Logger Log = LoggerFactory.getLogger(getClass());

		title1 = "Mr." + title1;

		Log.info("Title :" + title1);
		Log.info("Area :" + area);
	}

}
