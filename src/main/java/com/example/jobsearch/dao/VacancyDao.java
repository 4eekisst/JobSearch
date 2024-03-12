package com.example.jobsearch.dao;

import com.example.jobsearch.models.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getAllVacancies() {
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(Long id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id).stream().findAny();
    }

    public List<Vacancy> getVacanciesAppliedByUserId(Long userId) {
        String sql = "SELECT * FROM vacancies WHERE id IN (SELECT vacancy_id FROM responded_applicant WHERE applicant_id = ?)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
        }
    }

