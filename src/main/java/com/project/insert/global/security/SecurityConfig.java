package com.project.insert.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.global.jwt.auth.JwtAuth;
import com.project.insert.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final JwtAuth jwtAuth;
    private final AuthIdRepository authIdRepository;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(POST, "/api/answer/**").hasAuthority("ADMIN")
                .antMatchers(DELETE, "/api/post/remove/**").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and()
                .apply(new FilterConfig(jwtUtil, jwtAuth));

        return http.build();
    }
}
