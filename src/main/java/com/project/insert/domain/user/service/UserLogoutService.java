package com.project.insert.domain.user.service;

import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.insert.domain.user.exception.UserNotFoundException;
import com.project.insert.global.jwt.config.JwtConstants;
import com.project.insert.global.jwt.exception.InvalidJwtException;
import com.project.insert.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLogoutService {
    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    public String execute(String bearRefreshToken) {
        String authId = jwtUtil.getJwtBody(bearRefreshToken).get(JwtConstants.AUTH_ID.message).toString();
        authIdRepository.delete(
                authIdRepository.findByAuthId(authId).orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );

        refreshTokenRepository.delete(
                refreshTokenRepository.findById(authId).orElseThrow(() -> InvalidJwtException.EXCEPTION)
        );
        return authId;
    }
}
