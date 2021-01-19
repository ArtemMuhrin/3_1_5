package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Role> setRoles() {
        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
        return new HashSet<>(query.getResultList());
    }
}
