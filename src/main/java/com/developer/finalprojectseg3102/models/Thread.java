package com.developer.finalprojectseg3102.models;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Thread {
    private long thread_id;
    private String title;
    private String body_text;
    private User author;
    private Long author_id;
    private Team team;
    private Long team_id;
    public Thread() {
    }

    public Thread(String title, String body_text, User author, Team team) {
        this.title = title;
        this.body_text = body_text;
        this.author = author;
        this.team = team;
    }

    public long getThread_id() {
        return thread_id;
    }

    public void setThread_id(long thread_id) {
        this.thread_id = thread_id;
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

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }
}
