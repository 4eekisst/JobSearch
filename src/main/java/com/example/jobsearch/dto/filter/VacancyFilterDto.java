package com.example.jobsearch.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyFilterDto {
    protected String category;
    protected Double salary;
    protected String email;
}
