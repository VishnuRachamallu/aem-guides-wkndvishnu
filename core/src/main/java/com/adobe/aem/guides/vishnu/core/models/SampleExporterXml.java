package com.adobe.aem.guides.vishnu.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

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
import com.adobe.aem.guides.vishnu.core.models.interfa.SampleExporterXMLINterf;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonProperty;

@Exporter(name = "vishnuxml",extensions = "xml",selector = "samplegeeks")
@Model(adaptables = SlingHttpServletRequest.class,
adapters = SampleExporterXMLINterf.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
resourceType = "vishnuwknd/components/exporterxmlmftraining" )



@XmlRootElement(name = "Root xml element")
public class SampleExporterXml implements SampleExporterXMLINterf{
	@Inject
	Resource componentResource;

	@ScriptVariable
	Page currentPage;

	@XmlElement
	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy")
	String modifiedBy;

	@XmlElement
	@ValueMapValue
	private String author;

	@XmlElement
	@ValueMapValue
	private List<String> booksauthor;

	@Override
	@XmlElement
	public String getAuthorName() {
		// TODO Auto-generated method stub
		return author;
	}

	@Override
	@XmlElement
	public String getPageTitle() {
		return currentPage.getTitle();
	}

	@Override
	@XmlElement
	public String getHomePageName() {
		return currentPage.getName();
	}

	@Override
	@XmlElement(name =  "Last Modified by user xml")
	public String getLastModifiedBy() {
		return modifiedBy;
	}

	@XmlElement(name =  "vishnu-name xml")
	public String vishnuName() {
		return "vishnu rachamallu";
	}

	@Override
	@XmlElement(name = "GET BOOKS")
	@XmlElementWrapper(name = "total books volumes")
	public List<String> getBooks() {
		if (booksauthor != null) {
			return new ArrayList<String>(booksauthor);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	@XmlElement(name = "Book with map xml")
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

	@XmlElement(name = "Test vishnu function xml")
	public String TestVishnuFunction() {
		return "Hello from user declared function";
	}


}
