package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusAcmBuildBean {
	private String lcn;
	private String plusAcmBuildId;
	ArrayList<PlusAcmBuildItemBean> plusAcmBuildItemBeanList;
	public String getLcn() {
		return lcn;
	}
	public void setLcn(String lcn) {
		this.lcn = lcn;
	}
	public String getPlusAcmBuildId() {
		return plusAcmBuildId;
	}
	public void setPlusAcmBuildId(String plusAcmBuildId) {
		this.plusAcmBuildId = plusAcmBuildId;
	}
	public ArrayList<PlusAcmBuildItemBean> getPlusAcmBuildItemBeanList() {
		return plusAcmBuildItemBeanList;
	}
	public void setPlusAcmBuildItemBeanList(ArrayList<PlusAcmBuildItemBean> plusAcmBuildItemBeanList) {
		this.plusAcmBuildItemBeanList = plusAcmBuildItemBeanList;
	}
	@Override
	public String toString() {
		return "PlusAcmBuildBean [lcn=" + lcn + ", plusAcmBuildId=" + plusAcmBuildId + ", plusAcmBuildItemBeanList="
				+ plusAcmBuildItemBeanList + "]";
	}
	
	

}
