package com.wipro.iaf.emms.form;

import java.util.ArrayList;

public class ModelBean {
	
	private  String iafEmmsHalTrans;
	private  String iafAssetNum;
	private  String iafAssetHierarchyRemoved;
	private  String iafCmItem;
	private  String iafConfigId;
	private  String iafEmmsHalTransId;
	private  String iafLocation;
	private  String iafModel;
	private  String iafModelId;
	private  String iafRecordId;
	private  String iafSerialNum;
	private  String iafStatus;
	private  String iafVariation;
	private  String langCode;
	private String runningId;
	ArrayList<HalModelViewBean> halModelViewBeanList;
	public String getIafEmmsHalTrans() {
		return iafEmmsHalTrans;
	}
	public void setIafEmmsHalTrans(String iafEmmsHalTrans) {
		this.iafEmmsHalTrans = iafEmmsHalTrans;
	}
	public String getIafAssetNum() {
		return iafAssetNum;
	}
	public void setIafAssetNum(String iafAssetNum) {
		this.iafAssetNum = iafAssetNum;
	}
	public String getIafAssetHierarchyRemoved() {
		return iafAssetHierarchyRemoved;
	}
	public void setIafAssetHierarchyRemoved(String iafAssetHierarchyRemoved) {
		this.iafAssetHierarchyRemoved = iafAssetHierarchyRemoved;
	}
	public String getIafCmItem() {
		return iafCmItem;
	}
	public void setIafCmItem(String iafCmItem) {
		this.iafCmItem = iafCmItem;
	}
	public String getIafConfigId() {
		return iafConfigId;
	}
	public void setIafConfigId(String iafConfigId) {
		this.iafConfigId = iafConfigId;
	}
	public String getIafEmmsHalTransId() {
		return iafEmmsHalTransId;
	}
	public void setIafEmmsHalTransId(String iafEmmsHalTransId) {
		this.iafEmmsHalTransId = iafEmmsHalTransId;
	}
	public String getIafLocation() {
		return iafLocation;
	}
	public void setIafLocation(String iafLocation) {
		this.iafLocation = iafLocation;
	}
	public String getIafModel() {
		return iafModel;
	}
	public void setIafModel(String iafModel) {
		this.iafModel = iafModel;
	}
	public String getIafModelId() {
		return iafModelId;
	}
	public void setIafModelId(String iafModelId) {
		this.iafModelId = iafModelId;
	}
	public String getIafRecordId() {
		return iafRecordId;
	}
	public void setIafRecordId(String iafRecordId) {
		this.iafRecordId = iafRecordId;
	}
	public String getIafSerialNum() {
		return iafSerialNum;
	}
	public void setIafSerialNum(String iafSerialNum) {
		this.iafSerialNum = iafSerialNum;
	}
	public String getIafStatus() {
		return iafStatus;
	}
	public void setIafStatus(String iafStatus) {
		this.iafStatus = iafStatus;
	}
	public String getIafVariation() {
		return iafVariation;
	}
	public void setIafVariation(String iafVariation) {
		this.iafVariation = iafVariation;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public ArrayList<HalModelViewBean> getHalModelViewBeanList() {
		return halModelViewBeanList;
	}
	public void setHalModelViewBeanList(
			ArrayList<HalModelViewBean> halModelViewBeanList) {
		this.halModelViewBeanList = halModelViewBeanList;
	}
	
	public String getRunningId() {
		return runningId;
	}
	public void setRunningId(String runningId) {
		this.runningId = runningId;
	}
	@Override
	public String toString() {
		return "ModelBean [iafEmmsHalTrans=" + iafEmmsHalTrans
				+ ", iafAssetNum=" + iafAssetNum
				+ ", iafAssetHierarchyRemoved=" + iafAssetHierarchyRemoved
				+ ", iafCmItem=" + iafCmItem + ", iafConfigId=" + iafConfigId
				+ ", iafEmmsHalTransId=" + iafEmmsHalTransId + ", iafLocation="
				+ iafLocation + ", iafModel=" + iafModel + ", iafModelId="
				+ iafModelId + ", iafRecordId=" + iafRecordId
				+ ", iafSerialNum=" + iafSerialNum + ", iafStatus=" + iafStatus
				+ ", iafVariation=" + iafVariation + ", langCode=" + langCode
				+ ", runningId=" + runningId + ", halModelViewBeanList="
				+ halModelViewBeanList + "]";
	}
	
	
	
	
	
}
