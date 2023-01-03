package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.vishnu.core.serviceimpl.SearchServiceImplOmkar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/searchingOMKAR")
public class QueryBuilderAPIUsageOMKAR extends SlingSafeMethodsServlet {

	@Reference
	SearchServiceImplOmkar searchServiceImplOmkar;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ObjectMapper objectMapper = new ObjectMapper(); ArrayNode categoriListtemp2 =
		 * objectMapper.createArrayNode(); ArrayNode categoriListtemp3 =
		 * searchServiceImplOmkar
		 * .getDirectChildDetails("/content/vishnuwknd/us/omkar-main-page",
		 * categoriListtemp2);
		 * response.getWriter().write("\nExecuted Array Node Size categoriListtemp3 :" +
		 * categoriListtemp3.size());
		 * 
		 * for (JsonNode obj : categoriListtemp3) {
		 * response.getWriter().write("\n iterating nodes :" + obj.get("title"));
		 * response.getWriter().write("\n iterating nodes :" + obj.get("path"));
		 * 
		 * }
		 */

		/*
		 * //This is for getCategories 
		 * ArrayNode categoriListtemp =
		 * searchServiceImplOmkar.getCategories();
		 * response.getWriter().write("\nExecuted Array NODE");
		 * response.getWriter().write("\nExecuted Array Node Size :" +
		 * categoriListtemp.size());
		 */

		// This is for getCategoriesAll
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayNode categoriListAllChilds = objectMapper.createArrayNode();
		categoriListAllChilds=searchServiceImplOmkar.getCategoriesAll();
		
		response.getWriter().write("\nExecuted Array NODE");
		response.getWriter().write("\nExecuted Array Node Size :");

	}

}
