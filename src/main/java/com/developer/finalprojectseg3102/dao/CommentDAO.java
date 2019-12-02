package com.developer.finalprojectseg3102.dao;

import com.developer.finalprojectseg3102.models.Comment;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Parastoo on 12/1/2019.
 */
public class CommentDAO extends BaseDAO {

    public static Comment retrieve(Long id) throws Exception{

        URL url = new URL(BASEURLV1 + "comments/" + id.toString()+"/?format=json");
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
            Comment comment = new Comment();

            comment.setComment_id((Long)jsonObj.get("comment_id"));
            comment.setBody_text((String)jsonObj.get("body_text"));
            comment.setAuthor_id((Long)jsonObj.get("author"));
            comment.setThread_id((Long)jsonObj.get("thread_id"));

            return comment;
        }
    }
}
