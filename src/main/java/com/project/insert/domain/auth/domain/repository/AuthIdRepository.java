package com.project.insert.domain.auth.domain.repository;

import com.project.insert.domain.auth.domain.AuthId;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthIdRepository extends CrudRepository<AuthId,String> {
    Optional<AuthId> findByAuthId(String authId);
}
