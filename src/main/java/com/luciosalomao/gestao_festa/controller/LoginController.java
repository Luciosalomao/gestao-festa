package com.luciosalomao.gestao_festa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login"; // nome do template, ex: login.html no templates Thymeleaf
    }
}
