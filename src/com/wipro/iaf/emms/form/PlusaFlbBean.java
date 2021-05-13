package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusaFlbBean {

	
	private String acStatus;
	private String assetNum;
	private String description;
	private String flbNum;
	private String flbType;
	private String iaf_hh_AcFlightHrs;
	private String model;
	private String orgId;
	private String plusaFlbId;
	private String revisionNum;
	private String siteId;
	private String status;
	private String statusDate;
	private String variation;
	ArrayList<PlusaFlbSetupBean> plusaFlbSetupBeanList;
	public String getAcStatus() {
		return acStatus;
	}
	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFlbNum() {
		return flbNum;
	}
	public void setFlbNum(String flbNum) {
		this.flbNum = flbNum;
	}
	public String getFlbType() {
		return flbType;
	}
	public void setFlbType(String flbType) {
		this.flbType = flbType;
	}
	public String getIaf_hh_AcFlightHrs() {
		return iaf_hh_AcFlightHrs;
	}
	public void setIaf_hh_AcFlightHrs(String iaf_hh_AcFlightHrs) {
		this.iaf_hh_AcFlightHrs = iaf_hh_AcFlightHrs;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPlusaFlbId() {
		return plusaFlbId;
	}
	public void setPlusaFlbId(String plusaFlbId) {
		this.plusaFlbId = plusaFlbId;
	}
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getVariation() {
		return variation;
	}
	public void setVariation(String variation) {
		this.variation = variation;
	}
	public ArrayList<PlusaFlbSetupBean> getPlusaFlbSetupBeanList() {
		return plusaFlbSetupBeanList;
	}
	public void setPlusaFlbSetupBeanList(ArrayList<PlusaFlbSetupBean> plusaFlbSetupBeanList) {
		this.plusaFlbSetupBeanList = plusaFlbSetupBeanList;
	}
	@Override
	public String toString() {
		return "PlusaFlbBean [acStatus=" + acStatus + ", assetNum=" + assetNum + ", description=" + description
				+ ", flbNum=" + flbNum + ", flbType=" + flbType + ", iaf_hh_AcFlightHrs=" + iaf_hh_AcFlightHrs
				+ ", model=" + model + ", orgId=" + orgId + ", plusaFlbId=" + plusaFlbId + ", revisionNum="
				+ revisionNum + ", siteId=" + siteId + ", status=" + status + ", statusDate=" + statusDate
				+ ", variation=" + variation + ", plusaFlbSetupBeanList=" + plusaFlbSetupBeanList + "]";
	}
	
	
	
}
