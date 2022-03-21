package ru.arttrif.springPP321.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.arttrif.springPP321.models.User;
import ru.arttrif.springPP321.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("usersAtr", allUsers);
        return "users";
    }
    @GetMapping("/new")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("addUser", user);
        return "new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("addUser") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "/show";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("userEdit", userService.show(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }



}
