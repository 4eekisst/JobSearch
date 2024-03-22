package com.example.jobsearch.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    private String name;

    @NotNull(message = "UserId cannot be null")
    private Long userId;

    @NotNull(message = "CategoryId cannot be null")
    private Long categoryId;

    private Double salary;

    private Boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updateTime;

    @Valid
    private List<EducationDto> educations;

    @Valid
    private List<WorkExperienceDto> workExperiences;

    @Valid
    private List<ContactDto> contacts;
}
