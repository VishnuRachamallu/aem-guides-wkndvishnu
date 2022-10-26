package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.vishnu.core.models.DemoUserServletModel1;

// http://localhost:4502/bin/vishnuproject/abc
@Component(service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/vishnuproject/abc" })

public class DemoUserServletTask9 extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String paths = request.getRequestPathInfo().getResourcePath();
		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource resource = resourceResolver.getResource(
				"/content/vishnuwknd/us/demo-user-servlet1/jcr:content/root/container/container/demouserservlet");
		
		ValueMap properties = resource.adaptTo(ValueMap.class);
		DemoUserServletModel1 model = resource.adaptTo(DemoUserServletModel1.class);

		response.setHeader("Content-Type", "text/html");
		response.getWriter().println("<h1>Path based Sling Servlet with Resource injection uisng servlet</h1>");
		response.getWriter().print(getClass());
		response.getWriter().print("<h3>Paths :" + paths + "</h3>");
		response.getWriter().println("<h3>Title using resource :" + resource.getValueMap().get("title1", String.class)+"</h3>");
		response.getWriter().println("<h3>Title using model :" + model+"</h3>");
		response.getWriter().println("<h3>Title using properties :" + properties.get("title1", String.class)+"<h3>");
		response.getWriter().close();
	}
}
