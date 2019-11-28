package com.developer.finalprojectseg3102.models;

import java.util.List;

public class User {

	private long user_id;
	private String firstName;
	private String lastName;
	private long identification_number;
	private String email;
	private String account_type;
	private String program;
	private String password;
	private List<Team> teams;
	private List<Section> sections;


	public User() {
	}

	public User(long user_id, String firstName, String lastName, long identification_number, String email, String account_type, String program, String password, List<Team> teams) {
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identification_number = identification_number;
		this.email = email;
		this.account_type = account_type;
		this.program = program;
		this.password = password;
		this.teams = teams;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getIdentificationNumber() {
		return identification_number;
	}
	public void setIdentificationNumber(long identification_number) {
		this.identification_number = identification_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountType() {
		return account_type;
	}
	public void setAccountType(String account_type) {
		this.account_type = account_type;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Section> getSections() throws Exception {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Team> getTeams() throws Exception {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public boolean isAdmin(){
		return (this.account_type == "admin");
	}
	public boolean isStudent(){
		return (this.account_type == "student");
	}
	public boolean isProfessor() {
		return (this.account_type == "professor");
	}
	public String toString() {
		return getEmail();
	}
}
