package com.wipro.iaf.emms.form;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MeterDetailsForm {

	private String installedPN;// cm_item
	private String installedPartDescription;
	private String installedSN;
	private String meterName;// meter_name
	private String installationDate;
	private String existingCount;
	private String currentCount;
	private String ExAstCurrentCount;
	private String ExAstInstallCount;
	private String ExAstInstallCountHMS;
	private String ExAstCurrentCount_HMS;
	private String uom;
	private String existingInstalledPn;
	private String existingInstalledSN;
	private String error;
	private String errorDescription;
	private String recordRowId;

	public MeterDetailsForm() {

	}

	public String getExistingInstalledSN() {
		return existingInstalledSN;
	}

	public void setExistingInstalledSN(String existingInstalledSN) {
		this.existingInstalledSN = existingInstalledSN;
	}

	public String getExistingInstalledPn() {
		return existingInstalledPn;
	}

	public void setExistingInstalledPn(String existingInstalledPn) {
		this.existingInstalledPn = existingInstalledPn;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public String getExAstCurrentCount() {
		return ExAstCurrentCount;
	}

	public void setExAstCurrentCount(String exAstCurrentCount) {
		ExAstCurrentCount = exAstCurrentCount;
	}

	public String getExAstInstallCount() {
		return ExAstInstallCount;
	}

	public void setExAstInstallCount(String exAstInstallCount) {
		ExAstInstallCount = exAstInstallCount;
	}

	public String getExAstInstallCountHMS() {
		return ExAstInstallCountHMS;
	}

	public void setExAstInstallCountHMS(String exAstInstallCountHMS) {
		ExAstInstallCountHMS = exAstInstallCountHMS;
	}

	public String getExAstCurrentCount_HMS() {
		return ExAstCurrentCount_HMS;
	}

	public void setExAstCurrentCount_HMS(String exAstCurrentCount_HMS) {
		ExAstCurrentCount_HMS = exAstCurrentCount_HMS;
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

	public String getInstalledPartDescription() {
		return installedPartDescription;
	}

	public void setInstalledPartDescription(String installedPartDescription) {
		this.installedPartDescription = installedPartDescription;
	}

	public String getInstalledSN() {
		return installedSN;
	}

	public void setInstalledSN(String installedSN) {
		this.installedSN = installedSN;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public String getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getExistingCount() {
		return existingCount;
	}

	public void setExistingCount(String existingCount) {
		this.existingCount = existingCount;
	}

	@Override
	public String toString() {
		return "MeterDetailsForm [installedPN=" + installedPN + ", installedPartDescription=" + installedPartDescription
				+ ", installedSN=" + installedSN + ", meterName=" + meterName + ", installationDate=" + installationDate
				+ ", existingCount=" + existingCount + ", currentCount=" + currentCount + ", ExAstCurrentCount="
				+ ExAstCurrentCount + ", ExAstInstallCount=" + ExAstInstallCount + ", ExAstInstallCountHMS="
				+ ExAstInstallCountHMS + ", ExAstCurrentCount_HMS=" + ExAstCurrentCount_HMS + ", uom=" + uom
				+ ", existingInstalledPn=" + existingInstalledPn + ", existingInstalledSN=" + existingInstalledSN
				+ ", error=" + error + ", errorDescription=" + errorDescription + ", recordRowId=" + recordRowId + "]";
	}

}
