package com.example.demo.dao;


import com.example.demo.model.User;

import com.example.demo.model.Role;
import java.util.List;
import java.util.Set;

public interface UserDao {
   void add(User user);
   void delete(Long id);
   void update(User user);
   List<User> listUsers();
   User getById(Long id);
   User getByName(String name);
   Set<Role> setRoles();
}
