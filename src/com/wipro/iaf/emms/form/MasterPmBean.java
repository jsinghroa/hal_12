package com.wipro.iaf.emms.form;

import java.util.ArrayList;

public class MasterPmBean {
	private String frequency;  
	private String frequnit;
	private String masterPmNum;
	private String worktype;
	ArrayList<MasterPmMeterBean> masterPmMeterBeanList;
	ArrayList<PlusaMasterPmMeteriter> plusaMasterPmMeteriterList;
	
	ArrayList<PlusaMasterPmTimeBean> plusaMasterPmTimeBeanList;
	
	

	public String getFrequnit() {
		return frequnit;
	}

	public void setFrequnit(String frequnit) {
		this.frequnit = frequnit;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getMasterPmNum() {
		return masterPmNum;
	}

	public void setMasterPmNum(String masterPmNum) {
		this.masterPmNum = masterPmNum;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public ArrayList<MasterPmMeterBean> getMasterPmMeterBeanList() {
		return masterPmMeterBeanList;
	}

	public void setMasterPmMeterBeanList(
			ArrayList<MasterPmMeterBean> masterPmMeterBeanList) {
		this.masterPmMeterBeanList = masterPmMeterBeanList;
	}

	public ArrayList<PlusaMasterPmTimeBean> getPlusaMasterPmTimeBeanList() {
		return plusaMasterPmTimeBeanList;
	}

	public void setPlusaMasterPmTimeBeanList(
			ArrayList<PlusaMasterPmTimeBean> plusaMasterPmTimeBeanList) {
		this.plusaMasterPmTimeBeanList = plusaMasterPmTimeBeanList;
	}
	
	
	public ArrayList<PlusaMasterPmMeteriter> getPlusaMasterPmMeteriterList() {
		return plusaMasterPmMeteriterList;
	}

	public void setPlusaMasterPmMeteriterList(
			ArrayList<PlusaMasterPmMeteriter> plusaMasterPmMeteriterList) {
		this.plusaMasterPmMeteriterList = plusaMasterPmMeteriterList;
	}

	@Override
	public String toString() {
		return "MasterPmBean [frequency=" + frequency + ", frequnit="
				+ frequnit + ", masterPmNum=" + masterPmNum + ", worktype="
				+ worktype + ", masterPmMeterBeanList=" + masterPmMeterBeanList
				+ ", plusaMasterPmMeteriterList=" + plusaMasterPmMeteriterList
				+ ", plusaMasterPmTimeBeanList=" + plusaMasterPmTimeBeanList
				+ "]";
	}

	



	
	
	
	
	

}
