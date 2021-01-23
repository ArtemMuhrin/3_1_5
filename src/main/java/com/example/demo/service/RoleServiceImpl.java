package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly = true)
    @Override
    public Set<Role> setRoles() {
        return new HashSet<>(roleDao.findAll());
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
}
