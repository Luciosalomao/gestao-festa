package com.luciosalomao.gestao_festa.service;

import com.luciosalomao.gestao_festa.model.User;
import com.luciosalomao.gestao_festa.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service // Marca como um componente de serviço do Spring
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Converte os papéis (Roles) do usuário para GrantedAuthority do Spring Security
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // Retorna um objeto UserDetails do Spring Security
        // As senhas PRECISAM estar criptografadas no banco de dados
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // A senha já deve estar criptografada no banco
                authorities
        );
    }
}