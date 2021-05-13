package com.wipro.iaf.emms.utility;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wipro.iaf.emms.form.AssetBean;
import com.wipro.iaf.emms.form.HalCatMeterViewBean;
import com.wipro.iaf.emms.form.HalModelViewBean;
import com.wipro.iaf.emms.form.MasterPmBean;
import com.wipro.iaf.emms.form.MasterPmMeterBean;
import com.wipro.iaf.emms.form.MeterBean;
import com.wipro.iaf.emms.form.ModelBean;
import com.wipro.iaf.emms.form.PlusAcaxMasterPmBean;
import com.wipro.iaf.emms.form.PlusaMasterPmMeteriter;
import com.wipro.iaf.emms.form.PlusaMasterPmTimeBean;

public class ModelHandler extends DefaultHandler {
	
	// List of tags present in AMAC XML
	private final String IAF_EMMSHALTRANS="IAF_EMMSHALTRANS";
	private final String IAF_ASSETNUM="IAF_ASSETNUM";
	private final String IAF_ASSET_HIERARCHY_REMOVED="IAF_ASSET_HIERARCHY_REMOVED";
	private final String IAF_CMITEM="IAF_CMITEM";
	private final String IAF_CONFIGID="IAF_CONFIGID";
	private final String IAF_EMMSHALTRANSID="IAF_EMMSHALTRANSID";
	private final String IAF_LOCATION="IAF_LOCATION";
	private final String IAF_MODEL="IAF_MODEL";
	private final String IAF_MODELID="IAF_MODELID";
	private final String IAF_RECORDID="IAF_RECORDID";
	private final String IAF_SERIALNUM="IAF_SERIALNUM"; 
	private final String IAF_STATUS="IAF_STATUS";
	private final String IAF_VARIATION="IAF_VARIATION";
	private final String LANGCODE="LANGCODE";
	private final String IAF_HAL_MODEL_VIEW="IAF_HAL_MODEL_VIEW";
	private final String ABSTRACT="ABSTRACT";
	private final String ALC="ALC";
	private final String BUILDID="BUILDID";
	private final String CATID="CATID";
	private final String CMITEM="CMITEM";
	private final String CONFIGID="CONFIGID";
	private final String ENDITEMMODEL="ENDITEMMODEL";
	private final String END_MODEL="END_MODEL";
	private final String END_VARIATION="END_VARIATION";
	private final String INDENTURE="INDENTURE";
	private final String INDICATOR="INDICATOR";
	private final String ISDEFAULT="ISDEFAULT";
	private final String LCN="LCN";
	private final String MODEL="MODEL";
	private final String MODELDESCRIPTION="MODELDESCRIPTION";
	private final String NAME="NAME";
	private final String NONSERIALIZED="NONSERIALIZED";
	private final String PLUSACMBUILDITEMID="PLUSACMBUILDITEMID";
	private final String PLUSACMMODELID="PLUSACMMODELID";
	private final String POSITION="POSITION";
	private final String VARIATION="VARIATION";
	private final String PLUSACAXMASTERPM="PLUSACAXMASTERPM";
	private final String PLUSACAXMASTERPMID="PLUSACAXMASTERPMID";
	private final String MASTERPM="MASTERPM";
	private final String FREQUENCY="FREQUENCY";
	private final String MASTERPMNUM="MASTERPMNUM";
	private final String WORKTYPE="WORKTYPE";
	private final String MASTERPMMETER="MASTERPMMETER";
	private final String METERNAME="METERNAME";
	private final String  METER="METER";
	private final String MEASUREUNITID="MEASUREUNITID";
	private final String IAF_HAL_CATMETER_VIEW="IAF_HAL_CATMETER_VIEW";
	private final String PLUSACACATID="PLUSACACATID";
	private final String PLUSAMASTERPMTIME="PLUSAMASTERPMTIME";
	private final String FREQUNIT="FREQUNIT";
	private final String ITERATIONSEQ="ITERATIONSEQ";
	private final String PLUSAMASTERPMMETERITER="PLUSAMASTERPMMETERITER";
	private final String ITERATIONFREQ="ITERATIONFREQ";
	private final String PLUSAMASTERPMMETERITERID="PLUSAMASTERPMMETERITERID";

	// String value present between the tags
		private String elementValue;
		
	//// Beans
		private ModelBean modelbean;
		
		private HalModelViewBean halModelViewBean;
		ArrayList<HalModelViewBean> halModelViewBeanList;
		
		private PlusAcaxMasterPmBean plusAcaxMasterPmBean;
		ArrayList<PlusAcaxMasterPmBean> plusAcaxMasterPmBeanList;
		
		private HalCatMeterViewBean halCatMeterViewBean;
		ArrayList<HalCatMeterViewBean> halCatMeterViewBeanList;
		
		private MasterPmBean masterPmBean;
		ArrayList<MasterPmBean> masterPmBeanList;
		
		private MasterPmMeterBean masterPmMeterBean;
		ArrayList<MasterPmMeterBean> masterPmMeterBeanList;
		
		private MeterBean meterBean;
		ArrayList<MeterBean> meterBeanList;
		
