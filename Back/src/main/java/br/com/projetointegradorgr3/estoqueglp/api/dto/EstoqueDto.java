package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record EstoqueDto(

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Integer id,

        ProdutoDto produto,

        @JsonProperty("quantidade_entrada")
        Integer entrada,

        @JsonProperty("quantidade_saida")
        Integer saida,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("estoque_apos_operacao")
        Integer estoque,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("data_transacao")
        LocalDateTime data
) {

    public Transacao converter() {
        Transacao transacao = new Transacao();
        transacao.setProduto(produto.converterId());
        transacao.setEntradas(entrada);
        transacao.setVendas(saida);

        return transacao;
    }

    public EstoqueDto(Transacao transacao) {
        this(
                transacao.getId(),
                new ProdutoDto(transacao.getProduto()),
                transacao.getEntradas(),
                transacao.getVendas(),
                transacao.getEstoqueAposTransacao(),
                transacao.getData()
        );
    }
}
