//package com.example.jobsearch.controllers;
//
//import com.example.jobsearch.models.User;
//import com.example.jobsearch.services.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/register")
//@RequiredArgsConstructor
//public class RegistrationController {
//
//    private final UserService userService;
//
//
//    @GetMapping("/create")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/profile")
//    public String registerUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        Long userId = userService.getUserId(user.getEmail());
//        return "redirect:/profile/" + userId;
//    }
//}




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
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        Long userId = user.getId();
        return "redirect:/profile/" + userId;
    }
}
