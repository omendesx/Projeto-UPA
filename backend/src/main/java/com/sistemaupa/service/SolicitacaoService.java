package com.sistemaupa.service;

import com.sistemaupa.dto.SolicitacaoRequest;
import com.sistemaupa.entity.Prontuario;
import com.sistemaupa.entity.Solicitacao;
import com.sistemaupa.enums.StatusSolicitacao;
import com.sistemaupa.repository.ProntuarioRepository;
import com.sistemaupa.repository.SolicitacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final ProntuarioRepository prontuarioRepository;

    public SolicitacaoService(
            SolicitacaoRepository repository,
            ProntuarioRepository prontuarioRepository
    ) {
        this.repository = repository;
        this.prontuarioRepository = prontuarioRepository;
    }

    public Solicitacao criar(SolicitacaoRequest request) {
        Prontuario prontuario = prontuarioRepository
                .findById(request.prontuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Prontuário não encontrado."));

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setProntuario(prontuario);
        solicitacao.setTipo(request.tipo());
        solicitacao.setDescricao(request.descricao());
        solicitacao.setPrioridade(request.prioridade());
        solicitacao.setStatus(StatusSolicitacao.SOLICITADO);

        return repository.save(solicitacao);
    }

    public List<Solicitacao> listarPorProntuario(Long prontuarioId) {
        return repository.findByProntuarioId(prontuarioId);
    }
}
