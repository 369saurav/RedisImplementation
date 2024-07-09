package com.example.redis.domain.usecase;



import com.example.redis.contract.entity.User;
import com.example.redis.infra.cache.adapter.RedisService;
import com.example.redis.infra.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    @Cacheable(value = "users", key = "#id")
    public Mono<User> getUserById(Long id) {
        return redisService.getUser(id);
    }

    @CachePut(value = "users", key = "#user.id")
    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }
}
