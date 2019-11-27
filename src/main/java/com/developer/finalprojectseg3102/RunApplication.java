package com.developer.finalprojectseg3102;

import javax.servlet.http.HttpSession;

import com.developer.finalprojectseg3102.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.developer.finalprojectseg3102.controllers.BaseController;

@Controller
@SpringBootApplication
public class RunApplication extends BaseController{

	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}

	@RequestMapping("/")
	String index(Model model, HttpSession session) {
		if (!isLoggedIn(session)) {
			return "login";
		} else {
			User user = (User)session.getAttribute("user");
			String name = user.getFirstName();
			System.out.println(name);
			model.addAttribute(name);
			return "index";
		}
		
	}
	
	

}
