package com.sistemaupa.repository;

import com.sistemaupa.entity.Triagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TriagemRepository extends JpaRepository<Triagem, Long> {

    Optional<Triagem> findByAtendimentoId(Long atendimentoId);

    boolean existsByAtendimentoId(Long atendimentoId);
}
