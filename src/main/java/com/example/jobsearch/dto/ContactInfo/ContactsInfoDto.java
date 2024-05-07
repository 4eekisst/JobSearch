package com.example.jobsearch.dto.ContactInfo;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsInfoDto {

    @Size(max = 145)
    private String type;

    @Size(max = 255)
    private String infoValue;
}
