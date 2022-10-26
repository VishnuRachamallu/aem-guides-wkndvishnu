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

//@SlingServlet(paths="/bin/trainingproject/testservlet")  --Felix SCR Annotation

//OSGI Declarative Service Annotation
@Component(service = Servlet.class, property = { "sling.servlet.paths=" + "/bin/trainingproject/abc" })

public class TestPathSlingServletPankaj extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String paths=request.getRequestPathInfo().getResourcePath();
		
		response.setHeader("Content-Type", "text/html");
		response.getWriter().print("<h1>Path based Sling Servlet Called</h1>");
		response.getWriter().print(getClass());
		response.getWriter().print("<h3>Paths :"+paths+"</h3>");
		response.getWriter().close();
		
	}
}
