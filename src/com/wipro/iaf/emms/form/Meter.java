package com.wipro.iaf.emms.form;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Meter")
public class Meter {

	private String meterName;
	private List<String> meterValues;
	
	public Meter(){
		
	}
	
	
	
	public Meter(String meterName, List<String> meterValues) {
		super();
		this.meterName = meterName;
		this.meterValues = meterValues;
	}



	public String getMeterName() {
		return meterName;
	}
	
	@XmlElement
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public List<String> getMeterValues() {
		return meterValues;
	}
	
	@XmlElement
	public void setMeterValues(List<String> meterValues) {
		this.meterValues = meterValues;
	}
	
	
	
	
	
	
	
	
	
}
