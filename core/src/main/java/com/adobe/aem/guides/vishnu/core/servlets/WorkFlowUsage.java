
package com.adobe.aem.guides.vishnu.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
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

// http://localhost:4502/bin/test?page=/content/vishnuwknd/us/siva-reddy

@Component(service = Servlet.class)

@SlingServletPaths(value = { "/bin/test" })

public class WorkFlowUsage extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String status = "workflow executing";

		final ResourceResolver resourceResolver = request.getResourceResolver();

		String payload = request.getRequestParameter("page").getString();

		try {

			if (StringUtils.isNotBlank(payload)) {
				WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);

				WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/aem-vishnu-page-version");
				WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);

				status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
			}

		} catch (Exception e) {
			LOG.info("Error in workflow {}", e.getMessage());
		}

		response.setHeader("Content-Type", "text/html");
		response.getWriter().write(status);
	}

}