		private PlusaMasterPmTimeBean plusaMasterPmTimeBean;
		ArrayList<PlusaMasterPmTimeBean> plusaMasterPmTimeBeanlist;
		
		private PlusaMasterPmMeteriter plusaMasterPmMeteriter;
		ArrayList<PlusaMasterPmMeteriter> plusaMasterPmMeteriterList;
		
		
		public void startDocument(){
			modelbean = new ModelBean();
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			String tag = qName.toUpperCase();
			switch (tag) {
			case IAF_EMMSHALTRANS:
				halModelViewBeanList=new ArrayList<>();
				break;
				
			case IAF_HAL_MODEL_VIEW:
				halModelViewBean=new HalModelViewBean();
				plusAcaxMasterPmBeanList=new ArrayList<>();
				halCatMeterViewBeanList =new ArrayList<>();
				break;
				
			case PLUSACAXMASTERPM:
				plusAcaxMasterPmBean=new PlusAcaxMasterPmBean();
				masterPmBeanList=new ArrayList<>();
				break;
				
			case MASTERPM:
				masterPmBean=new MasterPmBean();
				masterPmMeterBeanList=new ArrayList<>();
				plusaMasterPmTimeBeanlist=new ArrayList<>();
				plusaMasterPmMeteriterList=new ArrayList<>();
				break;
				
			case MASTERPMMETER:
				masterPmMeterBean=new MasterPmMeterBean();
				meterBeanList=new ArrayList<>();
				break;
				
			case METER:
				meterBean=new MeterBean();
				break;
				
			case IAF_HAL_CATMETER_VIEW:
				halCatMeterViewBean=new HalCatMeterViewBean();
				break;
				
			case PLUSAMASTERPMTIME:
				//System.out.println("inside plusmasterpmtime");
				plusaMasterPmTimeBean=new PlusaMasterPmTimeBean();
				break;
				
			case PLUSAMASTERPMMETERITER:
				plusaMasterPmMeteriter=new PlusaMasterPmMeteriter();
				break;
				
			case FREQUNIT:
				System.out.println("element value" + elementValue);
				break;
					
			}
		}
		
