package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.users.UserCreationDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.dto.vacancies.VacancyDto;
import com.example.jobsearch.models.UserModel;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.ResumeService;
import com.example.jobsearch.services.UserService;
import com.example.jobsearch.services.VacancyService;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("register")
    public String create() {
        return "login/register";
    }

    @PostMapping("register")
    public String create(UserCreationDto newUser, Model model, HttpServletRequest request) {
        userService.createUser(newUser);
        try {
            request.login(newUser.getEmail(), newUser.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/profile";
    }

    @GetMapping("profile")
    public String applicantInfo(Model model, Authentication authentication) {
        authentication.getName();
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(authentication.getName());
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(authentication.getName());
        model.addAttribute("resumes", resumes);
        model.addAttribute("vacancies", vacancies);
        return "/users/profile";
    }

    @GetMapping()
    public String getVacancies(Model model,
                               @RequestParam(name = "page", defaultValue = "0") Integer page,
                               @RequestParam(name = "sortBy", required = false) String sortBy) {
        if (page < 0) {
            page = 0;
        }
        model.addAttribute("page", page);
        Page<VacancyDto> vacancies;

        if (sortBy != null && !sortBy.isEmpty()) {
            vacancies = vacancyService.getActiveVacanciesPage(page, sortBy);
        } else {
            vacancies = vacancyService.getActiveVacanciesPage(page, null);
        }

        model.addAttribute("vacancies", vacancies);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "main/main";
    }

    @GetMapping("forgot_password")
    public String showForgotPasswordForm() {
        return "login/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest httpServletRequest, Model model) {
        try {
            userService.makeResetPasswordLnk(httpServletRequest);
            model.addAttribute("message", "Мы отправили ссылку для сброса пароля на вашу электронную почту. Пожалуйста, проверьте.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Ошибка при отправке электронной почты");
        }
        return "login/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model){
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Неверный токен");
        }
        return "login/reset_password_form";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest httpServletRequest, Model model){
        String token = httpServletRequest.getParameter("token");
        String password= httpServletRequest.getParameter("password");
        try{
            UserModel userModel = userService.getByResetPasswordToken(token);
            userService.updatePassword(userModel, password);
            model.addAttribute( "message", "Вы успешно сменили пароль");
        } catch (UsernameNotFoundException e){
            model.addAttribute("message", "Неверный токен");
        }
        return "login/message";
    }
}


