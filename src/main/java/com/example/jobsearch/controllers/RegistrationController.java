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

    @PostMapping("/submit")
    public String registerUser(User user, @RequestParam("userType") String userType) {
        if (userType.equals("applicant")) {
            user.setRoleId(1L);
        } else {
            user.setRoleId(2L);
        }
        userService.createUser(user);
        return "redirect:/profile/" + user.getRoleId();
    }

}
