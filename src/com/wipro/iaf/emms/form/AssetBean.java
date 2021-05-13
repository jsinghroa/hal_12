package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AssetBean {
	
	
	private String iaf_AssetNum;
	private String iaf_AssetHierarchyRemoved;
	private String iaf_CmItem;
	private String iaf_ConfigId;
	private String iaf_EmmsHalTransId;
	private String inductionDate;
	private String iaf_Location;
	private String iaf_Model;
	private String iaf_ModelId;
	private String iaf_RecordId;
	private String iaf_RecordType; 
	private String iaf_SerialNum;
	private String iaf_Status;
	private String iaf_Variation;
	
	ArrayList<AssetHierarchyViewBean> assetHierarchyViewList;
	
	public String getIaf_AssetNum() {
		return iaf_AssetNum;
	}
	public void setIaf_AssetNum(String iaf_AssetNum) {
		this.iaf_AssetNum = iaf_AssetNum;
	}
	
	public ArrayList<AssetHierarchyViewBean> getAssetHierarchyViewList() {
		return assetHierarchyViewList;
	}
	public void setAssetHierarchyViewList(
			ArrayList<AssetHierarchyViewBean> assetHierarchyViewList) {
		this.assetHierarchyViewList = assetHierarchyViewList;
	}
	public String getIaf_AssetHierarchyRemoved() {
		return iaf_AssetHierarchyRemoved;
	}
	public void setIaf_AssetHierarchyRemoved(String iaf_AssetHierarchyRemoved) {
		this.iaf_AssetHierarchyRemoved = iaf_AssetHierarchyRemoved;
	}
	public String getIaf_CmItem() {
		return iaf_CmItem;
	}
	public void setIaf_CmItem(String iaf_CmItem) {
		this.iaf_CmItem = iaf_CmItem;
	}
	public String getIaf_ConfigId() {
		return iaf_ConfigId;
	}
	public void setIaf_ConfigId(String iaf_ConfigId) {
		this.iaf_ConfigId = iaf_ConfigId;
	}
	public String getIaf_EmmsHalTransId() {
		return iaf_EmmsHalTransId;
	}
	public void setIaf_EmmsHalTransId(String iaf_EmmsHalTransId) {
		this.iaf_EmmsHalTransId = iaf_EmmsHalTransId;
	}
	
	public String getInductionDate() {
		return inductionDate;
	}
	public void setInductionDate(String inductionDate) {
		this.inductionDate = inductionDate;
	}
	public String getIaf_Location() {
		return iaf_Location;
	}
	public void setIaf_Location(String iaf_Location) {
		this.iaf_Location = iaf_Location;
	}
	public String getIaf_Model() {
		return iaf_Model;
	}
	public void setIaf_Model(String iaf_Model) {
		this.iaf_Model = iaf_Model;
	}
	public String getIaf_ModelId() {
		return iaf_ModelId;
	}
	public void setIaf_ModelId(String iaf_ModelId) {
		this.iaf_ModelId = iaf_ModelId;
	}
	public String getIaf_RecordId() {
		return iaf_RecordId;
	}
	public void setIaf_RecordId(String iaf_RecordId) {
		this.iaf_RecordId = iaf_RecordId;
	}
	public String getIaf_RecordType() {
		return iaf_RecordType;
	}
	public void setIaf_RecordType(String iaf_RecordType) {
		this.iaf_RecordType = iaf_RecordType;
	}
	public String getIaf_SerialNum() {
		return iaf_SerialNum;
	}
	public void setIaf_SerialNum(String iaf_SerialNum) {
		this.iaf_SerialNum = iaf_SerialNum;
	}
	public String getIaf_Status() {
		return iaf_Status;
	}
	public void setIaf_Status(String iaf_Status) {
		this.iaf_Status = iaf_Status;
	}
	public String getIaf_Variation() {
		return iaf_Variation;
	}
	public void setIaf_Variation(String iaf_Variation) {
		this.iaf_Variation = iaf_Variation;
	}
	@Override
	public String toString() {
		return "AssetBean [iaf_AssetNum=" + iaf_AssetNum
				+ ", iaf_AssetHierarchyRemoved=" + iaf_AssetHierarchyRemoved
				+ ", iaf_CmItem=" + iaf_CmItem + ", iaf_ConfigId="
				+ iaf_ConfigId + ", iaf_EmmsHalTransId=" + iaf_EmmsHalTransId
				+ ", inductionDate=" + inductionDate + ", iaf_Location="
				+ iaf_Location + ", iaf_Model=" + iaf_Model + ", iaf_ModelId="
				+ iaf_ModelId + ", iaf_RecordId=" + iaf_RecordId
				+ ", iaf_RecordType=" + iaf_RecordType + ", iaf_SerialNum="
				+ iaf_SerialNum + ", iaf_Status=" + iaf_Status
				+ ", iaf_Variation=" + iaf_Variation
				+ ", assetHierarchyViewList=" + assetHierarchyViewList + "]";
	}
	
	
	
}
