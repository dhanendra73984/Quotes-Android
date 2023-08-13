package com.example.awesomequotesapp.Entity;

import java.io.Serializable;

public class FavQuote implements Serializable {

    int f_id;
    int user_id;
    int q_id;


    public FavQuote() {
    }

    public FavQuote(int f_id, int user_id, int q_id) {
        this.f_id = f_id;
        this.user_id = user_id;
        this.q_id = q_id;
    }


    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }
}
