package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {

    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private Integer authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;
}
