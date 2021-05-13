package com.wipro.iaf.emms.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FlbDetailForm {
	

		private String sortieNo;
	  private String sortieDate;
	  private String ETD;
	 
	  private String duration;
	  private String flightType;
	  private String sortieStatus;
	  private String statusDate;
	  private String changedBy;
	  private String remarks;
	  private String reason;
	  private String accept;
	  private String reject;
	  private String error;
	  private String errordesc;
	  private String flbNo;
	  private String flightNumber;
	  private String flightData;
	  private String departureTime;
	  private String arrivalTime;
	  private String flightHours;
	  private String status;
	  
	  private String meterDetails;
	  private String lcn;
	  private String installedpn;
	  private String installedsn;
	  private String meter;
	  private String description;
	  private String readingType;
	  private String updatedby;
	  private String meterReading;
	  
	  
	  
	  
	
	public String getSortieNo() {
		return sortieNo;
	}
	public void setSortieNo(String sortieNo) {
		this.sortieNo = sortieNo;
	}
	public String getSortieDate() {
		return sortieDate;
	}
	public void setSortieDate(String sortieDate) {
		this.sortieDate = sortieDate;
	}
	public String getETD() {
		return ETD;
	}
	public void setETD(String eTD) {
		ETD = eTD;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getSortieStatus() {
		return sortieStatus;
	}
	public void setSortieStatus(String sortieStatus) {
		this.sortieStatus = sortieStatus;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getReject() {
		return reject;
	}
	public void setReject(String reject) {
		this.reject = reject;
	}
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
	public String getFlbNo() {
		return flbNo;
	}
	public void setFlbNo(String flbNo) {
		this.flbNo = flbNo;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightData() {
		return flightData;
	}
	public void setFlightData(String flightData) {
		this.flightData = flightData;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getFlightHours() {
		return flightHours;
	}
	public void setFlightHours(String flightHours) {
		this.flightHours = flightHours;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "FlbDetailForm [sortieNo=" + sortieNo + ", sortieDate="
				+ sortieDate + ", ETD=" + ETD + ", duration=" + duration
				+ ", flightType=" + flightType + ", sortieStatus="
				+ sortieStatus + ", statusDate=" + statusDate + ", changedBy="
				+ changedBy + ", remarks=" + remarks + ", reason=" + reason
				+ ", accept=" + accept + ", reject=" + reject + ", error="
				+ error + ", errordesc=" + errordesc + ", flbNo=" + flbNo
				+ ", flightNumber=" + flightNumber + ", flightData="
				+ flightData + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", flightHours="
				+ flightHours + ", status=" + status + ", meterDetails="
				+ meterDetails + ", lcn=" + lcn + ", installedpn="
				+ installedpn + ", installedsn=" + installedsn + ", meter="
				+ meter + ", description=" + description + ", readingType="
				+ readingType + ", updatedby=" + updatedby + ", meterReading="
				+ meterReading + "]";
	}
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
