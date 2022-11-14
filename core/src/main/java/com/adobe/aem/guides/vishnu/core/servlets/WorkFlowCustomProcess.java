package com.adobe.aem.guides.vishnu.core.servlets;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, immediate = true, property = {
		"process.label" + "=AEM Vishnu Wrokflow Process 1" })
public class WorkFlowCustomProcess implements WorkflowProcess {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap dataMap)
			throws WorkflowException {

		/*
		 * try {
		 * 
		 * WorkflowData workflowData = workItem.getWorkflowData();
		 * 
		 * if (workflowData.getPayloadType().equals("JCR_PATH")) { Session session =
		 * workflowSession.adaptTo(Session.class); String path =
		 * workflowData.getPayload().toString() + "/jcr:content"; Node node = (Node)
		 * session.getItem(path);
		 * 
		 * String[] processArgs = dataMap.get("PROCESS_ARGS",
		 * "string").toString().split(","); for (String wfArgs : processArgs) { String[]
		 * args = wfArgs.split(":"); String prop = args[0]; String value = args[1]; if
		 * (node != null) { node.setProperty(prop, value); } } }
		 * 
		 * } catch (Exception e) { // TODO: handle exception }
		 */

	}

}
