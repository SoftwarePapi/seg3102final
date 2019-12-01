package com.developer.finalprojectseg3102.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.developer.finalprojectseg3102.models.User;

public class UserDAO extends BaseDAO {

	/*
	 * Implement CRUD, CREATE RETRIEVE -> can have either one or all UPDATE DELETE
	 */

	/*
	 * Connection connection = dataSource.getConnection(); Statement stmt =
	 * connection.createStatement(); ResultSet rs;
	 */

	// Doesn't POST properly, the data don't get inserted in the table :(
	public static void create(User user) throws Exception {
		URL url = new URL(BASEURLV1 + "users/");

		StringBuilder str = new StringBuilder();

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		str.append("{");
		str.append("\"account_type\"" + ":" + "\"" + user.getAccount_type() + "\",");
		str.append("\"first_name\"" + ":" + "\"" + user.getFirstName() + "\",");
		str.append("\"last_name\"" + ":" + "\"" + user.getLastName() + "\",");
		str.append("\"indentification_number\"" + ":" + "\"" + user.getIdentification_number() + "\",");
		str.append("\"program\"" + ":" + "\"" + user.getProgram() + "\",");
		str.append("\"email\"" + ":" + "\"" + user.getEmail() + "\",");
		str.append("\"password\"" + ":" + "\"" + user.getPassword() + "\"}");

		String jsonInputString = str.toString();
		System.out.println(jsonInputString);

		con.getOutputStream().write(jsonInputString.getBytes("UTF-8"));

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		con.disconnect();
	}
	public static User retrieve(Long id) throws Exception{

		URL url = new URL(BASEURLV1 + "users/" + id.toString()+"/?format=json");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();

		con.setRequestMethod("GET");
		con.connect();
		int responseCode = con.getResponseCode();

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			String rawJson = new String();
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				rawJson += sc.nextLine();
			}
			sc.close();
			JSONParser parse = new JSONParser();
			JSONObject jsonObj = (JSONObject) parse.parse(rawJson);

			User user = new User();
			// Create this method
			user.setUser_id(((Long) jsonObj.get("user_id")));
			user.setAccount_type((String) jsonObj.get("account_type"));
			user.setFirstName((String) jsonObj.get("first_name"));
			user.setLastName((String) jsonObj.get("last_name"));
			user.setIdentification_number((Long) jsonObj.get("indentification_number"));
			user.setProgram((String) jsonObj.get("program"));
			user.setEmail((String) jsonObj.get("email"));
			user.setPassword((String) jsonObj.get("password"));

			return user;
		}
	}


	@SuppressWarnings("unchecked")
	public static ArrayList<User> retrieveUsers() throws Exception {

		URL url = new URL(BASEURLV1 + "users/?format=json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();

		if (responseCode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		} else {
			String rawJson = new String();
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				rawJson += sc.nextLine();
			}
			sc.close();

			System.out.print("Raw Jason: ");
			System.out.println(rawJson);

			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(rawJson);

			ArrayList<User> users = new ArrayList<User>();
			for (int i = 0; i < jsonArray.size(); i++) {

				//
				JSONObject row = (JSONObject) jsonArray.get(i);
				User user = new User();

				user.setUser_id((Long)row.get("user_id"));
				user.setAccount_type((String) row.get("account_type"));
				user.setFirstName((String) row.get("first_name"));
				user.setLastName((String) row.get("last_name"));
				user.setIdentification_number((Long) row.get("indentification_number"));
				user.setProgram((String) row.get("program"));
				user.setEmail((String) row.get("email"));
				user.setPassword((String) row.get("password"));
				users.add(user);
			}
			return users;
		}
	}

	public static ArrayList<Section> retrieveStudentSections(Long user_id) throws Exception{

		URL url = new URL(BASEURLV1 + "section_students/?format=json");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();

		if(responseCode != 200){
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}
		else{
			String rawJson = new String();
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext()){
				rawJson += sc.nextLine();
			}
			sc.close();
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(rawJson);

			ArrayList<Section> student_sections = new ArrayList<Section>();
			for(int i=0; i < jsonArray.size(); i++){
				JSONObject row = (JSONObject)jsonArray.get(i);
				if(row.get("user_id") == user_id){

					Section section = SectionDAO.retrieve((Long)row.get("section_id"));
					student_sections.add(section);
				}
			}
			return student_sections;
		}
	}

	public static ArrayList<Team> retrieveStudentTeams(Long user_id) throws Exception{

		URL url = new URL(BASEURLV1 + "team_members/?format=json");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();

		if(responseCode != 200){
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}
		else{
			String rawJson = new String();
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext()){
				rawJson += sc.nextLine();
			}
			sc.close();
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(rawJson);

			ArrayList<Team> student_teams= new ArrayList<Team>();
			for(int i=0; i < jsonArray.size(); i++){
				JSONObject row = (JSONObject)jsonArray.get(i);

				if(row.get("user_id") == user_id){
					Team team = TeamDAO.retrieve((Long)row.get("team_id"));
					student_teams.add(team);
				}
			}
			return student_teams;
		}
	}

}