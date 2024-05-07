package com.example.jobsearch.repositories;

import com.example.jobsearch.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessageByRespApplicant_Id(Long respApplId);

    @Query(value = """ 
            select * from MESSAGES
            where id > ? and RESPONDED_APPLICANT_ID = ?
            """, nativeQuery = true)
    List<Message> findMessageByIdAndRespApplId(Long id, Long respondedApplicant);
}
