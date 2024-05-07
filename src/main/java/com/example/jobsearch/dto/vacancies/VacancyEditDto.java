package com.example.jobsearch.dto.vacancies;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyEditDto {
    private Long id;
    @NotBlank @Size(max = 30)
    private String name;
    @NotBlank @Size(max = 245,message = "description should contains not more then 245 characters")
    private String description;
    @NotBlank @Size(max = 30)
    private String category;
    private Double salary;
    @Max(value = 40)
    @Min(value = 0)
    private Integer expFrom;
    @Min(value = 0,message = "enter only rational numbers")
    @Max(value = 50,message = "enter only rational numbers")
    private Integer expTo;
}
