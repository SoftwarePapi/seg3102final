package com.developer.finalprojectseg3102.models;

import com.developer.finalprojectseg3102.dao.SectionDAO;
import com.developer.finalprojectseg3102.dao.TeamDAO;
import com.developer.finalprojectseg3102.dao.UserDAO;

import java.sql.Timestamp;
import java.util.List;

public class Team {

	private int team_id;
	private String team_name;
	private Timestamp creation_date;
	private List<User> members;
	private User captain;
	private int captain_id;
	private String status;
	private int min_capacity;
	private int max_capacity;
	private Section section;
	private int section_id;

	public Team() {
	}

	public Team(String team_name, Timestamp creation_date, List<User> members, User captain, String status, int min_capacity, int max_capacity, Section section) {
		this.team_name = team_name;
		this.creation_date = creation_date;
		this.members = members;
		this.captain = captain;
		this.status = status;
		this.min_capacity = min_capacity;
		this.max_capacity = max_capacity;
		this.section = section;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public String getTeamName() {
		return team_name;
	}
	public void setTeamName(String name) {
		this.team_name = team_name;
	}
	public List<User> getMemberList() {
		return members;
	}

	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMin_capacity() {
		return min_capacity;
	}

	public void setMin_capacity(int min_capacity) {
		this.min_capacity = min_capacity;
	}

	public int getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}

	public void setMemberList(List<User> members) {
		this.members = members;
	}

	public List<User> getMembers() throws Exception {
		List<User> members = TeamDAO.retrieveTeamMembers(getTeam_id());
		return members;
	}

	public void addMember(User newMember){
		this.members.add(newMember);
	}

	//This may not work. Need to test
	public void removeMember(User user){
		members.remove(user);
	}
	public User getCaptain() throws Exception {
		User user = UserDAO.retrieve(this.getCaptain_id());
		return user;
	}
	public void setCaptain(User captain) {
		this.captain = captain;
	}

	public Section getSection() throws Exception {
		return SectionDAO.retrieve(getSection_id());
	}

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public int getCaptain_id() {
		return captain_id;
	}

	public void setCaptain_id(int captain_id) {
		this.captain_id = captain_id;
	}
}
