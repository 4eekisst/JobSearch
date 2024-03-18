package com.example.jobsearch.services;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(User user);
    List<UserDto> getUserByName(String name);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);
    Boolean checkIfUserExists(String email);
    void editUser(User user);
}
