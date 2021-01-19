package com.example.demo.converters;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class StringToRoleSetConverter implements Converter<String, Set<Role>> {

    @Autowired
    private RoleService roleService;

    @Override
    public Set<Role> convert(String inputString) {
        String[] inArr = {inputString};
        return roleService.getSetOfRolesByName(inArr);
    }
}
