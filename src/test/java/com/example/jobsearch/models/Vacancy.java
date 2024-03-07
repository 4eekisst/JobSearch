package com.example.jobsearch.models;

import java.time.LocalDate;

public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Integer categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer getExpTo;
    private Boolean isActive;
    private Integer authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;
}
