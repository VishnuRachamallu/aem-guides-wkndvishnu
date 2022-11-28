package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.vishnu.core.serviceimpl.SearchServiceImpl;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/searching")
public class QueryBuilderAPIUsage extends SlingSafeMethodsServlet {

	@Reference
	SearchServiceImpl searchServiceImpl;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		JSONObject searchResult = null;

		try {
			String searchText = "women";
			searchResult = searchServiceImpl.searchRes(searchText);

		} catch (Exception e) {
			// TODO: handle exception
		}

		response.setContentType("application/json");
		response.getWriter().write(searchResult.toString());

	}

}
