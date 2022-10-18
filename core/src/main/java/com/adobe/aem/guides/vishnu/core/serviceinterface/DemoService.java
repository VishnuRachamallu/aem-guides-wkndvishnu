package com.adobe.aem.guides.vishnu.core.serviceinterface;

import java.util.Iterator;

import com.day.cq.wcm.api.Page;

public interface DemoService {
	public Iterator<Page> getPages();

	public String getServiceData();
}
