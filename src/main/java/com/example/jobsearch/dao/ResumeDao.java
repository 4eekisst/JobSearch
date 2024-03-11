package com.example.jobsearch.dao;

import com.example.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Resume> getAllResumes() {
        String sql = "SELECT * FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> getResumeById(Long id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)));
    }

    public List<Resume> getResumesByApplicantId(Long applicantId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), applicantId);
    }

    public void createResume(Resume resume) {
        String sql = "INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time) " +
                "VALUES (:applicantId, :name, :categoryId, :salary, :isActive, :createdDate, :updateTime)";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("applicantId", resume.getApplicantId())
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.isActive())
                .addValue("createdDate", resume.getCreatedDate())
                .addValue("updateTime", resume.getUpdateTime()));
    }
}
