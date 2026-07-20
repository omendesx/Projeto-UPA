package com.sistemaupa.dto;

import com.sistemaupa.enums.TipoSolicitacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoRequest(
        @NotNull Long prontuarioId,
        @NotNull TipoSolicitacao tipo,
        @NotBlank String descricao,
        String prioridade
) {
}
