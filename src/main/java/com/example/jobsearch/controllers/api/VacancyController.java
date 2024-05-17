package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.vacancies.VacancyCreateDto;
import com.example.jobsearch.dto.vacancies.VacancyDto;
import com.example.jobsearch.dto.vacancies.VacancyEditDto;
import com.example.jobsearch.services.AuthAdapter;
import com.example.jobsearch.services.VacancyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restVacancies")
@RequiredArgsConstructor
@RequestMapping("api/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final AuthAdapter authAdapter;

    @GetMapping()
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        return ResponseEntity.ok(vacancyService.getAllVacancies());
    }


    @GetMapping("company/{email}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCompanyId(@PathVariable String email) {
        List<VacancyDto> vacancies = vacancyService.getAllVacanciesByCompany(email);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("company/active/{email}")
    public ResponseEntity<List<VacancyDto>> getActiveVacanciesByCompanyId(@Email @PathVariable(name = "email") String email) {
        List<VacancyDto> vacancies = vacancyService.getActiveVacanciesByCompany(email);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/vacancy")
    public ResponseEntity<List<VacancyDto>> getVacanciesOfRespondedApplicantEmail(@Valid @Email @RequestParam(name = "email", defaultValue = "") String email) {
        return ResponseEntity.ok(vacancyService.getVacanciesByRespondedApplicant(email.strip()));
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<VacancyDto>> getVacancies(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "filter",defaultValue = "null") String filter){
        page = page > 0 ? page - 1 : 0;
        Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesPage(page, filter);
        return ResponseEntity.ok(vacancies);
    }

//    @GetMapping("/{email}/paging")
//    public ResponseEntity<Page<VacancyDto>> getVacanciesByProfile(@RequestParam(name = "page", defaultValue = "0") Integer page, @PathVariable String email){
//        page = page > 0 ? page - 1 : 0;
//        Page<VacancyDto> vacancies = vacancyService.getActiveVacanciesByCompany(page,email);
//        return ResponseEntity.ok(vacancies);
//    }

    @GetMapping("/category")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@Valid @RequestParam(name = "category", defaultValue = "") String category) {
        String categoryStriped = category.strip();
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(categoryStriped));
    }

    @PostMapping()
    public ResponseEntity<VacancyDto> createVacancy(@Valid VacancyCreateDto vacancy, Authentication authentication) {
        String email = authAdapter.getAuthUser().getEmail();
        Long id = vacancyService.createVacancyAndReturnId(vacancy,authentication);
        return ResponseEntity.ok(vacancyService.getVacancyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable(name = "id") Long id) {
        if (vacancyService.deleteVacancyById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<VacancyDto> editVacancy(@RequestBody VacancyEditDto vacancy, Authentication authentication) {
        String email = authAdapter.getAuthUser().getEmail();
        vacancyService.editVacancy(vacancy, authentication);
        return ResponseEntity.ok(vacancyService.getVacancyById(vacancy.getId()));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> changeVacancyState(@PathVariable(name = "id") Long id) {
        vacancyService.changeVacancyState(id);
        return ResponseEntity.ok(vacancyService.getVacancyById(id));
    }
}
