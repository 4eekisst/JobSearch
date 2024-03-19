package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.exceptions.VacancyNotFoundException;
import com.example.jobsearch.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacancyController {
    private final VacancyService service;

    @GetMapping()
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(service.getAllVacancies());
    }

    @GetMapping("/vacancy")
    public ResponseEntity<?> getVacanciesOfRespondedApplicantEmail(@RequestParam(name = "email",defaultValue = "") String email){
        try {
            return ResponseEntity.ok(service.getVacanciesOfRespondedApplicant(email.strip()));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getVacanciesOfCategory(@RequestParam(name = "category",defaultValue = "") String category){
        try {
            String categoryStriped = category.strip();
            return ResponseEntity.ok(service.getVacanciesOfCategory(categoryStriped));
        }catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        VacancyDto createdVacancyDto = service.createVacancy(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVacancyDto);
    }


    @DeleteMapping("/{vacancyId}")
    public ResponseEntity<?> deleteVacancy(@PathVariable Long vacancyId) {
        service.deleteVacancy(vacancyId);
        return ResponseEntity.ok("Vacancy deleted successfully");
    }

    @PutMapping("/{vacancyId}")
    public ResponseEntity<?> updateVacancy(@PathVariable Long vacancyId, @RequestBody VacancyDto updatedVacancy) {
        VacancyDto updatedDto;
        try {
            updatedDto = service.updateVacancy(vacancyId, updatedVacancy);
        } catch (VacancyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/{vacancyId}/applicants")
    public ResponseEntity<?> getRespondedApplicants(@PathVariable Long vacancyId) {
        List<ResumeDto> respondedApplicants = service.getRespondedApplicants(vacancyId);
        if (respondedApplicants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No applicants responded to vacancy with id: " + vacancyId);
        }
        return ResponseEntity.ok(respondedApplicants);
    }
}
