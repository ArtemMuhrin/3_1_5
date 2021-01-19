package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
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
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
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
