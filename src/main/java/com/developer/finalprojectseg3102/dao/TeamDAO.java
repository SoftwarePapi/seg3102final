package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Team;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Parastoo on 11/25/2019.
 */
public class TeamDAO {


    public static Team retrieve(Integer id) throws Exception{

        URL url = new URL("http://team-management-system.herokuapp.com/api/v1/teams/" + id.toString()+"/?format=json");
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

            // Create this method
            team.setTeamName((String)jsonObj.get("team_name"));
            team.setCaptain_id((Integer)jsonObj.get("captain"));
            return team;
        }
    }



}
