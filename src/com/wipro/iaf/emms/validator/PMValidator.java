package com.wipro.iaf.emms.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.PMDetailForm;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PMValidator {

	public String pmErrorMsg = Constants.NOERROR;
	CommonValidator commonValidator = new CommonValidator();

	String formatValidation;

	public String pmValidate(PMDetailForm pmForm, String inductionDate, String signalOutDate,
			String lastCompliedDate, String nextDueDate,
			BigDecimal lastCompliedValue, BigDecimal nextDueValue, String frequencyUnit, Map<String, String> mpmNumbersMap) {

		pmErrorMsg = Constants.NOERROR;

		// induction date validations
		if (inductionDate.isEmpty()) {
			if (pmErrorMsg.length() > 0) {
				pmErrorMsg += " || " + Constants.INDUCTIONMANDATORYERROR;
			} else
				pmErrorMsg += Constants.INDUCTIONMANDATORYERROR;
		}
		formatValidation = commonValidator.timeStampValidate(inductionDate);
		if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
			if (!formatValidation.isEmpty()) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + formatValidation;
				} else
					pmErrorMsg += formatValidation;
			}
		}

		// signal out date validations
		if (signalOutDate.isEmpty()) {
			if (pmErrorMsg.length() > 0) {
				pmErrorMsg += " || " + Constants.SIGNALOUTMANDATORYERROR;
			} else
				pmErrorMsg += Constants.SIGNALOUTMANDATORYERROR;
		}
		formatValidation = commonValidator.timeStampValidate(signalOutDate);
		if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
			if (!formatValidation.isEmpty()) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + formatValidation;
				} else
					pmErrorMsg += formatValidation;
			}
		}

		// validation for induction date should be lesser than signal out date
		try {
			if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(signalOutDate)
					.compareTo(
							new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
									.parse(inductionDate)) <= 0) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || "
							+ Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
				} else
					pmErrorMsg += Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
			}
		} catch (ParseException e) {
			if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
				} else
					pmErrorMsg += Constants.TIMESTAMPFORMATERROR;
			}
		}
		
		//MPM validations, for same MPM all rows must have same LastComplied Date.
		if(mpmNumbersMap.containsKey(pmForm.getMpmNum())) {
			if(!pmForm.getLastCompiledDate().equals(mpmNumbersMap.get(pmForm.getMpmNum()))) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.LASTCOMPLIEDDATEDIFFERENTFORSAMEMPM;
				} else
					pmErrorMsg += Constants.LASTCOMPLIEDDATEDIFFERENTFORSAMEMPM;
			}
		}
		

		// last complied date validations
		if (lastCompliedDate.isEmpty()) {
			if (pmErrorMsg.length() > 0) {
				pmErrorMsg += " || " + Constants.LASTCOMPLIEDDATEMANDATORYERROR;
			} else
				pmErrorMsg += Constants.LASTCOMPLIEDDATEMANDATORYERROR;
		}
		formatValidation = commonValidator.timeStampValidate(lastCompliedDate);
		if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
			if (!formatValidation.isEmpty()) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + formatValidation;
				} else
					pmErrorMsg += formatValidation;
			}
		}
		
		try {
			if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(signalOutDate)
					.compareTo(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(lastCompliedDate)) <= 0) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || "
							+ Constants.LASTCOMPLIEDDATEGREATERTOSIGNALOUTERROR;
				} else
					pmErrorMsg += Constants.LASTCOMPLIEDDATEGREATERTOSIGNALOUTERROR;
			}
		} catch (ParseException e) {
			if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
				} else
					pmErrorMsg += Constants.TIMESTAMPFORMATERROR;
			}
		}

		// next due date validations
		if(frequencyUnit.equals("YEARS")) {
			if (nextDueDate.isEmpty()) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.NEXTDUEDATEMANDATORYERROR;
				} else
					pmErrorMsg += Constants.NEXTDUEDATEMANDATORYERROR;
			}
			formatValidation = commonValidator.timeStampValidate(nextDueDate);
			if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
				if (!formatValidation.isEmpty()) {
					if (pmErrorMsg.length() > 0) {
						pmErrorMsg += " || " + formatValidation;
					} else
						pmErrorMsg += formatValidation;
				}
			}
			
			try {
				if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(nextDueDate)
						.compareTo(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(lastCompliedDate)) <= 0) {
					if (pmErrorMsg.length() > 0) {
						pmErrorMsg += " || "
								+ Constants.LASTCOMPLIEDDATEGREATERTONEXTDUEDATEERROR;
					} else
						pmErrorMsg += Constants.LASTCOMPLIEDDATEGREATERTONEXTDUEDATEERROR;
				}
			} catch (ParseException e) {
				if(!pmErrorMsg.contains(Constants.TIMESTAMPFORMATERROR)){
					if (pmErrorMsg.length() > 0) {
						pmErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
					} else
						pmErrorMsg += Constants.TIMESTAMPFORMATERROR;
				}
			}
		}

		// last complied value validations
		if(frequencyUnit.equals("")) {
			if (!(lastCompliedValue != null)) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.LASTCOMPLIEDVALUEMANDATORYERROR;
				} else
					pmErrorMsg += Constants.LASTCOMPLIEDVALUEMANDATORYERROR;
			}
			if(lastCompliedValue != null){
				if(lastCompliedValue.doubleValue() < 0){
					if (pmErrorMsg.length() > 0) {
						pmErrorMsg += " || " + Constants.LASTCOMPLIEDVALUENEGATIVEERROR;
					} else
						pmErrorMsg += Constants.LASTCOMPLIEDVALUENEGATIVEERROR;
				}
			}
		}
		if(frequencyUnit.equals("YEARS")){
			if (lastCompliedValue != null) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.LASTCOMPLIEDVALUENULLERROR;
				} else
					pmErrorMsg += Constants.LASTCOMPLIEDVALUENULLERROR;
			}
		}

		// next due value validations		
		if(frequencyUnit.equals("")) {
			if (!(nextDueValue != null)) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.NEXTDUEVALUEMANDATORYERROR;
				} else
					pmErrorMsg += Constants.NEXTDUEVALUEMANDATORYERROR;
			}
			if(nextDueValue != null){
				if(nextDueValue.doubleValue() < 0){
					if (pmErrorMsg.length() > 0) {
						pmErrorMsg += " || " + Constants.NEXTDUEVALUENEGATIVEERROR;
					} else
						pmErrorMsg += Constants.NEXTDUEVALUENEGATIVEERROR;
				}
			}
		}
		
		if(nextDueValue != null && lastCompliedValue != null){
			if (nextDueValue.compareTo(lastCompliedValue) <= 0) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || "
							+ Constants.LASTCOMPLIEDVALUEGREATERTONEXTDUEVALUEERROR;
				} else
					pmErrorMsg += Constants.LASTCOMPLIEDVALUEGREATERTONEXTDUEVALUEERROR;
			}
		}
		
		if(frequencyUnit.equals("YEARS")){
			if (nextDueValue != null) {
				if (pmErrorMsg.length() > 0) {
					pmErrorMsg += " || " + Constants.NEXTDUEVALUENULLERROR;
				} else
					pmErrorMsg += Constants.NEXTDUEVALUENULLERROR;
			}
		}

		return pmErrorMsg;
	}
	
	public boolean validateHeader(XSSFRow xssfRow) {
		System.out.println("Checking number of columns");
		if (xssfRow.getLastCellNum()!=15) {
			System.out.println("inside");
			return false;
		}

		String installedPN = xssfRow.getCell(0).getStringCellValue();
		String installedPartDescription = xssfRow.getCell(1).getStringCellValue();
		String installedSN = xssfRow.getCell(2).getStringCellValue();
		String workType = xssfRow.getCell(3).getStringCellValue();
		String mpm = xssfRow.getCell(4).getStringCellValue();
		String mpmDescription = xssfRow.getCell(5).getStringCellValue();
		String pmMeter = xssfRow.getCell(6).getStringCellValue();
		String frequency = xssfRow.getCell(7).getStringCellValue();
		String frequencyUnit = xssfRow.getCell(8).getStringCellValue();
		String lastCompliedDate = xssfRow.getCell(9).getStringCellValue();
		String lastCompliedValue = xssfRow.getCell(11).getStringCellValue();
		String nextDueDate = xssfRow.getCell(10).getStringCellValue();
		String nextDueValue = xssfRow.getCell(12).getStringCellValue();
		String errorStatus = xssfRow.getCell(13).getStringCellValue();
		String errorDesc = xssfRow.getCell(14).getStringCellValue();
		List<String> errorList = new ArrayList<String>();
		System.out.println("Number of columns are correct now checking name i.e header values");
		if (Constants.INSTALLEDPARTDESCRIPTION.equalsIgnoreCase(installedPartDescription)
				&& Constants.InstalledPN.equalsIgnoreCase(installedPN)
				&& Constants.InstalledSN.equalsIgnoreCase(installedSN)
				&& Constants.WORKTYPE.equalsIgnoreCase(workType) 
				&& Constants.MPM.equalsIgnoreCase(mpm)
				&& Constants.MPMDESCRIPTION.equalsIgnoreCase(mpmDescription)
				&& Constants.PMMETER.equalsIgnoreCase(pmMeter)
				&& Constants.FREQUENCY.equalsIgnoreCase(frequency)
				&& Constants.FREQUENCYUNIT.equalsIgnoreCase(frequencyUnit)
				&& Constants.LASTCOMPLIEDDATE.equalsIgnoreCase(lastCompliedDate)
				&& Constants.LASTCOMPLIEDVALUE.equalsIgnoreCase(lastCompliedValue)
				&& Constants.NEXTDUEDATE.equalsIgnoreCase(nextDueDate)
				&& Constants.NEXTDUEVALUE.equalsIgnoreCase(nextDueValue)
				&& Constants.ErrorStatus.equalsIgnoreCase(errorStatus)
				&& Constants.ErrorDescription.equalsIgnoreCase(errorDesc)) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
}
