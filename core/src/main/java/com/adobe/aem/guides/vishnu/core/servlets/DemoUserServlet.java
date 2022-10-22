package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.vishnu.core.models.DemoUserServletModel1;

@Component(service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/vishnuproject/abc" })

public class DemoUserServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();
		Resource resource = resourceResolver.getResource(
				"/content/vishnuwknd/us/demo-user-servlet1/jcr:content/root/container/container/demouserservlet");

		DemoUserServletModel1 model = resource.adaptTo(DemoUserServletModel1.class);

		response.setHeader("Content-Type", "text/html");
		response.getWriter().print("<h1>Sling Servlet Called</h1>");
		response.getWriter().print("title using resource :" + resource.getValueMap().get("title1", String.class));
		response.getWriter().print("title using model :" + model.getTitle1());
		response.getWriter().close();
	}
}
