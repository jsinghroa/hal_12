package com.wipro.iaf.emms.constants;

public abstract interface Constants {

	public static final String EMPTY_STRING = "";
	public static final String RECORDID = "Record_Id";
	public static final String TABLEORDER = "Table_Order";
	public static final String ASSET = "ASSET";
	public static final String ASSETDETAILS = "Asset_Details";
	public static final String ASSETMODEL = "Asset_Model";
	public static final String ASSETCFGPLAN = "Asset_Cfg_Plan";
	public static final String ASSETCFGACTUAL = "Asset_Cfg_Actual";
	public static final String ASSETMETERPLAN = "Asset_Meter_Plan";
	public static final String ASSETMETERACTUAL = "Asset_Meter_Actual";
	public static final String ASSETPM = "Asset_PM";
	public static final String LISTVIEWIMPORT = "List View Record";
	public static final String IMPORTXML = "Import XML";
	public static final String ERRORLIST = "Error List";
	public static final String FLBDETAILS = "FLB_Details";
	public static final String FLBSORTIEACCEPT = "FLB_Sortie_Accept";
	public static final String FLBPOSTFLTDETAILS = "FLB_Post_Flt_Details";
	public static final String LISTVIEW = "List View";
	public static final String ASSETCONFIG = "Asset Config";
	public static final String INSTALLABLEASSET = "Installable Asset";
	public static final String PM = "PM";
	public static final String METER = "Meter";
	public static final String FLB = "FLB";

	public static final String NOERROR = "";
	public static final String DATEFORMATERROR = "Please input date in DD-MMM-YY format";
	public static final String TIMEFORMATERROR = "Please input time in HH:MM (24HRS) format";
	public static final String TIMESTAMPFORMATERROR = "Please input timestamp in DD-MMM-YY HH:MM:SS (24HRS) format";
	public static final String TEXTLENGTHERROR = "Maximum 50 characters allowed";
	public static final String TEXTCHARERROR = "Only alphanumeric value and ./- allowed";

	public static final String INDUCTIONMANDATORYERROR = "Please enter Induction Date";
	// public static final String INDUCTIONFORMATERROR =
	// "Please enter Induction Date in DD-MMM-YY HH:MM:SS (24HRS) format";
	public static final String SIGNALOUTMANDATORYERROR = "Please enter Signal Out Date";
	public static final String SIGNALOUTGREATERTOINDUCTIONERROR = "Signal Out Date must be later than Induction Date";
	public static final String INSTALLEDINLIEUPNMANDATORYERROR = "Please enter at least one among Installed P/N and In-lieu P/N";
	public static final String INSTALLEDSNMANDATORYERROR = "Installed S/N should not be blank if Installed P/N or In-lieu P/N has value. Please fill Installed S/N";
	public static final String CONDITIONCODEMANDATORYERROR = "Please enter Condition Code";
	public static final String CONDITIONCODEVALUEERROR = "Please select the appropriate value from the look-up";
	public static final String DATEOFMANUFACTUREMANDATORYERROR = "Date of Manufacturing should not be blank if Installed P/N or In-lieu P/N has value. Please fill the Date of Manufacturing";
	public static final String SIGNALOUTGREATERTOMANUFACTORRECEIPTERROR = "Date of Manufacturing and Date of Receipt should be earlier than the Signal Out Date";

	public static final String BLANKRECEIPTDATEMESSAGE = "Date of Receipt is blank. Please provide Date of Receipt if it is different from Date of Manufacturing";

