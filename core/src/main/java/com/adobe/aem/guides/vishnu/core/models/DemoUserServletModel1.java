package com.adobe.aem.guides.vishnu.core.models;


import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class DemoUserServletModel1 {

	@Inject
	private String title1;

	public String getTitle1() {
		return title1;
	}

}
