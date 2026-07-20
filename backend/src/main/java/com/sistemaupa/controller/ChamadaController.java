package com.sistemaupa.controller;

import com.sistemaupa.dto.ChamadaRequest;
import com.sistemaupa.entity.Chamada;
import com.sistemaupa.service.ChamadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamadas")
public class ChamadaController {

    private final ChamadaService service;

    public ChamadaController(ChamadaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamada criar(@Valid @RequestBody ChamadaRequest request) {
        return service.criar(request);
    }

    @GetMapping("/ultima")
    public Chamada ultima() {
        return service.ultima();
    }

    @GetMapping("/recentes")
    public List<Chamada> recentes() {
        return service.recentes();
    }
}
