package com.luciosalomao.gestao_festa.controller;

import com.luciosalomao.gestao_festa.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Arquivo HTML: src/main/resources/templates/register.html
    }

    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        try {
            userService.registerUser(username, password);
            return "redirect:/login?registered";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }
}
