package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public record ClienteDto(String id,
                         @NotBlank String nome,
                         @NotBlank String endereco,
                         @NotBlank String telefone) {

    public Cliente toModel() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        return cliente;
    }

    public ClienteDto(Cliente cliente) {
        this(String.valueOf(cliente.getId()), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone());
    }
}
