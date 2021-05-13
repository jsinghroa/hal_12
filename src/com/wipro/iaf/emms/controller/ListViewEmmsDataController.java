package com.wipro.iaf.emms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.iaf.emms.constants.Constants;
import com.wipro.iaf.emms.form.AssetConfigForm;
import com.wipro.iaf.emms.form.EmmsDataForm;
import com.wipro.iaf.emms.form.PMDetailForm;
import com.wipro.iaf.emms.service.HalService;

@Controller
@RequestMapping("/ListView")
public class ListViewEmmsDataController {

	@Autowired
	private FLBController flbController;

	@Autowired
	private HalService halService;

	@Autowired
	InstallableAssetController installController;

	@Autowired
	private PMController pmController;

	@Autowired
	private AssetController assetController;

	@Autowired
	private MeterController meterController;

	@Autowired
	private EmmsDataForm emmsDataForm;

	@RequestMapping(value = { "/onListView" }, method = RequestMethod.GET)
	public String onPageLoad(ModelMap model) {
		System.out.println("on load");
		List<EmmsDataForm> emmsDataFormList = halService.getEmmsDataOnView();

		model.addAttribute("emmsDataForm", this.emmsDataForm);
		model.addAttribute("emmsDataFormList", emmsDataFormList);
		model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		return "basic";
	}

	@RequestMapping(value = { "/onListViewSubmit" }, method = RequestMethod.POST)
	public String onSubmit(ModelMap model, @RequestParam String action,
			@RequestParam String linkSelected,
			@RequestParam(required = false) String deleterecordId,
			@ModelAttribute("emmsDataForm") EmmsDataForm emmsDataForm) {
		if (linkSelected.equals(Constants.LISTVIEW)) {
			List<EmmsDataForm> emmsDataFormList = halService
					.getEmmsDataOnView();
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		} else if (action.equals("Yes")) {
			halService.deleteData(deleterecordId);
			List<EmmsDataForm> emmsDataFormList = halService
					.getEmmsDataOnView();
			model.addAttribute("emmsDataForm", this.emmsDataForm);
			model.addAttribute("emmsDataFormList", emmsDataFormList);
			model.addAttribute("pageVar", "/WEB-INF/jsp/ListViewEmmsData.jsp");
		} else {
			if (linkSelected.equals(Constants.ASSETCONFIG)) {
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					System.out.println("freeze==" + emmsDataForm.isFreeze());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/AssetConfig.jsp");

			} else if (linkSelected.equals(Constants.INSTALLABLEASSET)) {
				
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setAssetFormList(assetController
							.fetchAssetDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					for(int i=0;i<emmsDataForm.getAssetFormList().size();i++){
						if(emmsDataForm.getAssetFormList().get(i).getErrorStatus()!=null){
							if (emmsDataForm.getAssetFormList().get(i).getErrorStatus().equals(Constants.VALIDATEDWITHWARNING)
								&& emmsDataForm.getAssetFormList().get(i).getIndicator().equals("I") ){
								emmsDataForm.setInstallableFormList(installController
								             .fetchInstallableDetails(emmsDataForm
												.getSelectedRecordId(),emmsDataForm));
							}
						}
				   }
				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar",
						"/WEB-INF/jsp/InstallableAsset.jsp");

			} else if (linkSelected.equals(Constants.PM)) {
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {
					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setPmDetailFormList(pmController
							.fetchPMDetails(emmsDataForm.getSelectedRecordId(),
									emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				/*
				 * System.out.println(emmsDataForm.getPmDetailFormList().toString
				 * ());
				 */
				model.addAttribute("pageVar", "/WEB-INF/jsp/PMScreen.jsp");
			} else if (linkSelected.equals(Constants.METER)) {
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
				System.out.println("FLB"+emmsDataForm.getSelectedRecordId());
				if (null != emmsDataForm.getSelectedRecordId()
						&& !emmsDataForm.getSelectedRecordId().isEmpty()) {

					emmsDataForm = halService.fetchDetails(emmsDataForm
							.getSelectedRecordId());
					emmsDataForm.setFlbMeterDetailsFormList(flbController
							.fetchFLBMeterDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbPostFlightDataFormList(flbController
							.fetchFLBPostFlightDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));
					emmsDataForm.setFlbSortieArFormList(flbController
							.fetchFLBSortieArDetails(emmsDataForm
									.getSelectedRecordId(),emmsDataForm));

				}
				model.addAttribute("emmsDataForm", emmsDataForm);
				model.addAttribute("pageVar", "/WEB-INF/jsp/FLBScreen.jsp");

			}
		}
		return "basic";
	}
}
