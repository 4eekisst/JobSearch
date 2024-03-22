package com.example.jobsearch.dao;

import com.example.jobsearch.models.UserImage;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UserImageDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(UserImage userImage) {
        String sql = """
                insert into users_images(user_id, file_name)
                values (?, ?);
                """;
        jdbcTemplate.update(sql, userImage.getUserId(), userImage.getFileName());
    }

}
