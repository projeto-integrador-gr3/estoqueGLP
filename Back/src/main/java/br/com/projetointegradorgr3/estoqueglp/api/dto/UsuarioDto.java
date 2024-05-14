package br.com.projetointegradorgr3.estoqueglp.api.dto;

import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Usuario", description = "Informações do usuário da plataforma")
public record UsuarioDto(

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        Integer id,

        @NotBlank
        @Max(100)
        String nome,

        @Max(100)
        @NotBlank
        String login,

        @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
        @NotBlank
        @Max(100)
        String senha
) {


    public Usuario converter() {
        Usuario usuario = new Usuario(login, senha);
        usuario.setNome(nome);

        return usuario;
    }

    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getUsername(), null);
    }
}
