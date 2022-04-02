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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String showUser(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", userService.findByEmail(user.getUsername()));

        return "show";
    }

    //пока искал ошибку, нашел кучу вариантов в итоге оказались все рабочие

    /* ВАРИАНТ 2

    private final UserDetailServiceImpl userDetailService;
    @Autowired
    public UserController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }


    @GetMapping()
    public String getUserPage(Model model, Principal principal) {
        model.addAttribute("user", userDetailService.loadUserByUsername(principal.getName()));
        return "show";
    }*/


/* ВАРИАНТ 3
    @GetMapping()
    public String getUserPage(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("user", user);
        return "show";
    }*/

    /* ВАРИАНТ 4

    @GetMapping()
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("user", principal);

        return "show";
    }*/
    /*ВАРИАНТ 5

    @GetMapping()
    public String getUser(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", userService.findByEmail(user.getUsername()));
        return "show";
    }*/

}
