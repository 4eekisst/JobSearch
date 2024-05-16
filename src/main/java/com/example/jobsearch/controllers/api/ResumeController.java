package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.resumes.EditResumeDto;
import com.example.jobsearch.dto.resumes.ResumeCreateDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.services.AuthAdapter;
import com.example.jobsearch.services.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restResumes")
@RequiredArgsConstructor
@RequestMapping("api/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    private final AuthAdapter authAdapter;

    @GetMapping()
    public ResponseEntity<List<ResumeDto>> getResumes() {
        var resumes = resumeService.getResumes();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<ResumeDto>> getResumes(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        page = page > 0 ? page - 1 : 0;
        Page<ResumeDto> resumes = resumeService.getResumesPage(page);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/searchByName/{resumeName}")
    public ResponseEntity<List<ResumeDto>> getResumeByName(@Valid @PathVariable(name = "resumeName") String rName) {
        List<ResumeDto> dto = resumeService.getResumeByName(rName);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@Valid @PathVariable(name = "id") Long id) {
        ResumeDto dto = resumeService.getResumeById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@Valid @PathVariable(name = "category") String category) {
        List<ResumeDto> resumes = resumeService.getResumeByCategory(category.strip());
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/userEmail/{email}")
    public ResponseEntity<List<ResumeDto>> getResumesByUser(@Valid @PathVariable(name = "email") String email) {
        List<ResumeDto> resumes = resumeService.getResumesByUserEmail(email.strip());
        return ResponseEntity.ok(resumes);
    }

    @PostMapping()
    public ResponseEntity<ResumeDto> createResume(@Valid @RequestBody ResumeCreateDto resume, Authentication authentication) {
        resumeService.createResume(resume, authentication);
        Long id = resumeService.createResume(resume, authentication);
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@Valid @PathVariable(name = "id") Long id) {
        if (resumeService.deleteResumeById(id)) {
            return ResponseEntity.ok(resumeService.getResumes());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resumes by id: " + id + " not found");
    }

    @PutMapping()
    public ResponseEntity<ResumeDto> editResume(@Valid @RequestBody EditResumeDto resume, Authentication authentication) {
        String email = authAdapter.getAuthUser().getEmail();
        resumeService.editResume(resume,authentication);
        return ResponseEntity.ok(resumeService.getResumeById(resume.getId()));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResumeDto> changeResumeState(@Valid @PathVariable(name = "id") Long id) {
        resumeService.changeResumeState(id);
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }
}
