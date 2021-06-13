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
		
		System.out.println(existingCount + ":" + currentCount+":"+ExAssetCurrentCount + ":" + exInstalledPn + ":" + exInstalledSn + ":" + installedPn + ":"
				+ installedSn + ":" + lastCompiledValue + ":" + lastCompiledDate + ":" + uom);
		meterDetailsErrorMsg = Constants.NOERROR;
		
		if (existingCount.length() >= 15 || currentCount.length() >= 15) {
			System.out.println("inside length Check");
			if (meterDetailsErrorMsg.length() > 0) {
				meterDetailsErrorMsg += "\n" + Constants.COUNTLENGTHERROR;
			} else
				meterDetailsErrorMsg += Constants.COUNTLENGTHERROR;
		}

		if (uom.equalsIgnoreCase("hh:mm:ss")||uom.equalsIgnoreCase("hh:mm")) {

			if (!existingCount.isEmpty() && !commonValidator.uomValidate(existingCount,uom)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.UOMERROR;
				} else
					meterDetailsErrorMsg += Constants.UOMERROR;

			} else if (!currentCount.isEmpty() && !commonValidator.uomValidate(currentCount,uom)) {
				if (meterDetailsErrorMsg.length() > 0) {
					meterDetailsErrorMsg += "\n" + Constants.UOMERROR;
				} else
					meterDetailsErrorMsg += Constants.UOMERROR;

			}

			else {
				System.out.println("UOM IS CORRECT");

				existingCount = getDecimalValue(existingCount,uom);
				currentCount = getDecimalValue(currentCount,uom);

				System.out.println("existing=" + existingCount + ":" + currentCount + ":" + ExAssetCurrentCount);

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
			
				if (!existingCount.isEmpty()) {

					if (currentCount.isEmpty()) {
						if (meterDetailsErrorMsg.length() > 0) {
							meterDetailsErrorMsg += "\n" + Constants.CURRENTCOUNTMANDATORYERROR;
						} else
							meterDetailsErrorMsg += Constants.CURRENTCOUNTMANDATORYERROR;
					}
				}
				

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
			
			}
			}else {
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

				if (!existingCount.isEmpty()) {

					if (currentCount.isEmpty()) {
						if (meterDetailsErrorMsg.length() > 0) {
							meterDetailsErrorMsg += "\n" + Constants.CURRENTCOUNTMANDATORYERROR;
						} else
							meterDetailsErrorMsg += Constants.CURRENTCOUNTMANDATORYERROR;
					}
				}

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

			
			}

		}

		return meterDetailsErrorMsg;

	}

	public String getDecimalValue(String value, String uom) {
		// value->hh mm ss
		String uomValue[]=value.split(":");
		try {
		if(uom.equalsIgnoreCase("hh:mm:ss")) {
			int h = Integer.parseInt(uomValue[0]);
			int m = Integer.parseInt(uomValue[1]);
			int s = Integer.parseInt(uomValue[2]);
		Long cal = (long) (h * 3600 + m * 60 + s);
		return String.valueOf(cal);
		}else {
			int h = Integer.parseInt(uomValue[0]);
			int m = Integer.parseInt(uomValue[1]);
			Long cal = (long) (h * 3600 + m * 60);
			return String.valueOf(cal);
		}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "0";
		}
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
