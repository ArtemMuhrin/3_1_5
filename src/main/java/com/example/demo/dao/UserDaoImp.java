package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        entityManager.remove(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("from User where name = :name", User.class);
        query.setParameter("name", name);
        List<User> usersList = query.getResultList();
        return CollectionUtils.isEmpty(usersList) ? null : usersList.get(0);
    }
}
