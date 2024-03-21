package com.example.jobsearch.dao;

import com.example.jobsearch.dto.EducationDto;
import com.example.jobsearch.exceptions.EducationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationDao {
    private final JdbcTemplate template;

    public void createEducation(EducationDto education) {
        String sql = "INSERT INTO education (resume_id, institution, program, start_date, end_date, degree) " +
                "VALUES (:resumeId, :institution, :program, :startDate, :endDate, :degree)";
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", education.getResumeId())
                .addValue("institution", education.getInstitution())
                .addValue("program", education.getProgram())
                .addValue("startDate", education.getStartDate())
                .addValue("endDate", education.getEndDate())
                .addValue("degree", education.getDegree())
        );
    }

    public void deleteEducationById(Long id) throws EducationNotFoundException {
        String sql = "DELETE FROM education WHERE id = ?";
        int affectedRows = template.update(sql, id);
        if (affectedRows == 0) {
            throw new EducationNotFoundException("Education with id " + id + " not found");
        }
    }

    public void editEducation(EducationDto education) throws EducationNotFoundException {
        String sql = "UPDATE education SET resume_id = :resumeId, institution = :institution, program = :program, " +
                "start_date = :startDate, end_date = :endDate, degree = :degree WHERE id = :id";
        int affectedRows = template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", education.getResumeId())
                .addValue("institution", education.getInstitution())
                .addValue("program", education.getProgram())
                .addValue("startDate", education.getStartDate())
                .addValue("endDate", education.getEndDate())
                .addValue("degree", education.getDegree())
                .addValue("id", education.getId())
        );
        if (affectedRows == 0) {
            throw new EducationNotFoundException("Education with id " + education.getId() + " not found");
        }
    }

    public EducationDto getEducationById(Long id) throws EducationNotFoundException {
        String sql = "SELECT * FROM education WHERE id = ?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(EducationDto.class), id);
        } catch (EmptyResultDataAccessException e) {
            throw new EducationNotFoundException("Education with id " + id + " not found");
        }
    }

    public List<EducationDto> getAllEducationsByResumeId(Long resumeId) {
        String sql = "SELECT * FROM education WHERE resume_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(EducationDto.class), resumeId);
    }
}
