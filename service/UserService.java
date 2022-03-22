package com.typechecker.typesystem.service;

import com.typechecker.typesystem.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
}
