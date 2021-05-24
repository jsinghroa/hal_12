package com.wipro.iaf.emms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.iaf.emms.dao.HalDao;
import com.wipro.iaf.emms.form.AssetBean;
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.AssetHierarchyViewBean;
import com.wipro.iaf.emms.form.AssetMeterVwBean;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.ErrorStatusForm;
import com.wipro.iaf.emms.form.FlbDetailForm;
import com.wipro.iaf.emms.form.FlbMeterDetailsForm;
import com.wipro.iaf.emms.form.FlbPostFlightDataForm;
import com.wipro.iaf.emms.form.FlbSortieArForm;
import com.wipro.iaf.emms.form.HalCatMeterViewBean;
import com.wipro.iaf.emms.form.HalModelViewBean;
import com.wipro.iaf.emms.form.InstallableAssetForm;
import com.wipro.iaf.emms.form.MasterPmBean;
import com.wipro.iaf.emms.form.MasterPmMeterBean;
import com.wipro.iaf.emms.form.MeterBean;
import com.wipro.iaf.emms.form.MeterDetailsForm;
import com.wipro.iaf.emms.form.ModelBean;
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.form.PlusAcaxMasterPmBean;
import com.wipro.iaf.emms.form.PlusaMasterPmMeteriter;
import com.wipro.iaf.emms.form.UserRegistrationForm;

@Service
public class HalService {

	@Autowired
	private HalDao halDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Map<String, String> parentChild;

	private List<String> lcnList;

	private Map<String, String> insLcnList;

	/*
	 * public String fetchTableOrder(String recordId) { // TODO Auto-generated
	 * method stub String tableOrder=halDAO.fetchTableOrder(recordId); return
	 * tableOrder; }
	 */
	public List<EmmsDataForm> getEmmsDataOnView() {
		List<EmmsDataForm> emmsDataFormList = halDAO.getEmmsDataOnView();
		// TODO Auto-generated method stub
		return emmsDataFormList;
	}

	public EmmsDataForm fetchDetails(String selectedRecordId) {
		System.out.println("service" + " " + selectedRecordId);
		EmmsDataForm emmsDataForm = halDAO.fetchDetails(selectedRecordId);
		// System.out.println(emmsDataForm.getAssetConfigStatus());
		emmsDataForm.setSelectedRecordId(selectedRecordId);
		System.out.println("in  EmmsDataForm service" + emmsDataForm.getSelectedRecordId());
		return emmsDataForm;
	}

	public List<PMDetailForm> fetchPMDetails(String recordId) {
		return halDAO.fetchPMDetails(recordId);
	}

	public List<EmmsDataForm> getRecordIdOnView() {
		List<EmmsDataForm> emmsDataFormList = halDAO.getRecordIdOnView();
		// TODO Auto-generated method stub
		return emmsDataFormList;
	}

	public void deleteData(String deleterecordId) {
		halDAO.deleteData(deleterecordId);
	}

	public String fetchrecordStatus(String deleterecordId) {
		String status = halDAO.fetchrecordStatus(deleterecordId);
		return status;
	}

	public void deleteRecord(String deleterecordId) {
		halDAO.deleteRecord(deleterecordId);
	}

	public List<AssetConfigForm> fetchConfigDetails(String recordID) {
		return halDAO.fetchConfigDetails(recordID);
	}

	public List<InstallableAssetForm> fetchInstallableDetails(String recordID, List<String> lcnList,
			Map<String, String> parentChilds) {
		// list of lcn with indicator i and their childs

		System.out.println("Service:" + lcnList + ":" + parentChilds);

		return halDAO.fetchInstallableDetails(recordID);
	}

	public int update(EmmsDataForm emmsDataForm, PMDetailForm pmDetailForm) {
		return halDAO.update(emmsDataForm, pmDetailForm);

	}

	public int update(EmmsDataForm emmsDataForm, AssetConfigForm assetConfigForm) {
		// assetConfigForm.setDateOfManfacturing(new Timestamp(new
		// Date().getTime())assetConfigForm.getDateOfManfacturing());
		return halDAO.update(emmsDataForm, assetConfigForm);

	}

