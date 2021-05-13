package com.wipro.iaf.emms.form;

import java.util.ArrayList;

public class MasterPmMeterBean {
	private String frequency;
	private String meterName;
	ArrayList<MeterBean> MeterBeanList;
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public ArrayList<MeterBean> getMeterBeanList() {
		return MeterBeanList;
	}
	public void setMeterBeanList(ArrayList<MeterBean> meterBeanList) {
		MeterBeanList = meterBeanList;
	}
	@Override
	public String toString() {
		return "MasterPmMeterBean [frequency=" + frequency + ", meterName="
				+ meterName + ", MeterBeanList=" + MeterBeanList + "]";
	}
	
	
	

}
