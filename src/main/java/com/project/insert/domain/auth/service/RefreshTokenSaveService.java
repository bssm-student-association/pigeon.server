package com.project.insert.domain.auth.service;

import com.project.insert.domain.auth.domain.RefreshToken;
import com.project.insert.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import com.project.insert.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenSaveService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public void execute(TokenResponseDto response, String userId) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(userId)
                        .refreshToken(response.getRefreshToken())
                        .ttl(jwtUtil.getRefreshTokenExp())
                        .build()
        );
    }
}