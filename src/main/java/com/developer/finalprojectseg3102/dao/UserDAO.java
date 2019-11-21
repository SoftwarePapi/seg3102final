package com.developer.finalprojectseg3102.dao;

import java.sql.Connection;
import java.sql.Statement;

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
				query.append(user.getProgram() + QUERYEND);
			} else {
				query.append(" " + ", ");
			}
			query.append(user.getEmail() + ", ");
			query.append(user.getPassword() + ");");
			
			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			stmt.executeQuery(query.toString());
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
