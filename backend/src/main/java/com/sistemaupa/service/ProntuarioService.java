package com.sistemaupa.service;

import com.sistemaupa.dto.ProntuarioRequest;
import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.entity.Prontuario;
import com.sistemaupa.enums.StatusAtendimento;
import com.sistemaupa.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProntuarioService {

    private final ProntuarioRepository repository;
    private final AtendimentoService atendimentoService;

    public ProntuarioService(
            ProntuarioRepository repository,
            AtendimentoService atendimentoService
    ) {
        this.repository = repository;
        this.atendimentoService = atendimentoService;
    }

    @Transactional
    public Prontuario criar(ProntuarioRequest request) {
        if (repository.existsByAtendimentoId(request.atendimentoId())) {
            throw new IllegalArgumentException("Este atendimento já possui prontuário.");
        }

        Atendimento atendimento = atendimentoService.buscar(request.atendimentoId());

        Prontuario prontuario = new Prontuario();
        prontuario.setAtendimento(atendimento);
        prontuario.setObservacaoMedica(request.observacaoMedica());
        prontuario.setHipoteseClinica(request.hipoteseClinica());
        prontuario.setConduta(request.conduta());
        prontuario.setDataHoraAtendimento(LocalDateTime.now());

        atendimentoService.alterarStatus(
                atendimento.getId(),
                StatusAtendimento.EM_ATENDIMENTO
        );

        return repository.save(prontuario);
    }

    public Prontuario buscarPorAtendimento(Long atendimentoId) {
        return repository.findByAtendimentoId(atendimentoId)
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado."));
    }

    @Transactional
    public void finalizar(Long atendimentoId) {
        atendimentoService.alterarStatus(
                atendimentoId,
                StatusAtendimento.FINALIZADO
        );
    }
}
