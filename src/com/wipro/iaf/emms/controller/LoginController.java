package com.wipro.iaf.emms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/LoginPage", method=RequestMethod.GET)
	public String showLoginPage(){
		//return "plain-login";
		return "LoginPage";
	}
	
	/*@RequestMapping(value="/HALLogout", method=RequestMethod.GET)
	public String showLogoutPage(){
		return "HALLogout";
	}*/
}
