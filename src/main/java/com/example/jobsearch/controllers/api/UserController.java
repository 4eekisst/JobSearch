package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.users.UserCreationDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("restUser")
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserDto> getUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }


    @GetMapping("/phone")
    public ResponseEntity<UserDto> getUserByPhoneNum(@Valid @Pattern(regexp = "^\\d+$",message = "enter a correct number") @RequestParam(name = "phone") String phone) {
        UserDto user = userService.getUserByPhone(phone.strip());
        return ResponseEntity.ok(user);
    }



    @GetMapping("/email")
    public ResponseEntity<UserDto> getUserByEmail(@Valid@Email @RequestParam(name = "email", defaultValue = "example@example.com") String email) {
        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/checkLogin")
    public ResponseEntity<Boolean> loginCheck(@RequestParam String email, @RequestParam String password) {
        boolean isValidLogin = userService.loginCheck(email, password);
        return ResponseEntity.ok(isValidLogin);
    }

    @PostMapping()
    public ResponseEntity<UserCreationDto> createUser(@Valid UserCreationDto user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "image/{name}")
    public ResponseEntity<InputStreamResource> get(@PathVariable  String name){
        return userService.downloadImage(name);
    }
}
