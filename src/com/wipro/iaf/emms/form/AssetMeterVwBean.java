package com.wipro.iaf.emms.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AssetMeterVwBean {
	
	private String currentValue;
	private String measureUnitId;
	private String meterName;
	private String plusaAnchorCount;
	private String plusaAnchorDate;
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
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
	public String getPlusaAnchorCount() {
		return plusaAnchorCount;
	}
	public void setPlusaAnchorCount(String plusaAnchorCount) {
		this.plusaAnchorCount = plusaAnchorCount;
	}
	public String getPlusaAnchorDate() {
		return plusaAnchorDate;
	}
	public void setPlusaAnchorDate(String plusaAnchorDate) {
		this.plusaAnchorDate = plusaAnchorDate;
	}
	@Override
	public String toString() {
		return "AssetMeterVwBean [currentValue=" + currentValue
				+ ", measureUnitId=" + measureUnitId + ", meterName="
				+ meterName + ", plusaAnchorCount=" + plusaAnchorCount
				+ ", plusaAnchorDate=" + plusaAnchorDate + "]";
	}
	
	
}
