//package com.example.jobsearch.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//
//public class SecurityConfig {
//
//    private final PasswordEncoder encoder;
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public SecurityConfig(DataSource source, PasswordEncoder encoder) {
//        this.dataSource = source;
//        this.encoder = encoder;
//    }
//
//    private static final String USER_QUERY = "select EMAIL, PASSWORD, ENABLED from USERS where EMAIL =?;";
//    private static final String AUTHORITIES_QUERY = """
//            select ua.EMAIL, a.role from USER_AUTHORITY ua, AUTHORITIES a
//            where ua.AUTHORITY_ID = a.ID
//            and ua.EMAIL = ?;
//            """;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(USER_QUERY)
//                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers(HttpMethod.GET,"vacancies").permitAll()
////                        .requestMatchers(HttpMethod.GET,"employer/**","vacancies/create").hasAuthority("employer")
////                        .requestMatchers(HttpMethod.GET,"/register/submit","/login").permitAll()
////                        .requestMatchers(HttpMethod.POST,"/register/submit","/login").permitAll()
////                        .requestMatchers(HttpMethod.POST,"employer/*","/employer/**").hasAuthority("employer")
////                        .requestMatchers(HttpMethod.POST,"employee/*","/employee/**").hasAuthority("employee")
////                        .requestMatchers(HttpMethod.DELETE,"responses","resumes").hasAuthority("employee")
//                        .anyRequest().permitAll());
//
//        return http.build();
//    }
//}
//
//
//
//
//



package com.example.jobsearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final PasswordEncoder encoder;

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource source, PasswordEncoder encoder) {
        this.dataSource = source;
        this.encoder = encoder;
    }

    private static final String USER_QUERY = "select EMAIL, PASSWORD, ENABLED from USERS where EMAIL =?;";
    private static final String AUTHORITIES_QUERY = """
            SELECT u.email, a.accountType
            FROM users u, accountType a
            WHERE u.email = ? AND u.accountType = a.id;
            """;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/register").permitAll()
//                        .requestMatchers("/profile").authenticated()
//                        .requestMatchers("/").authenticated()
//                        .requestMatchers("/vacancies").permitAll()
//                        .requestMatchers("/resumes/**").hasAuthority("2")
//                        .requestMatchers("/vacancies/**").hasAuthority("1")
//                        .anyRequest().authenticated()
//                );
        return http.build();
    }
}