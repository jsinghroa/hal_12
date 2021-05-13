package com.wipro.iaf.emms.form;

public class HalCatMeterViewBean {
	private  String measureUnitId;
	private  String meterName;
	private  String plusAcaCatId;
	public String getMeasureUnitId() {
		return measureUnitId;
	}
	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
	}
	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public String getPlusAcaCatId() {
		return plusAcaCatId;
	}
	public void setPlusAcaCatId(String plusAcaCatId) {
		this.plusAcaCatId = plusAcaCatId;
	}
	@Override
	public String toString() {
		return "HalCatMeterViewBean [measureUnitId=" + measureUnitId
				+ ", meterName=" + meterName + ", plusAcaCatId=" + plusAcaCatId
				+ "]";
	}
	
	
	

}
