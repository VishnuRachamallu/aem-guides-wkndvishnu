package com.adobe.aem.guides.vishnu.core.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component(service = SearchServiceImplOmkar.class, immediate = true)
public class SearchServiceImplOmkar {

	@Reference
	QueryBuilder queryBuilder;

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	private List<String> children = new ArrayList<String>();

	/*
	 * @Reference Session session;
	 */

	private Logger LOG = LoggerFactory.getLogger(getClass());

	public Map<String, String> createTextSearchQuery(String searchText) {
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("type", "cq:Page");
		queryMap.put("path", "/content/we-retail");
		queryMap.put("fulltext", searchText);
		queryMap.put("p.limit", Long.toString(-1));
		return queryMap;
	}

	public JSONObject searchRes(String searchText) {
		JSONObject searchResults = new JSONObject();

		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			final Session session = resourceResolver.adaptTo(Session.class);

			Query query = queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery(searchText)), session);

			SearchResult result = query.getResult();

			List<Hit> hits = result.getHits();
			JSONArray resultsArray = new JSONArray();
			for (Hit hit : hits) {
				Page page = hit.getResource().adaptTo(Page.class);
				JSONObject temp = new JSONObject();
				temp.put("title", hit.getTitle());
				temp.put("path", page.getPath());
				temp.put("Resource Type property", hit.getProperties().get("sling:resourceType", String.class));
				resultsArray.put(temp);

			}
			searchResults.put("Results", resultsArray);

		} catch (LoginException | RepositoryException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchResults;

	}

	public ArrayNode getDirectChildDetails(String path, ArrayNode categoriList) {

		ResourceResolver resourceResolver = null;
		try {
			resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final Session session = resourceResolver.adaptTo(Session.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> queryMap = new HashMap<>();
		queryMap.put("path", path);
		queryMap.put("path.flat", "true");
		queryMap.put("type", "cq:Page");
		queryMap.put("orderby", "path");
		queryMap.put("p.limit", "-1");
		queryMap.put("child.name", "wantedChild");
		queryMap.put("child. operator", "exists");
		Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
		SearchResult result = query.getResult();

		LOG.info("\nPassed path :" + path);

		for (Hit hit : result.getHits()) {
			try {
				JsonNode jsonNode = objectMapper.createObjectNode();
				((ObjectNode) jsonNode).put("title", hit.getTitle());
				((ObjectNode) jsonNode).put("path", hit.getPath());
				((ObjectNode) jsonNode).put("last modified date ",
						hit.getProperties().get("cq:lastModified", String.class));
				((ObjectNode) jsonNode).put("last modified by ",
						hit.getProperties().get("cq:lastModifiedBy", String.class));
				((ObjectNode) jsonNode).put("last published by",
						hit.getProperties().get("last PublishedBy", String.class));
				((ObjectNode) jsonNode).put("last published", hit.getProperties().get("last Published", String.class));

				categoriList.add(jsonNode);

				// LOG.info("\nPATH in get direct child :" + hit.getPath());

			} catch (RepositoryException e) {
				LOG.debug(e.getMessage());

			}

		}
		return categoriList;
	}

	public ArrayNode getCategories() {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> queryMap = new HashMap<>();

		ResourceResolver resourceResolver = null;
		try {
			resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Session session = resourceResolver.adaptTo(Session.class);

		ArrayNode categoriList = objectMapper.createArrayNode();
		String OfferPagesPath = "/content/vishnuwknd/us/omkar-main-page";

		LOG.info("\nCateroryList size :" + categoriList.size());
		categoriList = getDirectChildDetails(OfferPagesPath, categoriList);

		Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
		List<String> mylist = new ArrayList<>();
		Pattern pattern = Pattern.compile("[Vv][0-9]*-[0-9]*");

		LOG.info("\nCateroryList size :" + categoriList.size());
		for (int i = 0; i < categoriList.size(); i++) {
			JsonNode node = categoriList.get(i);

			try {
				// CategoryDetailModel categoryDetail
				// =objectMapper.treeToValue(node,CategoryDetailModel.class);
				Matcher matcher = pattern.matcher(node.get("title").asText());

				while (matcher.find()) {
					ArrayNode ChildCategoriesArray = objectMapper.createArrayNode();
					ChildCategoriesArray = getDirectChildDetails(node.get("path").asText(), ChildCategoriesArray);
					// mylist.add(categoriList.get(i).asText());
					mylist.add(matcher.group());

					LOG.info("\nLevel2 Child Size :" + ChildCategoriesArray.size());

					for (int j = 0; j < ChildCategoriesArray.size(); j++) {
						ArrayNode ChildCategoriesArrayLevel3 = objectMapper.createArrayNode();
						JsonNode node2 = ChildCategoriesArray.get(j);
						ChildCategoriesArrayLevel3 = getDirectChildDetails(node2.get("path").asText(),
								ChildCategoriesArrayLevel3);
						LOG.info("\nLevel3 Child Size :" + ChildCategoriesArrayLevel3.size());

					}

				}

			} catch (Exception e) {
				LOG.debug(e.getMessage());

			}
		}

		return categoriList;

	}

	public List<String> getCategoriesAll() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
			ArrayNode categoriList = objectMapper.createArrayNode();
			String OfferPagesPath = "/content/vishnuwknd/us/omkar-main-page";

			LOG.info("\nCateroryList size :" + categoriList.size());
			categoriList = getDirectChildDetails(OfferPagesPath, categoriList);

			Pattern pattern = Pattern.compile("[Vv][0-9]*-[0-9]*");

			LOG.info("\nCateroryList size :" + categoriList.size());
			
			
			
			for (int i = 0; i < categoriList.size(); i++) {
				JsonNode node = categoriList.get(i);
				Matcher matcher = pattern.matcher(node.get("title").asText());
				while (matcher.find()) {
					// use ListChilderen to get all children recursively
					Page rootPage = resourceResolver.getResource(node.get("path").asText()).adaptTo(Page.class);
					Iterator<Page> listChildren = rootPage.listChildren(null, true);
					while (listChildren.hasNext()) {
						String pagePath = listChildren.next().getPath();
						children.add(listChildren.next().getPath());
						LOG.info("\nChild path :" + pagePath);
					}
				}
			}
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		}
		return children;
	}
}
