package com.example.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserImageDto {
    private MultipartFile file;

    @JsonProperty("user_id")
    private Long userId;
}
