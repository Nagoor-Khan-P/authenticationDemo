package com.learning.authenticationdemo.barberbuddy.controller;

import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/profile")
    public ResponseEntity<Users> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(myUserDetailsService.fetchUserByUserName(username));
    }

}
