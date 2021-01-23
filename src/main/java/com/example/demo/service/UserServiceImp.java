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
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        if (user.getPassword().equals("")) {
            User userFromDb = getById(user.getId());
            user.setPassword(userFromDb.getPassword());
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        if (user.getRoles() == null) {
            User userFromDb = getById(user.getId());
            user.setRoles(userFromDb.getRoles());
        }
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        return userDao.getOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByName(String name) {
        return userDao.findUserByName(name);
    }
}
