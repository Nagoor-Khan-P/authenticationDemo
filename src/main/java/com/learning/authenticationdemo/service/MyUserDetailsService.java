package com.learning.authenticationdemo.service;

import com.learning.authenticationdemo.model.MyUserDetails;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {
    Logger logger = Logger.getLogger(getClass().getName());

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public MyUserDetailsService(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if(users == null) {
            logger.info("User not Found");
            throw new UsernameNotFoundException("User "+ username + " not Found");
        }
        return new MyUserDetails(users);
    }

    public Users createUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepo.save(user);
        return user;
    }

    public String validateUserLogin(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "User not authenticated";

    }
}
