package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;

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

    @RequestMapping(value="/thread")
    public String getThreadPage(Model model, HttpSession session) {
        return "thread";
    }

}
