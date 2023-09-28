package com.musterdekho.tutorial.service;

import com.musterdekho.tutorial.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    public List<User> findAll();
    public User findUser(Integer id);
    User updateUser (User user);
    public void DeleteUser(Integer id);

}
