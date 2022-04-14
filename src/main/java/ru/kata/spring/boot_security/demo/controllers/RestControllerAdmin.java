package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAnyRole('ADMIN')")
public class RestControllerAdmin {
    private final UserService userService;

    public RestControllerAdmin(UserService userService) {
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
