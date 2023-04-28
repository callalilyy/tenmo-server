package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();

    public User findByUsername(String username);

    public int findIdByUsername(String username);

    public boolean create(String username, String password);
}
