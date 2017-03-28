package com.corp.conversj.outerspacemanager.Model;

/**
 * Created by conversj on 06/03/2017.
 */

public class User {
    private String username;
    private String password;
    private String email;
    private String token;
    private Double gas;
    private Double gasModifier;
    private Double minerals;
    private Double mineralsModifier;
    private Double points;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public String getUsername(){
        return this.username;
    }

    public Double getPoints(){
        return this.points;
    }

    public Double getGas() {
        return gas;
    }

    public Double getMinerals() {
        return minerals;
    }
}
