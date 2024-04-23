package com.example.jobsearch.controllers;

import com.example.jobsearch.models.User;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, @RequestParam("accountType") String accountType) {
        Long roleId = accountType.equalsIgnoreCase("employer") ? 2L : 1L;
        user.setRoleId(roleId);

        userService.createUser(user);
        return "redirect:/login";
    }
}
