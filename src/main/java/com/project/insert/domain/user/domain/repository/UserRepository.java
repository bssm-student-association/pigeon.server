package com.project.insert.domain.user.domain.repository;

import com.project.insert.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String name);

    boolean existsByEmail(String email);
}
