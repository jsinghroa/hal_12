package com.wipro.iaf.emms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wipro.iaf.emms.form.UserRegistrationForm;
import com.wipro.iaf.emms.service.HalService;

@Controller
@RequestMapping("/userRegistration")
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationForm userRegistrationForm;
	
	@Autowired
	private HalService halService;

	@RequestMapping(value={"/onLoad"}, method=RequestMethod.GET)
	public String onPageLoad(ModelMap model)	{
		model.addAttribute("pageVar", "/WEB-INF/jsp/UserRegistration.jsp");
		model.addAttribute("userRegistrationForm",userRegistrationForm);
		return "basic";
	}
	
	@RequestMapping(value={"/onSubmit"}, method=RequestMethod.POST)
	public String onPageSubmit(@ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm,BindingResult bindingResult, ModelMap model){
		halService.insertRegisteredUser(userRegistrationForm);
		model.addAttribute("pageVar", "/WEB-INF/jsp/UserRegistration.jsp");
		return "basic";
	}
}
