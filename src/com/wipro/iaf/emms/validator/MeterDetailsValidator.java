/**
 * MeterDetailsValidator is a validation class to validate user input on 'Meter Details' screen
 */

/**
 * @author Resham Das
 *
 */

package com.wipro.iaf.emms.validator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.wipro.iaf.emms.constants.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MeterDetailsValidator {

	public String meterDetailsErrorMsg;
	CommonValidator commonValidator = new CommonValidator();

	String formatValidation;

	public String meterDetailsValidate(String existingCount, String currentCount, String ExAssetCurrentCount,
			String exInstalledPn, String exInstalledSn, String installedPn, String installedSn,
			String lastCompiledValue, String lastCompiledDate, String uom) {
		System.out.println(existingCount + ":" + currentCount);

		System.out.println(ExAssetCurrentCount + ":" + exInstalledPn + ":" + exInstalledSn + ":" + installedPn + ":"
				+ installedSn + ":" + lastCompiledValue + ":" + lastCompiledDate + ":" + uom);
		meterDetailsErrorMsg = Constants.NOERROR;
		System.out.println(existingCount.length() + ":" + currentCount.length());
		if (existingCount.length() >= 15 || currentCount.length() >= 15) {
			System.out.println("inside length Check");
			if (meterDetailsErrorMsg.length() > 0) {
				meterDetailsErrorMsg += "\n" + Constants.COUNTLENGTHERROR;
			} else
				meterDetailsErrorMsg += Constants.COUNTLENGTHERROR;
		}

		if (uom.equalsIgnoreCase("hh:mm:ss")) {

			if (!existingCount.isEmpty() && !commonValidator.uomValidate(existingCount)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.UOMERROR;
				} else
					meterDetailsErrorMsg += Constants.UOMERROR;

			} else if (!currentCount.isEmpty() && !commonValidator.uomValidate(currentCount)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.UOMERROR;
				} else
					meterDetailsErrorMsg += Constants.UOMERROR;

			}

			else {
				System.out.println("UOM IS CORRECT");

				existingCount = getDecimalValue(existingCount);
				currentCount = getDecimalValue(currentCount);

				System.out.println("existing=" + existingCount + ":" + currentCount + ":" + ExAssetCurrentCount);

				// time stamp format validate
				// 2.Current Count must be equal or greater than the Existing Count.
				// Validation logic for UOM data type pending
				try {
				if (!currentCount.isEmpty()
						&& new BigInteger(currentCount).doubleValue() < new BigInteger(existingCount).doubleValue()) {
					System.out.println("after start");
					if (meterDetailsErrorMsg.length() > 0) {
						meterDetailsErrorMsg += "\n" + Constants.EXISTINGCOUNTGREATERTOCURRENTCOUNTERROR;
					} else
						meterDetailsErrorMsg += Constants.EXISTINGCOUNTGREATERTOCURRENTCOUNTERROR;
				}
				}catch(Exception e) {System.out.println(e.getMessage());}
				
				// 3.In case of existing assets that have been re-installed,
				// Current Count must be greater than the existing count* of the
				// asset.

				if (installedPn.equals(exInstalledPn) && installedSn.equals(exInstalledSn)) {
					try {
					if (!ExAssetCurrentCount.isEmpty()) {

						if (new BigInteger(currentCount).doubleValue() < new BigInteger(ExAssetCurrentCount)
								.doubleValue()) {

							if (meterDetailsErrorMsg.length() > 0) {
								meterDetailsErrorMsg += "\n" + Constants.EXISTINGCURRENTCOUNTERROR;
							} else
								meterDetailsErrorMsg += Constants.EXISTINGCURRENTCOUNTERROR;

						}
					}
					}catch(Exception e) {System.out.println(e.getMessage());}

				}
			
				// 4.Current Count should be mandatory if Initial Value and
				// Initial Date is having value.
				if (!existingCount.isEmpty()) {

					if (currentCount.isEmpty()) {
						if (meterDetailsErrorMsg.length() > 0) {
							meterDetailsErrorMsg += "\n" + Constants.CURRENTCOUNTMANDATORYERROR;
						} else
							meterDetailsErrorMsg += Constants.CURRENTCOUNTMANDATORYERROR;
					}
				}
				// 5.If Last Complied Date and Last Complied Value are filled up
				// against the MPM for a meter,
				// then the corresponding meter should have Installed Count,
				// Installed Date and Current Count in the Meter Details tab
				// for the respective Installed P/N-Installed S/N combination.

				if (null != lastCompiledDate && null != lastCompiledValue) {
					
					if (lastCompiledDate.length() > 0 && lastCompiledValue.length() > 0) {
						if (existingCount.isEmpty() || currentCount.isEmpty()) {
							if (meterDetailsErrorMsg.length() > 0) {
								meterDetailsErrorMsg += "\n" + Constants.PMERROR;
							} else
								meterDetailsErrorMsg += Constants.PMERROR;
						}
					}

				}
				System.out.println("else end");

			}
			System.out.println("main if end");
		} else {

			if (!existingCount.isEmpty() && !commonValidator.decimalValidate(existingCount)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.DECIMAL;
				} else
					meterDetailsErrorMsg += Constants.DECIMAL;

			} else if (!currentCount.isEmpty() && !commonValidator.decimalValidate(currentCount)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.DECIMAL;
				} else
					meterDetailsErrorMsg += Constants.DECIMAL;

			} else

			{

				// time stamp format validate
				// 2.Current Count must be equal or greater than the Existing Count.
				// Validation logic for UOM data type pending
				try {
				if (!currentCount.isEmpty()
						&& new BigInteger(currentCount).doubleValue() < new BigInteger(existingCount).doubleValue()) {
					System.out.println("after start");
					if (meterDetailsErrorMsg.length() > 0) {
						meterDetailsErrorMsg += "\n" + Constants.EXISTINGCOUNTGREATERTOCURRENTCOUNTERROR;
					} else
						meterDetailsErrorMsg += Constants.EXISTINGCOUNTGREATERTOCURRENTCOUNTERROR;
				}
				}catch(Exception e) {System.out.println(e.getMessage());}
				
				// 3.In case of existing assets that have been re-installed,
				// Current Count must be greater than the existing count* of the
				// asset.
				if (installedPn.equals(exInstalledPn) && installedSn.equals(exInstalledSn)) {
					try {
					if (!ExAssetCurrentCount.isEmpty()) {
						if (new BigInteger(currentCount).doubleValue() < new BigInteger(ExAssetCurrentCount)
								.doubleValue()) {
							if (meterDetailsErrorMsg.length() > 0) {
								meterDetailsErrorMsg += "\n" + Constants.EXISTINGCURRENTCOUNTERROR;
							} else
								meterDetailsErrorMsg += Constants.EXISTINGCURRENTCOUNTERROR;

						}
					}
					}catch(Exception e) {System.out.println(e.getMessage());}

				}

				// 4.Current Count should be mandatory if Initial Value and
				// Initial Date is having value.
				if (!existingCount.isEmpty()) {

					if (currentCount.isEmpty()) {
						if (meterDetailsErrorMsg.length() > 0) {
							meterDetailsErrorMsg += "\n" + Constants.CURRENTCOUNTMANDATORYERROR;
						} else
							meterDetailsErrorMsg += Constants.CURRENTCOUNTMANDATORYERROR;
					}
				}

				// 5.If Last Complied Date and Last Complied Value are filled up
				// against the MPM for a meter,
				// then the corresponding meter should have Installed Count,
				// Installed Date and Current Count in the Meter Details tab
				// for the respective Installed P/N-Installed S/N combination.

				if (null != lastCompiledDate && null != lastCompiledValue) {
					
					if (lastCompiledDate.length() > 0 && lastCompiledValue.length() > 0) {
						if (existingCount.isEmpty() || currentCount.isEmpty()) {
							if (meterDetailsErrorMsg.length() > 0) {
								meterDetailsErrorMsg += "\n" + Constants.PMERROR;
							} else
								meterDetailsErrorMsg += Constants.PMERROR;
						}
					}

				}

				formatValidation = commonValidator.meterValidate(currentCount);
				if (!formatValidation.isEmpty()) {
					if (meterDetailsErrorMsg.length() > 0) {
						meterDetailsErrorMsg += "\n" + formatValidation;
					} else
						meterDetailsErrorMsg += formatValidation;
				}

				formatValidation = commonValidator.meterValidate(existingCount);
				if (!formatValidation.isEmpty()) {
					if (meterDetailsErrorMsg.length() > 0) {
						meterDetailsErrorMsg += "\n" + formatValidation;
					} else
						meterDetailsErrorMsg += formatValidation;
				}
			}

		}

		return meterDetailsErrorMsg;

	}

	public String getDecimalValue(String value) {
		// value->hh mm ss
		int h = Integer.parseInt(value.substring(0, 2));
		int m = Integer.parseInt(value.substring(3, 5));
		int s = Integer.parseInt(value.substring(6, 8));
		Long cal = (long) (h * 3600 + m * 60 + s);
		return String.valueOf(cal);

	}

	public String getUomValue(String value) {
		System.out.println("VALUE=" + value);
		// String a[] = value.split(".0");

		String decimalValue = "";
		for (int i = 0; i < value.length(); i++) {
			if (value.charAt(i) == '.') {
				break;
			}
			decimalValue = decimalValue + value.charAt(i);

		}

		Long l = Long.parseLong(decimalValue);

		System.out.println(l);

		int s = (int) (l % 60);
		int h = (int) (l / 60);
		int m = h % 60;
		h = h / 60;
		String hh = String.valueOf(h);
		String mm = String.valueOf(m);
		String ss = String.valueOf(s);

		if (hh.length() < 2) {
			System.out.println("inisde");
			hh = "0" + hh;
		}
		if (mm.length() < 2) {

			mm = "0" + mm;
		}
		if (ss.length() < 2) {

			ss = "0" + ss;
		}

		System.out.println(hh + ":" + mm + ":" + ss);
		String uom = hh + ":" + mm + ":" + ss;

		System.out.println("AFTERVALUE=" + uom);
		return uom;
	}

	public boolean validateHeader(XSSFRow xssfRow) {
		System.out.println("Checking number of columns");
		if (xssfRow.getLastCellNum() != 9) {
			System.out.println("inside");
			return false;
		}

		String installedPN = xssfRow.getCell(0).getStringCellValue();
		String installedPartDescription = xssfRow.getCell(1).getStringCellValue();
		String installedSN = xssfRow.getCell(2).getStringCellValue();
		String meterName = xssfRow.getCell(3).getStringCellValue();
		String uom = xssfRow.getCell(4).getStringCellValue();
		String existingCount = xssfRow.getCell(5).getStringCellValue();
		String currentCount = xssfRow.getCell(6).getStringCellValue();
		String errorStatus = xssfRow.getCell(7).getStringCellValue();
		String errorDesc = xssfRow.getCell(8).getStringCellValue();
		List<String> errorList = new ArrayList<String>();
		System.out.println("Number of columns are correct now checking name i.e header values");
		if (Constants.INSTALLEDPARTDESCRIPTION.equalsIgnoreCase(installedPartDescription)
				&& Constants.InstalledPN.equalsIgnoreCase(installedPN)
				&& Constants.InstalledSN.equalsIgnoreCase(installedSN)
				&& Constants.METERNAME.equalsIgnoreCase(meterName) && Constants.UOM.equalsIgnoreCase(uom)
				&& Constants.EXISTINGCOUNT.equalsIgnoreCase(existingCount)
				&& Constants.CURRENTCOUNT.equalsIgnoreCase(currentCount)
				&& Constants.ErrorStatus.equalsIgnoreCase(errorStatus)
				&& Constants.ErrorDescription.equalsIgnoreCase(errorDesc)) {
			return true;
		} else {
			return false;
		}
	}

	public String checkCellType2003(XSSFCell cell, String uom) {

		String strCellValue = Constants.EMPTY_STRING;

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			System.out.println(
					"hello=" + cell.getNumericCellValue() + cell.getRawValue() + cell.getDateCellValue().getHours());
			if (uom.equalsIgnoreCase("hh:mm:ss")) {
				Date date = cell.getDateCellValue();
				strCellValue = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
			} else {
				strCellValue = cell.getNumericCellValue() + "";
			}
			System.out.println(strCellValue);

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
