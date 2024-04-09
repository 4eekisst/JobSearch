package com.example.jobsearch.services;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.exceptions.CategoryNotFoundException;
import com.example.jobsearch.exceptions.UserNotFoundException;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getResumeByCategory(String name) throws CategoryNotFoundException;
    List<ResumeDto> getResumes(int page, int pageSize);
    int getTotalResumesCount();
    List<ResumeDto> getResumeByUserEmail(String user) throws UserNotFoundException;

    ResumeDto getResumeById(Long id);

    void createResume(ResumeDto resume);

    void deleteResumeById(Long id);

    void editResume( ResumeDto resume);
    List<ResumeDto> getResumesByName(String name);

    List<ResumeDto> getActiveResumes();

    List<ResumeDto> getInactiveResumes();

    void activateResume(Long id);

    void deactivateResume(Long id);
    void deleteResumeAndRelatedEntitiesById(Long id);
}
