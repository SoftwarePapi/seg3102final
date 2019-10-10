package com.developer.finalprojectseg3102.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index"; 
        /*the return string is the name of the html file
        remember to return login page if session detects not logged in */
    }
	
}
