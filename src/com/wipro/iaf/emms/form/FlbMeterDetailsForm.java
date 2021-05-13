package com.wipro.iaf.emms.form;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FlbMeterDetailsForm {
	private String meterDetails;
	private String lcn;
	private String installedpn;
	private String installedsn;
	private String meter;
	private String description;
	private String readingType;
	private String updatedby;
	private String meterReading;
	private String builditem;

	private String error;
	private String errordesc;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrordesc() {
		return errordesc;
	}
	public void setErrordesc(String errordesc) {
		this.errordesc = errordesc;
	}
	public String getMeterDetails() {
		return meterDetails;
	}
	public void setMeterDetails(String meterDetails) {
		this.meterDetails = meterDetails;
	}
	public String getLcn() {
		return lcn;
	}
	public void setLcn(String lcn) {
		this.lcn = lcn;
	}
	public String getInstalledpn() {
		return installedpn;
	}
	public void setInstalledpn(String installedpn) {
		this.installedpn = installedpn;
	}
	public String getInstalledsn() {
		return installedsn;
	}
	public void setInstalledsn(String installedsn) {
		this.installedsn = installedsn;
	}
	public String getMeter() {
		return meter;
	}
	public void setMeter(String meter) {
		this.meter = meter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReadingType() {
		return readingType;
	}
	public void setReadingType(String readingType) {
		this.readingType = readingType;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getMeterReading() {
		return meterReading;
	}
	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}
	public String getBuilditem() {
		return builditem;
	}
	public void setBuilditem(String builditem) {
		this.builditem = builditem;
	}	
}
