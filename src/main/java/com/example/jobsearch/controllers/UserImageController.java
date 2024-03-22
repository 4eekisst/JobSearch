package com.example.jobsearch.controllers;

import com.example.jobsearch.dto.UserImageDto;
import com.example.jobsearch.services.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class UserImageController {
    private final UserImageService userImageService;

    @PostMapping
    public ResponseEntity<Void> upload(UserImageDto userImageDto) {
        userImageService.upload(userImageDto);
        return ResponseEntity.ok().build();
    }
}
