package com.example.jobsearch.models;

import lombok.Data;

@Data
public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
