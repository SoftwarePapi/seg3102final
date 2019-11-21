package com.developer.finalprojectseg3102.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{

	@RequestMapping(value="/login")
	public String getLoginPage(@RequestParam(name = "username", required = false, defaultValue = "person")
	String name, Model model, HttpSession session) {
		
		/*
		 * flow:
		 * check if logged in first
		 * 	if yes -> don't show this page
		 * if no -> continue to login
		 */
		
		if (session.getAttribute("loggedIn").equals(false)) {
			model.addAttribute("username", name);
			return "login";
		}
		else {
			//placeholder return
			return "home";
		}
	}
	
}
