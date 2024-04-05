package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserImageDto;
import com.example.jobsearch.services.UserImageService;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserImageService userImageService;
    @GetMapping("/{userId}")
    public String viewProfile(@PathVariable Long userId, Model model, Principal principal) {
        UserDto user = userService.getUserById(userId);

        boolean isApplicant = user.getRoleId() == 1;
        boolean isEmployer = user.getRoleId() == 2;

        model.addAttribute("user", user);
        model.addAttribute("isApplicant", isApplicant);
        model.addAttribute("isEmployer", isEmployer);

        return "profile";
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        if (!file.isEmpty()) {
            userImageService.upload(new UserImageDto());
        }
        return "redirect:/profile/" + userId;
    }
}
