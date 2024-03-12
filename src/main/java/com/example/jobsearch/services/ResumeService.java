package com.example.jobsearch.services;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exception.ResumeNotFoundException;
import com.example.jobsearch.models.Resume;

import java.util.List;

public interface ResumeService {

    List<ResumeDto> getAllResumes();

    ResumeDto getResumeById(Long id) throws ResumeNotFoundException;

    List<ResumeDto> getResumesByApplicantId(Long applicantId);

    void createResume(Resume resume);

    List<ResumeDto> getResumesByCategory(Integer categoryId);
}
