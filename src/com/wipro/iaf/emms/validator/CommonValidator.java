/**
 * CommonValidator is a validation class to validate the basic data type formatting
 */

/**
 * @author Resham Das
 *
 */

package com.wipro.iaf.emms.validator;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Component;

import com.wipro.iaf.emms.constants.*;

@Component
public class CommonValidator {
	
	//final static String DATE_FORMAT = "dd-MMM-yy";
	
	public String dateValidate(String date) {
		try {
            DateFormat df = new SimpleDateFormat("dd-MMM-yy");
            df.setLenient(false);
            df.parse(date);
            return Constants.NOERROR;
        } catch (ParseException e) {
            return Constants.DATEFORMATERROR;
        }
	}
	
	public String timeValidate(String time) {
		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(time);
		if(m.matches()) return Constants.NOERROR;
		else return Constants.TIMEFORMATERROR;
	}
	
	public String timeStampValidate(String timeStamp) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            df.setLenient(false);
            df.parse(timeStamp);
            return Constants.NOERROR;
        } catch (ParseException e) {
            return Constants.TIMESTAMPFORMATERROR;
        }
	}
	

	public String timeStampValidate1(String timeStamp) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            df.setLenient(false);
            df.parse(timeStamp);
            return Constants.NOERROR;
        } catch (ParseException e) {
            return Constants.TIMESTAMPFORMATERROR;
        }
	}
	public String textValidate(String text) {
		String textErrorMsg = Constants.NOERROR;
		if(text.length() > 50) {
			textErrorMsg += Constants.TEXTLENGTHERROR;
		}
		Pattern p = Pattern.compile("[\\.\\\\\"a-zA-Z0-9-]*");
	    Matcher m = p.matcher(text);
	    if(!m.matches()) {
	    	if(textErrorMsg.length() > 0) {
	    		textErrorMsg += "\n" + Constants.TEXTCHARERROR;
	    	}
	    	else
	    		textErrorMsg += Constants.TEXTCHARERROR;
	    }
	    return textErrorMsg;
	}
	

	public boolean decimalValidate(String decimalValue) {
		String regex = "^\\d+\\.\\d+";
		String regex1="^\\d+";
		if(!decimalValue.isEmpty()){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(decimalValue);
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(decimalValue);
		if(m.matches()||m1.matches()) {	return true;}
		else {return false;}
		}
		else{
			return true;
		}
	
	}

	//controller se validate aya 
	public boolean uomValidate(String value, String uom)
	{
		String uomValue[]=value.split(":");
		int length=uomValue.length;
	if(uom.equalsIgnoreCase("hh:mm:ss")) {
		if(length!=3) {return false;}
		try {
		int h = Integer.parseInt(uomValue[0]);
		int m = Integer.parseInt(uomValue[1]);
		int s = Integer.parseInt(uomValue[2]);
		}catch(Exception e)
		{
			return false;
		}
	return true;
	}else {
		if(length!=2) {return false;}
		try {
			int h = Integer.parseInt(uomValue[0]);
			int m = Integer.parseInt(uomValue[1]);
			}catch(Exception e)
			{
				return false;
			}
		return true;
	}
		
		}
	
	public boolean fileNameMatch(String file){
		
		boolean flag = false;
		
		//flag = regex.search("IAF_HAL",file);
		flag = file.startsWith("IAF_HAL_");
		System.out.println("flag: " +flag);
		return flag;
	
	}

	public String checkCellType2003(XSSFCell cell) {

		String strCellValue = Constants.EMPTY_STRING;
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			System.out.println("INSIDE NUMERIC before="+strCellValue+":"+cell.getRawValue()+cell.getCellComment()+
					cell.getDateCellValue());
			
			cell.setCellType(Cell.CELL_TYPE_STRING);
			strCellValue = cell.getStringCellValue() + "";
			System.out.println("INSIDE NUMERIC="+strCellValue+":"+cell.getRawValue()+
					cell.getRichStringCellValue());
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			strCellValue = cell.getBooleanCellValue() + "";
			System.out.println("INSIDE NUMERIC");
			break;

		default:
			strCellValue = cell.getStringCellValue();
		}
		System.out.println("strcELLvALUE="+strCellValue);
		return strCellValue;
	}

	

	
}