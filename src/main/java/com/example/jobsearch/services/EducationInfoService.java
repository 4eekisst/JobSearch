package com.example.jobsearch.services;

import com.example.jobsearch.dto.educations.CreateEducationInfoDto;
import com.example.jobsearch.dto.educations.EducationInfoDto;
import com.example.jobsearch.dto.educations.EducationInfoEditDto;
import com.example.jobsearch.models.Resume;

import java.util.List;

public interface EducationInfoService {
    void editInfo(EducationInfoEditDto info,Resume resume);

    boolean deleteEducationInfoById(Long id);

    void createEducationInfo(CreateEducationInfoDto info, Resume resume);


    List<EducationInfoDto> getEducationsInfoByResumeId(Resume resume);

    List<EducationInfoEditDto> getEducationsInfoForEditByResumeId(Resume resume);
}
