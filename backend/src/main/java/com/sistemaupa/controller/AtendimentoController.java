package com.sistemaupa.controller;

import com.sistemaupa.dto.AtendimentoRequest;
import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    private final AtendimentoService service;

    public AtendimentoController(AtendimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Atendimento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Atendimento buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atendimento criar(@Valid @RequestBody AtendimentoRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    public Atendimento atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtendimentoRequest request
    ) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long id) {
        service.cancelar(id);
    }

    @GetMapping("/fila/triagem")
    public List<Atendimento> filaTriagem() {
        return service.filaTriagem();
    }

    @GetMapping("/fila/medico")
    public List<Atendimento> filaMedico() {
        return service.filaMedico();
    }
}
