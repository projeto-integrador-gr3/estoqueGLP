package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoDto(String id,
                         @NotBlank String nome,
                         @NotBlank String descricao,
                         @Positive @NotNull BigDecimal preco,
                         String estoque,
                         LocalDateTime dataRecebimento) {

    public Produto converter() {
        Produto produto = new Produto();

        produto.setNome(nome);
        /*produto.setDescricao(descricao);
        produto.setPreco(preco);*/

        return produto;
    }

    public ProdutoDto(Produto produto) {
        this(
                String.valueOf(produto.getId()),
                produto.getNome(), null, null, null, null
                /*produto.getDescricao(),
                produto.getPreco(),
                String.valueOf(produto.getEstoque()),
                produto.getDataRecebimento()*/
        );
    }
}
