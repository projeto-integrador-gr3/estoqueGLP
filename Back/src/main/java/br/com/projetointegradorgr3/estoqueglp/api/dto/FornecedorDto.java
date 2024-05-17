package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Fornecedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorDto(
        Integer id,

        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @Size(max = 100)
        String endereco,

        @NotBlank
        @Size(max = 100)
        String telefone
) {

    public Fornecedor converter() {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome(nome);
        fornecedor.setEndereco(endereco);
        fornecedor.setTelefone(telefone);

        return fornecedor;
    }

    public Fornecedor converterId() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);

        return fornecedor;
    }

    public FornecedorDto(Fornecedor fornecedor) {
        this(fornecedor.getId(), fornecedor.getNome(), fornecedor.getEndereco(), fornecedor.getTelefone());
    }
}
