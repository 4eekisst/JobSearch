package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.workExpInfo.WorkExpInfoCreateDto;
import com.example.jobsearch.dto.workExpInfo.WorkExpInfoEditDto;
import com.example.jobsearch.dto.workExpInfo.WorkExperienceInfoDto;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.models.WorkExperienceInfo;
import com.example.jobsearch.repositories.WorkExperienceInfoRepository;
import com.example.jobsearch.services.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExpInfoImpl implements WorkExperienceInfoService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    @Override
    public void editInfo(WorkExpInfoEditDto info,Resume resume) {
        WorkExperienceInfo info2 = WorkExperienceInfo.builder()
                .id(info.getId())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .years(info.getYears())
                .resume(resume)
                .build();
        workExperienceInfoRepository.save(info2);
    }

    @Override
    public void createWorkExpInfo(WorkExpInfoCreateDto info, Resume resume) {
        WorkExperienceInfo info2 = WorkExperienceInfo.builder()
                .companyName(info.getCompanyName())
                .resume(resume)
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .years(info.getYears())
                .build();
        workExperienceInfoRepository.save(info2);
    }

    @Override
    public List<WorkExperienceInfoDto> getWorkExperiencesByResumeId(Resume resume) {
        List<WorkExperienceInfo> infos = workExperienceInfoRepository.findByResume(resume);
        List<WorkExperienceInfoDto> dtos = new ArrayList<>();
        infos.forEach( e -> dtos.add(WorkExperienceInfoDto.builder()
                        .id(e.getId())
                        .companyName(e.getCompanyName())
                        .resumeId(e.getResume().getId())
                        .position(e.getPosition())
                        .responsibilities(e.getResponsibilities())
                        .years(e.getYears())
                .build()));
        return dtos;
    }

    @Override
    public List<WorkExpInfoEditDto> getWorkExperiencesForEditByResumeId(Resume resume) {
        List<WorkExperienceInfo> infos = workExperienceInfoRepository.findByResume(resume);
        List<WorkExpInfoEditDto> dtos = new ArrayList<>();
        infos.forEach( e -> dtos.add(WorkExpInfoEditDto.builder()
                .id(e.getId())
                .companyName(e.getCompanyName())
                .position(e.getPosition())
                .responsibilities(e.getResponsibilities())
                .years(e.getYears())
                .build()));
        return dtos;
    }
}
