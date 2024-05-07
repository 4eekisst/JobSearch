package com.example.jobsearch.dao;

import com.example.jobsearch.dto.workExpInfo.WorkExpInfoCreateDto;
import com.example.jobsearch.models.EducationInfo;
import com.example.jobsearch.models.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkExpInfoDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

}
