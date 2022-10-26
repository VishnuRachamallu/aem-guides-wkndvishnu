package com.adobe.aem.guides.vishnu.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.test.gif
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.test.txt
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.test.momos
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1.test.xml
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.test.xml
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1.test.html
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.test.html

@Component(service = Servlet.class)
@SlingServletResourceTypes(methods = {
		HttpConstants.METHOD_GET }, resourceTypes = "vishnuwknd/components/page", selectors = { "geeks",
				"test" }, extensions = { "html", "momos", "gif", "txt", "xml" })
public class GeeksResourceTypesServlet extends SlingAllMethodsServlet {
	private static final Logger LOG = LoggerFactory.getLogger(GeeksResourceTypesServlet.class);

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		final Resource resource = req.getResource();

		String selectors = "Selectors -->";
		String extensions = req.getRequestPathInfo().getExtension();
		try {
			String selector[] = req.getRequestPathInfo().getSelectors();

			for (String s : selector) {
				selectors = selectors + " " + s;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		resp.setContentType("text/html");
		resp.getWriter().write("Page Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
		resp.getWriter().print(getClass());
		resp.getWriter().print("<h3>Resource based servlet</h3>");
		resp.getWriter().print("<h3>Selectors :" + selectors + "</h3>");
		resp.getWriter().print("<h3>Extensions :" + extensions + "</h3>");
	}

	@Override
	protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse resp)
			throws ServletException, IOException {
		try {
			LOG.info("\n ------------------------STARTED POST-------------------------");
			List<RequestParameter> requestParameterList = req.getRequestParameterList();
			for (RequestParameter requestParameter : requestParameterList) {
				LOG.info("\n ==PARAMETERS===>  {} : {} ", requestParameter.getName(), requestParameter.getString());
			}
		} catch (Exception e) {
			LOG.info("\n ERROR IN REQUEST {} ", e.getMessage());
		}
		resp.getWriter().write("======FORM SUBMITTED========");

	}

}
