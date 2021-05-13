package com.wipro.iaf.emms.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusAcmBuildItemBean {
	private String name;
	private String plusAcmBuildItemId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlusAcmBuildItemId() {
		return plusAcmBuildItemId;
	}
	public void setPlusAcmBuildItemId(String plusAcmBuildItemId) {
		this.plusAcmBuildItemId = plusAcmBuildItemId;
	}
	@Override
	public String toString() {
		return "PlusAcmBuildItemBean [name=" + name + ", plusAcmBuildItemId=" + plusAcmBuildItemId + "]";
	}
	
	

}
