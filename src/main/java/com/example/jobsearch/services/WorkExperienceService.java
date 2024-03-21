package com.example.jobsearch.services;

import com.example.jobsearch.dto.WorkExperienceDto;
import com.example.jobsearch.exceptions.WorkExperienceNotFoundException;

public interface WorkExperienceService {
    void createWorkExperience(WorkExperienceDto workExperienceDto);
    void deleteWorkExperienceById(Long id) throws WorkExperienceNotFoundException;
    void editWorkExperience(WorkExperienceDto workExperienceDto) throws WorkExperienceNotFoundException;
    WorkExperienceDto getWorkExperienceById(Long id) throws WorkExperienceNotFoundException;
}
