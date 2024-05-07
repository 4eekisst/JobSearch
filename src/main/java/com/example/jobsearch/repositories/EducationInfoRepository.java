package com.example.jobsearch.repositories;

import com.example.jobsearch.models.EducationInfo;
import com.example.jobsearch.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInfoRepository extends JpaRepository<EducationInfo,Long> {
    List<EducationInfo> findByResume(Resume resume);
}

