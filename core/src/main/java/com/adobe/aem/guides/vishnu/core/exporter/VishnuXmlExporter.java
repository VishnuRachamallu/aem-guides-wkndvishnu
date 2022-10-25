package com.adobe.aem.guides.vishnu.core.exporter;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.sling.models.export.spi.ModelExporter;
import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;

@Component(service = ModelExporter.class)
public class VishnuXmlExporter implements ModelExporter {

	@Override
	public <T> T export(Object arg0, Class<T> arg1, Map<String, String> arg2) throws ExportException {
		StringWriter stringWriter=new StringWriter();
		try {
			JAXBContext jaxbContext=JAXBContext.newInstance(arg0.getClass());
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(arg0, stringWriter);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (T)stringWriter.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "VishnuXML";
	}

	@Override
	public boolean isSupported(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
