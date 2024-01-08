package com.project.insert.domain.user.service;

import com.project.insert.domain.auth.service.RefreshTokenSaveService;
import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.exception.UserNotFoundException;
import com.project.insert.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.insert.global.config.GoogleAuthProperties;
import com.project.insert.global.feign.GoogleGetTokenClient;
import com.project.insert.global.feign.GoogleInfoClient;
import com.project.insert.global.feign.dto.request.GoogleTokenRequest;
import com.project.insert.global.feign.dto.response.GoogleInfoResponse;
import com.project.insert.global.feign.dto.response.GoogleTokenResponse;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import com.project.insert.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserGoogleLoginService {
    private final UserGetService userGetService;
    private final UserSaveService userSaveService;
    private final GoogleGetTokenClient googleGetTokenClient;
    private final GoogleAuthProperties googleAuthProperties;
    private final GoogleInfoClient googleInfoClient;
    private final JwtProvider jwtProvider;
    private final RefreshTokenSaveService refreshTokenSaveService;

    public TokenResponseDto execute(String code) throws IOException {
        code = code.replace("%2f", "/");

        try {
            GoogleTokenResponse response = googleGetTokenClient.getToken(
                    new GoogleTokenRequest(
                            googleAuthProperties.getClientId(),
                            googleAuthProperties.getClientSecret(),
                            code,
                            googleAuthProperties.getGrantType(),
                            googleAuthProperties.getRedirectUri()
                    )
            );

            String googleAccessToken = response.accessToken();

            if (googleAccessToken == null) {
                throw new IOException();
            }

            GoogleInfoResponse userInfo = googleInfoClient.getUserInfo(googleAccessToken);
            String email = userInfo.email();

            if(email.contains("teacher")) {
                System.out.println("teacher");
            }

            if (userSaveService.userIsEmpty(email)) {
                userSaveService.save(userInfo);
            }

            Long userId = userGetService.findByEmail(email).getId();
            String id = userId.toString();
            TokenResponseDto tokenResponse = jwtProvider.generateToken(id, "TEACHER");

            refreshTokenSaveService.execute(tokenResponse, userId.toString());

            return tokenResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
