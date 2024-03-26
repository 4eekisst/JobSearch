package com.example.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final PasswordEncoder encoder;

    private final DataSource source;
    @Autowired
    public SecurityConfig(DataSource source, PasswordEncoder encoder) {
        this.source = source;
        this.encoder = encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String USER_QUERY = "SELECT USERS.EMAIL, USERS.PASSWORD, USERS.ENABLED FROM USERS WHERE EMAIL = ?";
        String USER_ROLE = "SELECT USERS.EMAIL, AUTHORITIES.ROLE FROM USERS JOIN USER_AUTHORITY ON USERS.ID = USER_AUTHORITY.USER_ID JOIN AUTHORITIES ON USER_AUTHORITY.AUTHORITY_ID = AUTHORITIES.ID WHERE USERS.EMAIL = ?";

        auth.jdbcAuthentication()
                .dataSource(source)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(USER_ROLE)
                .passwordEncoder(encoder);
    }

//        @Bean
//        public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
//            http
//                    .authorizeHttpRequests(authorize -> {
//
//                    });
//            return http.build();
//        }
    }




