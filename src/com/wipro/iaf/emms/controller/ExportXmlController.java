package com.wipro.iaf.emms.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.iaf.emms.dao.HalDao;
import com.wipro.iaf.emms.form.Amac;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.ErrorStatusForm;
import com.wipro.iaf.emms.form.Meter;
import com.wipro.iaf.emms.form.Model;
import com.wipro.iaf.emms.form.TableStatusForm;
import com.wipro.iaf.emms.service.HalService;

@Controller
@RequestMapping("/exportXML")
@Scope(value = "prototype")
public class ExportXmlController {
	@Autowired
	private FLBController flbController;

	@Autowired
	private HalService halService;

	@Autowired
	InstallableAssetController installController;

	@Autowired
	private PMController pmController;

	@Autowired
	private AssetController assetController;

	@Autowired
	private MeterController meterController;

	@Autowired
	private EmmsDataForm emmsDataForm;
	
	@Autowired
	private TableStatusForm tableStatusForm;

	@Autowired
	private HalDao halDAO;

	List<EmmsDataForm> recordIdList;
	
	@RequestMapping(value = "/onLoad", method = RequestMethod.GET)
	public String onPageLoad(ModelMap model) {
		List<EmmsDataForm> recordIdList = null;
		try{
		 recordIdList = halDAO.getRecordIdOnExport();
		}catch(Exception e){e.getMessage();}
		this.recordIdList=recordIdList;
		System.out.println(recordIdList.toString()+":"+this.recordIdList.toString());
		model.addAttribute("recordIdList", recordIdList);
		model.addAttribute("tableStatusForm", this.tableStatusForm);
		model.addAttribute("tableOrder", "");
		model.addAttribute("pageVar", "/WEB-INF/jsp/Export.jsp");
		return "basic";
	}
	
	
	
	@RequestMapping(value = { "/export" }, method = RequestMethod.POST)
	public String onSubmit(@RequestParam String action,
			@RequestParam String linkSelected,
			@ModelAttribute("tableStatusForm") TableStatusForm tableStatusForm,
			ModelMap model, BindingResult bindingResult) {
		

		List<EmmsDataForm> recordIdList = halDAO.getRecordIdOnExport();
		if (action.equals("Export")) {
			/*try{
			halDAO.updatetableOrder();
			}catch(Exception e){e.getMessage();}*/
			String filename = "C:\\EMP.xml";
			
			try {
				JAXBContext context=JAXBContext.newInstance(Amac.class);
				Marshaller marshaller=context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				List<String> meterList=new ArrayList<String>();
				meterList.add("calss");
				meterList.add("adjbajs");
				Meter meter= new Meter("hello",meterList);
				Model model1=new Model("1000","23",meter);
				
				
				Amac a=new Amac(1,"sarab",model1);
				try {
					FileOutputStream fout=new FileOutputStream(filename);
					marshaller.marshal(a,fout);
					fout.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("inside export");
			model.addAttribute("recordIdList", recordIdList);
			model.addAttribute("tableStatusForm", tableStatusForm);
			model.addAttribute("pageVar", "/WEB-INF/jsp/Export.jsp");
			return "basic";
	
	}
	
		model.addAttribute("recordIdList", this.recordIdList);
		model.addAttribute("tableStatusForm", tableStatusForm);
		model.addAttribute("pageVar", "/WEB-INF/jsp/Export.jsp");
		return "basic";
	}

}
