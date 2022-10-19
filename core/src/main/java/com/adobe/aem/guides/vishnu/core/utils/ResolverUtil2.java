package com.adobe.aem.guides.vishnu.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class ResolverUtil2 {

	public static final String vishnuservice = "vishnuwkndguides";

	public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory)
			throws LoginException {

		Map<String, Object> param = new HashMap<>();
		param.put(resourceResolverFactory.SUBSERVICE, vishnuservice);

		ResourceResolver resourceResolver = resourceResolverFactory.getResourceResolver(param);

		return resourceResolver;

	}
}
