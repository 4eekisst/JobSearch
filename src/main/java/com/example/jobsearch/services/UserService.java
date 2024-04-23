package com.example.jobsearch.services;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserProfileDto;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.models.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(User user);
    List<UserDto> getUserByName(String name);
    UserDto getUserByPhone(String phone);
    void editUser(UserDto userDto) throws UserNotFoundException;
    Long getUserId(String user);
    Boolean checkIfUserExists(String email);
    List<UserDto> getApplicantsByAccountType(String accountType);
    List<UserDto> getEmployersByAccountType(String accountType);
    List<UserDto> getAllUsersByVacancyId(Long id);
    UserProfileDto getUser(Authentication authentication);
}
