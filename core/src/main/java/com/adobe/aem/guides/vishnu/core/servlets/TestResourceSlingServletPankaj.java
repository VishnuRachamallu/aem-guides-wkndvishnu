package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

//@SlingServlet(paths="/bin/trainingproject/testservlet")  --Felix SCR Annotation
//@SlingServlet(resourceTypes="/bin/trainingproject/testservlet")  --Felix SCR Annotation

//OSGI Declarative Service Annotation

//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1.sample.html
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.sample.html

@Component(service = Servlet.class, property = { "sling.servlet.resourceTypes=" + "vishnuwknd/components/page",
		"sling.servlet.selectors=" + "sample" })
public class TestResourceSlingServletPankaj extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String selectors = "Selectors -->";
		String extensions = request.getRequestPathInfo().getExtension();

		try {
			String selector[] = request.getRequestPathInfo().getSelectors();

			for (String s : selector) {
				selectors = selectors + " " + s;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		response.setHeader("Content-Type", "text/html");
		response.getWriter().print("<h1>Sling Servlet Called</h1>");
		response.getWriter().print(getClass());
		response.getWriter().print("<h3>Resource based servlet</h3>");
		response.getWriter().print("<h3>Selectors :" + selectors + "</h3>");
		response.getWriter().print("<h3>Extensions :" + extensions + "</h3>");

		response.getWriter().close();
	}
}
