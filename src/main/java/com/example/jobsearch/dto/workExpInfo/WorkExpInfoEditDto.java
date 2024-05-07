package com.example.jobsearch.dto.workExpInfo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExpInfoEditDto {
    private Long id;

    @Positive
    @Max(value = 30)
    private Byte years;

    @Size(max = 255)
    private String companyName;

    @Size(max = 255)
    private String position;

    @Size(max = 355)
    private String responsibilities;
}
