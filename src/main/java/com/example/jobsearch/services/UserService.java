package com.example.jobsearch.services;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.exception.UserNotFoundException;
import com.example.jobsearch.models.User;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto getUserByEmail(String email) throws UserNotFoundException;

    void createUser(User user);

    List<UserDto> getUserByName(String name);

    UserDto getUserByPhone(String phone) throws UserNotFoundException;

    Long getUserId(String user) throws UserNotFoundException;
}