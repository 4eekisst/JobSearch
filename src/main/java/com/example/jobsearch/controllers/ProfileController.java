package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.*;
import com.example.jobsearch.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final UserImageService userImageService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping("/{userId}")
    public String viewProfile(@PathVariable Long userId, Model model, Principal principal) {
        UserDto user = userService.getUserById(userId);

        boolean isApplicant = user.getRoleId() == 1;
        boolean isEmployer = user.getRoleId() == 2;

        List<VacancyDto> vacancies = vacancyService.getAllVacancies(0, 5);
        List<ResumeDto> resumes = resumeService.getResumes(0, 5);
        model.addAttribute("user", user);
        model.addAttribute("isApplicant", isApplicant);
        model.addAttribute("isEmployer", isEmployer);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("resumes", resumes);

        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute UserDto userDto) {
        userService.editUser(userDto);
        return "redirect:/profile/" + userDto.getId();
    }

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        if (!file.isEmpty()) {
            userImageService.upload(new UserImageDto());
        }
        return "redirect:/profile/" + userId;
    }
}
