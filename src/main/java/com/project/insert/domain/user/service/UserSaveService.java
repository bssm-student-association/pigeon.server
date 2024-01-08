package com.project.insert.domain.user.service;

import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.domain.authority.Authority;
import com.project.insert.domain.user.domain.repository.UserRepository;
import com.project.insert.global.feign.dto.response.GoogleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSaveService {

    private final UserRepository userRepository;

    public void save(GoogleInfoResponse response) {
        userRepository.save(
                User.builder()
                        .email(response.email())
                        .nickname(response.name())
                        .authority(Authority.TEACHER)
                        .build()
        );
    }

    public boolean userIsEmpty(String email) {
        return !userRepository.existsByEmail(email);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}