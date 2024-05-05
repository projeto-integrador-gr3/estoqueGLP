package br.com.projetointegradorgr3.estoqueglp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record EstoqueDto(@NotNull @JsonProperty("variacao_estoque") Integer variacaoEstoque) {
}
