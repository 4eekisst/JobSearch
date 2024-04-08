package com.example.jobsearch.controllers.api;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.models.User;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("profileRest")
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileController {
    private final UserService service;

    @PostMapping("/edit")
    public HttpStatus editProfile(@RequestBody UserDto userDto) {
        UserDto mercer = userDto;
        service.editUser(mercer);
        return HttpStatus.OK;
    }
}
