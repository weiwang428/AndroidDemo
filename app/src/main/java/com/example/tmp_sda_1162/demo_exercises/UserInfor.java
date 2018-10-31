package com.example.tmp_sda_1162.demo_exercises;

public class UserInfor {
    public UserInfor() {

    }

    public UserInfor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
