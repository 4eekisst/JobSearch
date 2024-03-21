package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {
    private Long id;
    private Long resumeId;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}