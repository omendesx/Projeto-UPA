package com.sistemaupa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChamadaRequest(
        @NotNull Long atendimentoId,
        @NotBlank String destino,
        @NotBlank String sala
) {
}
