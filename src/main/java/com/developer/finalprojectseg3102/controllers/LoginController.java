package com.developer.finalprojectseg3102.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{

	@RequestMapping(value="/login")
	public String getLoginPage(@RequestParam(name = "username", required = false, defaultValue = "person")
	String name, Model model) {
		model.addAttribute("username", name);
		return "login";
		
	}
	
}
