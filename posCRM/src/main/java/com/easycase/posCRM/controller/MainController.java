package com.easycase.posCRM.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String adminPage(Model model, Principal principal) {

//		User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//		String userInfo = WebUtils.toString(loginedUser);
//		model.addAttribute("userInfo", userInfo);

		return "dashboard";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
//			User loginedUser = (User) ((Authentication) principal).getPrincipal();
//
//			String userInfo = WebUtils.toString(loginedUser);
//
//			model.addAttribute("userInfo", userInfo);
//
//			String message = "Hi " + principal.getName() //
//					+ "<br> You do not have permission to access this page!";
//			model.addAttribute("message", message);

		}

		return "about";
	}
	@RequestMapping(value = "/username", method = RequestMethod.GET)
	  @ResponseBody
	  public String currentUserName(Principal principal) {
	     return principal.getName();
	  }

}
