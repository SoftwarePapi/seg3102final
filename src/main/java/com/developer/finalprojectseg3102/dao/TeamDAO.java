package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Team;
import com.developer.finalprojectseg3102.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Parastoo on 11/25/2019.
 */
public class TeamDAO extends BaseDAO{


    public static Team retrieve(Long id) throws Exception{

        URL url = new URL(BASEURLV1 + "teams/" + id.toString()+"/?format=json");
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

            Team team = new Team();

            team.setTeam_id((Long)jsonObj.get("team_id"));
            team.setTeam_name((String)jsonObj.get("team_name"));
//            team.setCreation_date((Timestamp)jsonObj.get("creation_date"));
            team.setCaptain_id((Long)jsonObj.get("captain"));
            team.setStatus((String)jsonObj.get("status"));
            team.setMin_capacity(((Long)jsonObj.get("min_capacity")).intValue());
            team.setMax_capacity(((Long)jsonObj.get("max_capacity")).intValue());
            team.setSection_id((Long)jsonObj.get("section_id"));

            return team;
        }
    }

    public static ArrayList<Team> retrieveTeams() throws Exception{

        URL url = new URL(BASEURLV1 + "teams/?format=json");
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

            ArrayList<Team> teams = new ArrayList<Team>();
            for(int i=0; i < jsonArray.size(); i++){

                //
                JSONObject row = (JSONObject)jsonArray.get(i);
                Team team = new Team();

                team.setTeam_id((Long)row.get("team_id"));
                team.setTeam_name((String)row.get("team_name"));
//                team.setCreation_date((Timestamp)row.get("creation_date"));
                team.setCaptain_id((Long)row.get("captain"));
                team.setStatus((String)row.get("status"));
                team.setMin_capacity(((Long)row.get("min_capacity")).intValue());
                team.setMax_capacity(((Long)row.get("max_capacity")).intValue());
                team.setSection_id((Long)row.get("section_id"));

                teams.add(team);
            }
            return teams;
        }
    }

    public static ArrayList<User> retrieveTeamMembers(Long team_id) throws Exception{

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

            ArrayList<User> team_members = new ArrayList<User>();
            for(int i=0; i < jsonArray.size(); i++){
                JSONObject row = (JSONObject)jsonArray.get(i);

                if(row.get("team_id") == team_id){
                    User student = UserDAO.retrieve((Long)row.get("user_id"));
                    team_members.add(student);
                }
            }
            return team_members;
        }
    }

    public static void addTeamMember(Long user_id, Long team_id) throws IOException {
        URL url = new URL(BASEURLV1 + "team_members/");

        StringBuilder str = new StringBuilder();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        str.append("{");
        str.append("\"team_id\"" + ":" + "\"" + team_id + "\",");
        str.append("\"user_id\"" + ":" + "\"" + user_id + "\"}");

        String jsonInputString = str.toString();
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




    public static ArrayList<User> retrieveJoinRequests(Long team_id) throws Exception {
        URL url = new URL(BASEURLV1 + "join_requests/?format=json");
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
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(rawJson);

            ArrayList<User> join_requests = new ArrayList<User>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject row = (JSONObject) jsonArray.get(i);
                if (row.get("team_id") == team_id) {
                    User student = UserDAO.retrieve((Long) row.get("student_id"));
                    join_requests.add(student);
                }
            }
            return join_requests;
        }
    }


    public static void addJoinRequest(Long user_id, Long team_id) throws Exception {

        URL url = new URL(BASEURLV1 + "join_requests/");

        StringBuilder str = new StringBuilder();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        str.append("{");
        str.append("\"student_id\"" + ":" + "\"" + user_id + "\",");
        str.append("\"team_id\"" + ":" + "\"" + team_id + "\"}");

        String jsonInputString = str.toString();
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


}
