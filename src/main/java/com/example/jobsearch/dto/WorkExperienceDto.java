package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {
    private Long id;
    private Long resumeId;

    @NotNull(message = "Years cannot be null")
    private Byte years;

    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 255, message = "Company name cannot be longer than 255 characters")
    private String companyName;

    @NotBlank(message = "Position cannot be blank")
    @Size(max = 255, message = "Position cannot be longer than 255 characters")
    private String position;

    private String responsibilities;
}
