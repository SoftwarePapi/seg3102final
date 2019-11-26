package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static Course retrieve(Integer id) throws Exception{

        URL url = new URL("http://team-management-system.herokuapp.com/api/v1/courses/" + id.toString()+"/?format=json");
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

            Course course = new Course();

            // Create this method
//			user.setUser_id(((Long)jsonObj.get("user_id")).intValue());
            course.setCourseCode((String)jsonObj.get("course_code"));
            return course;
        }
    }
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

}
