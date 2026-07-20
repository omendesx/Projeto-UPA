package com.sistemaupa.dto;

import com.sistemaupa.enums.Cargo;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Cargo cargo,
        boolean ativo
) {
}
