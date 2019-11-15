package com.developer.finalprojectseg3102.models;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Comment {
    private String body_text;
    private User author;
    private Thread thread;

    public Comment() {
    }

    public Comment(String body_text, User author, Thread thread) {
        this.body_text = body_text;
        this.author = author;
        this.thread = thread;
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

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }


}
