package com.example.jobsearch.dto.resumes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import com.example.jobsearch.dto.ContactInfo.ContactsInfoDto;
import com.example.jobsearch.dto.educations.CreateEducationInfoDto;
import com.example.jobsearch.dto.workExpInfo.WorkExpInfoCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeCreateDto {
    @NotBlank @Size(max = 45)
    private String title;

    @NotBlank @Size(max = 45)
    private String categoryName;

    @PositiveOrZero
    private Double salary;

    private List<@Valid WorkExpInfoCreateDto> workExpInfo;
    private List<@Valid CreateEducationInfoDto> educationInfo;
    private List<@Valid ContactsInfoDto> contacts;
}
