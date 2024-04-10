package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor

public class LoginController {
    private final UserService userService;
    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        UserDto user = userService.getUserByEmail(email);
        if (user != null && Objects.equals(password, user.getPassword())) {
            session.setAttribute("user", user);
            if (user.getRoleId() == 1) {
                return "redirect:/profile";
            } else if (user.getRoleId() == 2) {
                return "redirect:/profile";
            }
        }
        return "redirect:/login?error";
    }
}
