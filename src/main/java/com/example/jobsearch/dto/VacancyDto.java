package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "CategoryId cannot be null")
    private Long categoryId;

    @NotNull(message = "AuthorId cannot be null")
    private Long authorId;

    private Double salary;

    private Integer expFrom;

    private Integer expTo;

    private Boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;
}
