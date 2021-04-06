package com.example.football.api.controllers;

import com.example.football.models.User;
import com.example.football.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user/getAll"}, method = RequestMethod.GET)
    @PreAuthorize("@appAuthorizer.authorize(authentication, 'REGISTER', this)")
    public List<User> list() {
        return userService.listAllUser();
    }
}
