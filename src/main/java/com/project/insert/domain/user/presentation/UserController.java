package com.project.insert.domain.user.presentation;

import com.project.insert.domain.user.UserFacade;
import com.project.insert.domain.user.domain.User;
import com.project.insert.domain.user.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserFacade userFacade;

    @GetMapping
    public User getUser() {
        return userFacade.getCurrentUser();
    }
}
