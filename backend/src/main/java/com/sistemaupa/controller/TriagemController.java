package com.sistemaupa.controller;

import com.sistemaupa.dto.TriagemRequest;
import com.sistemaupa.entity.Triagem;
import com.sistemaupa.service.TriagemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/triagens")
public class TriagemController {

    private final TriagemService service;

    public TriagemController(TriagemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Triagem criar(@Valid @RequestBody TriagemRequest request) {
        return service.criar(request);
    }

    @GetMapping("/atendimento/{atendimentoId}")
    public Triagem buscarPorAtendimento(@PathVariable Long atendimentoId) {
        return service.buscarPorAtendimento(atendimentoId);
    }
}
