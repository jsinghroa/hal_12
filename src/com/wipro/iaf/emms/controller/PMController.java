package com.wipro.iaf.emms.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.validator.CommonValidator;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.PMValidator;

@Controller
@RequestMapping("/PM")
public class PMController {

	@Autowired
	private FLBController flbController;

	@Autowired
	List<PMDetailForm> pmDetailFormList;

	@Autowired
	AssetController assetController;

	@Autowired
	HalService halService;

	@Autowired
	MeterController meterController;

	@Autowired
	private EmmsDataForm emmsDataForm;

	@Autowired
	InstallableAssetController installAssetController;

	@Autowired
	PMValidator pmValidator;

	CommonValidator commonValidator = new CommonValidator();
	Logger logger = Logger.getLogger(PMController.class);

	@RequestMapping(value = { "/savePm" }, method = RequestMethod.GET)
	public List<PMDetailForm> fetchPMDetails(String recordID, EmmsDataForm emmsDataForm) {
		this.emmsDataForm = emmsDataForm;
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		pmDetailFormList = halService.fetchPMDetails(recordID);
		return pmDetailFormList;
	}

	@RequestMapping(value = "/savePm", method = RequestMethod.POST)
	public String submit(
			@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm,
			BindingResult bindingResult, ModelMap model,
			@RequestParam String action, @RequestParam String linkSelected,HttpServletResponse response) {

		if (linkSelected.equals(Constants.LISTVIEW)) {
			List<EmmsDataForm> emmsDataFormList = halService.getEmmsDataOnView();
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		} else {

			if (linkSelected.equals(Constants.ASSETCONFIG)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
					emmsDataForm.setAssetFormList(
							assetController.fetchAssetDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {

				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
					emmsDataForm.setAssetFormList(
							assetController.fetchAssetDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
					for (int i = 0; i < emmsDataForm.getAssetFormList().size(); i++) {
						System.out.println(
								"listviewEmmsdataController------ size" + emmsDataForm.getAssetFormList().get(i));
						if (emmsDataForm.getAssetFormList().get(i).getErrorStatus() != null) {
							System.out.println("error status value"
									+ emmsDataForm.getAssetFormList().get(i).getErrorStatus().toString());
							System.out.println("indicator value"
									+ emmsDataForm.getAssetFormList().get(i).getIndicator().toString());

							if (emmsDataForm.getAssetFormList().get(i).getErrorStatus()
									.equals(Constants.VALIDATEDWITHWARNING)
									&& emmsDataForm.getAssetFormList().get(i).getIndicator().equals("I")) {
								emmsDataForm.setInstallableFormList(installAssetController
										.fetchInstallableDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
							}
						}
					}

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());
					emmsDataForm
							.setPmDetailFormList(this.fetchPMDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {

				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId() && !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm.getSelectedRecordId());

					emmsDataForm.setMeterFormList(
							meterController.fetchMeterDetails(emmsDataForm.getSelectedRecordId(), emmsDataForm));
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
				logger.info("Inside PMController Validate");
				List<PMDetailForm> pmFormList = emmsDataForm.getPmDetailFormList();
				Map<String, String> mpmNumbersMap = new HashMap<String, String>();
				DateConvertor convertor = new DateConvertor();

				boolean flag = true;
				String status = Constants.NOTVALIDATED;
				String inductionDate = "";
				String signalOutDate = "";

				if (0 < emmsDataForm.getSignalOutDate().length() && 0 < emmsDataForm.getInductionDate().length()) {
					inductionDate = emmsDataForm.getInductionDate();
					signalOutDate = emmsDataForm.getSignalOutDate();
					emmsDataForm.setInductionDate(convertor.getDate(inductionDate));
					emmsDataForm.setSignalOutDate(convertor.getDate(signalOutDate));
				} else {
					emmsDataForm.setInductionDate("");
					emmsDataForm.setSignalOutDate("");
				}

				emmsDataForm.setAssetPmStatus(Constants.VALIDATIONCOMPLETED);
				if (null != pmFormList && pmFormList.size() > 0) {
					int i = 0;
					for (PMDetailForm pmForm : pmFormList) {

						pmForm.setRecordRowId(pmDetailFormList.get(i).getRecordRowId());
						pmForm.setComplianceStatusOptions(pmDetailFormList.get(i).getComplianceStatusOptions());
						
						String lastCompiledDate = "";
						String nextDueDate = "";
						String validate = "";

						if (pmForm.getLastCompiledDate().length() > 0) {
							lastCompiledDate = pmForm.getLastCompiledDate();
						}

						if (pmForm.getNextDueDate().length() > 0) {
							nextDueDate = pmForm.getNextDueDate();
						}

						pmForm.setLastCompiledDate(lastCompiledDate);
						pmForm.setNextDueDate(nextDueDate);

						if(pmForm.getComplianceStatus().equals("Performed")) {
							validate = pmValidator.pmValidate(pmForm, inductionDate, signalOutDate, lastCompiledDate,
									nextDueDate, pmForm.getLastCompiledValue(), pmForm.getNextDueValue(),
									pmForm.getFrequencyUnit(), mpmNumbersMap);
						}
						
						if(!mpmNumbersMap.containsKey(pmForm.getMpmNum())) {
							mpmNumbersMap.put(pmForm.getMpmNum(), pmForm.getLastCompiledDate());
						}
						
						if(pmForm.getComplianceStatus().equals("Performed")) {
							
							if (validate == "") {
								// NO WARNING ALL CORRECT
								System.out.println("INSIDE VALIDATED");
								status = Constants.VALIDATED;
								pmForm.setErrorDesc(validate);
								pmForm.setErrorStatus(status);
							} else {
								// ANY COLUMNS DOES NOT HAVE CORRECT DATA
								System.out.println("INSIDE NOT VALIDATED");
								status = Constants.NOTVALIDATED;
								flag = true;
								pmForm.setErrorDesc(validate);
								pmForm.setErrorStatus(status);
							}
						} else {
							pmForm.setErrorDesc("");
							pmForm.setErrorStatus("");
						}
						
						

						if (pmForm.getLastCompiledDate().length() > 0) {
							pmForm.setLastCompiledDate(convertor.getDateTime(pmForm.getLastCompiledDate()));
						}

						if (pmForm.getNextDueDate().length() > 0) {
							pmForm.setNextDueDate(convertor.getDateTime(pmForm.getNextDueDate()));
						}

						// Code for validation ends here
						//if (pmForm.getLastCompiledDate().length() > 0 && pmForm.getNextDueDate().length() > 0) {
						if (pmForm.getLastCompiledDate().length() > 0) {
							halService.update(emmsDataForm, pmForm);
						}

						if (pmForm.getLastCompiledDate().length() > 0) {
							pmForm.setLastCompiledDate(convertor.getDateTime5(lastCompiledDate));
						}

						if (pmForm.getNextDueDate().length() > 0) {
							pmForm.setNextDueDate(convertor.getDateTime5(nextDueDate));
						}

						pmDetailFormList.get(i).setLastCompiledDate(pmForm.getLastCompiledDate());
						pmDetailFormList.get(i).setNextDueDate(pmForm.getNextDueDate());
						i++;
					}

					if (flag == true) {

						emmsDataForm.setAssetPmStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
					} else {

						emmsDataForm.setAssetPmStatus(Constants.VALIDATIONCOMPLETED);
					}
				}

				emmsDataForm.setInductionDate(inductionDate);
				emmsDataForm.setSignalOutDate(signalOutDate);
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");

			} else if (action.equals("Save")) {

				List<PMDetailForm> pmFormList = emmsDataForm.getPmDetailFormList();

				DateConvertor convertor = new DateConvertor();

				String status = Constants.NOTVALIDATED;
				String time = new Time(System.currentTimeMillis()).toString();
				String inductionDate = "";
				String signalOutDate = "";
				if (0 < emmsDataForm.getSignalOutDate().length() && 0 < emmsDataForm.getInductionDate().length()) {
					inductionDate = emmsDataForm.getInductionDate();
					signalOutDate = emmsDataForm.getSignalOutDate();
					emmsDataForm.setInductionDate(convertor.getDate(inductionDate));
					emmsDataForm.setSignalOutDate(convertor.getDate(signalOutDate));
				} else {
					emmsDataForm.setInductionDate("");
					emmsDataForm.setSignalOutDate("");
				}
				emmsDataForm.setAssetPmStatus(Constants.VALIDATIONCOMPLETEDWITHERRORS);
				if (null != pmFormList && pmFormList.size() > 0) {

					int i = 0;
					for (PMDetailForm pmForm : pmFormList) {

						pmForm.setRecordRowId(pmDetailFormList.get(i).getRecordRowId());
						pmForm.setComplianceStatusOptions(pmDetailFormList.get(i).getComplianceStatusOptions());

						String lastCompiledDate = "";
						String nextDueDate = "";

						if (pmForm.getLastCompiledDate().length() > 0) {
							lastCompiledDate = pmForm.getLastCompiledDate() + " " + time;
						}

						if (pmForm.getNextDueDate().length() > 0) {
							nextDueDate = pmForm.getNextDueDate() + " " + time;
						}

						pmForm.setLastCompiledDate(lastCompiledDate);
						pmForm.setNextDueDate(nextDueDate);

						pmForm.setErrorStatus("");
						pmForm.setErrorDesc("");

						if (pmForm.getLastCompiledDate().length() > 0) {

							pmForm.setLastCompiledDate(convertor.getDateTime(pmForm.getLastCompiledDate()));
						}

						if (pmForm.getNextDueDate().length() > 0) {

							pmForm.setNextDueDate(convertor.getDateTime(pmForm.getNextDueDate()));
						}

						// Code for validation ends here
						//if (pmForm.getLastCompiledDate().length() > 0 && pmForm.getNextDueDate().length() > 0) {
						if (pmForm.getLastCompiledDate().length() > 0) {
							halService.update(emmsDataForm, pmForm);
						}

						if (pmForm.getLastCompiledDate().length() > 0) {
							pmForm.setLastCompiledDate(convertor.getDateTime5(lastCompiledDate));
						}

						if (pmForm.getNextDueDate().length() > 0) {
							pmForm.setNextDueDate(convertor.getDateTime5(nextDueDate));
						}

						pmDetailFormList.get(i).setLastCompiledDate(pmForm.getLastCompiledDate());
						pmDetailFormList.get(i).setNextDueDate(pmForm.getNextDueDate());
						i++;
						// }

					}
				}
				emmsDataForm.setInductionDate(inductionDate);
				emmsDataForm.setSignalOutDate(signalOutDate);
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");

			} else if (action.equals("Export")) {

				System.out.println("INSIDE EXPORT");
				
				String filename = "PM.xlsx";
				//String filename = "D:\\PM.xlsx";

				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("PMExportedData");
				XSSFRow head = sheet.createRow((short) 0);
				head.createCell(0).setCellValue(Constants.InstalledPN);
				head.createCell(1).setCellValue(Constants.INSTALLEDPARTDESCRIPTION);
				head.createCell(2).setCellValue(Constants.InstalledSN);
				head.createCell(3).setCellValue(Constants.WORKTYPE);
				head.createCell(4).setCellValue(Constants.MPM);
				head.createCell(5).setCellValue(Constants.MPMDESCRIPTION);
				head.createCell(6).setCellValue(Constants.PMMETER);
				head.createCell(7).setCellValue(Constants.FREQUENCY);
				head.createCell(8).setCellValue(Constants.FREQUENCYUNIT);
				head.createCell(9).setCellValue(Constants.LASTCOMPLIEDDATE);
				head.createCell(10).setCellValue(Constants.NEXTDUEDATE);
				head.createCell(11).setCellValue(Constants.LASTCOMPLIEDVALUE);
				head.createCell(12).setCellValue(Constants.NEXTDUEVALUE);
				head.createCell(13).setCellValue(Constants.ErrorStatus);
				head.createCell(14).setCellValue(Constants.ErrorDescription);
				XSSFRow row = null;
				int rowIndex = 1;
				List<PMDetailForm> pmForms = emmsDataForm.getPmDetailFormList();
				if (null != pmForms && pmForms.size() > 0) {

					for (PMDetailForm pmExportForm : pmForms) {
						System.out.println("count=" + rowIndex);
						row = sheet.createRow((short) rowIndex);
						row.createCell(0).setCellValue(pmExportForm.getInstalledPN());
						row.createCell(1).setCellValue(pmExportForm.getInstalledPartDesc());
						row.createCell(2).setCellValue(pmExportForm.getInstallSerialNum());
						row.createCell(3).setCellValue(pmExportForm.getWorkType());
						row.createCell(4).setCellValue(pmExportForm.getMpmNum());
						row.createCell(5).setCellValue(pmExportForm.getMpmDescription());
						row.createCell(6).setCellValue(pmExportForm.getMeterName());
						row.createCell(7).setCellValue(pmExportForm.getFrequencyIteration());
						row.createCell(8).setCellValue(pmExportForm.getFrequencyUnit());
						row.createCell(9).setCellValue(pmExportForm.getLastCompiledDate());
						row.createCell(10).setCellValue(pmExportForm.getNextDueDate());
						if (null != pmExportForm.getLastCompiledValue()) {
							row.createCell(11).setCellValue(pmExportForm.getLastCompiledValue().toPlainString());

						} else {
							row.createCell(11).setCellValue("");
						}
						if (null != pmExportForm.getNextDueValue()) {
							System.out.println(pmExportForm.getNextDueValue().toPlainString());
							row.createCell(12).setCellValue(pmExportForm.getNextDueValue().toPlainString());

						} else {
							row.createCell(12).setCellValue("");
						}

						row.createCell(13).setCellValue(pmExportForm.getErrorStatus());
						row.createCell(14).setCellValue(pmExportForm.getErrorDesc());
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
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");

			} else if (action.equals("Import")) {

				String s = emmsDataForm.getPmExcelfile().getOriginalFilename();

				List<PMDetailForm> oldPmList = emmsDataForm.getPmDetailFormList();
				List<PMDetailForm> excelPmList = new ArrayList<PMDetailForm>();

				XSSFWorkbook workbook = null;
				try {
					workbook = new XSSFWorkbook(emmsDataForm.getPmExcelfile().getInputStream());
				} catch (IOException e) {

				}

				XSSFSheet worksheet = workbook.getSheetAt(0);
				boolean headerStatus = pmValidator.validateHeader(worksheet.getRow(0));
				boolean countRows = oldPmList.size() == worksheet.getLastRowNum();
				System.out.println(headerStatus + ":" + countRows);

				if (headerStatus && countRows) {

					for (int j = 1, i = 0; j <= worksheet.getLastRowNum(); j++, i++) {
						PMDetailForm excelPmForm = new PMDetailForm();
						XSSFRow row = worksheet.getRow(j);

						commonValidator.checkCellType2003(row.getCell(0));

						excelPmForm.setInstalledPN(((oldPmList.get(i).getInstalledPN())));
						excelPmForm.setInstalledPartDesc((oldPmList.get(i).getInstalledPartDesc()));
						excelPmForm.setInstallSerialNum(oldPmList.get(i).getInstallSerialNum());
						excelPmForm.setWorkType(oldPmList.get(i).getWorkType());
						excelPmForm.setMpmNum(oldPmList.get(i).getMpmNum());
						excelPmForm.setMpmDescription(oldPmList.get(i).getMpmDescription());
						excelPmForm.setMeterName(oldPmList.get(i).getMeterName());
						excelPmForm.setFrequencyIteration(oldPmList.get(i).getFrequencyIteration());
						excelPmForm.setFrequencyUnit(oldPmList.get(i).getFrequencyUnit());
						excelPmForm.setRecordRowId(pmDetailFormList.get(i).getRecordRowId());

						if (row.getCell(9) != null) {
							String lastCompliedDate = commonValidator.checkCellType2003(row.getCell(9));
							if (commonValidator.timeStampValidate1(lastCompliedDate).equals(Constants.NOERROR)) {
								excelPmForm.setLastCompiledDate(lastCompliedDate);
							}
						}
						if (row.getCell(10) != null) {
							String nextDueDate = commonValidator.checkCellType2003(row.getCell(10));
							if (commonValidator.timeStampValidate1(nextDueDate).equals(Constants.NOERROR)) {

								excelPmForm.setNextDueDate(nextDueDate);
							}
						}
						if (row.getCell(11) != null) {
							System.out.println("LCV:" + row.getCell(11) + ":"
									+ commonValidator.checkCellType2003(row.getCell(11)));
							if (commonValidator.checkCellType2003(row.getCell(11)).isEmpty()) {

							} else {
								System.out.println("inside else pm");
								excelPmForm.setLastCompiledValue(
										new BigDecimal(commonValidator.checkCellType2003(row.getCell(11))));
							}
						}
						if (row.getCell(12) != null) {
							System.out.println("LCV:" + row.getCell(12) + ":"
									+ commonValidator.checkCellType2003(row.getCell(12)));
							if (commonValidator.checkCellType2003(row.getCell(12)).isEmpty()) {

							} else {
								System.out.println("inside else pm");

								excelPmForm.setNextDueValue(
										new BigDecimal(commonValidator.checkCellType2003(row.getCell(12))));
							}
						}
						excelPmForm.setErrorStatus(oldPmList.get(i).getErrorStatus());
						excelPmForm.setErrorDesc(oldPmList.get(i).getErrorDesc());

						excelPmList.add(excelPmForm);
						System.out.println(excelPmForm.toString());

					}
					emmsDataForm.setPmDetailFormList(excelPmList);

				} else

				{
					emmsDataForm.setBulkImportStatus("Error");
				}

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");

			}

		}
		return "basic";
	}
}
