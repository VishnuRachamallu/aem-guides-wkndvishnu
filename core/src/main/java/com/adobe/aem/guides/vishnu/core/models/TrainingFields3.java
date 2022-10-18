package com.adobe.aem.guides.vishnu.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.vishnu.core.models.interfa.TrainingFields2Interface;
import com.adobe.cq.wcm.core.components.models.Page;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = TrainingFields2Interface.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TrainingFields3 implements TrainingFields2Interface{

	@ScriptVariable
	Page page;
	
	@RequestAttribute(name = "var1")
	private String v1="fdfn"; 
	
	
	@Inject @Via("resource")
	@Named("title1")
	private String  titleVishnu;
	@Inject @Via("resource")
	@Default(values = "This is text area . you can add more lines")
	private String  textArea;
	@Inject @Via("resource")
	private String  type;
	@Inject @Via("resource")
	private String  typesone;
	@Inject @Via("resource")
	private String  linkURL;
	@Inject @Via("resource")
	private String  checkbox1;
	@Inject @Via("resource")
	private String  password;
	@Inject @Via("resource")
	private String  colorfield;
	@Inject @Via("resource")
	private String  datepicker;
	@ValueMapValue @Named("dropdown")       // inject and via not required 
	private String  dropDOWN;
	
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return titleVishnu;
	}
	@Override
	public String getTextArea() {
		// TODO Auto-generated method stub
		return textArea;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	@Override
	public String getTypesome() {
		// TODO Auto-generated method stub
		return typesone;
	}
	@Override
	public String getLinkURL() {
		// TODO Auto-generated method stub
		return linkURL;
	}
	@Override
	public String getCheckbox1() {
		// TODO Auto-generated method stub
		return checkbox1;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getColourField() {
		// TODO Auto-generated method stub
		return colorfield;
	}
	@Override
	public String getDatePicker() {
		// TODO Auto-generated method stub
		return datepicker;
	}
	@Override
	public String getDropDown() {
		// TODO Auto-generated method stub
		return dropDOWN;
	}
	
	public String getV1() {
		// TODO Auto-generated method stub
		return v1;
	}
	
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return page.getTitle();
	}
}

