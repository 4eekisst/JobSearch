package com.example.jobsearch.services;

import com.example.jobsearch.dto.EducationDto;
import com.example.jobsearch.exceptions.EducationNotFoundException;

import java.util.List;

public interface EducationService {
    void createEducation(EducationDto educationDto);
    void deleteEducationById(Long id) throws EducationNotFoundException;
    void editEducation(EducationDto educationDto) throws EducationNotFoundException;
    EducationDto getEducationById(Long id) throws EducationNotFoundException;
    List<EducationDto> getAllEducationsByResumeId(Long resumeId) throws EducationNotFoundException;

}
