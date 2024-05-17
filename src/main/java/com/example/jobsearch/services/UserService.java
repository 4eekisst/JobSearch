package com.example.jobsearch.services;

import com.example.jobsearch.dto.users.EditUserDto;
import com.example.jobsearch.dto.users.UserCreationDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.models.UserModel;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    UserDto getUser(String email);
    UserDto getUserByEmail(String email) ;
    UserDto getUserById(Long id);
    void createUser(UserCreationDto user);
    UserDto getUserByPhone(String phone);
    Long getUserId(String user);
    void uploadImage(MultipartFile avatar, Authentication authentication);
    void editUser(EditUserDto user, Authentication auth);
    void changePassword(String oldPassword, String newPassword, String email);
    ResponseEntity<InputStreamResource> downloadImage(String name);
    Boolean loginCheck(String email, String password);
    UserModel getUserModelByEmail(String email);
    void makeResetPasswordLnk(HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException;
    UserModel getByResetPasswordToken(String token);
    void updatePassword(UserModel userModel, String password);
}
