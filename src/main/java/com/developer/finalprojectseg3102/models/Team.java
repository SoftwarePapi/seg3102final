package com.developer.finalprojectseg3102.models;

import java.sql.Timestamp;
import java.util.List;

public class Team {

	private String team_name;
	private Timestamp creation_date;
	private List<User> members;
	private User captain;
	private String status;
	private int min_capacity;
	private int max_capacity;
	private Section section;

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

	public String getTeamName() {
		return team_name;
	}
	public void setTeamName(String name) {
		this.team_name = team_name;
	}
	public List<User> getMemberList() {
		return members;
	}
	public void setMemberList(List<User> members) {
		this.members = members;
	}
	public void addMember(User newMember){
		this.members.add(newMember);
	}

	//This may not work. Need to test
	public void removeMember(User user){
		members.remove(user);
	}
	public User getCaptain() {
		return captain;
	}
	public void setCaptain(User captain) {
		this.captain = captain;
	}

	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
}
