package com.developer.finalprojectseg3102.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.standard.expression.AndExpression;

import com.developer.finalprojectseg3102.dao.UserDAO;
import com.developer.finalprojectseg3102.models.User;

@Controller
public class LoginController extends BaseController {

	@RequestMapping(value = "/login")
	public String getLoginPage(Model model, HttpSession session) {

		if (session.getAttribute("user") == null) {
			return "login";
		} else {
			return "index";
		}
	}

	@RequestMapping(value = "/login-attempt")
	public String showLoginPage(Model model, HttpSession session, @ModelAttribute User user) throws Exception {

		try {
			if (isLoggedIn(session)) {
				return "index";
			} else {
				ArrayList<User> userList = new ArrayList<User>();
				userList = (ArrayList<User>) UserDAO.retrieveUsers();

				for (int i = 0; i < userList.size(); i++) {
					if (user.getEmail() != null && user.getPassword() != null) {
						if (user.getEmail().equals(userList.get(i).getEmail())
								&& user.getPassword().equals(userList.get(i).getPassword())) {
							session.setAttribute("loggedIn", true);
							session.setAttribute("user", user);
							break;
							
						}
						/*
						 * User loggedInUser = new User();
						 * loggedInUser.setAccountType(user.getAccountType());
						 * loggedInUser.setEmail(user.getEmail());
						 * loggedInUser.setFirstName(user.getFirstName());
						 * loggedInUser.setIdentificationNumber(user.getIdentificationNumber());
						 * loggedInUser.setLastName(user.getLastName());
						 * loggedInUser.setPassword(user.getPassword());
						 * loggedInUser.setProgram(user.getProgram());
						 * loggedInUser.setUser_id(user.getUser_id());
						 */
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isLoggedIn(session)) {
			return "index";
		} else {
			return "login";
		}
	}
}
