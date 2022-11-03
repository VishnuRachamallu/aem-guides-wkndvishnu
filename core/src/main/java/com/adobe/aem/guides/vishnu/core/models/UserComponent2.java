package com.adobe.aem.guides.vishnu.core.models;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserComponent2 {

	private static final Logger log = LoggerFactory.getLogger(UserComponent2.class);

	private List<String> users, groups;

	private Session session;

	@Self
	private SlingHttpServletRequest slingHttpServletRequest;

	@PostConstruct
	private void initial() {

		try {
			log.info("----------< Processing starts >----------");

			ResourceResolver resourceResolver = slingHttpServletRequest.getResourceResolver();

			session = resourceResolver.adaptTo(Session.class);
			UserManager userManager = ((JackrabbitSession) session).getUserManager();

			Iterator<Authorizable> userIterator = userManager.findAuthorizables("jcr:primaryType", "rep:User");
			Iterator<Authorizable> groupIterator = userManager.findAuthorizables("jcr:primaryType", "rep:Group");
			users = new LinkedList<>();
			groups = new LinkedList<>();

			while (userIterator.hasNext()) {

				log.info("Getting user");

				Authorizable user = userIterator.next();

				if (!user.isGroup()) {
					log.info("User found: {}", user.getID());
					users.add(user.getID());
				}
			}
			
			while(groupIterator.hasNext()) {
				
				log.info("Getting Group");
				Authorizable group=groupIterator.next();
				
				if(group.isGroup()) {
					log.info("Group Found: {}",group.getID());
					groups.add(group.getID());
				}
			}

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> getUsers() {
		return users;
	}
	public List<String> getGroups() {
		return users;
	}
}
