package com.project.insert.domain.auth.service;

import com.project.insert.domain.auth.domain.RefreshToken;
import com.project.insert.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import com.project.insert.global.jwt.exception.RefreshTokenExpiredException;
import com.project.insert.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessTokenRefreshService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public TokenResponseDto execute(final String bearerRefreshToken) {
        if(bearerRefreshToken == null) throw RefreshTokenExpiredException.EXCEPTION;
        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(bearerRefreshToken)
                .orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);
        return getNewAccessTokens(redisRefreshToken);
    }

    private TokenResponseDto getNewAccessTokens(final RefreshToken redisRefreshToken) {
        String newAccessToken = jwtProvider.generateAccessToken(redisRefreshToken.getId(), redisRefreshToken.getRole());

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(redisRefreshToken.getRefreshToken())
                .expiredAt(redisRefreshToken.getExpiredAt())
                .build();
    }
}
