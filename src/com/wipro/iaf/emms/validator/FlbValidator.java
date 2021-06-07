
package com.wipro.iaf.emms.validator;

import com.wipro.iaf.emms.constants.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;  


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FlbValidator {

	public String flbErrorMsg = Constants.NOERROR;
	public String sortieErrorMsg = Constants.NOERROR;
	CommonValidator commonValidator = new CommonValidator();
	
	String formatValidation;

	public String flbValidate (String sortieDate, String etd, String duration, 
			String sortieFlightType, String remarks, String reason, 
			String flightDate, String departureTime, String arrivalTime, 
			String postFlightType, String status, String meterReading,
			String inductionDate, String sortieStatus) {
		
		
		if(sortieDate.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.SORTIEDATEMANDATORYERROR;
	    	}
	    	else
	    		flbErrorMsg += Constants.SORTIEDATEMANDATORYERROR;
		}
		
		formatValidation = commonValidator.dateValidate(sortieDate);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		try {
			if(new SimpleDateFormat("dd-MMM-yy").parse(inductionDate)
					.compareTo(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(sortieDate))>=0) {
				if(flbErrorMsg.length() > 0) {
					flbErrorMsg += "\n" + Constants.INDUCTIONDATEGREATERTOSORTIEDATEERROR;
				}
				else
					flbErrorMsg += Constants.INDUCTIONDATEGREATERTOSORTIEDATEERROR;
			}
		} catch (ParseException e) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.TIMESTAMPFORMATERROR;
			}
			else
				flbErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}
		
		if(etd.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.ETDMANDATORYERROR;
	    	}
	    	else
	    		flbErrorMsg += Constants.ETDMANDATORYERROR;
		}
		
		formatValidation = commonValidator.timeValidate(etd);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		//changes
		
		try {
			if(new SimpleDateFormat("dd-MMM-yy").parse(sortieDate)
					.compareTo(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(etd))>=0) {
				if(flbErrorMsg.length() > 0) {
					flbErrorMsg += "\n" + Constants.ETDDATEGREATERTOSORTIEDATEERROR;
				}
				else
					flbErrorMsg += Constants.ETDDATEGREATERTOSORTIEDATEERROR;
			}
		} catch (ParseException e) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.TIMESTAMPFORMATERROR;
			}
			else
				flbErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}
		
		formatValidation = commonValidator.timeValidate(duration);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		formatValidation = commonValidator.textValidate(remarks);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		if(sortieStatus.equalsIgnoreCase("REJECT") && reason.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.REASONMANDATORYERROR;
	    	}
	    	else
	    		flbErrorMsg += Constants.REASONMANDATORYERROR;
		}
		
		formatValidation = commonValidator.textValidate(reason);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		formatValidation = commonValidator.dateValidate(flightDate);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		try {
			if(new SimpleDateFormat("dd-MMM-yy").parse(flightDate)
					.compareTo(new Date())>0) {
				if(flbErrorMsg.length() > 0) {
					flbErrorMsg += "\n" + Constants.FLIGHTDATEGREATERTOCURRENTDATEERROR;
				}
				else
					flbErrorMsg += Constants.FLIGHTDATEGREATERTOCURRENTDATEERROR;
			}
		} catch (ParseException e) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.DATEFORMATERROR;
			}
			else
				flbErrorMsg += Constants.DATEFORMATERROR;
		}
		
		if(departureTime.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.DEPARTURETIMEMANDATORYERROR;
	    	}
	    	else
	    		flbErrorMsg += Constants.DEPARTURETIMEMANDATORYERROR;
		}
		
		formatValidation = commonValidator.timeValidate(departureTime);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		String departureTimeStamp =  flightDate + " " + departureTime;
		String arrivalTimeStamp = flightDate + " " + arrivalTime;
		
		try {
			if(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(departureTimeStamp)
					.compareTo(new Date())>0) {
				if(flbErrorMsg.length() > 0) {
					flbErrorMsg += "\n" + Constants.DEPARTURETIMEGREATERTOCURRENTDATETIMEERROR;
				}
				else
					flbErrorMsg += Constants.DEPARTURETIMEGREATERTOCURRENTDATETIMEERROR;
			}
		} catch (ParseException e) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.TIMESTAMPFORMATERROR;
			}
			else
				flbErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}
		
		if(arrivalTime.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.ARRIVALTIMEMANDATORYERROR;
	    	}
	    	else
	    		flbErrorMsg += Constants.ARRIVALTIMEMANDATORYERROR;
		}
		
		formatValidation = commonValidator.timeValidate(arrivalTime);
		if(!formatValidation.isEmpty()) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + formatValidation;
	    	}
	    	else
	    		flbErrorMsg += formatValidation;
		}
		
		try {
			if(new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(arrivalTimeStamp)
					.compareTo(new Date())>0) {
				if(flbErrorMsg.length() > 0) {
					flbErrorMsg += "\n" + Constants.ARRIVALTIMEGREATERTOCURRENTDATETIMEERROR;
				}
				else
					flbErrorMsg += Constants.ARRIVALTIMEGREATERTOCURRENTDATETIMEERROR;
			}
		} catch (ParseException e) {
			if(flbErrorMsg.length() > 0) {
				flbErrorMsg += "\n" + Constants.TIMESTAMPFORMATERROR;
			}
			else
				flbErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}
		
		
		
		return flbErrorMsg;
	}
	
	
	public String validateSortie(String sortieDate,String etd,String etdDate)
	{
		System.out.println("sortieDate="+sortieDate+"etd="+etd+"etdDate="+etdDate);
		sortieErrorMsg = Constants.NOERROR;
		if(sortieDate.isEmpty()) {
			if(sortieErrorMsg.length() > 0) {
				sortieErrorMsg += " || " + Constants.SORTIEDATEMANDATORYERROR;
	    	}
	    	else
	    		sortieErrorMsg += Constants.SORTIEDATEMANDATORYERROR;
		}
		if(etd.isEmpty()) {
			if(sortieErrorMsg.length() > 0) {
				sortieErrorMsg += " || " + Constants.ETDMANDATORYERROR;
	    	}
	    	else
	    		sortieErrorMsg += Constants.ETDMANDATORYERROR;
		}
		
		if(etdDate.isEmpty()) {
			if(sortieErrorMsg.length() > 0) {
				sortieErrorMsg +=  " || " +  Constants.ETDDATEMANDATORYERROR;
	    	}
	    	else
	    		sortieErrorMsg += Constants.ETDDATEMANDATORYERROR;
		}
		//etd>sortie
		if(null!=etdDate&&null!=sortieDate&&!etdDate.equals("")&&!sortieDate.equals(""))
		{
		try {
			if (new SimpleDateFormat("dd-MMM-yy").parse(sortieDate)
					.compareTo(
							new SimpleDateFormat("dd-MMM-yy")
									.parse(etdDate)) >= 0) {
				if (sortieErrorMsg.length() > 0) {
					sortieErrorMsg += " || "
							+ Constants.SORTIEGREATERETDERROR;
				} else
					sortieErrorMsg += Constants.SORTIEGREATERETDERROR;
			}
		} catch (ParseException e) {
			System.out.println("exception catch");
			if (sortieErrorMsg.length() > 0) {
				sortieErrorMsg += " || " + Constants.TIMESTAMPFORMATERROR;
			} else
				sortieErrorMsg += Constants.TIMESTAMPFORMATERROR;
		}
		}
		
		
		
		
		return sortieErrorMsg;
	}
	
	
	
}
