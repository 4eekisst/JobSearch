package com.example.jobsearch.dao;

import com.example.jobsearch.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate template;

    public Optional<Category> getCategory(String name){
        String sql = """
                select * from CATEGORY
                where name = ?
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        template.query(sql,new BeanPropertyRowMapper<>(Category.class),name)
                )
        );
    }
}