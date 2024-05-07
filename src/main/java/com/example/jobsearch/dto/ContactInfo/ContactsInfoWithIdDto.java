package com.example.jobsearch.dto.ContactInfo;

import jakarta.validation.constraints.Pattern;
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
public class ContactsInfoWithIdDto {
    @Positive
    private Long id;
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String type;
    @Size(max = 255)
    private String infoValue;
}
