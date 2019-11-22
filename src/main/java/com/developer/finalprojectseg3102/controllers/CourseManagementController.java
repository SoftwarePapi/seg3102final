package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CourseDAO;
import com.developer.finalprojectseg3102.models.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static com.developer.finalprojectseg3102.controllers.BaseController.isLoggedIn;

@Controller
public class CourseManagementController extends BaseController {

    @RequestMapping(value = "/create-course")
    public String createCourse(@ModelAttribute Course course, Model model, HttpSession session) {
        if (isLoggedIn(session)) {
            model.addAttribute(course);

            if (course.getCourseCode() != null){
                CourseDAO.create(course);
            }
            return "course";
        }
        else{
            return "login";
        }
    }
}
