package com.example.jobsearch.services;

import com.example.jobsearch.exceptions.UserNotFoundException;
import com.example.jobsearch.models.User;

public interface ProfileService {
    void editProfile(User user) throws UserNotFoundException;
}
