package com.example.jobsearch.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long authorId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
