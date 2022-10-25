package com.adobe.aem.guides.vishnu.core.models.interfa;

import java.util.List;
import java.util.Map;

public interface SampleExporter {

	public String getAuthorName();
	public List<String> getBooks();
	String getLastModifiedBy();
	String getHomePageName();
	String getPageTitle();
	List<Map<String, String>> getBookDetailsWithMap();

}
