package com.sistemaupa.service;

import com.sistemaupa.dto.TriagemRequest;
import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.entity.Triagem;
import com.sistemaupa.enums.StatusAtendimento;
import com.sistemaupa.repository.TriagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TriagemService {

    private final TriagemRepository repository;
    private final AtendimentoService atendimentoService;

    public TriagemService(
            TriagemRepository repository,
            AtendimentoService atendimentoService
    ) {
        this.repository = repository;
        this.atendimentoService = atendimentoService;
    }

    @Transactional
    public Triagem criar(TriagemRequest request) {
        if (repository.existsByAtendimentoId(request.atendimentoId())) {
            throw new IllegalArgumentException("Este atendimento já possui triagem.");
        }

        Atendimento atendimento = atendimentoService.buscar(request.atendimentoId());

        Triagem triagem = new Triagem();
        triagem.setAtendimento(atendimento);
        triagem.setSintomas(request.sintomas());
        triagem.setPressaoArterial(request.pressaoArterial());
        triagem.setTemperatura(request.temperatura());
        triagem.setFrequenciaCardiaca(request.frequenciaCardiaca());
        triagem.setNivelDor(request.nivelDor());
        triagem.setObservacao(request.observacao());
        triagem.setClassificacaoRisco(request.classificacaoRisco());
        triagem.setDataHoraTriagem(LocalDateTime.now());

        atendimento.setClassificacaoRisco(request.classificacaoRisco());
        atendimentoService.alterarStatus(
                atendimento.getId(),
                StatusAtendimento.AGUARDANDO_MEDICO
        );

        return repository.save(triagem);
    }

    public Triagem buscarPorAtendimento(Long atendimentoId) {
        return repository.findByAtendimentoId(atendimentoId)
                .orElseThrow(() -> new IllegalArgumentException("Triagem não encontrada."));
    }
}
