package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import com.day.cq.commons.jcr.JcrConstants;

//http://localhost:4502/content/vishnuwknd/us/en/trainingfield2-page/jcr:content.junit.xml
//http://localhost:4502/content/vishnuwknd/us/en/trainingfield2-page.junit.xml
@Component(service = Servlet.class)
@SlingServletResourceTypes(methods = {
		HttpConstants.METHOD_GET }, resourceTypes = "vishnuwknd/components/page", selectors = { "junit",
				"sjunit" }, extensions = { "html", "momos", "gif", "txt", "xml" })
public class ResourceBasedServletJUnit extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		final Resource resource=request.getResource();
		
		response.getWriter().print("Page Title :"+resource.getValueMap().get(JcrConstants.JCR_TITLE));

	}

}
