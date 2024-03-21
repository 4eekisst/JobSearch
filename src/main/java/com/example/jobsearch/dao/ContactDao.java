package com.example.jobsearch.dao;

import com.example.jobsearch.dto.ContactDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactDao {
    private final JdbcTemplate template;

    public void createContact(ContactDto contact) {
        String sql = "INSERT INTO contacts (type_id, resume_id, info_value) VALUES (:typeId, :resumeId, :infoValue)";
        template.update(sql, new MapSqlParameterSource()
                .addValue("typeId", contact.getTypeId())
                .addValue("resumeId", contact.getResumeId())
                .addValue("infoValue", contact.getInfoValue())
        );
    }
}
