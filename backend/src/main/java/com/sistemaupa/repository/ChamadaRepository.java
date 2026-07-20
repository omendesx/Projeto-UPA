package com.sistemaupa.repository;

import com.sistemaupa.entity.Chamada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

    Optional<Chamada> findFirstByOrderByDataHoraDesc();

    List<Chamada> findTop5ByOrderByDataHoraDesc();
}
