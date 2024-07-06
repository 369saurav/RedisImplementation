package com.example.redis.infra.database.repository;

import com.example.redis.contract.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String email);
}
