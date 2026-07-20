package com.sistemaupa.repository;

import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.enums.StatusAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findByStatusOrderByDataHoraEntradaAsc(StatusAtendimento status);
}
