package com.example.jobsearch.controllers;

import com.example.jobsearch.models.User;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {
    private final UserService service;

    @PostMapping("/edit")
    public HttpStatus editProfile(@RequestBody User user) {
        User mercer = user;
        service.editUser(mercer);
        return HttpStatus.OK;
    }
}
