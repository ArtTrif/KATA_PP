package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
public class RestControllerUser {
    private final UserService userService;

    public RestControllerUser(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public User getUserByEmail (@AuthenticationPrincipal User user) {
        User userByEmail = userService.findByEmail(user.getUsername());
        return userByEmail;
    }

}
