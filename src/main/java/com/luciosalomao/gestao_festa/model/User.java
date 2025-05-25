package com.luciosalomao.gestao_festa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users") // Boa prática: nome da tabela no plural
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // Carrega os papéis junto com o usuário
    @JoinTable(
            name = "user_roles", // Tabela de junção para a relação ManyToMany
            joinColumns = @JoinColumn(name = "user_id"), // Coluna que referencia o User
            inverseJoinColumns = @JoinColumn(name = "role_id") // Coluna que referencia a Role
    )
    private Set<Role> roles; // Um usuário pode ter múltiplos papéis
}