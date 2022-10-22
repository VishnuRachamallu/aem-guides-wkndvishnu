package com.adobe.aem.guides.vishnu.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.vishnu.core.helper.MultifieldHelper;
import com.adobe.aem.guides.vishnu.core.models.interfa.AuthorBooks;

@Model(adaptables = SlingHttpServletRequest.class, adapters = AuthorBooks.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AuthorBooksImpl implements AuthorBooks {

	@Inject
	Resource componenetResource;

	@ValueMapValue
	@Default(values = "Jaffa")
	private String author;

	@ValueMapValue @Named("booksauthor")
	private List<String> books;

	// author
	@Override
	public String getAuthorName() {
		return author;
	}

	@Override
	public List<String> getAuthorBooks() {
		if (books != null) {
			return new ArrayList<String>(books);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
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

	@Override
	public List<MultifieldHelper> getBookDetailsWithBean() {

		List<MultifieldHelper> totalList = new ArrayList<>();

		Resource resource = componenetResource.getChild("bookdetailswithbean");
		for (Resource rc : resource.getChildren()) {
			MultifieldHelper multifieldHelper = new MultifieldHelper(rc);

			totalList.add(multifieldHelper);
		}
		return totalList;
	}

}
