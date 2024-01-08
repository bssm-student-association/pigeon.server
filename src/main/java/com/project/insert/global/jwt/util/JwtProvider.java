package com.project.insert.global.jwt.util;

import com.project.insert.domain.auth.domain.RefreshToken;
import com.project.insert.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.insert.global.jwt.config.JwtProperties;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

import static com.project.insert.global.jwt.config.JwtConstants.*;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String authId, String role) {
        return jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
    }

    public TokenResponseDto generateToken(String authId,String role) {
        String accessToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId,role,ACCESS_KEY.getMessage(),jwtProperties.getAccessExp());
        String refreshToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, REFRESH_KEY.getMessage() ,jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .id(authId)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .expiredAt(getExpiredTime())
                .build()
        );

        return new TokenResponseDto(accessToken, refreshToken, getExpiredTime());
    }

    private String generateToken(String authId,String role,String type,Long exp) {
        return Jwts.builder()
                .setHeaderParam(TYPE.message, type)
                .claim(ROLE.getMessage(), role)
                .claim(AUTH_ID.getMessage(), authId)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(
                        new Date(System.currentTimeMillis() + exp * 1000)
                )
                .compact();
    }

    private ZonedDateTime getExpiredTime(){
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }
}
