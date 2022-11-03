package com.adobe.aem.guides.vishnu.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class TempSiva {

	@Inject @Named("linkURL")
	private String link;
	
	@Inject @Named("jcr:lastModifiedBy")
	private String lastModifiedBY ;
	
	public String getLinkTesitng()
	{
		return link;
	}
	
	public String getLastModified() {
		return lastModifiedBY;
	}
	
	
}