	public int update(EmmsDataForm emmsDataForm, MeterDetailsForm meterForm) {
		// assetConfigForm.setDateOfManfacturing(new Timestamp(new
		// Date().getTime())assetConfigForm.getDateOfManfacturing());
		return halDAO.update(emmsDataForm, meterForm);

	}

	public int update(EmmsDataForm emmsDataForm, InstallableAssetForm insAsset) {
		// TODO Auto-generated method stub
		return halDAO.update(emmsDataForm, insAsset);
	}

	public int updateStatus(String installableStatus, String recordStatus, String recordId) {
		// TODO Auto-generated method stub
		return halDAO.updateStatus(installableStatus, recordStatus, recordId);
	}

	public List<MeterDetailsForm> fetchMeterDetails(String recordID) {
		// TODO Auto-generated method stub
		return halDAO.fetchMetersDetails(recordID);
	}

	public int[] importXml(List<AssetBean> assetConfigList) {
		List<String> queries = new ArrayList<String>();
		for (int i = 0; i < assetConfigList.size(); i++) {
			System.out.println("inside halheader");
			String query = "insert into iaf_halheader(IAF_ASSETNUM,IAF_ASSET_HIERARCHY_REMOVED,IAF_CMITEM,IAF_CONFIGID,IAF_EMMSHALTRANSID,IAF_INDUCTIONDATE,IAF_LOCATION,IAF_MODEL,IAF_MODELID,IAF_RECORDID,IAF_SERIALNUM,IAF_STATUS,IAF_VARIATION) "
					+ "values('" + assetConfigList.get(i).getIaf_AssetNum() + "','"
					+ assetConfigList.get(i).getIaf_AssetHierarchyRemoved() + "','"
					+ assetConfigList.get(i).getIaf_CmItem() + "','" + assetConfigList.get(i).getIaf_ConfigId() + "','"
					+ assetConfigList.get(i).getIaf_EmmsHalTransId() + "','" + assetConfigList.get(i).getInductionDate()
					+ "','" + assetConfigList.get(i).getIaf_Location() + "','" + assetConfigList.get(i).getIaf_Model()
					+ "','" + assetConfigList.get(i).getIaf_ModelId() + "','" + assetConfigList.get(i).getIaf_RecordId()
					+ "','" + assetConfigList.get(i).getIaf_SerialNum() + "','" + assetConfigList.get(i).getIaf_Status()
					+ "','" + assetConfigList.get(i).getIaf_Variation() + "')";
			queries.add(query);

			
/*			  ArrayList<AssetHierarchyViewBean>
			  assetHierarchyViewBean=assetConfigList.get(i).getAssetHierarchyViewList();
			  
			  for(int j=0;j<assetHierarchyViewBean.size();j++){
			  
			  String
			  query2="insert into iaf_hal_asset(IAF_RECORDID,IAF_ASSETID,IAF_ASSETNUM,IAF_BUILDITEMID,IAF_CMITEM,IAF_CONFIGID,END_ASSET,END_ASSETID,IAF_INDENTURE,IAF_INDICATOR,IAF_ISDEFAULT,ITEMNUM,IAF_LABEL,IAF_LCN,IAF_MODEL,IAF_MODELID,IAF_NAME,IAF_NONTRACKED,PARENTASSETID) "
			  + "values('" +assetConfigList.get(i).getIaf_RecordId() +"','"
			  +assetHierarchyViewBean.get(j).getAssetId() +"','"
			  +assetHierarchyViewBean.get(j).getAssetNum() +"','"
			  +assetHierarchyViewBean.get(j).getBuildItemId() +"','"
			  +assetHierarchyViewBean.get(j).getCmItem() +"','"
			  +assetHierarchyViewBean.get(j).getConfigId() +"','"
			  +assetHierarchyViewBean.get(j).getEndAsset() +"','"
			  +assetHierarchyViewBean.get(j).getEndAssetId() +"','"
			  +assetHierarchyViewBean.get(j).getIndenture() +"','"
			  +assetHierarchyViewBean.get(j).getIndicator() +"','"
			  +assetHierarchyViewBean.get(j).getIsDefault() +"','"
			  +assetHierarchyViewBean.get(j).getItemNum() +"','"
			  +assetHierarchyViewBean.get(j).getLabel() +"','"
			  +assetHierarchyViewBean.get(j).getLcn() +"','"
			  +assetHierarchyViewBean.get(j).getModel() +"','"
			  +assetHierarchyViewBean.get(j).getModelId() +"','"
			  +assetHierarchyViewBean.get(j).getName() +"','"
			  +assetHierarchyViewBean.get(j).getNonTracked() +"','"
			  +assetHierarchyViewBean.get(j).getParentAssetId() + "')";
			  
			  queries.add(query2);
			  
			  
			  ArrayList<AssetMeterVwBean>
			  assetMeterVwBean=assetConfigList.get(i).getAssetHierarchyViewList().get(j).
			  getAssetMeterVWList(); for(int k=0;assetMeterVwBean!=null && k<
			  assetMeterVwBean.size() ;k++){ String
			  query3="insert into iaf_assetmeter(CURRENTVALUE,IAF_MEASUREUNITID,IAF_METERNAME,IAF_PLUSAANCHORCOUNT) "
			  + "values('" + assetMeterVwBean.get(k).getCurrentValue() +"','"
			  +assetMeterVwBean.get(k).getMeasureUnitId() +"','"
			  +assetMeterVwBean.get(k).getMeterName() +"','"
			  +assetMeterVwBean.get(k).getPlusaAnchorCount() + "')"; queries.add(query3); }
			  
			  }*/
			  }
		String[] queryArray = queries.toArray(new String[queries.size()]);
		return halDAO.importXml(queryArray);
	 
		}
		
	

