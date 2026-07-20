package com.sistemaupa.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AtendimentoRequest(
        @NotBlank String nome,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        @NotBlank String motivo,
        boolean ambulancia
) {
}
