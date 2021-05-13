package com.wipro.iaf.emms.utility;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wipro.iaf.emms.form.AssetBean;
import com.wipro.iaf.emms.form.AssetHierarchyViewBean;
import com.wipro.iaf.emms.form.AssetMeterVwBean;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AMACHandler extends DefaultHandler{
	
	// List of tags present in AMAC XML
		
		private final String DESCRIPTION = "DESCRIPTION";
		private final String IAF_ASSETNUM="IAF_ASSETNUM";
		private final String IAF_ASSETHIERARCHYREMOVED="IAF_ASSETHIERARCHYREMOVED";
		private final String IAF_CMITEM="IAF_CMITEM";
		private final String IAF_CONFIGID="IAF_CONFIGID";
		private final String IAF_EMMSHALTRANSID="IAF_EMMSHALTRANSID";
		private final String IAF_INDUCTIONDATE="IAF_INDUCTIONDATE";
		private final String IAF_LOCATION="IAF_LOCATION";
		private final String IAF_MODEL="IAF_MODEL";
		private final String IAF_MODELID="IAF_MODELID";
		private final String IAF_RECORDID="IAF_RECORDID";
		private final String IAF_RECORDTYPE="IAF_RECORDTYPE"; 
		private final String IAF_SERIALNUM="IAF_SERIALNUM";
		private final String IAF_STATUS="IAF_STATUS";
		private final String IAF_VARIATION="IAF_VARIATION";
		private final String IAF_HAL_ASSETHIERARCHY_VIEW="IAF_HAL_ASSETHIERARCHY_VIEW";
		private final String AIRCRAFT="AIRCRAFT";
		private final String ASSETID="ASSETID";
		private final String ASSETNUM="ASSETNUM";
		private final String BUILDITEMID="BUILDITEMID";
		private final String CMITEM="CMITEM";
		private final String CONFIGID="CONFIGID";
		private final String ENDASSET="END_ASSET";
		private final String ENDASSETID="END_ASSETID";
		private final String IAFRECORDID="IAF_RECORDID";
		private final String INDENTURE="INDENTURE";
		private final String INDICATOR="INDICATOR";
		private final String ISDEFAULT="ISDEFAULT";
		private final String ITEMNUM="ITEMNUM";
		private final String LABEL="LABEL";
		private final String LCN="LCN";
		private final String MODEL="MODEL";
		private final String MODELID="MODELID";
		private final String NAME="NAME";
		private final String NONTRACKED="NONTRACKED";
		private final String ONTIME="ONTIME";
		private final String PARENTASSETID="PARENTASSETID";
		private final String PLUSACMBUILDID="PLUSACMBUILDID";
		private final String PLUSASAONOFFID="PLUSASAONOFFID";
		private final String POSITION="POSITION";
		private final String SERIALNUM="SERIALNUM";
		private final String VARIATION="VARIATION";
		private final String IAF_HAL_ASSETMETER_VW="IAF_HAL_ASSETMETER_VW";
		private final String CURRENTVALUE="CURRENTVALUE";
		private final String MEASUREUNITID="MEASUREUNITID";
		private final String METERNAME="METERNAME";
		private final String PLUSAANCHORCOUNT="PLUSAANCHORCOUNT";
		private final String PLUSAANCHORDATE="PLUSAANCHORDATE";
		private final String IAF_EMMSHALTRANS="IAF_EMMSHALTRANS";
			
	
	
	
	// String value present between the tags
	private String elementValue;
	
	//// Beans
	private AssetBean bean;
	
	private ArrayList<AssetHierarchyViewBean> assetHierarchyViewList;
	private AssetHierarchyViewBean assetHierarchyView;
	
	private ArrayList<AssetMeterVwBean> assetMeterVWList;
	private AssetMeterVwBean assetMeterVW;
	
	
	
	public void startDocument(){
		bean = new AssetBean();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String tag = qName.toUpperCase();
		switch (tag) {
		case IAF_EMMSHALTRANS:
			assetHierarchyViewList = new ArrayList<>();
			break;
		
		case IAF_HAL_ASSETHIERARCHY_VIEW:
			assetHierarchyView = new AssetHierarchyViewBean();
			assetMeterVWList = new ArrayList<>();
			
			break;
		case IAF_HAL_ASSETMETER_VW:
			assetMeterVW = new AssetMeterVwBean();
			
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
			bean.setIaf_AssetNum(elementValue);
			break;
			
		case IAF_ASSETHIERARCHYREMOVED:
			bean.setIaf_AssetHierarchyRemoved(elementValue);
			break;
		case IAF_CMITEM:
			bean.setIaf_CmItem(elementValue);
			break;
			
		case IAF_CONFIGID:
			bean.setIaf_ConfigId(elementValue);
			break;
			
		case IAF_EMMSHALTRANSID:
			bean.setIaf_EmmsHalTransId(elementValue);
			break;
			
		case IAF_INDUCTIONDATE:
			bean.setInductionDate(elementValue);
			break;
			
		case IAF_LOCATION:
			bean.setIaf_Location(elementValue);
			break;
			
		case IAF_MODEL:
			bean.setIaf_Model(elementValue);
			
		case IAF_MODELID:
			bean.setIaf_ModelId(elementValue);
			break;
			
		case IAF_RECORDID:
			if(assetHierarchyView!=null){
				assetHierarchyView.setIafRecordId(elementValue);
			}
			if(bean!=null){
			bean.setIaf_RecordId(elementValue);
			}
			break;
			
		case IAF_RECORDTYPE:
			bean.setIaf_RecordType(elementValue);
			break;
			
		case IAF_SERIALNUM:
			bean.setIaf_SerialNum(elementValue);
			break;
			
		case IAF_STATUS:
			bean.setIaf_Status(elementValue);
			break;
		case IAF_VARIATION:
			bean.setIaf_Variation(elementValue);
			break;
			
			////////////////
		case IAF_HAL_ASSETHIERARCHY_VIEW:
			assetHierarchyViewList.add(assetHierarchyView);
			bean.setAssetHierarchyViewList(assetHierarchyViewList);
			break;
			
		
		case AIRCRAFT:
			assetHierarchyView.setAircraft(elementValue);
			break;	
		case ASSETID:
			assetHierarchyView.setAssetId(elementValue);
			break;
		case ASSETNUM:
			assetHierarchyView.setAssetNum(elementValue);
			break;
			
		case BUILDITEMID:
			assetHierarchyView.setBuildItemId(elementValue);
			break;
			
		case CMITEM:
			assetHierarchyView.setCmItem(elementValue);
			break;
			
		case CONFIGID:
			assetHierarchyView.setConfigId(elementValue);
			break;
			
		case ENDASSET:
			assetHierarchyView.setEndAsset(elementValue);
				break;
				
		case ENDASSETID:
			assetHierarchyView.setEndAssetId(elementValue);
				break;
				
		case INDENTURE:
			assetHierarchyView.setIndenture(elementValue);
			break;
			
		case INDICATOR:
			assetHierarchyView.setIndicator(elementValue);
			break;
			
		case ISDEFAULT:
			assetHierarchyView.setIsDefault(elementValue);
			break;
				
		case ITEMNUM:
			assetHierarchyView.setItemNum(elementValue);
			break;
			
		case LABEL:
			assetHierarchyView.setLabel(elementValue);
			break;
			
		case LCN:
			assetHierarchyView.setLcn(elementValue);
			break;
			
		case MODEL:
			assetHierarchyView.setModel(elementValue);
			break;
			
		case MODELID:
			assetHierarchyView.setModelId(elementValue);
			break;
			
		case NAME:
			assetHierarchyView.setName(elementValue);
			break;
			
		case NONTRACKED:
			assetHierarchyView.setNonTracked(elementValue);
			break;
			
		case ONTIME:
			assetHierarchyView.setOnTime(elementValue);
			break;
			
		case PARENTASSETID:
			assetHierarchyView.setParentAssetId(elementValue);
			break;
			
		case PLUSACMBUILDID:
			assetHierarchyView.setPlusAcmBuildId(elementValue);
			break;
			
		case PLUSASAONOFFID:
			assetHierarchyView.setPlusAsaOnoffId(elementValue);
			break;
			
		case POSITION:
			assetHierarchyView.setPosition(elementValue);
			break;
			
		case SERIALNUM:
			assetHierarchyView.setSerialNum(elementValue);
			break;
				
		case VARIATION:
			assetHierarchyView.setVariation(elementValue);
				break;
				
			//////	
		case IAF_HAL_ASSETMETER_VW:
			assetMeterVWList.add(assetMeterVW);
			assetHierarchyView.setAssetMeterVWList(assetMeterVWList);
			break;
			
		case CURRENTVALUE:
			assetMeterVW.setCurrentValue(elementValue);
			break;
			
		case MEASUREUNITID:
			assetMeterVW.setMeasureUnitId(elementValue);
			break;
		case METERNAME:
			assetMeterVW.setMeterName(elementValue);
			break;
			
		case PLUSAANCHORCOUNT:
			assetMeterVW.setPlusaAnchorCount(elementValue);
			break;
			
			
		case PLUSAANCHORDATE:
			assetMeterVW.setPlusaAnchorDate(elementValue);
			break;
		}
		
			
	}
	public AssetBean getIaf_AssetNum() {
		return bean;
	}
		

}

