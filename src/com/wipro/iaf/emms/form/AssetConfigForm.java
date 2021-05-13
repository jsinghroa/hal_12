package com.wipro.iaf.emms.form;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AssetConfigForm {

	  private String indicator;
	  private  String lcn;
	  private String position;
	  private String buildItem;
	  private String assetNum;
	  private String partNum;
	  private String partDescription;
	  private String serialNum;
	  private String installedPN;
	  private String inLieuPn;
	  private String installedSN;
	  private String conditionCode;
	  private String dateOfManfacturing;
	  private String dateOfReciept;
	  private  String errorStatus;
	  private String errorDesc;
	  private LinkedHashMap<String,String> installedPNList;
	  private List<String> conditionCodes;
	  private String recordId;
	  private String parentInsRowId;
	  private String planRowId;
	  private String model;
	  private String variation;
	  private String buildPosition;
	  private BigInteger buildId;
	  private String cmItem;
	   private String assetCmItem;
	  private String assetSerial;
	  private int indenture;
	  private Timestamp exportDate;
	  private String recordRowId;
	  private String biType;
	  private boolean flag;
	  
	  public AssetConfigForm()
	  {
		  
	  }
	  
	  
	  
	  
	  
	public String getBiType() {
		return biType;
	}





	public void setBiType(String biType) {
		this.biType = biType;
	}





	public LinkedHashMap<String, String> getInstalledPNList() {
		return installedPNList;
	}





	public void setInstalledPNList(LinkedHashMap<String, String> installedPNList) {
		this.installedPNList = installedPNList;
	}





	public List<String> getConditionCodes() {
		return conditionCodes;
	}





	public void setConditionCodes(List<String> conditionCodes) {
		this.conditionCodes = conditionCodes;
	}



	public boolean isFlag(){
		return flag;
		}
		
		public void setFlag(boolean flag){
			this.flag=flag;
		}

	public String getRecordId() {
		return recordId;
	}





	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}





	public String getParentInsRowId() {
		return parentInsRowId;
	}





	public void setParentInsRowId(String parentInsRowId) {
		this.parentInsRowId = parentInsRowId;
	}





	public String getPlanRowId() {
		return planRowId;
	}





	public void setPlanRowId(String planRowId) {
		this.planRowId = planRowId;
	}





	public String getModel() {
		return model;
	}





	public void setModel(String model) {
		this.model = model;
	}





	public String getVariation() {
		return variation;
	}





	public void setVariation(String variation) {
		this.variation = variation;
	}





	public String getBuildPosition() {
		return buildPosition;
	}





	public void setBuildPosition(String buildPosition) {
		this.buildPosition = buildPosition;
	}





	public BigInteger getBuildId() {
		return buildId;
	}





	public void setBuildId(BigInteger buildId) {
		this.buildId = buildId;
	}





	public String getCmItem() {
		return cmItem;
	}





	public void setCmItem(String cmItem) {
		this.cmItem = cmItem;
	}





	public String getAssetCmItem() {
		return assetCmItem;
	}





	public void setAssetCmItem(String assetCmItem) {
		this.assetCmItem = assetCmItem;
	}





	public String getAssetSerial() {
		return assetSerial;
	}





	public void setAssetSerial(String assetSerial) {
		this.assetSerial = assetSerial;
	}





	public int getIndenture() {
		return indenture;
	}





	public void setIndenture(int indenture) {
		this.indenture = indenture;
	}





	public Timestamp getExportDate() {
		return exportDate;
	}





	public void setExportDate(Timestamp exportDate) {
		this.exportDate = exportDate;
	}





	public String getRecordRowId() {
		return recordRowId;
	}





	public void setRecordRowId(String recordRowId) {
		this.recordRowId = recordRowId;
	}





	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public  String getLcn() {
		return lcn;
	}
	public void setLcn(String lcn) {
		this.lcn = lcn;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBuildItem() {
		return buildItem;
	}
	public void setBuildItem(String buildItem) {
		this.buildItem = buildItem;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getInstalledPN() {
		return installedPN;
	}
	public void setInstalledPN(String installedPN) {
		this.installedPN = installedPN;
	}
	public String getInLieuPn() {
		return inLieuPn;
	}
	public void setInLieuPn(String inLieuPn) {
		this.inLieuPn = inLieuPn;
	}
	public String getInstalledSN() {
		return installedSN;
	}
	public void setInstalledSN(String installedSN) {
		this.installedSN = installedSN;
	}
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	public String getDateOfManfacturing() {
		return dateOfManfacturing;
	}
	public void setDateOfManfacturing(String dateOfManfacturing) {
		this.dateOfManfacturing = dateOfManfacturing;
	}
	public String getDateOfReciept() {
		return dateOfReciept;
	}
	public void setDateOfReciept(String dateOfReciept) {
		this.dateOfReciept = dateOfReciept;
	}
	public  String getErrorStatus() {
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

	@Override
	public String toString() {
		return "AssetConfigForm [indicator=" + indicator + ", lcn=" + lcn
				+ ", position=" + position + ", buildItem=" + buildItem
				+ ", assetNum=" + assetNum + ", partNum=" + partNum
				+ ", partDescription=" + partDescription + ", serialNum="
				+ serialNum + ", installedPN=" + installedPN + ", inLieuPn="
				+ inLieuPn + ", installedSN=" + installedSN
				+ ", conditionCode=" + conditionCode + ", dateOfManfacturing="
				+ dateOfManfacturing + ", dateOfReciept=" + dateOfReciept
				+ ", errorStatus=" + errorStatus + ", errorDesc=" + errorDesc
				+ ", installedPNList=" + installedPNList + ", conditionCodes="
				+ conditionCodes + ", recordId=" + recordId
				+ ", parentInsRowId=" + parentInsRowId + ", planRowId="
				+ planRowId + ", model=" + model + ", variation=" + variation
				+ ", buildPosition=" + buildPosition + ", buildId=" + buildId
				+ ", cmItem=" + cmItem + ", assetCmItem=" + assetCmItem
				+ ", assetSerial=" + assetSerial + ", indenture=" + indenture
				+ ", exportDate=" + exportDate + ", recordRowId=" + recordRowId
				+ ", biType=" + biType + ", flag=" + flag + ", getBiType()="
				+ getBiType() + ", getInstalledPNList()="
				+ getInstalledPNList() + ", getConditionCodes()="
				+ getConditionCodes() + ", isFlag()=" + isFlag()
				+ ", getRecordId()=" + getRecordId() + ", getParentInsRowId()="
				+ getParentInsRowId() + ", getPlanRowId()=" + getPlanRowId()
				+ ", getModel()=" + getModel() + ", getVariation()="
				+ getVariation() + ", getBuildPosition()=" + getBuildPosition()
				+ ", getBuildId()=" + getBuildId() + ", getCmItem()="
				+ getCmItem() + ", getAssetCmItem()=" + getAssetCmItem()
				+ ", getAssetSerial()=" + getAssetSerial()
				+ ", getIndenture()=" + getIndenture() + ", getExportDate()="
				+ getExportDate() + ", getRecordRowId()=" + getRecordRowId()
				+ ", getIndicator()=" + getIndicator() + ", getLcn()="
				+ getLcn() + ", getPosition()=" + getPosition()
				+ ", getBuildItem()=" + getBuildItem() + ", getAssetNum()="
				+ getAssetNum() + ", getPartNum()=" + getPartNum()
				+ ", getPartDescription()=" + getPartDescription()
				+ ", getSerialNum()=" + getSerialNum() + ", getInstalledPN()="
				+ getInstalledPN() + ", getInLieuPn()=" + getInLieuPn()
				+ ", getInstalledSN()=" + getInstalledSN()
				+ ", getConditionCode()=" + getConditionCode()
				+ ", getDateOfManfacturing()=" + getDateOfManfacturing()
				+ ", getDateOfReciept()=" + getDateOfReciept()
				+ ", getErrorStatus()=" + getErrorStatus()
				+ ", getErrorDesc()=" + getErrorDesc() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	  


}
