package com.example.jobsearch.services;

import com.example.jobsearch.dto.EducationDto;
import com.example.jobsearch.exceptions.EducationNotFoundException;

public interface EducationService {
    void createEducation(EducationDto educationDto);
    void deleteEducationById(Long id) throws EducationNotFoundException;
    void editEducation(EducationDto educationDto) throws EducationNotFoundException;
    EducationDto getEducationById(Long id) throws EducationNotFoundException;
}
