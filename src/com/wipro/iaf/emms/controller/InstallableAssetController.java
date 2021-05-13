package com.wipro.iaf.emms.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wipro.iaf.emms.dao.HalDao;
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.InstallableAssetForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.validator.AssetConfigValidator;
import com.wipro.iaf.emms.validator.CommonValidator;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.ExcelValidator;
import com.wipro.iaf.emms.validator.InstallableAssetValidator;

@Controller
@RequestMapping("/Installable")
public class InstallableAssetController {

	@Autowired
	private FLBController flbController;

	@Autowired
	List<InstallableAssetForm> installableFormList;

	@Autowired
	MeterController meterController;

	@Autowired
	AssetController assetController;

	@Autowired
	PMController pmController;

	@Autowired
	private ExcelValidator excelValidator;
	@Autowired
	private InstallableAssetValidator installableValidator;

	@Autowired
	private AssetConfigValidator assetValidator;
	@Autowired
	HalService halService;

	@Autowired
	private AssetConfigForm assetConfigForm;

	@Autowired
	private EmmsDataForm emmsDataForm;

	private Map<String, String> parentChild;
	private List<String> lcnList;
	private Map<String, String> insLcnList;
	CommonValidator commonValidator = new CommonValidator();
	
	@RequestMapping(value = { "/saveInstall" }, method = RequestMethod.GET)
	public List<InstallableAssetForm> fetchInstallableDetails(String recordID,
			EmmsDataForm emmsDataForm) {
		this.emmsDataForm = emmsDataForm;
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		System.out.println(this.emmsDataForm.getAssetConfigStatus() + ":"
				+ this.emmsDataForm.getInstallableStatus());
		parentChild = halService.setParentChild(emmsDataForm.getRecordId());
		lcnList = halService.setLcnList();

		installableFormList = halService.fetchInstallableDetails(recordID,
				lcnList, parentChild);

		return installableFormList;
	}

