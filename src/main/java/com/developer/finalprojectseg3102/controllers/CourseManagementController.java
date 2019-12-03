package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.*;
import com.developer.finalprojectseg3102.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static com.developer.finalprojectseg3102.controllers.BaseController.isLoggedIn;

@Controller
public class CourseManagementController extends BaseController {

    @RequestMapping(value = "/course", params = "section_id")
    public String course(@RequestParam ("section_id") String section_id, @ModelAttribute Section section, Model model, HttpSession session) throws Exception {
        // Check if user has a team
        Boolean hasTeam = false;
        Boolean isStudent = false;

        section = SectionDAO.retrieve(Long.parseLong(section_id));
        model.addAttribute("section", section);
        model.addAttribute("courseInfo", sectionFullName(Long.parseLong(section_id)));
        User current_user = (User) session.getAttribute("user");

        if((current_user.getAccount_type()).equals("professor")){
            isStudent = false;
            hasTeam = false;

            List<Team> sectionTeams = sectionTeams(section.getSection_id());
            model.addAttribute("sectionTeams", sectionTeams);
        }

        // User is a student
        else{
            isStudent = true;
            Team team = studentTeamBasedOnSection(current_user.getUser_id(), section.getSection_id());
            // User has a team
            if(team != null && team.getTeam_id() != 0){
                hasTeam = true;
                model.addAttribute("team", team);
            }

            // user has no team
            else{
                hasTeam = false;
                List<Team> availableTeams = new ArrayList<>();
                List<Team> allTeams = TeamDAO.retrieveTeams();
                for(int i = 0; i<allTeams.size(); i++){

                    int max = (allTeams.get(i)).getMax_capacity();
                    List<User> teamMembers = TeamDAO.retrieveTeamMembers((allTeams.get(i)).getTeam_id());

                    if(max > teamMembers.size()){
                        availableTeams.add(allTeams.get(i));
                    }
                }
                model.addAttribute("availableTeams", availableTeams);
            }
        }

        model.addAttribute("hasTeam", hasTeam);
        model.addAttribute("isStudent", isStudent);
        
        session.setAttribute("hasTeam", hasTeam);
        session.setAttribute("isStudent", isStudent);

        // Threads and comments
        com.developer.finalprojectseg3102.models.Thread thread = ThreadDAO.retrieve(Long.valueOf(2));
        model.addAttribute("thread", thread);

        return "course";
    }
}
