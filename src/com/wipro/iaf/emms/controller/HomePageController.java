package com.wipro.iaf.emms.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
	@RequestMapping(value = { "/" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String defaultredir(ModelMap map, HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		Principal principal = request.getUserPrincipal();
		/*System.out.println("Username:"+ principal.getName());*/
		if (httpSession.isNew()) {
			httpSession.setMaxInactiveInterval(1800);
		}
		map.addAttribute("pageVar", "/WEB-INF/jsp/homePage.jsp");
		return "basic";
	}
}
