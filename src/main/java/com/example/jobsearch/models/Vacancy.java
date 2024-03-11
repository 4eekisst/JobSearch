package com.example.jobsearch.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

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
