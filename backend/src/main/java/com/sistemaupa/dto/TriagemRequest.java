package com.sistemaupa.dto;

import com.sistemaupa.enums.ClassificacaoRisco;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TriagemRequest(
        @NotNull Long atendimentoId,
        String sintomas,
        String pressaoArterial,
        Double temperatura,
        Integer frequenciaCardiaca,
        @Min(0) @Max(10) Integer nivelDor,
        String observacao,
        @NotNull ClassificacaoRisco classificacaoRisco
) {
}
