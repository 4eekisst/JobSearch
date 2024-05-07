package com.example.jobsearch.dto.ContactType;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactTypeDto {
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String type;
}
