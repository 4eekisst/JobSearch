package com.example.jobsearch.services;

import com.example.jobsearch.dto.RespondedApplicantDto;

import java.util.List;

public interface RespondedApplicantsService {
    List<RespondedApplicantDto> getAllRespondents();

    List<RespondedApplicantDto> getRespondedApplicantsByVacancyId(Long id);
}
