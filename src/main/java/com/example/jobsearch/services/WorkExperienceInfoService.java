package com.example.jobsearch.services;

import com.example.jobsearch.dto.workExpInfo.WorkExpInfoCreateDto;
import com.example.jobsearch.dto.workExpInfo.WorkExpInfoEditDto;
import com.example.jobsearch.dto.workExpInfo.WorkExperienceInfoDto;
import com.example.jobsearch.models.Resume;

import java.util.List;

public interface WorkExperienceInfoService {
    void editInfo(WorkExpInfoEditDto info,Resume resume);


    void createWorkExpInfo(WorkExpInfoCreateDto info, Resume resume);


    List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Resume resume);

    List<WorkExpInfoEditDto> getWorkExperiencesForEditByResumeId(Resume resume);
}
