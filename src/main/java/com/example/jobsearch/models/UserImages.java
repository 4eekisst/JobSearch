package com.example.jobsearch.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserImages {
    private Long id;
    private Long userId;
    private String fileName;
}
