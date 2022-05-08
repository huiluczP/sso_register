package com.huiluczp.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 356425130811357425L;

    public User(){
    }

    public User(String userName, String password, String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    private int id;
    private String userName;
    @JsonIgnore
    private String password;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
