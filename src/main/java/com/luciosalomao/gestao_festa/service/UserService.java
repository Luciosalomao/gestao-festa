package com.luciosalomao.gestao_festa.service;

import com.luciosalomao.gestao_festa.model.Role;
import com.luciosalomao.gestao_festa.model.User;
import com.luciosalomao.gestao_festa.repository.RoleRepository;
import com.luciosalomao.gestao_festa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Usuário já existe!");
        }

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role padrão não encontrada"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles(Collections.singleton(defaultRole));

        User savedUser = userRepository.save(user);
        System.out.println("Usuário salvo: " + savedUser.getUsername());
        return savedUser;
    }
}
