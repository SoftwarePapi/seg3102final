package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Course;
import com.developer.finalprojectseg3102.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDAO extends BaseDAO {

    /*
	 * Implement CRUD,
	 * CREATE
	 * RETRIEVE -> can have either one or all
	 * UPDATE
	 * DELETE
	 */

    public static Course retrieve(Long id) throws Exception{

        URL url = new URL(BASEURLV1 + "courses/" + id.toString()+"/?format=json");
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

			course.setCourse_id(((Long)jsonObj.get("course_id")).intValue());
            course.setCourseCode((String)jsonObj.get("course_code"));
            return course;
        }
    }
    public static ArrayList<Course> retrieveCourses() throws Exception{

        URL url = new URL(BASEURLV1 + "courses/?format=json");
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

            ArrayList<Course> courses = new ArrayList<Course>();
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject row = (JSONObject)jsonArray.get(i);
                Course course = new Course();

                course.setCourse_id(((Long)row.get("course_id")).intValue());
                course.setCourseCode((String)row.get("course_code"));

                courses.add(course);
            }
            return courses;
        }
    }

}
