package com.adobe.aem.guides.vishnu.core.servlets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class ResourceBasedServletJUnitTest {

	ResourceBasedServletJUnit jUnit=new ResourceBasedServletJUnit();
	AemContext aemContext=new AemContext();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		aemContext.build().resource("content/test", "jcr:title","Sports");
		aemContext.currentResource("/content/test");
		
		MockSlingHttpServletRequest request = aemContext.request();
		MockSlingHttpServletResponse response = aemContext.response();

		jUnit.doGet(request, response);

		assertEquals("Page Title :Sports", response.getOutputAsString());
	}

}
