package com.wipro.iaf.emms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.util.Converter;
import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.FlbDetailForm;
import com.wipro.iaf.emms.form.FlbMeterDetailsForm;
import com.wipro.iaf.emms.form.FlbPostFlightDataForm;
import com.wipro.iaf.emms.form.FlbSortieArForm;
import com.wipro.iaf.emms.service.HalService;
import com.wipro.iaf.emms.validator.AssetConfigValidator;
import com.wipro.iaf.emms.validator.DateConvertor;
import com.wipro.iaf.emms.validator.FlbValidator;

@Controller
@RequestMapping(value = { "/FLB" }, method = RequestMethod.GET)
public class FLBController {

	/*
	 * @Autowired List<FlbDetailForm> flbList;
	 */

	private static List<String> statuses = new ArrayList<String>() {

		private static final long serialVersionUID = 1L;

		{
			add(Constants.DEFAULTPOSTFLIGHTSTATUS);
			add(Constants.ABORTPOSTFLIGHTSTATUS);
			add(Constants.CLOSEDPOSTFLIGHTSTATUS);

		}
	};

	private static DateConvertor convertor = new DateConvertor();

	@Autowired
	List<FlbMeterDetailsForm> flbMeterDetailsList;

	@Autowired
	List<FlbPostFlightDataForm> flbPostFlightDataList;

	@Autowired
	List<FlbSortieArForm> flbSortieArList;

	@Autowired
	InstallableAssetController installController;

	@Autowired
	MeterController meterController;

	@Autowired
	AssetController assetController;

	@Autowired
	PMController pmController;

	@Autowired
	HalService halService;

	@Autowired
	private FlbValidator flbValidator;

	
	@Autowired
	private EmmsDataForm emmsDataForm;

	@RequestMapping(value = { "/saveFlb1" }, method = RequestMethod.GET)
	public List<FlbSortieArForm> fetchFLBSortieArDetails(String recordID,EmmsDataForm emmsDataForm) {
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		this.emmsDataForm = emmsDataForm;
		
		flbSortieArList = halService.fetchFlbSortieArDetails(recordID);
		return flbSortieArList;
	}

	@RequestMapping(value = { "/saveFlb2" }, method = RequestMethod.GET)
	public List<FlbPostFlightDataForm> fetchFLBPostFlightDetails(
			String recordID, EmmsDataForm emmsDataForm) {
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		this.emmsDataForm = emmsDataForm;
		
		flbPostFlightDataList = halService.fetchFLBPostFlightDetails(recordID);
		System.out
				.println("PostFlightList=" + flbPostFlightDataList.toString());
		return flbPostFlightDataList;
	}

	@RequestMapping(value = { "/saveFlb3" }, method = RequestMethod.GET)
	public List<FlbMeterDetailsForm> fetchFLBMeterDetails(String recordID, EmmsDataForm emmsDataFor) {
		emmsDataForm.setRecordId(recordID);
		emmsDataForm.setSelectedRecordId(recordID);
		this.emmsDataForm = emmsDataForm;
		
		flbMeterDetailsList = halService.fetchFLBMeterDetails(recordID);
		return flbMeterDetailsList;
	}