	@RequestMapping(value = "/saveInstall", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm,
			BindingResult bindingResult, ModelMap model,
			@RequestParam String action, @RequestParam String linkSelected,
			@RequestParam String disableIndicator) {
		
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
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {

				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
					for (int i = 0; i < emmsDataForm.getAssetFormList().size(); i++) {
						System.out
								.println("listviewEmmsdataController------ size"
										+ emmsDataForm.getAssetFormList()
												.get(i));
						if (emmsDataForm.getAssetFormList().get(i)
								.getErrorStatus() != null) {
							System.out.println("error status value"
									+ emmsDataForm.getAssetFormList().get(i)
											.getErrorStatus().toString());
							System.out.println("indicator value"
									+ emmsDataForm.getAssetFormList().get(i)
											.getIndicator().toString());

							if (emmsDataForm.getAssetFormList().get(i)
									.getErrorStatus()
									.equals(Constants.VALIDATEDWITHWARNING)
									&& emmsDataForm.getAssetFormList().get(i)
											.getIndicator().equals("I")) {
								emmsDataForm.setInstallableFormList(this
										.fetchInstallableDetails(emmsDataForm
												.getSelectedRecordId(),
												emmsDataForm));
							}
						}
					}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setPmDetailFormList(pmController
							.fetchPMDetails(emmsDataForm.getSelectedRecordId(),
									emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());

					emmsDataForm.setMeterFormList(meterController
							.fetchMeterDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
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

			}

			else if (action.equals("Validate")) {

				DateConvertor convertor = new DateConvertor();

				int flag = 0;
				int count = 0;
				int warning = 0;
				String status = Constants.NOTVALIDATED;
				String time = new Time(System.currentTimeMillis()).toString();

				String inductionDate = "";
				String signalOutDate = "";
				if (emmsDataForm.getSignalOutDate().length() > 0
						&& emmsDataForm.getInductionDate().length() > 0) {
					inductionDate = emmsDataForm.getInductionDate() + " "
							+ time;
					signalOutDate = emmsDataForm.getSignalOutDate() + " "
							+ time;
					emmsDataForm.setInductionDate(convertor
							.getDate(inductionDate));
					emmsDataForm.setSignalOutDate(convertor
							.getDate(signalOutDate));
				} else {

					emmsDataForm.setInductionDate("");
					emmsDataForm.setSignalOutDate("");
					// assetConfigForm.setErrorDesc(Constants.NOTNULL);
				}

				emmsDataForm.setAssetConfigStatus(this.emmsDataForm
						.getAssetConfigStatus());
				List<String> pnsncombination = new ArrayList<String>();
				List<String> pninlieucombination = new ArrayList<String>();
				List<String> sninlieucombination = new ArrayList<String>();
				List<InstallableAssetForm> installableList = emmsDataForm
						.getInstallableFormList();
				Map<String, InstallableAssetForm> lcnForm = new HashMap<String, InstallableAssetForm>();
				parentChild = halService.getParentChild();
				// Map<String, String> editchild=halService.getLcnEdit();

				if (null != installableList && installableList.size() > 0) {

					int i = 0;
					// emmsDataForm.setInstallableStatus("");
					for (InstallableAssetForm installableForm : installableList) {

						System.out.println("Status:"
								+ emmsDataForm.getAssetConfigStatus());
						installableForm.setErrorDescription("");
						installableForm.setInstalledPNList(installableFormList
								.get(i).getInstalledPNList());
						installableForm.setConditionCodes(installableFormList
								.get(i).getConditionCodes());
						installableForm.setRecordRowId(installableFormList.get(
								i).getRecordRowId());
						String lcn = installableForm.getLcn();

						String parent = parentChild.get(lcn);

						lcnForm.put(lcn, installableForm);

						if (installableForm.getInstallablePN().length() <= 0
								&& installableForm.getConditionCode().length() <= 0
								&& installableForm.getDateofManufacturing()
										.length() <= 0
								&& installableForm.getDateofReceipt().length() <= 0
								&& installableForm.getInstallableSN().length() <= 0
								&& installableForm.getiNlieuPN().length() <= 0) {
							installableForm.setErrorStatus(Constants.WARNING);

							emmsDataForm
									.setInstallableStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
							if (Constants.VALIDATIONCOMPLETED
									.equals(this.emmsDataForm
											.getAssetConfigStatus())
									&& emmsDataForm
											.getInstallableStatus()
											.equals(Constants.VALIDATIONCOMPLETED)) {

								System.out.println("inside record status:");
								emmsDataForm
										.setRecordStatus(Constants.VALIDATIONCOMPLETED);
							} else {
								emmsDataForm
										.setRecordStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
							}

							warning = 1;

							if (emmsDataForm.getSignalOutDate().length() > 0
									&& emmsDataForm.getInductionDate().length() > 0) {
								
								halService.updateHeader(emmsDataForm);
							} else {
								assetConfigForm
										.setErrorDesc(Constants.NOTNULL1);
							}

						} else {

							i++;
							String receiptDate = "";
							String manufacturingDate = installableForm
									.getDateofManufacturing() + " " + time;

							if (installableForm.getDateofReceipt().length() > 0) {
								receiptDate = installableForm
										.getDateofReceipt() + " " + time;
							}

							installableForm
									.setDateofManufacturing(manufacturingDate);
							installableForm.setDateofReceipt(receiptDate);

							String validate = installableValidator
									.installableAssetValidate(inductionDate,
											signalOutDate,
											installableForm.getInstallablePN(),
											installableForm.getiNlieuPN(),
											installableForm.getInstallableSN(),
											installableForm.getConditionCode(),
											manufacturingDate, receiptDate,
											pnsncombination,
											pninlieucombination,
											sninlieucombination, lcnForm,
											parent);

							String pnsn = installableForm.getInstallablePN()
									+ ":" + installableForm.getInstallableSN();
							String pninlieu = installableForm
									.getInstallablePN()
									+ ":"
									+ installableForm.getiNlieuPN();
							String sninlieu = installableForm
									.getInstallableSN()
									+ ":"
									+ installableForm.getiNlieuPN();
							pnsncombination.add(pnsn);
							pninlieucombination.add(pninlieu);
							sninlieucombination.add(sninlieu);

							if (validate == "") {
								System.out.println("List:"
										+ installableForm.getInstalledPNList()
										+ " inlieu="
										+ installableForm.getiNlieuPN());
								if (!(installableForm.getInstalledPNList()
										.containsValue(installableForm
												.getiNlieuPN()))) {
									System.out.println("INSIDE WARNING");
									status = Constants.VALIDATEDWITHWARNING;
									installableForm.setErrorStatus(status);
									installableForm
											.setErrorDescription(Constants.WARNINGMESSAGE);
								}

								else {
									System.out.println("INSIDE VALIDATED");
									status = Constants.VALIDATED;
									installableForm.setErrorStatus(status);
								}
								System.out.println(getClass()
										+ ":Error Status:"
										+ installableForm.getErrorStatus());

							} else {
								System.out.println("INSIDE NOT VALIDATED");
								flag = 1;
								status = Constants.NOTVALIDATED;
								installableForm.setErrorDescription(validate);
								installableForm.setErrorStatus(status);
							}

							if (flag == 1) {
								System.out.println("Flag=1;Errors");
								emmsDataForm
										.setInstallableStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
								flag = 0;
								count = count + 1;

							} else {

								if (count <= 0 && warning <= 0) {
									System.out.println("NOERROR");
									emmsDataForm
											.setInstallableStatus(Constants.VALIDATIONCOMPLETED);
								}

							}

							System.out.println(this.emmsDataForm
									.getAssetConfigStatus()
									+ ":"
									+ emmsDataForm.getInstallableStatus()
									+ ":"
									+ emmsDataForm.getAssetConfigStatus());
							if (Constants.VALIDATIONCOMPLETED
									.equals(this.emmsDataForm
											.getAssetConfigStatus())
									&& emmsDataForm
											.getInstallableStatus()
											.equals(Constants.VALIDATIONCOMPLETED)) {

								System.out.println("inside record status:");
								emmsDataForm
										.setRecordStatus(Constants.VALIDATIONCOMPLETED);
							} else {
								emmsDataForm
										.setRecordStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
							}
							installableForm.setDateofManufacturing(convertor
									.getDateTime(installableForm
											.getDateofManufacturing()));
							installableForm.setDateofReceipt(convertor
									.getDateTime(installableForm
											.getDateofReceipt()));

							if (!validate.contains(Constants.INLIEULENGTHERROR)
									&& !validate
											.contains(Constants.SNLENGTHERROR)) {
								halService
										.update(emmsDataForm, installableForm);

							} else {
								System.out.println("LENGTH GREATER");
								assetConfigForm
										.setErrorDesc(Constants.INLIEULENGTHERROR
												+ "||"
												+ Constants.SNLENGTHERROR);
							}

							installableForm.setDateofManufacturing(convertor
									.getDateTime4(manufacturingDate));
							installableForm.setDateofReceipt(convertor
									.getDateTime4(receiptDate));
						}
					}
				} else {
					System.out.println("inside else");
					emmsDataForm
							.setInstallableStatus(Constants.VALIDATIONCOMPLETED);
					System.out.println(emmsDataForm.toString());
					if (Constants.VALIDATIONCOMPLETED.equals(this.emmsDataForm
							.getAssetConfigStatus())
							&& emmsDataForm.getInstallableStatus().equals(
									Constants.VALIDATIONCOMPLETED)) {

						System.out.println("inside else record status:");
						emmsDataForm
								.setRecordStatus(Constants.VALIDATIONCOMPLETED);
					} else {
						emmsDataForm
								.setRecordStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
					}
					halService.updateStatus(Constants.VALIDATIONCOMPLETED,
							emmsDataForm.getRecordStatus(),
							emmsDataForm.getRecordId());
				}
				emmsDataForm.setInductionDate(convertor.getDate2(emmsDataForm
						.getInductionDate()));
				emmsDataForm.setSignalOutDate(convertor.getDate2(emmsDataForm
						.getSignalOutDate()));
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");
			} else if (action.equals("Save")) {
				DateConvertor convertor = new DateConvertor();
				String inductionDate = "";
				String signalOutDate = "";
				if (emmsDataForm.getSignalOutDate().length() > 0
						&& emmsDataForm.getInductionDate().length() > 0) {
					inductionDate = emmsDataForm.getInductionDate();
					signalOutDate = emmsDataForm.getSignalOutDate();
					emmsDataForm.setInductionDate(convertor
							.getDate(inductionDate));
					emmsDataForm.setSignalOutDate(convertor
							.getDate(signalOutDate));
				}

				else {

					emmsDataForm.setInductionDate("");
					emmsDataForm.setSignalOutDate("");
					// assetConfigForm.setErrorDesc(Constants.NOTNULL);
				}

				List<InstallableAssetForm> installableList = emmsDataForm
						.getInstallableFormList();
				if (null != installableList && installableList.size() > 0) {
					System.out.println(installableList);
					int i = 0;
					emmsDataForm.setInstallableStatus("");
					emmsDataForm.setRecordStatus("");
					for (InstallableAssetForm installableForm : installableList) {
						installableForm.setInstalledPNList(installableFormList
								.get(i).getInstalledPNList());
						installableForm.setConditionCodes(installableFormList
								.get(i).getConditionCodes());
						installableForm.setRecordRowId(installableFormList.get(
								i).getRecordRowId());
						i++;
						String mfgDate = installableForm
								.getDateofManufacturing();
						String receiptDate = installableForm.getDateofReceipt();

						installableForm.setDateofManufacturing(convertor
								.getDateTime3(mfgDate));
						installableForm.setDateofReceipt(convertor
								.getDateTime3(receiptDate));

						System.out.println(halService.update(emmsDataForm,
								installableForm));

						installableForm.setDateofManufacturing(mfgDate);
						installableForm.setDateofReceipt(receiptDate);

					}
				}

				emmsDataForm.setInductionDate(inductionDate);
				emmsDataForm.setSignalOutDate(signalOutDate);

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");
			}else if (action.equals("Export")) {

				System.out.println("INSIDE EXPORT");

				String filename = "C:\\Users\\Public\\Desktop\\Installable.xlsx";
				//String filename = "D:\\Installable.xlsx";
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("InstallableExportedData");
				XSSFRow head = sheet.createRow((short) 0);
				head.createCell(0).setCellValue(Constants.ENDITEMPN);
				head.createCell(1).setCellValue(Constants.ENDITEMSN);
				head.createCell(2).setCellValue(Constants.INSTALLABLEMODEL);
				head.createCell(3).setCellValue(Constants.LCN);
				head.createCell(4).setCellValue(Constants.Position);
				head.createCell(5).setCellValue(Constants.BuildItem);
				head.createCell(6).setCellValue(Constants.AssetNum);
				head.createCell(7).setCellValue(Constants.PN);
				head.createCell(8).setCellValue(Constants.PartDescription);
				head.createCell(9).setCellValue(Constants.SN);
				head.createCell(10).setCellValue(Constants.InstalledPN);
				head.createCell(11).setCellValue(Constants.InLieuPN);
				head.createCell(12).setCellValue(Constants.InstalledSN);
				head.createCell(13).setCellValue(Constants.ConditionCode);
				head.createCell(14).setCellValue(Constants.DateofManufacturing);
				head.createCell(15).setCellValue(Constants.DateofReceipt);
				head.createCell(16).setCellValue(Constants.ErrorStatus);
				head.createCell(17).setCellValue(Constants.ErrorDescription);

				XSSFRow row = null;

				int rowIndex = 1;

				List<InstallableAssetForm> installableForms = emmsDataForm
						.getInstallableFormList();
				if (null != installableForms && installableForms.size() > 0) {
					int i=0;
					for (InstallableAssetForm installConfigForm : installableForms) {
						installConfigForm.setInstalledPNList(installableFormList
								.get(i).getInstalledPNList());
						installConfigForm.setConditionCodes(installableFormList
								.get(i).getConditionCodes());
						installConfigForm.setRecordRowId(installableFormList.get(
								i).getRecordRowId());
						i++;
						System.out.println("count=" + rowIndex);
						row = sheet.createRow((short) rowIndex);

						row.createCell(0).setCellValue(
								installConfigForm.getEnditemPN());
						row.createCell(1).setCellValue(
								installConfigForm.getEnditemsn());
						row.createCell(2).setCellValue(
								installConfigForm.getInstallableModel());
						row.createCell(3)
								.setCellValue(installConfigForm.getLcn());
						row.createCell(4).setCellValue(
								installConfigForm.getPosition());
						row.createCell(5).setCellValue(
								installConfigForm.getBuilditem());
						row.createCell(6).setCellValue(
								installConfigForm.getAssertnum());
						row.createCell(7).setCellValue(
								installConfigForm.getPartnum());
						row.createCell(8).setCellValue(
								installConfigForm.getPartDesc());
						row.createCell(9).setCellValue(
								installConfigForm.getSerialnum());
						row.createCell(10).setCellValue(
								installConfigForm.getInstallablePN());
						row.createCell(11).setCellValue(
								installConfigForm.getiNlieuPN());
						row.createCell(12).setCellValue(
								installConfigForm.getInstallableSN());
						row.createCell(13).setCellValue(
								installConfigForm.getConditionCode());
						row.createCell(14).setCellValue(
								installConfigForm.getDateofManufacturing());
						row.createCell(15).setCellValue(
								installConfigForm.getDateofReceipt());
						row.createCell(16).setCellValue(
								installConfigForm.getErrorStatus());
						row.createCell(17).setCellValue(
								installConfigForm.getErrorDescription());

						rowIndex++;
					}

				}
				FileOutputStream fout;
				try {
					fout = new FileOutputStream(filename);
					workbook.write(fout);
					fout.close();

				} catch (Exception e) {

					e.printStackTrace();
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (action.equals("Import")) {

				String s = emmsDataForm.getInstallableExcelfile()
						.getOriginalFilename();

				List<InstallableAssetForm> oldInstallableList = emmsDataForm
						.getInstallableFormList();
				List<InstallableAssetForm> installableList = new ArrayList<InstallableAssetForm>();

				XSSFWorkbook workbook = null;
				try {
					workbook = new XSSFWorkbook(emmsDataForm
							.getInstallableExcelfile().getInputStream());
				} catch (IOException e) {

				}

				XSSFSheet worksheet = workbook.getSheetAt(0);
				boolean headerStatus = installableValidator.validateHeader(worksheet
						.getRow(0));
				boolean countRows = oldInstallableList.size() == worksheet
						.getLastRowNum();
				System.out.println(headerStatus + ":" + countRows);

				if (headerStatus && countRows) {

					for (int j = 1, i = 0; j <= worksheet.getLastRowNum(); j++, i++) {
						InstallableAssetForm installableForm = new InstallableAssetForm();
						XSSFRow row = worksheet.getRow(j);

						assetValidator.checkCellType2003(row.getCell(0));

						installableForm.setEnditemPN((oldInstallableList.get(i)
								.getEnditemPN()));
						installableForm.setEnditemsn((oldInstallableList.get(i)
								.getEnditemsn()));
						installableForm.setInstallableModel(oldInstallableList.get(i).getInstallableModel());
						installableForm.setLcn(oldInstallableList.get(i).getLcn());
						installableForm.setPosition(oldInstallableList.get(i)
								.getPosition());
						installableForm.setBuilditem(oldInstallableList.get(i)
								.getBuilditem());
						installableForm.setAssertnum(oldInstallableList.get(i)
								.getAssertnum());
						installableForm
								.setPartnum(oldInstallableList.get(i).getPartnum());
						installableForm.setPartDesc(oldInstallableList.get(i)
								.getPartDesc());
						installableForm.setSerialnum(oldInstallableList.get(i)
								.getSerialnum());
						installableForm.setInstalledPNList(installableFormList.get(i)
								.getInstalledPNList());
						installableForm.setConditionCodes(installableFormList.get(i)
								.getConditionCodes());
						installableForm.setRecordRowId(installableFormList.get(
								i).getRecordRowId());
						if (row.getCell(8) != null) {
							String installedPN = commonValidator
									.checkCellType2003(row.getCell(8));
							System.out.println(installedPN);
							installableForm.setInstallablePN(installedPN);

						}
						if (row.getCell(9) != null) {
							String inLieuPn = commonValidator
									.checkCellType2003(row.getCell(9));
							installableForm.setiNlieuPN(inLieuPn);
						}
						if (row.getCell(10) != null) {
							String installedSN = commonValidator
									.checkCellType2003(row.getCell(10));
							installableForm.setInstallableSN(installedSN);
						}
						if (row.getCell(11) != null) {
							String conditionCode = commonValidator
									.checkCellType2003(row.getCell(11));
							installableForm.setConditionCode(conditionCode);
						}
						if (row.getCell(12) != null) {
							String dateOfManfacturing = commonValidator
									.checkCellType2003(row.getCell(12));
							System.out.println(dateOfManfacturing);
							if (commonValidator.timeStampValidate1(
									dateOfManfacturing).equals(
									Constants.NOERROR)) {
								System.out.println("insdie manu");
								installableForm
										.setDateofManufacturing(dateOfManfacturing);
							}
						}
						if (row.getCell(13) != null) {
							String dateOfReciept = commonValidator
									.checkCellType2003(row.getCell(13));

							if (commonValidator.timeStampValidate1(
									dateOfReciept).equals(Constants.NOERROR)) {
								System.out.println("inside");
								installableForm.setDateofReceipt(dateOfReciept);
							}
						}

						installableList.add(installableForm);
						System.out.println(installableForm.toString());

					}
					emmsDataForm.setInstallableFormList(installableList);

				} else

				{
					emmsDataForm.setBulkImportStatus("Error");
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/InstallableAsset.jsp");

			}
		}
		
		if(disableIndicator.equals("1")){
			model.addAttribute("disableIndicator", disableIndicator);
			System.out.println("Disable indicator in Installabel Controller: "+disableIndicator);
		}

		return "basic";
	}
}
