package com.adobe.aem.guides.vishnu.core.models;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class DemoUserServletModel1 {

	@Inject
	private String title1;

	public String getTitle1() {
		return title1;
	}
	
}
