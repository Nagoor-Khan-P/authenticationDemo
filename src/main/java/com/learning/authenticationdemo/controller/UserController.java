package com.learning.authenticationdemo.controller;

import com.learning.authenticationdemo.controller.mapper.UserMapper;
import com.learning.authenticationdemo.exception.AppException;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.model.usermodel.UserLoginResponse;
import com.learning.authenticationdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<Users> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(myUserDetailsService.fetchUserByUserName(username));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Users user) {
        String response = myUserDetailsService.createUser(user);
        return ResponseEntity.ok(Map.of("message", response));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody Users user) {
        String token = myUserDetailsService.validateUserLogin(user);
        user = myUserDetailsService.fetchUserByUserName(user.getUsername());
        return ResponseEntity.ok(userMapper.toLoginResponse(user, token));
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AppException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        String userName = myUserDetailsService.validateUser(token);
        return ResponseEntity.ok(Map.of("username", userName));
    }


}
