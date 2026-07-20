package com.sistemaupa.controller;

import com.sistemaupa.dto.ProntuarioRequest;
import com.sistemaupa.entity.Prontuario;
import com.sistemaupa.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioService service;

    public ProntuarioController(ProntuarioService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prontuario criar(@Valid @RequestBody ProntuarioRequest request) {
        return service.criar(request);
    }

    @GetMapping("/atendimento/{atendimentoId}")
    public Prontuario buscarPorAtendimento(@PathVariable Long atendimentoId) {
        return service.buscarPorAtendimento(atendimentoId);
    }

    @PatchMapping("/atendimento/{atendimentoId}/finalizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long atendimentoId) {
        service.finalizar(atendimentoId);
    }
}
