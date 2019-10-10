package com.developer.finalprojectseg3102.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index"; //the return string is the name of the html file
    }
	
	@RequestMapping(value="/login")
	public String getLoginPage(@RequestParam(name = "name", required = false, defaultValue = "person")
	String name, Model model) {
		model.addAttribute("name", name);
		return "login";
		
	}
}
