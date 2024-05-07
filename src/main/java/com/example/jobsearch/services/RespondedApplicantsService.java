package com.example.jobsearch.services;

import com.example.jobsearch.dto.RespondedApplicantDto;
import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.models.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<ResumeDto> getRespondedApplicantsByVacancyId(Long id);

    void applyToVacancy(Long resumeId, Long vacancyId);


    Long ifThereResumeIdAndVacancyId(List<ResumeDto> resume,Long vacancyId);

    RespondedApplicantDto getRespondedApplicantById(Long respId);

    Integer getRespondentsSizeByEmployer(String email);

    RespondedApplicant getRespondedApplicantModelById(Long respApplId);
}
