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
public class ContactDto {
    private Long id;

    @NotNull(message = "TypeId cannot be null")
    private Long typeId;

    @NotNull(message = "ResumeId cannot be null")
    private Long resumeId;

    @NotBlank(message = "InfoValue cannot be blank")
    @Size(max = 255, message = "InfoValue cannot be longer than 255 characters")
    private String infoValue;
}
