package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {
    //list all users
    public List<String> findAll();

    //find user by username
    public User findByUsername(String username);

    //find userid by username
    public int findIdByUsername(String username);

    //create user
    public boolean create(String username, String password);
}
