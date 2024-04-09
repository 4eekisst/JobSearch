package com.example.jobsearch.dao;

import com.example.jobsearch.models.Resume;
import com.example.jobsearch.models.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate template;


    public List<Vacancy> getAllVacancies(int offset, int pageSize) {
        String sql = "SELECT * FROM vacancies LIMIT ? OFFSET ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), pageSize, offset);
    }


    public int getTotalVacanciesCount() {
        String sql = "SELECT COUNT(*) FROM vacancies";
        return template.queryForObject(sql, Integer.class);
    }

    public Optional<Vacancy> getVacancyById(Long id){
        String sql = """
              select * from VACANCIES
              where ID =?
              """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Vacancy.class),id)
                )
        );
    }

    public List<Vacancy> getVacanciesByRespondedApplicantsId(Long id) {
        String sql = """
                SELECT v.*
                FROM vacancies v
                INNER JOIN responded_applicants ra ON v.id = ra.resume_id
                WHERE ra.vacancy_id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getVacancyByCategory(Long id) {
        String sql = """
                SELECT * FROM vacancies\s
                WHERE CATEGORY_ID = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public Long createVacancy(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies (name, description, category_id, author_id, salary, exp_from, exp_to, is_active, created_date, UPDATE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getDescription());
            ps.setLong(3, vacancy.getCategoryId());
            ps.setLong(4, vacancy.getAuthorId());
            ps.setDouble(5, vacancy.getSalary());
            ps.setInt(6, vacancy.getExpFrom());
            ps.setInt(7, vacancy.getExpTo());
            ps.setBoolean(8, vacancy.getIsActive());
            ps.setObject(9, vacancy.getCreatedDate());
            ps.setObject(9, LocalDateTime.now());


            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void deleteVacancyById(Long id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        template.update(sql, id);
    }

    public void updateVacancy(Vacancy vacancy) {
        String sql = "UPDATE vacancies SET name=?, description=?, category_id=?, author_id=?, salary=?, exp_from=?, exp_to=?, is_active=?, UPDATE_DATE=? WHERE id=?";
        template.update(sql, vacancy.getName(), vacancy.getDescription(), vacancy.getCategoryId(), vacancy.getAuthorId(), vacancy.getSalary(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.getIsActive(), vacancy.getUpdateTime(), vacancy.getId());
    }

    public List<Resume> getRespondedApplicantsForVacancy(Long vacancyId) {
        String sql = """
            SELECT r.*
            FROM resumes r
            INNER JOIN responded_applicants ra ON r.id = ra.resume_id
            WHERE ra.vacancy_id = ?
            """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), vacancyId);
    }
}
