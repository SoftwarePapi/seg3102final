package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Team;
import com.developer.finalprojectseg3102.models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Parastoo on 11/25/2019.
 */
public class TeamDAO extends BaseDAO{


    public static Team retrieve(Integer id) throws Exception{

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

            team.setTeam_id((Integer)jsonObj.get("team_id"));
            team.setTeamName((String)jsonObj.get("team_name"));
            team.setCreation_date((Timestamp)jsonObj.get("creation_date"));
            team.setCaptain_id((Integer)jsonObj.get("captain"));
            team.setStatus((String)jsonObj.get("status"));
            team.setMin_capacity((Integer)jsonObj.get("min_capacity"));
            team.setMax_capacity((Integer)jsonObj.get("max_capacity"));
            //TODO: Add this column to table
            team.setSection_id((Integer)jsonObj.get("section"));

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

                team.setTeam_id((Integer)row.get("team_id"));
                team.setTeamName((String)row.get("team_name"));
                team.setCreation_date((Timestamp)row.get("creation_date"));
                team.setCaptain_id((Integer)row.get("captain"));
                team.setStatus((String)row.get("status"));
                team.setMin_capacity((Integer)row.get("min_capacity"));
                team.setMax_capacity((Integer)row.get("max_capacity"));
                //TODO: Add this column to table

                team.setSection_id((Integer)row.get("section"));

                teams.add(team);
            }
            return teams;
        }
    }

    public static ArrayList<User> retrieveTeamMembers(int team_id) throws Exception{

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

                if((Integer)row.get("team_id") == team_id){
                    User student = UserDAO.retrieve((Integer)row.get("user_id"));
                    team_members.add(student);
                }
            }
            return team_members;
        }
    }

}
