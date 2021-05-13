package com.wipro.iaf.emms.utility;
import java.math.BigInteger;
import java.util.Date;


public class XmlBean {

	  private String record_id;
	  private String assetId;
	  private String assetNum;
	  private String iafCdCts;
	  private String iafRemPartNum;
	  private String iafRemSerialNum;
	  private String label;
	  private String lcn;
	  private String name;
	  private String nonSerialized;
	  private String nonTracked;
	  private String onTime;
	  private String orgId;
	  private String parentAssetId;
	  private String parentAssetNum;
	  private String part;
	  private String plusAsaOnOffId;
	  private String removeAsset;
	  private String removeToLocation;
	  private String serial;
	  private String siteId;
	  private String topAssetId;
	  private String transactionDate;
	  private String type;
	  private String assetDescription;
	  
	  //pmxml
	  private String pmDescription;
	  private String frequency;
	  private String nextDate;
	  private String pmNum;
	  private String workType;
	  private String pmMeterFrequency;
	  
	  //model xml
	  private String modelDescription;
	  private String model;
	  private String modelId;
	  private String variation;
	  private String modelName;
	  
	  
	  public XmlBean() {
		// TODO Auto-generated constructor stub
	}
	  
	  
	  
	  
	public String getAssetDescription() {
		return assetDescription;
	}




	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}




	public String getPmDescription() {
		return pmDescription;
	}




	public void setPmDescription(String pmDescription) {
		this.pmDescription = pmDescription;
	}




	public String getFrequency() {
		return frequency;
	}




	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}




	public String getNextDate() {
		return nextDate;
	}




	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}




	public String getPmNum() {
		return pmNum;
	}




	public void setPmNum(String pmNum) {
		this.pmNum = pmNum;
	}




	public String getWorkType() {
		return workType;
	}




	public void setWorkType(String workType) {
		this.workType = workType;
	}




	public String getPmMeterFrequency() {
		return pmMeterFrequency;
	}




	public void setPmMeterFrequency(String pmMeterFrequency) {
		this.pmMeterFrequency = pmMeterFrequency;
	}




	public String getModelDescription() {
		return modelDescription;
	}




	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
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




	public String getVariation() {
		return variation;
	}




	public void setVariation(String variation) {
		this.variation = variation;
	}




	public String getModelName() {
		return modelName;
	}




	public void setModelName(String modelName) {
		this.modelName = modelName;
	}




	public String getRecordId() {
		return record_id;
	}
	public void setRecordId(String record_id) {
		this.record_id = record_id;
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
	public String getIafCdCts() {
		return iafCdCts;
	}
	public void setIafCdCts(String iafCdCts) {
		this.iafCdCts = iafCdCts;
	}
	public String getIafRemPartNum() {
		return iafRemPartNum;
	}
	public void setIafRemPartNum(String iafRemPartNum) {
		this.iafRemPartNum = iafRemPartNum;
	}
	public String getIafRemSerialNum() {
		return iafRemSerialNum;
	}
	public void setIafRemSerialNum(String iafRemSerialNum) {
		this.iafRemSerialNum = iafRemSerialNum;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNonSerialized() {
		return nonSerialized;
	}
	public void setNonSerialized(String nonSerialized) {
		this.nonSerialized = nonSerialized;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getParentAssetId() {
		return parentAssetId;
	}
	public void setParentAssetId(String parentAssetId) {
		this.parentAssetId = parentAssetId;
	}
	public String getParentAssetNum() {
		return parentAssetNum;
	}
	public void setParentAssetNum(String parentAssetNum) {
		this.parentAssetNum = parentAssetNum;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPlusAsaOnOffId() {
		return plusAsaOnOffId;
	}
	public void setPlusAsaOnOffId(String plusAsaOnOffId) {
		this.plusAsaOnOffId = plusAsaOnOffId;
	}
	public String getRemoveAsset() {
		return removeAsset;
	}
	public void setRemoveAsset(String removeAsset) {
		this.removeAsset = removeAsset;
	}
	public String getRemoveToLocation() {
		return removeToLocation;
	}
	public void setRemoveToLocation(String removeToLocation) {
		this.removeToLocation = removeToLocation;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getTopAssetId() {
		return topAssetId;
	}
	public void setTopAssetId(String topAssetId) {
		this.topAssetId = topAssetId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "XmlBean [recordid=" + record_id + ", assetId=" + assetId + ", assetNum="
				+ assetNum + ", iafCdCts=" + iafCdCts + ", iafRemPartNum="
				+ iafRemPartNum + ", iafRemSerialNum=" + iafRemSerialNum
				+ ", label=" + label + ", lcn=" + lcn + ", name=" + name
				+ ", nonSerialized=" + nonSerialized + ", nonTracked="
				+ nonTracked + ", onTime=" + onTime + ", orgId=" + orgId
				+ ", parentAssetId=" + parentAssetId + ", parentAssetNum="
				+ parentAssetNum + ", part=" + part + ", plusAsaOnOffId="
				+ plusAsaOnOffId + ", removeAsset=" + removeAsset
				+ ", removeToLocation=" + removeToLocation + ", serial="
				+ serial + ", siteId=" + siteId + ", topAssetId=" + topAssetId
				+ ", transactionDate=" + transactionDate + ", type=" + type
				+ "]";
	}
	
	 
	
	
	
}
