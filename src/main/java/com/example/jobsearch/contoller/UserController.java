package com.example.jobsearch.contoller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.exception.UserNotFoundException;
import com.example.jobsearch.models.User;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("users/{name}")
    public ResponseEntity<List<UserDto>> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getUserByName(name));
    }

    @GetMapping("users/phone")
    public ResponseEntity<?> getUserByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        try {
            UserDto user = service.getUserByPhone(phone);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("users/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email", defaultValue = "John") String email) {
        try {
            return ResponseEntity.ok(service.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("users")
    public HttpStatus createUser(User user) {
        service.createUser(user);
        return HttpStatus.OK;
    }
}