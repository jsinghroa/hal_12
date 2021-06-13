package com.wipro.iaf.emms.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.InstallableAssetForm;
import com.wipro.iaf.emms.form.MeterDetailsForm;
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.validator.AssetConfigValidator;
import com.wipro.iaf.emms.validator.CommonValidator;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.MeterDetailsValidator;

@Controller
@RequestMapping("/METER")
public class MeterController {

	@Autowired
	private FLBController flbController;

	@Autowired
	List<MeterDetailsForm> meterFormList;

	@Autowired
	AssetController assetController;

	@Autowired
	PMController pmController;

	@Autowired
	HalService halService;

	@Autowired
	InstallableAssetController installablecontroller;

	@Autowired
	private EmmsDataForm emmsDataForm;

	@Autowired
	private MeterDetailsValidator meterValidator;

	CommonValidator commonValidator = new CommonValidator();
	
	
	
	@RequestMapping(value = { "/saveMeter" }, method = RequestMethod.GET)
	public List<MeterDetailsForm> fetchMeterDetails(String recordID,
			EmmsDataForm emmsDataForm) {
		this.emmsDataForm = emmsDataForm;
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		try{
		meterFormList = halService.fetchMeterDetails(recordID);
		}catch(Exception e){e.getMessage();}
		System.out.println("emmsDataForm=" + emmsDataForm);
		System.out.println("this.emmsDataForm=" + this.emmsDataForm);
		System.out.println("meterList:" + meterFormList.toString());
		return meterFormList;
	}

