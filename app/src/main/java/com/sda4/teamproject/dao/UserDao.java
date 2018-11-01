package com.sda4.teamproject.dao;


import com.sda4.teamproject.model.User;

public interface UserDao {
    public void add(User user);
    public void delete(String user_name);
    public void update(String user_name,String password);
    public User getUser(String user_name);
}
