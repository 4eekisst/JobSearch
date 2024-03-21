package com.example.jobsearch.dao;

import com.example.jobsearch.dto.EducationDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
}
