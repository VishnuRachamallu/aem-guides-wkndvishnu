package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, immediate = true)
@SlingServletPaths(value = { "/bin/pavan", "/bin/pavan2" })
public class PavanPathServlet extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().print("Hello world");
	}

}
