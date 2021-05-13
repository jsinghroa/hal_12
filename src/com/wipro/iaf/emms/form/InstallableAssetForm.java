package com.wipro.iaf.emms.form;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)

public class InstallableAssetForm {
	
	
	

	  private String enditemPN;
	  private String enditemsn;
	  private String installableModel;
	  private String lcn;
	  private String position;
	  private String builditem;
	  private String assertnum;
	  private String partnum;
	  private String partDesc;
	  private String serialnum;
	  private String installablePN;
	  private LinkedHashMap<String,String> installedPNList;
	  private List<String> conditionCodes;
	  private String iNlieuPN;
	  private String installableSN;
	  private String conditionCode;
	  private String dateofManufacturing;
	  private String dateofReceipt;
	  private String errorStatus;
	  private String errorDescription;
	  private String recordRowId;
	  private String recordId;
	  private boolean flag;
	  public  InstallableAssetForm()
		{
			
		}
	  

	  
	  
		public String getBuilditem() {
		return builditem;
	}




	public void setBuilditem(String builditem) {
		this.builditem = builditem;
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




		public String getRecordRowId() {
		return recordRowId;
	}




	public void setRecordRowId(String recordRowId) {
		this.recordRowId = recordRowId;
	}




		public List<String> getConditionCodes() {
		return conditionCodes;
	}




	public void setConditionCodes(List<String> conditionCodes) {
		this.conditionCodes = conditionCodes;
	}




		public LinkedHashMap<String, String> getInstalledPNList() {
		return installedPNList;
	}




	public void setInstalledPNList(LinkedHashMap<String, String> installedPNList) {
		this.installedPNList = installedPNList;
	}




		public String getEnditemPN() {
			return enditemPN;
		}

		public void setEnditemPN(String enditemPN) {
			this.enditemPN = enditemPN;
		}

		public String getEnditemsn() {
			return enditemsn;
		}

		public void setEnditemsn(String enditemsn) {
			this.enditemsn = enditemsn;
		}

		public String getInstallableModel() {
			return installableModel;
		}

		public void setInstallableModel(String installableModel) {
			this.installableModel = installableModel;
		}

		public String getLcn() {
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

	
		public String getAssertnum() {
			return assertnum;
		}

		public void setAssertnum(String assertnum) {
			this.assertnum = assertnum;
		}

		public String getPartnum() {
			return partnum;
		}

		public void setPartnum(String partnum) {
			this.partnum = partnum;
		}

		public String getPartDesc() {
			return partDesc;
		}

		public void setPartDesc(String partDesc) {
			this.partDesc = partDesc;
		}

		public String getSerialnum() {
			return serialnum;
		}

		public void setSerialnum(String serialnum) {
			this.serialnum = serialnum;
		}

		public String getInstallablePN() {
			return installablePN;
		}

		public void setInstallablePN(String installablePN) {
			this.installablePN = installablePN;
		}

		public String getiNlieuPN() {
			return iNlieuPN;
		}

		public void setiNlieuPN(String iNlieuPN) {
			this.iNlieuPN = iNlieuPN;
		}

		public String getInstallableSN() {
			return installableSN;
		}

		public void setInstallableSN(String installableSN) {
			this.installableSN = installableSN;
		}

		public String getConditionCode() {
			return conditionCode;
		}

		public void setConditionCode(String conditionCode) {
			this.conditionCode = conditionCode;
		}

		public String getDateofManufacturing() {
			return dateofManufacturing;
		}

		public void setDateofManufacturing(String dateofManufacturing) {
			this.dateofManufacturing = dateofManufacturing;
		}

		public String getDateofReceipt() {
			return dateofReceipt;
		}

		public void setDateofReceipt(String dateofReceipt) {
			this.dateofReceipt = dateofReceipt;
		}

		public String getErrorStatus() {
			return errorStatus;
		}

		public void setErrorStatus(String errorStatus) {
			this.errorStatus = errorStatus;
		}

		public String getErrorDescription() {
			return errorDescription;
		}

		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}




		@Override
		public String toString() {
			return "InstallableAssetForm [enditemPN=" + enditemPN
					+ ", enditemsn=" + enditemsn + ", installableModel="
					+ installableModel + ", lcn=" + lcn + ", position="
					+ position + ", builditem=" + builditem + ", assertnum="
					+ assertnum + ", partnum=" + partnum + ", partDesc="
					+ partDesc + ", serialnum=" + serialnum
					+ ", installablePN=" + installablePN + ", installedPNList="
					+ installedPNList + ", conditionCodes=" + conditionCodes
					+ ", iNlieuPN=" + iNlieuPN + ", installableSN="
					+ installableSN + ", conditionCode=" + conditionCode
					+ ", dateofManufacturing=" + dateofManufacturing
					+ ", dateofReceipt=" + dateofReceipt + ", errorStatus="
					+ errorStatus + ", errorDescription=" + errorDescription
					+ ", recordRowId=" + recordRowId + ", recordId=" + recordId
					+ "]";
		}

	
	 }
