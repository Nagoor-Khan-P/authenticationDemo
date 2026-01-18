package com.learning.authenticationdemo.controller.mapper;

import com.learning.authenticationdemo.model.Role;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.model.usermodel.UserLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(
            target = "role",
            expression = "java(extractPrimaryRole(user.getRoles()))"
    )
    UserLoginResponse toLoginResponse(Users user, String token);

    default String extractPrimaryRole(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.iterator().next().getName();
    }
}
