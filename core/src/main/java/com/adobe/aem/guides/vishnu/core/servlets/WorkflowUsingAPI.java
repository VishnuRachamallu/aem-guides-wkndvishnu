package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

// http://localhost:4502/bin/test123?page=/content/vishnuwknd/us/wf-model-testing/using-api
@Component(service = Servlet.class,immediate = true)
@SlingServletPaths(value = "/bin/test123")
public class WorkflowUsingAPI extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		String status = "workflow executing";
		ResourceResolver resourceResolver = request.getResourceResolver();
		String payload = request.getRequestParameter("page").getString();

		try {
			WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);

			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/wf-testing");
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
			status = workflowSession.startWorkflow(workflowModel, workflowData).getState();

		} catch (Exception e) {
			LOG.info("Error payload " + e.getMessage());
		}

		response.setHeader("Content-Type", "text/html");
		response.getWriter().write(status);
	}

}
