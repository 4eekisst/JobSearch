package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondedApplicantDto {
    private Long id;

    @NotNull(message = "ResumeId cannot be null")
    private Long resumeId;

    @NotNull(message = "VacancyId cannot be null")
    private Long vacancyId;

    private Boolean confirmation;
}