	@RequestMapping(value = "/saveMeter", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm,
			BindingResult bindingResult, ModelMap model,
			@RequestParam String action, @RequestParam String linkSelected,HttpServletResponse response) {
		if (linkSelected.equals(Constants.LISTVIEW)) {
			List<EmmsDataForm> emmsDataFormList = halService
					.getEmmsDataOnView();
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		} else {

			if (linkSelected.equals(Constants.ASSETCONFIG)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try{
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
					}catch(Exception e){e.getMessage();}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {
				
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try{
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					for(int i=0;i<emmsDataForm.getAssetFormList().size();i++){
						if(emmsDataForm.getAssetFormList().get(i).getErrorStatus()!=null){
							if (emmsDataForm.getAssetFormList().get(i).getErrorStatus().equals(Constants.VALIDATEDWITHWARNING)
								&& emmsDataForm.getAssetFormList().get(i).getIndicator().equals("I") ){
								emmsDataForm.setInstallableFormList(installablecontroller
								             .fetchInstallableDetails(emmsDataForm
												.getSelectedRecordId(),emmsDataForm));
							}
						}
				   }
					
					}catch(Exception e){e.getMessage();}
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try{
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setPmDetailFormList(pmController
							.fetchPMDetails(emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
					}catch(Exception e){e.getMessage();}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try{
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());

					emmsDataForm.setMeterFormList(this.fetchMeterDetails(
							emmsDataForm.getSelectedRecordId(), emmsDataForm));
					}catch(Exception e){e.getMessage();}
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");

			} else if (linkSelected.equals(Constants.FLB)) {

				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {

					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setFlbMeterDetailsFormList(flbController
							.fetchFLBMeterDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbPostFlightDataFormList(flbController
							.fetchFLBPostFlightDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbSortieArFormList(flbController
							.fetchFLBSortieArDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			} else if (action.equals("Validate")) {
				System.out.println("METER->validate");
				DateConvertor convertor = new DateConvertor();
				int warning = 0;
				int flag = 0;
				int count = 0;
				String status = Constants.NOTVALIDATED;
				
				if (null==emmsDataForm.getSignalOutDate()||emmsDataForm.getSignalOutDate().length()<=0) {
					emmsDataForm.setSignalOutDate("");
				}
				if (null==emmsDataForm.getInductionDate()||emmsDataForm.getInductionDate().length()<=0) {

					emmsDataForm.setInductionDate("");

				}
				emmsDataForm.setInductionDate(convertor.getDateTime(emmsDataForm.getInductionDate()));
				emmsDataForm.setSignalOutDate(convertor.getDateTime(emmsDataForm.getSignalOutDate()));
				
				emmsDataForm.setAssetMeterStatus(this.emmsDataForm
						.getAssetMeterStatus());
				Map<String, String> fetchPmValues = new HashMap<String, String>();
				List<MeterDetailsForm> meterDetailsFormList = emmsDataForm
						.getMeterFormList();
				try{
				if (null != meterDetailsFormList
						&& meterDetailsFormList.size() > 0) {

					int i = 0;
					System.out.println("Iterating through List");
					for (MeterDetailsForm meterForm : meterDetailsFormList) {

						meterForm.setErrorDescription("");
						meterForm.setRecordRowId(meterFormList.get(i)
								.getRecordRowId());
						meterForm.setExAstCurrentCount(meterFormList.get(i)
								.getExAstCurrentCount());
						meterForm.setExistingInstalledPn(meterFormList.get(i)
								.getExistingInstalledPn());
						meterForm.setExistingInstalledSN(meterFormList.get(i)
								.getExistingInstalledSN());

						System.out.println("meterRow:" + meterForm.toString());
						
						fetchPmValues = halService.fetchPmValues(
								meterForm.getInstalledPN(),
								meterForm.getInstalledSN(),
								meterForm.getInstalledPartDescription());
						System.out.println("fetchPmValues="
								+ fetchPmValues.toString());

						/*
						 * When validated without entering data in any row
						 */
						if (meterForm.getCurrentCountHms().length() <= 0
								&& meterForm.getExistingCountHms().length() <= 0) {
							meterForm.setError(Constants.WARNING);
							emmsDataForm
									.setAssetMeterStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
							warning = 1;

							if (emmsDataForm.getSignalOutDate().length() > 0
									&& emmsDataForm.getInductionDate().length() > 0) {
								System.out
										.println("METER-> UPDATE HEADER ONLY");
								halService.updateHeader(emmsDataForm);
							} else {
								meterForm
										.setErrorDescription(Constants.NOTNULL1);
							}

						} else {
							/*
							 * if there is any data in any row
							 */
							
							i++;
							System.out.println("METER->VALIDATION STARTS");
							String validate = meterValidator
									.meterDetailsValidate(meterForm
											.getExistingCountHms(), meterForm
											.getCurrentCountHms(), meterForm
											.getExAstCurrentCount(), meterForm
											.getExistingInstalledPn(),
											meterForm.getExistingInstalledSN(),
											meterForm.getInstalledPN(),
											meterForm.getInstalledSN(),
											fetchPmValues
													.get("lastCompiledValue"),
											fetchPmValues
													.get("lastCompiledDate"),
											meterForm.getUom()

									);

							/*
							 * Checking after validations
							 */
							System.out.println("METER->Validate=" + validate);
							if (validate == "") {

								System.out
										.println("METER->INSIDE VALIDATED:DATA IS CORRECT");
								status = Constants.VALIDATED;
								meterForm.setError(status);
							} else {
								System.out
										.println("METER->INSIDE NOT VALIDATED:SOME DATA IS INCORRECT");
								flag = 1;
								status = Constants.NOTVALIDATED;
								meterForm.setErrorDescription(validate);
								meterForm.setError(status);
							}
							// SETTING CURRENT SCREEN STATUS
							if (flag == 1) {
								System.out.println("METER->Flag=1;Errors");
								emmsDataForm
										.setAssetMeterStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
								flag = 0;
								count = count + 1;

							} else {

								if (count <= 0 && warning <= 0) {
									System.out.println("METER->NOERROR");
									emmsDataForm
											.setAssetMeterStatus(Constants.VALIDATIONCOMPLETED);
								}

							}

							System.out.println("UPDATE STARTS");
							if (emmsDataForm.getSignalOutDate().length() > 0
									&& emmsDataForm.getInductionDate().length() > 0) {
								
								if (!validate.contains(Constants.DECIMAL)
										&& !validate
												.contains(Constants.UOMERROR)) {
									
									if (meterForm.getUom().equalsIgnoreCase(
											"hh:mm:ss")||meterForm.getUom().equalsIgnoreCase(
													"hh:mm")){

										if (!meterForm.getCurrentCountHms()
												.isEmpty()) {
											meterForm
													.setCurrentCount(meterValidator
															.getDecimalValue(meterForm
																	.getCurrentCountHms(),meterForm.getUom()));
										}
											if (!meterForm.getExistingCountHms()
												.isEmpty())
											{	meterForm
													.setExistingCount(meterValidator
															.getDecimalValue(meterForm
																	.getExistingCountHms(),meterForm.getUom()));
											}
											}else
											{
												if (!meterForm.getCurrentCountHms()
														.isEmpty()) {
													meterForm
															.setCurrentCount(meterForm
																			.getCurrentCountHms());
												}
													if (!meterForm.getExistingCountHms()
														.isEmpty())
													{	meterForm
															.setExistingCount(meterForm
																			.getExistingCountHms());
													}
											}
									
									if(!validate.contains(Constants.COUNTLENGTHERROR))
									{
										halService.update(emmsDataForm, meterForm);
									}else
									{
										meterForm.setErrorDescription(Constants.COUNTLENGTHERROR);
									}

								} else {

									if (validate.contains(Constants.DECIMAL)) {
										meterForm
												.setErrorDescription(Constants.DECIMAL);
									} else
										meterForm
												.setErrorDescription(Constants.UOMERROR);

								}
							} else {
								meterForm
										.setErrorDescription(Constants.NOTNULL1);
							}
							
						}
							System.out.println("**Meter**="+meterForm.toString());
					}
				
				}
				}catch(Exception e){e.getMessage();}
				emmsDataForm.setInductionDate(convertor.getDateTime1(emmsDataForm.getInductionDate()));
				emmsDataForm.setSignalOutDate(convertor.getDateTime1(emmsDataForm.getSignalOutDate()));

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");
			}else if (action.equals("Export")) {

				System.out.println("INSIDE EXPORT");
				String filename="MeterDetails.xlsx";
				//String filepath = "C:\\Users\\Public\\Desktop//";
				//String filename = "D:\\MeterDetails.xlsx";
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("MeterExportedData");
				XSSFRow head = sheet.createRow((short) 0);
				head.createCell(0).setCellValue(Constants.InstalledPN);
				head.createCell(1).setCellValue(Constants.INSTALLEDPARTDESCRIPTION);
				head.createCell(2).setCellValue(Constants.InstalledSN);
				head.createCell(3).setCellValue(Constants.METERNAME);
				head.createCell(4).setCellValue(Constants.UOM);
				head.createCell(5).setCellValue(Constants.EXISTINGCOUNT);
				head.createCell(6).setCellValue(Constants.CURRENTCOUNT);
				head.createCell(7).setCellValue(Constants.ErrorStatus);
				head.createCell(8).setCellValue(Constants.ErrorDescription);
				XSSFRow row = null;
				int rowIndex = 1;
				List<MeterDetailsForm> meterForms = emmsDataForm
						.getMeterFormList();
				if (null != meterForms && meterForms.size() > 0) {

					for (MeterDetailsForm meterExportForm : meterForms) {
						System.out.println("count=" + rowIndex);
						row = sheet.createRow((short) rowIndex);

						row.createCell(0).setCellValue(
								meterExportForm.getInstalledPN());
						row.createCell(1).setCellValue(
								meterExportForm.getInstalledPartDescription());
						row.createCell(2).setCellValue(
								meterExportForm.getInstalledSN());
						row.createCell(3)
								.setCellValue(meterExportForm.getMeterName());
						row.createCell(4).setCellValue(
								meterExportForm.getUom());
						row.createCell(5).setCellValue(
								meterExportForm.getExistingCount());
						row.createCell(6).setCellValue(
								meterExportForm.getCurrentCount());
						row.createCell(7).setCellValue(
								meterExportForm.getError());
						row.createCell(8).setCellValue(
								meterExportForm.getErrorDescription());
						rowIndex++;
					}

				}
				
				try {
					
					
					response.setHeader("Content-disposition", "attachment; filename=\""+filename+"\"");
					workbook.write(response.getOutputStream());
					

				} catch (Exception e) {

					e.printStackTrace();
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");

			} else if (action.equals("Import")) {

				System.out.println(emmsDataForm.getMeterExcelfile());

				List<MeterDetailsForm> oldMeterList = emmsDataForm
						.getMeterFormList();
				List<MeterDetailsForm> excelMeterList = new ArrayList<MeterDetailsForm>();

				XSSFWorkbook workbook = null;
				try {
					workbook = new XSSFWorkbook(emmsDataForm
							.getMeterExcelfile().getInputStream());
				} catch (IOException e) {

				}

				XSSFSheet worksheet = workbook.getSheetAt(0);
				boolean headerStatus = meterValidator.validateHeader(worksheet
						.getRow(0));
				boolean countRows = oldMeterList.size() == worksheet
						.getLastRowNum();
				System.out.println(headerStatus + ":" + countRows);

				if (headerStatus && countRows) {

					for (int j = 1, i = 0; j <= worksheet.getLastRowNum(); j++, i++) {
						MeterDetailsForm excelMeterForm = new MeterDetailsForm();
						XSSFRow row = worksheet.getRow(j);

						commonValidator.checkCellType2003(row.getCell(0));

						excelMeterForm.setInstalledPN(((oldMeterList.get(i)
								.getInstalledPN())));
						excelMeterForm.setInstalledPartDescription((oldMeterList.get(i)
								.getInstalledPartDescription()));
						excelMeterForm.setInstalledSN(oldMeterList.get(i).getInstalledSN());
						excelMeterForm.setMeterName(oldMeterList.get(i).getMeterName());
						excelMeterForm.setUom(oldMeterList.get(i)
								.getUom());
						excelMeterForm.setRecordRowId(meterFormList.get(i)
								.getRecordRowId());
						excelMeterForm.setExAstCurrentCount(meterFormList.get(i)
								.getExAstCurrentCount());
						excelMeterForm.setExistingInstalledPn(meterFormList.get(i)
								.getExistingInstalledPn());
						excelMeterForm.setExistingInstalledSN(meterFormList.get(i)
								.getExistingInstalledSN());
						
						
						if (row.getCell(5) != null) {
							System.out.println("inside inital coutn");
						String existingCount=meterValidator
								.checkCellType2003(row.getCell(5),excelMeterForm.getUom());
						System.out.println("ic="+existingCount);
						excelMeterForm.setExistingCount(existingCount);
						}
						
						if (row.getCell(6) != null) {
						excelMeterForm
								.setCurrentCount(meterValidator
										.checkCellType2003(row.getCell(6),excelMeterForm.getUom()));
						}
						excelMeterForm.setError(oldMeterList.get(i)
								.getError());
						excelMeterForm.setErrorDescription(oldMeterList.get(i)
								.getErrorDescription());
						
						excelMeterList.add(excelMeterForm);
						System.out.println(excelMeterForm.toString());

					}
					emmsDataForm.setMeterFormList(excelMeterList);

				}else

				{
					emmsDataForm.setBulkImportStatus("Error");
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");

			} 

		}
		return "basic";
	}

}
