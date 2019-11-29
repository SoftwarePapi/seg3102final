package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.SectionDAO;
import com.developer.finalprojectseg3102.dao.TeamDAO;
import com.developer.finalprojectseg3102.dao.UserDAO;
import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.Team;
import com.developer.finalprojectseg3102.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parastoo on 11/25/2019.
 */
@Controller
public class TeamManagementController extends BaseController{
    private int min = 0;
    private int max = 0;
    private Timestamp creation_date;


    @RequestMapping(value = "/setup-teams")
    public String createTeam(@ModelAttribute int min, int max, Timestamp creation_date, Model model, HttpSession session) throws Exception {
        //Also check if the user is admin: current_user.isAdmin()

        this.min = min;
        this.max = max;
        this.creation_date = creation_date;

        return "course";
    }

    @RequestMapping(value="/course")
    public String getCoursePage(Model model, HttpSession session) {
        return "course";

    }

    @RequestMapping(value="/team")
    public String getTeamPage(Model model, HttpSession session) {
        return "team";

    }

    @RequestMapping(value = "/team", params = "team_id")
    public String team(@RequestParam("team_id") String team_id, @ModelAttribute Team team, Model model, HttpSession session) throws Exception {
        team = TeamDAO.retrieve(Long.parseLong(team_id));
        model.addAttribute("team", team);

        List<User> members = TeamDAO.retrieveTeamMembers(team.getTeam_id());
        List<String> member_names = new ArrayList<>();
        for(int i=0; i<members.size(); i++){
            member_names.add((members.get(i)).fullName());
        }
        model.addAttribute("members", member_names);
        User captain = UserDAO.retrieve(team.getCaptain_id());
        model.addAttribute("captain", captain.fullName());
        return "team";
    }
}
