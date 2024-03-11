package com.example.jobsearch.contoller;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exception.ResumeNotFoundException;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resumes")
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    @GetMapping("/resumes/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        try {
            ResumeDto resumeDto = resumeService.getResumeById(id);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/resumes")
    public ResponseEntity<Void> createResume(@RequestBody Resume resume) {
        resumeService.createResume(resume);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
