package br.com.projetointegradorgr3.estoqueglp.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        @Max(100)
        String login,

        @NotBlank
        @Max(100)
        String senha
) {
}
