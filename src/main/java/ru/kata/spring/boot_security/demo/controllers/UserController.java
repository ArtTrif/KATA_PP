package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
//@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*private final UserDetailServiceImpl userDetailService;
    @Autowired
    public UserController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }


    @GetMapping()
    public String getUserPage(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userDetailService.loadUserByUsername(principal.getName()));
        return "user";
    }*/

    /*@GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        User user = userService.show(id);
        model.addAttribute("user", userService.show(id));
        System.out.println(user);
        return "show";
    }*/
    @GetMapping(value = "/user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String showUser(@AuthenticationPrincipal User authUser, Model model) {

        model.addAttribute("user", userService.findByEmail(authUser.getUsername()));

        return "show";
    }
    /*@GetMapping()
    public String showUser(@ModelAttribute("user") User user, Model model) {

        User user1 = userService.show(user.getId());
        System.out.println(user1);
        model.addAttribute("user", user1);

        return "show";
    }*/
   /* @GetMapping()
    public String getUserPage(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("user", user);
        return "user";
    }*/
    /*@GetMapping()
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("user", principal);

        return "user";
    }*/
    /*@GetMapping()
    public String getUser(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", userService.findByEmail(user.getUsername()));
        return "/user";
    }*/
    /*@GetMapping("/user")
    public String getUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("roles", userService.findByEmail(user.getUsername()).getAuthorities());
        model.addAttribute("user", userService.findByEmail(user.getUsername()));
        return "show";
    }*/
}
