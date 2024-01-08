package com.project.insert.global.jwt.auth;

import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.domain.user.exception.UserNotLoginException;
import com.project.insert.global.jwt.config.JwtConstants;
import com.project.insert.global.jwt.exception.InvalidJwtException;
import com.project.insert.global.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuth jwtAuth;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        SetAuthenticationInSecurityContext(token);
        filterChain.doFilter(request, response);
    }

    private void SetAuthenticationInSecurityContext(String token){
        if(token != null) {
            Authentication authentication = jwtAuth.authentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
