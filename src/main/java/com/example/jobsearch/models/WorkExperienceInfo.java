package com.example.jobsearch.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class WorkExperienceInfo {
    private Long id;
    private Long resumeId;
    private Byte years;
    private String companyName;
    private String position;
    private String responsibilities;
}
