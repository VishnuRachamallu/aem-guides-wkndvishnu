package com.adobe.aem.guides.vishnu.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.vishnu.core.helper.MultifieldHelper;
import com.adobe.aem.guides.vishnu.core.models.interfa.AuthorBooks;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class HtlTrainingListRepeat {

	@Inject
	Resource componenetResource;

	@ValueMapValue
	@Default(values = "Jaffa vishnu")
	private String author;

	@ValueMapValue
	@Named("booksauthor")
	private List<String> books;

	@ScriptVariable
	PageManager pageManager;

	@SlingObject
	ResourceResolver resourceResolver;

	public String getAuthorName() {
		return author;
	}

	public List<String> getAuthorBooks() {
		if (books != null) {
			return new ArrayList<String>(books);
		} else {
			return Collections.emptyList();
		}
	}

	public List<Map<String, String>> getBookDetailsWithMap() {

		List<Map<String, String>> totalList = new ArrayList<>();

		Resource resource = componenetResource.getChild("bookdetailswithmap");
		for (Resource rc : resource.getChildren()) {
			Map<String, String> map = new HashMap<>();

			map.put("BOOKNAME", rc.getValueMap().get("bookname", String.class));
			map.put("BOOKSUBJECT", rc.getValueMap().get("booksubject", String.class));
			map.put("PUBLISHYEAR", rc.getValueMap().get("publishyear", String.class));

			totalList.add(map);
		}

		return totalList;
	}

	public Map<String, String> getSampleMap() {
		Map<String, String> sMap = new HashMap<>();

		sMap.put("Key1", "Value1");
		sMap.put("Key2", "Value2");
		sMap.put("Key3", "Value3");
		sMap.put("Key4", "Value4");
		sMap.put("Key5", "Value5");
		sMap.put("Key6", "Value6");
		sMap.put("Key7", "Value7");

		return sMap;
	}

	public List<Page> getPages() {

		List<Page> childPage = new ArrayList<>();
		Page home = pageManager.getPage("/content/vishnuwknd/us/events");
		Iterator<Page> childs = home.listChildren();

		while (childs.hasNext()) {
			Page page = childs.next();
			childPage.add(page);
		}
		return childPage;
	}
	
	public Iterator<Page> getPagesIterator() {

		List<Page> childPage = new ArrayList<>();
		Page home = pageManager.getPage("/content/vishnuwknd/us/events");
		Iterator<Page> childs = home.listChildren();
		return childs;
	}

}
