package com.luciosalomao.gestao_festa.repository;
import com.luciosalomao.gestao_festa.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Método para buscar papel pelo nome
    Optional<Role> findByName(String name);
}
