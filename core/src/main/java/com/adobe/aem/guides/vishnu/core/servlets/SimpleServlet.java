/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.vishnu.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
//http://localhost:4502/content/vishnuwknd/us/demo-user-servlet1/_jcr_content.dad

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vishnuwknd/components/page", methods = HttpConstants.METHOD_GET, extensions = "dad")
@ServiceDescription("Simple Demo Servlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {
		final Resource resource = req.getResource();

		String selectors = "Selectors -->";
		String extensions = req.getRequestPathInfo().getExtension();
		try {
			String selector[] = req.getRequestPathInfo().getSelectors();

			for (String s : selector) {
				selectors = selectors + " , " + s;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		resp.setContentType("text/html");
		resp.getWriter().write("Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
		resp.getWriter().print(getClass());
		resp.getWriter().print("<h3>Resource based servlet</h3>");
		resp.getWriter().print("<h3>Selectors :" + selectors + "</h3>");
		resp.getWriter().print("<h3>Extensions :" + extensions + "</h3>");
	}
}
