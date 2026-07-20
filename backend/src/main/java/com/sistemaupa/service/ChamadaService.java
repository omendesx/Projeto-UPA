package com.sistemaupa.service;

import com.sistemaupa.dto.ChamadaRequest;
import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.entity.Chamada;
import com.sistemaupa.enums.StatusAtendimento;
import com.sistemaupa.repository.ChamadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChamadaService {

    private final ChamadaRepository repository;
    private final AtendimentoService atendimentoService;

    public ChamadaService(
            ChamadaRepository repository,
            AtendimentoService atendimentoService
    ) {
        this.repository = repository;
        this.atendimentoService = atendimentoService;
    }

    @Transactional
    public Chamada criar(ChamadaRequest request) {
        Atendimento atendimento = atendimentoService.buscar(request.atendimentoId());

        StatusAtendimento novoStatus =
                request.destino().equalsIgnoreCase("TRIAGEM")
                        ? StatusAtendimento.CHAMADO_TRIAGEM
                        : StatusAtendimento.CHAMADO_CONSULTORIO;

        atendimentoService.alterarStatus(atendimento.getId(), novoStatus);

        Chamada chamada = new Chamada();
        chamada.setAtendimento(atendimento);
        chamada.setDestino(request.destino().toUpperCase());
        chamada.setSala(request.sala());
        chamada.setDataHora(LocalDateTime.now());

        return repository.save(chamada);
    }

    public Chamada ultima() {
        return repository.findFirstByOrderByDataHoraDesc().orElse(null);
    }

    public List<Chamada> recentes() {
        return repository.findTop5ByOrderByDataHoraDesc();
    }
}
