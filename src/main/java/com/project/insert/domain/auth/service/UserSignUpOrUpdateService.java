package com.project.insert.domain.auth.service;

import com.project.insert.domain.user.exception.UserNotFoundException;
import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.domain.authority.Authority;
import com.project.insert.domain.user.domain.repository.UserRepository;
import com.project.insert.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.insert.global.oauth.exception.BsmAuthIdInvalidClientException;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class UserSignUpOrUpdateService {
    private final BsmOauth bsmOauth;
    private final UserRepository userRepository;

    public User execute(String authId) throws IOException {
        String token;
        BsmResourceResponse resource;

        try {
            token = bsmOauth.getToken(authId);
            resource = bsmOauth.getResource(token);
        } catch (BsmAuthCodeNotFoundException | BsmAuthTokenNotFoundException e) {
            throw UserNotFoundException.EXCEPTION;
        } catch (BsmAuthInvalidClientException e) {
            throw BsmAuthIdInvalidClientException.EXCEPTION;
        }

        return updateOrSignUp(resource);
    }

    @Transactional
    protected User updateOrSignUp(BsmResourceResponse resource) {
        Optional<User> user = userRepository.findByEmail(resource.getEmail());
        if(user.isEmpty()) {
            return saveUser(resource);
        }
        User updateUser = user.get();
        return updateUser.update(resource);
    }

    @Transactional
    protected User saveUser(BsmResourceResponse response) {
        return userRepository.save(
            User.builder()
            .email(response.getEmail())
            .nickname(response.getNickname())
            .authority(Authority.USER)
            .build()
            );
    }
}
