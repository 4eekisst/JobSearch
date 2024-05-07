package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.educations.CreateEducationInfoDto;
import com.example.jobsearch.dto.educations.EducationInfoDto;
import com.example.jobsearch.dto.educations.EducationInfoEditDto;
import com.example.jobsearch.models.EducationInfo;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.repositories.EducationInfoRepository;
import com.example.jobsearch.services.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {
    private final EducationInfoRepository educationInfoRepository;


    @Override
    public void editInfo(EducationInfoEditDto info,Resume resume) {
        EducationInfo edu = EducationInfo.builder()
                .id(info.getId())
                .institution(info.getInstitution())
                .program(info.getProgram())
                .startDate(info.getStartDate().toLocalDate())
                .endDate(info.getEndDate().toLocalDate())
                .degree(info.getDegree())
                .resume(resume)
                .build();
        educationInfoRepository.save(edu);
    }

    @Override
    public boolean deleteEducationInfoById(Long id) {
        if (educationInfoRepository.findById(id).isPresent()) {
            educationInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void createEducationInfo(CreateEducationInfoDto info, Resume resume) {
        EducationInfo educationInfo = EducationInfo.builder()
                .institution(info.getInstitution())
                .degree(info.getDegree())
                .resume(resume)
                .program(info.getProgram())
                .startDate(info.getStartDate().toLocalDate())
                .endDate(info.getEndDate().toLocalDate())
                .build();
           educationInfoRepository.save(educationInfo);
    }


    @Override
    public List<EducationInfoDto> getEducationsInfoByResumeId(Resume resume) {
        List<EducationInfoDto> dtos = new ArrayList<>();
        educationInfoRepository.findByResume(resume).forEach(e -> dtos.add(getEducationInfoDto(e)));
        return dtos;
    }

    @Override
    public List<EducationInfoEditDto> getEducationsInfoForEditByResumeId(Resume resume) {
        List<EducationInfoEditDto> dtos = new ArrayList<>();
        educationInfoRepository.findByResume(resume).forEach(e -> dtos.add(EducationInfoEditDto.builder()
                        .id(e.getId())
                        .degree(e.getDegree())
                        .institution(e.getInstitution())
                        .program(e.getProgram())
                        .startDate(Date.valueOf(e.getStartDate()))
                        .endDate(Date.valueOf(e.getEndDate()))
                .build()));
        return dtos;
    }

    private EducationInfoDto getEducationInfoDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .degree(info.getDegree())
                .program(info.getProgram())
                .institution(info.getInstitution())
                .resumeId(info.getResume().getId())
                .startDate(Date.valueOf(info.getStartDate()))
                .endDate(Date.valueOf(info.getEndDate()))
                .build();
    }
}
