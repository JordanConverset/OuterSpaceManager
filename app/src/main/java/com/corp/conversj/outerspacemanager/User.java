package com.corp.conversj.outerspacemanager;

/**
 * Created by conversj on 06/03/2017.
 */

public class User {
    private String username;
    private String password;
    private String token;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getToken(){
        return this.token;
    }
}