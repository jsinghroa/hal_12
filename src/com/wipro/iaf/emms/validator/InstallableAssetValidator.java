/**
 * InstallableAssetValidator is a validation class to validate user input on 'Installable Asset' screen
 */

/**
 * @author Resham Das
 *
 */

package com.wipro.iaf.emms.validator;

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
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.InstallableAssetForm;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class InstallableAssetValidator {

	public String installableAssetErrorMsg = Constants.NOERROR;
	CommonValidator commonValidator = new CommonValidator();

	String formatValidation;

	public String installableAssetValidate(String inductionDate, String signalOutDate, String installedPN,
			String inLieuPN, String installedSN, String conditionCode, String manufactorDate, String receiptDate,
			List<String> pnsncombination, List<String> pninlieucombination, List<String> sninlieucombination,
			Map<String, InstallableAssetForm> lcnForm, String parent) {
		installableAssetErrorMsg = Constants.NOERROR;

		String pnsn = installedPN + ":" + installedSN;
		String pninlieu = installedPN + ":" + inLieuPN;
		String sninlieu = installedSN + ":" + inLieuPN;

		installableAssetErrorMsg = Constants.NOERROR;
		

		if (installedSN.length() >= 50 || inLieuPN.length() >= 80) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.INLIEULENGTHERROR + "||" + Constants.SNLENGTHERROR;
			} else
				installableAssetErrorMsg += Constants.INLIEULENGTHERROR + "||" + Constants.SNLENGTHERROR;
		}

		if (inductionDate.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.INDUCTIONMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.INDUCTIONMANDATORYERROR;
		}

		if (signalOutDate.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.SIGNALOUTMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.SIGNALOUTMANDATORYERROR;
		}

		try {
			if (new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(signalOutDate)
					.compareTo(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(inductionDate)) <= 0) {
				if (installableAssetErrorMsg.length() > 0) {
					installableAssetErrorMsg += " || " + Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
				} else
					installableAssetErrorMsg += Constants.SIGNALOUTGREATERTOINDUCTIONERROR;
			}
		} catch (ParseException e) {
			/*if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
			} else
				installableAssetErrorMsg += Constants.TIMESTAMPFORMATERROR;*/
			System.out.println(e.getMessage());
		}

		// CHANGES
		if (installedPN.isEmpty() && !(inLieuPN.isEmpty())) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.INSTALLEDPNMANDATORY;
			} else
				installableAssetErrorMsg += Constants.INSTALLEDPNMANDATORY;
		}

		if (!(installedPN.isEmpty()) && !(installedSN.isEmpty())) {
			if (null != lcnForm.get(parent)) {

				String pn = lcnForm.get(parent).getInstallablePN();
				String sn = lcnForm.get(parent).getInstallableSN();

				if (pn.isEmpty() || sn.isEmpty()) {
					if (installableAssetErrorMsg.length() > 0) {
						installableAssetErrorMsg += " || " + Constants.PARENTCHILDERROR;
					} else
						installableAssetErrorMsg += Constants.PARENTCHILDERROR;

				}
			} else {

				if (installableAssetErrorMsg.length() > 0) {
					installableAssetErrorMsg += " || " + Constants.PARENT;
				} else
					installableAssetErrorMsg += Constants.PARENT;

			}

		}

		if (installedPN.isEmpty() && inLieuPN.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.INSTALLEDINLIEUPNMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.INSTALLEDINLIEUPNMANDATORYERROR;
		}

		formatValidation = commonValidator.textValidate(inLieuPN);
		if (!formatValidation.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + formatValidation;
			} else
				installableAssetErrorMsg += formatValidation;
		}

		if (installedSN.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.INSTALLEDSNMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.INSTALLEDSNMANDATORYERROR;
		}

		formatValidation = commonValidator.textValidate(installedSN);
		if (!formatValidation.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + formatValidation;
			} else
				installableAssetErrorMsg += formatValidation;
		}

		if ((!installedPN.isEmpty()) && (!installedSN.isEmpty()) && conditionCode.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.CONDITIONCODEMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.CONDITIONCODEMANDATORYERROR;
		}

		if (!(conditionCode.equals(Constants.CONDITIONCODEA) || conditionCode.equals(Constants.CONDITIONCODEB))) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.CONDITIONCODEVALUEERROR;
			} else
				installableAssetErrorMsg += Constants.CONDITIONCODEVALUEERROR;
		}

		if (manufactorDate.isEmpty()) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += " || " + Constants.DATEOFMANUFACTUREMANDATORYERROR;
			} else
				installableAssetErrorMsg += Constants.DATEOFMANUFACTUREMANDATORYERROR;
		}

		try {
			if (new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(manufactorDate)
					.compareTo(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(signalOutDate)) >= 0) {
				if (installableAssetErrorMsg.length() > 0) {
					installableAssetErrorMsg += " || " + Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
				} else
					installableAssetErrorMsg += Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		try {
			if (receiptDate.length() > 0) {

				if (new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(receiptDate)
						.compareTo(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(signalOutDate)) >= 0) {
					if (installableAssetErrorMsg.length() > 0) {
						installableAssetErrorMsg += " || " + Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
					} else
						installableAssetErrorMsg += Constants.SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR;
				}

			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			}

		if (pnsncombination.contains(pnsn)) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += "||" + Constants.SAMEPNSNCOMBINATIONERROR;
			} else
				installableAssetErrorMsg += Constants.SAMEPNSNCOMBINATIONERROR;
		}

		if (pninlieucombination.contains(pninlieu)) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += "||" + Constants.SAMEPNLIEUCOMBINATIONERROR;
			} else
				installableAssetErrorMsg += Constants.SAMEPNLIEUCOMBINATIONERROR;
		}

		if (sninlieucombination.contains(sninlieu)) {
			if (installableAssetErrorMsg.length() > 0) {
				installableAssetErrorMsg += "||" + Constants.SAMESNLIEUCOMBINATIONERROR;
			} else
				installableAssetErrorMsg += Constants.SAMESNLIEUCOMBINATIONERROR;
		}

		System.out.println(installableAssetErrorMsg);
		return installableAssetErrorMsg;
	}

	public boolean validateHeader(XSSFRow xssfRow) {
		System.out.println("Checking number of columns");
		if (xssfRow.getLastCellNum() <= 18) {
			System.out.println("inside");
			return false;
		}
		String endItemPn = xssfRow.getCell(0).getStringCellValue();
		String endItemSn = xssfRow.getCell(1).getStringCellValue();
		String installableModel = xssfRow.getCell(2).getStringCellValue();
		String indicator = xssfRow.getCell(3).getStringCellValue();
		String lcn = xssfRow.getCell(4).getStringCellValue();
		String position = xssfRow.getCell(5).getStringCellValue();
		String buildItem = xssfRow.getCell(6).getStringCellValue();
		String assetNum = xssfRow.getCell(7).getStringCellValue();
		String partNum = xssfRow.getCell(8).getStringCellValue();
		String partDescription = xssfRow.getCell(9).getStringCellValue();
		String serialNum = xssfRow.getCell(10).getStringCellValue();
		String installedPN = xssfRow.getCell(11).getStringCellValue();
		String inLieuPn = xssfRow.getCell(12).getStringCellValue();
		String installedSN = xssfRow.getCell(13).getStringCellValue();
		String conditionCode = xssfRow.getCell(14).getStringCellValue();
		String dateOfManfacturing = xssfRow.getCell(15).getStringCellValue();
		String dateOfReciept = xssfRow.getCell(16).getStringCellValue();
		String errorStatus = xssfRow.getCell(17).getStringCellValue();
		String errorDesc = xssfRow.getCell(18).getStringCellValue();
		List<String> errorList = new ArrayList<String>();
		System.out.println("Number of columns are correct now checking name i.e header values");
		if (Constants.ENDITEMPN.equalsIgnoreCase(endItemPn) && Constants.ENDITEMSN.equalsIgnoreCase(endItemSn)
				&& Constants.INSTALLABLEMODEL.equalsIgnoreCase(installableModel)
				&& Constants.INDICATOR.equalsIgnoreCase(indicator) && Constants.LCN.equalsIgnoreCase(lcn)
				&& Constants.Position.equalsIgnoreCase(position) && Constants.BuildItem.equalsIgnoreCase(buildItem)
				&& Constants.AssetNum.equalsIgnoreCase(assetNum) && Constants.PN.equalsIgnoreCase(partNum)
				&& Constants.PartDescription.equalsIgnoreCase(partDescription)
				&& Constants.SN.equalsIgnoreCase(serialNum) && Constants.InstalledPN.equalsIgnoreCase(installedPN)
				&& Constants.InLieuPN.equalsIgnoreCase(inLieuPn) && Constants.InstalledSN.equalsIgnoreCase(installedSN)
				&& Constants.ConditionCode.equalsIgnoreCase(conditionCode)
				&& Constants.DateofManufacturing.equalsIgnoreCase(dateOfManfacturing)
				&& Constants.DateofReceipt.equalsIgnoreCase(dateOfReciept)
				&& Constants.ErrorStatus.equalsIgnoreCase(errorStatus)
				&& Constants.ErrorDescription.equalsIgnoreCase(errorDesc)) {
			return true;
		} else {
			return false;
		}
	}

}
