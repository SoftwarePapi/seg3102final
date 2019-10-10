package com.developer.finalprojectseg3102.model;

import java.util.List;
import com.developer.finalprojectseg3102.model.User;

public class Team {

	private String name;
	private Integer id;
	private List<User> members;
	private User captain;
	private String course;
	private String section;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public User getCaptain() {
		return captain;
	}
	public void setCaptain(User captain) {
		this.captain = captain;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	
	
}
