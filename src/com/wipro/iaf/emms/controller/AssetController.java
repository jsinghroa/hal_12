package com.wipro.iaf.emms.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.validator.AssetConfigValidator;
import com.wipro.iaf.emms.validator.CommonValidator;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.ExcelValidator;

@Controller
@RequestMapping("/ASSET")
public class AssetController {

	@Autowired
	private FLBController flbController;

	@Autowired
	List<AssetConfigForm> assetFormList;

	@Autowired
	PMController pmController;

	@Autowired
	InstallableAssetController installAssetController;
	@Autowired
	HalService halService;

	@Autowired
	MeterController meterController;

	@Autowired
	private AssetConfigValidator assetValidator;

	@Autowired
	private EmmsDataForm emmsDataForm;

	CommonValidator commonValidator = new CommonValidator();
	Map<String, String> parentChild;

	@RequestMapping(value = { "/saveAsset" }, method = RequestMethod.GET)
	public List<AssetConfigForm> fetchAssetDetails(String recordID, EmmsDataForm emmsDataForm) {

		try {
			this.emmsDataForm = emmsDataForm;
			emmsDataForm.setRecordId(recordID);
			emmsDataForm.setSelectedRecordId(recordID);
			this.emmsDataForm = emmsDataForm;
			assetFormList = halService.fetchConfigDetails(recordID);
			parentChild = halService.setParentChild(emmsDataForm.getRecordId());
		} catch (Exception e) {
			e.getMessage();
		}
		return assetFormList;
	}

