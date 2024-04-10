package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor

public class VacanciesController {
    private final VacancyService vacancyService;

    @GetMapping
    public String getAllVacancies(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int pageSize,
                                  @RequestParam(required = false) String category) {

        List<VacancyDto> vacancies;
        int totalVacancies;
        int totalPages;

        if (category != null && !category.isEmpty()) {
            vacancies = vacancyService.getVacanciesByCategory(category, page, pageSize);
            totalVacancies = vacancyService.getTotalVacanciesCountByCategory(category);
        } else {
            vacancies = vacancyService.getAllVacancies(page, pageSize);
            totalVacancies = vacancyService.getTotalVacanciesCount();
        }

        totalPages = (int) Math.ceil((double) totalVacancies / pageSize);

        model.addAttribute("vacancies", vacancies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("selectedCategory", category);

        return "vacancies";
    }

    @GetMapping(params = "category")
    public String getVacanciesByCategory(@RequestParam("category") String category,
                                         Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int pageSize) {

        List<VacancyDto> vacancies = vacancyService.getVacanciesByCategory(category, page, pageSize);
        int totalVacancies = vacancyService.getTotalVacanciesCountByCategory(category);
        int totalPages = (int) Math.ceil((double) totalVacancies / pageSize);

        model.addAttribute("vacancies", vacancies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "vacancies";
    }

    @GetMapping("/{id}")
    public String viewVacancyDetails(@PathVariable("id") Long id, Model model) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "vacancy_details";
    }
}
