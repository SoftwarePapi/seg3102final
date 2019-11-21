package com.developer.finalprojectseg3102.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController{

	@RequestMapping(value="/course")
	public String getCoursePage(Model model, HttpSession session) {
		return "course";
		
	}
	
	@RequestMapping(value="/team")
	public String getTeamPage(Model model, HttpSession session) {
		return "team";
		
	}
	
	@RequestMapping(value="/thread")
	public String getThreadPage(Model model, HttpSession session) {
		return "thread";
		
	}
}
