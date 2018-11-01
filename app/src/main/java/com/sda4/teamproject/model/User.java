package com.sda4.teamproject.model;

public class User {

    private String username;
    private String password;
    private boolean status;

    public User(String username, String password, boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public User() {
        this.username="";
        this.password="";
        this.status=true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
