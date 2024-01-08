package com.project.insert.domain.user;

import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.domain.repository.UserRepository;
import com.project.insert.domain.user.exception.UserNotFoundException;
import com.project.insert.domain.user.exception.UserNotLoginException;
import com.project.insert.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        User currentUserWithLogin = SecurityUtil
                .getCurrentUserOrNotLogin();

        return userRepository
                .findById(currentUserWithLogin.getId())
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);
    }
}