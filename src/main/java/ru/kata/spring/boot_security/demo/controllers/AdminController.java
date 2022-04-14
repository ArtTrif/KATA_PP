package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.RoleDAOImpl;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
//        список юзеров
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("usersAtr", allUsers);
        model.addAttribute("authUser", user);
//        форма с новым юзером
        User newUser = new User();
        model.addAttribute("addUser", newUser);
        model.addAttribute("roles", roleService.getAllRole());

        return "users";
    }
//    можно удалить
/*    @GetMapping("/new")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("addUser", user);
        model.addAttribute("roles", roleService.getAllRole());
        return "new";
    }*/

    @PostMapping()
    public String saveUser(@ModelAttribute("addUser") User user, @RequestParam String[] roles1) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles1) {
            roleList.add(roleService.roleByName(s));
        }
        System.out.println(roleList);
        user.setAuthorities(roleList);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "/show";
    }
//можно удалить
    /*@GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("userEdit", userService.show(id));
        model.addAttribute("roles", roleService.getAllRole());
        return "/admin";
    }*/

    @PostMapping("{id}")
    public String updateUser(@ModelAttribute("userUpdate") User user, @PathVariable("id") long id, @RequestParam(value = "rolesEdit") String[] rolesEdit) {
        List<Role> roleList = new ArrayList<>();
        for (String s : rolesEdit) {
            roleList.add(roleService.roleByName(s));
        }
        System.out.println(roleList);
        user.setAuthorities(roleList);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
