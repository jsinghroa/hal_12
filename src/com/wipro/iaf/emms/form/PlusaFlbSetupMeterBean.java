package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusaFlbSetupMeterBean {
	
	private String buildId;
	private String flbType;
	private String iaf_FdrParameter;
	private String iaf_ModifiedBy;
	private String iaf_Sequence;
	private String isDefault;
	private String meterName;
	private String meterReading;
	private String orgId;
	private String plusaFlbSetupMeterId;
	private String readingType;
	private String revisionNum;
	private String whereUsed;
	ArrayList<PlusAcmBuildBean> plusAcmBuildBeanList;
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getFlbType() {
		return flbType;
	}
	public void setFlbType(String flbType) {
		this.flbType = flbType;
	}
	public String getIaf_FdrParameter() {
		return iaf_FdrParameter;
	}
	public void setIaf_FdrParameter(String iaf_FdrParameter) {
		this.iaf_FdrParameter = iaf_FdrParameter;
	}
	public String getIaf_ModifiedBy() {
		return iaf_ModifiedBy;
	}
	public void setIaf_ModifiedBy(String iaf_ModifiedBy) {
		this.iaf_ModifiedBy = iaf_ModifiedBy;
	}
	public String getIaf_Sequence() {
		return iaf_Sequence;
	}
	public void setIaf_Sequence(String iaf_Sequence) {
		this.iaf_Sequence = iaf_Sequence;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public String getMeterReading() {
		return meterReading;
	}
	public void setMeterReading(String meterReading) {
		this.meterReading = meterReading;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPlusaFlbSetupMeterId() {
		return plusaFlbSetupMeterId;
	}
	public void setPlusaFlbSetupMeterId(String plusaFlbSetupMeterId) {
		this.plusaFlbSetupMeterId = plusaFlbSetupMeterId;
	}
	public String getReadingType() {
		return readingType;
	}
	public void setReadingType(String readingType) {
		this.readingType = readingType;
	}
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public String getWhereUsed() {
		return whereUsed;
	}
	public void setWhereUsed(String whereUsed) {
		this.whereUsed = whereUsed;
	}
	public ArrayList<PlusAcmBuildBean> getPlusAcmBuildBeanList() {
		return plusAcmBuildBeanList;
	}
	public void setPlusAcmBuildBeanList(ArrayList<PlusAcmBuildBean> plusAcmBuildBeanList) {
		this.plusAcmBuildBeanList = plusAcmBuildBeanList;
	}
	@Override
	public String toString() {
		return "PlusaFlbSetupMeterBean [buildId=" + buildId + ", flbType=" + flbType + ", iaf_FdrParameter="
				+ iaf_FdrParameter + ", iaf_ModifiedBy=" + iaf_ModifiedBy + ", iaf_Sequence=" + iaf_Sequence
				+ ", isDefault=" + isDefault + ", meterName=" + meterName + ", meterReading=" + meterReading
				+ ", orgId=" + orgId + ", plusaFlbSetupMeterId=" + plusaFlbSetupMeterId + ", readingType=" + readingType
				+ ", revisionNum=" + revisionNum + ", whereUsed=" + whereUsed + ", plusAcmBuildBeanList="
				+ plusAcmBuildBeanList + "]";
	}
	
}
