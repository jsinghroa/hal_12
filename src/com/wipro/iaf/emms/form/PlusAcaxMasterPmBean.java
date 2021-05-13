package com.wipro.iaf.emms.form;

import java.util.ArrayList;

public class PlusAcaxMasterPmBean {
	private String plusAcaxMasterPmId;
	ArrayList<MasterPmBean> masterPmBeanList;
	public String getPlusAcaxMasterPmId() {
		return plusAcaxMasterPmId;
	}
	public void setPlusAcaxMasterPmId(String plusAcaxMasterPmId) {
		this.plusAcaxMasterPmId = plusAcaxMasterPmId;
	}
	public ArrayList<MasterPmBean> getMasterPmBeanList() {
		return masterPmBeanList;
	}
	public void setMasterPmBeanList(ArrayList<MasterPmBean> masterPmBeanList) {
		this.masterPmBeanList = masterPmBeanList;
	}
	@Override
	public String toString() {
		return "PlusAcaxMasterPmBean [plusAcaxMasterPmId=" + plusAcaxMasterPmId
				+ ", masterPmBeanList=" + masterPmBeanList + "]";
	}
	
	
	

}
