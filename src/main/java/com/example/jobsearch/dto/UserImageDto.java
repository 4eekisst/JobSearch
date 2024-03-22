package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImageDto {
    @NotNull(message = "File cannot be null")
    private MultipartFile file;

    @NotNull(message = "UserId cannot be null")
    private Long userId;
}