		public void characters(char ch[], int start, int length) {
			elementValue = new String(ch, start, length);
		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			String tag = qName.toUpperCase();
			switch (tag) {
			
			case IAF_ASSETNUM:
				modelbean.setIafAssetNum(elementValue);
				break;
				
			case IAF_ASSET_HIERARCHY_REMOVED:
				modelbean.setIafAssetHierarchyRemoved(elementValue);
				break;
				
			case	 IAF_CMITEM:
				modelbean.setIafCmItem(elementValue);
				break;
				
			case	 IAF_CONFIGID:
				modelbean.setIafConfigId(elementValue);
				break;
				
			case	IAF_EMMSHALTRANSID:
				modelbean.setIafEmmsHalTransId(elementValue);
				break;
				
			case	 IAF_LOCATION:
				modelbean.setIafLocation(elementValue);
				break;
				
			case	 IAF_MODEL:
				modelbean.setIafModel(elementValue);
				break;
				
			case IAF_MODELID:
				modelbean.setIafModelId(elementValue);
				break;
				
			case IAF_RECORDID:
				modelbean.setIafRecordId(elementValue);
				break;
				
			case IAF_SERIALNUM: 
				modelbean.setIafSerialNum(elementValue);
				break;
				
			case IAF_STATUS:
				modelbean.setIafStatus(elementValue);
				break;
				
			case IAF_VARIATION:
				modelbean.setIafVariation(elementValue);
				break;
				
			case LANGCODE:
				modelbean.setLangCode(elementValue);
				break;
				
			case IAF_HAL_MODEL_VIEW:
				halModelViewBeanList.add(halModelViewBean);
				modelbean.setHalModelViewBeanList(halModelViewBeanList);
				break;
				
			case ABSTRACT:
				halModelViewBean.setAbstract1(elementValue);
				break;
				
			case ALC:
				halModelViewBean.setAlc(elementValue);
				break;
				
			case BUILDID:
				halModelViewBean.setBuildId(elementValue);
				break;
				
			case CATID:
				halModelViewBean.setCatId(elementValue);
				break;
				
			case CMITEM:
				halModelViewBean.setCmItem(elementValue);
				break;
				
			case CONFIGID:
				halModelViewBean.setConfigId(elementValue);
				break;
				
			case ENDITEMMODEL:
				halModelViewBean.setEndItemModel(elementValue);
				break;
				
			case END_MODEL:
				halModelViewBean.setEndModel(elementValue);
				break;
				
			case END_VARIATION:
				halModelViewBean.setEndVariation(elementValue);
				break;
				
			case INDENTURE:
				halModelViewBean.setIndenture(elementValue);
				break;
				
			case INDICATOR:
				halModelViewBean.setIndicator(elementValue);
				break;
				
			case ISDEFAULT:
				halModelViewBean.setIsDefault(elementValue);
				break;
				
			case LCN:
				halModelViewBean.setLcn(elementValue);
				break;
				
			case MODEL:
				halModelViewBean.setModel(elementValue);
				break;
				
			case MODELDESCRIPTION:
				halModelViewBean.setModelDescription(elementValue);
				break;
				
			case NAME:
				halModelViewBean.setName(elementValue);
				break;
				
			case NONSERIALIZED:
				halModelViewBean.setNonSerialized(elementValue);
				break;
				
			case PLUSACMBUILDITEMID:
				halModelViewBean.setPlusAcmBuildItemId(elementValue);
				break;
				
			case PLUSACMMODELID:
				halModelViewBean.setPlusAcmModelId(elementValue);
				break;
				
			case POSITION:
				halModelViewBean.setPosition(elementValue);
				break;
				
			case VARIATION:
				halModelViewBean.setVariation(elementValue);
				break;
				
			case PLUSACAXMASTERPM:
				plusAcaxMasterPmBeanList.add(plusAcaxMasterPmBean);
				halModelViewBean.setPlusAcaxMasterPmBeanList(plusAcaxMasterPmBeanList);
				break;
				
			case PLUSACAXMASTERPMID:
				plusAcaxMasterPmBean.setPlusAcaxMasterPmId(elementValue);
				break;
				
			case MASTERPM:
				masterPmBeanList.add(masterPmBean);
				plusAcaxMasterPmBean.setMasterPmBeanList(masterPmBeanList);
				break;
				
			case FREQUENCY:
				if(plusaMasterPmTimeBean!=null){
					plusaMasterPmTimeBean.setFrequency(elementValue);
				}
				if(plusaMasterPmMeteriter!=null){
					plusaMasterPmMeteriter.setFrequency(elementValue);
				}
				if(masterPmBean!=null){
				masterPmBean.setFrequency(elementValue);
				}
				if(masterPmMeterBean!=null) {
					masterPmMeterBean.setFrequency(elementValue);
				}
				break;
				
			case MASTERPMNUM:
				masterPmBean.setMasterPmNum(elementValue);
				break;
				
			case WORKTYPE:
				masterPmBean.setWorktype(elementValue);
				break;
				
			case MASTERPMMETER:
				masterPmMeterBeanList.add(masterPmMeterBean);
				masterPmBean.setMasterPmMeterBeanList(masterPmMeterBeanList);
				break;
				
			case METERNAME:
				if(masterPmMeterBean!=null){
					masterPmMeterBean.setMeterName(elementValue);
				}
				if(plusaMasterPmMeteriter!=null){
					plusaMasterPmMeteriter.setMetername(elementValue);
				}
				if(halCatMeterViewBean!=null){
					halCatMeterViewBean.setMeterName(elementValue);
				}
				break;
				
			case  METER:
				meterBeanList.add(meterBean);
				masterPmMeterBean.setMeterBeanList(meterBeanList);
				break;
				
			case MEASUREUNITID:
				if(meterBean!=null){
					meterBean.setMeasureUnitId(elementValue);
				}
				if(halCatMeterViewBean!=null){
					halCatMeterViewBean.setMeasureUnitId(elementValue);	
				}
				break;
			case IAF_HAL_CATMETER_VIEW:
				halCatMeterViewBeanList.add(halCatMeterViewBean);
				halModelViewBean.setHalCatMeterViewBeanList(halCatMeterViewBeanList);
				break;
				
			case PLUSACACATID:
				halCatMeterViewBean.setPlusAcaCatId(elementValue);
				break;
				
			case PLUSAMASTERPMTIME:
				plusaMasterPmTimeBeanlist.add(plusaMasterPmTimeBean);
				masterPmBean.setPlusaMasterPmTimeBeanList(plusaMasterPmTimeBeanlist);
				break;
				
			case PLUSAMASTERPMMETERITER:
				plusaMasterPmMeteriterList.add(plusaMasterPmMeteriter);
				masterPmBean.setPlusaMasterPmMeteriterList(plusaMasterPmMeteriterList);
				
			case FREQUNIT:
				System.out.println("frequnit");
				if(plusaMasterPmTimeBean!=null){
				plusaMasterPmTimeBean.setFrequnit(elementValue);
				}
				if(masterPmBean!=null){
					masterPmBean.setFrequnit(elementValue);
				}
				break;
				
			case ITERATIONSEQ:
				if(plusaMasterPmMeteriter!=null){
				plusaMasterPmMeteriter.setIterationseq(elementValue);	
				}
				if(plusaMasterPmTimeBean!=null){
				plusaMasterPmTimeBean.setIterationseq(elementValue);
				}
				break;
				
			case ITERATIONFREQ:
				if(plusaMasterPmMeteriter!=null){
					plusaMasterPmMeteriter.setIterationfreq(elementValue);	
					}
					if(plusaMasterPmTimeBean!=null){
					plusaMasterPmTimeBean.setIterationfreq(elementValue);
					}
					break;
				
			case PLUSAMASTERPMMETERITERID:
				plusaMasterPmMeteriter.setPlusamasterpmmeteriterid(elementValue);
				break;
				
				
			}
		}
		
		public ModelBean getIafAssetNum() {
			return modelbean;
		}
			
		
}
