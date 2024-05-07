package com.example.jobsearch.controllers.mvc;

import com.example.jobsearch.models.Resume;
import com.example.jobsearch.services.CategoryService;
import com.example.jobsearch.services.ContactsInfoService;
import com.example.jobsearch.services.ResumeService;
import com.example.jobsearch.services.UserService;
import jakarta.validation.Valid;
import com.example.jobsearch.dto.CategoryDto;
import com.example.jobsearch.dto.ContactInfo.ContactsInfoWithIdDto;
import com.example.jobsearch.dto.educations.EducationInfoForFrontDto;
import com.example.jobsearch.dto.resumes.EditResumeDto;
import com.example.jobsearch.dto.resumes.ResumeCreateDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.dto.workExpInfo.WorkExpInfoForFrontDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("resume")
public class ResumeViewController {
    private final ResumeService service;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ContactsInfoService contactsInfoService;

    @GetMapping("/{id}")
    public String getResumeById(@PathVariable Long id, Model model){
        ResumeDto resume = service.getResumeById(id);
        Resume resume1 = service.getResumeModel(id);
        UserDto user = userService.getUserByEmail(resume.getUserEmail());
        List<WorkExpInfoForFrontDto> workExpInfos = service.getWorkExpInfoByResumeId(id);
        List<EducationInfoForFrontDto> educationInfo = service.getEducationInfoByResumeId(id);
        List<ContactsInfoWithIdDto> contacts = contactsInfoService.getContactsByResumeId(resume1);
        model.addAttribute("user",user);
        model.addAttribute("resume",resume);
        model.addAttribute("works",workExpInfos);
        model.addAttribute("educations",educationInfo);
        model.addAttribute("contacts",contacts);
        return "resume/resumesInfo";
    }


    @GetMapping("/create")
    public String getResumeCreatePage(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "resume/resumeCreate";
    }

    @PostMapping("/create")
    public String createResume(ResumeCreateDto createDto, Authentication authentication){
        Long id = service.createResume(createDto,authentication);
        return "redirect:/resume/"+id;
    }


    @GetMapping()
    public String getResumes(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page){
        if (page < 0){
            page = 0;
        }
        model.addAttribute("page", page);
        Page<ResumeDto> resumes = service.getResumesPage(page);
        model.addAttribute("resumes",resumes);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "resume/resumeList";
    }

    @GetMapping("/filter")
    public String getVacancyByCategory(@RequestParam String category, Model model, Authentication authentication){
        List<ResumeDto> resumes = service.getResumeByCategory(category);
        model.addAttribute("resumes", resumes);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        return "resume/filteredResumes";
    }

    @GetMapping("/update")
    public String getResumeEditPage(Model model, @RequestParam Long id, Authentication authentication){
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        UserDto user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user",user);
        EditResumeDto resume = service.getResumeForEdit(id);
        model.addAttribute("resume",resume);
        return "resume/editResume";
    }


    @PostMapping("/update")
    public String updateResume(Model model,@RequestParam Long id ,@Valid EditResumeDto editDto, Authentication authentication){
        editDto.setId(id);
        service.editResume(editDto,authentication);
        model.addAttribute("user",userService.getUserByEmail(authentication.getName()));
        return "redirect:/resume/"+editDto.getId();
    }

    @PostMapping("/changeState")
    public String changeActivation(@RequestParam Long id){
        service.changeResumeState(id);
        return "redirect:/profile";
    }
}
