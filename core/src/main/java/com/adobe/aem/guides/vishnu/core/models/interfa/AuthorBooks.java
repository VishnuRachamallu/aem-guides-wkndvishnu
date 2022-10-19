package com.adobe.aem.guides.vishnu.core.models.interfa;

import java.util.List;
import java.util.Map;

import com.adobe.aem.guides.vishnu.core.helper.MultifieldHelper;



public interface AuthorBooks {
	List<Map<String, String>> getBookDetailsWithMap();

	String getAuthorName();

	List<String> getAuthorBooks();

	List<MultifieldHelper> getBookDetailsWithBean();

	
}
