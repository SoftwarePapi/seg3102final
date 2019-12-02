package com.developer.finalprojectseg3102.models;

/**
 * Created by Parastoo on 11/13/2019.
 */
public class Comment {
    private long comment_id;
    private String body_text;
    private User author;
    private long author_id;
    private Thread thread;
    private long thread_id;

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

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getThread_id() {
        return thread_id;
    }

    public void setThread_id(long thread_id) {
        this.thread_id = thread_id;
    }
}
