package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void delete(Long id);

    void update(User user);

    List<User> listUsers();

    User getById(Long id);

    User getByName(String name);
}
