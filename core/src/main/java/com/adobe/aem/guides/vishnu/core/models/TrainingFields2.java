package com.adobe.aem.guides.vishnu.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.vishnu.core.models.interfa.TrainingFields2Interface;

@Model(adaptables =Resource.class,
adapters = TrainingFields2Interface.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class TrainingFields2 implements TrainingFields2Interface{

	@Inject
	private String  title1;
	@Inject
	private String  textArea;
	@Inject
	private String  type;
	@Inject
	private String  typesone;
	@Inject
	private String  linkURL;
	@Inject
	private String  checkbox1;
	@Inject
	private String  password;
	@Inject
	private String  colorfield;
	@Inject
	private String  datepicker;
	@Inject
	private String  dropdown;
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title1;
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
		return dropdown;
	}
	///test
}

