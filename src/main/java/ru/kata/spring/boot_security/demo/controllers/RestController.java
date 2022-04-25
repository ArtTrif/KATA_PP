package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/users")
public class RestController {
    private final UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> userList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") long id) {
        return userService.show(id);
    }


    @PutMapping()
    public void updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @PostMapping()
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}
