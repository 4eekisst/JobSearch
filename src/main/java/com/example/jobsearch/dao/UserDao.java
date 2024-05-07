package com.example.jobsearch.dao;

import com.example.jobsearch.enums.AccountTypes;
import com.example.jobsearch.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public Optional<UserModel> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(UserModel.class), email)
                )
        );
    }
}
