package com.adobe.aem.guides.vishnu.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class HtlTrainingNumberFormat {

	@Inject
	private String number;
	
	public String getNumber() {
		return number;
	}
}
