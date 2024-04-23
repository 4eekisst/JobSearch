package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.*;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final UserImageService userImageService;


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
    public String updateProfile(UserDto userDto) throws UserNotFoundException {
        userService.editUser(userDto);
        return "redirect:/profile/" + userDto.getId();
    }

    @PostMapping("/uploadAvatar")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("userId") Long userId) {
        try {
            UserImageDto userImageDto = new UserImageDto();
            userImageDto.setUserId(userId);
            userImageDto.setFile(file);
            userImageService.upload(userImageDto);
            return "redirect:/profile/" + userId;
        } catch (Exception e) {
            return "/profile";
        }
    }
}
