package com.learning.authenticationdemo.controller;

import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/register")
    public Users createUser(@RequestBody Users user) {
        return myUserDetailsService.createUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        return myUserDetailsService.validateUserLogin(user);
    }

    @GetMapping
    public String get() {
        return "Users";
    }
}
