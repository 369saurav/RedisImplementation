package com.example.redis.app.rest.controller;


import com.example.redis.contract.entity.User;
import com.example.redis.domain.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
