package com.example.jobsearch.services.impl;

import com.example.jobsearch.dao.EducationDao;
import com.example.jobsearch.dto.EducationDto;
import com.example.jobsearch.exceptions.EducationNotFoundException;
import com.example.jobsearch.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationDao educationDao;

    @Override
    public void createEducation(EducationDto educationDto) {
        educationDao.createEducation(educationDto);
    }

    @Override
    public void deleteEducationById(Long id) throws EducationNotFoundException {
        educationDao.deleteEducationById(id);
    }

    @Override
    public void editEducation(EducationDto educationDto) throws EducationNotFoundException {
        educationDao.editEducation(educationDto);
    }

    @Override
    public EducationDto getEducationById(Long id) throws EducationNotFoundException {
        return educationDao.getEducationById(id);
    }
}
