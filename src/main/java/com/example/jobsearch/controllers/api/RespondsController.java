package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.RespondedApplicantDto;
import com.example.jobsearch.services.RespondedApplicantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("responsesRest")
@RequiredArgsConstructor
@RequestMapping("api/responses")
public class RespondsController {
    private final RespondedApplicantsService service;

    @GetMapping()
    public ResponseEntity<List<RespondedApplicantDto>> getAllRespondedApplicants(){
        List<RespondedApplicantDto> dtos = service.getAllRespondents();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{vacancyId}")
    public ResponseEntity<?> getRespondedApplicantsToVacancy(@PathVariable Long vacancyId){
        List<RespondedApplicantDto> dtos = service.getRespondedApplicantsByVacancyId(vacancyId);
        if (dtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacancy by id: "+vacancyId+" not found");
        }
        return ResponseEntity.ok(dtos);
    }
}
