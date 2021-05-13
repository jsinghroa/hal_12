package com.wipro.iaf.emms.form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Model {

	private String salary;
	private String age;
	private Meter meter;
	
	
	public Model()
	{
		
	}

	
	

	public Model(String salary, String age,Meter meter) {
		super();
		this.salary = salary;
		this.age = age;
		this.meter=meter;
	}



	
	public Meter getMeter() {
		return meter;
	}



	@XmlElement
	public void setMeter(Meter meter) {
		this.meter = meter;
	}




	@XmlElement
	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}

	@XmlElement
	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}
	
	
}
