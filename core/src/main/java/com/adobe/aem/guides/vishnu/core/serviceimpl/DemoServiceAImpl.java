package com.adobe.aem.guides.vishnu.core.serviceimpl;

import java.util.Iterator;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnu.core.serviceinterface.DemoServiceInterf;
import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil;
import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil2;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = DemoServiceInterf.class, immediate = true, name = "DemoA")
public class DemoServiceAImpl implements DemoServiceInterf {

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	public static final Logger LOG = LoggerFactory.getLogger(DemoServiceAImpl.class);

	@Override
	public String getPagesWithString() {
		// TODO Auto-generated method stub
		String pageChildrenList = "";
		try {
			ResourceResolver resourceResolver = ResolverUtil2.newResolver(resourceResolverFactory);
			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page passedPage = pageManager.getPage("/content/vishnuwknd/us/en");
			Iterator<Page> pages = passedPage.listChildren();
			while (pages.hasNext()) {
				pageChildrenList = pageChildrenList + pages.next().getTitle();
			}
			return pageChildrenList;
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "hello";
	}
	@Override
	public Iterator<Page> getPagesWithItr() {

		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/vishnuwknd/us/en");
			Iterator<Page> pages = page.listChildren();
			return pages;
		} catch (LoginException e) {
			LOG.info("\n Login Exception {} ", e.getMessage());

		}
		
		return null;
	}

	@Override
	public String getServiceData() {
		// TODO Auto-generated method stub
		return null;
	}
}
