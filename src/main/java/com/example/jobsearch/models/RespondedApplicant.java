package com.example.jobsearch.models;

import lombok.Data;

@Data
public class RespondedApplicant {
    private Long id;
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
}
