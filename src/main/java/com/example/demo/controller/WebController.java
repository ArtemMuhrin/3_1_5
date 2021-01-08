package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String showMainPage(ModelMap model) {
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("roleList", userService.setRoles());
        model.addAttribute("user", new User());
        return "admin";
    }

    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("id") long id, ModelMap model) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.GET)
    public String showUpdateMenu(@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", userService.setRoles());
        return "edit";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String updateCurrentUser(@ModelAttribute User user, ModelMap model) {
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/new", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute User user, ModelMap model) {
        userService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getByName(principal.getName()));
        return "user";
    }
}