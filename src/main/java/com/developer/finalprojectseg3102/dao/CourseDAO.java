package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Course;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends BaseDAO {

    /*
	 * Implement CRUD,
	 * CREATE
	 * RETRIEVE -> can have either one or all
	 * UPDATE
	 * DELETE
	 */

    private final static String TABLENAME = "COURSE";
    private final static String COLUMNS = "(course_code)";
    private final static String QUERYEND = ");";


    public static void create(Course course) {
        Connection connection;
        try {
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO " + TABLENAME + COLUMNS + "VALUES (");
            query.append(course.getCourseCode() + QUERYEND);
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query.toString());
            stmt.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    public static Course Retrieve(Integer course_id){
//        Connection connection;
//        try{
//            StringBuilder query = new StringBuilder();
//            query.append("SELECT *  FROM" + TABLENAME + "WHERE course_id=");
//            query.append(course_id + QUERYEND);
//            connection = dataSource.getConnection();
//            Statement stmt = connection.createStatement();
//            stmt.executeQuery(query.toString());
//            stmt.close();
//        }catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public static List<Object> retrieveList() {
        Connection connection;
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM " + TABLENAME);
            connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query.toString());
            stmt.close();

            List<Object> usersList = new ArrayList<>();
            //getString returns by column name
            while(rs.next()) {
                usersList.add(rs.getString("first_name"));
            }
            return usersList;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null; //TODO fix this, returning null is fucking stupid
        }
    }

    public static void update(Course course){


    }
}
