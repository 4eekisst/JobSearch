package com.example.jobsearch.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class Resume {
    private Long id;
    private Integer applicantId;
    private String name;
    private Integer categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
}
