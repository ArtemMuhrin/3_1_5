package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> setRoles() {
       return userDao.setRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> getSetOfRolesByName(String[] strArr) {
            Set<Role> newSetOfRoles = new HashSet<>();
            Set<Role> setOfAllRole = this.setRoles();

            for (String roleName : strArr) {
                for (Role role : setOfAllRole) {
                    if (role.getRole().equals(roleName)) {
                        newSetOfRoles.add(role);
                    }
                }
            }
            return newSetOfRoles;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
       return userDao.getByName(name);
    }
}
