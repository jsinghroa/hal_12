package com.wipro.iaf.emms.validator;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.AssetConfigForm;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ExcelValidator {
	public String excelErrorMsg = Constants.NOERROR;
	
	
	
	public String excelValidate () {
		
		excelErrorMsg=Constants.NOERROR;
		
		
		
		
		
		if(excelErrorMsg.length() > 0) {
			excelErrorMsg += " || " + Constants.INDUCTIONMANDATORYERROR;
    	}
    	else
    		excelErrorMsg += Constants.INDUCTIONMANDATORYERROR;
	
	
	return excelErrorMsg;
	
	
	}
	
}
