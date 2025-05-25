package com.luciosalomao.gestao_festa.repository;

import com.luciosalomao.gestao_festa.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Convidados extends JpaRepository<Convidado, Long> {
}
