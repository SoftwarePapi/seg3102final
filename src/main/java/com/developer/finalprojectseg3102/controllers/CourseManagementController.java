package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CourseDAO;
import com.developer.finalprojectseg3102.models.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static com.developer.finalprojectseg3102.controllers.BaseController.isLoggedIn;

@Controller
public class CourseManagementController extends BaseController {

    @RequestMapping(value = "/create-course")
    public String createCourse(@ModelAttribute Course course, Model model, HttpSession session) {
        //Also check if the user is admin: current_user.isAdmin()
        if (isLoggedIn(session)){
            model.addAttribute(course);

//            if (course.getCourseCode() != null){
//                CourseDAO.create(course);
//            }
            return "admin-course";
        }
        else{
            return "login";
        }
    }

    @RequestMapping(value = "/admin-course")
    public String adminCourse(@ModelAttribute Course course, Model model, HttpSession session) throws Exception {
        //Also check if the user is admin: current_user.isAdmin()
        ArrayList<Course> courses = CourseDAO.retrieveCourses();
        model.addAttribute("courses", courses);
        return "admin-course";
    }


}
