package com.sistemaupa.controller;

import com.sistemaupa.dto.LoginRequest;
import com.sistemaupa.dto.UsuarioResponse;
import com.sistemaupa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public UsuarioResponse login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }
}
