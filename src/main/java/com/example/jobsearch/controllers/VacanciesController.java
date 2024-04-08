package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor

public class VacanciesController {
    private final VacancyService vacancyService;

    @GetMapping()
    public String viewAllVacancies(Model model) {
        List<VacancyDto> allVacancies = vacancyService.getAllVacancies();
        model.addAttribute("vacancies", allVacancies);
        return "vacancies";
    }

    @GetMapping("/{id}")
    public String viewVacancyDetails(@PathVariable("id") Long id, Model model) {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        model.addAttribute("vacancy", vacancy);
        return "vacancy_details";
    }
}
