package com.example.jobsearch.repositories;

import com.example.jobsearch.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> getByEmail(String email);

}
