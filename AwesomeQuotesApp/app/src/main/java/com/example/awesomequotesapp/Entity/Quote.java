package com.example.awesomequotesapp.Entity;

import java.io.Serializable;

public class Quote implements Serializable
{
    int q_id;
    String text;
    String author;
    int user_id;

    public Quote() {
    }

    public Quote(int q_id, String text, String author, int user_id) {
        this.q_id = q_id;
        this.text = text;
        this.author = author;
        this.user_id = user_id;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
