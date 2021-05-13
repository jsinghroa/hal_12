package com.wipro.iaf.emms.form;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class PlusaFlbSetupBean {
	private String configId;
	private String createpFlins;
	private String description;
	private String flbType;
	private String iaf_Class;
	private String measureUnitId;
	private String model;
    private String modelId;
    private String orgId;
    private String plusaFlbSetupId;
    private String revisionNum;
    private String statSymbReq;
    private String status; 
    private String statusDate;
    private String variation;
    ArrayList<PlusaFlbSetupMeterBean> plusaFlbSetupMeterBeanList =new ArrayList<>();
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getCreatepFlins() {
		return createpFlins;
	}
	public void setCreatepFlins(String createpFlins) {
		this.createpFlins = createpFlins;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFlbType() {
		return flbType;
	}
	public void setFlbType(String flbType) {
		this.flbType = flbType;
	}
	public String getIaf_Class() {
		return iaf_Class;
	}
	public void setIaf_Class(String iaf_Class) {
		this.iaf_Class = iaf_Class;
	}
	public String getMeasureUnitId() {
		return measureUnitId;
	}
	public void setMeasureUnitId(String measureUnitId) {
		this.measureUnitId = measureUnitId;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPlusaFlbSetupId() {
		return plusaFlbSetupId;
	}
	public void setPlusaFlbSetupId(String plusaFlbSetupId) {
		this.plusaFlbSetupId = plusaFlbSetupId;
	}
	public String getRevisionNum() {
		return revisionNum;
	}
	public void setRevisionNum(String revisionNum) {
		this.revisionNum = revisionNum;
	}
	public String getStatSymbReq() {
		return statSymbReq;
	}
	public void setStatSymbReq(String statSymbReq) {
		this.statSymbReq = statSymbReq;
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
	public ArrayList<PlusaFlbSetupMeterBean> getPlusaFlbSetupMeterBeanList() {
		return plusaFlbSetupMeterBeanList;
	}
	public void setPlusaFlbSetupMeterBeanList(ArrayList<PlusaFlbSetupMeterBean> plusaFlbSetupMeterBeanList) {
		this.plusaFlbSetupMeterBeanList = plusaFlbSetupMeterBeanList;
	}
	@Override
	public String toString() {
		return "PlusaFlbSetupBean [configId=" + configId + ", createpFlins=" + createpFlins + ", description="
				+ description + ", flbType=" + flbType + ", iaf_Class=" + iaf_Class + ", measureUnitId=" + measureUnitId
				+ ", model=" + model + ", modelId=" + modelId + ", orgId=" + orgId + ", plusaFlbSetupId="
				+ plusaFlbSetupId + ", revisionNum=" + revisionNum + ", statSymbReq=" + statSymbReq + ", status="
				+ status + ", statusDate=" + statusDate + ", variation=" + variation + ", plusaFlbSetupMeterBeanList="
				+ plusaFlbSetupMeterBeanList + "]";
	}
    
    

}
