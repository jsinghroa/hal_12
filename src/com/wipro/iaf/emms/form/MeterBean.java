package com.wipro.iaf.emms.form;

public class MeterBean {
	private String measureUnitId;

	public String getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
	}

	@Override
	public String toString() {
		return "MeterBean [measureUnitId=" + measureUnitId + "]";
	}
	
	
}