	public static final String LASTCOMPLIEDDATEMANDATORYERROR = "Last Complied Date should not be blank";
	public static final String LASTCOMPLIEDDATEGREATERTOINDUCTIONERROR ="Last Complied Date should be earlier than the Induction Date";
	public static final String LASTCOMPLIEDDATEGREATERTOSIGNALOUTERROR = "Last Complied Date should be earlier than the Signal Out Date";
	public static final String NEXTDUEDATEMANDATORYERROR = "Next Due Date should not be blank when Meter is 'Calendar Based'";
	public static final String LASTCOMPLIEDDATEGREATERTONEXTDUEDATEERROR = "Next Due Date should be later than the Last Complied Date";
	public static final String LASTCOMPLIEDVALUEMANDATORYERROR = "Last Complied Value should not be blank when Meter is 'Meter Based";
	public static final String NEXTDUEVALUEMANDATORYERROR = "Next Due Value should not be blank when Meter is 'Meter Based'";
	public static final String LASTCOMPLIEDVALUEGREATERTONEXTDUEVALUEERROR = "Next Due Value should be greater than the Last Complied Value";
	public static final String LASTCOMPLIEDVALUENULLERROR = "Last Complied Value must be empty when Meter is 'Calendar Based'";
	public static final String LASTCOMPLIEDVALUENEGATIVEERROR = "Last Complied Value should not be Negative";
	public static final String NEXTDUEVALUENEGATIVEERROR = "Next Due Value should not be Negative";
	public static final String NEXTDUEVALUENULLERROR = "Next Due Value must be empty when Meter is 'Calendar Based'";
	public static final String LASTCOMPLIEDDATEDIFFERENTFORSAMEMPM = "Last Complied date cannot be different for same Master PM records";

	public static final String CURRENTCOUNTMANDATORYERROR = "Current Count should not be blank if Initial Value and Initial Date is having value.";
	public static final String EXISTINGCOUNTGREATERTOCURRENTCOUNTERROR = "Current Count must be equal or greater than the Initial Count";

	public static final String SORTIEDATEMANDATORYERROR = "Sortie Date should not be blank";
	public static final String ETDMANDATORYERROR = "ETD(IST) (HH:MM) should not be blank";
	public static final String ETDDATEMANDATORYERROR = "ETD Date should not be blank";

	public static final String INDUCTIONDATEGREATERTOSORTIEDATEERROR = "Sortie Date should be later than the Induction Date";
	public static final String REASONMANDATORYERROR = "Reason should not be blank when Sortie Status is REJECT";
	public static final String FLIGHTDATEGREATERTOCURRENTDATEERROR = "Flight Date cannot be later than the current time";
	public static final String DEPARTURETIMEMANDATORYERROR = "Departure Time should not be blank";
	public static final String DEPARTURETIMEGREATERTOCURRENTDATETIMEERROR = "Departure Time cannot be later than the current time";
	public static final String ARRIVALTIMEGREATERTOCURRENTDATETIMEERROR = "Arrival Time cannot be later than the current time";
	public static final String ARRIVALTIMEMANDATORYERROR = "Arrival Time should not be blank";
	public static final String CONDITIONCODEA = "CAT A";
	public static final String CONDITIONCODEB = "CAT B";
	public static final String NOTNULL = "Any of inductionDate,SignalOutDate and manufacturing date cannot be empty";
	public static final String NOTNULL1 = "Any of inductionDate,SignalOutDate cannot be empty";
	
	public static final String INSTALLEDPNMANDATORY = "Please select a value in the Installed P/N";
	public static final String SAMECOMBINATIONERROR = "Please mention different Installed P/N-Installed S/N combination as same values are available in another line.";
	public static final String SAMEPNSNCOMBINATIONERROR = "Please mention different Installed P/N-Installed S/N combination as same values are available in another line.";
	public static final String SAMEPNLIEUCOMBINATIONERROR = "Please mention different Installed P/N-In Lieu P/N combination as same values are available in another line.";
	public static final String SAMESNLIEUCOMBINATIONERROR = "Please mention different Installed S/N-In Lieu P/N combination as same values are available in another line.";
	public static final String VALIDATED = "Validated";
	public static final String NOTVALIDATED = "Error";
	public static final String VALIDATEDWITHWARNING = "Validated With Warning";
	public static final String WARNING = "Warning";
	public static final String WARNINGMESSAGE = "In-lieu P/N is not available against the Build Item in Model. Please verify the P/N";
	public static final String VALIDATIONCOMPLETEDWITHERRORS = "Error";
	public static final String VALIDATIONCOMPLETED = "Validation Completed";
	
