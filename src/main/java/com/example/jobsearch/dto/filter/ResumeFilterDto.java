package com.example.jobsearch.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeFilterDto {
    protected String category;
    protected Double salary;
    protected String email;
    private String uni;
    private String companyWork;
}
