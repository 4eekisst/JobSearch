package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dto.ContactDto;
import com.example.jobsearch.dto.EducationDto;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.WorkExperienceDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.exceptions.ResumeNotFoundException;
import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.models.Category;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ResumeServiceImpl implements ResumeService {
    private final CategoryService category;
    private final ResumeDao dao;
    private final UserService userService;
    private final EducationService educationService;
    private final WorkExperienceService workExperienceService;
    private final ContactService contactService;
    @Override
    public List<ResumeDto> getResumeByCategory(String categoryName) throws CategoryNotFoundException {
        Category categoryId = category.getCategoryIdByName(categoryName);
        if (categoryId == null){
            throw new CategoryNotFoundException();
        }
        List<Resume> resumes = dao.getAllResumesByCategoryId(categoryId.getId());
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumes() {
        List<Resume> resumes = dao.getAllResumes();
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getResumeByUserEmail(String email) throws UserNotFoundException {
        try {
            Long id = userService.getUserId(email);
            if (id == null){
                log.error("user with email {} not found",email);
                throw new UserNotFoundException();
            }
            List<Resume> resumes = dao.getAllResumesByUserId(id);
            return getResumeDtos(resumes);
        } catch (ResumeNotFoundException e) {
            log.error("Resumes by user with email:{} not found",email);
        }
        return null;
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        try {
        Resume r = dao.getResumeById(id).orElseThrow(ResumeNotFoundException::new);
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .categoryId(r.getCategoryId())
                .userId(r.getApplicantId())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateDate())
                .build();
        }catch (ResumeNotFoundException e){
            log.error("Resume by id: {} not found",id);
        }
        return null;
    }

    @Override
    public List<ResumeDto> getResumesByName(String name) {
        List<Resume> resumes = dao.getResumesByName(name);
        return getResumeDtos(resumes);
    }

    @Override
    public void createResume(ResumeDto resumeDto) {
        dao.createResume(resumeDto);

        Long resumeId = dao.getResumeIdByName(resumeDto.getName());

        if (resumeDto.getEducations() != null) {
            for (EducationDto educationDto : resumeDto.getEducations()) {
                educationDto.setResumeId(resumeId);
                educationService.createEducation(educationDto);
            }
        }

        if (resumeDto.getWorkExperiences() != null) {
            for (WorkExperienceDto workExperienceDto : resumeDto.getWorkExperiences()) {
                workExperienceDto.setResumeId(resumeId);
                workExperienceService.createWorkExperience(workExperienceDto);
            }
        }

        if (resumeDto.getContacts() != null) {
            for (ContactDto contactDto : resumeDto.getContacts()) {
                contactDto.setResumeId(resumeId);
                contactService.createContact(contactDto);
            }
        }
    }

    @Override
    public void deleteResumeById(Long id) {
        dao.deleteResumeById(id);
    }

    @Override
    public void editResume( ResumeDto resume) {
        dao.editResume(resume);
    }


    @Override
    public List<ResumeDto> getActiveResumes() {
        List<Resume> resumes = dao.getActiveResumes();
        return getResumeDtos(resumes);
    }

    @Override
    public List<ResumeDto> getInactiveResumes() {
        List<Resume> resumes = dao.getInactiveResumes();
        return getResumeDtos(resumes);
    }

    @Override
    public void activateResume(Long id) {
        dao.activateResume(id);
    }

    @Override
    public void deactivateResume(Long id) {
        dao.deactivateResume(id);
    }

    @Override
    public void deleteResumeAndRelatedEntitiesById(Long id) {
        dao.deleteResumeAndRelatedEntitiesById(id);
    }


    private List<ResumeDto> getResumeDtos(List<Resume> resumes) {
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(r -> dtos.add(ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .categoryId(r.getCategoryId())
                .userId(r.getApplicantId())
                .salary(r.getSalary())
                .isActive(r.getIsActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateDate())
                .build()));
        return dtos;
    }
}
