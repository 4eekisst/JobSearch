package com.example.jobsearch.dao;


import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedParameter;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public void createUser(User user) {
        String sql = """
            insert into users(name, surname, age, email, password, phone_number, avatar, enabled, role_id)
            values (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :enabled, :roleId);
            """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("enabled", user.isEnabled())
                .addValue("roleId", user.getRoleId())
        );
    }

    public List<User> getUserByName(String name) {
        String sql = """
                select * from users
                where name ilike ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public Optional<User> getUserByPhone(String phone) {
        String sql = """
                select * from users
                where PHONE_NUMBER ilike ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), phone)
                )
        );
    }

    public List<User> getAllUsersByVacancyId(Long id) {
        String sql = """
                SELECT u.*
                FROM users u
                INNER JOIN responded_applicants r ON u.id = r.resume_id
                WHERE r.vacancy_id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public Optional<User> getUserById(Long id) {
        String sql = """
                select * from users
                where ID = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public void editUser(UserDto userDto) {
        String sql = """
        UPDATE USERS
        SET NAME = :name, SURNAME = :surname, AGE = :age, email = :email, password = :password, 
        phone_number = :phoneNumber, avatar = :avatar, enabled = :enabled, role_id = :roleId
        WHERE id = :id;
        """;
        namedParameter.update(sql, new MapSqlParameterSource()
                .addValue("name", userDto.getName())
                .addValue("surname", userDto.getSurname())
                .addValue("age", userDto.getAge())
                .addValue("email", userDto.getEmail())
                .addValue("password", userDto.getPassword())
                .addValue("phoneNumber", userDto.getPhoneNumber())
                .addValue("avatar", userDto.getAvatar())
                .addValue("enabled", userDto.getEnabled())
                .addValue("roleId", userDto.getRoleId())
                .addValue("id", userDto.getId())
        );
    }


    public List<User> getUsersByAccountType(String accountType) {
        String sql = "SELECT * FROM users WHERE enabled = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), true);
    }

    public List<User> getEmployersByAccountType(String accountType) {
        long employerRoleId = 2;
        String sql = "SELECT * FROM users WHERE role_id = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), employerRoleId);
    }
}
