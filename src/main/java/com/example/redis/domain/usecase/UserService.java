package com.example.redis.domain.usecase;



import com.example.redis.contract.entity.User;
import com.example.redis.infra.cache.adapter.RedisService;
import com.example.redis.infra.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    public Mono<User> getUserById(Long id) {
        return redisService.getUser(id)
                .switchIfEmpty(userRepository.findById(id)
                        .flatMap(userEntity -> redisService.saveUser(userEntity)
                                .thenReturn(userEntity)));
    }

    public Mono<User> saveUser(User user) {
        return userRepository.save(user)
                .flatMap(savedUserEntity -> redisService.saveUser(user)
                        .thenReturn(user));
    }
}
