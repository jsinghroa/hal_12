package com.wipro.iaf.emms.form;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FlbSortieArForm {
	private String sortieNo;
	private String sortieDate;
	private String ETD;
	private String etdDate;
	private String duration;
	private String flightType;
	private List<String> flightTypes;
	private String sortieStatus;
	private String statusDate;
	private String changedBy;
	private String remarks;
	private String reason;
	private String accept;
	private String reject;
	private String error;
	private String errordesc;
	private String recordId;
	private String flbNum;
	private String recordRowId;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getFlbNum() {
		return flbNum;
	}

	public void setFlbNum(String flbNum) {
		this.flbNum = flbNum;
	}

	public String getRecordRowId() {
		return recordRowId;
	}

	public void setRecordRowId(String recordRowId) {
		this.recordRowId = recordRowId;
	}

	public String getEtdDate() {
		return etdDate;
	}

	public void setEtdDate(String etdDate) {
		this.etdDate = etdDate;
	}

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

	public List<String> getFlightTypes() {
		return flightTypes;
	}

	public void setFlightTypes(List<String> flightTypes) {
		this.flightTypes = flightTypes;
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

	@Override
	public String toString() {
		return "FlbSortieArForm [sortieNo=" + sortieNo + ", sortieDate=" + sortieDate + ", ETD=" + ETD + ", etdDate="
				+ etdDate + ", duration=" + duration + ", flightType=" + flightType + ", flightTypes=" + flightTypes
				+ ", sortieStatus=" + sortieStatus + ", statusDate=" + statusDate + ", changedBy=" + changedBy
				+ ", remarks=" + remarks + ", reason=" + reason + ", accept=" + accept + ", reject=" + reject
				+ ", error=" + error + ", errordesc=" + errordesc + ", recordId=" + recordId + ", flbNum=" + flbNum
				+ ", recordRowId=" + recordRowId + "]";
	}

}
