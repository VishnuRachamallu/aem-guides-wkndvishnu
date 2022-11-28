
package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
class ResourceBasedServletJUnitTest2 {

	private ResourceBasedServletJUnit fixture = new ResourceBasedServletJUnit();

	@Test
	void doGet(AemContext context) throws ServletException, IOException {
		context.build().resource("/content/test", "jcr:title", "Sports").commit();
		context.currentResource("/content/test");

		MockSlingHttpServletRequest request = context.request();
		MockSlingHttpServletResponse response = context.response();

		fixture.doGet(request, response);

		assertEquals("Page Title :Sports", response.getOutputAsString());
	}
}
