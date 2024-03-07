package com.example.jobsearch.models;

import java.time.LocalDate;

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
