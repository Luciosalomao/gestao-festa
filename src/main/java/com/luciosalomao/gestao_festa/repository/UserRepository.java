package com.luciosalomao.gestao_festa.repository;

import com.luciosalomao.gestao_festa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Método para buscar o usuário pelo username
    Optional<User> findByUsername(String username);

    // Verificar se username já existe
    boolean existsByUsername(String username);
}
