package com.developer.finalprojectseg3102.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.developer.finalprojectseg3102.dao.CourseDAO;
import com.developer.finalprojectseg3102.dao.SectionDAO;
import com.developer.finalprojectseg3102.dao.TeamDAO;
import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.Team;
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
				ArrayList<User> userList = UserDAO.retrieveUsers();

				for (int i = 0; i < userList.size(); i++) {
					if (user.getEmail() != null && user.getPassword() != null) {
						if (user.getEmail().equals(userList.get(i).getEmail())
								&& user.getPassword().equals(userList.get(i).getPassword())) {
							session.setAttribute("loggedIn", true);
							session.setAttribute("user", userList.get(i));
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Clean up login check in this method
		if (isLoggedIn(session)) {
			User current_user = (User) session.getAttribute("user");
			model.addAttribute("user", current_user);
			List<Section> sections = getStudentSections(current_user.getUser_id());
			model.addAttribute("sections", sections);
			return "index";
		} else {
			return "login";
		}
	}

	public List<Section> getStudentSections(long user_id) throws Exception {
		return UserDAO.retrieveStudentSections(user_id);
	}
}
