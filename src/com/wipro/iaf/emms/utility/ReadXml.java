package com.wipro.iaf.emms.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wipro.iaf.emms.form.AssetBean;
import com.wipro.iaf.emms.form.FlbBean;
import com.wipro.iaf.emms.form.ModelBean;
import com.wipro.iaf.emms.service.HalService;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ReadXml extends DefaultHandler {

	@Autowired
	private HalService halService;

	
	List<AssetBean> assetConfigList=new ArrayList<>();
	List<ModelBean> modelList=new ArrayList<>();
	List<FlbBean> flbList=new ArrayList<>();
	

	public void readXml(File file, String recordId, String fileName)
			throws SAXException, IOException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
		
		if (fileName.contains("AMAC")) {
			AMACHandler amacparser = new AMACHandler();
			saxParser.parse(file, amacparser);
			AssetBean data = amacparser.getIaf_AssetNum();
			assetConfigList.add(data);
			halService.importXml(assetConfigList);	
		}
		
		else if(fileName.contains("MODEL")){ 
		  ModelHandler modelparser=new ModelHandler();
		  saxParser.parse(file,modelparser); ModelBean data =
		  modelparser.getIafAssetNum(); modelList.add(data);
		  System.out.println("model beans values" + data.toString());
		  halService.modelXml(modelList); }
		 
		  
		/*
		 * if(fileName.contains("FLB")){ FlbHandler flbparser=new FlbHandler();
		 * saxParser.parse(file,flbparser); FlbBean data = flbparser.getIafAssetNum();
		 * flbList.add(data); System.out.println("flb beans values" + data.toString());
		 * halService.modelXml(modelList); }
		 */

		
	}
}