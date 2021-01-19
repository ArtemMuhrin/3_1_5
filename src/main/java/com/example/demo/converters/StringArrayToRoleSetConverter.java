package com.example.demo.converters;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import java.util.Set;

@Data
@Component
public class StringArrayToRoleSetConverter implements Converter<String[], Set<Role>> {

    @Autowired
    private RoleService roleService;

    @Override
    public Set<Role> convert(String[] inputArr) {
        return roleService.getSetOfRolesByName(inputArr);
    }
}
