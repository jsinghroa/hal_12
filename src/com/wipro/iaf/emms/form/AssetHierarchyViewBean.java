package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AssetHierarchyViewBean {
	
	
	private String aircraft;
	private String assetId;
	private String assetNum;
	private String buildItemId;
	private String cmItem;
	private String configId;
	private String endAsset;
	private String endAssetId;
	private String iafRecordId;
	private String indenture;
	private String indicator;
	private String isDefault;
	private String itemNum;
	private String label;
	private String lcn;
	private String model;
	private String modelId;
	private String name;
	private String nonTracked;
	private String onTime;
	private String parentAssetId;
	private String plusAcmBuildId;
	private String plusAsaOnoffId;
	private String  position;
	private String serialNum;
	private String variation;
	
	ArrayList<AssetMeterVwBean> assetMeterVWList;
	
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	public String getBuildItemId() {
		return buildItemId;
	}
	public void setBuildItemId(String buildItemId) {
		this.buildItemId = buildItemId;
	}
	public String getCmItem() {
		return cmItem;
	}
	public void setCmItem(String cmItem) {
		this.cmItem = cmItem;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getEndAsset() {
		return endAsset;
	}
	public void setEndAsset(String endAsset) {
		this.endAsset = endAsset;
	}
	public String getEndAssetId() {
		return endAssetId;
	}
	public void setEndAssetId(String endAssetId) {
		this.endAssetId = endAssetId;
	}
	public String getIafRecordId() {
		return iafRecordId;
	}
	public void setIafRecordId(String iafRecordId) {
		this.iafRecordId = iafRecordId;
	}
	public String getIndenture() {
		return indenture;
	}
	public void setIndenture(String indenture) {
		this.indenture = indenture;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLcn() {
		return lcn;
	}
	public void setLcn(String lcn) {
		this.lcn = lcn;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNonTracked() {
		return nonTracked;
	}
	public void setNonTracked(String nonTracked) {
		this.nonTracked = nonTracked;
	}
	public String getOnTime() {
		return onTime;
	}
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
	public String getParentAssetId() {
		return parentAssetId;
	}
	public void setParentAssetId(String parentAssetId) {
		this.parentAssetId = parentAssetId;
	}
	public String getPlusAcmBuildId() {
		return plusAcmBuildId;
	}
	public void setPlusAcmBuildId(String plusAcmBuildId) {
		this.plusAcmBuildId = plusAcmBuildId;
	}
	public String getPlusAsaOnoffId() {
		return plusAsaOnoffId;
	}
	public void setPlusAsaOnoffId(String plusAsaOnoffId) {
		this.plusAsaOnoffId = plusAsaOnoffId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getVariation() {
		return variation;
	}
	public void setVariation(String variation) {
		this.variation = variation;
	}
	
	
	
	public ArrayList<AssetMeterVwBean> getAssetMeterVWList() {
		return assetMeterVWList;
	}
	public void setAssetMeterVWList(ArrayList<AssetMeterVwBean> assetMeterVWList) {
		this.assetMeterVWList = assetMeterVWList;
	}
	@Override
	public String toString() {
		return "AssetHierarchyViewBean [aircraft=" + aircraft + ", assetId="
				+ assetId + ", assetNum=" + assetNum + ", buildItemId="
				+ buildItemId + ", cmItem=" + cmItem + ", configId=" + configId
				+ ", endAsset=" + endAsset + ", endAssetId=" + endAssetId
				+ ", iafRecordId=" + iafRecordId + ", indenture=" + indenture
				+ ", indicator=" + indicator + ", isDefault=" + isDefault
				+ ", itemNum=" + itemNum + ", label=" + label + ", lcn=" + lcn
				+ ", model=" + model + ", modelId=" + modelId + ", name="
				+ name + ", nonTracked=" + nonTracked + ", onTime=" + onTime
				+ ", parentAssetId=" + parentAssetId + ", plusAcmBuildId="
				+ plusAcmBuildId + ", plusAsaOnoffId=" + plusAsaOnoffId
				+ ", position=" + position + ", serialNum=" + serialNum
				+ ", variation=" + variation + ", assetMeterVWList="
				+ assetMeterVWList + "]";
	}
	
	
	
	

}