	@RequestMapping(value = "/saveAsset", method = RequestMethod.POST)
	public String submit(@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm, BindingResult bindingResult,
			ModelMap model, @RequestParam String action, @RequestParam String linkSelected,
			HttpServletResponse response) {
		System.out.println("action" + " " + action);
		System.out.println("linkSelected" + linkSelected);

		if (linkSelected.equals(Constants.LISTVIEW)) {
			System.out.println(Constants.LISTVIEW);
			List<EmmsDataForm> emmsDataFormList = halService.getEmmsDataOnView();
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		} else {

			if (linkSelected.equals(Constants.ASSETCONFIG)) {
				System.out.println(Constants.ASSETCONFIG);
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try {
						emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
						emmsDataForm.setAssetFormList(
								this.fetchAssetDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
					} catch (Exception e) {
						e.getMessage();
					}
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {

				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				System.out.println(emmsDataForm.getSelectedRecordId() + ":" + emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try {
						emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
						emmsDataForm.setAssetFormList(
								this.fetchAssetDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
						for (int i = 0; i < emmsDataForm.getAssetFormList().size(); i++) {
							if (emmsDataForm.getAssetFormList().get(i).getErrorStatus() != null) {
								if (emmsDataForm.getAssetFormList().get(i).getErrorStatus()
										.equals(Constants.VALIDATEDWITHWARNING)
										&& emmsDataForm.getAssetFormList().get(i).getIndicator().equals("I")) {
									emmsDataForm.setInstallableFormList(installAssetController
											.fetchInstallableDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
								}
							}
						}
					} catch (Exception e) {
						e.getMessage();
					}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				System.out.println(Constants.PM);
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {

					try {
						emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
						emmsDataForm.setPmDetailFormList(
								pmController.fetchPMDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
						System.out.println("emmsDataForm after" + emmsDataForm.toString());
					} catch (Exception e) {
						e.getMessage();
					}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {
				System.out.println(Constants.METER);
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					try {
						emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());

						emmsDataForm.setMeterFormList(
								meterController.fetchMeterDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
						System.out.println("emmsDataForm after" + emmsDataForm.toString());
					} catch (Exception e) {
						e.getMessage();
					}
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");

			} else if (linkSelected.equals(Constants.FLB)) {

				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {

					emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
					emmsDataForm.setFlbMeterDetailsFormList(
							flbController.fetchFLBMeterDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
					emmsDataForm.setFlbPostFlightDataFormList(
							flbController.fetchFLBPostFlightDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
					emmsDataForm.setFlbSortieArFormList(
							flbController.fetchFLBSortieArDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			} else if (action.equals("Validate")) {
				System.out.println("Inside Validator");
				System.out.println("emmsDataForm=" + emmsDataForm.toString());
				int warning = 0;
				int flag = 0;
				int count = 0;
				DateConvertor convertor = new DateConvertor();

				String status = Constants.NOTVALIDATED;
				if (null==emmsDataForm.getSignalOutDate()||emmsDataForm.getSignalOutDate().length()<=0) {
					emmsDataForm.setSignalOutDate("");
				}
				if (null==emmsDataForm.getInductionDate()||emmsDataForm.getInductionDate().length()<=0) {

					emmsDataForm.setInductionDate("");

				}

				emmsDataForm.setInstallableStatus(this.emmsDataForm.getInstallableStatus());
				System.out.println("ScreenStatus(IA)=" + this.emmsDataForm.getInstallableStatus()
						+ emmsDataForm.getAssetConfigStatus());
				List<String> pnsncombination = new ArrayList<String>();
				List<String> pninlieucombination = new ArrayList<String>();
				List<String> sninlieucombination = new ArrayList<String>();
				List<AssetConfigForm> configForm = emmsDataForm.getAssetFormList();
				Map<String, AssetConfigForm> lcnForm = new HashMap<String, AssetConfigForm>();
				parentChild = halService.getParentChild();
				System.out.println("PARENT CHILD:" + parentChild);
				try {
					if (null != configForm && configForm.size() > 0) {

						int i = 0;
						// emmsDataForm.setAssetConfigStatus("");
						for (AssetConfigForm assetConfigForm : configForm) {
							System.out.println("Row1=" + assetConfigForm.toString());
							System.out.println("Status:" + emmsDataForm.getAssetConfigStatus());
							assetConfigForm.setErrorDesc("");
							assetConfigForm.setInstalledPNList(assetFormList.get(i).getInstalledPNList());
							assetConfigForm.setConditionCodes(assetFormList.get(i).getConditionCodes());
							assetConfigForm.setRecordRowId(assetFormList.get(i).getRecordRowId());

							String lcn = assetConfigForm.getLcn();
							String parent = parentChild.get(lcn);
							lcnForm.put(lcn, assetConfigForm);

							/*
							 * When validated without entering data in any row
							 */

							if (assetConfigForm.getInstalledPN().length() <= 0
									&& assetConfigForm.getConditionCode().length() <= 0
									&& assetConfigForm.getDateOfManfacturing().length() <= 0
									&& assetConfigForm.getDateOfReciept().length() <= 0
									&& assetConfigForm.getInstalledSN().length() <= 0
									&& assetConfigForm.getInLieuPn().length() <= 0) {
								assetConfigForm.setErrorStatus(Constants.WARNING);
								emmsDataForm.setAssetConfigStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
								if (emmsDataForm.getAssetConfigStatus().equals(Constants.VALIDATIONCOMPLETED)
										&& this.emmsDataForm.getInstallableStatus()
												.equals(Constants.VALIDATIONCOMPLETED)) {
									emmsDataForm.setRecordStatus(Constants.VALIDATIONCOMPLETED);
								} else {
									emmsDataForm.setRecordStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
								}

								warning = 1;
								if (emmsDataForm.getSignalOutDate().length() > 0
										&& emmsDataForm.getInductionDate().length() > 0) {
									System.out.println("UPDATE HEADAER ONLY");
									halService.updateHeader(emmsDataForm);
								} else {
									assetConfigForm.setErrorDesc(Constants.NOTNULL1);
								}

							} else {
								/*
								 * if there is any data in any row
								 */
								System.out.println("***DATA PRESENT IN A ROW***");
								System.out.println("Current LCN=" + lcn);
								System.out.println("LCNFORM=" + lcnForm);
								System.out.println("Parent=" + parent);
								i++;
								String receiptDate = "";
								String manufacturingDate = "";

								if (assetConfigForm.getDateOfManfacturing().length() > 0) {
									manufacturingDate = assetConfigForm.getDateOfManfacturing();
								}
								if (assetConfigForm.getDateOfReciept().length() > 0) {
									receiptDate = assetConfigForm.getDateOfReciept();
								}

								assetConfigForm.setDateOfManfacturing(manufacturingDate);
								assetConfigForm.setDateOfReciept(receiptDate);

								String validate = assetValidator.assetConfigValidate(emmsDataForm.getInductionDate(),
										emmsDataForm.getSignalOutDate(), assetConfigForm.getInstalledPN(),
										assetConfigForm.getInLieuPn(), assetConfigForm.getInstalledSN(),
										assetConfigForm.getConditionCode(), manufacturingDate, receiptDate,
										pnsncombination, pninlieucombination, sninlieucombination, lcnForm, parent);

								String pnsn = assetConfigForm.getInstalledPN() + ":" + assetConfigForm.getInstalledSN();
								String pninlieu = assetConfigForm.getInstalledPN() + ":"
										+ assetConfigForm.getInLieuPn();
								String sninlieu = assetConfigForm.getInstalledSN() + ":"
										+ assetConfigForm.getInLieuPn();
								pnsncombination.add(pnsn);
								pninlieucombination.add(pninlieu);
								sninlieucombination.add(sninlieu);
								/*
								 * Checking after validations
								 */
								if (validate == "") {
									// ALL COLUMNS HAVE CORRECT DATA
									if (null != assetConfigForm.getInLieuPn()
											&& assetConfigForm.getInLieuPn().length() > 0
											&& !(assetConfigForm.getInstalledPNList()
													.containsValue(assetConfigForm.getInLieuPn()))) {
										System.out.println("INSIDE WARNING");
										status = Constants.VALIDATEDWITHWARNING;
										assetConfigForm.setErrorStatus(status);
										assetConfigForm.setErrorDesc(Constants.WARNINGMESSAGE);
									} else {
										// NO WARNING ALL CORRECT
										System.out.println("INSIDE VALIDATED");
										status = Constants.VALIDATED;
										assetConfigForm.setErrorStatus(status);
									}
									System.out
											.println(getClass() + ":Error Status:" + assetConfigForm.getErrorStatus());

								} else {
									// ANY COLUMNS DOES NOT HAVE CORRECT DATA
									System.out.println("INSIDE NOT VALIDATED");
									flag = 1;
									status = Constants.NOTVALIDATED;
									assetConfigForm.setErrorDesc(validate);
									assetConfigForm.setErrorStatus(status);
								}
								// SETTING CURRENT SCREEN STATUS
								if (flag == 1) {
									System.out.println("Errors");
									emmsDataForm.setAssetConfigStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
									flag = 0;
									count = count + 1;

								} else {

									if (count <= 0 && warning <= 0) {
										System.out.println("NOERROR");
										emmsDataForm.setAssetConfigStatus(Constants.VALIDATIONCOMPLETED);
									}

								}
								System.out.println("AfterValidateScreenStatus=" + emmsDataForm.getAssetConfigStatus()
										+ ":" + this.emmsDataForm.getInstallableStatus() + ":"
										+ emmsDataForm.getInstallableStatus());

								// SETTING MULTIPLE SCREEN COMBINED STATUS
								if (Constants.VALIDATIONCOMPLETED.equals(emmsDataForm.getAssetConfigStatus())
										&& Constants.VALIDATIONCOMPLETED
												.equals(this.emmsDataForm.getInstallableStatus())) {

									emmsDataForm.setRecordStatus(Constants.VALIDATIONCOMPLETED);
								} else {
									emmsDataForm.setRecordStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
								}

								assetConfigForm.setDateOfManfacturing(
										convertor.getDateTime(assetConfigForm.getDateOfManfacturing()));
								assetConfigForm
										.setDateOfReciept(convertor.getDateTime(assetConfigForm.getDateOfReciept()));
								emmsDataForm.setInductionDate(convertor.getDateTime(emmsDataForm.getInductionDate()));
								emmsDataForm.setSignalOutDate(convertor.getDateTime(emmsDataForm.getSignalOutDate()));
								if (!validate.contains(Constants.INLIEULENGTHERROR)
										&& !validate.contains(Constants.SNLENGTHERROR)) {
									System.out.println("INSIDE CONTAINS");
									halService.update(emmsDataForm, assetConfigForm);
								} else {
									System.out.println("LENGTH GREATER");
									assetConfigForm
											.setErrorDesc(Constants.INLIEULENGTHERROR + "||" + Constants.SNLENGTHERROR);
								}

							}
							assetConfigForm.setDateOfManfacturing(
									convertor.getDateTime1(assetConfigForm.getDateOfManfacturing()));
							assetConfigForm
									.setDateOfReciept(convertor.getDateTime1(assetConfigForm.getDateOfReciept()));
							emmsDataForm.setInductionDate(convertor.getDateTime1(emmsDataForm.getInductionDate()));
							emmsDataForm.setSignalOutDate(convertor.getDateTime1(emmsDataForm.getSignalOutDate()));

						}
					}
				} catch (Exception e) {
					e.getMessage();
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");
			} else if (action.equals("Save")) {

				System.out.println("inside save");
				DateConvertor convertor = new DateConvertor();
				if (null==emmsDataForm.getSignalOutDate()||emmsDataForm.getSignalOutDate().length()<=0) {
					emmsDataForm.setSignalOutDate("");
				}
				if (null==emmsDataForm.getInductionDate()||emmsDataForm.getInductionDate().length()<=0) {

					emmsDataForm.setInductionDate("");

				}
				emmsDataForm.setSignalOutDate(convertor.getDateTime(emmsDataForm.getSignalOutDate()));
				emmsDataForm.setInductionDate(convertor.getDateTime(emmsDataForm.getInductionDate()));
							
				List<AssetConfigForm> configForm = emmsDataForm.getAssetFormList();

				try {
					if (null != configForm && configForm.size() > 0) {
						System.out.println(configForm);
						int i = 0;
						emmsDataForm.setAssetConfigStatus("");
						emmsDataForm.setRecordStatus("");
						for (AssetConfigForm assetConfigForm : configForm) {
							assetConfigForm.setInstalledPNList(assetFormList.get(i).getInstalledPNList());
							assetConfigForm.setConditionCodes(assetFormList.get(i).getConditionCodes());
							assetConfigForm.setRecordRowId(assetFormList.get(i).getRecordRowId());
							i++;
							String receiptDate = "";
							String manufacturingDate = "";

							if (assetConfigForm.getDateOfManfacturing().length() > 0) {
								manufacturingDate = assetConfigForm.getDateOfManfacturing();
							}
							if (assetConfigForm.getDateOfReciept().length() > 0) {
								receiptDate = assetConfigForm.getDateOfReciept();
							}

							assetConfigForm.setDateOfManfacturing(manufacturingDate);
							assetConfigForm.setDateOfReciept(receiptDate);

							
							assetConfigForm.setDateOfManfacturing(
									convertor.getDateTime(assetConfigForm.getDateOfManfacturing()));
							assetConfigForm
									.setDateOfReciept(convertor.getDateTime(assetConfigForm.getDateOfReciept()));
							
							System.out.println("Update Query=" + halService.update(emmsDataForm, assetConfigForm));

							assetConfigForm.setDateOfManfacturing(
									convertor.getDateTime1(assetConfigForm.getDateOfManfacturing()));
							assetConfigForm
									.setDateOfReciept(convertor.getDateTime1(assetConfigForm.getDateOfReciept()));
							
						}

					}

				} catch (Exception e) {
					e.getMessage();
				}
				emmsDataForm.setInductionDate(convertor.getDateTime1(emmsDataForm.getInductionDate()));
				emmsDataForm.setSignalOutDate(convertor.getDateTime1(emmsDataForm.getSignalOutDate()));

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");
			} else if (action.equals("Yes")) {
				System.out.println("inside Yes");
				emmsDataForm.setFreeze(true);
				System.out.println(emmsDataForm.getRecordId() + ":" + emmsDataForm.isFreeze());

				halService.updateFreeze(emmsDataForm.isFreeze(), emmsDataForm.getRecordId());

				List<AssetConfigForm> configForm = emmsDataForm.getAssetFormList();
				try {
					if (null != configForm && configForm.size() > 0) {

						for (AssetConfigForm assetConfigForm : configForm) {

							System.out.println(assetConfigForm.getInstalledPN());
							if (!assetConfigForm.getInstalledPN().isEmpty()) {
								halService.updateMeter(assetConfigForm, 0);
							} else {
								halService.updateMeter(assetConfigForm, 1);
							}

						}
					}
				} catch (Exception e) {
					e.getMessage();
				}
				System.out.println("FreezeRecordID" + this.emmsDataForm.getSelectedRecordId());
				emmsDataForm.setInstallableFormList(installAssetController
						.fetchInstallableDetails(this.emmsDataForm.getSelectedRecordId(), this.emmsDataForm));
				List<InstallableAssetForm> installableFormList = emmsDataForm.getInstallableFormList();
				try {
					if (null != installableFormList && installableFormList.size() > 0) {

						for (InstallableAssetForm installableForm : installableFormList) {

							System.out.println(installableForm.getInstallablePN());
							if (!installableForm.getInstallablePN().isEmpty()) {
								halService.updateMeter(installableForm, 0);
							} else {
								halService.updateMeter(installableForm, 1);
							}

						}
					}
				} catch (Exception e) {
					e.getMessage();
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/homePage.jsp");
			} else if (action.equals("Export")) {

				System.out.println("INSIDE EXPORT");

				String filename = "AssetConfiguration.xlsx";
				// String filename = "D:\\AssetConfiguration.xlsx";
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("AssetExportedData");
				XSSFRow head = sheet.createRow((short) 0);
				head.createCell(0).setCellValue(Constants.INDICATOR);
				head.createCell(1).setCellValue(Constants.LCN);
				head.createCell(2).setCellValue(Constants.Position);
				head.createCell(3).setCellValue(Constants.BuildItem);
				head.createCell(4).setCellValue(Constants.AssetNum);
				head.createCell(5).setCellValue(Constants.PN);
				head.createCell(6).setCellValue(Constants.PartDescription);
				head.createCell(7).setCellValue(Constants.SN);
				head.createCell(8).setCellValue(Constants.InstalledPN);
				head.createCell(9).setCellValue(Constants.InLieuPN);
				head.createCell(10).setCellValue(Constants.InstalledSN);
				head.createCell(11).setCellValue(Constants.ConditionCode);
				head.createCell(12).setCellValue(Constants.DateofManufacturing);
				head.createCell(13).setCellValue(Constants.DateofReceipt);
				head.createCell(14).setCellValue(Constants.ErrorStatus);
				head.createCell(15).setCellValue(Constants.ErrorDescription);

				XSSFRow row = null;

				int rowIndex = 1;

				List<AssetConfigForm> configForm = emmsDataForm.getAssetFormList();
				if (null != configForm && configForm.size() > 0) {
					int i = 0;
					for (AssetConfigForm assetConfigForm : configForm) {
						System.out.println("count=" + rowIndex);
						assetConfigForm.setInstalledPNList(assetFormList.get(i).getInstalledPNList());
						assetConfigForm.setConditionCodes(assetFormList.get(i).getConditionCodes());
						assetConfigForm.setRecordRowId(assetFormList.get(i).getRecordRowId());
						i++;
						row = sheet.createRow((short) rowIndex);

						row.createCell(0).setCellValue(assetConfigForm.getIndicator());
						row.createCell(1).setCellValue(assetConfigForm.getLcn());
						row.createCell(2).setCellValue(assetConfigForm.getPosition());
						row.createCell(3).setCellValue(assetConfigForm.getBuildItem());
						row.createCell(4).setCellValue(assetConfigForm.getAssetNum());
						row.createCell(5).setCellValue(assetConfigForm.getPartNum());
						row.createCell(6).setCellValue(assetConfigForm.getPartDescription());
						row.createCell(7).setCellValue(assetConfigForm.getSerialNum());
						row.createCell(8).setCellValue(assetConfigForm.getInstalledPN());
						row.createCell(9).setCellValue(assetConfigForm.getInLieuPn());
						row.createCell(10).setCellValue(assetConfigForm.getInstalledSN());
						row.createCell(11).setCellValue(assetConfigForm.getConditionCode());
						row.createCell(12).setCellValue(assetConfigForm.getDateOfManfacturing());
						row.createCell(13).setCellValue(assetConfigForm.getDateOfReciept());
						row.createCell(14).setCellValue(assetConfigForm.getErrorStatus());
						row.createCell(15).setCellValue(assetConfigForm.getErrorDesc());

						rowIndex++;
					}

				}

				try {
					response.setHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
					workbook.write(response.getOutputStream());

				} catch (Exception e) {

					e.printStackTrace();
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (action.equals("Import")) {

				String s = emmsDataForm.getAssetExcelfile().getOriginalFilename();

				List<AssetConfigForm> oldConfigList = emmsDataForm.getAssetFormList();
				List<AssetConfigForm> configList = new ArrayList<AssetConfigForm>();

				XSSFWorkbook workbook = null;
				try {
					workbook = new XSSFWorkbook(emmsDataForm.getAssetExcelfile().getInputStream());
				} catch (IOException e) {

				}

				XSSFSheet worksheet = workbook.getSheetAt(0);
				boolean headerStatus = assetValidator.validateHeader(worksheet.getRow(0));
				boolean countRows = oldConfigList.size() == worksheet.getLastRowNum();
				System.out.println(headerStatus + ":" + countRows);

				if (headerStatus && countRows) {

					for (int j = 1, i = 0; j <= worksheet.getLastRowNum(); j++, i++) {
						AssetConfigForm configForm = new AssetConfigForm();
						XSSFRow row = worksheet.getRow(j);

						assetValidator.checkCellType2003(row.getCell(0));

						configForm.setIndicator(oldConfigList.get(i).getIndicator());
						configForm.setLcn(oldConfigList.get(i).getLcn());
						configForm.setPosition(oldConfigList.get(i).getPosition());
						configForm.setBuildItem(oldConfigList.get(i).getBuildItem());
						configForm.setAssetNum(oldConfigList.get(i).getAssetNum());
						configForm.setPartNum(oldConfigList.get(i).getPartNum());
						configForm.setPartDescription(oldConfigList.get(i).getPartDescription());
						configForm.setSerialNum(oldConfigList.get(i).getSerialNum());
						configForm.setInstalledPNList(assetFormList.get(i).getInstalledPNList());
						configForm.setConditionCodes(assetFormList.get(i).getConditionCodes());
						configForm.setRecordRowId(assetFormList.get(i).getRecordRowId());
						if (row.getCell(8) != null) {
							String installedPN = assetValidator.checkCellType2003(row.getCell(8));
							System.out.println(installedPN);
							configForm.setInstalledPN(installedPN);

						}
						if (row.getCell(9) != null) {
							String inLieuPn = assetValidator.checkCellType2003(row.getCell(9));
							configForm.setInLieuPn(inLieuPn);
						}
						if (row.getCell(10) != null) {
							String installedSN = assetValidator.checkCellType2003(row.getCell(10));
							configForm.setInstalledSN(installedSN);
						}
						if (row.getCell(11) != null) {
							String conditionCode = assetValidator.checkCellType2003(row.getCell(11));
							configForm.setConditionCode(conditionCode);
						}
						if (row.getCell(12) != null) {
							String dateOfManfacturing = assetValidator.checkCellType2003(row.getCell(12));
							System.out.println(dateOfManfacturing);
							if (commonValidator.timeStampValidate1(dateOfManfacturing).equals(Constants.NOERROR)) {
								System.out.println("insdie manu");
								configForm.setDateOfManfacturing(dateOfManfacturing);
							}
						}
						if (row.getCell(13) != null) {
							String dateOfReciept = assetValidator.checkCellType2003(row.getCell(13));

							if (commonValidator.timeStampValidate1(dateOfReciept).equals(Constants.NOERROR)) {
								System.out.println("inside");
								configForm.setDateOfReciept(dateOfReciept);
							}
						}

						configList.add(configForm);
						System.out.println(configForm.toString());

					}
					emmsDataForm.setAssetFormList(configList);

				} else

				{
					emmsDataForm.setBulkImportStatus("Error");
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			}
		}
		return "basic";
	}
}
