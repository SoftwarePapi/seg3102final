package com.developer.finalprojectseg3102.dao;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.developer.finalprojectseg3102.models.Thread;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Parastoo on 12/1/2019.
 */
public class ThreadDAO extends BaseDAO {

    public static Thread retrieve(Long id) throws Exception{

        URL url = new URL(BASEURLV1 + "threads/" + id.toString()+"/?format=json");
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
            Thread thread = new Thread();


            thread.setThread_id(((Long)jsonObj.get("thread_id")).intValue());
            thread.setTitle((String)jsonObj.get("title"));
            thread.setBody_text((String)jsonObj.get("body_text"));
            thread.setAuthor_id((Long)jsonObj.get("author"));
            thread.setTeam_id((Long)jsonObj.get("team_id"));

            return thread;
        }
    }
}
