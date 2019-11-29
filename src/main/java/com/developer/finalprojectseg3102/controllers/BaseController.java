package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CourseDAO;
import com.developer.finalprojectseg3102.dao.SectionDAO;
import com.developer.finalprojectseg3102.dao.TeamDAO;
import com.developer.finalprojectseg3102.dao.UserDAO;
import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.Team;
import com.developer.finalprojectseg3102.models.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
	
	protected static Boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("loggedIn")!= null || 
		(session.getAttribute("loggedIn")!= null && session.getAttribute("loggedIn").equals(true));
	}

	public List<Team> getStudentTeams(Long user_id) throws Exception {
		return UserDAO.retrieveStudentTeams(user_id);
	}

	public List<User> getTeamMembers(Long team_id) throws Exception {
		Team team = TeamDAO.retrieve(team_id);
		List<User> members = TeamDAO.retrieveTeamMembers(team.getTeam_id());
		return members;
	}
	public User getTeamCaptain(Long team_id) throws Exception {
		Team team = TeamDAO.retrieve(team_id);
		User user = UserDAO.retrieve(team.getCaptain_id());
		return user;
	}

	public Section getTeamSection(Long team_id) throws Exception {
		Team team = TeamDAO.retrieve(team_id);
		return SectionDAO.retrieve(team.getSection_id());
	}

	public List<Section> getStudentSections(long user_id) throws Exception {
		return UserDAO.retrieveStudentSections(user_id);
	}

	public String sectionFullName(Long section_id) throws Exception {
		Section section = SectionDAO.retrieve(section_id);
		Course course = CourseDAO.retrieve(section.getCourse_id());
		return (course.getCourseCode() + " - " + section.getSection_name());
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

	public Team studentTeamBasedOnSection(Long user_id, Long section_id) throws Exception {
		//Get all the teams of the user
		List<Team> userTeams = UserDAO.retrieveStudentTeams(user_id);

		// THE TEAM that belongs to the section and is the user's

		// Get the teams that belong to the considered section
		for(int i=0; i < userTeams.size(); i++){
			Team team = userTeams.get(i);
			if(team.getSection_id() == section_id){
				return team;
			}
		}
		return (new Team());
	}

}
