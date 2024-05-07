package com.example.jobsearch.services;

import com.example.jobsearch.dto.educations.EducationInfoForFrontDto;
import com.example.jobsearch.dto.resumes.ResumeCreateDto;
import com.example.jobsearch.dto.resumes.EditResumeDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.users.UserDto;
import com.example.jobsearch.dto.workExpInfo.WorkExpInfoForFrontDto;
import com.example.jobsearch.models.Resume;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name);
    List<ResumeDto> getResumeByCategory(String email,String name);

    List<ResumeDto> getResumesByUserEmail(String user);

    ResumeDto getResumeDto(Resume r);

    ResumeDto getResumeById(Long id);

    Long createResume(ResumeCreateDto resume, Authentication auth);

    Boolean deleteResumeById(Long id);

    void editResume(EditResumeDto editDto, Authentication auth);

    List<ResumeDto> getResumeByName(String rName);

    void changeResumeState(Long id);

    List<WorkExpInfoForFrontDto> getWorkExpInfoByResumeId(Long id);

    List<EducationInfoForFrontDto> getEducationInfoByResumeId(Long id);

    Page<ResumeDto> getResumesPage(Integer page);

    EditResumeDto getResumeForEdit(Long id);

    void updateResume(Long id);

    Resume getResumeModel(Long id);

    List<ResumeDto> getResumes();
}

