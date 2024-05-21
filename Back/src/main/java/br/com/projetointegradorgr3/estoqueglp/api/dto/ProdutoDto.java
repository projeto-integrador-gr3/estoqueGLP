package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.exception.UnprocessableEntityException;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProdutoDto(

        Integer id,

        @NotBlank
        @Size(max = 100)
        String nome,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty("quantidade_estoque")
        Integer estoque,

        FornecedorDto fornecedor
) {

    public Produto converter() {
        Produto produto = new Produto();

        produto.setNome(nome);
        produto.setFornecedor(fornecedor.converterId());

        return produto;
    }

    public Produto converterId() {
        Produto produto = new Produto();
        if (id == null) {
            throw new UnprocessableEntityException("Id do produto não pode ser nulo");
        }
        produto.setId(id);

        return produto;
    }

    public ProdutoDto(Produto produto, int quantidadeEstoque) {
        this(
                produto.getId(),
                produto.getNome(),
                quantidadeEstoque,
                new FornecedorDto(produto.getFornecedor())
        );
    }

    public ProdutoDto(Produto produto) {
        this(produto, produto.getQuantidadeEstoque());
    }
}
