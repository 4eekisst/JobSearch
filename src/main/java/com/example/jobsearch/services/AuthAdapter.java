package com.example.jobsearch.services;

import com.example.jobsearch.dto.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class AuthAdapter {
    private final UserService service;
    public UserDto getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NoSuchElementException("user not authorized");
        }
        if (authentication instanceof AnonymousAuthenticationToken){
            throw new IllegalArgumentException("user by email: "+ authentication.getName() +" not authorized");
        }
        String name = authentication.getName();
        return service.getUserByEmail(name);
    }

}
