package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.*;
import com.developer.finalprojectseg3102.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Parastoo on 11/25/2019.
 */
@Controller
public class TeamManagementController extends BaseController{

    @RequestMapping(value = "/setup-team")
    public String setupTeam(Model model, HttpSession session) throws Exception {
        return "setup-team";
    }

    @RequestMapping(value = "/set-params")
    public String setParams(@ModelAttribute Team team, Model model, HttpSession session) throws Exception {
        Singleton param = Singleton.getInstance();
        param.min = team.getMin_capacity();
        param.max = team.getMax_capacity();
        System.out.println(param.min);
        System.out.println(param.max);
        return "redirect:/course/?section_id=" + session.getAttribute("section_id");
    }
    
    @RequestMapping(value = "/create-team")
    public String createTeam(@ModelAttribute Team team, Model model, HttpSession session) throws Exception {
        //Also check if the user is admin: current_user.isAdmin()
    	if (isLoggedIn(session) && !session.getAttribute("user").equals(null)) {
    		
    		Team createdTeam = new Team();
    		User requestedUser = (User)session.getAttribute("user");
    		
    		createdTeam.setTeam_name(team.getTeam_name());
    		createdTeam.setMax_capacity(5);
    		createdTeam.setMin_capacity(1);
    		createdTeam.setStatus("incomplete");
    		
    		createdTeam.setSection_id(Long.parseLong((String) session.getAttribute("section_id")));
    		createdTeam.setCaptain_id(requestedUser.getUser_id());
    		
    		TeamDAO.create(createdTeam);
    		
    		ArrayList<Team> allTeams = TeamDAO.retrieveTeams();
    		
    		//add the team id of the LAST team to be added
    		TeamDAO.addTeamMember(createdTeam.getCaptain_id(), allTeams.get(allTeams.size()-1).getTeam_id());
    		
    		//return "course/?section_id=" + session.getAttribute("section_id");
    		return "redirect:/course/?section_id=" + session.getAttribute("section_id");
    		
    	} else {
    		return "login";
    	}
    }


    @RequestMapping(value="/course")
    public String getCoursePage(Model model, HttpSession session) {
    	//session.setAttribute("Section", );
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
        session.setAttribute("team", team);

        List<User> members = TeamDAO.retrieveTeamMembers(team.getTeam_id());
        List<String> member_names = new ArrayList<>();
        for(int i=0; i<members.size(); i++){
            member_names.add((members.get(i)).fullName());
        }
        model.addAttribute("members", member_names);
        User captain = UserDAO.retrieve(team.getCaptain_id());
        model.addAttribute("captain", captain.fullName());
        User current_user = (User)session.getAttribute("user");
        boolean hasRequested = checkJoinRequestSent(current_user.getUser_id(), Long.parseLong(team_id));
        session.setAttribute("hasRequested", hasRequested);

        model.addAttribute("hasRequested", hasRequested);
        model.addAttribute("hasTeam" , session.getAttribute("hasTeam"));
        model.addAttribute("isStudent" , session.getAttribute("isStudent"));

        // If the user logged in is the captain of the team, display the join requests
        boolean isCaptain = isCaptainOfTeam(current_user.getUser_id(), team.getTeam_id());
        model.addAttribute("isCaptain", isCaptain);

        List<User> joinRequestsUser = TeamDAO.retrieveJoinRequests(team.getTeam_id());
        Map<String,User> joinRequests = new HashMap<>();
        for(int i= 0; i< joinRequestsUser.size(); i++){
            User user = joinRequestsUser.get(i);
            joinRequests.put(user.fullName(), user);
        }
        model.addAttribute("joinRequests", joinRequests);


        // Threads and comments
        com.developer.finalprojectseg3102.models.Thread thread = ThreadDAO.retrieve(Long.valueOf(2));
        Comment comment = CommentDAO.retrieve(Long.valueOf(1));
        model.addAttribute("thread", thread);
        model.addAttribute("comment", comment);
        
        session.setAttribute("team_id", team_id);
        return "team";
    }

    @RequestMapping(value = "/join-team")
    public String join_team(@ModelAttribute Team team, Model model, HttpSession session) throws Exception {

        Team current_team = (Team)session.getAttribute("team");
        long team_id = current_team.getTeam_id();
        User user = (User)session.getAttribute("user");
        long user_id = user.getUser_id();

        // Add the request to the request tables
        TeamDAO.addJoinRequest(user_id, team_id);

        Boolean hasRequested = checkJoinRequestSent(user_id, team_id);
        session.setAttribute("hasRequested", hasRequested);

        model.addAttribute("hasRequested", hasRequested);
        model.addAttribute("hasTeam" , session.getAttribute("hasTeam"));
        model.addAttribute("isStudent" , session.getAttribute("isStudent"));

        return "redirect:/team/?team_id=" + session.getAttribute("team_id");
    }

    @RequestMapping(value = "/confirm-join", params = "user_id")
    public String confirm_join(@RequestParam("user_id") String user_id, Model model, HttpSession session) throws Exception {

        Team current_team = (Team)session.getAttribute("team");
        User user = UserDAO.retrieve(Long.parseLong(user_id));
        TeamDAO.addTeamMember(user.getUser_id(), current_team.getTeam_id());

        return "course";

    }

}
