package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.dto.users.EditUserDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.services.RespondedApplicantsService;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService userService;
    private final RespondedApplicantsService respondedApplicantsService;

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/edit")
    public String employerInfo(Model model, Authentication auth) {
        UserDto user = userService.getUserByEmail(auth.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/editUser";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/edit")
    public String editProfile(EditUserDto userDto, Authentication auth) {
        userService.editUser(userDto,auth);
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/uploadImage")
    public String uploadImageToProfile(Model model, Authentication authentication) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/uploadImage";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/uploadImage")
    public String uploadImageProfile(Model model, Authentication authentication, MultipartFile file) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        userService.uploadImage(file, authentication);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }

    @PreAuthorize("hasAuthority('employee')")
    @GetMapping("/changePassword")
    public String changePassword(Model model, Authentication authentication) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("accType", user.getAccType());
        return "edit/setNewPassword";
    }

    @PreAuthorize("hasAuthority('employee')")
    @PostMapping("/changePassword")
    public String SetNewPassword(Model model, String email, String oldPassword, String newPassword) {
        UserDto user = userService.getUserByEmail(email);
        userService.changePassword(oldPassword,newPassword,email);
        model.addAttribute("accType", user.getAccType());
        return "redirect:/profile";
    }
}
