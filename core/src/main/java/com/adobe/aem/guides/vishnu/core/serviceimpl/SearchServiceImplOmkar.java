/*
 * package com.adobe.aem.guides.vishnu.core.serviceimpl;
 * 
 * import java.util.HashMap; import java.util.List; import java.util.Map;
 * 
 * import javax.jcr.RepositoryException; import javax.jcr.Session; import
 * javax.json.JsonArray;
 * 
 * import org.apache.sling.api.resource.LoginException; import
 * org.apache.sling.api.resource.ResourceResolver; import
 * org.apache.sling.api.resource.ResourceResolverFactory; import
 * org.json.JSONArray; import org.json.JSONException; import
 * org.json.JSONObject; import org.osgi.service.component.annotations.Component;
 * import org.osgi.service.component.annotations.Reference; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * import com.adobe.aem.guides.vishnu.core.utils.ResolverUtil; import
 * com.day.cq.search.PredicateGroup; import com.day.cq.search.Query; import
 * com.day.cq.search.QueryBuilder; import com.day.cq.search.result.Hit; import
 * com.day.cq.search.result.SearchResult; import com.day.cq.wcm.api.Page; import
 * com.fasterxml.jackson.databind.JsonNode; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.fasterxml.jackson.databind.node.ArrayNode;
 * 
 * @Component(service = SearchServiceImplOmkar.class, immediate = true) public
 * class SearchServiceImplOmkar {
 * 
 * @Reference QueryBuilder queryBuilder;
 * 
 * @Reference ResourceResolverFactory resourceResolverFactory;
 * 
 * Logger LOG = LoggerFactory.getLogger(getClass());
 * 
 * public Map<String, String> createTextSearchQuery(String searchText) {
 * Map<String, String> queryMap = new HashMap<>(); queryMap.put("type",
 * "cq:Page"); queryMap.put("path", "/content/we-retail");
 * queryMap.put("fulltext", searchText); queryMap.put("p.limit",
 * Long.toString(-1)); return queryMap; }
 * 
 * public JSONObject searchRes(String searchText) { JSONObject searchResults =
 * new JSONObject();
 * 
 * try { ResourceResolver resourceResolver =
 * ResolverUtil.newResolver(resourceResolverFactory); final Session session =
 * resourceResolver.adaptTo(Session.class);
 * 
 * Query query =
 * queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery(
 * searchText)), session);
 * 
 * SearchResult result = query.getResult();
 * 
 * List<Hit> hits = result.getHits(); JSONArray resultsArray = new JSONArray();
 * 
 * for (Hit hit : hits) { Page page = hit.getResource().adaptTo(Page.class);
 * JSONObject temp = new JSONObject(); temp.put("title", page.getTitle());
 * temp.put("path", page.getPath()); temp.put("properties",
 * hit.getProperties()); resultsArray.put(temp);
 * 
 * } searchResults.put("Results", resultsArray);
 * 
 * for (int i = 0; i < resultsArray.length(); i++) {
 * 
 * }
 * 
 * } catch (LoginException | RepositoryException | JSONException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); }
 * 
 * return searchResults;
 * 
 * }
 * 
 * public JSONArray getDirectChildDetails(String path,JSONArray categoriList) {
 * Map<String,String> queryMap=new HashMap<>();
 * 
 * queryMap.put("path", path); queryMap.put("path.flat", "true");
 * 
 * //complete this query map
 * 
 * Query SearchResult
 * 
 * JSONArray resultsArray=new JSONArray();
 * 
 * for(Hit hit:resultsArray.getHits()) {
 * 
 * Resource res;
 * 
 * JSONObject temp=new JSONObject(); temp.put("title", hit.getTitle());
 * temp.put("path", hit.getPath()); temp.put("properties",hit.getProperties());
 * resultsArray.put(temp);
 * 
 * 
 * categoriList.put(temp);
 * 
 * }
 * 
 * 
 * return categoriList;
 * 
 * 
 * } }
 */