	public int[] modelXml(List<ModelBean> modelConfigList){
		List<String> queries = new ArrayList<String>();
		
		for(int i=0;i<modelConfigList.size();i++){
		ArrayList<HalModelViewBean>	 halModelViewBean =modelConfigList.get(i).getHalModelViewBeanList();
		
		for(int j=0;j<halModelViewBean.size();j++){
			
			if(halModelViewBean.get(j).getCmItem().equalsIgnoreCase(halModelViewBean.get(j).getEndModel())){
				System.out.println("inside halmodel");
				
				 String query="insert into iaf_hal_model(IAF_MODELID,IAF_MODELNAME,IAF_COFIGID,IAF_CMITEM,IAF_VARIATION,IAF_RECORDID,MODELDESCRIPTION) "
							+ "values('"
							+ halModelViewBean.get(j).getPlusAcmModelId()
							+"','"
							+halModelViewBean.get(j).getModel()
							+"','"
							+halModelViewBean.get(j).getConfigId()
							+"','"
							+halModelViewBean.get(j).getCmItem()
							+"','"
							+halModelViewBean.get(j).getVariation()
							+"','"
							+modelConfigList.get(i).getIafRecordId()
							+"','"
							+halModelViewBean.get(j).getModelDescription()
				            + "')";	
				 queries.add(query);
			}
			
		}
		for(int k=0;k<halModelViewBean.size();k++){
			
//			String query="insert into iaf_hal_plusacmbuild (IAF_RECORDID,IAF_BUILDID,IAF_CATID,IAF_CMITEM,IAF_COFIGID,END_MODEL,END_VARIATION,IAF_INDENTURE,IAF_INDICATOR,IAF_ISDEFAULT,IAF_NAME,IAF_BUILDITEMID,IAF_POSITION,IAF_NONSERIALIZED,IAF_MODELID,ALC,IAF_ABSTRACT) "
//					+ "values('"
//					+ modelConfigList.get(i).getIafRecordId()
//					+"','"
//					+halModelViewBean.get(k).getBuildId() 
//					+"','"
//					+halModelViewBean.get(k).getCatId()
//					+"','"
//					+halModelViewBean.get(k).getCmItem()
//					+"','"
//					+halModelViewBean.get(k).getConfigId()
//					+"','"
//					+halModelViewBean.get(k).getEndItemModel()
//					+"','"
//					+halModelViewBean.get(k).getEndVariation()
//					+"','"
//					+halModelViewBean.get(k).getIndenture()
//					+"','"
//					+halModelViewBean.get(k).getIndicator()
//					+"','"
//					+halModelViewBean.get(k).getIsDefault()
//					+"','"
//					+halModelViewBean.get(k).getName()
//					+"','"
//					+halModelViewBean.get(k).getPlusAcmBuildItemId()
//					+"','"
//					+halModelViewBean.get(k).getPosition()
//					+"','"
//					+halModelViewBean.get(k).getNonSerialized()
//					+"','"
//					+halModelViewBean.get(k).getPlusAcmModelId()
//					+"','"
//					+halModelViewBean.get(k).getAlc()
//					+"','"
//					+halModelViewBean.get(k).getAbstract1()
//		            + "')";	
//		 queries.add(query);
		 
//		 ArrayList<HalCatMeterViewBean> halCatMeterViewBean =modelConfigList.get(i).getHalModelViewBeanList().get(k).getHalCatMeterViewBeanList();
//		 for(int a=0; halCatMeterViewBean!=null && a<halCatMeterViewBean.size();a++) {
//			 System.out.println("inside halcatmeter");
//			 String query6="insert into iaf_hal_catmeter_view(IAF_RECORDID,measureunitid,metername,plusacacatid)"
//					 + "values('"
//						/*
//						 * + modelConfigList.get(i).getIafRecordId() +"','"
//						 
//						+halCatMeterViewBean.get(a).getMeasureUnitId()
//						+"','"
//						+halCatMeterViewBean.get(a).getMeterName()
//						+"','"
//						+halCatMeterViewBean.get(a).getPlusAcaCatId()
//						+ "')";
//			 
//			 queries.add(query6);
//		 }
		 ArrayList<PlusAcaxMasterPmBean> plusAcaxMasterPmBean= modelConfigList.get(i).getHalModelViewBeanList().get(k).getPlusAcaxMasterPmBeanList();
		 for(int p=0; plusAcaxMasterPmBean!=null && p<plusAcaxMasterPmBean.size();p++) {
		 ArrayList<MasterPmBean> masterPmBean=modelConfigList.get(i).getHalModelViewBeanList().get(k).getPlusAcaxMasterPmBeanList().get(p).getMasterPmBeanList();
		 
		 for(int l=0; masterPmBean!=null && l<masterPmBean.size();l++){
			 System.out.println("inside halmodel-2");
			 String query2="insert into iaf_hal_masterpm(IAF_RECORDID,iaf_frequency,iaf_catid,iaf_masterpmnum,iaf_worktype) "
						+ "values('"
						  + modelConfigList.get(i).getIafRecordId() 
						  +"','"
						  +masterPmBean.get(l).getFrequency() 
						  +"','" 
						  +halModelViewBean.get(k).getCatId() 
						  +"','"
						+masterPmBean.get(l).getMasterPmNum()
						+"','"
						+masterPmBean.get(l).getWorktype()
						+ "')";	
			 queries.add(query2);
			 
			 
			 ArrayList<MasterPmMeterBean> masterPmMeterBean=masterPmBean.get(l).getMasterPmMeterBeanList();
			 for(int m=0; masterPmMeterBean!=null && m<masterPmMeterBean.size();m++){
				 System.out.println("inside halmodel3");
				 ArrayList<MeterBean> meterBean=masterPmMeterBean.get(m).getMeterBeanList();
				 
				 for(int n=0; meterBean!=null && n<meterBean.size();n++){
					System.out.println("inside meter bean");
					 String query3="insert into iaf_hal_masterpmmeter(IAF_RECORDID,IAF_METER,IAF_MEASUREUNITID,IAF_FREQUENCY) "
								+ "values('"
								  + modelConfigList.get(i).getIafRecordId() 
								  +"','"
								  +masterPmMeterBean.get(m).getMeterName() 
								  +"','"
								  +meterBean.get(n).getMeasureUnitId() 
								  +"','"
								  +masterPmMeterBean.get(m).getFrequency()
								  + "')";	
					 queries.add(query3);
				 }
			 }
			 
			 
			 
			 
			 ArrayList<PlusaMasterPmMeteriter> plusaMasterPmMeteriter =masterPmBean.get(l).getPlusaMasterPmMeteriterList();
			 
			 for(int b=0; plusaMasterPmMeteriter!=null && b<plusaMasterPmMeteriter.size();b++){
				 String query5="insert into iaf_hal_plusamasterpmmeteriter(IAF_RECORDID,iaf_meter,iaf_masterpmnum,iaf_frequency,iaf_iterationseq,iaf_iterationfreq)"
						 + "values('"
							  + modelConfigList.get(i).getIafRecordId() 
							  +"','"
							+plusaMasterPmMeteriter.get(b).getMetername()
							+"','"
							+masterPmBean.get(l).getMasterPmNum()
							+"','"
							+plusaMasterPmMeteriter.get(b).getFrequency() 
							+"','"
							/*
							 * +meterBean.get(b).getMeasureUnitId() +"','"
							 */
							+plusaMasterPmMeteriter.get(b).getIterationseq()
							+"','"
							+plusaMasterPmMeteriter.get(b).getIterationfreq()
							+ "')";
				 queries.add(query5);
			 	}
			 
			 }
		 }
	}
}			
	
		


		
		String[] queryArray=queries.toArray(new String[queries.size()]);
		return halDAO.importXml(queryArray);		
	}

