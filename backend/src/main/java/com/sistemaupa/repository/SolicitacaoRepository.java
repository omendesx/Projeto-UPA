package com.sistemaupa.repository;

import com.sistemaupa.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findByProntuarioId(Long prontuarioId);
}
