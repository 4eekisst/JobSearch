package com.example.jobsearch.services.impl;

import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.models.User;
import com.example.jobsearch.services.ProfileService;
import com.example.jobsearch.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService service;

    @Override
    public void editProfile(User user) {
        service.editUser(UserDto.builder().build());
    }
}