	@RequestMapping(value = "/saveFlb", method = RequestMethod.POST)
	public String submit(

	@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm,
			BindingResult bindingResult, ModelMap model,
			@RequestParam(required = false) String addPostFlightRow,
			@RequestParam(required = false) String addSortieRow,
			@RequestParam(required = false) String action,
			@RequestParam String linkSelected) {
		System.out.println("link: ");
		System.out.println("linkSelected:" + linkSelected);
		if (linkSelected.equals(Constants.LISTVIEW)) {
			System.out.println(Constants.LISTVIEW);
			List<EmmsDataForm> emmsDataFormList = halService
					.getEmmsDataOnView();
			System.out.println("link1: ");
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
			System.out.println("link2: ");
		} else {
			if (linkSelected.equals(Constants.ASSETCONFIG)) {
				System.out.println(Constants.ASSETCONFIG);
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());

					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setInstallableFormList(installController
							.fetchInstallableDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setPmDetailFormList(pmController
							.fetchPMDetails(emmsDataForm.getSelectedRecordId(),
									emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {
				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				System.out.println(Constants.METER);

				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());

					emmsDataForm.setMeterFormList(meterController
							.fetchMeterDetails(
									emmsDataForm.getSelectedRecordId(),
									emmsDataForm));
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/meterDetails.jsp");

			} else if (linkSelected.equals(Constants.FLB)) {

				emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				this.emmsDataForm.setSelectedRecordId(emmsDataForm.getRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {

					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setFlbMeterDetailsFormList(this
							.fetchFLBMeterDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbPostFlightDataFormList(this
							.fetchFLBPostFlightDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbSortieArFormList(this
							.fetchFLBSortieArDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			}

			else if (action != null && action.equals("Submit")) {
				System.out.println("Inside flb submit");

				List<FlbDetailForm> FLBForm = emmsDataForm.getFlbFormList();

				if (null != FLBForm && FLBForm.size() > 0) {
					System.out.println(FLBForm.size());
					for (FlbDetailForm flbdetail : FLBForm) {
						System.out.println(flbdetail.toString());
						// int i = halService.update(emmsDataForm, flbdetail);
					}
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			} else if (addSortieRow != null && addSortieRow.equals("New Row")) {
				System.out.println("Inside add new sortie row");
				if (emmsDataForm.getFlbSortieArFormList() == null){
					System.out.println("eMMS data form was empty");
					emmsDataForm.setFlbSortieArFormList(new ArrayList<FlbSortieArForm>());
				}
				
				System.out.println("New row created");
				int index = emmsDataForm.getFlbSortieArFormList().size() + 1;
				FlbSortieArForm newSortieRow = new FlbSortieArForm();
				
				newSortieRow.setRecordId(emmsDataForm.getRecordId());
				newSortieRow.setFlbNum(emmsDataForm.getFlbNum());
				newSortieRow.setRecordRowId(emmsDataForm.getRecordId() + "_" + index);
				// Default Status is pending for new sortie
				newSortieRow.setSortieStatus(Constants.PENDING);
				
				// Auto generated Sortie number
				newSortieRow.setSortieNo(String.valueOf(emmsDataForm.getFlbSortieArFormList().size() + 1));
				
				// Status Date should be current date
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm");
				Date date = new Date();
				newSortieRow.setStatusDate(dateFormatter.format(date));
				try{
					System.out.println("Adding new row to Sortie list");
					halService.insertSortieArRow(newSortieRow);
				}
				catch(Exception e){
					System.out.println("Some error occured while adding new sortie row in DB\n" + e.getMessage());
				}
				
				// Adding new row
				emmsDataForm.getFlbSortieArFormList()
						.add(newSortieRow);
				this.flbSortieArList = emmsDataForm.getFlbSortieArFormList();
				System.out.println("New row added to the Sortie form");
				
				if(null != emmsDataForm
						.getFlbPostFlightDataFormList() && emmsDataForm
						.getFlbPostFlightDataFormList().size() > 0){
				int k=0;
				for (FlbPostFlightDataForm postFlightRow : emmsDataForm
						.getFlbPostFlightDataFormList()) {
					
					postFlightRow.setCreationDate((this.flbPostFlightDataList
							.get(k).getCreationDate()));
					postFlightRow.setStatuses(this.flbPostFlightDataList.get(k)
							.getStatuses());
					postFlightRow.setFlightTypes(this.flbPostFlightDataList
							.get(k).getFlightTypes());
					this.flbPostFlightDataList.get(k).getListSortieNumber().add(newSortieRow.getSortieNo());
					postFlightRow.setListSortieNumber(this.flbPostFlightDataList.get(k).getListSortieNumber());
					k++;
				}
				}

				
				
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");
			} else if (addPostFlightRow != null
					&& addPostFlightRow.equals("New Row")) {
				List<String> flightTypes=new ArrayList<String>();
				List<String> sortieNumbers=new ArrayList<String>();
				System.out.println("Inside add post flight");
				int flightNumber = 0;
				Date d = new Date();
				System.out.println(d.getDate() + d.getMonth() + d.getYear());
				String currentDate = String.valueOf(d.getDate() + ":"
						+ d.getMonth() + ":" + d.getYear());
				System.out.println("CurrentDate=" + currentDate);
				// Setting Lookup
				int i = 0;
				
				if(null != emmsDataForm
						.getFlbPostFlightDataFormList() && emmsDataForm
						.getFlbPostFlightDataFormList().size() > 0){
				
				flightTypes=this.flbPostFlightDataList.get(0).getFlightTypes();
				sortieNumbers=this.flbPostFlightDataList.get(0).getListSortieNumber();
				for (FlbPostFlightDataForm postFlightRow : emmsDataForm
						.getFlbPostFlightDataFormList()) {
					postFlightRow.setCreationDate((this.flbPostFlightDataList
							.get(i).getCreationDate()));
					postFlightRow.setStatuses(this.flbPostFlightDataList.get(i)
							.getStatuses());
					postFlightRow.setFlightTypes(this.flbPostFlightDataList
							.get(i).getFlightTypes());
					postFlightRow.setListSortieNumber(this.flbPostFlightDataList.get(i).getListSortieNumber());
					i++;
					System.out.println(postFlightRow.getCreationDate() + ":"
							+ currentDate);
					if (null != postFlightRow.getCreationDate()
							&& postFlightRow.getCreationDate().equals(
									currentDate)) {
						flightNumber++;
					}

				}
				}else {
					emmsDataForm.setFlbPostFlightDataFormList(new ArrayList<FlbPostFlightDataForm>());
				}

				// Adding new Row
				FlbPostFlightDataForm postFlightDataForm = new FlbPostFlightDataForm();
				postFlightDataForm.setRecordId(emmsDataForm.getRecordId());
				postFlightDataForm.setRecordRowId(postFlightDataForm
						.getRecordId() + "_" + i);
				postFlightDataForm.setStatuses(statuses);
				System.out.println(flightTypes+":"+sortieNumbers);
				postFlightDataForm.setListSortieNumber(sortieNumbers);
				postFlightDataForm.setFlightTypes(flightTypes);
				postFlightDataForm.setStatus(Constants.DEFAULTPOSTFLIGHTSTATUS);
				postFlightDataForm.setCreationDate(currentDate);
				postFlightDataForm.setFlbNo(emmsDataForm.getFlbNum());

				// Setting flight number sequential
				postFlightDataForm.setFlightNumber(String
						.valueOf(flightNumber + 1));

				// Inserting new row to database.
				try {
					System.out.println("inside update");
					halService.insertPostFlight(postFlightDataForm);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				emmsDataForm.getFlbPostFlightDataFormList().add(
						postFlightDataForm);

				this.flbPostFlightDataList = emmsDataForm
						.getFlbPostFlightDataFormList();
				System.out.println(this.flbPostFlightDataList.toString());
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");
			} else if (action != null && action.equals("Save")) {
				System.out.println("Inside  Save");
				DateConvertor convertor = new DateConvertor();
				
				List<FlbSortieArForm> flbSortieList = emmsDataForm.getFlbSortieArFormList();
				if (null != flbSortieList && flbSortieList.size() > 0) {int i = 0;
					for (FlbSortieArForm flbSortieArRow: flbSortieList) {
						
						flbSortieArRow.setRecordRowId(this.flbSortieArList.get(i).getRecordRowId());
						flbSortieArRow.setRecordId(this.flbSortieArList.get(i).getRecordId());
						
						
						System.out.println("SortieRow="+flbSortieArRow.toString());
						
						String validateSortie=flbValidator.validateSortie(flbSortieArRow.getSortieDate(),flbSortieArRow.getETD(),
								flbSortieArRow.getEtdDate());
						System.out.println("validateSortie="+validateSortie);
						if(validateSortie.equals(""))
						{
							flbSortieArRow.setError(Constants.VALIDATED);
						}
						else
						{
							flbSortieArRow.setError(Constants.NOTVALIDATED);
							flbSortieArRow.setErrordesc(validateSortie);
								
						}
						// convert date in format yyyy-mm-dd if date is not null or empty string
						if(flbSortieArRow.getSortieDate() != null && flbSortieArRow.getSortieDate() != "") {
							flbSortieArRow.setSortieDate(convertor.getDate(flbSortieArRow.getSortieDate()));
						}else{
							flbSortieArRow.setSortieDate(null);
						}	
						// convert date in format yyyy-mm-dd if date is not null or empty string
						if(flbSortieArRow.getEtdDate() != null && flbSortieArRow.getEtdDate() != "") {
							flbSortieArRow.setEtdDate(convertor.getDate(flbSortieArRow.getEtdDate()));
						}else{
							flbSortieArRow.setEtdDate(null);
						}
					try{
							System.out.println("Updating flbSortieAR");
							halService.updateSortieArData(flbSortieArRow);
						}
						catch(Exception e){
							System.out.println("Some error occured while adding new sortie row in DB\n" + e.getMessage());
						}
					flbSortieArRow.setSortieDate(convertor.getDate2(flbSortieArRow.getSortieDate()));
					flbSortieArRow.setEtdDate(convertor.getDate2(flbSortieArRow.getEtdDate()));
					
						i++;
				}
				}
				List<FlbPostFlightDataForm> postFlightList = emmsDataForm.getFlbPostFlightDataFormList();
				if(null != postFlightList && postFlightList.size() > 0){
					int i = 0;
					for (FlbPostFlightDataForm postFlightRow : postFlightList) {
						System.out.println("--PostFlightRow="+postFlightRow);
						postFlightRow.setStatuses(this.flbPostFlightDataList
								.get(i).getStatuses());
						postFlightRow
								.setCreationDate((this.flbPostFlightDataList
										.get(i).getCreationDate()));
						postFlightRow.setRecordRowId(this.flbPostFlightDataList
								.get(i).getRecordRowId());
						postFlightRow.setFlightTypes(this.flbPostFlightDataList
								.get(i).getFlightTypes());
						postFlightRow.setListSortieNumber(this.flbPostFlightDataList.get(i).getListSortieNumber());
						
						postFlightRow.setFlightHours(getHours(
								postFlightRow.getArrivalTime(),
								postFlightRow.getDepartureTime()));
						postFlightRow.setFlightDate(convertor.getDateTime3(postFlightRow.getFlightDate()));
						System.out.println("--PostFlightRow="+postFlightRow.toString());
						if(postFlightRow.getArrivalTime().isEmpty()||postFlightRow.getDepartureTime().isEmpty()
								)
						{
							postFlightRow.setErrorDesc(Constants.POSTFLIGHTTIMEMANDATEERROR);
							postFlightRow.setError(Constants.NOTVALIDATED);
						}
						else
						{
							postFlightRow.setError(Constants.VALIDATED);
						try {
							halService.updatePostFlight(postFlightRow);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						}
						if(postFlightRow.getStatus().equalsIgnoreCase(Constants.CLOSEDPOSTFLIGHTSTATUS)&& 
								!postFlightRow.getSortieNumber().isEmpty())
						{   
							System.out.println("Inside SortieStatus Update");
							halService.updateSortieStatus(postFlightRow);
							
							
						}
						i++;
						postFlightRow.setFlightDate(convertor.getDateTime2(postFlightRow.getFlightDate()));
					}
				}
				System.out.println("After statusUpdate="+this.emmsDataForm
						.getSelectedRecordId()+":"+this.emmsDataForm.toString());
				emmsDataForm.setFlbSortieArFormList(this
						.fetchFLBSortieArDetails(this.emmsDataForm
								.getSelectedRecordId(),emmsDataForm));
				

				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			}
			
			else if(action != null && action.equals("Submit")) {
				
				
				
			}
		}
		return "basic";
	}

	private String getHours(String arrivalTime, String departureTime) {

		if (arrivalTime.isEmpty() || departureTime.isEmpty()) {
			System.out.println("inside null");
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

		Date arrival = null;
		try {
			arrival = simpleDateFormat.parse(arrivalTime);
		} catch (ParseException e) {
			e.getMessage();
		}
		Date departure = null;
		try {
			departure = simpleDateFormat.parse(departureTime);
		} catch (ParseException e) {

			e.getMessage();
		}
		String hours = String.valueOf((float) (arrival.getTime() - departure
				.getTime()) / 3600000);
		return hours;
	}

}