	public Map<String, String> setParentChild(String recordId) {
		Map<String, String> parentChild = new HashMap<String, String>();
		parentChild = halDAO.getLcnList(recordId);

		this.parentChild = parentChild;
		return parentChild;

	}

	public Map<String, String> getParentChild() {
		return parentChild;
	}

	public List<String> getLcnList() {

		return lcnList;
	}

	public List<String> setLcnList() {
		System.out.println("befor indicator lcn=" + lcnList);
		List<String> lcnList = halDAO.getLcnIndicator();

		System.out.println("indicator lcn=" + lcnList);
		this.lcnList = lcnList;
		return lcnList;
	}

	public Map<String, String> getLcnEdit() {
		return insLcnList;
	}

	public Map<String, String> setLcnEdit() throws SQLException {
		Map<String, String> insLcnList = halDAO.getLcnedit();
		this.insLcnList = insLcnList;
		return insLcnList;
	}

	public void insertRegisteredUser(UserRegistrationForm user) {
		System.out.println(passwordEncoder.encode(user.getPassword()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		halDAO.insertRegisteredUser(user);
	}

	public List<ErrorStatusForm> fetchErrors(String recordId) {
		return halDAO.fetchErrors(recordId);
	}

	public int updateFreeze(boolean freeze, String recordId) {
		return halDAO.updateFreeze(freeze, recordId);

	}

	public int updateHeader(EmmsDataForm emmsDataForm) {
		return halDAO.updateHeader(emmsDataForm);
		// TODO Auto-generated method stub

	}

	public int updateMeter(AssetConfigForm assetConfigForm, int flag) {
		// TODO Auto-generated method stub
		return	halDAO.updateMeter(assetConfigForm,flag);
		

	}

	public Map<String, String> fetchPmValues(String installedPN, String installedSN, String meterName) {

		return halDAO.fetchPmValues(installedPN, installedSN, meterName);
	}

	public List<FlbSortieArForm> fetchFlbSortieArDetails(String recordID) {
		return halDAO.fetchFLBSortieARDetails(recordID);
	}

	public List<FlbPostFlightDataForm> fetchFLBPostFlightDetails(String recordID) {
		return halDAO.fetchFLBPostFlightDetails(recordID);
	}

	public List<FlbMeterDetailsForm> fetchFLBMeterDetails(String recordID) {
		return halDAO.fetchFLBMeterDetails(recordID);
	}

	public void insertPostFlight(FlbPostFlightDataForm postFlightDataForm) {
		halDAO.insertPostFlight(postFlightDataForm);

	}

	// Inserting new row to sorite AR form
	public void insertSortieArRow(FlbSortieArForm newSortieRow) {
		halDAO.insertSortieArRow(newSortieRow);

	}

	public void updateSortieArData(FlbSortieArForm flbSortieRow) {
		halDAO.updateSortieArData(flbSortieRow);
	}

	public void updatePostFlight(FlbPostFlightDataForm postFlightRow) {
		halDAO.updatePostFlight(postFlightRow);

	}
	public void updateSortieStatus(FlbPostFlightDataForm postFlightRow) {
		// TODO Auto-generated method stub
		halDAO.updateSortieStatus(postFlightRow);
		
	}
	public void updateMeter(InstallableAssetForm installableForm, int flag) {
		halDAO.updateMeter(installableForm,flag);
		
		
	}

	public String getEtdDate(String sortieNo) {
	
		return halDAO.getEtdDate(sortieNo);
	}
}
