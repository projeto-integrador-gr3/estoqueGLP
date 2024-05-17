package br.com.projetointegradorgr3.estoqueglp.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank
        @Size(max = 100)
        String login,

        @NotBlank
        @Size(max = 100)
        String senha
) {
}
