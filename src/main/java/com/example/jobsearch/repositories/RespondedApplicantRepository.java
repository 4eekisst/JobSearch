package com.example.jobsearch.repositories;

import com.example.jobsearch.models.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant,Long> {
    List<RespondedApplicant> findByVacancyId(Long id);


}
