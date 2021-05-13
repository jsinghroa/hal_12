package com.wipro.iaf.emms.form;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FlbPostFlightDataForm {

	private String flbNo;
	private String flightNumber;
	private String recordId;
	private String flightDate;
	private String departureTime;
	private String arrivalTime;
	private String flightHours;
	private String flightType;
	private String status;
	private String creationDate;
	private String sortieNumber;
	private List<String> listSortieNumber;
	private List<String> statuses;
	private List<String> flightTypes;
	private String recordRowId;
	private String errorDesc;
	private String error;
	
	
	
	
	public List<String> getListSortieNumber() {
		return listSortieNumber;
	}
	public void setListSortieNumber(List<String> listSortieNumber) {
		this.listSortieNumber = listSortieNumber;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getFlbNo() {
		return flbNo;
	}
	public void setFlbNo(String flbNo) {
		this.flbNo = flbNo;
	}
	
	
	public List<String> getFlightTypes() {
		return flightTypes;
	}
	public void setFlightTypes(List<String> flightTypes) {
		this.flightTypes = flightTypes;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
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
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public String getRecordRowId() {
		return recordRowId;
	}
	public void setRecordRowId(String recordRowId) {
		this.recordRowId = recordRowId;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getSortieNumber() {
		return sortieNumber;
	}
	public void setSortieNumber(String sortieNumber) {
		this.sortieNumber = sortieNumber;
	}
	public List<String> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}
	@Override
	public String toString() {
		return "FlbPostFlightDataForm [flbNo=" + flbNo + ", flightNumber=" + flightNumber + ", recordId=" + recordId
				+ ", flightDate=" + flightDate + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", flightHours=" + flightHours + ", flightType=" + flightType + ", status=" + status
				+ ", creationDate=" + creationDate + ", sortieNumber=" + sortieNumber + ", statuses=" + statuses
				+ ", flightTypes=" + flightTypes + ", recordRowId=" + recordRowId + ", errorDesc=" + errorDesc
				+ ", error=" + error + "]";
	}
	
	
	
	
	
	
	
}
