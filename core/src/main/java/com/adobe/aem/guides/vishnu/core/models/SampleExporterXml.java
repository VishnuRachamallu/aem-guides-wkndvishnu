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
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.vishnu.core.models.interfa.SampleExporter;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonProperty;


///content/vishnuwknd/us/exporter/exporterusingxml/jcr:content/root/container/container/exporterxmlmftrainin
@Model(adaptables = SlingHttpServletRequest.class,adapters = SampleExporter.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = "/apps/vishnuwknd/components/exporterxmlmftraining" )

@Exporter(name = "VishnuXML",extensions = "xml")

public class SampleExporterXml implements SampleExporter{
	@Inject
	Resource componentResource;

	@ScriptVariable
	Page currentPage;

	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy")
	String modifiedBy;

	@Inject
	@Via("resource")
	@Default(values = "AEM")
	private String fname;

	@ValueMapValue
	@Default(values = "GEEKS")
	private String lname;

	@ValueMapValue
	private String author;

	@ValueMapValue
	private List<String> booksauthor;

	@Override
	public String getAuthorName() {
		// TODO Auto-generated method stub
		return author;
	}

	@Override
	public String getPageTitle() {
		return currentPage.getTitle();
	}

	@Override
	public String getHomePageName() {
		return currentPage.getName();
	}

	@Override
	@JsonProperty(value = "Last Modified by user")
	public String getLastModifiedBy() {
		return modifiedBy;
	}

	@JsonProperty(value = "vishnu-name")
	public String vishnuName() {
		return "vishnu rachamallu";
	}

	@Override
	public List<String> getBooks() {
		if (booksauthor != null) {
			return new ArrayList<String>(booksauthor);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	@JsonProperty(value = "Book with map")
	public List<Map<String, String>> getBookDetailsWithMap() {

		List<Map<String, String>> totalList = new ArrayList<>();

		Resource resource = componentResource.getChild("bookdetailswithmap");
		for (Resource rc : resource.getChildren()) {
			Map<String, String> map = new HashMap<>();

			map.put("BOOKNAME", rc.getValueMap().get("bookname", String.class));
			map.put("BOOKSUBJECT", rc.getValueMap().get("booksubject", String.class));
			map.put("PUBLISHYEAR", rc.getValueMap().get("publishyear", String.class));

			totalList.add(map);
		}

		return totalList;
	}

	@JsonProperty(value = "Test vishnu function")
	public String TestVishnuFunction() {
		return "Hello from user declared function";
	}


}
