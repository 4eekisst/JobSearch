package com.example.jobsearch.dto;

import com.example.jobsearch.dto.resumes.ResumeDto;
import com.example.jobsearch.dto.vacancies.VacancyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;
    private ResumeDto resume;
    private VacancyDto vacancy;
    private Boolean confirmation;
}
