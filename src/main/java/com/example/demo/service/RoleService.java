package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> setRoles();

    Set<Role> getSetOfRolesByName(String[] strArr);
}
