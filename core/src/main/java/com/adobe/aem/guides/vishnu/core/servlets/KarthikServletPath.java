package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/blogkar")

public class KarthikServletPath extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();
		JSONObject jsonObject = new JSONObject();
		JSONArray array;

		try {

			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/vishnuwknd/us/tasks");
			Iterator<Page> pages = page.listChildren();

			while (pages.hasNext()) {
				Page page1 = pages.next();
				Resource resource = page1.adaptTo(Resource.class);
				array = new JSONArray();

				for (Resource rc : resource.getChildren()) {

					if (rc.getValueMap().get("jcr:title", String.class) != null)
						array.put("Title :" + rc.getValueMap().get("jcr:title", String.class));
					if (rc.getValueMap().get("jcr:description", String.class) != null)
						array.put("Description :" + rc.getValueMap().get("jcr:description", String.class));
					if (rc.getChild("image") != null) {
						if (rc.getChild("image").getValueMap().get("fileReference", String.class) != null)
							array.put("Thumbnail :"
									+ rc.getChild("image").getValueMap().get("fileReference", String.class));
					}
					jsonObject.put(page1.getPath(), array);
				}
			}
		} catch (Exception e) {
			LOG.info("\n Login Exception {} ", e.getMessage());
			response.getWriter().print(e.getMessage());
		}

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonObject.toString());
		out.flush();
	}

}
