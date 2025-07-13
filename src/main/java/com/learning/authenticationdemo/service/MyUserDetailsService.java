package com.learning.authenticationdemo.service;

import com.learning.authenticationdemo.exception.AppException;
import com.learning.authenticationdemo.model.MyUserDetails;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static com.learning.authenticationdemo.constants.AppConstants.*;

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

    public String createUser(Users user) {
        if(userRepo.existsByUsername(user.getUsername())) {
            throw new AppException(String.format(USER_NAME_ALREADY_TAKEN, user.getUsername()));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered suucessfully!";
    }

    public String validateUserLogin(Users user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        } catch (BadCredentialsException e) {
            throw new AppException(INVALID_USER_NAME_OR_PASSWORD);
        } catch (UsernameNotFoundException e) {
            throw new AppException(USER_NOT_FOUND);
        }
        throw new AppException(USER_LOGIN_FAILED);

    }

    public String validateUser(String token) {
        try {
            String userName = jwtService.extractUserName(token);
            Users user = new Users();
            user.setUsername(userName);
            if(!jwtService.validateToken(token, new MyUserDetails(user))) {
                throw new AppException("Invalid token or Expired token!");
            }
            return userName;
        } catch (Exception e) {
            throw new AppException("User authentication failed!");
        }
    }

    public Users fetchUserByUserName(String userName) {
        Users users = userRepo.findByUsername(userName);
        if(users == null) {
            logger.info("User not Found");
            throw new AppException("User "+ userName + " not Found");
        }
        return users;
    }
}
