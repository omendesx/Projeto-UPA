package com.sistemaupa.controller;

import com.sistemaupa.dto.SolicitacaoRequest;
import com.sistemaupa.entity.Solicitacao;
import com.sistemaupa.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService service;

    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Solicitacao criar(@Valid @RequestBody SolicitacaoRequest request) {
        return service.criar(request);
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public List<Solicitacao> listarPorProntuario(@PathVariable Long prontuarioId) {
        return service.listarPorProntuario(prontuarioId);
    }
}
