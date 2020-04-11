package com.carcompany.web_project.service;

import com.carcompany.web_project.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    boolean exists(User user);
    boolean exists(String username);
    User create(User user);
    User edit(String username, String password);
    User delete(String username);
    User get(String username);
}
