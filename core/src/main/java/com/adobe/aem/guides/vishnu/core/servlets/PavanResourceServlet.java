package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class,immediate = true)
@SlingServletResourceTypes(resourceTypes = "foundation/components/redirect")
public class PavanResourceServlet extends SlingSafeMethodsServlet{

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		Resource resource=request.getResource();
		ResourceResolver resourceResolver=request.getResourceResolver();
		
		String pagePath=resource.getPath();
		String pagePath2=resource.getPath();
		pagePath=pagePath.replace("/jcr:content", "");
		
		int lastIndex=pagePath2.lastIndexOf("/");
		pagePath2=pagePath2.substring(0, lastIndex);
		
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(pagePath2);
		

		
		Iterator<Page> pages=page.listChildren();
		while(pages.hasNext())
		{
			response.getWriter().print(pages.next().getTitle()+"\n");
		}
		
		response.getWriter().print("\n"+"hello vishnu resource");
		response.getWriter().print("\n"+resource.getValueMap().get("cq:template", String.class));
		response.getWriter().print("\n"+resource.getPath());
		response.getWriter().print("\n page path using replace"+pagePath);
		response.getWriter().print("\n page path using substring"+pagePath);
	}
	

}