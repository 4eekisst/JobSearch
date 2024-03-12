package com.example.jobsearch.contoller;

import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.exception.VacancyNotFoundException;
import com.example.jobsearch.services.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VacancyController {
    private final VacancyService vacancyService;

    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/vacancies")
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        List<VacancyDto> vacancies = vacancyService.getAllVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancies/{id}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long id) {
        try {
            VacancyDto vacancyDto = vacancyService.getVacancyById(id);
            return ResponseEntity.ok(vacancyDto);
        } catch (VacancyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vacancies/applied/{userId}")
    public ResponseEntity<List<VacancyDto>> getVacanciesAppliedByUserId(@PathVariable Long userId) {
        List<VacancyDto> vacancies = vacancyService.getVacanciesAppliedByUserId(userId);
        return ResponseEntity.ok(vacancies);
    }
}
