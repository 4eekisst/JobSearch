package com.example.jobsearch.dao;

import com.example.jobsearch.dto.WorkExperienceDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class WorkExperienceDao {
    private final JdbcTemplate template;

    public void createWorkExperience(WorkExperienceDto workExperience) {
        String sql = "INSERT INTO work_experience (resume_id, years, company_name, position, responsibilities) " +
                "VALUES (:resumeId, :years, :companyName, :position, :responsibilities)";
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", workExperience.getResumeId())
                .addValue("years", workExperience.getYears())
                .addValue("companyName", workExperience.getCompanyName())
                .addValue("position", workExperience.getPosition())
                .addValue("responsibilities", workExperience.getResponsibilities())
        );
    }
}
