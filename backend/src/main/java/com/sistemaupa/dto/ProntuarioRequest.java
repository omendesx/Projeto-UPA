package com.sistemaupa.dto;

import jakarta.validation.constraints.NotNull;

public record ProntuarioRequest(
        @NotNull Long atendimentoId,
        String observacaoMedica,
        String hipoteseClinica,
        String conduta
) {
}
