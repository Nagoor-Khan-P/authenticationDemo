package com.learning.authenticationdemo.service;

import com.learning.authenticationdemo.model.MyUserDetails;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserRepo userRepo;
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
}
