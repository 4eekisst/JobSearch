package com.example.jobsearch.repositories;

import com.example.jobsearch.models.ContactsInfo;
import com.example.jobsearch.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactsInfo,Long> {
    List<ContactsInfo> findByResume(Resume resume);
}
