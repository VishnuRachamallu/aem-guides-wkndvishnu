package com.adobe.aem.guides.vishnu.core.exporter;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.sling.models.export.spi.ModelExporter;
import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;

@Component(service = ModelExporter.class)
public class VishnuXmlExporter implements ModelExporter {

    @Override
    public <T> T export(Object model, Class<T> aClass, Map<String, String> options) throws ExportException {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(model, stringWriter);
        } catch (JAXBException e) {
            
        }
        return (T) stringWriter.toString();
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "vishnuxml";
	}

	@Override
	public boolean isSupported(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
