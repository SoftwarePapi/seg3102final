package com.developer.finalprojectseg3102.controllers;

import javax.servlet.http.HttpSession;

public class BaseController {
	
	protected static Boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("loggedIn")!= null || 
		(session.getAttribute("loggedIn")!= null && session.getAttribute("loggedIn").equals(true));
	}
}
