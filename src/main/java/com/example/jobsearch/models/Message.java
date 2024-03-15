package com.example.jobsearch.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long respondedApplicants;
    private String content;
    private LocalDateTime timestamp;
}
