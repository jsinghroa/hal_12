/**
 * AssetConfigValidator is a validation class to validate user input on 'Asset Configuration Details' screen
 */

/**
 * @author Resham Das
 *
 */

package com.wipro.iaf.emms.validator;

import com.wipro.iaf.emms.constants.*;
import com.wipro.iaf.emms.form.AssetConfigForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AssetConfigValidator {

	public String assetConfigErrorMsg = Constants.NOERROR;
	CommonValidator commonValidator = new CommonValidator();

	String formatValidation;

	public String assetConfigValidate(String inductionDate,
			String signalOutDate, String installedPN, String inLieuPN,
			String installedSN, String conditionCode, String manufactorDate,
			String receiptDate, List<String> pnsncombination,
			List<String> pninlieucombination, List<String> sninlieucombination,
			Map<String, AssetConfigForm> lcnForm, String parent) {

		/*
		 * System.out.println("inductionDate="+inductionDate);
		 * System.out.println("signalOutDate="+signalOutDate);
		 * System.out.println("manufactureDate="+manufactorDate);
		 * System.out.println("receiptDate="+receiptDate);
		 * System.out.println("length="+receiptDate.length());
		 */
		String pnsn = installedPN + ":" + installedSN;
		String pninlieu = installedPN + ":" + inLieuPN;
		String sninlieu = installedSN + ":" + inLieuPN;

		assetConfigErrorMsg = Constants.NOERROR;
		
		if(installedSN.length()>=50||inLieuPN.length()>=80)
		{
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.INLIEULENGTHERROR+"||"+Constants.SNLENGTHERROR;
			} else
				assetConfigErrorMsg += Constants.INLIEULENGTHERROR+"||"+Constants.SNLENGTHERROR;
		}
		
		
		if (inductionDate.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.INDUCTIONMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.INDUCTIONMANDATORYERROR;
		}

		formatValidation = commonValidator.timeStampValidate(inductionDate);
		if (!formatValidation.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + formatValidation;
			} else
				assetConfigErrorMsg += formatValidation;
		}

		if (signalOutDate.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.SIGNALOUTMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.SIGNALOUTMANDATORYERROR;
		}

		formatValidation = commonValidator.timeStampValidate(signalOutDate);
		if (!formatValidation.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + formatValidation;
			} else
				assetConfigErrorMsg += formatValidation;
		}

		// if(Timestamp.valueOf(signalOutDate).compareTo(Timestamp.valueOf(inductionDate))<=0)
		// {

		try {
			if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(signalOutDate)
					.compareTo(
							new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
									.parse(inductionDate)) <= 0) {
				if (assetConfigErrorMsg.length() > 0) {
					assetConfigErrorMsg += " || "
							+ Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
				} else
					assetConfigErrorMsg += Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
			}
		} catch (ParseException e) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
			} else
				assetConfigErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}

		// CHANGES
		if (installedPN.isEmpty() && !(inLieuPN.isEmpty())) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + Constants.INSTALLEDPNMANDATORY;
			} else
				assetConfigErrorMsg += Constants.INSTALLEDPNMANDATORY;
		}

		if (!(installedPN.isEmpty()) && !(installedSN.isEmpty())) {
			if (null != lcnForm.get(parent))

			{
				System.out.println("vALIDATOR:INSIDE IF");
				String pn = lcnForm.get(parent).getInstalledPN();
				String sn = lcnForm.get(parent).getInstalledSN();

				System.out.println("PN=" + pn);
				System.out.println("SN=" + sn);

				if (pn.isEmpty() || sn.isEmpty()) {
					if (assetConfigErrorMsg.length() > 0) {
						assetConfigErrorMsg += " || "
								+ Constants.PARENTCHILDERROR;
					} else
						assetConfigErrorMsg += Constants.PARENTCHILDERROR;

				}
			} else {
				System.out.println("vALIDATOR:INSIDE ELSE");
				if (assetConfigErrorMsg.length() > 0) {
					assetConfigErrorMsg += " || " + Constants.PARENT;
				} else
					assetConfigErrorMsg += Constants.PARENT;

			}

		}

		if (installedPN.isEmpty() && inLieuPN.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.INSTALLEDINLIEUPNMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.INSTALLEDINLIEUPNMANDATORYERROR;
		}

		formatValidation = commonValidator.textValidate(inLieuPN);
		if (!formatValidation.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + formatValidation;
			} else
				assetConfigErrorMsg += formatValidation;
		}

		if (installedSN.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.INSTALLEDSNMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.INSTALLEDSNMANDATORYERROR;
		}

		formatValidation = commonValidator.textValidate(installedSN);
		if (!formatValidation.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + formatValidation;
			} else
				assetConfigErrorMsg += formatValidation;
		}

		if ((!installedPN.isEmpty()) && (!installedSN.isEmpty())
				&& conditionCode.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.CONDITIONCODEMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.CONDITIONCODEMANDATORYERROR;
		}

		if (!(conditionCode.equals(Constants.CONDITIONCODEA) || conditionCode
				.equals(Constants.CONDITIONCODEB))) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.CONDITIONCODEVALUEERROR;
			} else
				assetConfigErrorMsg += Constants.CONDITIONCODEVALUEERROR;
		}

		if (manufactorDate.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || "
						+ Constants.DATEOFMANUFACTUREMANDATORYERROR;
			} else
				assetConfigErrorMsg += Constants.DATEOFMANUFACTUREMANDATORYERROR;
		}

		formatValidation = commonValidator.timeStampValidate(manufactorDate);
		if (!formatValidation.isEmpty()) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + formatValidation;
			} else
				assetConfigErrorMsg += formatValidation;
		}

		/*
		 * if(Timestamp.valueOf(manufactorDate).compareTo(Timestamp.valueOf(
		 * signalOutDate))>=0) { if(assetConfigErrorMsg.length() > 0) {
		 * assetConfigErrorMsg += " || " +
		 * Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR; } else
		 * assetConfigErrorMsg +=
		 * Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR; }
		 */

		try {
			if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
					.parse(manufactorDate).compareTo(
							new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
									.parse(signalOutDate)) >= 0) {
				if (assetConfigErrorMsg.length() > 0) {
					assetConfigErrorMsg += " || "
							+ Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
				} else
					assetConfigErrorMsg += Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
			}
		} catch (ParseException e) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
			} else
				assetConfigErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}

		if (receiptDate.length() > 0) {
			System.out.println("insideFormat");
			formatValidation = commonValidator.timeStampValidate(receiptDate);
			if (!formatValidation.isEmpty()) {
				if (assetConfigErrorMsg.length() > 0) {
					assetConfigErrorMsg += " || " + formatValidation;
				} else
					assetConfigErrorMsg += formatValidation;
			}
		}

		try {
			System.out.println(assetConfigErrorMsg);
			System.out.println(receiptDate.length());
			if (receiptDate.length() > 0) {
				System.out.println("inside");
				if (new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(
						receiptDate).compareTo(
						new SimpleDateFormat("dd-MMM-yy hh:mm:ss")
								.parse(signalOutDate)) >= 0) {
					if (assetConfigErrorMsg.length() > 0) {
						assetConfigErrorMsg += " || "
								+ Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
					} else
						assetConfigErrorMsg += Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
				}

			}
		} catch (ParseException e) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
			} else
				assetConfigErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}

		if (pnsncombination.contains(pnsn)) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += "||"
						+ Constants.SAMEPNSNCOMBINATIONERROR;
			} else
				assetConfigErrorMsg += Constants.SAMEPNSNCOMBINATIONERROR;
		}

		if (pninlieucombination.contains(pninlieu)) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += "||"
						+ Constants.SAMEPNLIEUCOMBINATIONERROR;
			} else
				assetConfigErrorMsg += Constants.SAMEPNLIEUCOMBINATIONERROR;
		}

		if (sninlieucombination.contains(sninlieu)) {
			if (assetConfigErrorMsg.length() > 0) {
				assetConfigErrorMsg += "||"
						+ Constants.SAMESNLIEUCOMBINATIONERROR;
			} else
				assetConfigErrorMsg += Constants.SAMESNLIEUCOMBINATIONERROR;
		}

		System.out.println(assetConfigErrorMsg);
		return assetConfigErrorMsg;
	}

	public String receiptDateValidate(String manufactorDate, String receiptDate) {

		if (receiptDate.isEmpty())
			return Constants.NOERROR;
		else
			return Constants.BLANKRECEIPTDATEMESSAGE;
	}

	public boolean validateHeader(XSSFRow xssfRow) {

		System.out.println("Checking number of columns");
		if (xssfRow.getLastCellNum()!=16) {
			System.out.println("inside");
			return false;
		}

		String indicator = xssfRow.getCell(0).getStringCellValue();
		String lcn = xssfRow.getCell(1).getStringCellValue();
		String position = xssfRow.getCell(2).getStringCellValue();
		String buildItem = xssfRow.getCell(3).getStringCellValue();
		String assetNum = xssfRow.getCell(4).getStringCellValue();
		String partNum = xssfRow.getCell(5).getStringCellValue();
		String partDescription = xssfRow.getCell(6).getStringCellValue();
		String serialNum = xssfRow.getCell(7).getStringCellValue();
		String installedPN = xssfRow.getCell(8).getStringCellValue();
		String inLieuPn = xssfRow.getCell(9).getStringCellValue();
		String installedSN = xssfRow.getCell(10).getStringCellValue();
		String conditionCode = xssfRow.getCell(11).getStringCellValue();
		String dateOfManfacturing = xssfRow.getCell(12).getStringCellValue();
		String dateOfReciept = xssfRow.getCell(13).getStringCellValue();
		String errorStatus = xssfRow.getCell(14).getStringCellValue();
		String errorDesc = xssfRow.getCell(15).getStringCellValue();
		List<String> errorList = new ArrayList<String>();
		System.out
				.println("Number of columns are correct now checking name i.e header values");
		if (Constants.INDICATOR.equalsIgnoreCase(indicator)
				&& Constants.LCN.equalsIgnoreCase(lcn)
				&& Constants.Position.equalsIgnoreCase(position)
				&& Constants.BuildItem.equalsIgnoreCase(buildItem)
				&& Constants.AssetNum.equalsIgnoreCase(assetNum)
				&& Constants.PN.equalsIgnoreCase(partNum)
				&& Constants.PartDescription.equalsIgnoreCase(partDescription)
				&& Constants.SN.equalsIgnoreCase(serialNum)
				&& Constants.InstalledPN.equalsIgnoreCase(installedPN)
				&& Constants.InLieuPN.equalsIgnoreCase(inLieuPn)
				&& Constants.InstalledSN.equalsIgnoreCase(installedSN)
				&& Constants.ConditionCode.equalsIgnoreCase(conditionCode)
				&& Constants.DateofManufacturing
						.equalsIgnoreCase(dateOfManfacturing)
				&& Constants.DateofReceipt.equalsIgnoreCase(dateOfReciept)
				&& Constants.ErrorStatus.equalsIgnoreCase(errorStatus)
				&& Constants.ErrorDescription.equalsIgnoreCase(errorDesc)) {
			return true;
		} else {
			return false;
		}

	}

	public String checkCellType2003(XSSFCell cell) {

		String strCellValue = Constants.EMPTY_STRING;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			strCellValue = (int) cell.getNumericCellValue() + "";
			System.out.println("INSIDE NUMERIC");
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			strCellValue = cell.getBooleanCellValue() + "";
			System.out.println("INSIDE NUMERIC");
			break;

		default:
			strCellValue = cell.getStringCellValue();
		}
		
		return strCellValue;
	}

}