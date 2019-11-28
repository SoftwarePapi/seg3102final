package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CourseDAO;
import com.developer.finalprojectseg3102.dao.SectionDAO;
import com.developer.finalprojectseg3102.dao.UserDAO;
import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

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

    public List<Section> getCourseSections(int course_id) throws Exception {
        List<Section> sections = SectionDAO.retrieveSections();

        for(int i=0; i<sections.size(); i++){
            Section section = sections.get(i);
            if(section.getCourse_id() == course_id){
                sections.add(section);
            }
        }
        return sections;
    }

    public Course getSectionsCourse(Long section_id ) throws Exception {
        Section section = SectionDAO.retrieve(section_id);
        Course course = CourseDAO.retrieve(section.getCourse_id());
        return course;
    }


    public List<User> getSectionsStudents(Long section_id) throws Exception {
        return SectionDAO.retrieveSectionStudents(section_id);
    }

    public User getSectionsProfessor(Long section_id) throws Exception {
        Section section = SectionDAO.retrieve(section_id);
        return UserDAO.retrieve(section.getProfessor_id());
    }

    public String sectionFullName(Long section_id) throws Exception {
        Section section = SectionDAO.retrieve(section_id);
        Course course = CourseDAO.retrieve(section.getCourse_id());
        return (course.getCourseCode() + section.getSection_name());
    }
}
