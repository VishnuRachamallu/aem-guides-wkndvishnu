package com.adobe.aem.guides.vishnu.core.serviceinterface;

import java.util.Iterator;

import com.day.cq.wcm.api.Page;

public interface DemoServiceInterf {
	public String getPagesWithString();

	public String getServiceData();

	public Iterator<Page> getPagesWithItr();
}
