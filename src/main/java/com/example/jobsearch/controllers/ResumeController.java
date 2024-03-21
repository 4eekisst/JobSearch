package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumeController {
    private final ResumeService service;

    @GetMapping()
    public ResponseEntity<List<ResumeDto>> getResumes() {
        var resumes = service.getResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable(name = "id")Long id){
        ResumeDto dto = service.getResumeById(id);
        return dto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume by Id: "+id+" not found")
                :ResponseEntity.ok(dto);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getResumesByName(@PathVariable(name = "name") String name) {
        List<ResumeDto> resumes = service.getResumesByName(name);
        if (resumes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes with name: " + name + " not found");
        }
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable(name = "category") String category) {
        try {
            List<ResumeDto> resumes = service.getResumeByCategory(category.strip());
            return resumes == null ?
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by category: " + category + " not found")
                    : ResponseEntity.ok(resumes);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category: " + category + " not found");
        }
    }

    @GetMapping("/userEmail/{email}")
    public ResponseEntity<?> getResumesByUser(@PathVariable(name = "email") String email) {
        try {
            List<ResumeDto> resumes = service.getResumeByUserEmail(email.strip());
            if (resumes.isEmpty()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by user with email: " + email + " not found");
            }
            return ResponseEntity.ok(resumes);
        }catch (UserNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: "+email+" not found");
        }
    }

    @PostMapping("/create")
    public HttpStatus createResume(@RequestBody ResumeDto resume) {
        service.createResume(resume);
        return HttpStatus.OK;
    }

    @PostMapping("/delete/{id}")
    public HttpStatus deleteResumeById(@PathVariable(name = "id") Long id){
        service.deleteResumeById(id);
        return HttpStatus.OK;
    }

    @PutMapping("/edit")
    public HttpStatus editResume(@RequestBody ResumeDto resume) {
        service.editResume(resume);
        return HttpStatus.OK;
    }

    @GetMapping("/active")
    public ResponseEntity<List<ResumeDto>> getActiveResumes() {
        var resumes = service.getActiveResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<ResumeDto>> getInactiveResumes() {
        var resumes = service.getInactiveResumes();
        return ResponseEntity.ok(resumes);
    }

    @PostMapping("/activate/{id}")
    public HttpStatus activateResume(@PathVariable(name = "id") Long id) {
        service.activateResume(id);
        return HttpStatus.OK;
    }

    @PostMapping("/deactivate/{id}")
    public HttpStatus deactivateResume(@PathVariable(name = "id") Long id) {
        service.deactivateResume(id);
        return HttpStatus.OK;
    }

    @PostMapping("/deleteCascade/{id}")
    public HttpStatus deleteResumeAndRelatedEntitiesById(@PathVariable(name = "id") Long id){
        service.deleteResumeAndRelatedEntitiesById(id);
        return HttpStatus.OK;
    }
}
