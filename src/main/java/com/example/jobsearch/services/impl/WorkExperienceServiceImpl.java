package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.WorkExperienceDao;
import com.example.jobsearch.dto.WorkExperienceDto;
import com.example.jobsearch.exceptions.WorkExperienceNotFoundException;
import com.example.jobsearch.services.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceDao workExperienceDao;

    @Override
    public void createWorkExperience(WorkExperienceDto workExperienceDto) {
        workExperienceDao.createWorkExperience(workExperienceDto);
    }

    @Override
    public void deleteWorkExperienceById(Long id) throws WorkExperienceNotFoundException {
        workExperienceDao.deleteWorkExperienceById(id);
    }

    @Override
    public void editWorkExperience(WorkExperienceDto workExperienceDto) throws WorkExperienceNotFoundException {
        workExperienceDao.editWorkExperience(workExperienceDto);
    }

    @Override
    public WorkExperienceDto getWorkExperienceById(Long id) throws WorkExperienceNotFoundException {
        return workExperienceDao.getWorkExperienceById(id);
    }
}
