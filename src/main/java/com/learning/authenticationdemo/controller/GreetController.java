package com.learning.authenticationdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping
    public String greet(HttpServletRequest request) {
        return "Learn Authentication " + request.getSession().getId();
    }

}
