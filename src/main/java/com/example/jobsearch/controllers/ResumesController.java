package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumesController {

    private final ResumeService resumeService;
    private final CategoryService categoryService;

    @GetMapping("/create_resume")
    public String showResumeForm(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("resumeDto", new ResumeDto());
        return "create_resume";
    }

    @GetMapping()
    public String viewAllResumes(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int pageSize) {
        List<ResumeDto> allResumes = resumeService.getResumes(page, pageSize);
        int totalResumes = resumeService.getTotalResumesCount();
        int totalPages = (int) Math.ceil((double) totalResumes / pageSize);

        model.addAttribute("resumes", allResumes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "resumes";
    }


    @GetMapping("/{id}")
    public String viewResumeDetails(@PathVariable("id") Long id, Model model) {
        ResumeDto resume = resumeService.getResumeById(id);
        model.addAttribute("resume", resume);
        return "resume_details";
    }
}
