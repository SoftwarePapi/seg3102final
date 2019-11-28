package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Section;
import com.developer.finalprojectseg3102.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Parastoo on 11/26/2019.
 */
public class SectionDAO extends BaseDAO{

    public static Section retrieve(Long id) throws Exception{

        URL url = new URL(BASEURLV1 + "sections/" + id.toString()+"/?format=json");
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
            Section section = new Section();
            section.setSection_id((Long)jsonObj.get("section_id"));
            section.setSection_name((String)jsonObj.get("section_name"));
            section.setCourse_id((Long)jsonObj.get("course_id"));
            section.setProfessor_id((Long)jsonObj.get("professor"));

            return section;
        }
    }
    public static ArrayList<Section> retrieveSections() throws Exception{

        URL url = new URL(BASEURLV1 + "sections/?format=json");
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

            ArrayList<Section> sections = new ArrayList<Section>();
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject row = (JSONObject)jsonArray.get(i);

                Section section = new Section();
                section.setSection_id((Long)row.get("section_id"));
                section.setSection_name((String)row.get("section_name"));
                section.setCourse_id((Long)row.get("course_id"));
                section.setProfessor_id((Long)row.get("professor"));

                sections.add(section);
            }
            return sections;
        }
    }

    public static ArrayList<User> retrieveSectionStudents(Long section_id) throws Exception{

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

            ArrayList<User> section_students = new ArrayList<User>();
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject row = (JSONObject)jsonArray.get(i);

                if(row.get("section_id") == section_id){
                    User student = UserDAO.retrieve((Long)row.get("user_id"));
                    section_students.add(student);
                }
            }
            return section_students;
        }
    }
}
