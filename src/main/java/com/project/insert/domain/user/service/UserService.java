package com.project.insert.domain.user.service;

import com.project.insert.domain.auth.domain.repository.AuthIdRepository;
import com.project.insert.domain.user.exception.UserNotLoginException;
import com.project.insert.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.insert.global.jwt.config.JwtConstants;
import com.project.insert.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserService {
    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;

    public String checkIsLoginUser(String bearer) {
        String authId = jwtUtil.getJwtBody(bearer).get(JwtConstants.AUTH_ID.message).toString();

        authIdRepository.findByAuthId(authId)
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);

        return authId;
    }
}
