package com.project.insert.domain.user.service;

import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.domain.auth.service.UserSignUpOrUpdateService;
import com.project.insert.domain.user.domain.User;
import com.project.insert.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.insert.global.jwt.config.JwtProperties;
import com.project.insert.global.jwt.util.JwtProvider;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import com.project.insert.domain.auth.domain.AuthId;
import java.io.IOException;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserLoginService {
    private final UserSignUpOrUpdateService userSignUpOrUpdateService;
    private final JwtProvider jwtProvider;
    private final AuthIdRepository authIdRepository;
    private final JwtProperties jwtProperties;

    public TokenResponseDto execute(String authId) throws IOException {
        User user = userSignUpOrUpdateService.execute(authId);
        saveAuthId(user.getEmail());
        return jwtProvider.generateToken(user.getEmail(), user.getAuthority().name());
    }

    private void saveAuthId(String email) {
        authIdRepository.save(
                AuthId.builder()
                        .id(email)
                        .authId(email)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );
    }
}
