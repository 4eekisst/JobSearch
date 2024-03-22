package com.example.jobsearch.dao;

import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.models.Resume;
import com.example.jobsearch.exceptions.ResumeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<Resume> getAllResumesByCategoryId(Long id) {
        String sql = """
                SELECT * FROM RESUMES
                WHERE CATEGORY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public List<Resume> getAllResumes() {
        String sql = """
                SELECT * FROM RESUMES
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getAllResumesByUserId(Long id) throws ResumeNotFoundException {
        String sql = """
                SELECT * FROM RESUMES
                WHERE APPLICANT_ID = ?
                """;
        List<Resume> resumes = template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
        if (resumes.isEmpty()) {
            throw new ResumeNotFoundException("Resumes by this user not found!");
        }
        return resumes;
    }

    public Optional<Resume> getResumeById(Long id) {
        String sql = """
                SELECT * FROM RESUMES
                WHERE ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }

    public List<Resume> getResumesByName(String name) {
        String sql = "SELECT * FROM RESUMES WHERE NAME LIKE ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), "%" + name + "%");
    }

    public Long getResumeIdByName(String name) {
        String sql = "SELECT ID FROM RESUMES WHERE NAME = ?";
        return template.queryForObject(sql, Long.class, name);
    }

    public void createResume(ResumeDto resume) {
        String sql = """
                INSERT INTO RESUMES(NAME, CATEGORY_ID, APPLICANT_ID, SALARY, IS_ACTIVE, CREATED_DATE, UPDATE_DATE)
                VALUES (:name, :categoryId, :applicantId, :salary, :isActive, :createdDate, :updateTime)
                """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("applicantId", resume.getUserId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", true)
                .addValue("createdDate", LocalDateTime.now())
                .addValue("updateTime", LocalDateTime.now())
        );
    }

    public void deleteResumeById(Long id) {
        String sql = """
                DELETE FROM RESUMES WHERE ID = :id
                """;
        namedParameter.update(sql,new MapSqlParameterSource().addValue("id",id));
    }

    public void editResume(ResumeDto resume) {
        String sql = """
            UPDATE RESUMES
            SET NAME = :name, CATEGORY_ID = :categoryId, SALARY = :salary, IS_ACTIVE = :isActive, UPDATE_DATE = :updateTime
            WHERE ID = :id
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name",resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.getIsActive())
                .addValue("updateTime", resume.getUpdateTime())
                .addValue("id",resume.getId())
        );
    }

    public void activateResume(Long id) {
        String sql = "UPDATE RESUMES SET IS_ACTIVE = TRUE WHERE ID = ?";
        template.update(sql, id);
    }

    public void deactivateResume(Long id) {
        String sql = "UPDATE RESUMES SET IS_ACTIVE = FALSE WHERE ID = ?";
        template.update(sql, id);
    }

    public List<Resume> getActiveResumes() {
        String sql = "SELECT * FROM RESUMES WHERE IS_ACTIVE = TRUE";
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> getInactiveResumes() {
        String sql = "SELECT * FROM RESUMES WHERE IS_ACTIVE = FALSE";
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public void deleteResumeAndRelatedEntitiesById(Long id) {
        deleteContactsByResumeId(id);
        deleteWorkExperiencesByResumeId(id);
        deleteEducationsByResumeId(id);
        deleteResumeById(id);
    }

    private void deleteContactsByResumeId(Long resumeId) {
        String sql = "DELETE FROM CONTACTS_INFO WHERE resume_id = ?";
        template.update(sql, resumeId);
    }

    private void deleteWorkExperiencesByResumeId(Long resumeId) {
        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE resume_id = ?";
        template.update(sql, resumeId);
    }

    private void deleteEducationsByResumeId(Long resumeId) {
        String sql = "DELETE FROM EDUCATION_INFO WHERE resume_id = ?";
        template.update(sql, resumeId);
    }

}
