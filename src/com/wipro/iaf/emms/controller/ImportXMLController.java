package com.wipro.iaf.emms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;

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
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.dao.HalDao;
import com.wipro.iaf.emms.form.AssetBean;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.ErrorStatusForm;
import com.wipro.iaf.emms.form.TableStatusForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.utility.ReadXml;
import com.wipro.iaf.emms.validator.CommonValidator;

@Controller
@RequestMapping("/importXML")
@Scope(value = "prototype")
public class ImportXMLController {
	
	@Autowired
	ReadXml read;

	@Autowired
	private TableStatusForm tableStatusForm;

	@Autowired
	private HalDao halDAO;

	@Autowired
	private HalService halService;

	@Autowired
	List<AssetBean> assetConfigList;

	@Autowired
	private CommonValidator commonvalidator;

	@RequestMapping(value = "/onLoad", method = RequestMethod.GET)
	public String onPageLoad(ModelMap model) {
		cleanForm();
		
		List<EmmsDataForm> recordIdList = halService.getRecordIdOnView();
		
		List<List> errorStatusList = new ArrayList<List>();
		List<ErrorStatusForm> error = null;
		List<String> list = new ArrayList<String>();
		list.add("1001");
		list.add("1002");
		for (String temp : list) {
			
			try {
				error = halService.fetchErrors(temp);
			}catch(Exception e){e.getMessage();}
			errorStatusList.add(error);
		}
		
		System.out.println(errorStatusList);
		model.addAttribute("errorStatusList", errorStatusList);
		model.addAttribute("recordIdList", recordIdList);
		model.addAttribute("tableStatusForm", this.tableStatusForm);
		model.addAttribute("tableOrder", "");
		model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewRecordID.jsp");
		
		return "basic";
	}

	private void cleanForm() {
		this.tableStatusForm.setRecordId("");
	}

	@RequestMapping(value = "/onSubmit", method = RequestMethod.POST)
	public String submitTableForm(@RequestParam String action,
			@RequestParam String linkSelected,
			@RequestParam(required = false) String deleterecordId,
			@RequestParam(required = false) String tableOrder,
			@ModelAttribute("tableStatusForm") TableStatusForm tableStatusForm,
			ModelMap model, BindingResult bindingResult) throws IOException, SAXException, ParserConfigurationException {
		if (linkSelected.equals(Constants.LISTVIEWIMPORT)) {
			List<List> errorStatusList = new ArrayList<List>();
			List<ErrorStatusForm> error = null;
			List<String> list = new ArrayList<String>();
			list.add("1001");
			list.add("1002");
			for (String temp : list) {
				try{
				error = halService.fetchErrors(temp);
				}catch(Exception e){e.getMessage();}
				errorStatusList.add(error);
			}
			List<EmmsDataForm> recordIdList = halService.getRecordIdOnView();
			model.addAttribute("errorStatusList", errorStatusList);
			model.addAttribute("tableStatusForm", this.tableStatusForm);
			model.addAttribute("recordIdList", recordIdList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewRecordID.jsp");
		} else if (action.equals("Yes")) {
			String status = halService.fetchrecordStatus(deleterecordId);
			if (null != status) {
				bindingResult
						.addError(new ObjectError(
								"tableStatusForm",
								"Record ID "
										+ deleterecordId
										+ " is in-progress, Kindly Delete corresponding data first"));
			} else {
				try{
				halService.deleteRecord(deleterecordId);
				}catch(Exception e){e.getMessage();}
			}
			List<EmmsDataForm> recordIdList = halService.getRecordIdOnView();
			model.addAttribute("recordIdList", recordIdList);
			model.addAttribute("tableStatusForm", tableStatusForm);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewRecordID.jsp");
		} else if (action.equals("Import")) {

			List<File> files = new ArrayList<File>();
			MultipartFile zipFile = tableStatusForm.getZip1();
			System.out.println("Zip file size: " + zipFile.getSize());

			byte[] buffer = new byte[(int) zipFile.getSize()];

			boolean xmlFileCheck = true;
			boolean success = false;

			ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());

			ZipEntry zipEntry = zis.getNextEntry();

			int count = 0;

			while (zipEntry != null) {
				File tempFile = File.createTempFile(zipEntry.getName(), ".xml");
				System.out.println(tempFile.getAbsolutePath());
				System.out.println("Unzipping " + tempFile.getAbsoluteFile());
				if (!zipEntry.isDirectory()) {
					FileOutputStream fos = new FileOutputStream(
							tempFile.getAbsoluteFile());
					int len;
					count++;

					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					// System.out.println(tempFile.getName());
					files.add(tempFile);
					tempFile.deleteOnExit();
					fos.close();
				} else {
					/*
					 * File dir = new File(filePath); dir.mkdir();
					 */
					System.out.println("Bad zip file uploaded");
				}
				zis.closeEntry();
				zipEntry = zis.getNextEntry();
			}

			model.addAttribute("count", count);
			System.out.println("count: " + count);

			zis.closeEntry();
			zis.close();
			System.out.println("Unzipping Complete");

			// String tableName = "";
			String importrecordid = "";
			
			for(File file : files){
				System.out.println(getClass().getName() + "::" + "File Name:"+ file.getName());
				
				  read.readXml(file,tableStatusForm.getRecordId(),file.getName());
				
				
			}
			

		//	halService.importXml(assetConfigList);

			for (File file : files) {

				if (count == 4) {
					System.out.println("into if");
					if (xmlFileCheck == true) {
						xmlFileCheck = commonvalidator.fileNameMatch(file
								.getName());
					}
					System.out.println(getClass().getName() + "::"
							+ "File Name:" + file.getName());
					 /*assetConfigList = ReadXml.readXml(file,
					 tableStatusForm.getRecordId());*/

				}
				model.addAttribute("xmlFileCheck", xmlFileCheck);
				System.out.println("xmlFileCheck: " + xmlFileCheck);

			}

			String tableName = "";
			tableName = "Asset Cfg Plan";
			tableOrder = "6";
			tableStatusForm.setTableOrder(tableOrder);
			if (xmlFileCheck == true) {
				success = true;
			}
			model.addAttribute("success", success);
			// halService.importXml(assetConfigList); 
			halDAO.updateTableOrder(tableStatusForm, tableName);
			model.addAttribute("tableStatusForm", tableStatusForm);
			model.addAttribute("pageVar", "/WEB-INF/jsp/recordIdStatus.jsp");

			/* halDAO.isRecordIdPresent("1001"); */
			/*
			 * List<MultipartFile> files = tableStatusForm.getXml1(); String
			 * tableName = "";
			 * 
			 * for (MultipartFile file : files) {
			 * 
			 * 
			 * assetConfigList = ReadXml.readXml(file, "1001"); }
			 */

		} else if(linkSelected.equals(Constants.ERRORLIST)) {
			
			model.addAttribute("tableStatusForm", tableStatusForm);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListErrors.jsp");
		}
		
		else {
			tableStatusForm.setTableOrder(tableOrder);
			model.addAttribute("tableStatusForm", tableStatusForm);
			model.addAttribute("pageVar", "/WEB-INF/jsp/recordIdStatus.jsp");
		}
		return "basic";
	}

	/*
	 * @RequestMapping(value = "/desc", method = RequestMethod.GET) public
	 * String showError(ModelMap model) { return "ModalContainer"; }
	 */
}
