package com.example.jobsearch.dao;

import com.example.jobsearch.dto.ContactDto;
import com.example.jobsearch.exceptions.ContactNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactDao {
    private final JdbcTemplate template;

    public void createContact(ContactDto contact) {
        String sql = "INSERT INTO CONTACTS_INFO (type_id, resume_id, info_value) VALUES (:typeId, :resumeId, :infoValue)";
        template.update(sql, new MapSqlParameterSource()
                .addValue("typeId", contact.getTypeId())
                .addValue("resumeId", contact.getResumeId())
                .addValue("infoValue", contact.getInfoValue())
        );
    }

    public void deleteContactById(Long id) throws ContactNotFoundException {
        String sql = "DELETE FROM CONTACTS_INFO WHERE id = ?";
        int affectedRows = template.update(sql, id);
        if (affectedRows == 0) {
            throw new ContactNotFoundException("Contact with id " + id + " not found");
        }
    }

    public void editContact(ContactDto contact) throws ContactNotFoundException {
        String sql = "UPDATE CONTACTS_INFO SET type_id = :typeId, resume_id = :resumeId, info_value = :infoValue WHERE id = :id";
        int affectedRows = template.update(sql, new MapSqlParameterSource()
                .addValue("typeId", contact.getTypeId())
                .addValue("resumeId", contact.getResumeId())
                .addValue("infoValue", contact.getInfoValue())
                .addValue("id", contact.getId())
        );
        if (affectedRows == 0) {
            throw new ContactNotFoundException("Contact with id " + contact.getId() + " not found");
        }
    }

    public ContactDto getContactById(Long id) throws ContactNotFoundException {
        String sql = "SELECT * FROM CONTACTS_INFO WHERE id = ?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(ContactDto.class), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ContactNotFoundException("Contact with id " + id + " not found");
        }
    }

    public List<ContactDto> getAllContactsByResumeId(Long resumeId) {
        String sql = "SELECT * FROM CONTACTS_INFO WHERE resume_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(ContactDto.class), resumeId);
    }
}
