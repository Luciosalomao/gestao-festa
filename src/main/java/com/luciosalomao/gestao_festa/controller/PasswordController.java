package com.luciosalomao.gestao_festa.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordController {

    private final PasswordEncoder passwordEncoder;

    // Injeção via construtor
    public PasswordController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/generate-password")
    public String showForm() {
        return "generate-password";
    }

    @PostMapping("/generate-password")
    public String generatePassword(@RequestParam String plainPassword, Model model) {
        String hashedPassword = passwordEncoder.encode(plainPassword);
        model.addAttribute("hashedPassword", hashedPassword);
        return "generate-password";
    }
}
