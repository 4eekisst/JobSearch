package com.example.jobsearch.controllers;

import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
@RequiredArgsConstructor

public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String showLoginForm() {
        return "login";
    }
}
