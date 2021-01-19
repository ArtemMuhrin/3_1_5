package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String showMainPage(Principal principal, ModelMap model) {
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("roleList", userService.setRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("currentUser", userService.getByName(principal.getName()));
        return "main";
    }

    @RequestMapping(value = "admin/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("id") long id, ModelMap model) {
        userService.delete(id);
        return "redirect:/main";
    }

    @RequestMapping(value = "admin/edit", method = RequestMethod.POST)
    public String updateCurrentUser(@ModelAttribute User user, ModelMap model) {
        userService.update(user);
        return "redirect:/main";
    }

    @RequestMapping(value = "admin/new", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute User user, ModelMap model) {
        userService.add(user);
        return "redirect:/main";
    }
}