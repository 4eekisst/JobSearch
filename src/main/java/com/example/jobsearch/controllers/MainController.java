package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final ResumeService resumeService;
    @GetMapping()
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int pageSize) {
        List<ResumeDto> allResumes = resumeService.getResumes(page, pageSize);
        int totalResumes = resumeService.getTotalResumesCount();
        int totalPages = (int) Math.ceil((double) totalResumes / pageSize);

        model.addAttribute("resumes", allResumes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "home";
    }
}
