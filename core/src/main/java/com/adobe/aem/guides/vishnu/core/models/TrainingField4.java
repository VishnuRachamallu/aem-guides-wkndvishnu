package com.adobe.aem.guides.vishnu.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Filter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.aem.guides.vishnu.core.models.interfa.TrainingField4Interface;
import com.adobe.aem.guides.vishnu.core.serviceinterface.DemoServiceInterf;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, adapters = TrainingField4Interface.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TrainingField4 implements TrainingField4Interface {

	@SlingObject
	ResourceResolver resourceResolver;

	@Self
	SlingHttpServletRequest slingHttpServletRequest;

	@OSGiService
	DemoServiceInterf demoServiceimplA;

	@Inject
	@Via("resource")
	private String titleservices;

	@Override
	public List<String> getTitlesWithList() {
		// TODO Auto-generated method stub
		List<String> listPages=new ArrayList<String>();
		Iterator<Page> pages= demoServiceimplA.getPagesWithItr();
		while (pages.hasNext()) {
			listPages.add(pages.next().getTitle());		
		}
		return listPages;
	}
	@Override
	public String getPagesTraining() {
		// TODO Auto-generated method stub
		String pagepaths = "";
		Iterator<Page> pages = demoServiceimplA.getPagesWithItr();
		while (pages.hasNext()) {
			pagepaths = pagepaths + pages.next().getTitle() + "\n";
		}
		return pagepaths;
	}

	@Override
	public String getTitleservices() {
		// TODO Auto-generated method stub
		return titleservices;
	}

}
