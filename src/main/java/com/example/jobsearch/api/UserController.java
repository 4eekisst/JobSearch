package com.example.jobsearch.api;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.models.User;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(service.getUserByName(name.strip()));
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getUserByPhoneNum(@RequestParam(name = "phone", defaultValue = "0") String phone) {
        UserDto user = service.getUserByPhone(phone.strip());
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with phone number " + phone + " not found") : ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = service.getUserByEmail(email);
        return user == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found") : ResponseEntity.ok(user);
    }

    @GetMapping("confirm/{confirm}")
    public ResponseEntity<?> checkUserByEmail(@PathVariable String confirm) {
        return ResponseEntity.ok(service.checkIfUserExists(confirm.strip()));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            service.createUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<UserDto>> getApplicantsByAccountType(@RequestParam(name = "accountType") String accountType) {
        return ResponseEntity.ok(service.getApplicantsByAccountType(accountType.strip()));
    }

    @GetMapping("/employers")
    public ResponseEntity<?> getEmployers() {
        try {
            List<UserDto> employers = service.getEmployersByAccountType("employer");
            return ResponseEntity.ok(employers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get employers: " + e.getMessage());
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
