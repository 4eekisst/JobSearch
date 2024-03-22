package com.example.jobsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private Long id;
    private Long resumeId;

    @NotBlank(message = "Institution cannot be blank")
    @Size(max = 255, message = "Institution cannot be longer than 255 characters")
    private String institution;

    @NotBlank(message = "Program cannot be blank")
    @Size(max = 255, message = "Program cannot be longer than 255 characters")
    private String program;

    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    private String degree;
}
