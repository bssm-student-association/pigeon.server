package com.project.insert.domain.auth.presentation;

import com.project.insert.domain.auth.service.AccessTokenRefreshService;
import com.project.insert.domain.user.service.UserLoginService;
import com.project.insert.domain.user.service.UserLogoutService;
import com.project.insert.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserLoginService userLoginService;
    private final UserLogoutService userLogoutService;
    private final AccessTokenRefreshService accessTokenRefreshService;

    @PostMapping("/login")
    public TokenResponseDto userSignup(@RequestHeader("authCode") String code) throws IOException {
        return ResponseEntity.ok(userLoginService.execute(code)).getBody();
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> userLogout(@RequestHeader("refresh_token") String refreshToken) {
        return ResponseEntity.ok(userLogoutService.execute(refreshToken));
    }

    @PutMapping("/refresh")
    public TokenResponseDto refreshAccessToken(@RequestHeader("refresh_token") String refreshToken) {
        return ResponseEntity.ok(accessTokenRefreshService.execute(refreshToken)).getBody();
    }
}
