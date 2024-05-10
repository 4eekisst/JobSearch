package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.services.*;
import jakarta.validation.Valid;
import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.dto.vacancies.VacancyCreateDto;
import com.example.jobsearch.dto.vacancies.VacancyDto;
import com.example.jobsearch.dto.vacancies.VacancyEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
   private final VacancyService vacancyService;
   private final UserService userService;
   private final CategoryService categoryService;
   private final RespondedApplicantsService respondedApplicantsService;
   private final ResumeService resumeService;

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



   @GetMapping("/info/{vacancyId}")
    public String getVacancyById(@PathVariable Long vacancyId, Model model){
       VacancyDto vacancy = vacancyService.getVacancyById(vacancyId);
       model.addAttribute("vacancy", vacancy);
       List<VacancyDto> vacanciesOfAuthor = vacancyService.getActiveVacanciesByCompany(vacancy.getAuthorEmail());
       model.addAttribute("employers", vacanciesOfAuthor);
       return "vacancy/vacancyInfo";
   }

   @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, Model model){
        List<VacancyDto> vacancies = vacancyService.getVacanciesByCategory(category);
        model.addAttribute("vacancies", vacancies);
       List<CategoryDto> categories = categoryService.getCategories();
       model.addAttribute("categories",categories);
        return "vacancy/filteredVacancies";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @GetMapping("/create")
    public String getVacancyCreatePage(Model model){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "vacancy/vacancyCreate";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @PostMapping("/create")
    public String createVacancy(Model model, @Valid VacancyCreateDto createDto, Authentication authentication){
        vacancyService.createVacancyAndReturnId(createDto,authentication);
        model.addAttribute("user",userService.getUserByEmail(authentication.getName()));
        return "redirect:/profile";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @GetMapping("/update")
    public String getVacancyEditPage(Model model, @RequestParam Long id){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        VacancyDto dto = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy",dto);
        return "vacancy/editVacancy";
    }

    @PreAuthorize("(hasAuthority('employer'))")
    @PostMapping("/update")
    public String updateVacancy(@RequestParam Long id,Model model, @Valid VacancyEditDto editDto, Authentication authentication){
       editDto.setId(id);
       vacancyService.editVacancy(editDto,authentication);
        model.addAttribute("user",userService.getUserByEmail(authentication.getName()));
        return "redirect:/profile";
    }

    @PostMapping("/changeActivation")
    public String changeActivation(@RequestParam Long id) {
        vacancyService.changeVacancyState(id);
        return "redirect:/profile";
    }
}
