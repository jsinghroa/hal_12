package com.wipro.iaf.emms.form;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Amac {

	private int id;
	private String name;
	private Model model;
	
	public Amac()
	{
		
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Model getModel() {
		return model;
	}

	@XmlElement(name="Model")
	public void setModel(Model model) {
		this.model = model;
	}

	public Amac(int id, String name,Model m) {
		super();
		this.id = id;
		this.name = name;
		this.model=m;
	}
	
	
	
	
	
	
	
}
