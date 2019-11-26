package com.developer.finalprojectseg3102.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import com.developer.finalprojectseg3102.models.User;
import com.developer.finalprojectseg3102.models.User;;

public class UserDAO extends BaseDAO {
	
	/* 
	 * Implement CRUD, 
	 * CREATE
	 * RETRIEVE -> can have either one or all
	 * UPDATE
	 * DELETE
	 */
	
	/*
	 *  Connection connection = dataSource.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet rs;
	 */
	private final static String TABLENAME = "USERS";
	private final static String COLUMNS = "(account_type, first_name, last_name, indentification_number, program, email, password )";
	private final static String QUERYEND = ");";
	
	public static void create(User user) {
		Connection connection;
		try {
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO " + TABLENAME + COLUMNS + "VALUES (");
			query.append(user.getAccountType() + ", ");
			query.append(user.getFirstName() + ", ");
			query.append(user.getLastName() + ", ");
			query.append(user.getIdentificationNumber() + ", ");
			if (user.getProgram()!=null || user.getProgram()!= "") {
				query.append(user.getProgram() + ", ");
			} else {
				query.append(" " + ", ");
			}
			query.append(user.getEmail() + ", ");
			query.append(user.getPassword() + QUERYEND);
			
			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query.toString());
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static User retrieve(Integer id) throws Exception{

		URL url = new URL("http://team-management-system.herokuapp.com/api/v1/users/" + id.toString()+"/?format=json");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		int responseCode = con.getResponseCode();

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
			JSONParser parse = new JSONParser();
			JSONObject jsonObj = (JSONObject)parse.parse(rawJson);

			User user = new User();

			// Create this method
//			user.setUser_id(((Long)jsonObj.get("user_id")).intValue());
			user.setAccountType((String)jsonObj.get("account_type"));
			user.setFirstName((String)jsonObj.get("first_name"));
			user.setLastName((String)jsonObj.get("last_name"));
			user.setIdentificationNumber((Long)jsonObj.get("indentification_number"));
			user.setProgram((String)jsonObj.get("program"));
			user.setEmail((String)jsonObj.get("email"));
			user.setPassword((String)jsonObj.get("password"));

			return user;
		}
	}

	public static void retrieveList() {
		Connection connection;
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM " + TABLENAME);
			
			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query.toString());
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
