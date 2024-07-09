package com.example.redis.infra.cache.adapter;

import com.example.redis.contract.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RedisService {

    private final ReactiveValueOperations<String, User> valueOps;

    @Autowired
    public RedisService(ReactiveRedisTemplate<String, User> redisTemplate) {
        this.valueOps = redisTemplate.opsForValue();
    }

    public Mono<User> getUser(Long id) {
        return valueOps.get("user:" + id);
    }

    public Mono<Boolean> saveUser(User user) {
        return valueOps.set("user:" + user.getId(), user);
    }
}
