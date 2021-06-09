package com.wipro.iaf.emms.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

	public DateConvertor() {

	}

	public String getDate(String date) {
		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				Date iDate = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
				String correctDate = new SimpleDateFormat("yyyy-MM-dd")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return " ";
			}
		}

	}

	public String getDate2(String date) {

		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				Date iDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	}

	public String getDateTime(String date) {

		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				
				Date iDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
						.parse(date);
				String correctDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}

	}

	public String getDateTime1(String date) {

		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				
				Date iDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss")
						.format(iDate);
				
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}

	}
	
	
	public String getDateTime2(String date) {

		if (null == date) {
			return "";
		}  else if (date.isEmpty()) {
			return "";
		}else {
			try {
				Date iDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	}

	public String getDateTime3(String date) {

		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				Date iDate = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
				String correctDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	}

	public String getDateTime4(String date) {

		if (null == date) {
			return "";
		}  else if (date.isEmpty()) {
			return "";
		}else {
			try {
				Date iDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss")
						.parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy")
						.format(iDate);
				System.out.println("4:" + correctDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	}
	
	public String getDateTime5(String date) {

		if (null == date) {
			return "";
		}  else if (date.isEmpty()) {
			return "";
		}else {
			try {
				Date iDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss")
						.parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				e.printStackTrace();
				return "";
			}
		}
	}
	
	public String getDateTime6(String date) {

		if (null == date) {
			return "";
		} else if (date.isEmpty()) {
			return "";
		} else {
			try {
				Date iDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
				String correctDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss")
						.format(iDate);
				return correctDate;
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				return "";
			}
		}
	}

}
