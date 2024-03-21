package com.example.jobsearch.dao;

import com.example.jobsearch.dto.WorkExperienceDto;
import com.example.jobsearch.exceptions.WorkExperienceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceDao {
    private final JdbcTemplate template;

    public void createWorkExperience(WorkExperienceDto workExperience) {
        String sql = "INSERT INTO WORK_EXPERIENCE_INFO (resume_id, years, company_name, position, responsibilities) " +
                "VALUES (:resumeId, :years, :companyName, :position, :responsibilities)";
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", workExperience.getResumeId())
                .addValue("years", workExperience.getYears())
                .addValue("companyName", workExperience.getCompanyName())
                .addValue("position", workExperience.getPosition())
                .addValue("responsibilities", workExperience.getResponsibilities())
        );
    }

    public void deleteWorkExperienceById(Long id) throws WorkExperienceNotFoundException {
        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE id = ?";
        int affectedRows = template.update(sql, id);
        if (affectedRows == 0) {
            throw new WorkExperienceNotFoundException("Work experience with id " + id + " not found");
        }
    }

    public void editWorkExperience(WorkExperienceDto workExperience) throws WorkExperienceNotFoundException {
        String sql = "UPDATE WORK_EXPERIENCE_INFO SET resume_id = :resumeId, years = :years, company_name = :companyName, " +
                "position = :position, responsibilities = :responsibilities WHERE id = :id";
        int affectedRows = template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", workExperience.getResumeId())
                .addValue("years", workExperience.getYears())
                .addValue("companyName", workExperience.getCompanyName())
                .addValue("position", workExperience.getPosition())
                .addValue("responsibilities", workExperience.getResponsibilities())
                .addValue("id", workExperience.getId())
        );
        if (affectedRows == 0) {
            throw new WorkExperienceNotFoundException("Work experience with id " + workExperience.getId() + " not found");
        }
    }

    public WorkExperienceDto getWorkExperienceById(Long id) throws WorkExperienceNotFoundException {
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO WHERE id = ?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(WorkExperienceDto.class), id);
        } catch (EmptyResultDataAccessException e) {
            throw new WorkExperienceNotFoundException("Work experience with id " + id + " not found");
        }
    }

    public List<WorkExperienceDto> getAllWorkExperiencesByResumeId(Long resumeId) {
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO WHERE resume_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceDto.class), resumeId);
    }
}
