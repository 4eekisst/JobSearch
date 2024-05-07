package com.example.jobsearch.dao;


import com.example.jobsearch.models.RespondedApplicant;
import com.example.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
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

@Component
@RequiredArgsConstructor
public class RespondedApplicantsDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParam;



}
