package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.users.UserCreationDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.dto.vacancies.VacancyDto;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.ResumeService;
import com.example.jobsearch.services.UserService;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final UserService service;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final CategoryService categoryService;
    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("profile")
    public String applicantInfo(Model model, Authentication authentication) {
        authentication.getName();
        UserDto user = service.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(authentication.getName());
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(authentication.getName());
        model.addAttribute("resumes", resumes);
        model.addAttribute("vacancies", vacancies);
        return "/users/profile";
    }

    @GetMapping("register")
    public String create() {
        return "login/register";
    }

    @PostMapping("register")
    public String create(UserCreationDto newUser, Model model) {
        service.createUser(newUser);
        return "redirect:/login";
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

}
