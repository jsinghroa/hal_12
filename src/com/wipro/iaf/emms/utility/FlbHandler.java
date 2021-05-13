package com.wipro.iaf.emms.utility;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wipro.iaf.emms.form.FlbBean;
import com.wipro.iaf.emms.form.PlusAcmBuildBean;
import com.wipro.iaf.emms.form.PlusAcmBuildItemBean;
import com.wipro.iaf.emms.form.PlusaFlbBean;
import com.wipro.iaf.emms.form.PlusaFlbSetupBean;
import com.wipro.iaf.emms.form.PlusaFlbSetupMeterBean;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FlbHandler extends DefaultHandler {
	
	// List of tags present in FLB XML
	private final String IAF_EMMSHALTRANS="IAF_EMMSHALTRANS";
	private final String IAF_ASSETNUM="IAF_ASSETNUM";
	private final String IAF_CMITEM="IAF_CMITEM";
	//private final String IAF_CONFIGID="IAF_CONFIGID";
	private final String IAF_EMMSHALTRANSID="IAF_EMMSHALTRANSID";
	private final String IAF_EXPSYSDATE="IAF_EXPSYSDATE";
	private final String IAF_INDUCTIONDATE="IAF_INDUCTIONDATE";
	private final String IAF_LOCATION="IAF_LOCATION";
	private final String IAF_MODEL="IAF_MODEL";
	private final String IAF_MODELID="IAF_MODELID";
	private final String IAF_RECORDID="IAF_RECORDID";
	private final String IAF_RECORDTYPE="IAF_RECORDTYPE";
	private final String IAF_RETURNDATE="IAF_RETURNDATE";
	private final String IAF_SERIALNUM="IAF_SERIALNUM";
	private final String IAF_SIGNALOUTDATE="IAF_SIGNALOUTDATE";
	//private final String IAF_STATUS="IAF_STATUS";
	private final String IAF_VALSTATUS="IAF_VALSTATUS";
	//private final String IAF_VARIATION="IAF_VARIATION";
	private final String LANGCODE="LANGCODE";
	private final String PLUSAFLB="PLUSAFLB";
	private final String ACSTATUS="ACSTATUS";
	private final String ASSETNUM="ASSETNUM";
	private final String DESCRIPTION="DESCRIPTION";
	private final String FLBNUM="FLBNUM";
	private final String FLBTYPE="FLBTYPE";
	private final String IAF_HH_ACFLIGHTHRS="IAF_HH_ACFLIGHTHRS";
	private final String MODEL="MODEL";
	private final String ORGID="ORGID";
	private final String PLUSAFLBID="PLUSAFLBID";
	private final String REVISIONNUM="REVISIONNUM";
	private final String SITEID="SITEID";
	private final String STATUS="STATUS";
	private final String STATUSDATE="STATUSDATE";
	private final String VARIATION="VARIATION";
	private final String PLUSAFLBSETUP="PLUSAFLBSETUP";
	private final String CONFIGID="CONFIGID";
	private final String CREATEPFLINS="CREATEPFLINS";
	private final String IAF_CLASS="IAF_CLASS";
	private final String MEASUREUNITID="MEASUREUNITID";
	private final String MODELID="MODELID";
	private final String PLUSAFLBSETUPID="PLUSAFLBSETUPID";
	private final String STATSYMBREQ="STATSYMBREQ";
	private final String PLUSAFLBSETUPMETER="PLUSAFLBSETUPMETER";
	private final String BUILDID="BUILDID";
	private final String IAF_FDRPARAMETER="IAF_FDRPARAMETER";
	private final String IAF_MODIFIEDBY="IAF_MODIFIEDBY";
	private final String IAF_SEQUENCE="IAF_SEQUENCE";
	private final String ISDEFAULT="ISDEFAULT";
	private final String METERNAME="METERNAME";
	private final String METERREADING="METERREADING";
	private final String PLUSAFLBSETUPMETERID="PLUSAFLBSETUPMETERID";
	private final String READINGTYPE="READINGTYPE"; 
	private final String WHEREUSED="WHEREUSED"; 
	private final String PLUSACMBUILD="PLUSACMBUILD";
	private final String LCN="LCN";
	private final String PLUSACMBUILDID="PLUSACMBUILDID";
	private final String PLUSACMBUILDITEM="PLUSACMBUILDITEM";
	private final String NAME="NAME";
	private final String PLUSACMBUILDITEMID="PLUSACMBUILDITEMID";
	
	
	// String value present between the tags
			private String elementValue;
			
		//// Beans
			private FlbBean flbbean;
			
			private PlusaFlbBean plusaFlbBean;
			ArrayList<PlusaFlbBean> plusaFlbBeanList=new ArrayList<>();
			
			private PlusaFlbSetupBean plusaFlbSetupBean;
			ArrayList<PlusaFlbSetupBean> plusaFlbSetupBeanList=new ArrayList<>();
			
			private PlusaFlbSetupMeterBean plusaFlbSetupMeterBean;
			ArrayList<PlusaFlbSetupMeterBean> plusaFlbSetupMeterBeanList=new ArrayList<>();
			
			private PlusAcmBuildBean plusAcmBuildBean;
			ArrayList<PlusAcmBuildBean> plusAcmBuildBeanList=new ArrayList<PlusAcmBuildBean>();
			
			private PlusAcmBuildItemBean plusAcmBuildItemBean;
			ArrayList<PlusAcmBuildItemBean> plusAcmBuildItemBeanList=new ArrayList<>();
			
			public void startDocument(){
				flbbean = new FlbBean();
			}

			public void startElement(String uri, String localName, String qName,
					Attributes attributes) throws SAXException {
				String tag = qName.toUpperCase();
				switch (tag) {
				case IAF_EMMSHALTRANS:
					plusaFlbBeanList=new ArrayList<>();
					break;
				
				case PLUSAFLB:
					plusaFlbBean=new PlusaFlbBean();
					plusaFlbSetupBeanList=new ArrayList<>();
					break;
				
				case PLUSAFLBSETUP:
					plusaFlbSetupBean=new PlusaFlbSetupBean();
					plusaFlbSetupMeterBeanList=new ArrayList<>();
					break;
					
				case PLUSAFLBSETUPMETER:
					plusaFlbSetupMeterBean=new PlusaFlbSetupMeterBean();
					plusAcmBuildBeanList=new ArrayList<>();
					break;
				
				case PLUSACMBUILD:
					plusAcmBuildBean=new PlusAcmBuildBean();
					plusAcmBuildItemBeanList=new ArrayList<>();
					break;
					
				case PLUSACMBUILDITEM:
						plusAcmBuildItemBean=new PlusAcmBuildItemBean();
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
				
				case PLUSAFLB:
					plusaFlbBeanList.add(plusaFlbBean);
					flbbean.setPlusaFlbBeanList(plusaFlbBeanList);
					break;
				
				case ACSTATUS:
					plusaFlbBean.setAcStatus(elementValue);
					break;
					
				case ASSETNUM:
					plusaFlbBean.setAssetNum(elementValue);
					break;
					
				case DESCRIPTION:
					if(plusaFlbSetupBean!=null) {
					plusaFlbSetupBean.setDescription(elementValue);
					}
					plusaFlbBean.setDescription(elementValue);
					break;
					
				case FLBNUM:
					plusaFlbBean.setFlbNum(elementValue);
					break;
					
				case FLBTYPE:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setFlbType(elementValue);
					}
					else if(plusaFlbSetupMeterBean!=null) {
						plusaFlbSetupMeterBean.setFlbType(elementValue);
					}
					plusaFlbBean.setFlbType(elementValue);
					break;
					
				case IAF_HH_ACFLIGHTHRS:
					plusaFlbBean.setIaf_hh_AcFlightHrs(elementValue);
					break;
					
				case MODEL:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setModel(elementValue);
					}
					plusaFlbBean.setModel(elementValue);
					break;
					
				case ORGID:
					plusaFlbBean.setOrgId(elementValue);
					break;
					
				case PLUSAFLBID:
					plusaFlbBean.setPlusaFlbId(elementValue);
					break;
					
				case REVISIONNUM:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setRevisionNum(elementValue);
					}
					plusaFlbBean.setRevisionNum(elementValue);
					break;
					
				case SITEID:
					plusaFlbBean.setSiteId(elementValue);
					break;
					
				case STATUS:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setStatus(elementValue);
					}
					plusaFlbBean.setStatus(elementValue);
					break;
					
				case STATUSDATE:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setStatusDate(elementValue);
					}
					plusaFlbBean.setStatusDate(elementValue);
					break;
					
				case VARIATION:
					if(plusaFlbSetupBean!=null) {
						plusaFlbSetupBean.setVariation(elementValue);
					}
					plusaFlbBean.setVariation(elementValue);
					break;
					
				case PLUSAFLBSETUP:
					plusaFlbSetupBeanList.add(plusaFlbSetupBean);
					plusaFlbBean.setPlusaFlbSetupBeanList(plusaFlbSetupBeanList);
					break;
					
				case CONFIGID:
					plusaFlbSetupBean.setConfigId(elementValue);
					break;
					
				case CREATEPFLINS:
					plusaFlbSetupBean.setCreatepFlins(elementValue);
					break;
					
				case IAF_CLASS:
					plusaFlbSetupBean.setIaf_Class(elementValue);
					break;
					
				case MEASUREUNITID:
					plusaFlbSetupBean.setMeasureUnitId(elementValue);
					break;
					
				case MODELID:
					plusaFlbSetupBean.setModelId(elementValue);
					break;
					
				case PLUSAFLBSETUPID:
					plusaFlbSetupBean.setPlusaFlbSetupId(elementValue);
					break;
					
				case STATSYMBREQ:
					plusaFlbSetupBean.setStatSymbReq(elementValue);
					break;
					
				case PLUSAFLBSETUPMETER:
					plusaFlbSetupMeterBeanList.add(plusaFlbSetupMeterBean);
					plusaFlbSetupBean.setPlusaFlbSetupMeterBeanList(plusaFlbSetupMeterBeanList);
					break;
				
				case BUILDID:
					plusaFlbSetupMeterBean.setBuildId(elementValue);
					break;
					
				case IAF_FDRPARAMETER:
					plusaFlbSetupMeterBean.setIaf_FdrParameter(elementValue);
					break;
					
				case IAF_MODIFIEDBY:
					plusaFlbSetupMeterBean.setIaf_ModifiedBy(elementValue);
					break;
					
				case IAF_SEQUENCE:
					plusaFlbSetupMeterBean.setIaf_Sequence(elementValue);
					break;
					
				case ISDEFAULT:
					plusaFlbSetupMeterBean.setIsDefault(elementValue);
					break;
					
				case METERNAME:
					plusaFlbSetupMeterBean.setMeterName(elementValue);
					break;
					
				case METERREADING:
					plusaFlbSetupMeterBean.setMeterReading(elementValue);
					break;
					
				case PLUSAFLBSETUPMETERID:
					plusaFlbSetupMeterBean.setPlusaFlbSetupMeterId(elementValue);
					break;
					
				case READINGTYPE:
					plusaFlbSetupMeterBean.setReadingType(elementValue);
					break;
					
				case WHEREUSED:
					plusaFlbSetupMeterBean.setWhereUsed(elementValue);
					break;
					
				case PLUSACMBUILD:
					plusAcmBuildBeanList.add(plusAcmBuildBean);
					plusaFlbSetupMeterBean.setPlusAcmBuildBeanList(plusAcmBuildBeanList);
					break;
					
				case LCN:
					plusAcmBuildBean.setLcn(elementValue);
					break;
					
				case PLUSACMBUILDID:
					plusAcmBuildBean.setPlusAcmBuildId(elementValue);
					break;
					
				case PLUSACMBUILDITEM:
					plusAcmBuildItemBeanList.add(plusAcmBuildItemBean);
					plusAcmBuildBean.setPlusAcmBuildItemBeanList(plusAcmBuildItemBeanList);
					break;
					
				case NAME:
					plusAcmBuildItemBean.setName(elementValue);
					break;
					
				case PLUSACMBUILDITEMID:
					plusAcmBuildItemBean.setPlusAcmBuildItemId(elementValue);
					break;
				}
			}
					
			public FlbBean getIafAssetNum() {
				return flbbean;
			}
	

}
