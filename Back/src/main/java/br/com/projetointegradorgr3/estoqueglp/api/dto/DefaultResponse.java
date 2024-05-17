package br.com.projetointegradorgr3.estoqueglp.api.dto;

import java.util.List;

public record DefaultResponse(List<String> mensagens) {

    public DefaultResponse(String mensagem) {
        this(List.of(mensagem));
    }
}
