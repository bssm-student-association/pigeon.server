package com.project.insert.domain.auth.domain.repository;

import com.project.insert.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken,String> {
    Optional<RefreshToken> findById(String authId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
