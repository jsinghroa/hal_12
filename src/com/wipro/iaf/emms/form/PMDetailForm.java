package com.wipro.iaf.emms.form;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PMDetailForm {

	private String installedPN;
	private String installedPartDesc;
	private String installSerialNum;
	private String workType;
	private String mpmNum;
	private String mpmDescription;
	private String meterName;
	private int frequencyIteration;
	private String frequencyUnit;
	private String errorStatus;
	private String errorDesc;
	private String lastCompiledDate;
	private String nextDueDate;
	private BigDecimal lastCompiledValue;
	private BigDecimal nextDueValue;
	private String recordRowId;

	public PMDetailForm() {
	}

	public String getRecordRowId() {
		return recordRowId;
	}

	public void setRecordRowId(String recordRowId) {
		this.recordRowId = recordRowId;
	}

	public String getInstalledPN() {
		return installedPN;
	}

	public void setInstalledPN(String installedPN) {
		this.installedPN = installedPN;
	}

	public String getInstalledPartDesc() {
		return installedPartDesc;
	}

	public void setInstalledPartDesc(String installedPartDesc) {
		this.installedPartDesc = installedPartDesc;
	}

	public String getInstallSerialNum() {
		return installSerialNum;
	}

	public void setInstallSerialNum(String installSerialNum) {
		this.installSerialNum = installSerialNum;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getMpmNum() {
		return mpmNum;
	}

	public void setMpmNum(String mpmNum) {
		this.mpmNum = mpmNum;
	}

	public String getMpmDescription() {
		return mpmDescription;
	}

	public void setMpmDescription(String mpmDescription) {
		this.mpmDescription = mpmDescription;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public int getFrequencyIteration() {
		return frequencyIteration;
	}

	public void setFrequencyIteration(int frequencyIteration) {
		this.frequencyIteration = frequencyIteration;
	}

	public String getFrequencyUnit() {
		return frequencyUnit;
	}

	public void setFrequencyUnit(String frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getLastCompiledDate() {
		return lastCompiledDate;
	}

	public void setLastCompiledDate(String lastCompiledDate) {
		this.lastCompiledDate = lastCompiledDate;
	}

	public String getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(String nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	public BigDecimal getLastCompiledValue() {
		return lastCompiledValue;
	}

	public void setLastCompiledValue(BigDecimal lastCompiledValue) {
		this.lastCompiledValue = lastCompiledValue;
	}

	public BigDecimal getNextDueValue() {
		return nextDueValue;
	}

	public void setNextDueValue(BigDecimal nextDueValue) {
		this.nextDueValue = nextDueValue;
	}

	@Override
	public String toString() {
		return "PMDetailForm [installedPN=" + installedPN
				+ ", installedPartDesc=" + installedPartDesc
				+ ", installSerialNum=" + installSerialNum + ", workType="
				+ workType + ", mpmNum=" + mpmNum + ", mpmDescription="
				+ mpmDescription + ", meterName=" + meterName
				+ ", frequencyIteration=" + frequencyIteration
				+ ", frequencyUnit=" + frequencyUnit + ", errorStatus="
				+ errorStatus + ", errorDesc=" + errorDesc
				+ ", lastCompiledDate=" + lastCompiledDate + ", nextDueDate="
				+ nextDueDate + ", lastCompiledValue=" + lastCompiledValue
				+ ", nextDueValue=" + nextDueValue + ", recordRowId="
				+ recordRowId + "]";
	}

}
