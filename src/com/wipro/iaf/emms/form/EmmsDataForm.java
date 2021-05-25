package com.wipro.iaf.emms.form;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class EmmsDataForm {
	
	private String recordId;
	private String desc;
	private String tableOrder;
	private String mainAsset;
	private String mainAssetPart;
	private String mainAssetSerial;
	private String location;
	private String signalOutDate;
	private String recordStatus;
	private String recordIdStatus;
	private String selectedRecordId;
	private String inductionDate;
	private String model;
	private String assetPmStatus;
	private String assetConfigStatus;
	private String assetMeterStatus;
	private String installableStatus;
	private String flbValidationStatus;
	private String flbStatus;
	private String bulkImportStatus;
	private String flbNum;
	private boolean freeze;

	private List<PMDetailForm> pmDetailFormList;
	private List<AssetConfigForm> assetFormList;
	private List<InstallableAssetForm> installableFormList;
	private List<MeterDetailsForm> meterFormList;
	private List<FlbDetailForm> flbFormList;
	 private MultipartFile assetExcelfile;
	 private MultipartFile installableExcelfile;
	 private MultipartFile meterExcelfile;
	 private MultipartFile pmExcelfile;
	 private List<FlbMeterDetailsForm> flbMeterDetailsFormList;
		private List<FlbSortieArForm> flbSortieArFormList;
		private List<FlbPostFlightDataForm> flbPostFlightDataFormList;
	
	
	public MultipartFile getInstallableExcelfile() {
			return installableExcelfile;
		}




		public void setInstallableExcelfile(MultipartFile installableExcelfile) {
			this.installableExcelfile = installableExcelfile;
		}




		public MultipartFile getMeterExcelfile() {
			return meterExcelfile;
		}




		public void setMeterExcelfile(MultipartFile meterExcelfile) {
			this.meterExcelfile = meterExcelfile;
		}




		public MultipartFile getPmExcelfile() {
			return pmExcelfile;
		}
		
		
		




		public String getFlbValidationStatus() {
			return flbValidationStatus;
		}




		public void setFlbValidationStatus(String flbValidationStatus) {
			this.flbValidationStatus = flbValidationStatus;
		}




		public void setPmExcelfile(MultipartFile pmExcelfile) {
			this.pmExcelfile = pmExcelfile;
		}




	public String getBulkImportStatus() {
		return bulkImportStatus;
	}
	
	
	

	public String getFlbNum() {
		return flbNum;
	}




	public void setFlbNum(String flbNum) {
		this.flbNum = flbNum;
	}




	public List<FlbMeterDetailsForm> getFlbMeterDetailsFormList() {
		return flbMeterDetailsFormList;
	}

	public void setFlbMeterDetailsFormList(
			List<FlbMeterDetailsForm> flbMeterDetailsFormList) {
		this.flbMeterDetailsFormList = flbMeterDetailsFormList;
	}

	public List<FlbSortieArForm> getFlbSortieArFormList() {
		return flbSortieArFormList;
	}

	public void setFlbSortieArFormList(List<FlbSortieArForm> flbSortieArFormList) {
		this.flbSortieArFormList = flbSortieArFormList;
	}

	public List<FlbPostFlightDataForm> getFlbPostFlightDataFormList() {
		return flbPostFlightDataFormList;
	}

	public void setFlbPostFlightDataFormList(
			List<FlbPostFlightDataForm> flbPostFlightDataFormList) {
		this.flbPostFlightDataFormList = flbPostFlightDataFormList;
	}

	public void setBulkImportStatus(String bulkImportStatus) {
		this.bulkImportStatus = bulkImportStatus;
	}

	public MultipartFile getAssetExcelfile() {
		return assetExcelfile;
	}

	public void setAssetExcelfile(MultipartFile assetExcelfile) {
		this.assetExcelfile = assetExcelfile;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public String getAssetConfigStatus() {
		return assetConfigStatus;
	}

	public void setAssetConfigStatus(String assetConfigStatus) {
		this.assetConfigStatus = assetConfigStatus;
	}

	public String getAssetMeterStatus() {
		return assetMeterStatus;
	}

	public void setAssetMeterStatus(String assetMeterStatus) {
		this.assetMeterStatus = assetMeterStatus;
	}

	public String getInstallableStatus() {
		return installableStatus;
	}

	public void setInstallableStatus(String installableStatus) {
		this.installableStatus = installableStatus;
	}

	public String getFlbStatus() {
		return flbStatus;
	}

	public void setFlbStatus(String flbStatus) {
		this.flbStatus = flbStatus;
	}

	public List<FlbDetailForm> getFlbFormList() {
		return flbFormList;
	}

	public void setFlbFormList(List<FlbDetailForm> flbFormList) {
		this.flbFormList = flbFormList;
	}

	public List<MeterDetailsForm> getMeterFormList() {
		return meterFormList;
	}

	public void setMeterFormList(List<MeterDetailsForm> meterFormList) {
		this.meterFormList = meterFormList;
	}

	public List<InstallableAssetForm> getInstallableFormList() {
		return installableFormList;
	}

	public void setInstallableFormList(
			List<InstallableAssetForm> installableFormList) {
		this.installableFormList = installableFormList;
	}

	public List<AssetConfigForm> getAssetFormList() {
		return assetFormList;
	}

	public void setAssetFormList(List<AssetConfigForm> assetFormList) {
		this.assetFormList = assetFormList;
	}

	public String getAssetPmStatus() {
		return assetPmStatus;
	}

	public void setAssetPmStatus(String assetPmStatus) {
		this.assetPmStatus = assetPmStatus;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTableOrder() {
		return tableOrder;
	}

	public void setTableOrder(String tableOrder) {
		this.tableOrder = tableOrder;
	}

	public String getMainAsset() {
		return mainAsset;
	}
	public void setMainAsset(String mainAsset) {
		this.mainAsset = mainAsset;
	}
	public String getMainAssetPart() {
		return mainAssetPart;
	}
	public void setMainAssetPart(String mainAssetPart) {
		this.mainAssetPart = mainAssetPart;
	}
	public String getMainAssetSerial() {
		return mainAssetSerial;
	}
	public void setMainAssetSerial(String mainAssetSerial) {
		this.mainAssetSerial = mainAssetSerial;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSignalOutDate() {
		return signalOutDate;
	}
	public void setSignalOutDate(String signalOutDate) {
		this.signalOutDate = signalOutDate;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getSelectedRecordId() {
		return selectedRecordId;
	}
	public void setSelectedRecordId(String selectedRecordId) {
		this.selectedRecordId = selectedRecordId;
	}
	
	public List<PMDetailForm> getPmDetailFormList() {
		return pmDetailFormList;
	}
	public void setPmDetailFormList(List<PMDetailForm> pmDetailFormList) {
		this.pmDetailFormList = pmDetailFormList;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getInductionDate() {
		return inductionDate;
	}
	public void setInductionDate(String inductionDate) {
		this.inductionDate = inductionDate;
	}

	public String getRecordIdStatus() {
		return recordIdStatus;
	}

	public void setRecordIdStatus(String recordIdStatus) {
		this.recordIdStatus = recordIdStatus;
	}




	@Override
	public String toString() {
		return "EmmsDataForm [recordId=" + recordId + ", desc=" + desc + ", tableOrder=" + tableOrder + ", mainAsset="
				+ mainAsset + ", mainAssetPart=" + mainAssetPart + ", mainAssetSerial=" + mainAssetSerial
				+ ", location=" + location + ", signalOutDate=" + signalOutDate + ", recordStatus=" + recordStatus
				+ ", recordIdStatus=" + recordIdStatus + ", selectedRecordId=" + selectedRecordId + ", inductionDate="
				+ inductionDate + ", model=" + model + ", assetPmStatus=" + assetPmStatus + ", assetConfigStatus="
				+ assetConfigStatus + ", assetMeterStatus=" + assetMeterStatus + ", installableStatus="
				+ installableStatus + ", flbValidationStatus=" + flbValidationStatus + ", flbStatus=" + flbStatus
				+ ", bulkImportStatus=" + bulkImportStatus + ", flbNum=" + flbNum + ", freeze=" + freeze
				+ ", pmDetailFormList=" + pmDetailFormList + ", assetFormList=" + assetFormList
				+ ", installableFormList=" + installableFormList + ", meterFormList=" + meterFormList + ", flbFormList="
				+ flbFormList + ", assetExcelfile=" + assetExcelfile + ", installableExcelfile=" + installableExcelfile
				+ ", meterExcelfile=" + meterExcelfile + ", pmExcelfile=" + pmExcelfile + ", flbMeterDetailsFormList="
				+ flbMeterDetailsFormList + ", flbSortieArFormList=" + flbSortieArFormList
				+ ", flbPostFlightDataFormList=" + flbPostFlightDataFormList + "]";
	}


}
