package br.com.projetointegradorgr3.estoqueglp.api.controller;

import br.com.projetointegradorgr3.estoqueglp.api.dto.RevendedorDto;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Revendedor;
import br.com.projetointegradorgr3.estoqueglp.domain.model.Usuario;
import br.com.projetointegradorgr3.estoqueglp.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<RevendedorDto> cadastrar(@RequestBody @Valid RevendedorDto revendedorDto, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioService.cadastrar(revendedorDto.converter().getUsuario());

        return ResponseEntity.created(uriComponentsBuilder.path("/revendedores/{id}")
                .buildAndExpand(usuario.getId())
                .toUri())
                .body(new RevendedorDto(new Revendedor()));
    }
}
