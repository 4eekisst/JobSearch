package com.example.jobsearch.repositories;

import com.example.jobsearch.models.Resume;
import com.example.jobsearch.models.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo,Long> {
    List<WorkExperienceInfo> findByResume(Resume resume);
}
