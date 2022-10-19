package com.adobe.aem.guides.vishnu.core.serviceimpl;

import java.util.Iterator;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnu.core.serviceinterface.DemoService;
import com.adobe.em.guides.vishnu.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = DemoService.class, immediate = true)
public class DemoServiceAImpl implements DemoService {

	private static final Logger LOG = LoggerFactory.getLogger(DemoServiceAImpl.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	@Override
	public String getPages() {
		String pagepaths = "";

		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/vishnuwknd/us/en");
			Iterator<Page> pages = page.listChildren();

			while (pages.hasNext()) {
				pagepaths = pagepaths + pages.next().getTitle()+"\n";
			}
			return pagepaths;
		} catch (LoginException e) {
			LOG.info("\n Login Exception {} ", e.getMessage());
			pagepaths = e.getMessage();
		}
		return pagepaths;

	}

	@Override
	public String getServiceData() {
		// TODO Auto-generated method stub
		return null;
	}

}
