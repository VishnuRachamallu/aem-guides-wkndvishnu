package com.adobe.em.guides.vishnu.core.utils;



import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;



public final class ResolverUtil {

    private ResolverUtil() {

    }

	public static final String vishnu_SERVICE_USER = "vishnuwkndguides";
    public static ResourceResolver newResolver( ResourceResolverFactory resourceResolverFactory ) throws LoginException {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put( ResourceResolverFactory.SUBSERVICE, vishnu_SERVICE_USER );

        // fetches the admin service resolver using service user.
        ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
        return resourceResolver;
    }
    
	
}