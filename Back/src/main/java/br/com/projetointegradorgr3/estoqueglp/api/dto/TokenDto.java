package br.com.projetointegradorgr3.estoqueglp.api.dto;

public record TokenDto(
        String token,
        boolean admin
) {
}
