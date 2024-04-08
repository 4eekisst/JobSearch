package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumesController {

    private final ResumeService resumeService;

    @GetMapping
    public String viewAllResumes(Model model) {
        List<ResumeDto> allResumes = resumeService.getResumes();
        model.addAttribute("resumes", allResumes);
        return "resumes";
    }

    @GetMapping("/{id}")
    public String viewResumeDetails(@PathVariable("id") Long id, Model model) {
        ResumeDto resume = resumeService.getResumeById(id);
        model.addAttribute("resume", resume);
        return "resume_details";
    }
}
