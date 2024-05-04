package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record RevendedorDto(
        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        String login,

        @NotBlank
        String senha
) {


    public Revendedor converter() {
        Revendedor revendedor = new Revendedor();
        revendedor.setNome(nome);
        revendedor.setTelefone(telefone);

        Usuario usuario = new Usuario(login, senha);
        revendedor.setUsuario(usuario);

        return revendedor;
    }

    public RevendedorDto(Revendedor revendedor) {
        this(revendedor.getNome(), revendedor.getTelefone(), revendedor.getUsuario().getUsername(), null);
    }
}
