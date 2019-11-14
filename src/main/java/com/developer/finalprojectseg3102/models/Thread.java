package com.developer.finalprojectseg3102.models;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Thread {
    private String title;
    private String body_text;
    private User author;
    private Team team;

    public Thread() {
    }

    public Thread(String title, String body_text, User author, Team team) {
        this.title = title;
        this.body_text = body_text;
        this.author = author;
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody_text() {
        return body_text;
    }

    public void setBody_text(String body_text) {
        this.body_text = body_text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
