package com.example.jobsearch.dao;

import com.example.jobsearch.models.ContactsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;


}
