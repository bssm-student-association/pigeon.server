package com.project.insert.global.security;

import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.global.error.ExceptionFilter;
import com.project.insert.global.jwt.auth.JwtAuth;
import com.project.insert.global.jwt.auth.JwtFilter;
import com.project.insert.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtUtil jwtUtil;
    private final JwtAuth jwtAuth;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtAuth, jwtUtil);
        ExceptionFilter globalExceptionFilter = new ExceptionFilter();
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