	public static final String NOTVALIDPN ="The Installed P/N is not valid Part as per the Model. Please select a Part from the Installed P/N look up. "
													+ "You can enter the actual P/N in the in-lieu P/N field if it is different from the available Installed P/N.";
	public static final String PARENTCHILDERROR ="Parent Build Item does not have asset attached to it. Please provide the details against it.";
	public static final String PARENT="NO PARENT PRESENT";
	public static final String READONLY="Asset is already attached to the Build Item. The field is read-only.";
	public static final String DECIMAL="Please enter the Integer value only";
	public static final String EXISTINGCURRENTCOUNTERROR="Current Count must be greater than the existing count of the asset";	
	public static final String PMERROR="Please enter Existing Count,Current Count for the Installed P/N-Installed S/N-Meter combination as PM compliance data is available in PM Details section";
	public static final String UOMERROR="Please enter the field in given uom format";	
	public static final String ENDITEMPN="End Item P/N";
	public static final String ENDITEMSN="End Item S/N";
	public static final String INSTALLABLEMODEL="Installable Model";
	public static final String INDICATOR="Indicator";
	public static final String LCN="LCN";
	public static final String Position="Position";
	public static final String BuildItem="Build Item";
	public static final String AssetNum="Assetnum";
	public static final String PN="P/N";
	public static final String PartDescription="Part Description";
	public static final String SN="S/N";
	public static final String InstalledPN="Installed P/N";
	public static final String InLieuPN="In-Lieu P/N";
	public static final String InstalledSN="Installed S/N";
	public static final String ConditionCode="Condition Code";
	public static final String DateofManufacturing="Date of Manufacturing";
	public static final String DateofReceipt="Date of Receipt";
	public static final String ErrorStatus="Error Status";
	public static final String ErrorDescription="Error Description";
	public static final String INLIEULENGTHERROR="Length of inLieu should be less than 80";
	public static final String SNLENGTHERROR="Length of Serial Number should be less than 50";

	//FLB status
	public static final String PENDING = "PENDING";
	public static final String ACCEPTED = "ACCEPTED";
	public static final String REJECTED = "REJECTED";
	public static final String CLOSED = "CLOSED";
	public static final String COUNTLENGTHERROR="Length of Count column should be less than 15";
	public static final String DEFAULTPOSTFLIGHTSTATUS="ACTIVE";
	public static final String ABORTPOSTFLIGHTSTATUS="ABORT";
	public static final String CLOSEDPOSTFLIGHTSTATUS="CLOSED";
	public static final String ETDDATEGREATERTOSORTIEDATEERROR="ETD Date should be later than the Sortie Date";
	
	//meter export
	public static final String METERNAME="Metername";
	public static final String INSTALLEDPARTDESCRIPTION="Installed Part Description";
	public static final String UOM="UOM";
	public static final String EXISTINGCOUNT="Existing Count";
	public static final String INSTALLATIONDATE="Installation Date";
	public static final String CURRENTCOUNT="Current Count";
	
	//pm export
	public static final String WORKTYPE="Work Type";
	public static final String MPM="MPM#";
	public static final String MPMDESCRIPTION="MPM Desc";
	public static final String PMMETER="Meter";
	public static final String FREQUENCY="Frequency";
	public static final String FREQUENCYUNIT="Frequency Unit";
	public static final String LASTCOMPLIEDDATE="Last Complied Date";
	public static final String LASTCOMPLIEDVALUE="Last Complied Value";
	public static final String NEXTDUEDATE="Next Due Date";
	public static final String NEXTDUEVALUE="Next Due Value";
	public static final String POSTFLIGHTTIMEMANDATEERROR = "Arrival and Departure time are Mandatory Fields";
	public static final String SORTIEGREATERETDERROR = "ETD DATE SHOULD BE GREATER THAN SORTIE DATE";
	public static final String ARRIVALTIMELESS = "ARRIVAL TIME SHOULD BE GREATER THAN DEPARTURE";
	
	
	
	
	
	
}
